
package org.itson.restaurante.dtos;

import java.util.List;

/**
 *
 * @author oliro
 */
public class NuevaComandaDTO {
    
    private Long idMesa;
    private Long idCliente;
    private List<NuevoProductoComandaDTO> productosComanda;
    
    public NuevaComandaDTO() {
    }

    public NuevaComandaDTO(Long idMesa, Long idCliente, List<NuevoProductoComandaDTO> productosComanda) {
        this.idMesa = idMesa;
        this.idCliente = idCliente;
        this.productosComanda = productosComanda;
    }

    public NuevaComandaDTO(Long idMesa, List<NuevoProductoComandaDTO> productosComanda) {
        this.idMesa = idMesa;
        this.productosComanda = productosComanda;
    }

    public Long getIdMesa() {
        return idMesa;
    }

    public Long getIdCliente() {
        return idCliente;
    }

    public void setIdCliente(Long idCliente) {
        this.idCliente = idCliente;
    }

    public List<NuevoProductoComandaDTO> getProductosComanda() {
        return productosComanda;
    }
    
}
