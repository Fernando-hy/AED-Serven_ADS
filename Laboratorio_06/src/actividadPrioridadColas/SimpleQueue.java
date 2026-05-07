package actividadPrioridadColas;

import actividad1.ExceptionIsEmpty;

/**
 * Cola simple basada en lista enlazada, utilizada internamente por PrioridadColas.
 * Inserta por el final y extrae por el frente.
 *
 * @param <E> Tipo de elemento a almacenar.
 */
public class SimpleQueue<E> {
    private Node<E> front; // Primer nodo
    private Node<E> rear;  // Último nodo

    public SimpleQueue() {
        front = null;
        rear  = null;
    }

    /**
     * Inserta al final de la cola.
     * @param x Elemento a insertar.
     */
    public void enqueue(E x) {
        Node<E> newNode = new Node<>(x);
        // Bloque 1: Si está vacía, es frente y fin
        if (isEmpty()) {
            front = newNode;
            rear  = newNode;
        } else {
            // Bloque 2: Se enlaza al final actual y actualiza el fin
            rear.setNext(newNode);
            rear = newNode;
        }
    }

    /**
     * Extrae el primer elemento.
     * @return Elemento extraído.
     * @throws ExceptionIsEmpty Si está vacía.
     */
    public E dequeue() throws ExceptionIsEmpty {
        if (isEmpty())
            throw new ExceptionIsEmpty("Cola vacía");
            
        // Bloque 1: Extraer cabeza
        E data = front.getData();
        front = front.getNext();
        
        // Si quedó vacía, limpia el puntero rear
        if (front == null) rear = null;
        return data;
    }

    /** Verifica si la cola no tiene elementos. */
    public boolean isEmpty() {
        return front == null;
    }

    /** Formato de cadena. */
    @Override
    public String toString() {
        if (isEmpty()) return "vacía";
        StringBuilder sb = new StringBuilder();
        Node<E> actual = front;
        while (actual != null) {
            sb.append(actual.getData());
            if (actual.getNext() != null) sb.append(" -> ");
            actual = actual.getNext();
        }
        return sb.toString();
    }
}