package org.itson.restaurante.negocio;

import java.util.List;
import org.itson.restaurante.adapters.IngredienteAIngredienteDTO;
import org.itson.restaurante.dtos.IngredienteDTO;
import org.itson.restaurante.dtos.NuevoIngredienteDTO;
import org.itson.restaurante.persistencia.IIngredientesDAO;
import org.itson.restaurante.dominio.Ingrediente;
import org.itson.restaurante.dtos.IngredienteActualizadoDTO;
import org.itson.restaurante.persistencia.IngredientesDAO;
import org.itson.restaurante.persistencia.PersistenciaException;

public class IngredientesBO implements IIngredientesBO {

    private IIngredientesDAO ingredientesDAO;

    public IngredientesBO() {
        this.ingredientesDAO = new IngredientesDAO();
    }

    

    @Override
    public IngredienteDTO registrarIngrediente(NuevoIngredienteDTO nuevoIngrediente) throws NegocioException {
        
        if (nuevoIngrediente == null)
            
            throw new NegocioException("El ingrediente no puede ser nulo", null);
        
        
        if (nuevoIngrediente.getNombre() == null || nuevoIngrediente.getNombre().isBlank())
            
            throw new NegocioException("El nombre no puede estar vacío", null);
        
        if (!nuevoIngrediente.getNombre().matches("[a-zA-ZÁÉÍÓÚáéíóúÑñ ]+"))
            
            throw new NegocioException("El nombre no puede contener números", null);
        
        if (nuevoIngrediente.getUnidadMedida() == null)
            
            throw new NegocioException("La unidad de medida no puede ser nula", null);
        
        if (nuevoIngrediente.getStock() == null || nuevoIngrediente.getStock() < 0)
            
            throw new NegocioException("El stock no puede ser nulo ni negativo", null);
        
        try {
            
            Ingrediente ingrediente = ingredientesDAO.registrarIngrediente(nuevoIngrediente);
            
            return IngredienteAIngredienteDTO.convertirADTO(ingrediente);
            
        } catch (PersistenciaException ex) {
            
            throw new NegocioException("No fue posible registrar el ingrediente", ex);
            
        }
    }

    @Override
    public IngredienteDTO actualizarIngrediente(IngredienteDTO ingrediente) throws NegocioException {
        
        if (ingrediente == null)
            
            throw new NegocioException("El ingrediente no puede ser nulo", null);
        
        if (ingrediente.getId() == null)
            
            throw new NegocioException("El id no puede ser nulo", null);
        
        if (ingrediente.getNombre() == null || ingrediente.getNombre().isBlank())
            
            throw new NegocioException("El nombre no puede estar vacío", null);
        
        if (!ingrediente.getNombre().matches("[a-zA-ZÁÉÍÓÚáéíóúÑñ ]+"))
            
            throw new NegocioException("El nombre no puede contener números", null);
        
        if (ingrediente.getStock() == null || ingrediente.getStock() < 0)
            
            throw new NegocioException("El stock no puede ser nulo ni negativo", null);
        
        try {
            IngredienteActualizadoDTO inrgedienteActualizado = new IngredienteActualizadoDTO( 
                    ingrediente.getId(),
                    ingrediente.getNombre(),
                    ingrediente.getStock(),
                    ingrediente.getImagen()
            );
            Ingrediente actualizado = ingredientesDAO.actualizarIngrediente(inrgedienteActualizado);
            
            
            return IngredienteAIngredienteDTO.convertirADTO(actualizado);
            
        } catch (PersistenciaException ex) {
            
            throw new NegocioException("No fue posible actualizar el ingrediente", ex);
            
        }
    }

    @Override
    public List<IngredienteDTO> consultarIngredientes() throws NegocioException {
        
        try {
            
            List<Ingrediente> ingredientes = ingredientesDAO.consultarIngredientes();
            
            return IngredienteAIngredienteDTO.convertirListaIngredientesADTO(ingredientes);
            
        } catch (PersistenciaException ex) {
            
            throw new NegocioException("No fue posible consultar los ingredientes", ex);
            
        }
    }

    @Override
    public List<IngredienteDTO> consultarIngredientesFiltro(String filtro) throws NegocioException {
        
        try {
            
            List<Ingrediente> todos = ingredientesDAO.consultarIngredientesFiltro(filtro);
            
            return IngredienteAIngredienteDTO.convertirListaIngredientesADTO(todos);
            
        } catch (PersistenciaException ex) {
            
            throw new NegocioException("No fue posible filtrar los ingredientes", ex);
        }
    }

}