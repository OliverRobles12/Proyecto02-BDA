
package org.itson.restaurante.persistencia;

import java.util.List;
import javax.persistence.PersistenceException;
import org.itson.restaurante.dominio.Mesa;

/**
 *
 * @author oliro
 */
public interface IMesaDAO {
    
    /**
     * Registra la mesa y la inicializa con un estado de disponible.
     * @param noMesa Numero de la mesa nueva.
     * @return La nueva mesa registrada.
     * @throws PersistenciaException 
     */
    public abstract Mesa registrarMesa(Integer noMesa) throws PersistenciaException;
    
    /**
     * Eliminar una mesa mediante su id.
     * @param idMesa Id de la mesa a eliominar.
     * @return Regresa la mesa eliminada.
     * @throws PersistenciaException 
     */
    public abstract Mesa eliminarMesa(Long idMesa) throws PersistenciaException;
 
    /**
     * Consulta las mesas con estado disponibles.
     * @return Lista de las mesas.
     * @throws PersistenceException 
     */
    public abstract List<Mesa> consultarMesasDisponibles() throws PersistenciaException;
    
    /**
     * Consulta la mesa con el id solicitado;
     * @param id Id mesa;
     * @return Mesa encontrada.
     * @throws PersistenciaException 
     */
    public abstract Mesa consultarMesa(Long id) throws PersistenciaException;
    
    /**
     * Metodo que regresa el noMesa de la ultima mesa registrada, en caso de que no haya mesas
     * registradas regresara 0.
     * @return noMesa de la ultima mesa registrada.
     * @throws PersistenceException
     */
    public abstract Integer consultarNumeroUltimaMesa() throws PersistenciaException;
    
}
