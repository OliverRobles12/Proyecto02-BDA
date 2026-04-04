
package org.itson.restaurante.presentacion;

import javax.swing.JFrame;
import org.itson.restaurante.dtos.ClienteFrecuenteDTO;
import org.itson.restaurante.dtos.IngredienteActualizadoDTO;
import org.itson.restaurante.dtos.IngredienteDTO;
import org.itson.restaurante.dtos.NuevoClienteFrecuenteDTO;
import org.itson.restaurante.dtos.NuevoIngredienteDTO;
import org.itson.restaurante.dtos.RolEmpleado;
import org.itson.restaurante.negocio.ClienteFrecuenteBO;
import org.itson.restaurante.negocio.IClienteFrecuenteBO;
import org.itson.restaurante.negocio.IIngredientesBO;
import org.itson.restaurante.negocio.IngredientesBO;
import org.itson.restaurante.negocio.NegocioException;

/**
 *Esta clase nos ayuda a controlar el flujo entre las pantallas y
 * es el intermediario entre la presentacion y la logica de negocio
 * Implementa el patrón singleton para que solo exista una instancia que
 * este controlando la navegación y los datos
 * @author juanl
 */
public class Controlador {
   
    private RolEmpleado rolActual ;
    private static Controlador instancia;
    private IClienteFrecuenteBO clientesBO;
    private IIngredientesBO ingredientesBO;
    
    private Controlador() {
       this.clientesBO = new ClienteFrecuenteBO();
       this.ingredientesBO = new IngredientesBO();
    }
    
    /**
     * Este metodo nos una única instancia del Controlador
     * y si no existe la crea
     * @return La instancia estatica de controlador
     */
    public static Controlador getIntancia(){
        if(instancia == null){
            instancia = new Controlador();
        }
        return instancia;
    }
    /**
     * Este metodo abre la pantalla del menu principal
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
     * Este metodo abre la pantalla de clientes
     * @param pantallaActual 
     */
    public void abrirClientes(JFrame pantallaActual) {
        PantallaCliente cliente = new PantallaCliente();
        cliente.setVisible(true);
        
        if (pantallaActual != null) {
            pantallaActual.dispose(); 
        }
    }
    
    /**
     * Este metodo abre la pantalla del Formulario de registro
     * @param pantallaActual la pantalla actual es ocultada temporalmente
     */
    public void abrirFomularioCliente(JFrame pantallaActual){
        PantallaFormularioCliente formulario = new PantallaFormularioCliente();
        formulario.setVisible(true);
        
        if(pantallaActual != null){
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
     * Este metodo abre la pantalla del Formulario de registro
     * 
     * @param pantallaActual la pantalla actual es ocultada temporalmente
     */
    public void abrirFomularioIngrediente(JFrame pantallaActual){
        PantallaFormularioIngrediente formulario = new PantallaFormularioIngrediente();
        formulario.setVisible(true);
        
        if(pantallaActual != null){
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

    public void abrirIngredientes(JFrame pantallaActual){
        PantallaIngredientes ingrediente = new PantallaIngredientes();
        ingrediente.setVisible(true);
        
        if (pantallaActual != null) {
            pantallaActual.dispose(); 
        }

    }
    
    public void abrirProductos(JFrame pantallaActual){

    }
    
    public void abrirComandas(JFrame pantallaActual){
        PantallaComandas comandas = new PantallaComandas();
        comandas.setVisible(true);
        
        if (pantallaActual != null) {
            pantallaActual.dispose(); 
        }
        
    }
    
    public void abrirFormularioComanda(JFrame pantallaActual) {
        PantallaFormularioComanda formulario = new PantallaFormularioComanda();
        formulario.setVisible(true);
        
        if (pantallaActual != null) {
            pantallaActual.dispose(); 
        }
        
    }
    
    public void abrirReportes(JFrame pantallaActual){

    }
    
    
    /**
     * Este metodo abre la pantalla del formulario en modo de edicion y carga
     * los datos del cliente que se seleccionó.
     * @param cliente la DTO con los datos que se van a editar
     * @param pantallaActual la ventana actual será oculta temporalmente
     */
    public void abrirFormularioEditarCliente(ClienteFrecuenteDTO cliente, JFrame pantallaActual){
        PantallaFormularioCliente formulario = new PantallaFormularioCliente(cliente);
        formulario.setVisible(true);
        
        if(pantallaActual != null){
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
     * Este metodo abre la pantalla del formulario en modo de edicion y carga
     * los datos del ingrediente que se seleccionó.
     * @param ingrediente la DTO con los datos que se van a editar
     * @param pantallaActual la ventana actual será oculta temporalmente
     */
    public void abrirFormularioEditarIngrediente(IngredienteDTO ingrediente, JFrame pantallaActual){
        PantallaFormularioIngrediente formulario = new PantallaFormularioIngrediente(ingrediente);
        formulario.setVisible(true);
        
        if(pantallaActual != null){
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
     * Recibe los datos de un nuevo cliente desde el formulario y los envia 
     * a la capa de negocio para su validacion y despues a la capa de persistencia
     * para que los registre
     * @param nuevoCliente es la dto que contiene todos lo datos que se ingresaron
     * en el formulario
     * @return devuelve al cliente ya registrado con todos sus atributos
     * @throws NegocioException  Si ocurre un error en la validacion y si
     * la conexion se rechaza
     */
    public ClienteFrecuenteDTO registrarClienteC(NuevoClienteFrecuenteDTO nuevoCliente)throws NegocioException{
        return this.clientesBO.registrarCliente(nuevoCliente);
    }
    
    /**
     * Envia los datos modificados de un cliente que ya existe a la capa
     * de negocio para que los vuelva a validar y posteriormente actualiza 
     * el registro en el sistema
     * @param clienteActualizado La DTO con los nuevos datos del cliente ingresados
     * @return devuelve al cliente con la informacion ya actualizada
     * @throws NegocioException Si los datos son invalidos o si el cliente no existe
     */
    public ClienteFrecuenteDTO actualizarClienteC(ClienteFrecuenteDTO clienteActualizado)throws NegocioException{
        return this.clientesBO.actualizarCliente(clienteActualizado);
    
    }
    /**
     * @param nuevoCliente
     * @return 
     * @throws org.itson.restaurante.negocio.NegocioException */
    public IngredienteDTO registrarIngrediente(NuevoIngredienteDTO nuevoCliente)throws NegocioException{
        return this.ingredientesBO.registrarIngrediente(nuevoCliente);
    }
    /**
     * 
     * @param clienteActualizado
     * @return 
     * @throws org.itson.restaurante.negocio.NegocioException
     */
    public IngredienteDTO actualizarIngrediente(IngredienteDTO clienteActualizado)throws NegocioException{
        return this.ingredientesBO.actualizarIngrediente(clienteActualizado);
    
    }
    
    public RolEmpleado getRolActual() {
        return rolActual;
    }

    public void setRolActual(RolEmpleado rolActual) {
        this.rolActual = rolActual;
    }
    
    public boolean esAdministrador(){
        return rolActual == RolEmpleado.ADMINISTRADOR;
    }
    public boolean esMesero(){
        return rolActual == RolEmpleado.MESERO;
    }
    
    public boolean noRol(){
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
