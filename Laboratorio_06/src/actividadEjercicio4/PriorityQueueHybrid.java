package actividadEjercicio4;

import actividad1.ExceptionIsEmpty;

/**
 * Cola de Prioridad Híbrida que utiliza un arreglo de Colas Ordenadas (SortedQueue).
 * Esta estructura combina el acceso directo a diferentes niveles de prioridad (mediante un arreglo)
 * con el ordenamiento interno basado en un valor secundario para desempatar elementos en el mismo nivel.
 *
 * @param <E> El tipo de dato del elemento.
 * @param <V> El tipo del valor secundario utilizado para el ordenamiento interno.
 */
public class PriorityQueueHybrid<E, V extends Comparable<V>> {

    private SortedQueue<E, V>[] levels; // Arreglo de colas ordenadas por cada nivel de prioridad
    private int numLevels;              // Cantidad total de niveles de prioridad disponibles

    /**
     * Constructor de la cola híbrida.
     * @param numLevels La cantidad de niveles de prioridad que soportará.
     */
    @SuppressWarnings("unchecked")
    public PriorityQueueHybrid(int numLevels) {
        this.numLevels = numLevels;
        levels = new SortedQueue[numLevels];
        
        // Bloque 1: Inicialización
        // Se instancia una nueva SortedQueue vacía para cada nivel de prioridad del arreglo.
        for (int i = 0; i < numLevels; i++)
            levels[i] = new SortedQueue<>();
    }

    /**
     * Inserta un elemento en la cola híbrida.
     * @param data El elemento a guardar.
     * @param priority El nivel principal de prioridad (índice del arreglo).
     * @param value El valor secundario para ordenar dentro de la misma prioridad.
     */
    public void enqueue(E data, int priority, V value) {
        // Bloque 1: Verificación de límite
        if (priority < 0 || priority >= numLevels)
            throw new IllegalArgumentException("Prioridad inválida: " + priority);
            
        // Bloque 2: Inserción
        // Busca la cola correspondiente al nivel de prioridad e inserta el elemento ordenado por su valor.
        levels[priority].enqueue(data, value);
    }

    /**
     * Extrae el elemento con mayor prioridad global.
     * @return El elemento extraído.
     * @throws ExceptionIsEmpty Si todas las colas están vacías.
     */
    public E dequeue() throws ExceptionIsEmpty {
        // Bloque 1: Extracción descendente
        // Recorre los niveles desde el de mayor prioridad (numLevels - 1) hasta el menor (0).
        // Extrae del primer nivel que no esté vacío.
        for (int i = numLevels - 1; i >= 0; i--) {
            if (!levels[i].isEmpty())
                return levels[i].dequeue();
        }
        
        // Si el ciclo termina y no se extrajo nada, es porque todo estaba vacío
        throw new ExceptionIsEmpty("Cola híbrida vacía");
    }

    /**
     * Verifica si la cola híbrida no tiene elementos.
     */
    public boolean isEmpty() {
        for (SortedQueue<E, V> q : levels)
            if (!q.isEmpty()) return false;
        return true;
    }

    /** Representación visual de los niveles. */
    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        for (int i = numLevels - 1; i >= 0; i--) {
            sb.append("Nivel ").append(i)
              .append(": [ ").append(levels[i]).append(" ]\n");
        }
        return sb.toString();
    }

    // Métodos getter para la representación gráfica en la GUI
    public SortedQueue<E, V>[] getLevels() { return levels; }
    public int getNumLevels() { return numLevels; }
}