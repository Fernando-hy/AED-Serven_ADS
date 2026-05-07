package actividad2;
import actividad1.ExceptionIsEmpty;

/**
 * Interfaz genérica que define las operaciones de una Cola Doblemente Terminada (Deque).
 * Un Deque permite inserciones y eliminaciones tanto al principio como al final.
 *
 * @param <E> Tipo de elemento a almacenar.
 */
public interface Deque<E> {
    /** Inserta un elemento al inicio del Deque. */
    void addFirst(E x);
    
    /** Inserta un elemento al final del Deque. */
    void addLast(E x);
    
    /** Extrae el primer elemento. */
    E removeFirst() throws ExceptionIsEmpty;
    
    /** Extrae el último elemento. */
    E removeLast() throws ExceptionIsEmpty;
    
    /** Obtiene el primer elemento sin extraerlo. */
    E getFirst() throws ExceptionIsEmpty;
    
    /** Obtiene el último elemento sin extraerlo. */
    E getLast() throws ExceptionIsEmpty;
    
    /** Verifica si el Deque está vacío. */
    boolean isEmpty();
}
