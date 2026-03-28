
package org.itson.restaurante.persistencia;

import org.itson.restaurante.adapters.ClienteDTOAClienteFrecuenteDominioAdapter;
import org.itson.restaurante.dtos.ClienteFrecuenteActualizadoDTO;
import org.itson.restaurante.dtos.NuevoClienteFrecuenteDTO;
import org.itson.restaurante.dominio.Cliente;
import org.itson.restaurante.dominio.ClienteFrecuente;

import java.util.List;
import java.util.logging.Logger;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceException;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import org.itson.restaurante.utilerias.Encriptador;

/**
 *
 * @author oliro
 */
public class ClienteDAO implements IClienteDAO {

    private static final Logger LOGGER = Logger.getLogger(ClienteDAO.class.getName());

    @Override
    public Cliente registrarClienteFrecuente(NuevoClienteFrecuenteDTO nuevoCliente) throws PersistenciaException {
        
        Cliente cliente = ClienteDTOAClienteFrecuenteDominioAdapter.adapter(nuevoCliente);
        EntityManager em = null;
        
        try{
            em = ManejadorConexiones.crearEntityManager();
            em.getTransaction().begin();
            em.persist(cliente);
            em.getTransaction().commit();
            return cliente;
        } catch (PersistenceException ex) {
            LOGGER.severe(ex.getMessage());
            throw new PersistenciaException("No ha sido posible agregar al nuevo cliente frecuente.", ex);
        } finally {
            if (em != null) {
                em.close();
            }
        } 
        
    }

    @Override
    public ClienteFrecuente actualizarClienteFrecuente(ClienteFrecuenteActualizadoDTO clienteActualizado) throws PersistenciaException {
        
        try {
            
            EntityManager em = ManejadorConexiones.crearEntityManager();
            em.getTransaction().begin();
            ClienteFrecuente clienteGuardado = em.find(ClienteFrecuente.class, clienteActualizado.getId());
            
            clienteGuardado.setNombre(clienteActualizado.getNombre());
            clienteGuardado.setApellidoPaterno(clienteActualizado.getApellidoPaterno());
            clienteGuardado.setApellidoMaterno(clienteActualizado.getApellidoMaterno());
            clienteGuardado.setTelefono(clienteActualizado.getTelefono());
            
            if(clienteActualizado.getCorreo() != null && !clienteActualizado.getCorreo().isBlank()){
                clienteGuardado.setCorreo(clienteActualizado.getCorreo());
            }
            
            em.persist(clienteGuardado);
            em.getTransaction().commit();
            return clienteGuardado;
            
        } catch (PersistenceException ex) {
            LOGGER.severe(ex.getMessage());
            throw new PersistenciaException("No ha sido posible actualizar al cliente frecuente", ex);
        }
        
    }

    @Override
    public Cliente eliminarCliente(Long id) throws PersistenciaException {

        EntityManager em = null;
        
        try {
            em = ManejadorConexiones.crearEntityManager();
            em.getTransaction().begin();
            Cliente cliente = em.find(ClienteFrecuente.class, id);
            em.remove(cliente);
            em.getTransaction().commit();
            return cliente;
        } catch (PersistenceException ex) {
            LOGGER.severe(ex.getMessage());
            throw new PersistenciaException("No ha sido posible eliminar al cliente frecuente", ex);
        } finally {
            if (em != null) {
                em.close();
            }
        }   
        
    }

    @Override
    public List<ClienteFrecuente> consultarClientesFrecuentes() throws PersistenciaException {
        
        EntityManager em = null;
        
        try {
            em = ManejadorConexiones.crearEntityManager();
            String JPQL = "SELECT cf FROM ClienteFrecuente cf";
            TypedQuery<ClienteFrecuente> query = em.createQuery(JPQL, ClienteFrecuente.class);
            return query.getResultList();
        } catch (PersistenceException ex) {
            LOGGER.severe(ex.getMessage());
            throw new PersistenciaException("No ha sido posible consultar los clientes frecuentes", ex);
        } finally {
            if (em != null) {
                em.close();
            }
        }   
        
                
    }

    @Override
    public List<ClienteFrecuente> consultarClientesFrecuentesFiltro(String filtro) throws PersistenciaException {
        
        EntityManager em = null;
        
        try {
            em = ManejadorConexiones.crearEntityManager();
            CriteriaBuilder cb = em.getCriteriaBuilder();
            CriteriaQuery<ClienteFrecuente> cq = cb.createQuery(ClienteFrecuente.class);
            Root<ClienteFrecuente> cliente = cq.from(ClienteFrecuente.class);
            
            // En caso de que no haya filtro, traeremos toda las lista
            if (filtro == null || filtro.isBlank()) {
                return em.createQuery(cq).getResultList();
            }
            
            // Limpiamos el filtro en caso de tener espacios en blanco a los lados
            String filtroLimpio = filtro.trim();
            String patron = "%" + filtroLimpio.toLowerCase() + "%";
            // Para buscar en la BD tendremos que encriptar el filtro apra poder buscar en los datos sencibles
            String patronEncriptado = Encriptador.convertToDatabaseColumn(filtroLimpio);
            
            Predicate pNombre = cb.like(cb.lower(cliente.get("nombre")), patron);
            Predicate pApellidoP = cb.like(cb.lower(cliente.get("apellidoPaterno")), patron);
            Predicate pApellidoM = cb.like(cb.lower(cliente.get("apellidoMaterno")), patron);
            
            // En estos predicate buscamos que el encriptado sea igual, utilizamos un trim por que la BD nos devuelve
            // el tamaño maximo que tiene el atributo agregando espaciados para completar.
            Predicate pTelefono = cb.equal(cb.trim(cliente.get("telefono")), patronEncriptado);
            Predicate pCorreo = cb.equal(cb.trim(cliente.get("correo")), patronEncriptado);
            
            // Unimos los predicados
            cq.where(cb.or(pNombre, pTelefono, pCorreo, pApellidoP, pApellidoM));
            
            return em.createQuery(cq).getResultList();
        } catch (PersistenceException ex) {
            LOGGER.severe(ex.getMessage());
            throw new PersistenciaException("No ha sido posible consultar los clientes frecuentes", ex);
        } finally {
            if (em != null) {
                em.close();
            }
        }       
        
    }
    
    
    
}
