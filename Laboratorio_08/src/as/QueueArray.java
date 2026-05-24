package as;

/**
 * Implementación de una Cola utilizando un arreglo circular estático.
 * Optimiza las operaciones de inserción (enqueue) y eliminación (dequeue) a O(1).
 * 
 * @param <E> El tipo de elemento almacenado en la cola.
 */
public class QueueArray<E> implements Queue<E> {
    private E[] array;       // Arreglo para almacenar los elementos de la cola.
    private int front;       // Índice del elemento al frente de la cola.
    private int rear;        // Índice del último elemento insertado.
    private int size;        // Cantidad de elementos actualmente almacenados.
    private int capacidad;   // Capacidad máxima de la cola.

    /**
     * Constructor que inicializa la cola con una capacidad máxima definida.
     * @param n Capacidad máxima de la cola.
     */
    @SuppressWarnings("unchecked")
    public QueueArray(int n) {
        capacidad = n;
        array = (E[]) new Object[n];
        front = 0;
        rear  = -1;
        size  = 0;
    }

    /**
     * Encola un elemento al final de la cola (comportamiento circular).
     * @param x El elemento a insertar.
     */
    @Override
    public void enqueue(E x) {
        if (isFull()) {
            System.out.println("Cola llena");
            return;
        }
        // rear avanza circularmente usando el operador módulo (%)
        rear = (rear + 1) % capacidad;
        array[rear] = x;
        size++;
    }

    /**
     * Desencola el elemento en el frente de la cola (comportamiento circular).
     * @return El elemento removido.
     * @throws ExceptionIsEmpty Si la cola está vacía.
     */
    @Override
    public E dequeue() throws ExceptionIsEmpty {
        if (isEmpty())
            throw new ExceptionIsEmpty("Cola vacía");
        E element = array[front];
        // front avanza circularmente usando el operador módulo (%)
        front = (front + 1) % capacidad;
        size--;
        return element;
    }

    /**
     * Obtiene el elemento en el frente sin removerlo.
     * @return El elemento al frente.
     * @throws ExceptionIsEmpty Si la cola está vacía.
     */
    @Override
    public E front() throws ExceptionIsEmpty {
        if (isEmpty())
            throw new ExceptionIsEmpty("Cola vacía");
        return array[front];
    }

    /**
     * Comprueba si la cola está vacía.
     * @return true si el tamaño es 0.
     */
    @Override
    public boolean isEmpty() {
        return size == 0;
    }

    /**
     * Comprueba si la cola alcanzó su límite de capacidad.
     * @return true si el tamaño actual es igual a la capacidad.
     */
    public boolean isFull() {
        return size == capacidad;
    }

    /**
     * Representación en String para visualización fácil de la cola.
     * Muestra los elementos desde el frente hasta el final.
     */
    @Override
    public String toString() {
        if (isEmpty()) return "[]";
        StringBuilder sb = new StringBuilder("[");
        for (int i = 0; i < size; i++) {
            sb.append(array[(front + i) % capacidad]);
            if (i < size - 1) sb.append(", ");
        }
        sb.append("]");
        return sb.toString();
    }
}
