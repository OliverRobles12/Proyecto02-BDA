package org.itson.restaurante.dtos;

import java.time.LocalDateTime;

/**
 *
 * @author joset
 */
public class ReporteComandaDTO {

    private String folio;
    private LocalDateTime fecha;
    private Integer numeroMesa;
    private String estado;
    private Double total;
    private String nombreCliente;

    public ReporteComandaDTO() {
    }

    public ReporteComandaDTO(String folio, LocalDateTime fecha, Integer numeroMesa, String estado, Double total, String nombreCliente) {
        this.folio = folio;
        this.fecha = fecha;
        this.numeroMesa = numeroMesa;
        this.estado = estado;
        this.total = total;
        this.nombreCliente = nombreCliente;
    }

    public String getFolio() {
        return folio;
    }

    public void setFolio(String folio) {
        this.folio = folio;
    }

    public LocalDateTime getFecha() {
        return fecha;
    }

    public void setFecha(LocalDateTime fecha) {
        this.fecha = fecha;
    }

    public Integer getNumeroMesa() {
        return numeroMesa;
    }

    public void setNumeroMesa(Integer numeroMesa) {
        this.numeroMesa = numeroMesa;
    }

    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }

    public Double getTotal() {
        return total;
    }

    public void setTotal(Double total) {
        this.total = total;
    }

    public String getNombreCliente() {
        return nombreCliente;
    }

    public void setNombreCliente(String nombreCliente) {
        this.nombreCliente = nombreCliente;
    }
}
