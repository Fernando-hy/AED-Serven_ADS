package exceptions;

/**
 * Excepción personalizada para indicar que un elemento buscado no ha sido encontrado
 * en la estructura de datos correspondiente.
 */
public class ItemNotFound extends Exception {
    
    /**
     * Constructor que recibe un mensaje explicativo de la búsqueda fallida.
     * @param msg Mensaje detallado del error.
     */
    public ItemNotFound(String msg) {
        super(msg);
    }
    
    /**
     * Constructor por defecto sin mensaje.
     */
    public ItemNotFound() {
        super();
    }
}