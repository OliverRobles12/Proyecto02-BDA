
package org.itson.restaurante.controladores;

import java.util.List;
import javax.swing.JFrame;
import org.itson.restaurante.dtos.ClienteFrecuenteDTO;
import org.itson.restaurante.dtos.NuevoClienteFrecuenteDTO;
import org.itson.restaurante.negocio.ClienteFrecuenteBO;
import org.itson.restaurante.negocio.IClienteFrecuenteBO;
import org.itson.restaurante.negocio.NegocioException;
import org.itson.restaurante.presentacion.PantallaBusquedaClienteD;
import org.itson.restaurante.presentacion.PantallaCliente;
import org.itson.restaurante.presentacion.PantallaFormularioCliente;

/**
 *
 * @author oliro
 */
public class ControladorClientes {

    private IClienteFrecuenteBO clienteFrecuenteBO = new ClienteFrecuenteBO();
    
    public ControladorClientes() {
    }
    
    public void mostarPantallaClientes(JFrame pantallaActual) {
        PantallaCliente vistaCliente = new PantallaCliente(this);
        vistaCliente.setVisible(true);
        
        if (pantallaActual != null) {
            pantallaActual.dispose(); 
        }
    }

    /**
     * Este método abre la pantalla del formulario de comandas.
     * @param pantallaActual la ventana actual que será cerrada al abrir la nueva
     */
    public void mostrarFormularioNuevoCliente(JFrame pantallaActual) {
        PantallaFormularioCliente vistaFormulario = new PantallaFormularioCliente(this);
        vistaFormulario.setVisible(true);
        
        if (pantallaActual != null) {
            pantallaActual.dispose(); 
        }
    }
    
    /**
     * Este metodo abre la pantalla del formulario en modo de edicion y carga
     * los datos del cliente que se seleccionó.
     *
     * @param cliente la DTO con los datos que se van a editar
     * @param pantallaActual la ventana actual será oculta temporalmente
     */
    public void mostrarFormularioEditarCliente(JFrame pantallaActual, ClienteFrecuenteDTO cliente) {
        PantallaFormularioCliente vistaFormulario = new PantallaFormularioCliente(this, cliente);
        vistaFormulario.setVisible(true);
        
        if (pantallaActual != null) {
            pantallaActual.dispose(); 
        }
        
    }
    
    /**
     * Metodo encargado de crear la PantallaBusquedaClienteDialog y hacerla visible,
     * al finalizar la pantalla recoge al cliente seleccionado dentro del dialog.
     * @param pantallaActual Pantalla desde donde se invoca el buscador.
     * @return Regresa el cliente seleccionado en el buscardorCLienteDialog.
     */
    public ClienteFrecuenteDTO mostrarBuscadorClienteDialog(JFrame pantallaActual) {
        PantallaBusquedaClienteD dialog = new PantallaBusquedaClienteD(pantallaActual, true, this);
        dialog.setVisible(true);
        return dialog.getClienteSeleccionado();
    }
    
    /**
     * 
     * TODO Remover el throws Negocio Exception
     * 
     * Recibe los datos de un nuevo cliente desde el formulario y los envia a la
     * capa de negocio para su validacion y despues a la capa de persistencia
     * para que los registre
     *
     * @param nuevoCliente es la dto que contiene todos lo datos que se
     * ingresaron en el formulario
     * @return devuelve al cliente ya registrado con todos sus atributos
     * @throws NegocioException Si ocurre un error en la validacion y si la
     * conexion se rechaza
     */
    public ClienteFrecuenteDTO registrarClienteC(NuevoClienteFrecuenteDTO nuevoCliente) throws NegocioException {
        return this.clienteFrecuenteBO.registrarCliente(nuevoCliente);
    }
    
    /**
     * 
     * TODO Remover el throws Negocio Exception
     * 
     * Envia los datos modificados de un cliente que ya existe a la capa de
     * negocio para que los vuelva a validar y posteriormente actualiza el
     * registro en el sistema
     *
     * @param clienteActualizado La DTO con los nuevos datos del cliente
     * ingresados
     * @return devuelve al cliente con la informacion ya actualizada
     * @throws NegocioException Si los datos son invalidos o si el cliente no
     * existe
     */
    public ClienteFrecuenteDTO actualizarClienteC(ClienteFrecuenteDTO clienteActualizado) throws NegocioException {
        return this.clienteFrecuenteBO.actualizarCliente(clienteActualizado);
    }
    
    
    /**
     * 
     * TODO Remover el throws Negocio Exception
     * 
     * Consulta todos los clientes frecuentes registrados en el sistema.
     * @return una lista con todos los clientes frecuentes registrados
     * @throws NegocioException si ocurre un error al consultar los clientes
     */
    public List<ClienteFrecuenteDTO> consultarClientes() throws NegocioException {
        return this.clienteFrecuenteBO.consultarClientes();
    }
    
    /**
     * Consulta los clientes frecuentes que coincidan con el filtro proporcionado.
     * @param filtro texto que se utilizará para buscar clientes por nombre,
     * teléfono o correo
     * @return una lista de clientes que cumplen con el criterio de búsqueda
     * @throws NegocioException si ocurre un error al realizar la consulta
     */
    public List<ClienteFrecuenteDTO> consultarClientesFiltro(String filtro) throws NegocioException {
        return this.clienteFrecuenteBO.consultarClienteFiltro(filtro);
    }
    
    
    
}
