package graph;

import java.util.ArrayList;

// Interfaz que define las operaciones básicas de un grafo.
// V = tipo de los vértices, E = tipo de las aristas
public interface Graph<V, E> {

    // agrega un vértice al grafo
    void insertVertex(V data);

    // agrega una arista entre dos vértices
    void insertEdge(V origin, V destination);

    // elimina un vértice y todas sus aristas
    void removeVertex(V data);

    // elimina la arista entre dos vértices
    void removeEdge(V origin, V destination);

    // retorna true si el vértice existe
    boolean searchVertex(V data);

    // retorna true si existe una arista entre dos vértices
    boolean searchEdge(V origin, V destination);

    // retorna los vértices adyacentes a un vértice
    ArrayList<V> adjacentVertices(V data);
}