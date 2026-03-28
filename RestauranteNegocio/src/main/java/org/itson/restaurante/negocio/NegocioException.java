
package org.itson.restaurante.negocio;

/**
 *
 * @author juanl
 */
public class NegocioException extends Exception{
    
    public NegocioException(String message, Throwable cause){
        super(message, cause);
    }
}
