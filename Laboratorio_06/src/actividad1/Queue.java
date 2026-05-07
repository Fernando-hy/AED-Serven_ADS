package actividad1;

/**
 * Interfaz genérica que define las operaciones fundamentales de una Cola (Queue).
 * Una cola sigue el principio FIFO (First In, First Out).
 *
 * @param <E> El tipo de elementos que almacenará la cola.
 */
public interface Queue<E> {
    /**
     * Inserta un elemento al final de la cola.
     * @param x El elemento a encolar.
     */
    void enqueue(E x);

    /**
     * Extrae y devuelve el elemento al frente de la cola.
     * @return El elemento extraído.
     * @throws ExceptionIsEmpty Si la cola está vacía.
     */
    E dequeue() throws ExceptionIsEmpty;

    /**
     * Obtiene el elemento al frente de la cola sin extraerlo.
     * @return El elemento en el frente.
     * @throws ExceptionIsEmpty Si la cola está vacía.
     */
    E front() throws ExceptionIsEmpty;

    /**
     * Verifica si la cola no contiene elementos.
     * @return true si la cola está vacía, false de lo contrario.
     */
    boolean isEmpty();
}