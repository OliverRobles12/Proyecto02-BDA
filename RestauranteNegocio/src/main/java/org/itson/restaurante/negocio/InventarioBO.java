
package org.itson.restaurante.negocio;

import java.util.Map;
import org.itson.restaurante.dominio.Ingrediente;
import org.itson.restaurante.dominio.Producto;
import org.itson.restaurante.dominio.ProductoIngrediente;
import org.itson.restaurante.persistencia.IIngredientesDAO;
import org.itson.restaurante.persistencia.IProductoDAO;
import org.itson.restaurante.persistencia.IngredientesDAO;
import org.itson.restaurante.persistencia.PersistenciaException;
import org.itson.restaurante.persistencia.ProductoDAO;

/**
 *
 * @author oliro
 */
public class InventarioBO implements IInventarioBO {
    
    private IIngredientesDAO ingredientesDAO;
    private IProductoDAO productoDAO;
    
    public InventarioBO(){
        this.ingredientesDAO = new IngredientesDAO();
        this.productoDAO = new ProductoDAO();
    }
    
    @Override
    public boolean ingredientesNecesarios(Map<Long, Double> ingredientesRequeridos) throws NegocioException {
        try {
            for (Map.Entry<Long, Double> requerimiento : ingredientesRequeridos.entrySet()) {
                Long idIngrediente = requerimiento.getKey();
                Double cantidadTotalComanda = requerimiento.getValue();
                
                Ingrediente ingrediente = ingredientesDAO.consultarIngrediente(idIngrediente);
                
                if (ingrediente == null) {
                    throw new NegocioException("El ingrediente con Id: " + idIngrediente + " no se encontro en la base da datos.", null);
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
