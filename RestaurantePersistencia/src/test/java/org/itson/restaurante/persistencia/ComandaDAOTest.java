
package org.itson.restaurante.persistencia;

import java.util.ArrayList;
import java.util.List;
import org.itson.restaurante.dominio.Comanda;
import org.itson.restaurante.dtos.NuevaComandaDTO;
import org.itson.restaurante.dtos.ProductoComandaDTO;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.BeforeEach;

/**
 *
 * @author oliro
 */
public class ComandaDAOTest {
    
    private ComandaDAO dao;
    
    public ComandaDAOTest() {
    }
    
    @BeforeEach
    public void init() {
        dao = new ComandaDAO();
    }
    
//    @Test
//    public void registrarComandaClienteGeneralFuncionaOk() {
//        
//        List<ProductoComandaDTO> productos = new ArrayList<>();
//        
//        productos.add(new ProductoComandaDTO(1, "Sin cebolla", 120D, 1L));
//        
//        NuevaComandaDTO nuevaComandaDTO = new NuevaComandaDTO(1L, productos);
//        
//        Comanda comanda = assertDoesNotThrow(() -> {
//            return dao.registrarNuevaComanda(nuevaComandaDTO);
//        });
//        
//        assertNotNull(comanda.getId());
//        
//    }
    
    @Test
    public void consultarComandasFuncionaOk() {
        
        List<Comanda> comandas = assertDoesNotThrow(() -> {
            return dao.consultarComandas();
        });
        
        for(Comanda c : comandas){
            System.out.println(c);
        }
        
    }
    
}
