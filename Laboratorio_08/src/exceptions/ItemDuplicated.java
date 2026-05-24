package exceptions;

/**
 * Excepción personalizada para indicar que se ha intentado insertar un elemento
 * que ya existe en la estructura de datos (como un árbol binario de búsqueda BST o un AVL).
 */
public class ItemDuplicated extends Exception {
    
    /**
     * Constructor que acepta un mensaje detallado de error.
     * @param message Mensaje descriptivo indicando el elemento duplicado.
     */
    public ItemDuplicated(String message) {
        super(message);
    }
    
    /**
     * Constructor por defecto sin mensaje.
     */
    public ItemDuplicated() {
        super();
    }
}