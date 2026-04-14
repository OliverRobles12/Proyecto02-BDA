package org.itson.restaurante.persistencia;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.EntityManager;
import org.itson.restaurante.dominio.Comanda;
import org.itson.restaurante.dominio.EstadoComanda;
import org.itson.restaurante.dominio.EstadoMesa;
import org.itson.restaurante.dominio.EstadoProducto;
import org.itson.restaurante.dominio.Ingrediente;
import org.itson.restaurante.dominio.Mesa;
import org.itson.restaurante.dominio.Producto;
import org.itson.restaurante.dominio.ProductoIngrediente;
import org.itson.restaurante.dominio.TipoProducto;
import org.itson.restaurante.dominio.UnidadMedida;
import org.itson.restaurante.dtos.NuevaComandaDTO;
import org.itson.restaurante.dtos.NuevoProductoComandaDTO;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class ComandaDAOTest {

    private ComandaDAO comandaDAO;
    private Long idMesaPrueba;
    private Long idProductoPrueba;
    private Long idIngredientePrueba;
    private String folioComandaPrueba;
    private Long idComandaPrueba;

    public ComandaDAOTest() {
    }

    @BeforeEach
    public void setup() {
        comandaDAO = new ComandaDAO();
        EntityManager em = null;

        try {
        em = ManejadorConexiones.crearEntityManager();
        em.getTransaction().begin();

        // 1. Creas y persistes (aún no tienen ID)
        Mesa mesa = new Mesa();
        mesa.setEstado(EstadoMesa.DISPONIBLE);
        mesa.setNoMesa((int) (System.currentTimeMillis() % 100000)); // Usando el fix anterior
        em.persist(mesa);

        Ingrediente ingrediente = new Ingrediente("Ingrediente Test", UnidadMedida.GRAMOS, 100);
        em.persist(ingrediente);

        Producto producto = new Producto("Producto Test", 50.0, TipoProducto.PLATILLO, EstadoProducto.ACTIVO, "Desc", null);
        ProductoIngrediente pi = new ProductoIngrediente(1.0);
        pi.setIngrediente(ingrediente);
        pi.setProducto(producto);
        producto.getReceta().add(pi);
        em.persist(producto);

        // 2. Haces el commit (AQUÍ la base de datos genera y asigna los IDs)
        em.getTransaction().commit();

        // 3. AHORA SÍ, lees los IDs porque ya no son nulos
        this.idMesaPrueba = mesa.getId();
        this.idIngredientePrueba = ingrediente.getId();
        this.idProductoPrueba = producto.getId();

        // 4. Continúas con la lógica de tu DAO usando los IDs correctos
        List<NuevoProductoComandaDTO> listaProductos = new ArrayList<>();
        listaProductos.add(new NuevoProductoComandaDTO(1, idProductoPrueba, "Producto Test", 2, "Sin cebolla", 100.0, 50.0));
        
        NuevaComandaDTO nuevaComandaDTO = new NuevaComandaDTO(idMesaPrueba, listaProductos);

        Comanda comandaGenerada = comandaDAO.registrarNuevaComanda(nuevaComandaDTO);
        this.folioComandaPrueba = comandaGenerada.getFolio();
        this.idComandaPrueba = comandaGenerada.getId();

    } catch (Exception ex) {
        if (em != null && em.getTransaction().isActive()) {
            em.getTransaction().rollback();
        }
        Assertions.fail(ex.getMessage());
    } finally {
        if (em != null) em.close();
    }
    }

    @AfterEach
    public void tearDown() {
        EntityManager em = null;
        try {
            em = ManejadorConexiones.crearEntityManager();
            em.getTransaction().begin();

            if (idComandaPrueba != null) {
                Comanda c = em.find(Comanda.class, idComandaPrueba);
                if (c != null) em.remove(c);
            }

            if (idProductoPrueba != null) {
                Producto p = em.find(Producto.class, idProductoPrueba);
                if (p != null) em.remove(p);
            }

            if (idIngredientePrueba != null) {
                Ingrediente i = em.find(Ingrediente.class, idIngredientePrueba);
                if (i != null) em.remove(i);
            }

            if (idMesaPrueba != null) {
                Mesa m = em.find(Mesa.class, idMesaPrueba);
                if (m != null) em.remove(m);
            }

            em.getTransaction().commit();
        } catch (Exception ex) {
        } finally {
            if (em != null) em.close();
        }
    }

    @Test
    public void testRegistrarNuevaComandaFuncionaOk() {
        // setup
        List<NuevoProductoComandaDTO> listaProductos = new ArrayList<>();
        listaProductos.add(new NuevoProductoComandaDTO(2, idProductoPrueba, "Producto Test", 1, "Extra queso", 50.0, 50.0));
        
        NuevaComandaDTO nuevaComandaDTO = new NuevaComandaDTO(idMesaPrueba, listaProductos);

        // ejecucion
        Comanda comanda = Assertions.assertDoesNotThrow(() -> {
            return comandaDAO.registrarNuevaComanda(nuevaComandaDTO);
        });

        // validacion
        Assertions.assertNotNull(comanda.getId());
        Assertions.assertNotNull(comanda.getFolio());
        Assertions.assertEquals(EstadoComanda.ABIERTA, comanda.getEstado());

        // teardown
        EntityManager em = ManejadorConexiones.crearEntityManager();
        em.getTransaction().begin();
        em.remove(em.find(Comanda.class, comanda.getId()));
        em.getTransaction().commit();
        em.close();
    }

    @Test
    public void testConsultarComandasFuncionaOk() {
        // ejecucion
        List<Comanda> comandas = Assertions.assertDoesNotThrow(() -> {
            return comandaDAO.consultarComandas();
        });

        // validacion
        Assertions.assertFalse(comandas.isEmpty());
    }

    @Test
    public void testConsultarComandasFechaFuncionaOk() {
        // setup
        LocalDate hoy = LocalDate.now();
        
        // ejecucion
        List<Comanda> comandas = Assertions.assertDoesNotThrow(() -> {
            return comandaDAO.consultarComandasFecha(hoy.minusDays(1), hoy.plusDays(1));
        });

        // validacion
        Assertions.assertFalse(comandas.isEmpty());
    }

    @Test
    public void testActualizarComandaFuncionaOk() {
        // setup
        Comanda comandaPrueba = Assertions.assertDoesNotThrow(() -> {
            return comandaDAO.consultarComanda(folioComandaPrueba);
        });
        comandaPrueba.setEstado(EstadoComanda.CANCELADA); 

        // ejecucion
        boolean resultado = Assertions.assertDoesNotThrow(() -> {
            return comandaDAO.actualizarComanda(comandaPrueba);
        });

        // validacion
        Assertions.assertTrue(resultado);
        Comanda comandaActualizada = Assertions.assertDoesNotThrow(() -> {
            return comandaDAO.consultarComanda(folioComandaPrueba);
        });
        Assertions.assertEquals(EstadoComanda.CANCELADA, comandaActualizada.getEstado());
    }

    @Test
    public void testConsultarComandaPorFolioFuncionaOk() {
        // ejecucion
        Comanda comanda = Assertions.assertDoesNotThrow(() -> {
            return comandaDAO.consultarComanda(folioComandaPrueba);
        });

        // validacion
        Assertions.assertNotNull(comanda);
        Assertions.assertEquals(folioComandaPrueba, comanda.getFolio());
    }
    
    @Test
    public void testConsultarComandaPorFolioNoExistenteRetornaNull() {
        // setup
        String folioInvalido = "FOLIO_FALSO_123";
        
        // ejecucion
        Comanda comanda = Assertions.assertDoesNotThrow(() -> {
            return comandaDAO.consultarComanda(folioInvalido);
        });

        // validacion
        Assertions.assertNull(comanda);
    }

}