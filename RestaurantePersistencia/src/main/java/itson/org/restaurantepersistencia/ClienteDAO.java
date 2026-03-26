
package itson.org.restaurantepersistencia;

import adapters.ClienteDTOAClienteFrecuenteDominioAdapter;
import com.mycompany.restaurantedtos.ClienteFrecuenteActualizadoDTO;
import com.mycompany.restaurantedtos.NuevoClienteFrecuenteDTO;
import itson.org.restaurantedominio.Cliente;
import itson.org.restaurantedominio.ClienteFrecuente;
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

/**
 *
 * @author oliro
 */
public class ClienteDAO implements IClienteDAO {

    private static final Logger LOGGER = Logger.getLogger(ClienteDAO.class.getName());

    @Override
    public Cliente registrarCliente(NuevoClienteFrecuenteDTO nuevoCliente) throws PersistenciaException {
        
        Cliente cliente = ClienteDTOAClienteFrecuenteDominioAdapter.adapter(nuevoCliente);
        
        try {
            EntityManager em = ManejadorConexiones.crearEntityManager();
            em.getTransaction().begin();
            em.persist(cliente);
            em.getTransaction().commit();
            return cliente;
        } catch (PersistenceException ex) {
            LOGGER.severe(ex.getMessage());
            throw new PersistenciaException("No ha sido posible agregar al nuevo cliente frecuente.", ex);
        }
        
    }

    @Override
    public Cliente actualizarCliente(ClienteFrecuenteActualizadoDTO clienteActualizado) throws PersistenciaException {
        
        try {
            
            EntityManager em = ManejadorConexiones.crearEntityManager();
            em.getTransaction().begin();
            Cliente clienteGuardado = em.find(ClienteFrecuente.class, clienteActualizado.getId());
            
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
         
        try {
            
            EntityManager em = ManejadorConexiones.crearEntityManager();
            em.getTransaction().begin();
            Cliente cliente = em.find(ClienteFrecuente.class, id);
            em.remove(cliente);
            em.getTransaction().commit();
            return cliente;
            
        } catch (PersistenceException ex) {
            LOGGER.severe(ex.getMessage());
            throw new PersistenciaException("No ha sido posible eliminar al cliente frecuente", ex);
        }
        
    }

    @Override
    public List<ClienteFrecuente> consultarClientesFrecuentes() throws PersistenciaException {
        
        try {
            
            EntityManager em = ManejadorConexiones.crearEntityManager();
            String JPQL = "SELECT cf FROM ClienteFrecuente cf";
            TypedQuery<ClienteFrecuente> query = em.createQuery(JPQL, ClienteFrecuente.class);
            return query.getResultList();
            
        } catch (PersistenceException ex) {
            LOGGER.severe(ex.getMessage());
            throw new PersistenciaException("No ha sido posible consultar los clientes frecuentes", ex);
        }
        
                
    }

    @Override
    public List<ClienteFrecuente> consultarClientesFrecuentesFiltro(String nombre, String telefono, String correo) throws PersistenciaException {
        
        try {
            
            EntityManager em = ManejadorConexiones.crearEntityManager();
            CriteriaBuilder cb = em.getCriteriaBuilder();
            CriteriaQuery<ClienteFrecuente> cq = cb.createQuery(ClienteFrecuente.class);
            Root<ClienteFrecuente> cliente = cq.from(ClienteFrecuente.class);
            
            // Guardamos las condiciones que se cumplen
            List<Predicate> predicados = new ArrayList<>();
            
            if(nombre != null && !nombre.isBlank()) {
                predicados.add(cb.like(cliente.get("nombre"), "%" + nombre + "%"));
            }
            
            if(telefono != null && !telefono.isBlank()) {
                predicados.add(cb.equal(cliente.get("telefono"), telefono));
            }
            
            if(correo != null && !correo.isBlank()) {
                predicados.add(cb.equal(cliente.get("correo"), correo));
            }
            
            if(!predicados.isEmpty()) {
                cq.where(cb.and(predicados.toArray(new Predicate[0])));
            }
                    
            return em.createQuery(cq).getResultList();
            
        } catch (PersistenceException ex) {
            LOGGER.severe(ex.getMessage());
            throw new PersistenciaException("No ha sido posible consultar los clientes frecuentes", ex);
        }
        
    }
    
    
}
