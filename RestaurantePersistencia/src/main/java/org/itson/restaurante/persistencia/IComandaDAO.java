
package org.itson.restaurante.persistencia;

import java.util.List;
import org.itson.restaurante.dominio.Comanda;
import org.itson.restaurante.dtos.NuevaComandaDTO;

/**
 *
 * @author oliro
 */
public interface IComandaDAO {

    public abstract Comanda registrarNuevaComanda(NuevaComandaDTO nuevaComanda) throws PersistenciaException;
    
    public abstract List<Comanda> consultarComandas() throws PersistenciaException;
    
}
