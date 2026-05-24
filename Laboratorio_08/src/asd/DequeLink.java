package asd;

import as.ExceptionIsEmpty;

/**
 * Implementación de Deque basada en una lista enlazada simple.
 * Permite inserciones en el frente y final, y eliminaciones en O(1) para el frente
 * y O(n) para el final (debido al recorrido necesario en una lista simplemente enlazada).
 * 
 * @param <E> El tipo de elemento almacenado en el Deque.
 */
public class DequeLink<E> implements Deque<E> {
    private Node<E> first; // Puntero al primer nodo del Deque.
    private Node<E> last;  // Puntero al último nodo del Deque.

    /**
     * Constructor por defecto que inicializa el Deque como vacío.
     */
    public DequeLink() {
        first = null;
        last  = null;
    }

    /**
     * Inserta un elemento al inicio de la lista enlazada (addFirst) - Complejidad O(1).
     * @param x El elemento a insertar.
     */
    public void addFirst(E x) {
        Node<E> newNode = new Node<>(x);
        if (isEmpty()) {
            first = newNode;
            last  = newNode;
        } else {
            newNode.setNext(first);
            first = newNode;
        }
    }

    /**
     * Inserta un elemento al final de la lista enlazada (addLast) - Complejidad O(1).
     * @param x El elemento a insertar.
     */
    public void addLast(E x) {
        Node<E> newNode = new Node<>(x);
        if (isEmpty()) {
            first = newNode;
            last  = newNode;
        } else {
            last.setNext(newNode);
            last = newNode;
        }
    }

    /**
     * Remueve y retorna el primer elemento de la lista (removeFirst) - Complejidad O(1).
     * @return El elemento removido.
     * @throws ExceptionIsEmpty Si el Deque se encuentra vacío.
     */
    public E removeFirst() throws ExceptionIsEmpty {
        if (isEmpty())
            throw new ExceptionIsEmpty("Deque vacío");
        E data = first.getData();
        first = first.getNext();
        if (first == null) last = null; // Si quedó vacío, last también debe ser null
        return data;
    }

    /**
     * Remueve y retorna el último elemento de la lista (removeLast) - Complejidad O(n) en lista simple.
     * Requiere recorrer la lista para encontrar el penúltimo nodo y actualizar el puntero.
     * @return El elemento removido.
     * @throws ExceptionIsEmpty Si el Deque se encuentra vacío.
     */
    public E removeLast() throws ExceptionIsEmpty {
        if (isEmpty())
            throw new ExceptionIsEmpty("Deque vacío");
        E data = last.getData();
        if (first == last) {
            // Si solo había un nodo en el Deque, se vacía por completo
            first = null;
            last  = null;
        } else {
            // Recorrido secuencial hasta encontrar el penúltimo nodo
            Node<E> actual = first;
            while (actual.getNext() != last) {
                actual = actual.getNext();
            }
            actual.setNext(null);
            last = actual;
        }
        return data;
    }

    /**
     * Retorna el primer elemento sin removerlo del Deque.
     * @return Primer elemento.
     * @throws ExceptionIsEmpty Si el Deque está vacío.
     */
    public E getFirst() throws ExceptionIsEmpty {
        if (isEmpty())
            throw new ExceptionIsEmpty("Deque vacío");
        return first.getData();
    }

    /**
     * Retorna el último elemento sin removerlo del Deque.
     * @return Último elemento.
     * @throws ExceptionIsEmpty Si el Deque está vacío.
     */
    public E getLast() throws ExceptionIsEmpty {
        if (isEmpty())
            throw new ExceptionIsEmpty("Deque vacío");
        return last.getData();
    }
    
    /**
     * Retorna el número de elementos actuales en el Deque recorriendo la lista.
     * @return Cantidad de elementos.
     */
    public int size() {
        int count = 0;
        Node<E> actual = first;
        while (actual != null) {
            count++;
            actual = actual.getNext();
        }
        return count;
    }

    /**
     * Comprueba si el Deque está vacío.
     * @return true si el puntero 'first' es nulo.
     */
    public boolean isEmpty() {
        return first == null;
    }

    /**
     * Representación visual en formato de lista enlazada doblemente terminada.
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
}