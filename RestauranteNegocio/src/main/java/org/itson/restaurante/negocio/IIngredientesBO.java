/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package org.itson.restaurante.negocio;

import java.util.List;
import org.itson.restaurante.dtos.IngredienteDTO;
import org.itson.restaurante.dtos.NuevoIngredienteDTO;

/**
 *
 * @author joset
 */
public interface IIngredientesBO {

    public abstract IngredienteDTO registrarIngrediente(NuevoIngredienteDTO nuevoIngrediente) throws NegocioException;

    public abstract IngredienteDTO actualizarIngrediente(IngredienteDTO ingrediente) throws NegocioException;

    public abstract List<IngredienteDTO> consultarIngredientesFiltro(String filtro) throws NegocioException;

    public abstract List<IngredienteDTO> consultarIngredientes() throws NegocioException;
    
    public abstract IngredienteDTO eliminarIngrediente(IngredienteDTO nuevoIngrediente) throws NegocioException; 
    
    public abstract IngredienteDTO consultarIngredientesFiltro(Long id) throws NegocioException;
}
