package exceptions;

/**
 * Excepción personalizada para indicar que una estructura de datos (como un árbol,
 * una cola o un deque) se encuentra vacía al intentar realizar una operación no permitida.
 */
public class ExceptionIsEmpty extends Exception {
    
    /**
     * Constructor que acepta un mensaje detallado del error.
     * @param msg Mensaje descriptivo de la excepción.
     */
    public ExceptionIsEmpty(String msg) {
        super(msg);
    }
    
    /**
     * Constructor por defecto sin mensaje.
     */
    public ExceptionIsEmpty() {
        super();
    }
}