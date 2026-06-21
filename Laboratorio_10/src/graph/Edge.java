package graph;

// Representa una arista que apunta a un vértice destino.
// Incluye peso (weight) para grafos ponderados; por defecto es 1.
public class Edge<E> {

    private Vertex<E> destination; // vértice al que apunta esta arista
    private int weight;             // peso (distancia, costo, etc.)

    // Constructor sin peso → grafo no ponderado (weight = 1)
    public Edge(Vertex<E> destination) {
        this(destination, 1);
    }

    // Constructor con peso → grafo ponderado
    public Edge(Vertex<E> destination, int weight) {
        this.destination = destination;
        this.weight = weight;
    }

    public Vertex<E> getDestination() { 
    	return this.destination; 
    	}
    
    public int getWeight()      { 
    	return this.weight; 
    	}
    
    public void setDestination(Vertex<E> destination) { 
    	this.destination = destination; 
    	}
    
    public void setWeight(int weight) { 
    	this.weight = weight; 
    	}

    // Al imprimir una arista muestra el vértice destino
    @Override
    public String toString() { 
    	return destination.toString(); 
    }
}
