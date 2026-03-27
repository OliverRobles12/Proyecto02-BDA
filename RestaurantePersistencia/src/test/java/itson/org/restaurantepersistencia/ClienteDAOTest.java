
package itson.org.restaurantepersistencia;

import com.mycompany.restaurantedtos.ClienteFrecuenteActualizadoDTO;
import com.mycompany.restaurantedtos.NuevoClienteFrecuenteDTO;
import itson.org.restaurantedominio.Cliente;
import itson.org.restaurantedominio.ClienteFrecuente;
import java.time.LocalDate;
import java.util.List;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

/**
 *
 * @author oliro
 */
public class ClienteDAOTest {
    
    private ClienteDAO dao;
    private Long idCliente;
    
    public ClienteDAOTest() {
    }
    
    @BeforeEach
    public void setup(){
        dao = new ClienteDAO();
        
        NuevoClienteFrecuenteDTO clientePrueba = new NuevoClienteFrecuenteDTO(
                "Cliente", "Test", "Temporal", "1234567890", "test@gmail.com", LocalDate.now()
        );
        
        try {
            Cliente cliente = dao.registrarClienteFrecuente(clientePrueba);
            this.idCliente = cliente.getId();
        } catch (Exception ex) {
            Assertions.fail("No se puedo generar al cliente de prueba: " + ex.getMessage());
        }
        
    }
    
    @AfterEach
    public void tearDown(){
        if (this.idCliente != null) {
            try {
                dao.eliminarCliente(this.idCliente);
            } catch (Exception ex) {
                System.err.println("No se pudo limpiar al cliente de prueba: " + ex.getMessage());
            }
        }
    }
    

    @Test
    public void testCrearClienteFuncionaOk() {
        
        NuevoClienteFrecuenteDTO nuevoCliente = new NuevoClienteFrecuenteDTO(
                "Juan", "Perez", "Garcia", "1234567890", LocalDate.now()
        );
        
        Cliente cliente = Assertions.assertDoesNotThrow(() -> {
            return dao.registrarClienteFrecuente(nuevoCliente);
        });
        
        Assertions.assertNotNull(cliente.getId());
        
        Assertions.assertDoesNotThrow(() -> {
            dao.eliminarCliente(cliente.getId());
        });
        
    }
    
    @Test
    public void testActualizarClienteFrecuenteFuncionaOk() {
        
        ClienteFrecuenteActualizadoDTO clienteActualizado = new ClienteFrecuenteActualizadoDTO(
                idCliente,
                "Maria", 
                "Lopez", 
                "Lopez", 
                "1234567890",
                "maria@gmail.com"
        );
        
        Cliente cliente = Assertions.assertDoesNotThrow(() -> {
            return dao.actualizarClienteFrecuente(clienteActualizado);
        });
        
        Assertions.assertEquals(cliente.getCorreo(), "maria@gmail.com");
        
    }
    
    @Test
    public void testEliminarClienteFrecuenteFuncionaOk() {
        
        Cliente cliente = Assertions.assertDoesNotThrow(() -> {
            return dao.eliminarCliente(idCliente);
        });
        
        Assertions.assertNotNull(cliente.getId());
        
    }
    
    @Test
    public void testConsultarClientesFrecuentesFuncionaOk() {
        
        List<ClienteFrecuente> clientes = Assertions.assertDoesNotThrow(() -> {
            return dao.consultarClientesFrecuentes();
        });

        Assertions.assertTrue(!clientes.isEmpty());
        
    }
    
    @Test
    public void testConsultarClientesFrecuentesFiltroFuncionaOk() {
        
        List<ClienteFrecuente> clientes = Assertions.assertDoesNotThrow(() -> {
            return dao.consultarClientesFrecuentesFiltro("test@gmail.com");
        });
        
        Assertions.assertTrue(!clientes.isEmpty());
        
    }
    
    
}
