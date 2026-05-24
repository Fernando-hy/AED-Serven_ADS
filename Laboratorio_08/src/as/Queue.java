package as;

/**
 * Interfaz genérica para la estructura de datos Cola (Queue).
 * Sigue el principio FIFO (First-In, First-Out): el primer elemento en entrar es el primero en salir.
 * 
 * @param <E> El tipo de elemento almacenado en la cola.
 */
public interface Queue<E> {
    
    /**
     * Inserta un elemento al final de la cola.
     * @param x El elemento a encolar.
     */
    void enqueue(E x);
    
    /**
     * Remueve y retorna el elemento en el frente de la cola.
     * @return El elemento removido.
     * @throws ExceptionIsEmpty Si la cola está vacía.
     */
    E dequeue() throws ExceptionIsEmpty;
    
    /**
     * Retorna el elemento en el frente de la cola sin removerlo.
     * @return El elemento en el frente de la cola.
     * @throws ExceptionIsEmpty Si la cola está vacía.
     */
    E front() throws ExceptionIsEmpty;
    
    /**
     * Verifica si la cola no contiene elementos.
     * @return true si la cola está vacía, false en caso contrario.
     */
    boolean isEmpty();
}