
package org.itson.restaurante.adapters;

import org.itson.restaurante.dominio.ProductoComanda;
import org.itson.restaurante.dtos.ProductoComandaDTO;

/**
 *
 * @author oliro
 */
public class ProductoComandaAProductoComandaDTOAdapter {

    public static ProductoComandaDTO adapter(ProductoComanda productoComanda) {
        return new ProductoComandaDTO(
                productoComanda.getCantidad(),
                productoComanda.getDetalles(), 
                productoComanda.getSubtotal(), 
                productoComanda.getProducto().getId(), 
                productoComanda.getProducto().getNombre()
        );
    }
    
}
