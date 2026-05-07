package actividad3;

import actividad1.ExceptionIsEmpty;

/**
 * Interfaz que define las operaciones básicas de una Cola de Prioridad.
 *
 * @param <E> El tipo del elemento a almacenar.
 * @param <N> El tipo de la prioridad (debe ser comparable).
 */
public interface PriorityQueue<E, N> {
    /** Inserta un elemento con una prioridad específica. */
    void enqueue(E x, N pr);
    
    /** Extrae el elemento con la prioridad más alta. */
    E dequeue() throws ExceptionIsEmpty;
    
    /** Observa el elemento con la prioridad más alta sin extraerlo. */
    E front() throws ExceptionIsEmpty;
    
    /** Observa el elemento con la prioridad más baja sin extraerlo. */
    E back() throws ExceptionIsEmpty;
    
    /** Retorna verdadero si la cola está vacía. */
    boolean isEmpty();
}