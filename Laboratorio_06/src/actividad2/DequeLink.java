package actividad2;

import actividad1.ExceptionIsEmpty;

/**
 * Implementación de una Cola Doble (Deque) utilizando una lista simplemente enlazada.
 * Un Deque permite inserciones y eliminaciones tanto por el frente como por el final.
 *
 * @param <E> El tipo de elemento que almacenará el Deque.
 */
public class DequeLink<E> implements Deque<E> {
    private Node<E> first; // Puntero al primer nodo de la lista
    private Node<E> last;  // Puntero al último nodo de la lista

    /**
     * Constructor por defecto. Inicializa los punteros en null.
     */
    public DequeLink() {
        first = null;
        last  = null;
    }

    /**
     * Inserta un elemento al inicio del Deque.
     * @param x Elemento a insertar.
     */
    public void addFirst(E x) {
        Node<E> newNode = new Node<>(x);
        
        // Bloque 1: Si está vacío, el nuevo nodo es el primero y el último a la vez
        if (isEmpty()) {
            first = newNode;
            last  = newNode;
        } else {
            // Bloque 2: El nuevo nodo apunta al que antes era el primero,
            // y luego actualizamos el puntero 'first'.
            newNode.setNext(first);
            first = newNode;
        }
    }

    /**
     * Inserta un elemento al final del Deque.
     * @param x Elemento a insertar.
     */
    public void addLast(E x) {
        Node<E> newNode = new Node<>(x);
        
        // Bloque 1: Si está vacío, ambos punteros apuntan al nuevo nodo
        if (isEmpty()) {
            first = newNode;
            last  = newNode;
        } else {
            // Bloque 2: Enlazamos el último nodo actual con el nuevo,
            // y luego actualizamos el puntero 'last' al nuevo nodo.
            last.setNext(newNode);
            last = newNode;
        }
    }

    /**
     * Extrae y retorna el elemento al inicio del Deque.
     * @return El primer elemento.
     * @throws ExceptionIsEmpty Si el Deque está vacío.
     */
    public E removeFirst() throws ExceptionIsEmpty {
        if (isEmpty())
            throw new ExceptionIsEmpty("Deque vacío");
            
        // Bloque 1: Guardamos el dato antes de eliminar el nodo
        E data = first.getData();
        
        // Bloque 2: Avanzamos 'first' al siguiente nodo
        first = first.getNext();
        
        // Bloque 3: Si al eliminar el nodo la lista quedó vacía, 
        // también actualizamos 'last' a null
        if (first == null) last = null; 
        
        return data;
    }

    /**
     * Extrae y retorna el elemento al final del Deque.
     * Nota: Al ser una lista simplemente enlazada, eliminar al final toma un tiempo O(n)
     * porque debemos recorrer la lista hasta el penúltimo nodo.
     * @return El último elemento.
     * @throws ExceptionIsEmpty Si el Deque está vacío.
     */
    public E removeLast() throws ExceptionIsEmpty {
        if (isEmpty())
            throw new ExceptionIsEmpty("Deque vacío");
            
        E data = last.getData();
        
        // Bloque 1: Si solo hay un nodo en toda la lista
        if (first == last) {
            first = null;
            last  = null;
        } else {
            // Bloque 2: Hay más de un nodo. Recorremos hasta encontrar el PENÚLTIMO
            Node<E> actual = first;
            while (actual.getNext() != last) {
                actual = actual.getNext();
            }
            // Desenlazamos el último nodo y actualizamos 'last'
            actual.setNext(null);
            last = actual;
        }
        return data;
    }

    /**
     * Obtiene el elemento al inicio sin extraerlo.
     */
    public E getFirst() throws ExceptionIsEmpty {
        if (isEmpty())
            throw new ExceptionIsEmpty("Deque vacío");
        return first.getData();
    }

    /**
     * Obtiene el elemento al final sin extraerlo.
     */
    public E getLast() throws ExceptionIsEmpty {
        if (isEmpty())
            throw new ExceptionIsEmpty("Deque vacío");
        return last.getData();
    }

    /**
     * Verifica si el Deque está vacío.
     */
    public boolean isEmpty() {
        return first == null; // Si no hay primero, está vacío
    }

    /**
     * Representación textual del Deque mostrando los enlaces.
     */
    @Override
    public String toString() {
        if (isEmpty()) return "[]";
        StringBuilder sb = new StringBuilder("[");
        Node<E> actual = first;
        while (actual != null) {
            sb.append(actual.getData());
            if (actual.getNext() != null) sb.append(" <-> ");
            actual = actual.getNext();
        }
        sb.append("]");
        return sb.toString();
    }

    // Métodos getter para la representación gráfica en la GUI
    public Node<E> getFirstNode() { return first; }
    public Node<E> getLastNode() { return last; }
}
