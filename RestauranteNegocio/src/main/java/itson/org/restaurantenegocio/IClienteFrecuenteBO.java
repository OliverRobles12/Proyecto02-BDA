/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package itson.org.restaurantenegocio;

import com.mycompany.restaurantedtos.ClienteFrecuenteActualizadoDTO;
import com.mycompany.restaurantedtos.NuevoClienteFrecuenteDTO;
import itson.org.restaurantedominio.Cliente;
import itson.org.restaurantedominio.ClienteFrecuente;

/**
 *
 * @author oliro
 */
public interface IClienteFrecuenteBO {
    
    public abstract Cliente registrarCliente(NuevoClienteFrecuenteDTO nuevoCliente) throws NegocioException;;
    
    public abstract Cliente actualizarCliente(ClienteFrecuenteActualizadoDTO nuevoCliente) throws NegocioException;;
    
    public abstract Cliente EliminarCliente() throws NegocioException;;
    
}
