
package org.itson.restaurante.negocio;

import java.time.LocalDate;
import java.util.List;
import org.itson.restaurante.dtos.ComandaDTO;
import org.itson.restaurante.dtos.NuevaComandaDTO;

/**
 *
 * @author oliro
 */
public interface IComandaBO {

    /**
     * Metodo encargado de registrar una nueva comanda comprobando el stock de los ingredientes
     * necesarios, comprobando el cliente referido y que la mesa se encuentre disponible.
     * @param nuevaComanda NuevaComandaDTO con los productos seleccionados, mesa y cliente referido.
     * @return Resultado de la operacion
     * @throws NegocioException 
     */
    public boolean registrarNuevaComanda(NuevaComandaDTO nuevaComanda) throws NegocioException;
    
    /**
     * Metodo que cambia el marca la entrega de una comanda. Ademas libera la mesa asociada a la comanda.
     * @param folio Folio identificador de la comanda.
     * @return
     * @throws NegocioException 
     */
    public boolean marcarEntregaComanda(String folio) throws NegocioException;
    
    /**
     * Metodo que consulta la comanda donde el folio coincide.
     * @param folio
     * @return
     * @throws NegocioException 
     */
    public ComandaDTO consultarComanda(String folio) throws NegocioException;
    
    /**
     * Metodo que conulta las comanda registradas.
     * @return
     * @throws NegocioException 
     */
    public List<ComandaDTO> consultarComandas() throws NegocioException;
    
    /**
     * 
     * @param inicio
     * @param fin
     * @return
     * @throws NegocioException 
     */
    public List<ComandaDTO> consultarComandasFechas(LocalDate inicio,LocalDate fin) throws NegocioException;
    
}
