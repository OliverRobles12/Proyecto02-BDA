
package org.itson.restaurante.dtos;

import java.util.List;
import java.util.Map;

/**
 *
 * @author oliro
 */
public class NuevaComandaDTO {
    
    private Long idMesa;
    private Long idCliente;
    private List<NuevoProductoComandaDTO> productosComanda;
    private Map<Long, Double> ingredientesRequeridos;
    
    public NuevaComandaDTO() {
    }

    public NuevaComandaDTO(Long idMesa, Long idCliente, List<NuevoProductoComandaDTO> productosComanda, Map<Long, Double> ingredientesRequeridos) {
        this.idMesa = idMesa;
        this.idCliente = idCliente;
        this.productosComanda = productosComanda;
        this.ingredientesRequeridos = ingredientesRequeridos;
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

    public Map<Long, Double> getIngredientesRequeridos() {
        return ingredientesRequeridos;
    }
    
}
