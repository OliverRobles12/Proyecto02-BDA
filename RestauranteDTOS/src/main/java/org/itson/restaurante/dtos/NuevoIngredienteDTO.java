/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package org.itson.restaurante.dtos;

/**
 *
 * @author joset
 */
public class NuevoIngredienteDTO {
    private String nombre; 
    private UnidadMedidaDTO unidadMedida;
    private Integer stock;
    private byte[] imagen;

    public NuevoIngredienteDTO(String nombre, UnidadMedidaDTO unidadMedida, Integer stock, byte[] imagen) {
        this.nombre = nombre;
        this.unidadMedida = unidadMedida;
        this.stock = stock;
        this.imagen = imagen;
    }

    public String getNombre() {
        return nombre;
    }

    public UnidadMedidaDTO getUnidadMedida() {
        return unidadMedida;
    }

    public Integer getStock() {
        return stock;
    }

    public byte[] getImagen() {
        return imagen;
    }
    
    
}
