
package org.itson.restaurante.dtos;

import java.time.LocalDateTime;
import java.util.List;

/**
 *
 * @author oliro
 */
public class ComandaDTO {
    
    private Long id;
    private String folio;
    private EstadoComanda estado;
    private Double totalAcumulado;
    private LocalDateTime fechaHora;
    private Integer noMesa;
    private String nombreCompleto;
    private List<ProductoComandaDTO> listaProductos;

    public ComandaDTO() {
    }

    public ComandaDTO(Long id, String folio, EstadoComanda estado, Double totalAcumulado, LocalDateTime fechaHora, Integer noMesa, String nombreCompleto, List listaProductos) {
        this.id = id;
        this.folio = folio;
        this.estado = estado;
        this.totalAcumulado = totalAcumulado;
        this.fechaHora = fechaHora;
        this.noMesa = noMesa;
        this.nombreCompleto = nombreCompleto;
        this.listaProductos = listaProductos;
    }
    
    public Long getId() {
        return id;
    }

    public String getFolio() {
        return folio;
    }

    public EstadoComanda getEstado() {
        return estado;
    }

    public Double getTotalAcumulado() {
        return totalAcumulado;
    }

    public LocalDateTime getFechaHora() {
        return fechaHora;
    }

    public Integer getNoMesa() {
        return noMesa;
    }

    public String getNombreCompleto() {
        return nombreCompleto;
    }

    public List getListaProductos() {
        return listaProductos;
    }
    
}
