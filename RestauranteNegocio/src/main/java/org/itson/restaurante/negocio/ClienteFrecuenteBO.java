
package org.itson.restaurante.negocio;

import static org.itson.restaurante.adapters.ClienteFrecuenteAClienteDTOAdapter.convertirADTO;
import static org.itson.restaurante.adapters.ClienteFrecuenteAClienteDTOAdapter.convertirListaClientesADTO;
import org.itson.restaurante.dtos.ClienteFrecuenteActualizadoDTO;
import org.itson.restaurante.dtos.ClienteFrecuenteDTO;
import org.itson.restaurante.dtos.NuevoClienteFrecuenteDTO;
import org.itson.restaurante.dominio.Cliente;
import org.itson.restaurante.dominio.ClienteFrecuente;
import org.itson.restaurante.persistencia.ClienteDAO;
import org.itson.restaurante.persistencia.PersistenciaException;
import java.time.LocalDate;

import java.util.List;
/**
 *
 * @author juanl
 */
public class ClienteFrecuenteBO implements IClienteFrecuenteBO {

    private final ClienteDAO clientesDAO;

    public ClienteFrecuenteBO() {
        this.clientesDAO = new ClienteDAO();
    }

    /**
     * Registra un nuevo cliente frecuente en el sistema tras validar sus datos.
     * Valida que los campos obligatorios no estén vacíos, que los nombres no contengan números,
     * y que el formato del correo y teléfono sean correctos.
     *
     * @param nuevoCliente Objeto DTO con los datos del nuevo cliente a registrar.
     * @return ClienteFrecuenteDTO Objeto DTO con los datos del cliente ya registrado
     * @throws NegocioException Si algún dato es inválido o si ocurre un error en la capa de persistencia.
     */
    @Override
    public ClienteFrecuenteDTO registrarCliente(NuevoClienteFrecuenteDTO nuevoCliente) throws NegocioException {

        if (nuevoCliente == null) {
            throw new NegocioException("El cliente no puede ser nulo", null);
        }
        if (nuevoCliente.getNombre() == null || nuevoCliente.getNombre().isBlank()) {
            throw new NegocioException("El nombre no puede ser nulo", null);
        }
        
        if(!(nuevoCliente.getNombre().matches("[a-zA-ZÁÉÍÓÚáéíóúÑñ ]+"))) {
            throw new NegocioException("El nombre no puede contener numeros", null);
        }
        
        if (nuevoCliente.getApellidoPaterno() == null || nuevoCliente.getApellidoPaterno().isBlank()) {
            throw new NegocioException("El apellido paterno no puede ser nulo", null);
        }

        if (!(nuevoCliente.getApellidoPaterno().matches("[a-zA-ZÁÉÍÓÚáéíóúÑñ ]+"))) {
            throw new NegocioException("El apellido paterno no puede contener numeros", null);
        }

        if (nuevoCliente.getApellidoMaterno() == null || nuevoCliente.getApellidoMaterno().isBlank()) {
            throw new NegocioException("El apellido materno no puede ser nulo", null);
        }

        if (!(nuevoCliente.getApellidoMaterno().matches("[a-zA-ZÁÉÍÓÚáéíóúÑñ ]+"))) {
            throw new NegocioException("El apellido materno no puede contener numeros", null);
        }

        if (nuevoCliente.getCorreo() == null || nuevoCliente.getCorreo().isBlank()) {
        } else {
            if (!(nuevoCliente.getCorreo().matches("^[a-zA-Z0-9._%+-]+@[a-zA-Z0-9.-]+\\.[a-zA-Z]{2,}$"))) {
                throw new NegocioException("El correo es invalido", null);
            }
        }

        if (nuevoCliente.getTelefono() == null || nuevoCliente.getTelefono().isBlank()) {
            throw new NegocioException("El telefono no puede ser nulo", null);
        }

        if (!(nuevoCliente.getTelefono().matches("^\\d{3}[- ]?\\d{3}[- ]?\\d{4}$"))) {
            throw new NegocioException("El telefono es invalido", null);
        }
        

        try {
            Cliente cliente = clientesDAO.registrarClienteFrecuente(nuevoCliente);
            //TODO
            //LocalDate fechaUltimaComanda = clientesDAO.consultarFechaUltimaComanda(cliente.getId());
            LocalDate fechaUltimaComanda = LocalDate.now(); //Eliminar esto
            return new ClienteFrecuenteDTO(
                    cliente.getId(),
                    cliente.getNombre(),
                    cliente.getApellidoPaterno(),
                    cliente.getApellidoMaterno(),
                    cliente.getTelefono(),
                    cliente.getCorreo(),
                    cliente.getFechaRegistro(), 0, 0.0d, 0,
                    fechaUltimaComanda
            );

        } catch (PersistenciaException ex) {
            throw new NegocioException("No fue posible registrar al cliente", ex);
        }
    }

    /**
     * Actualiza la información de un cliente frecuente existente.
     * Realiza las mismas validaciones de formato antes de actualizarlo.
     *
     * @param clienteActualizado Objeto DTO con los datos actualizados del cliente.
     * @return ClienteFrecuenteDTO Objeto DTO con los datos del cliente ya actualizado.
     * @throws NegocioException Si algún dato es inválido o si ocurre un error en la capa de persistencia.
     */
    @Override
    public ClienteFrecuenteDTO actualizarCliente(ClienteFrecuenteDTO clienteActualizado) throws NegocioException {
        if (clienteActualizado.getNombre() == null || clienteActualizado.getNombre().isBlank()) {
            throw new NegocioException("El nombre no puede ser nulo", null);
            
        } else if (!(clienteActualizado.getNombre().matches("[a-zA-ZÁÉÍÓÚáéíóúÑñ ]+"))) {
            throw new NegocioException("El nombre no puede contener numeros", null);
        }

        if (clienteActualizado.getApellidoPaterno() == null || clienteActualizado.getApellidoPaterno().isBlank()) {
            throw new NegocioException("El apellido paterno no puede ser nulo", null);
        }

        if (!(clienteActualizado.getApellidoPaterno().matches("[a-zA-ZÁÉÍÓÚáéíóúÑñ ]+"))) {
            throw new NegocioException("El apellido paterno no puede contener numeros", null);
        }

        if (clienteActualizado.getApellidoMaterno() == null || clienteActualizado.getApellidoMaterno().isBlank()) {
            throw new NegocioException("El apellido materno no puede ser nulo", null);
        }

        if (!(clienteActualizado.getApellidoMaterno().matches("[a-zA-ZÁÉÍÓÚáéíóúÑñ ]+"))) {
            throw new NegocioException("El apellido materno no puede contener numeros", null);
        }
        
        if (clienteActualizado.getCorreo() == null || clienteActualizado.getCorreo().isBlank()) {
        } else {
            if (!(clienteActualizado.getCorreo().matches("^[a-zA-Z0-9._%+-]+@[a-zA-Z0-9.-]+\\.[a-zA-Z]{2,}$"))) {
                throw new NegocioException("El correo es invalido", null);
            }
        }

        if (clienteActualizado.getTelefono() == null || clienteActualizado.getTelefono().isBlank()) {
            throw new NegocioException("El telefono no puede ser nulo", null);
        }

        if (!(clienteActualizado.getTelefono().matches("^\\d{3}[- ]?\\d{3}[- ]?\\d{4}$"))) {
            throw new NegocioException("El telefono es invalido", null);
        }
        try {
            ClienteFrecuenteActualizadoDTO clienteConversion = new ClienteFrecuenteActualizadoDTO(
                    clienteActualizado.getId(),
                    clienteActualizado.getNombre(),
                    clienteActualizado.getApellidoPaterno(),
                    clienteActualizado.getApellidoMaterno(),
                    clienteActualizado.getTelefono(),
                    clienteActualizado.getCorreo()
            );
            ClienteFrecuente cliente = clientesDAO.actualizarClienteFrecuente(clienteConversion);
            
            return convertirADTO(cliente);
        } catch (PersistenciaException ex) {
            throw new NegocioException("No fue posible actualizar al cliente", ex);
        }

    }
    
    /**
     * Consulta una lista de clientes frecuentes que se encuentren con el filtro
     * @param filtro parametro para filtrar la búsqueda
     * @return List&lt;ClienteFrecuenteDTO&gt; Lista de clientes que coinciden con el criterio de búsqueda.
     * @throws NegocioException Si ocurre un error al realizar la consulta
     */
    @Override
    public List<ClienteFrecuenteDTO> consultarClienteFiltro(String filtro) throws NegocioException {
        try {

            List<ClienteFrecuente> clientes = clientesDAO.consultarClientesFrecuentesFiltro(filtro);


            return convertirListaClientesADTO(clientes);

        } catch (PersistenciaException ex) {
            throw new NegocioException("No fue posible consultar al cliente", ex);
        }
    }

    
    /**
     * Consulta y devuelve la lista completa de todos los clientes frecuentes registrados en el sistema.
     *
     * @return List&lt;ClienteFrecuenteDTO&gt; Lista con todos los clientes frecuentes.
     * @throws NegocioException Si ocurre un error al realizar la consulta
     */
    @Override
    public List<ClienteFrecuenteDTO> consultarClientes() throws NegocioException {
        try {

            List<ClienteFrecuente> clientes = clientesDAO.consultarClientesFrecuentes();

            return convertirListaClientesADTO(clientes);

        } catch (PersistenciaException ex) {
            throw new NegocioException("No fue posible consultar a los cliente", ex);
        }
    }

}
