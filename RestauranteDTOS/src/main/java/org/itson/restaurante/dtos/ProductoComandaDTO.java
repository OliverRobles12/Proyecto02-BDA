
package org.itson.restaurante.dtos;

/**
 *
 * @author oliro
 */
public class ProductoComandaDTO {

    private Integer cantidad;
    private String detalles;
    private Double subtotal;
    private Long idProducto;

    public ProductoComandaDTO(Integer cantidad, String detalles, Double subtotal, Long idProducto) {
        this.cantidad = cantidad;
        this.detalles = detalles;
        this.subtotal = subtotal;
        this.idProducto = idProducto;
    }

    public Integer getCantidad() {
        return cantidad;
    }

    public String getDetalles() {
        return detalles;
    }

    public Double getSubtotal() {
        return subtotal;
    }

    public Long getIdProducto() {
        return idProducto;
    }
    
    
    
}
