/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package org.itson.restaurante.negocio;

import org.itson.restaurante.dtos.ClienteFrecuenteActualizadoDTO;
import org.itson.restaurante.dtos.ClienteFrecuenteDTO;
import org.itson.restaurante.dtos.NuevoClienteFrecuenteDTO;
import java.util.List;

/**
 *
 * @author oliro
 */
public interface IClienteFrecuenteBO {
    
    public abstract ClienteFrecuenteDTO registrarCliente(NuevoClienteFrecuenteDTO nuevoCliente) throws NegocioException;
    
    public abstract ClienteFrecuenteDTO actualizarCliente(ClienteFrecuenteDTO nuevoCliente) throws NegocioException;
    
    public abstract  List<ClienteFrecuenteDTO> consultarClienteFiltro(String filtro) throws NegocioException;
    
    public abstract  List<ClienteFrecuenteDTO> consultarClientes( ) throws NegocioException;
    
}
