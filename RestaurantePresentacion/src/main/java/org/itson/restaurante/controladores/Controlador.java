package org.itson.restaurante.controladores;

import javax.swing.JFrame;
import org.itson.restaurante.dtos.IngredienteDTO;
import org.itson.restaurante.dtos.RolEmpleado;
import org.itson.restaurante.negocio.IMesaBO;
import org.itson.restaurante.negocio.MesaBO;
import org.itson.restaurante.negocio.NegocioException;
import org.itson.restaurante.presentacion.PantallaBusquedaIngrediente;
import org.itson.restaurante.presentacion.PantallaFormularioIngrediente;
import org.itson.restaurante.presentacion.PantallaIngredientes;
import org.itson.restaurante.presentacion.PantallaLogin;
import org.itson.restaurante.presentacion.PantallaMenuPrincipal;
import org.itson.restaurante.presentacion.PantallaReportes;

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

    private ControladorProductos controladorProducos;
    private ControladorProductos controladorProductos;
    private ControladorComandas controladorComandas;
    private ControladorClientes controladorClientes;
    private ControladorIngrediente controladorIngredientes;
    private IMesaBO mesaBO;

    private Controlador() {
        this.mesaBO = new MesaBO();
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


   
    public ControladorProductos getControladorProductos(){
        if(controladorProductos == null){
            controladorProductos = new ControladorProductos();
            controladorProductos.setControl(this);

        }
        
    }
    
    public ControladorIngrediente getControladorIngredientes(){
        if(controladorIngredientes == null){
            controladorIngredientes = new ControladorIngrediente();
        }
        return controladorIngredientes;
    }

    public void registrarMesas(PantallaMenuPrincipal vistaMenuPrincipal, int cantidad) {
        try {
            mesaBO.registrarMesa(cantidad);
        } catch (NegocioException ex) {
            vistaMenuPrincipal.mostarMensaje("No ha sido posible registrar la mesa.", true);
            return;
        }
        vistaMenuPrincipal.mostarMensaje("Se han agregado " + cantidad + " mesas.", false);
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
     * @param pantallaActual la ventana actual que será cerrada al abrir la
     * nueva
     */
    public void abrirIngredientes(JFrame pantallaActual) {
        PantallaIngredientes ingrediente = new PantallaIngredientes();
        ingrediente.setVisible(true);

        if (pantallaActual != null) {
            pantallaActual.dispose();
        }

    }


    public IngredienteDTO abrirBusquedaIngredientes(JFrame pantallaActual){

        PantallaBusquedaIngrediente busqueda = new PantallaBusquedaIngrediente(pantallaActual, true);
        busqueda.setModal(true);
        busqueda.setVisible(true);

        if (pantallaActual != null) {
            pantallaActual.dispose();
        }
        
        IngredienteDTO seleccionado = busqueda.getIngredienteSeleccionado();
        return seleccionado;
    }
    
    public IngredienteDTO abrirBusquedaIngredientesProductos(JFrame pantallaActual){
        PantallaBusquedaIngrediente busqueda =new PantallaBusquedaIngrediente(pantallaActual, true);

        busqueda.setLocationRelativeTo(pantallaActual);

        busqueda.setVisible(true); 

        return busqueda.getIngredienteSeleccionado();
    }

    /**
     * Este método abre la pantalla de productos.
     *
     * @param pantallaActual la ventana actual que será cerrada al abrir la
     * nueva
     */
    public void abrirProductos(JFrame pantallaActual) {

    }

    /**
     * Este método abre la pantalla de reportes.
     *
     * @param pantallaActual la ventana actual que será cerrada al abrir la
     * nueva
     */
    public void abrirReportes(JFrame pantallaActual) {
        PantallaReportes reporte = new PantallaReportes();
        reporte.setVisible(true);
        if (pantallaActual != null) {
            pantallaActual.dispose();
        }
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
