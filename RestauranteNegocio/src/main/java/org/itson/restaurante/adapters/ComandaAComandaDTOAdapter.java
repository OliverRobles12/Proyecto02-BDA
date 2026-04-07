
package org.itson.restaurante.adapters;

import org.itson.restaurante.dominio.Comanda;
import org.itson.restaurante.dtos.ComandaDTO;
import org.itson.restaurante.dtos.EstadoComanda;

/**
 *
 * @author oliro
 */
public class ComandaAComandaDTOAdapter {

    public static ComandaDTO adapter(Comanda comanda) {
        
        String nombreCliente = "Cliente general";
        if (comanda.getCliente() != null) {
            nombreCliente = comanda.getCliente().getNombre() + " " + comanda.getCliente().getApellidoPaterno() + " " + comanda.getCliente().getApellidoMaterno();
        }
        
        EstadoComanda estado = EstadoComanda.ABIERTA;
        
        if (comanda.getEstado() == org.itson.restaurante.dominio.EstadoComanda.CANCELADA) {
            estado = EstadoComanda.CANCELADA;
        } else if (comanda.getEstado() == org.itson.restaurante.dominio.EstadoComanda.ENTREGADA) {
            estado = EstadoComanda.ENTREGADA;
        }
        
        return new ComandaDTO(
                comanda.getId(),
                comanda.getFolio(),
                estado,
                comanda.getTotalAcumulado(), 
                comanda.getFechaHora(), 
                comanda.getMesa().getNoMesa(), 
                nombreCliente
        );
        
    }
    
}
