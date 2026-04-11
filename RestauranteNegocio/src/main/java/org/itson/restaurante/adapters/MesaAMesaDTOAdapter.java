
package org.itson.restaurante.adapters;

import org.itson.restaurante.dominio.Mesa;
import org.itson.restaurante.dtos.EstadoMesa;
import org.itson.restaurante.dtos.MesaDTO;

/**
 *
 * @author oliro
 */
public class MesaAMesaDTOAdapter {
    
    public static MesaDTO adapter(Mesa mesa) {
        
        EstadoMesa estado = EstadoMesa.DISPONIBLE;
        if (mesa.getEstado() == org.itson.restaurante.dominio.EstadoMesa.INACTIVA) {
            estado = EstadoMesa.INACTIVA;
        } else if (mesa.getEstado() == org.itson.restaurante.dominio.EstadoMesa.OCUPADA) {
            estado = EstadoMesa.OCUPADA;
        }
        
       return new MesaDTO(
                mesa.getId(), 
                estado, 
                mesa.getNoMesa()
        );
        
    }
    
}
