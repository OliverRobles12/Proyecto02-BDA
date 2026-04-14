
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
    private String nombreProducto;

    public ProductoComandaDTO(Integer cantidad, String detalles, Double subtotal, Long idProducto, String nombreProducto) {
        this.cantidad = cantidad;
        this.detalles = detalles;
        this.subtotal = subtotal;
        this.idProducto = idProducto;
        this.nombreProducto = nombreProducto;
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

    public String getNombreProducto() {
        return nombreProducto;
    }
    
}
