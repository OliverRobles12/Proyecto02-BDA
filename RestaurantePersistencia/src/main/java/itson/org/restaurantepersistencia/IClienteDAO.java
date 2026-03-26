
package itson.org.restaurantepersistencia;

import com.mycompany.restaurantedtos.ClienteFrecuenteActualizadoDTO;
import com.mycompany.restaurantedtos.NuevoClienteFrecuenteDTO;
import itson.org.restaurantedominio.Cliente;

/**
 *
 * @author oliro
 */
public interface IClienteDAO {

    public abstract Cliente registrarCliente(NuevoClienteFrecuenteDTO nuevoCliente) throws PersistenciaException;
    
    public abstract Cliente actualizarCliente(ClienteFrecuenteActualizadoDTO clienteActualizado) throws PersistenciaException;
    
    public abstract Cliente eliminarCliente() throws PersistenciaException;
    
}
