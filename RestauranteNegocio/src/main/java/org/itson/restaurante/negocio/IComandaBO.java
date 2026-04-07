
package org.itson.restaurante.negocio;

import java.util.List;
import org.itson.restaurante.dtos.ComandaDTO;
import org.itson.restaurante.dtos.NuevaComandaDTO;

/**
 *
 * @author oliro
 */
public interface IComandaBO {

    public ComandaDTO registrarNuevaComanda(NuevaComandaDTO nuevaComanda) throws NegocioException;
    
    public List<ComandaDTO> consultarComandas() throws NegocioException;
    
}
