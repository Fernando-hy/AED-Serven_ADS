package actividadPilaLista;

/**
 * Clase genérica Node para listas enlazadas.
 * Almacena un elemento y una referencia al siguiente nodo en la estructura.
 *
 * @param <E> El tipo de dato que almacenará el nodo.
 */
public class Node<E> {
    private E value;      // Valor del nodo
    private Node<E> next; // Referencia al siguiente nodo

    /**
     * Constructor del nodo.
     * @param value El dato a almacenar.
     */
    public Node(E value) {
        this.value = value;
        this.next = null; // Inicializa sin apuntar a nada
    }

    /** Retorna el dato contenido. */
    public E getData() { 
    	return value; 
    }
    
    /** Retorna el nodo siguiente. */
    public Node<E> getNext() { 
    	return next; 
    }
    
    /** Asigna el siguiente nodo al que apuntará. */
    public void setNext(Node<E> next) { 
    	this.next = next; 
    }
}
