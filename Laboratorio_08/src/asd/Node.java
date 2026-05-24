package asd;

/**
 * Nodo básico para una lista enlazada simple, utilizado por DequeLink.
 * Almacena la información genérica del elemento y un puntero de referencia al siguiente nodo.
 * 
 * @param <E> El tipo de elemento almacenado en el nodo.
 */
public class Node<E> {
    private E value;       // El valor o dato contenido en el nodo.
    private Node<E> next;  // Referencia al siguiente nodo en la secuencia enlazada.

    /**
     * Constructor que inicializa el nodo con el valor proporcionado.
     * El puntero al siguiente nodo se inicializa como nulo.
     * @param value El valor del elemento.
     */
    public Node(E value) {
        this.value = value;
        this.next = null;
    }

    /**
     * Obtiene el dato contenido en el nodo.
     * @return El valor almacenado.
     */
    public E getData() { 
    	return value; 
    }
    
    /**
     * Obtiene la referencia al siguiente nodo.
     * @return El nodo siguiente.
     */
    public Node<E> getNext() { 
    	return next; 
    }
    
    /**
     * Establece la referencia al siguiente nodo en la lista.
     * @param next Siguiente nodo.
     */
    public void setNext(Node<E> next) { 
    	this.next = next; 
    }
}
