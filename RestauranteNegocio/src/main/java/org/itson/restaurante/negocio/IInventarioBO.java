
package org.itson.restaurante.negocio;

import java.util.Map;

/**
 *
 * @author oliro
 */
public interface IInventarioBO {
    
    public abstract boolean sePuedePreparar(Long IdProducto, Integer cantidadPreparar) throws NegocioException;
    
    public abstract boolean ingredientesNecesarios(Map<Long, Double> ingredientesRequeridos) throws NegocioException;
    
}
