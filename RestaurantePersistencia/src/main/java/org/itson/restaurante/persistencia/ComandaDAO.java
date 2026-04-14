
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
import org.itson.restaurante.dominio.Mesa;
import org.itson.restaurante.dominio.Producto;
import org.itson.restaurante.dominio.ProductoComanda;
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
            
            for (NuevoProductoComandaDTO productoComandaDTO : nuevaComanda.getProductosComanda()) {
                Producto producto = em.find(Producto.class, productoComandaDTO.getIdProducto());
                ProductoComanda pc = new ProductoComanda(
                        productoComandaDTO.getCantidad(), 
                        productoComandaDTO.getDetalles(), 
                        productoComandaDTO.getSubtotal(), 
                        comanda, 
                        producto
                );
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

    
}
