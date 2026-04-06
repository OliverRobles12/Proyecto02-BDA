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

    /**
     * Registra un nuevo ingrediente en el sistema tras validar sus datos.
     * Valida que los campos obligatorios no estén vacíos, que los nombres no
     * contengan números, y que la unidadMedida y Stock esten dentro de los
     * parametros .
     *
     * @param nuevoIngrediente Objeto DTO con los datos del nuevo ingrediente a
     * registrar.
     * @return IngredienteDTO Objeto DTO con los datos del ingrediente ya registrado
     * @throws NegocioException Si algún dato es inválido o si ocurre un error
     * en la capa de persistencia.
     */
    @Override
    public IngredienteDTO registrarIngrediente(NuevoIngredienteDTO nuevoIngrediente) throws NegocioException {

        if (nuevoIngrediente == null) {
            throw new NegocioException("El ingrediente no puede ser nulo", null);
        }
        if (nuevoIngrediente.getNombre() == null || nuevoIngrediente.getNombre().isBlank()) {
            throw new NegocioException("El nombre no puede estar vacío", null);
        }
        if (!nuevoIngrediente.getNombre().matches("[a-zA-ZÁÉÍÓÚáéíóúÑñ ]+")) {
            throw new NegocioException("El nombre no puede contener números", null);
        }
        if (nuevoIngrediente.getUnidadMedida() == null) {
            throw new NegocioException("La unidad de medida no puede ser nula", null);
        }
        if (nuevoIngrediente.getStock() == null || nuevoIngrediente.getStock() < 0) {
            throw new NegocioException("El stock no puede ser nulo ni negativo", null);
        }

        try {
            if (ingredientesDAO.existeIngrediente(null,nuevoIngrediente.getNombre(), nuevoIngrediente.getUnidadMedida())) {
                throw new NegocioException("Ya existe un ingrediente con estas caracteristicas" + nuevoIngrediente.getNombre() + " y " + nuevoIngrediente.getUnidadMedida(), null);
            }
            Ingrediente ingrediente = ingredientesDAO.registrarIngrediente(nuevoIngrediente);

            return IngredienteAIngredienteDTO.convertirADTO(ingrediente);
        } catch (PersistenciaException ex) {
            throw new NegocioException("No fue posible registrar el ingrediente", ex);
        }
    }
    /**
     * Actualiza un ingrediente del sistema tras validar los datos ingresados.
     * Valida que los campos obligatorios no estén vacíos, que los nombres no
     * contengan números, y que la unidadMedida y Stock esten dentro de los
     * parametros .
     *
     * @param ingredienteViejo Objeto DTO con los datos del ingrediente a
     * actualizar.
     * @return IngredienteDTO Objeto DTO con los datos del ingrediente ya actualizado
     * @throws NegocioException Si algún dato es inválido o si ocurre un error
     * en la capa de persistencia.
     */
    @Override
    public IngredienteDTO actualizarIngrediente(IngredienteDTO ingredienteViejo) throws NegocioException {

        if (ingredienteViejo == null) {
            throw new NegocioException("El ingrediente no puede ser nulo", null);
        }
        if (ingredienteViejo.getId() == null) {
            throw new NegocioException("El id no puede ser nulo", null);
        }
        if (ingredienteViejo.getNombre() == null || ingredienteViejo.getNombre().isBlank()) {
            throw new NegocioException("El nombre no puede estar vacío", null);
        }
        if (!ingredienteViejo.getNombre().matches("[a-zA-ZÁÉÍÓÚáéíóúÑñ ]+")) {
            throw new NegocioException("El nombre no puede contener números", null);
        }
        if (ingredienteViejo.getStock() == null || ingredienteViejo.getStock() < 0) {
            throw new NegocioException("El stock no puede ser nulo ni negativo", null);
        }

        try {
            if (ingredientesDAO.existeIngrediente(ingredienteViejo.getId(),ingredienteViejo.getNombre(), ingredienteViejo.getUnidadMedida())) {
                throw new NegocioException("Ya existe un ingrediente con estas caracteristicas" + ingredienteViejo.getNombre() + " y " + ingredienteViejo.getUnidadMedida(), null);
            }
            IngredienteActualizadoDTO inrgedienteActualizado = new IngredienteActualizadoDTO(
                    ingredienteViejo.getId(),
                    ingredienteViejo.getNombre(),
                    ingredienteViejo.getStock(),
                    ingredienteViejo.getImagen()
            );

            Ingrediente actualizado = ingredientesDAO.actualizarIngrediente(inrgedienteActualizado);

            return IngredienteAIngredienteDTO.convertirADTO(actualizado);
        } catch (PersistenciaException ex) {

            throw new NegocioException("No fue posible actualizar el ingrediente", ex);

        }
    }
    /**
     * Consulta todos los ingredientes existentes en el sistema
     * 
     * @return ListIngredienteDTO lista de objetos DTO con los datos de cada ingrediente
     * @throws NegocioException Si algún dato es inválido o si ocurre un error
     * en la capa de persistencia.
     */
    @Override
    public List<IngredienteDTO> consultarIngredientes() throws NegocioException {

        try {
            List<Ingrediente> ingredientes = ingredientesDAO.consultarIngredientes();

            return IngredienteAIngredienteDTO.convertirListaIngredientesADTO(ingredientes);
        } catch (PersistenciaException ex) {
            throw new NegocioException("No fue posible consultar los ingredientes", ex);
        }
    }
    /**
     * Consulta todos los ingredientes existentes en el sistema que concuerden con el filtro ingresado
     * 
     * @param filtro String que filtrara los ingredientes 
     * @return ListIngredienteDTO lista de objetos DTO con los datos de cada ingrediente
     * @throws NegocioException Si algún dato es inválido o si ocurre un error
     * en la capa de persistencia.
     */
    @Override
    public List<IngredienteDTO> consultarIngredientesFiltro(String filtro) throws NegocioException {

        try {
            List<Ingrediente> todos = ingredientesDAO.consultarIngredientesFiltro(filtro);

            return IngredienteAIngredienteDTO.convertirListaIngredientesADTO(todos);
        } catch (PersistenciaException ex) {
            throw new NegocioException("No fue posible filtrar los ingredientes", ex);
        }
    }
    /**
     * Elimina el ingrediente seleccionado luego de haber validado que no existe stock
     * y que el producto no se este usando en ninguna receta
     * 
     * @param ingrediente ingrediente que se desea eliminar
     * @return IngredienteDTO objeto DTO con todos los datos del ingrediente eliminado
     * @throws NegocioException Si algún dato es inválido o si ocurre un error
     * en la capa de persistencia.
     */
    @Override
    public IngredienteDTO eliminarIngrediente(IngredienteDTO ingrediente) throws NegocioException {
        if (ingrediente.getStock() != 0) {
            throw new NegocioException("No es posible eliminar un ingrediente con stock disponible", null);
        }
        
        //si pertenece a un producto no se puede eliminar
//        if () { 
//            throw new NegocioException("No fue posible eliminar el ingrediente", null);
//        }


        try {

            Ingrediente ingredienteEliminado = ingredientesDAO.eliminarIngrediente(ingrediente.getId());

            return IngredienteAIngredienteDTO.convertirADTO(ingredienteEliminado);
        } catch (PersistenciaException ex) {
            throw new NegocioException("No fue posible eliminar el ingrediente", ex);
        }
    }

}
