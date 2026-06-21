package graph;
import java.util.ArrayList;

import actividad1.ExceptionIsEmpty;
import listlinked.DequeLink;
import listlinked.ListLinked;

// Implementación del grafo usando lista de vértices y lista de aristas.
// Diferencia con GraphLink: las aristas se guardan en una lista separada,
// cada una con referencia a sus dos vértices extremos.
public class GraphListEdge<E> implements Graph<E, Edge<E>> {

    private ListLinked<Vertex<E>> vertices; // lista de vértices
    private ListLinked<EdgeNode<E>> edges;  // lista de aristas

    public GraphListEdge() {
        vertices = new ListLinked<>();
        edges    = new ListLinked<>();
    }

    // nodo interno que representa una arista con sus dos extremos
    public class EdgeNode<E> {
        Vertex<E> origin;
        Vertex<E> destination;

        public EdgeNode(Vertex<E> origin, Vertex<E> destination) {
            this.origin      = origin;
            this.destination = destination;
        }
    }

    // busca un vértice por dato, retorna null si no existe
    private Vertex<E> findVertex(E data) {
        for (int i = 0; i < vertices.size(); i++)
            if (vertices.get(i).getData().equals(data))
                return vertices.get(i);
        return null;
    }

    @Override
    public void insertVertex(E data) {
        if (findVertex(data) != null) return;
        vertices.insertLast(new Vertex<>(data));
    }

    @Override
    public void insertEdge(E origin, E destination) {
        Vertex<E> v1 = findVertex(origin);
        Vertex<E> v2 = findVertex(destination);
        if (v1 == null || v2 == null) return;
        edges.insertLast(new EdgeNode<>(v1, v2));
    }

    @Override
    public void removeVertex(E data) {
        Vertex<E> v = findVertex(data);
        if (v == null) return;

        // eliminamos todas las aristas que contengan este vértice
        for (int i = edges.size() - 1; i >= 0; i--) {
            EdgeNode<E> e = edges.get(i);
            if (e.origin.getData().equals(data) || e.destination.getData().equals(data))
                edges.removeAt(i);
        }

        // eliminamos el vértice
        for (int i = 0; i < vertices.size(); i++) {
            if (vertices.get(i).getData().equals(data)) {
                vertices.removeAt(i);
                break;
            }
        }
    }

    @Override
    public void removeEdge(E origin, E destination) {
        for (int i = 0; i < edges.size(); i++) {
            EdgeNode<E> e = edges.get(i);
            if (e.origin.getData().equals(origin) && e.destination.getData().equals(destination)) {
                edges.removeAt(i);
                return;
            }
        }
    }

    @Override
    public boolean searchVertex(E data) {
        return findVertex(data) != null;
    }

    @Override
    public boolean searchEdge(E origin, E destination) {
        for (int i = 0; i < edges.size(); i++) {
            EdgeNode<E> e = edges.get(i);
            if (e.origin.getData().equals(origin) && e.destination.getData().equals(destination))
                return true;
        }
        return false;
    }

    @Override
    public ArrayList<E> adjacentVertices(E data) {
        ArrayList<E> adjacents = new ArrayList<>();
        for (int i = 0; i < edges.size(); i++) {
            EdgeNode<E> e = edges.get(i);
            if (e.origin.getData().equals(data))
                adjacents.add(e.destination.getData());
        }
        return adjacents;
    }
    
    public boolean isConexo() {
        if (vertices.size() == 0) return true;

        boolean[] visited = new boolean[vertices.size()];
        DequeLink<E> queue = new DequeLink<>();

        try {
            visited[0] = true;
            queue.addLast(vertices.get(0).getData());
            int count = 1;

            while (!queue.isEmpty()) {
                E current = queue.removeFirst();

                // buscamos los vecinos usando la lista de aristas
                for (int i = 0; i < edges.size(); i++) {
                    EdgeNode<E> e = edges.get(i);
                    E neighbor = null;

                    if (e.origin.getData().equals(current))
                        neighbor = e.destination.getData();
                    else if (e.destination.getData().equals(current))
                        neighbor = e.origin.getData();

                    if (neighbor != null) {
                        // buscamos el índice del vecino
                        for (int j = 0; j < vertices.size(); j++) {
                            if (!visited[j] && vertices.get(j).getData().equals(neighbor)) {
                                visited[j] = true;
                                count++;
                                queue.addLast(neighbor);
                            }
                        }
                    }
                }
            }

            return count == vertices.size();

        } catch (ExceptionIsEmpty e) {
            e.printStackTrace();
            return false;
        }
    }
    
	 // retorna true si este grafo es isomorfo con respecto a otro.
	 // osea: mismo nº de vértices, mismo nº de aristas
	 // y misma secuencia de grados (grado = nº de aristas de cada vértice).
	 public boolean isIsomorfo(GraphListEdge<E> other) {
	
	     // condición 1: mismo número de vértices
	     if (this.vertices.size() != other.vertices.size()) return false;
	
	     // condición 2: mismo número de aristas
	     if (this.edges.size() != other.edges.size()) return false;
	
	     // condición 3: misma secuencia de grados
	     int[] degrees1 = getDegrees(this);
	     int[] degrees2 = getDegrees(other);
	
	     // ordenamos ambas secuencias para comparar
	     java.util.Arrays.sort(degrees1);
	     java.util.Arrays.sort(degrees2);
	
	     for (int i = 0; i < degrees1.length; i++)
	         if (degrees1[i] != degrees2[i]) return false;
	
	     return true;
	 }
	
	 // Auxiliar: retorna un arreglo con el grado de cada vértice del grafo.
	 // Grado = número de aristas que tiene ese vértice.
	 private int[] getDegrees(GraphListEdge<E> g) {
	     int n = g.vertices.size();
	     int[] degrees = new int[n];
	
	     for (int i = 0; i < g.edges.size(); i++) {
	         EdgeNode<E> e = g.edges.get(i);
	         for (int j = 0; j < n; j++) {
	             if (g.vertices.get(j).getData().equals(e.origin.getData()))
	                 degrees[j]++;
	             if (g.vertices.get(j).getData().equals(e.destination.getData()))
	                 degrees[j]++;
	         }
	     }
	     return degrees;
	 }
	 
	// retorna true si el grafo es plano (sus aristas no se cruzan).
	// Usa la fórmula de Euler: E <= 3V - 6 para grafos conexos simples.
	// Condición necesaria pero no suficiente para grafos grandes.
	public boolean isPlano() {
	    int v = vertices.size();
	    int e = edges.size();

	    // grafos con menos de 3 vértices siempre son planos
	    if (v < 3) return true;

	    // fórmula de Euler: E <= 3V - 6
	    return e <= (3 * v - 6);
	}
	
	
	// Retorna true si el grafo es auto complementario.
	// Un grafo es auto complementario si su complemento es isomorfo a él mismo.
	public boolean isAutoComplementario() {
	    // construimos el complemento del grafo
	    GraphListEdge<E> complement = new GraphListEdge<>();

	    // agregamos los mismos vértices
	    for (int i = 0; i < vertices.size(); i++)
	        complement.insertVertex(vertices.get(i).getData());

	    // agregamos las aristas que NO existen en el grafo original
	    for (int i = 0; i < vertices.size(); i++) {
	        for (int j = i + 1; j < vertices.size(); j++) {
	            E v1 = vertices.get(i).getData();
	            E v2 = vertices.get(j).getData();

	            // si la arista NO existe en el original, la agregamos al complemento
	            if (!searchEdge(v1, v2) && !searchEdge(v2, v1))
	                complement.insertEdge(v1, v2);
	        }
	    }

	    // verificamos si el complemento es isomorfo al original
	    return this.isIsomorfo(complement);
	}
    

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("Vertices: ");
        for (int i = 0; i < vertices.size(); i++)
            sb.append(vertices.get(i)).append(" ");
        sb.append("\nAristas:\n");
        for (int i = 0; i < edges.size(); i++)
            sb.append(" ").append(edges.get(i).origin)
              .append(" -> ").append(edges.get(i).destination).append("\n");
        return sb.toString();
    }
}