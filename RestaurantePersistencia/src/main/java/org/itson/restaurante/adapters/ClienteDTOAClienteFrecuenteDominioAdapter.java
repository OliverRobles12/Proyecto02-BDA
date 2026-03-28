
package org.itson.restaurante.adapters;

import org.itson.restaurante.dtos.NuevoClienteFrecuenteDTO;
import org.itson.restaurante.dominio.Cliente;
import org.itson.restaurante.dominio.ClienteFrecuente;

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
