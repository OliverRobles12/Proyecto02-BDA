/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package org.itson.restaurante.persistencia;

import java.util.List;
import org.itson.restaurante.dominio.Ingrediente;
import org.itson.restaurante.dtos.IngredienteActualizadoDTO;
import org.itson.restaurante.dtos.IngredienteDTO;
import org.itson.restaurante.dtos.NuevoIngredienteDTO;
import org.itson.restaurante.dtos.UnidadMedidaDTO;

/**
 *
 * @author joset
 */

public interface IIngredientesDAO {
    
    public Ingrediente registrarIngrediente(NuevoIngredienteDTO nuevoIngrediente) throws PersistenciaException;
    
    public Ingrediente actualizarIngrediente(IngredienteActualizadoDTO ingrediente) throws PersistenciaException;
    
    public List<Ingrediente> consultarIngredientes() throws PersistenciaException;
    
    public List<Ingrediente> consultarIngredientesFiltro(String nombre) throws PersistenciaException;
    
    public Ingrediente eliminarIngrediente(Long id) throws PersistenciaException;
    
    public Ingrediente consultarIngrediente(Long id) throws PersistenciaException;
    
    public boolean existeIngredienteEnProductos(IngredienteDTO ingrediente) throws PersistenciaException;
    
    public boolean existeIngrediente(Long idExcluir,String nombre, UnidadMedidaDTO unidad) throws PersistenciaException; // METODO QUE DEVOLVERA SI HAY UN INGREDIENTE CON LOS MISMOS DATOS QUE SE ESTAN INGRESANDO
}