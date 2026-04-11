
package org.itson.restaurante.dtos;

/**
 *
 * @author oliro
 */
public class NuevoProductoComandaDTO {
    
    private Integer idTemporal;
    private Long idProducto;
    private String nombreProducto;
    private Integer cantidad;
    private String detalles;
    private Double subtotal;
    private Double precioUnitario;

    public NuevoProductoComandaDTO(Integer idTemporal, Long idProducto, String nombreProducto, Integer cantidad, String detalles, Double subtotal, Double precioUnitario) {
        this.idTemporal = idTemporal;
        this.idProducto = idProducto;
        this.nombreProducto = nombreProducto;
        this.cantidad = cantidad;
        this.detalles = detalles;
        this.subtotal = subtotal;
        this.precioUnitario = precioUnitario;
    }

    public Integer getIdTemporal() {
        return idTemporal;
    }

    public Long getIdProducto() {
        return idProducto;
    }

    public String getNombreProducto() {
        return nombreProducto;
    }

    public Integer getCantidad() {
        return cantidad;
    }

    public void setCantidad(Integer cantidad) {
        this.cantidad = cantidad;
    }

    public String getDetalles() {
        return detalles;
    }

    public void setDetalles(String detalles) {
        this.detalles = detalles;
    }

    public Double getSubtotal() {
        return subtotal;
    }

    public void setSubtotal(Double subtotal) {
        this.subtotal = subtotal;
    }

    public Double getPrecioUnitario() {
        return precioUnitario;
    }

}
