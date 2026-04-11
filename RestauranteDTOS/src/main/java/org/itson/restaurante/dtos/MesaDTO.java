
package org.itson.restaurante.dtos;

/**
 *
 * @author oliro
 */
public class MesaDTO {

    private Long id;
    private EstadoMesa estado;
    private Integer noMesa;

    public MesaDTO(Long id, EstadoMesa estado, Integer noMesa) {
        this.id = id;
        this.estado = estado;
        this.noMesa = noMesa;
    }
    
    public Long getId() {
        return id;
    }

    public EstadoMesa getEstado() {
        return estado;
    }

    public Integer getNoMesa() {
        return noMesa;
    }
    
    @Override
    public String toString() {
        return "Mesa " + noMesa;
    }
    
}
