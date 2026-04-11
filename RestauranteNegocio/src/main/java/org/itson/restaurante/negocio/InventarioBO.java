
package org.itson.restaurante.negocio;

import java.util.Map;
import org.itson.restaurante.dominio.Ingrediente;
import org.itson.restaurante.dominio.Producto;
import org.itson.restaurante.dominio.ProductoIngrediente;
import org.itson.restaurante.persistencia.IIngredientesDAO;
import org.itson.restaurante.persistencia.IngredientesDAO;
import org.itson.restaurante.persistencia.PersistenciaException;

/**
 *
 * @author oliro
 */
public class InventarioBO implements IInventarioBO {
    
    private IIngredientesDAO ingredientesDAO;
    
    public InventarioBO(){
        ingredientesDAO = new IngredientesDAO();
    }
    
    @Override
    public boolean sePuedePreparar(Long IdProducto, Integer cantidadPreparar) throws NegocioException{
        Producto producto = null;
        for(ProductoIngrediente productoIngrediente : producto.getReceta()) {
            double cantidadRequerida = productoIngrediente.getCantidad() * cantidadPreparar;
            int stockActual = productoIngrediente.getIngrediente().getStock();
            if (stockActual < cantidadRequerida) {
                throw new NegocioException("No hay ingredientes suficientes para preparar " + cantidadPreparar + " " + producto.getNombre(), null);
            }
        }
        return true;
    }
    
    @Override
    public boolean ingredientesNecesarios(Map<Long, Double> ingredientesRequeridos) throws NegocioException {
        try {
            for (Map.Entry<Long, Double> requerimiento : ingredientesRequeridos.entrySet()) {
                Long idIngrediente = requerimiento.getKey();
                Double cantidadTotalComanda = requerimiento.getValue();
                
                Ingrediente ingrediente = ingredientesDAO.consultarIngrediente(idIngrediente);
                
                if (ingrediente == null) {
                    throw new NegocioException("El ingrediente: " + ingrediente.getNombre() + " no existe en el inventario.", null);
                }
                
                if (ingrediente.getStock() < cantidadTotalComanda) {
                    throw new NegocioException("Stock insuficiente: Se requieren " + cantidadTotalComanda + " " + ingrediente.getUnidadMedida() + " de "
                            + ingrediente.getNombre(), null);
                }
            }
            return true;
        } catch (PersistenciaException ex) {
            throw new NegocioException("No ha sido posible validar existencia de los ingredientes necesarios.", ex);
        }
    }
    
}
