/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package org.itson.restaurante.controladores;

import java.util.List;
import javax.swing.JFrame;
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
    }
    
    public static ControladorProductos getInstancia() {
        if (instancia == null) {
            instancia = new ControladorProductos();
        }
        return instancia;
    }
    
    public void mostarPantallaProductos(JFrame pantallaActual) {
        PantallaProductos productos = new PantallaProductos(this);
        productos.setVisible(true);
        
        if (pantallaActual != null) {
            pantallaActual.dispose(); 
        }
        
    }
    
    public void mostrarPantallaBusquedaProductos (JFrame pantallaActual) {
       
        PantallaBusquedaProductos busquedaProductos = new PantallaBusquedaProductos(pantallaActual, true, this);
        busquedaProductos.setModal(true);
        busquedaProductos.setVisible(true); 
        
        
        ProductoDTO elegido = busquedaProductos.getProductoSeleccionado();
        
        if (elegido != null) {
            
            if (pantallaActual != null) {
                pantallaActual.dispose(); 
            }
            
            if (elegido != null) {
            mostrarPantallaFormularioProductos(pantallaActual, elegido); 
            } 
            
        } else {
            System.out.println("Búsqueda cancelada.");
        }
        
    }
    
    public void mostrarPantallaFormularioProductos(JFrame pantallaActual) {
        PantallaFormularioProducto formularioProductos = new PantallaFormularioProducto(this, null);
        formularioProductos.setVisible(true);
        
        if (pantallaActual != null) {
            pantallaActual.dispose(); 
        }
    }

    public void mostrarPantallaFormularioProductos(JFrame pantallaActual, ProductoDTO productoAEditar) {
        PantallaFormularioProducto formularioProductos = new PantallaFormularioProducto(this, productoAEditar);
        formularioProductos.setVisible(true);
        
        if (pantallaActual != null) {
            pantallaActual.dispose(); 
        }
    }
    
    public List<ProductoDTO> consultarProductos() throws NegocioException {
        return productoBO.consultarProductos(); 
    }
    
    public List<ProductoDTO> consultarProductosFiltro(String nombre,TipoProducto tipo) throws NegocioException {
        return productoBO.consultarProductosFiltro(nombre, tipo);
    }
}
