package actividadPilaLista;

import actividad1.ExceptionIsEmpty;

/**
 * Interfaz que define las operaciones de una Pila (Stack).
 * Una pila sigue el principio LIFO (Last In, First Out).
 *
 * @param <E> El tipo de dato a almacenar.
 */
public interface Stack<E> {
    /** Inserta un elemento en el tope de la pila. */
    void push(E x);
    
    /** Extrae el elemento en el tope de la pila. */
    E pop() throws ExceptionIsEmpty;
    
    /** Obtiene el elemento en el tope sin extraerlo. */
    E top() throws ExceptionIsEmpty;
    
    /** Verifica si la pila está vacía. */
    boolean isEmpty();
}