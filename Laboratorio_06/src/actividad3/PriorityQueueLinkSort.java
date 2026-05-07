package actividad3;

import actividad1.ExceptionIsEmpty;

/**
 * Implementación de una Cola de Prioridad utilizando una lista enlazada simple.
 * Esta implementación inserta los elementos ya ORDENADOS de mayor a menor prioridad,
 * lo que permite que las extracciones (dequeue) sean súper rápidas (O(1)).
 *
 * @param <E> El tipo de dato a almacenar.
 * @param <N> El tipo de dato para la prioridad, el cual debe ser Comparable (ej. Integer, String).
 */
public class PriorityQueueLinkSort<E, N extends Comparable<N>> implements PriorityQueue<E, N> {

    /**
     * Clase interna que actúa como envoltorio (wrapper) para almacenar
     * tanto el dato de la cola como su prioridad correspondiente en un solo objeto.
     */
    public class EntryNode {
        public E data;       // El valor real almacenado en la cola
        public N priority;   // El valor que define la prioridad

        EntryNode(E data, N priority) {
            this.data     = data;
            this.priority = priority;
        }

        @Override
        public String toString() {
            return "(" + data + ", p=" + priority + ")";
        }
    }

    // Punteros al inicio y fin de la lista enlazada
    private Node<EntryNode> first;
    private Node<EntryNode> last;

    public PriorityQueueLinkSort() {
        this.first = null;
        this.last  = null;
    }

    /**
     * Inserta un elemento en la posición correcta según su prioridad.
     * Mayor prioridad se coloca más cerca del inicio de la cola.
     * @param x El dato a insertar.
     * @param pr La prioridad asignada al dato.
     */
    public void enqueue(E x, N pr) {
        // Envolvemos el dato y la prioridad en un EntryNode, y creamos el nodo de la lista
        Node<EntryNode> newNode = new Node<>(new EntryNode(x, pr));

        // Bloque 1: Si la lista está vacía, el nuevo nodo es el único elemento
        if (isEmpty()) {
            first = newNode;
            last  = newNode;
            return;
        }

        // Bloque 2: La nueva prioridad es mayor que la prioridad del primer nodo actual.
        // Se debe insertar en la primera posición para ser el primero en salir.
        // compareTo > 0 significa que 'pr' es mayor que la prioridad del nodo actual.
        if (pr.compareTo(first.getData().priority) > 0) {
            newNode.setNext(first);
            first = newNode;
            return;
        }

        // Bloque 3: Buscar la posición correcta en el medio o final.
        // Avanzamos por la lista hasta encontrar un nodo cuya prioridad sea MENOR
        // a la nueva prioridad, para insertar el nuevo nodo justo ANTES de él.
        Node<EntryNode> actual = first;
        while (actual.getNext() != null &&
               actual.getNext().getData().priority.compareTo(pr) >= 0) {
            actual = actual.getNext(); // Seguimos avanzando mientras tengan mayor o igual prioridad
        }
        
        // Enlazamos el nuevo nodo con el resto de la cola
        newNode.setNext(actual.getNext());
        // El nodo actual ahora apuntará a nuestro nuevo nodo
        actual.setNext(newNode);

        // Bloque 4: Si se insertó al puro final de la lista, actualizamos el puntero 'last'
        if (newNode.getNext() == null)
            last = newNode;
    }

    /**
     * Extrae el elemento con mayor prioridad (que siempre estará al inicio).
     * @return El dato extraído.
     * @throws ExceptionIsEmpty Si la cola está vacía.
     */
    public E dequeue() throws ExceptionIsEmpty {
        if (isEmpty())
            throw new ExceptionIsEmpty("Cola de prioridad vacía");
            
        // Bloque 1: Obtenemos solo el dato (E), ignorando la prioridad (N) para el retorno
        E aux = this.first.getData().data;
        
        // Bloque 2: El nuevo inicio es el siguiente nodo
        this.first = this.first.getNext();
        
        // Si al eliminar quedó vacía, limpiamos también 'last'
        if (this.first == null) this.last = null;
        
        return aux;
    }

    /**
     * Obtiene el dato con MAYOR prioridad sin extraerlo.
     */
    public E front() throws ExceptionIsEmpty {
        if (isEmpty())
            throw new ExceptionIsEmpty("Cola de prioridad vacía");
        return first.getData().data;
    }

    /**
     * Obtiene el dato con MENOR prioridad sin extraerlo.
     */
    public E back() throws ExceptionIsEmpty {
        if (isEmpty())
            throw new ExceptionIsEmpty("Cola de prioridad vacía");
        return last.getData().data;
    }

    /**
     * Verifica si la cola de prioridad está vacía.
     */
    public boolean isEmpty() {
        return first == null;
    }

    /**
     * Representación en formato texto mostrando la cola y las prioridades en orden.
     */
    @Override
    public String toString() {
        if (isEmpty()) return "[]";
        StringBuilder sb = new StringBuilder("[");
        Node<EntryNode> actual = first;
        while (actual != null) {
            sb.append(actual.getData());
            if (actual.getNext() != null) sb.append(" -> ");
            actual = actual.getNext();
        }
        sb.append("]");
        return sb.toString();
    }

    // Métodos getter para la representación gráfica en la GUI
    public Node<EntryNode> getFirstNode() { return first; }
    public Node<EntryNode> getLastNode() { return last; }
}
