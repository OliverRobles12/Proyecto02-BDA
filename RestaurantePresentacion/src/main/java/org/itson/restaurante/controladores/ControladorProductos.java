/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package org.itson.restaurante.controladores;

import java.util.List;
import javax.swing.JFrame;
import org.itson.restaurante.dtos.IngredienteDTO;
import org.itson.restaurante.dtos.NuevoProductoDTO;
import org.itson.restaurante.dtos.ProductoActualizadoDTO;
import org.itson.restaurante.dtos.ProductoDTO;
import org.itson.restaurante.dtos.TipoProducto;
import org.itson.restaurante.negocio.NegocioException;
import org.itson.restaurante.negocio.ProductoBO;
import org.itson.restaurante.presentacion.PantallaBusquedaProductos;
import org.itson.restaurante.presentacion.PantallaFormularioProducto;
import org.itson.restaurante.presentacion.PantallaProductos;

/**
 *
 * @author juanl
 */
public class ControladorProductos {
    
    private static ControladorProductos instancia;
    private Controlador control;
    private ProductoBO productoBO = new ProductoBO();

    public ControladorProductos() {
        this.control = control;
    }
    
    public static ControladorProductos getInstancia() {
        if (instancia == null) {
            instancia = new ControladorProductos();
        }
        return instancia;
    }
    
    public void mostarPantallaProductos(JFrame pantallaActual) {
        PantallaProductos productos = new PantallaProductos(this);
        productos.setLocationRelativeTo(pantallaActual);
        productos.setVisible(true);
        
        if (pantallaActual != null) {
            pantallaActual.dispose(); 
        }
        
    }
    
    public ProductoDTO mostrarPantallaBusquedaProductos (JFrame pantallaActual) {
       
        PantallaBusquedaProductos busquedaProductos = new PantallaBusquedaProductos(pantallaActual, true, this);
        busquedaProductos.setModal(true);
        busquedaProductos.setLocationRelativeTo(pantallaActual);
        busquedaProductos.setVisible(true); 
        
        
        ProductoDTO elegido = busquedaProductos.getProductoSeleccionado();
        
        return elegido;
        
    }
    
    public void mostrarPantallaFormularioProductos(JFrame pantallaActual) {
        PantallaFormularioProducto formularioProductos = new PantallaFormularioProducto(this, null,control,null);
        formularioProductos.setLocationRelativeTo(pantallaActual);
        formularioProductos.setVisible(true);
        
        if (pantallaActual != null) {
            pantallaActual.dispose(); 
        }
    }

    public void mostrarPantallaFormularioProductos(JFrame pantallaActual, ProductoDTO productoAEditar) {
        PantallaFormularioProducto formularioProductos = new PantallaFormularioProducto(this, productoAEditar,control,null);
        formularioProductos.setLocationRelativeTo(pantallaActual);
        formularioProductos.setVisible(true);
        
        if (pantallaActual != null) {
            pantallaActual.dispose(); 
        }
    }
    
    public ProductoDTO registrar(NuevoProductoDTO nuevoProducto) throws NegocioException{
        return productoBO.registrarProducto(nuevoProducto);
    }
    
    public List<ProductoDTO> consultarProductos() throws NegocioException {
        return productoBO.consultarProductos(); 
    }
    
    public List<ProductoDTO> consultarProductosFiltro(String nombre,TipoProducto tipo) throws NegocioException {
        return productoBO.consultarProductosFiltro(nombre, tipo);
    }
    
    public ProductoDTO consultarProductoID(Long id) throws NegocioException{
        return productoBO.consultarProductoPorId(id);
    }
    
    public void ActualizarProducto(ProductoDTO productoActualizado) throws NegocioException{
        productoBO.actualizarProducto(productoActualizado);
    }
    
    public IngredienteDTO ConsultarIngrediente(Long id) throws NegocioException{
       return control.getControladorIngredientes().consultarIngrediente(id);
    }
    
    public void setControl(Controlador control) {
    this.control = control;
}
}
