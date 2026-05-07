package actividad2;

/**
 * Clase que representa un Nodo simple para una lista simplemente enlazada.
 * Cada nodo almacena un dato y una referencia al siguiente nodo.
 *
 * @param <E> El tipo de dato almacenado en el nodo.
 */
public class Node<E> {
    private E value;      // El valor almacenado
    private Node<E> next; // Puntero al siguiente nodo

    /**
     * Constructor del Nodo.
     * @param value El valor a inicializar en el nodo.
     */
    public Node(E value) {
        this.value = value;
        this.next = null; // Por defecto no apunta a nada
    }

    /** Retorna el dato almacenado. */
    public E getData() { 
    	return value; 
    }
    
    /** Retorna el siguiente nodo de la lista. */
    public Node<E> getNext() { 
    	return next; 
    }
    
    /** Establece la referencia hacia el siguiente nodo. */
    public void setNext(Node<E> next) { 
    	this.next = next; 
    }
}
