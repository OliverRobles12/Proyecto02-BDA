/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/UnitTests/JUnit5TestClass.java to edit this template
 */
package org.itson.restaurante.persistencia;

import java.util.LinkedList;
import java.util.List;
import org.itson.restaurante.dominio.Producto;
import org.itson.restaurante.dtos.EstadoProducto;
import org.itson.restaurante.dtos.TipoProducto;
import org.itson.restaurante.dtos.IngredienteRecetaDTO;
import org.itson.restaurante.dtos.NuevoProductoDTO;
import org.itson.restaurante.dtos.ProductoActualizadoDTO;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.BeforeEach;

/**
 *
 * @author juanl
 */
public class ProductoDAOTest {
    
    private ProductoDAO dao;
    
    public ProductoDAOTest() {
    }
    
    @BeforeEach
    public void init() {
        dao = new ProductoDAO();
    }

    @Test
    public void RegistrarProductoFuncionaOK() {
        
        List<IngredienteRecetaDTO> receta = new LinkedList<>();
        
        NuevoProductoDTO producto1 = new NuevoProductoDTO("Coca cola",50.0,TipoProducto.BEBIDA,"",null,receta);
        
        Producto p1 = assertDoesNotThrow(() -> {
            return dao.registrarProducto(producto1);
        });
        
        assertNotNull(p1);
    }
    
    @Test
    public void ActualizarProductoFuncionaOK(){
        List<IngredienteRecetaDTO> receta = new LinkedList<>();
        IngredienteRecetaDTO ing1 = new IngredienteRecetaDTO(41L,"Jamón de Pavo",250.00);
        receta.add(ing1);
        
        ProductoActualizadoDTO productoAct = new ProductoActualizadoDTO(1L,"Sandwich",70.0,TipoProducto.PLATILLO,EstadoProducto.ACTIVO,"Sin tomate",null,receta);
        
        Producto p1 = assertDoesNotThrow(() -> {
            return dao.actualizarProducto(productoAct);
        });
        
        assertEquals(p1.getNombre(),productoAct.getNombre());
    }
    
    @Test
    public void ConsultarProductosOK() {
        assertDoesNotThrow(() -> {
            List<Producto> productos = dao.consultarProductos();
            assertEquals(productos.size(),5);
        });
    }
    
    @Test
    public void ConsultarPorNombreFuncionaOk(){
        
        assertDoesNotThrow(() -> {
            List<Producto> productos = dao.consultarProductosFiltro("Co", null);
            assertEquals(productos.get(0).getNombre(),"Coca cola");
        });
        
    
    }
    
    @Test
    public void ConsultarPorCategoriaFuncionaOk(){
        assertDoesNotThrow(() -> {
            List<Producto> productos = dao.consultarProductosFiltro(null, org.itson.restaurante.dominio.TipoProducto.PLATILLO);
            assertEquals(productos.size(),2);
        });
    
    }
    
    @Test
    public void ConsultarPorAmbosFuncionaOk(){
        assertDoesNotThrow(() -> {
                List<Producto> productos = dao.consultarProductosFiltro("Pan", org.itson.restaurante.dominio.TipoProducto.PLATILLO);
                assertEquals(productos.size(),1);
            });
    
    }
    
    @Test
    public void CambiarDeEstadoFuncionaOK(){
        assertDoesNotThrow(() -> {
            dao.cambiarEstadoProducto(2L);
            List<Producto> productos = dao.consultarProductosFiltro("pan",null);
            System.out.println(productos.get(0).getEstado());
        });
        
    }
    
}
