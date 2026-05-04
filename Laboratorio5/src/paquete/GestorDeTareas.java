package paquete;

/**
 * Clase que gestiona una colección de tareas.
 * Actúa como una capa intermedia entre el usuario y la estructura ListLinked.
 */
public class GestorDeTareas<T extends Comparable<T>> {
    // Usamos nuestra lista enlazada simple como almacenamiento interno
    private ListLinked<T> listaEnlazada;

    /**
     * Constructor: Inicializa el gestor con una lista vacía.
     */
    public GestorDeTareas() {
        this.listaEnlazada = new ListLinked<>();
    }

    /**
     * Añade una nueva tarea al final de la lista.
     * @param tarea El objeto tarea a guardar.
     */
    public void agregarTarea(T tarea) {
    	listaEnlazada.insertLast(tarea); // Delegamos la tarea a la lista enlazada
    }

    /**
     * Elimina una tarea específica.
     * @param tarea La tarea que queremos quitar.
     * @return true si se eliminó, false si no existía.
     */
    public boolean eliminarTarea(T tarea) {
        return listaEnlazada.removeNode(tarea); // Delegamos la eliminación
    }

    /**
     * Verifica si una tarea está en el gestor.
     */
    public boolean contieneTarea(T tarea) {
        return listaEnlazada.Search(tarea);
    }

    /**
     * Muestra todas las tareas en pantalla.
     */
    public void imprimirTareas() {
    	listaEnlazada.print();
    }

    /**
     * Devuelve el número total de tareas.
     */
    public int contarTareas() {
        return listaEnlazada.length();
    }

    /**
     * Algoritmo de búsqueda: Encuentra la tarea con la mayor prioridad.
     * @return La tarea más importante (el valor menor según compareTo).
     */
    public T obtenerTareaMasPrioritaria() {
        if (listaEnlazada.isEmptyList()) {
            return null; // Si no hay tareas, no hay prioridad que buscar
        }

        ListLinked.Node<T> actual = listaEnlazada.getFirst();
        T masPrioritaria = actual.getValue(); // Suponemos que la primera es la mejor

        // Recorremos toda la lista buscando una prioridad menor (más importante)
        while (actual != null) {
            // Si el valor actual es menor (< 0) que el que teníamos guardado...
            if (actual.getValue().compareTo(masPrioritaria) < 0) {
                masPrioritaria = actual.getValue(); // ... actualizamos nuestro récord
            }
            actual = actual.getNext(); // Pasamos al siguiente
        }
        return masPrioritaria;
    }
    
    /**
     * Invierte el orden de todas las tareas.
     */
    public void invertirTareas() {
    	listaEnlazada.reverse();
    }
}