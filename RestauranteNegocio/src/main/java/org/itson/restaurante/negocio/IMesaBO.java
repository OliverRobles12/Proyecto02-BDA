
package org.itson.restaurante.negocio;

import java.util.List;
import org.itson.restaurante.dtos.MesaDTO;

/**
 *
 * @author oliro
 */
public interface IMesaBO {
    
    /**
     * Metodo que registra cierta cantidad de mesas, se encarga de encontrar la ultima mesa
     * registrada y comeinza a agregar depues de ella
     * @param cantidad Cantidad de mesas a agregar
     * @throws NegocioException 
     */
    public abstract void registrarMesa(Integer cantidad) throws NegocioException;
    
    /**
     * Metodo que consulta las mesas que estan disponibles.
     * @return Lista de mesas disponibles.
     * @throws NegocioException 
     */
    public abstract List<MesaDTO> consultarMesasDisponibles() throws NegocioException;
    
}
