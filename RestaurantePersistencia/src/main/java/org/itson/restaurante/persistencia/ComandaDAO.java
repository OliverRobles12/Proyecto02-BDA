
package org.itson.restaurante.persistencia;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Logger;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceException;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import org.itson.restaurante.dominio.Cliente;
import org.itson.restaurante.dominio.ClienteFrecuente;
import org.itson.restaurante.dominio.Comanda;
import org.itson.restaurante.dominio.EstadoComanda;
import org.itson.restaurante.dominio.EstadoMesa;
import org.itson.restaurante.dominio.Ingrediente;
import org.itson.restaurante.dominio.Mesa;
import org.itson.restaurante.dominio.Producto;
import org.itson.restaurante.dominio.ProductoComanda;
import org.itson.restaurante.dominio.ProductoIngrediente;
import org.itson.restaurante.dtos.NuevaComandaDTO;
import org.itson.restaurante.dtos.NuevoProductoComandaDTO;
import org.itson.restaurante.utilerias.GeneradorFolio;

/**
 *
 * @author oliro
 */
public class ComandaDAO implements IComandaDAO{

    private static final Logger LOGGER = Logger.getLogger(ComandaDAO.class.getName());
    
    @Override
    public Comanda registrarNuevaComanda(NuevaComandaDTO nuevaComanda) throws PersistenciaException {
        
        EntityManager em = null;
        
        try {
            
            em = ManejadorConexiones.crearEntityManager();
            em.getTransaction().begin();
            
            Mesa mesa = em.find(Mesa.class, nuevaComanda.getIdMesa());
            mesa.setEstado(EstadoMesa.OCUPADA);
            
            Cliente cliente = null;
            if (nuevaComanda.getIdCliente() != null) {
                cliente = em.find(Cliente.class, nuevaComanda.getIdCliente());
            }
            
            Comanda comanda = new Comanda(
                    "", 
                    EstadoComanda.ABIERTA, 
                    0d, 
                    LocalDateTime.now(), 
                    mesa, 
                    cliente, 
                    new ArrayList<>()
            );

            em.persist(comanda);
            em.flush();
            
            String folio = GeneradorFolio.generarFolio(comanda.getId());
            comanda.setFolio(folio);
            
            Double totalAcumulado = 0d;
            
            // Recorremos la lista de productos
            for (NuevoProductoComandaDTO productoComandaDTO : nuevaComanda.getProductosComanda()) {
                // Buscamos el producto
                Producto producto = em.find(Producto.class, productoComandaDTO.getIdProducto());
                // Creamos un producto comanda y lo asociamos
                ProductoComanda pc = new ProductoComanda(
                        productoComandaDTO.getCantidad(), 
                        productoComandaDTO.getDetalles(), 
                        productoComandaDTO.getSubtotal(), 
                        comanda, 
                        producto
                );
                // Restamos el stock a los ingredientes recorriento por cada elemento de la receta
                for (ProductoIngrediente productoIngrediente : producto.getReceta()) {
                    // Por efectos de salud mental y 0 conflictos con el equipo, redondearemos hacia arriba. Mejor que sobre a que falte.
                    Double cantidadIngredienteRequerida = productoIngrediente.getCantidad() * productoComandaDTO.getCantidad();
                    Integer cantidadDescontar = (int) Math.ceil(cantidadIngredienteRequerida);
                    
                    Ingrediente ingrediente = productoIngrediente.getIngrediente();
                    Integer stockIngredienteActual = ingrediente.getStock();
                    ingrediente.setStock(stockIngredienteActual - cantidadDescontar);
                    em.merge(ingrediente);
                    
                }
                
                comanda.getProductos().add(pc);
                totalAcumulado = totalAcumulado + productoComandaDTO.getSubtotal();
            }
            
            if (cliente instanceof ClienteFrecuente) {
                ClienteFrecuente clienteFrecuente = (ClienteFrecuente) cliente;
                int puntosGanados = (int) (totalAcumulado / 20);
                Integer puntosActuales = clienteFrecuente.getPuntosAcumulados();
                clienteFrecuente.setPuntosAcumulados(puntosActuales + puntosGanados);
                
                Integer numeroVisitas = clienteFrecuente.getNumeroVisitas();
                clienteFrecuente.setNumeroVisitas(numeroVisitas + 1);
                
                Double totalGastado = clienteFrecuente.getTotalGastado();
                clienteFrecuente.setTotalGastado(totalGastado + totalAcumulado);
            }
            
            comanda.setTotalAcumulado(totalAcumulado);
            em.getTransaction().commit();
            
            return comanda;
            
        } catch (PersistenceException ex){
            if (em != null && em.getTransaction().isActive()) {
                em.getTransaction().rollback();
            }
            LOGGER.severe(ex.getMessage());
            throw new PersistenciaException("No ha sido posible registrar la nueva comanda.", ex);
        } finally {
            if (em != null) {
                em.close();
            }
        }
        
    }
    
    @Override
    public List<Comanda> consultarComandas() throws PersistenciaException {
        
        EntityManager em = null;
        
        try {
            
            em = ManejadorConexiones.crearEntityManager();
            String JPQL = "SELECT c FROM Comanda c";
            TypedQuery<Comanda> query = em.createQuery(JPQL, Comanda.class);
            return query.getResultList();
            
        } catch (PersistenceException ex){
            LOGGER.severe(ex.getMessage());
            throw new PersistenciaException("No ha sido posible consultar las comandas registradas.", ex);
        } finally {
            if (em != null) {
                em.close();
            }
        }
        
    }

    @Override
    public List<Comanda> consultarComandasFecha(LocalDate fechaInicio, LocalDate fechaFin) throws PersistenciaException {
        EntityManager em = null;
        try {
            em = ManejadorConexiones.crearEntityManager(); 
            CriteriaBuilder cb = em.getCriteriaBuilder();
            CriteriaQuery<Comanda> cq = cb.createQuery(Comanda.class);
            Root<Comanda> root = cq.from(Comanda.class);
            
            LocalDateTime inicio = fechaInicio.atStartOfDay();
            LocalDateTime fin = fechaFin.atTime(LocalTime.MAX);
            
            Predicate rango = cb.between(root.get("fechaHora"), inicio, fin);
            
            cq.select(root).where(rango);
            cq.orderBy(cb.desc(root.get("fechaHora")));
            
            TypedQuery<Comanda> query = em.createQuery(cq);
            return query.getResultList();
            
        } catch (PersistenceException ex) {
            LOGGER.severe(ex.getMessage());
            throw new PersistenciaException("No ha sido posible consultar comandas con el rango de fechas.", ex);
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    @Override
    public boolean actualizarComanda(Comanda comanda) throws PersistenciaException {
        
        EntityManager em = null;
        
        try {
            em = ManejadorConexiones.crearEntityManager();
            em.getTransaction().begin();
            em.merge(comanda);
            em.getTransaction().commit();
            return true;
            
        } catch (PersistenceException ex) {
            if (em != null && em.getTransaction().isActive()) {
                em.getTransaction().rollback();
            }
            LOGGER.severe("Error al actualizar la comanda: " + ex.getMessage());
            throw new PersistenciaException("No ha sido posible actualizar la comanda con folio: " + comanda.getFolio(), ex);
        } finally {
            if (em != null) {
                em.close();
            }
        }
        
    }

    @Override
    public Comanda consultarComanda(String folio) throws PersistenciaException {
        
        EntityManager em = null;
        
        try {
            em = ManejadorConexiones.crearEntityManager();
            
            String jpql = "SELECT c FROM Comanda c WHERE c.folio = :folio";
            TypedQuery<Comanda> query = em.createQuery(jpql, Comanda.class);
            query.setParameter("folio", folio);
            // Si el folio no existe devolvemos null.
            return query.getResultStream().findFirst().orElse(null);
            
        } catch (PersistenceException ex) {
            LOGGER.severe("Error al consultar la comanda por folio: " + ex.getMessage());
            throw new PersistenciaException("No ha sido posible consultar la comanda con folio: " + folio, ex);
        } finally {
            if (em != null) {
                em.close();
            }
        }
        
    }

    
}
