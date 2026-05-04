package paquete;

/**
 * Clase para una Lista Enlazada que siempre mantiene sus elementos ORDENADOS.
 * Hereda (extends) de ListLinked para usar toda su infraestructura de nodos.
 */
public class SortedListLinked<T extends Comparable<T>> extends ListLinked<T> {
	 
    /**
     * Inserta un elemento en la posición correcta para no romper el orden.
     * @param x El valor que queremos insertar.
     */
    public void insertOrden(T x) {
        // CASO 1: Si la lista está vacía O si el nuevo valor es menor que el primero
        // En ambos casos, el nuevo elemento debe ir al principio de todo.
        if (isEmptyList() || x.compareTo(getFirst().getValue()) <= 0) {
            insertFirst(x); // Usamos el método heredado de ListLinked
            return; // Terminamos la función aquí
        }
 
        // CASO 2: El elemento va en medio o al final
        ListLinked.Node<T> actual = getFirst(); // Empezamos a buscar desde el inicio
        
        // El bucle 'mientras': 
        // 1. Que no sea el último nodo (actual.getNext() != null)
        // 2. Y que el valor que sigue sea menor que el nuestro (compareTo(x) < 0)
        while (actual.getNext() != null && actual.getNext().getValue().compareTo(x) < 0) {
            actual = actual.getNext(); // Seguimos caminando
        }
 
        // Al salir del bucle, 'actual' es el nodo justo ANTES de donde debe ir 'x'.
        ListLinked.Node<T> newNode = new ListLinked.Node<>(x); // Creamos el nodo físicamente
        
        // Hacemos el "puente":
        newNode.setNext(actual.getNext()); // El nuevo nodo se agarra del que seguía de 'actual'
        actual.setNext(newNode);           // El nodo 'actual' se agarra del nuevo nodo
    }
}