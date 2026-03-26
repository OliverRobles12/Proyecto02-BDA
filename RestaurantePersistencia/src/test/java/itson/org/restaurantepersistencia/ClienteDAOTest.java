
package itson.org.restaurantepersistencia;

import com.mycompany.restaurantedtos.ClienteFrecuenteActualizadoDTO;
import com.mycompany.restaurantedtos.NuevoClienteFrecuenteDTO;
import itson.org.restaurantedominio.Cliente;
import itson.org.restaurantedominio.ClienteFrecuente;
import java.time.LocalDate;
import java.util.List;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

/**
 *
 * @author oliro
 */
public class ClienteDAOTest {
    
    private ClienteDAO dao;
    private Long idCliente = 5L;
    
    public ClienteDAOTest() {
    }
    
    @BeforeEach
    public void init(){
        dao = new ClienteDAO();
    }

    @Test
    public void testCrearClienteFuncionaOk() {
        
        NuevoClienteFrecuenteDTO nuevoCliente = new NuevoClienteFrecuenteDTO(
                "Oliver", 
                "Robles", 
                "Cota", 
                "1234567890", 
                LocalDate.now()
        );
        
        Cliente cliente = Assertions.assertDoesNotThrow(() -> {
            return dao.registrarCliente(nuevoCliente);
        });
        
        Assertions.assertNotNull(cliente.getId());
        
        idCliente = cliente.getId();
        
    }
    
    @Test
    public void testActualizarClienteFrecuenteFuncionaOk() {
        
        ClienteFrecuenteActualizadoDTO clienteActualizado = new ClienteFrecuenteActualizadoDTO(
                idCliente,
                "Oliver", 
                "Robles", 
                "Cota", 
                "1234567890",
                "oliver@gmail.com"
        );
        
        Cliente cliente = Assertions.assertDoesNotThrow(() -> {
            return dao.actualizarCliente(clienteActualizado);
        });
        
        Assertions.assertEquals(cliente.getCorreo(), "oliver@gmail.com");
        
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
        
        for(ClienteFrecuente c : clientes) {
            System.out.println(c);
        }
        
        
    }
    
    @Test
    public void testConsultarClientesFrecuentesFiltroFuncionaOk() {
        
        List<ClienteFrecuente> clientes = Assertions.assertDoesNotThrow(() -> {
            return dao.consultarClientesFrecuentesFiltro("Ju");
        });
        
        for(ClienteFrecuente c : clientes) {
            System.out.println(c);
        }
        
    }
    
}
