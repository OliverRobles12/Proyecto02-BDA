
package adapters;

import com.mycompany.restaurantedtos.NuevoClienteFrecuenteDTO;
import itson.org.restaurantedominio.Cliente;
import itson.org.restaurantedominio.ClienteFrecuente;

/**
 *
 * @author oliro
 */
public class ClienteDTOAClienteFrecuenteDominioAdapter {

    public static Cliente adapter(NuevoClienteFrecuenteDTO nuevoCliente) {
        
        Cliente cliente = new ClienteFrecuente(
                nuevoCliente.getNombre(), 
                nuevoCliente.getApellidoPaterno(), 
                nuevoCliente.getApellidoMaterno(), 
                nuevoCliente.getTelefono(), 
                nuevoCliente.getFechaRegistro()
        );
        
        if(nuevoCliente.getCorreo() != null && !nuevoCliente.getCorreo().isBlank()){
            cliente.setCorreo(nuevoCliente.getCorreo());
        }
        
        return cliente;
        
    }
    
}
