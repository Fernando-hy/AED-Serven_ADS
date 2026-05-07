package actividadEjercicio4;

/**
 * Clase de soporte para almacenar un elemento y su valor/prioridad
 * para ser utilizado en colas ordenadas.
 *
 * @param <E> El tipo de dato almacenado.
 * @param <V> El tipo de valor (para usar en comparaciones).
 */
public class Entry<E, V> {
    E data;   // El dato
    V value;  // El valor de ordenamiento

    /** Constructor para inicializar una entrada con su valor. */
    public Entry(E data, V value) {
        this.data  = data;
        this.value = value;
    }

    /** Formato de cadena de la entrada. */
    @Override
    public String toString() {
        return "(" + data + ", " + value + ")";
    }

    // Métodos getter públicos para la GUI
    public E getData() { return data; }
    public V getValue() { return value; }
}