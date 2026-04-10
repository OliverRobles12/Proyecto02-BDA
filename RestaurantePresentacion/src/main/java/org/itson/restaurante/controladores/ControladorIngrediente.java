/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package org.itson.restaurante.controladores;

import java.util.List;
import javax.swing.JFrame;
import org.itson.restaurante.dtos.IngredienteDTO;
import org.itson.restaurante.dtos.NuevoIngredienteDTO;
import org.itson.restaurante.negocio.IIngredientesBO;
import org.itson.restaurante.negocio.IngredientesBO;
import org.itson.restaurante.negocio.NegocioException;
import org.itson.restaurante.presentacion.PantallaFormularioIngrediente;

/**
 *
 * @author joset
 */
public class ControladorIngrediente {

    private IIngredientesBO ingredientesBO;

    public ControladorIngrediente() {
        this.ingredientesBO = new IngredientesBO();
    }

    /**
     * Este metodo abre la pantalla del formulario en modo de edicion y carga
     * los datos del ingrediente que se seleccionó.
     *
     * @param ingrediente la DTO con los datos que se van a editar
     * @param pantallaActual la ventana actual será oculta temporalmente
     */
    public void abrirFormularioEditarIngrediente(IngredienteDTO ingrediente, JFrame pantallaActual) {
        PantallaFormularioIngrediente formulario = new PantallaFormularioIngrediente(ingrediente);
        formulario.setVisible(true);

        if (pantallaActual != null) {
            pantallaActual.dispose();

            formulario.addWindowListener(new java.awt.event.WindowAdapter() {
                @Override
                public void windowClosed(java.awt.event.WindowEvent e) {
                    pantallaActual.setVisible(true);
                }
            });
        }
    }

    /**
     * Registra un nuevo ingrediente en el sistema tras validar sus datos.
     * Valida que los campos obligatorios no estén vacíos, que los nombres no
     * contengan números, y que la unidadMedida y Stock esten dentro de los
     * parametros .
     *
     * @param nuevoIngrediente Objeto DTO con los datos del nuevo ingrediente a
     * registrar.
     * @return IngredienteDTO Objeto DTO con los datos del cliente ya registrado
     * @throws NegocioException Si algún dato es inválido o si ocurre un error
     * en la capa de persistencia.
     */
    public IngredienteDTO registrarIngrediente(NuevoIngredienteDTO nuevoIngrediente) throws NegocioException {
        return this.ingredientesBO.registrarIngrediente(nuevoIngrediente);
    }

    /**
     * Envia los datos modificados de un ingrediente que ya existe a la capa de
     * negocio para que los vuelva a validar y posteriormente actualiza el
     * registro en el sistema.
     *
     * @param ingredienteActualizado La DTO con los nuevos datos del ingrediente
     * ingresados
     * @return devuelve el ingrediente con la informacion ya actualizada
     * @throws NegocioException Si los datos son invalidos o si el ingrediente
     * no existe
     */
    public IngredienteDTO actualizarIngrediente(IngredienteDTO ingredienteActualizado) throws NegocioException {
        return this.ingredientesBO.actualizarIngrediente(ingredienteActualizado);

    }

    /**
     * Envia los datos de un ingrediente existente a la capa de negocio para
     * realizar su eliminacion logica o actualizacion de estado dentro del
     * sistema.
     *
     * @param ingredienteElegido La DTO del ingrediente que se desea eliminar
     * @return devuelve el ingrediente con la informacion ya actualizada
     * @throws NegocioException Si los datos son invalidos o si el ingrediente
     * no existe
     */
    public IngredienteDTO eliminarIngrediente(IngredienteDTO ingredienteElegido) throws NegocioException {
        return this.ingredientesBO.eliminarIngrediente(ingredienteElegido);

    }

    /**
     * Consulta todos los ingredientes registrados en el sistema.
     *
     * @return una lista con todos los ingredientes registrados
     * @throws NegocioException si ocurre un error al consultar los ingredientes
     */
    public List<IngredienteDTO> consultarIngredientes() throws NegocioException {
        return this.ingredientesBO.consultarIngredientes();
    }

    /**
     * Consulta los ingredientes que coincidan con el filtro proporcionado.
     *
     * @param filtro texto que se utilizará para buscar ingredientes por nombre
     * o unidad de medida
     * @return una lista de ingredientes que cumplen con el criterio de búsqueda
     * @throws NegocioException si ocurre un error al realizar la consulta
     */
    public List<IngredienteDTO> consultarIngredientesFiltro(String filtro) throws NegocioException {
        return this.ingredientesBO.consultarIngredientesFiltro(filtro);
    }
}
