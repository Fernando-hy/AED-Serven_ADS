package paquete;

/**
 * Representa un nodo genérico dentro de un Árbol Binario de Búsqueda (BST) y Árbol AVL.
 * Almacena el dato de información de tipo genérico, referencias a los nodos hijos izquierdo y derecho,
 * y el factor de equilibrio (bf - balance factor) usado para las rotaciones AVL.
 * 
 * @param <E> El tipo del dato almacenado en el nodo.
 */
public class Node<E> {
    private E data;          // El dato o valor de información del nodo.
    private Node<E> left;    // Puntero o referencia al nodo hijo izquierdo.
    private Node<E> right;   // Puntero o referencia al nodo hijo derecho.
    private int bf;          // Factor de equilibrio del nodo (altura_derecha - altura_izquierda).

    /**
     * Constructor para inicializar el nodo con un valor y establecer sus referencias como nulas.
     * El factor de equilibrio inicial es 0 (balanceado).
     * @param value Valor con el que se inicializa el nodo.
     */
    public Node(E value) {
        this.data = value;
        this.left = null;
        this.right = null;
        this.bf = 0;
    }

    /**
     * Obtiene el dato almacenado en el nodo.
     * @return El valor genérico almacenado.
     */
    public E getData() { 
    	return this.data; 
    }
    
    /**
     * Obtiene el hijo izquierdo del nodo.
     * @return El nodo hijo izquierdo.
     */
    public Node<E> getLeft() { 
    	return this.left; 
    }

    /**
     * Obtiene el hijo derecho del nodo.
     * @return El nodo hijo derecho.
     */
    public Node<E> getRight() { 
    	return right; 
    }
    
    /**
     * Obtiene el factor de equilibrio del nodo (bf).
     * En AVL, se define típicamente como la diferencia de alturas de sus subárboles.
     * @return Entero que representa el factor de equilibrio.
     */
    public int get_bf() { 
    	return this.bf; 
    }
    
    /**
     * Modifica el dato de información del nodo.
     * @param d Nuevo valor a almacenar.
     */
    public void setData(E d) { 
    	this.data = d; 
    }
    
    /**
     * Establece el nodo hijo izquierdo.
     * @param left Nodo hijo izquierdo.
     */
    public void setLeft(Node<E> left) { 
    	this.left = left; 
    }
    
    /**
     * Establece el nodo hijo derecho.
     * @param right Nodo hijo derecho.
     */
    public void setRight(Node<E> right) { 
    	this.right = right; 
    }
    
    /**
     * Actualiza el factor de equilibrio del nodo (bf).
     * @param b Nuevo factor de equilibrio.
     */
    public void set_bf(int b) { 
    	this.bf = b; 
    }
}
