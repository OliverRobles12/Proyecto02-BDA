package org.itson.restaurante.controladores;

import java.util.List;
import javax.swing.JFrame;
import org.itson.restaurante.controladores.ControladorClientes;
import org.itson.restaurante.controladores.ControladorComandas;
import org.itson.restaurante.dtos.IngredienteDTO;
import org.itson.restaurante.dtos.NuevoIngredienteDTO;
import org.itson.restaurante.dtos.RolEmpleado;
import org.itson.restaurante.negocio.ClienteFrecuenteBO;
import org.itson.restaurante.negocio.IClienteFrecuenteBO;
import org.itson.restaurante.negocio.IIngredientesBO;
import org.itson.restaurante.negocio.IngredientesBO;
import org.itson.restaurante.negocio.NegocioException;
import org.itson.restaurante.presentacion.PantallaFormularioIngrediente;
import org.itson.restaurante.presentacion.PantallaIngredientes;
import org.itson.restaurante.presentacion.PantallaLogin;
import org.itson.restaurante.presentacion.PantallaMenuPrincipal;

/**
 * Esta clase nos ayuda a controlar el flujo entre las pantallas y es el
 * intermediario entre la presentacion y la logica de negocio Implementa el
 * patrón singleton para que solo exista una instancia que este controlando la
 * navegación y los datos
 *
 * @author juanl
 */
public class Controlador {

    private RolEmpleado rolActual;
    private static Controlador instancia;
    private IClienteFrecuenteBO clientesBO;
    private IIngredientesBO ingredientesBO;

    private ControladorComandas controladorComandas;
    private ControladorClientes controladorClientes;
    
    private Controlador() {
        this.clientesBO = new ClienteFrecuenteBO();
        this.ingredientesBO = new IngredientesBO();
    }

    /**
     * Este metodo nos una única instancia del Controlador y si no existe la
     * crea
     *
     * @return La instancia estatica de controlador
     */
    public static Controlador getIntancia() {
        if (instancia == null) {
            instancia = new Controlador();
        }
        return instancia;
    }

    public ControladorClientes getControladorClientes() {
        if (controladorClientes == null) {
            controladorClientes = new ControladorClientes();
        }
        return controladorClientes;
    }
    
    public ControladorComandas getControladorComandas() {
        if (controladorComandas == null) {
            controladorComandas = new ControladorComandas();
        }
        return controladorComandas;
    }
    
    /**
     * Este metodo abre la pantalla del menu principal
     *
     * @param pantallaActual
     */

    public void abrirMenuPrincipal(JFrame pantallaActual) {
        PantallaMenuPrincipal menu = new PantallaMenuPrincipal();
        menu.setVisible(true);

        if (pantallaActual != null) {
            pantallaActual.dispose();
        }
    }

    /**
     * Este metodo abre la pantalla del Formulario de registro
     *
     * @param pantallaActual la pantalla actual es ocultada temporalmente
     */
    public void abrirFomularioIngrediente(JFrame pantallaActual) {
        PantallaFormularioIngrediente formulario = new PantallaFormularioIngrediente();
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
        formulario.setVisible(true);
    }
/**
 * Este método abre la pantalla de ingredientes.
 *
 * @param pantallaActual la ventana actual que será cerrada al abrir la nueva
 */
    public void abrirIngredientes(JFrame pantallaActual) {
        PantallaIngredientes ingrediente = new PantallaIngredientes();
        ingrediente.setVisible(true);

        if (pantallaActual != null) {
            pantallaActual.dispose();
        }

    }
/**
 * Este método abre la pantalla de productos.
 *
 * @param pantallaActual la ventana actual que será cerrada al abrir la nueva
 */
    public void abrirProductos(JFrame pantallaActual) {

    }
    
/**
 * Este método abre la pantalla de reportes.
 *
 * @param pantallaActual la ventana actual que será cerrada al abrir la nueva
 */
    public void abrirReportes(JFrame pantallaActual) {

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
 * @throws NegocioException Si los datos son invalidos o si el ingrediente no
 * existe
 */
    public IngredienteDTO actualizarIngrediente(IngredienteDTO ingredienteActualizado) throws NegocioException {
        return this.ingredientesBO.actualizarIngrediente(ingredienteActualizado);

    }

/**
 * Envia los datos de un ingrediente existente a la capa de negocio para
 * realizar su eliminacion logica o actualizacion de estado dentro del sistema.
 *
 * @param ingredienteElegido La DTO del ingrediente que se desea eliminar
 * @return devuelve el ingrediente con la informacion ya actualizada
 * @throws NegocioException Si los datos son invalidos o si el ingrediente no
 * existe
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
 * @param filtro texto que se utilizará para buscar ingredientes por nombre o
 * unidad de medida
 * @return una lista de ingredientes que cumplen con el criterio de búsqueda
 * @throws NegocioException si ocurre un error al realizar la consulta
 */
    public List<IngredienteDTO> consultarIngredientesFiltro(String filtro) throws NegocioException {
        return this.ingredientesBO.consultarIngredientesFiltro(filtro);
    }

    //METODOS LOGIN
    public void cerrarSesion(JFrame pantallaActual) {
        this.rolActual = null; // limpiar el rol
        abrirLogin(pantallaActual);
    }

    public void abrirLogin(JFrame pantallaActual) {
        PantallaLogin login = new PantallaLogin();
        login.setVisible(true);
        if (pantallaActual != null) {
            pantallaActual.dispose();
        }
    }
    
    /**
    * Obtiene el rol del empleado que ha iniciado sesión actualmente.
    *
    * @return el rol actual del empleado en el sistema
    */
    public RolEmpleado getRolActual() {
        return rolActual;
    }
/**
 * Establece el rol del empleado que ha iniciado sesión en el sistema.
 *
 * @param rolActual el rol que se asignará como actual
 */
    public void setRolActual(RolEmpleado rolActual) {
        this.rolActual = rolActual;
    }
/**
 * Verifica si el rol actual corresponde a un administrador.
 *
 * @return true si el rol actual es administrador, false en caso contrario
 */
    public boolean esAdministrador() {
        return rolActual == RolEmpleado.ADMINISTRADOR;
    }
/**
 * Verifica si el rol actual corresponde a un mesero.
 *
 * @return true si el rol actual es mesero, false en caso contrario
 */
    public boolean esMesero() {
        return rolActual == RolEmpleado.MESERO;
    }
/**
 * Verifica si actualmente no hay ningún rol asignado.
 *
 * @return true si no existe un rol actual, false en caso contrario
 */
    public boolean noRol() {
        return rolActual == null;
    }

    //METODOS QUE LLENAN EL MENUPRINCIPAL
    public int contarComandasHoy() {
        // return comandaBO.contarComandasHoy();
        return 12; // por ahora
    }

    public double calcularVentasHoy() {
        // return comandaBO.calcularVentasHoy();
        return 4380.00; // por ahora
    }

    public int contarMesasAbiertas() {
        // return comandaBO.contarMesasAbiertas();
        return 3; // por ahora
    }

    public int contarProductosActivos() {
        // return productoBO.contarProductosActivos();
        return 47; // por ahora
    }

}
