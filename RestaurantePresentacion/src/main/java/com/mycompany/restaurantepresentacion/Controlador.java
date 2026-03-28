
package com.mycompany.restaurantepresentacion;

import com.mycompany.restaurantedtos.ClienteFrecuenteDTO;
import com.mycompany.restaurantedtos.NuevoClienteFrecuenteDTO;
import itson.org.restaurantenegocio.ClienteFrecuenteBO;
import itson.org.restaurantenegocio.IClienteFrecuenteBO;
import itson.org.restaurantenegocio.NegocioException;

/**
 *Esta clase nos ayuda a controlar el flujo entre las pantallas y
 * es el intermediario entre la presentacion y la logica de negocio
 * Implementa el patrón singleton para que solo exista una instancia que
 * este controlando la navegación y los datos
 * @author juanl
 */
public class Controlador {
   
    
    private static Controlador instancia;
    private IClienteFrecuenteBO clientesBO;
    
    
    private Controlador() {
       this.clientesBO = new ClienteFrecuenteBO();
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
    public void abrirMenuPrincipal(javax.swing.JFrame pantallaActual) {
        PantallaCliente menu = new PantallaCliente();
        menu.setVisible(true);
        
        if (pantallaActual != null) {
            pantallaActual.dispose(); 
        }
    }
    
    /**
     * Este metodo abre la pantalla del Formulario en el modo registro
     * y oculta la pantalla de donde se llamo hasta la muestra hasta que termine
     * @param pantallaActual la pantalla actual es ocultada temporalmente
     */
    public void abrirFomularioCliente(javax.swing.JFrame pantallaActual){
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
     * Este metodo abre la pantalla del formulario en modo de edicion y carga
     * los datos del cliente que se seleccionó.
     * @param cliente la DTO con los datos que se van a editar
     * @param pantallaActual la ventana actual será oculta temporalmente
     */
    public void abrirFormularioEditarCliente(ClienteFrecuenteDTO cliente, javax.swing.JFrame pantallaActual){
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
}
