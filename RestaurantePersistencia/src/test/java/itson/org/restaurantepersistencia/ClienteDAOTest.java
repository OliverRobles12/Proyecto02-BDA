
package itson.org.restaurantepersistencia;

import com.mycompany.restaurantedtos.NuevoClienteFrecuenteDTO;
import itson.org.restaurantedominio.Cliente;
import java.time.LocalDate;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

/**
 *
 * @author oliro
 */
public class ClienteDAOTest {
    
    ClienteDAO dao;
    
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
        
    }
    
}
