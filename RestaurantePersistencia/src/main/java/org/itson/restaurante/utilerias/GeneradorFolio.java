
package org.itson.restaurante.utilerias;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

/**
 *
 * @author oliro
 */
public class GeneradorFolio {
    
    /**
     * Metodo encargado de generar un folio a una comanda
     * @param numeroConsecutivo Numero consecutivo al final de la comanda
     * @return 
     */
    public static String generarFolio(Long numeroConsecutivo){
        
        DateTimeFormatter FORMATO_FECHA = DateTimeFormatter.ofPattern("yyyyMMdd");
        String fechaString = LocalDate.now().format(FORMATO_FECHA);
        String numero = String.format("%03d", numeroConsecutivo);
        String folio = "OB-" + fechaString + "-" + numero;
        
        return folio;
        
    }
    
    
    
}
