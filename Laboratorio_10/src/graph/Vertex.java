package graph;

// Representa un nodo/vértice del grafo.
// E es el tipo de dato que almacena (String, Integer, etc.)
public class Vertex<E> {

    private E data; // valor que guarda el vértice

    public Vertex(E data) {
        this.data = data;
    }

    public E getData() { return data; }

    public void setData(E data) { this.data = data; }

    // Útil para imprimir el grafo: muestra el valor del vértice
    @Override
    public String toString() { return data.toString(); }
}