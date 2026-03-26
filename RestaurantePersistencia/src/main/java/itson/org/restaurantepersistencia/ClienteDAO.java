
package itson.org.restaurantepersistencia;

import adapters.ClienteDTOAClienteFrecuenteDominioAdapter;
import com.mycompany.restaurantedtos.ClienteFrecuenteActualizadoDTO;
import com.mycompany.restaurantedtos.NuevoClienteFrecuenteDTO;
import itson.org.restaurantedominio.Cliente;
import java.util.logging.Logger;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceException;

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
            return null;
        } catch (PersistenceException ex) {
            LOGGER.severe(ex.getMessage());
            throw new PersistenciaException("No ha sido posible actualizar al cliente frecuente", ex);
        }
        
    }

    @Override
    public Cliente eliminarCliente() throws PersistenciaException {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }
    
}
