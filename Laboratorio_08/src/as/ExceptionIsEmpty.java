package as;

/**
 * Excepción personalizada para el paquete 'as' indicando que se ha intentado
 * extraer o examinar un elemento de una cola vacía.
 */
public class ExceptionIsEmpty extends Exception {
    
    /**
     * Constructor con mensaje explicativo.
     * @param message Mensaje descriptivo de la excepción.
     */
    public ExceptionIsEmpty(String message) {
        super(message);
    }
}