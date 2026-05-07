package actividadPilaLista;

import actividad1.ExceptionIsEmpty;

/**
 * Implementación de una Pila utilizando una lista simplemente enlazada.
 * Todas las inserciones (push) y extracciones (pop) ocurren en O(1) modificando la cabeza (top).
 *
 * @param <E> Tipo de elemento almacenado.
 */
public class StackLink<E> implements Stack<E> {
    private Node<E> top; // Puntero al elemento superior de la pila

    public StackLink() {
        this.top = null;
    }

    /**
     * Apila un nuevo elemento.
     * @param x Elemento a insertar.
     */
    public void push(E x) {
        Node<E> newNode = new Node<>(x);
        
        // Bloque 1: Inserción en cabeza
        // El nuevo nodo apunta al antiguo tope, y luego asume el título de tope.
        newNode.setNext(top); // apunta al anterior tope
        top = newNode;        // el nuevo nodo es el tope
    }

    /**
     * Desapila y devuelve el elemento superior.
     * @return El elemento extraído.
     * @throws ExceptionIsEmpty Si la pila está vacía.
     */
    public E pop() throws ExceptionIsEmpty {
        if (isEmpty())
            throw new ExceptionIsEmpty("Pila vacía");
            
        // Bloque 1: Extracción de cabeza
        E data = top.getData();
        top = top.getNext(); // el tope baja un nivel al siguiente nodo
        return data;
    }

    /**
     * Observa el tope de la pila.
     */
    public E top() throws ExceptionIsEmpty {
        if (isEmpty())
            throw new ExceptionIsEmpty("Pila vacía");
        return top.getData();
    }

    /** Verifica si está vacía. */
    public boolean isEmpty() {
        return top == null;
    }

    /** Muestra el contenido desde el tope hacia abajo. */
    @Override
    public String toString() {
        if (isEmpty()) return "[]";
        StringBuilder sb = new StringBuilder("tope -> [");
        Node<E> actual = top;
        while (actual != null) {
            sb.append(actual.getData());
            if (actual.getNext() != null) sb.append(", ");
            actual = actual.getNext();
        }
        sb.append("]");
        return sb.toString();
    }
}