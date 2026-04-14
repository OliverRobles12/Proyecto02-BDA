
package org.itson.restaurante.persistencia;

import java.time.LocalDate;
import org.itson.restaurante.dtos.ClienteFrecuenteActualizadoDTO;
import org.itson.restaurante.dtos.NuevoClienteFrecuenteDTO;
import org.itson.restaurante.dominio.Cliente;
import org.itson.restaurante.dominio.ClienteFrecuente;
import java.util.List;

/**
 *
 * @author oliro
 */
public interface IClienteDAO {

    /**
     * Método para registrar un cliente frecuente nuevo en la base de datos.
     * @param nuevoCliente NuevoClienteFrecuenteDTO con la información del cliente a registrar.
     * @return El objeto ClienteFrecuente creado, incluyendo su ID generado.
     * @throws PersistenciaException Si ocurre un error en la base de datos.
     */
    public abstract Cliente registrarClienteFrecuente(NuevoClienteFrecuenteDTO nuevoCliente) throws PersistenciaException;

    /**
     * Método para actualizar los datos de un cliente frecuente ya existente en la base de datos.
     * @param clienteActualizado ClienteFrecuenteDTO con los nuevos datos y el ID del cliente a modificar.
     * @return El objeto ClienteFrecuente con la información actualizada.
     * @throws PersistenciaException Si ocurre un error en la base de datos.
     */
    public abstract ClienteFrecuente actualizarClienteFrecuente(ClienteFrecuenteActualizadoDTO clienteActualizado) throws PersistenciaException;

    /**
     * Método para eliminar un cliente frecuente que se encuentre actualmente en la base de datos.
     * @param id Identificador único del cliente a eliminar.
     * @return El objeto ClienteFrecuente que ha sido eliminado.
     * @throws PersistenciaException Si ocurre un error en la base de datos.
     */
    public abstract Cliente eliminarCliente(Long id) throws PersistenciaException;

    /**
     * Metodo que consulta al cliente frecuente con el id proporcionado.
     * @param id Identificador del cliente.
     * @return ClienteFrecuente encontrado.
     * @throws PersistenciaException 
     */
    public abstract Cliente consultarClienteFrecuente(Long id) throws PersistenciaException;
    
    /**
     * Método que hace una consulta con todos los clientes frecuentes en el sistema y devuelve una lista con ellos.
     * @return Una lista de objetos ClienteFrecuente; si no hay, devuelve una lista vacía.
     * @throws PersistenciaException Si ocurre un error en la base de datos.
     */
    public abstract List<ClienteFrecuente> consultarClientesFrecuentes() throws PersistenciaException;

    /**
     * Método para hacer una consulta de todos los clientes frecuentes donde el filtro pueda coincidir con alguno de los parámetros:
     * nombre, teléfono o correo.
     * @param filtro Filtro de texto a buscar en los campos del cliente.
     * @return Una lista de objetos ClienteFrecuente que coinciden con el criterio de búsqueda; si no hay, devuelve una lista vacía.
     * @throws PersistenciaException Si ocurre un error en la base de datos.
     */
    public abstract List<ClienteFrecuente> consultarClientesFrecuentesFiltro(String filtro) throws PersistenciaException;
    
    public LocalDate consultarFechaUltimaComanda(Long idCliente) throws PersistenciaException ;
}
