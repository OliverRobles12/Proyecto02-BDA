
package org.itson.restaurante.persistencia;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

/**
 *
 * @author oliro
 */
public class ManejadorConexiones {

    public static EntityManager crearEntityManager() {
        // Fábrica que permite construir entity managers a partir de ciertas configuraciones
        EntityManagerFactory emFactory = Persistence.createEntityManagerFactory("RestauranteDominioPU");
        // Objrego que permite hacer operaciones de BD
        EntityManager entityManager = emFactory.createEntityManager();
        return entityManager;
    } 
    
}
