package actividadPrioridadColas;

import actividad1.ExceptionIsEmpty;

/**
 * Implementación de una Cola de Prioridad utilizando un arreglo de Colas Simples.
 * A diferencia del híbrido que ordena internamente, este modelo solo segrega
 * elementos por nivel y extrae siguiendo el orden FIFO original de cada nivel.
 *
 * @param <E> Tipo de elemento a almacenar.
 */
public class PrioridadColas<E> {

    private SimpleQueue<E>[] queues; // Arreglo que contiene una cola por cada prioridad
    private int levels;              // Número total de niveles soportados

    /**
     * Inicializa la estructura con una cantidad específica de niveles.
     * @param levels Cantidad de niveles (0 al levels-1).
     */
    @SuppressWarnings("unchecked")
    public PrioridadColas(int levels) {
        this.levels = levels;
        queues = new SimpleQueue[levels];
        
        // Instancia cada cola en su respectivo nivel
        for (int i = 0; i < levels; i++)
            queues[i] = new SimpleQueue<>();
    }

    /**
     * Inserta un elemento directamente en la cola de su nivel de prioridad.
     * @param x Elemento a almacenar.
     * @param priority Nivel en el arreglo.
     */
    public void enqueue(E x, int priority) {
        if (priority < 0 || priority >= levels)
            throw new IllegalArgumentException("Prioridad inválida: " + priority);
        // Inserta en la SimpleQueue correspondiente
        queues[priority].enqueue(x);
    }

    /**
     * Extrae el elemento del nivel más alto que tenga elementos.
     * @return El elemento de mayor prioridad.
     * @throws ExceptionIsEmpty Si todas las colas están vacías.
     */
    public E dequeue() throws ExceptionIsEmpty {
        // Bloque 1: Recorre el arreglo en sentido descendente
        // La mayor prioridad es levels - 1
        for (int i = levels - 1; i >= 0; i--) {
            if (!queues[i].isEmpty())
                return queues[i].dequeue();
        }
        throw new ExceptionIsEmpty("Cola de prioridad vacía");
    }

    /** Verifica si no hay elementos en absoluto. */
    public boolean isEmpty() {
        for (SimpleQueue<E> q : queues)
            if (!q.isEmpty()) return false;
        return true;
    }

    /** Representación visual. */
    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        for (int i = levels - 1; i >= 0; i--) {
            sb.append("Nivel ").append(i)
              .append(": [ ").append(queues[i]).append(" ]\n");
        }
        return sb.toString();
    }
}