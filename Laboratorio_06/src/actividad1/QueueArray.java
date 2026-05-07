package actividad1;

/**
 * Implementación de una Cola (Queue) utilizando un Arreglo Circular.
 * Esto permite reutilizar los espacios vacíos que van quedando al inicio 
 * del arreglo a medida que se extraen elementos, evitando tener que moverlos.
 *
 * @param <E> El tipo de elemento que almacenará la cola.
 */
public class QueueArray<E> implements Queue<E> {
    private E[] array;     // Arreglo genérico donde se guardan los elementos
    private int front;     // Índice que apunta al primer elemento de la cola
    private int rear;      // Índice que apunta al último elemento insertado
    private int size;      // Cantidad actual de elementos en la cola
    private int capacidad; // Capacidad máxima definida para el arreglo

    /**
     * Constructor que inicializa la cola con una capacidad específica.
     * @param n Capacidad máxima de la cola.
     */
    @SuppressWarnings("unchecked")
	public QueueArray(int n) {
    	capacidad = n;
        // En Java no se pueden instanciar arreglos de tipos genéricos directamente.
        // Se crea un arreglo de Object y se castea al tipo <E>.
        array = (E[]) new Object[n];
        front = 0;
        rear  = -1; // Comienza en -1 porque el primer enqueue lo moverá a 0
        size  = 0;
    }

    /**
     * Agrega un nuevo elemento al final de la cola.
     * @param x Elemento a encolar.
     */
    @Override
    public void enqueue(E x) {
        // Bloque 1: Verificación de capacidad
        if (isFull()) {
            System.out.println("Cola llena");
            return;
        }
        // Bloque 2: Avance circular de 'rear'
        // El operador módulo (%) asegura que si rear + 1 es igual a la capacidad,
        // el índice vuelva a 0, creando el efecto circular.
        rear = (rear + 1) % capacidad;
        array[rear] = x;
        size++; // Aumenta el contador de elementos
    }

    /**
     * Extrae y devuelve el primer elemento de la cola.
     * @return El elemento extraído.
     * @throws ExceptionIsEmpty Si la cola está vacía.
     */
    @Override
    public E dequeue() throws ExceptionIsEmpty {
        // Bloque 1: Verificación de estado
        if (isEmpty())
            throw new ExceptionIsEmpty("Cola vacía");
        
        // Bloque 2: Extracción del elemento
        E element = array[front]; // Obtenemos el elemento apuntado por 'front'
        
        // Bloque 3: Avance circular de 'front'
        front = (front + 1) % capacidad; 
        size--; // Disminuimos el contador
        
        return element;
    }

    /**
     * Devuelve el primer elemento de la cola sin extraerlo.
     * @return El elemento en la posición 'front'.
     * @throws ExceptionIsEmpty Si la cola está vacía.
     */
    @Override
    public E front() throws ExceptionIsEmpty {
        if (isEmpty())
            throw new ExceptionIsEmpty("Cola vacía");
        return array[front];
    }

    /**
     * Verifica si la cola está vacía.
     * @return true si el tamaño es 0, false de lo contrario.
     */
    @Override
    public boolean isEmpty() {
        return size == 0;
    }

    /**
     * Verifica si la cola está llena.
     * @return true si el tamaño es igual a la capacidad máxima.
     */
    public boolean isFull() {
        return size == capacidad;
    }

    /**
     * Representación en formato String de la cola, respetando el orden circular.
     */
    @Override
    public String toString() {
        if (isEmpty()) return "[]";
        StringBuilder sb = new StringBuilder("[");
        // Recorremos la cantidad de elementos ('size') y calculamos su índice circular
        for (int i = 0; i < size; i++) {
            sb.append(array[(front + i) % capacidad]);
            if (i < size - 1) sb.append(", ");
        }
        sb.append("]");
        return sb.toString();
    }

    // Métodos getter públicos para posibilitar la representación gráfica en la GUI
    public E[] getArray() { return array; }
    public int getFrontIndex() { return front; }
    public int getRearIndex() { return rear; }
    public int getSize() { return size; }
    public int getCapacidad() { return capacidad; }
}

