package asd;
import as.ExceptionIsEmpty;

/**
 * Interfaz genérica para la estructura de datos Deque (Double Ended Queue o Cola Doblemente Terminada).
 * Permite inserciones y eliminaciones eficientes tanto por el frente (first) como por el final (last).
 * 
 * @param <E> El tipo de elemento almacenado en el Deque.
 */
public interface Deque<E> {
    
    /**
     * Inserta un elemento al inicio del Deque.
     * @param x El elemento a insertar.
     */
    void addFirst(E x);
    
    /**
     * Inserta un elemento al final del Deque.
     * @param x El elemento a insertar.
     */
    void addLast(E x);
    
    /**
     * Remueve y retorna el primer elemento del Deque.
     * @return El elemento removido.
     * @throws ExceptionIsEmpty Si el Deque está vacío.
     */
    E removeFirst() throws ExceptionIsEmpty;
    
    /**
     * Remueve y retorna el último elemento del Deque.
     * @return El elemento removido.
     * @throws ExceptionIsEmpty Si el Deque está vacío.
     */
    E removeLast() throws ExceptionIsEmpty;
    
    /**
     * Retorna el primer elemento sin removerlo.
     * @return El primer elemento del Deque.
     * @throws ExceptionIsEmpty Si el Deque está vacío.
     */
    E getFirst() throws ExceptionIsEmpty;
    
    /**
     * Retorna el último elemento sin removerlo.
     * @return El último elemento del Deque.
     * @throws ExceptionIsEmpty Si el Deque está vacío.
     */
    E getLast() throws ExceptionIsEmpty;
    
    /**
     * Verifica si el Deque no contiene elementos.
     * @return true si está vacío, false de lo contrario.
     */
    boolean isEmpty();
}
