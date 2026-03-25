
package itson.org.restaurantepersistencia;

import itson.org.restaurantedominio.Cliente;
import java.util.logging.Logger;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceException;

/**
 *
 * @author oliro
 */
public class ClienteDAO {

    private static final Logger LOGGER = Logger.getLogger(ClienteDAO.class.getName());
    
    public Cliente crearClientePrueba(){
        
        Cliente nuevoCliente = new Cliente("Oliver");
        
        try {
            EntityManager em = ManejadorConexiones.crearEntityManager();
            em.getTransaction().begin();
            em.persist(nuevoCliente);
            em.getTransaction().commit();
            return nuevoCliente;
        } catch (PersistenceException ex) {
            LOGGER.severe("Crear cliente fallo.");
            throw new IllegalArgumentException("No se puedo crear el cliente");
        }
        
    }
    
}
