package actividadEjercicio4;

import actividad1.ExceptionIsEmpty;

/**
 * Cola ordenada que sirve como estructura base para la PriorityQueueHybrid.
 * Mantiene sus elementos ordenados ascendentemente (de menor a mayor valor) al momento de la inserción.
 *
 * @param <E> El tipo de dato a almacenar.
 * @param <V> El tipo de valor usado para ordenar (Comparable).
 */
public class SortedQueue<E, V extends Comparable<V>> {
    private Node<Entry<E, V>> head; // Puntero a la cabeza de la lista

    public SortedQueue() {
        head = null;
    }

    /**
     * Inserta un elemento manteniendo el orden de menor a mayor.
     * @param data El elemento a almacenar.
     * @param value El valor para determinar su posición.
     */
    public void enqueue(E data, V value) {
        Node<Entry<E, V>> newNode = new Node<>(new Entry<>(data, value));

        // Bloque 1: Si la lista está vacía o el nuevo valor es MENOR que la cabeza
        // compareTo < 0 significa que el nuevo valor va antes que el actual head.
        if (head == null || value.compareTo(head.getData().value) < 0) {
            newNode.setNext(head);
            head = newNode;
            return;
        }

        // Bloque 2: Buscar la posición correcta.
        // Avanza mientras el valor del siguiente nodo sea menor o igual al nuevo valor.
        Node<Entry<E, V>> actual = head;
        while (actual.getNext() != null &&
               actual.getNext().getData().value.compareTo(value) <= 0) {
            actual = actual.getNext();
        }
        
        // Inserta en la posición encontrada
        newNode.setNext(actual.getNext());
        actual.setNext(newNode);
    }

    /**
     * Extrae el primer elemento de la cola (el de menor valor).
     * @return El elemento extraído.
     * @throws ExceptionIsEmpty Si la cola está vacía.
     */
    public E dequeue() throws ExceptionIsEmpty {
        if (isEmpty())
            throw new ExceptionIsEmpty("Cola vacía");
            
        E data = head.getData().data;
        head = head.getNext();
        return data;
    }

    /** Verifica si está vacía. */
    public boolean isEmpty() {
        return head == null;
    }

    /** Representación visual. */
    @Override
    public String toString() {
        if (isEmpty()) return "vacía";
        StringBuilder sb = new StringBuilder();
        Node<Entry<E, V>> actual = head;
        while (actual != null) {
            sb.append(actual.getData());
            if (actual.getNext() != null) sb.append(" -> ");
            actual = actual.getNext();
        }
        return sb.toString();
    }

    // Método getter para la representación gráfica en la GUI
    public Node<Entry<E, V>> getHead() { return head; }
}