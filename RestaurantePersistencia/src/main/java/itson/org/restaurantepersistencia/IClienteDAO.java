
package itson.org.restaurantepersistencia;

import com.mycompany.restaurantedtos.ClienteFrecuenteActualizadoDTO;
import com.mycompany.restaurantedtos.NuevoClienteFrecuenteDTO;
import itson.org.restaurantedominio.Cliente;
import itson.org.restaurantedominio.ClienteFrecuente;
import java.util.List;

/**
 *
 * @author oliro
 */
public interface IClienteDAO {

    public abstract Cliente registrarCliente(NuevoClienteFrecuenteDTO nuevoCliente) throws PersistenciaException;
    
    public abstract Cliente actualizarCliente(ClienteFrecuenteActualizadoDTO clienteActualizado) throws PersistenciaException;
    
    public abstract Cliente eliminarCliente(Long id) throws PersistenciaException;
    
    public abstract List<ClienteFrecuente> consultarClientesFrecuentes() throws PersistenciaException;
    
    public abstract List<ClienteFrecuente> consultarClientesFrecuentesFiltro(String nombre, String telefono, String correo) throws PersistenciaException;
    
}
