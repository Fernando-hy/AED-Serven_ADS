package graph;
import listlinked.ListLinked;

import listlinked.DequeLink;
import listlinked.StackLink;
import actividad1.ExceptionIsEmpty;
import java.util.ArrayList;
import java.util.HashMap;

public class GraphLink<E> implements Graph<E, Edge<E>> {
    private ListLinked<AdjList<E>> graph;  // AdjList, no Vertex

    public GraphLink() {
        graph = new ListLinked<>();
    }

    private AdjList<E> findVertex(E data) {
        for (int i = 0; i < graph.size(); i++) {
            AdjList<E> adj = graph.get(i);
            if (adj.getVertex().getData().equals(data))
                return adj;
        }
        return null;
    }

    public void insertVertex(E data) {
        if (findVertex(data) != null) return;
        Vertex<E> vertex = new Vertex<>(data);
        graph.insertLast(new AdjList<>(vertex));
    }

    public void insertEdge(E origin, E destination) {
        AdjList<E> v1 = findVertex(origin);
        AdjList<E> v2 = findVertex(destination);
        if (v1 == null || v2 == null) 
        	return;

        v1.getEdges().insertLast(new Edge<>(v2.getVertex()));
        v2.getEdges().insertLast(new Edge<>(v1.getVertex()));
    }
    
 // Elimina un vértice y todas las aristas que apunten a él.
    public void removeVertex(E data) {
        if (findVertex(data) == null) return;

        // eliminamos aristas que apunten a data en todos los vértices
        for (int i = 0; i < graph.size(); i++) {
            AdjList<E> adj = graph.get(i);
            for (int j = 0; j < adj.getEdges().size(); j++) {
                if (adj.getEdges().get(j).getDestination().getData().equals(data)) {
                    adj.getEdges().removeAt(j);
                    break;
                }
            }
        }

        // eliminamos el vértice de la lista principal por índice
        for (int i = 0; i < graph.size(); i++) {
            if (graph.get(i).getVertex().getData().equals(data)) {
                graph.removeAt(i);
                break;
            }
        }
    }
    
 // Elimina la arista entre origin y destination en ambos sentidos.
    public void removeEdge(E origin, E destination) {
        AdjList<E> v1 = findVertex(origin);
        AdjList<E> v2 = findVertex(destination);
        if (v1 == null || v2 == null) return;

        // eliminamos la arista origin → destination
        for (int i = 0; i < v1.getEdges().size(); i++) {
            if (v1.getEdges().get(i).getDestination().getData().equals(destination)) {
                v1.getEdges().removeAt(i);
                break;
            }
        }

        // eliminamos la arista destination → origin
        for (int j = 0; j < v2.getEdges().size(); j++) {
            if (v2.getEdges().get(j).getDestination().getData().equals(origin)) {
                v2.getEdges().removeAt(j);
                break;
            }
        }
    }
    
 	public void BFS(E origin) {
	  if (findVertex(origin) == null) return;
	
	  // arreglo para marcar vértices visitados
	  boolean[] visited = new boolean[graph.size()];
	  DequeLink<E> queue = new DequeLink<>();
	
	  try {
	      // marcamos el origen como visitado y lo encolamos
	      int startIdx = getIndex(origin);
	      visited[startIdx] = true;
	      queue.addLast(origin);
	
	      System.out.print("BFS: ");
	
	      while (!queue.isEmpty()) {
	          E current = queue.removeFirst();
	          System.out.print(current + " ");
	
	          AdjList<E> adj = findVertex(current);
	
	          // encolamos los vecinos no visitados
	          for (int i = 0; i < adj.getEdges().size(); i++) {
	              E neighbor = adj.getEdges().get(i).getDestination().getData();
	              int neighborIdx = getIndex(neighbor);
	              if (!visited[neighborIdx]) {
	                  visited[neighborIdx] = true;
	                  queue.addLast(neighbor);
	              }
	          }
	      }
	      System.out.println();
	
	  } catch (ExceptionIsEmpty e) {
	      e.printStackTrace();
	  }
	}
 	
	public void DFS(E origin) {
	    if (findVertex(origin) == null) return;
	
	    boolean[] visited = new boolean[graph.size()];
	    StackLink<E> stack = new StackLink<>();
	
	    try {
	        // apilamos el origen
	        stack.push(origin);
	
	        System.out.print("DFS: ");
	
	        while (!stack.isEmpty()) {
	            E current = stack.pop();
	
	             // solo procesamos si no fue visitado
	            int idx = getIndex(current);
	            if (!visited[idx]) {
	                visited[idx] = true;
	                System.out.print(current + " ");
	
	                AdjList<E> adj = findVertex(current);
	
	                // apilamos vecinos no visitados en orden inverso
	                // para mantener el orden correcto de visita
	                for (int i = adj.getEdges().size() - 1; i >= 0; i--) {
	                    E neighbor = adj.getEdges().get(i).getDestination().getData();
	                    int neighborIdx = getIndex(neighbor);
	                    if (!visited[neighborIdx])
	                         stack.push(neighbor);
	                }
	            }
	        }
	        System.out.println();

	    } catch (ExceptionIsEmpty e) {
	        e.printStackTrace();
	    }
	}
	
	public boolean isConnected() {
	    if (graph.size() == 0) return true;

	    boolean[] visited = new boolean[graph.size()];
	    DequeLink<E> queue = new DequeLink<>();

	    try {
	        // arrancamos desde el primer vértice
	        visited[0] = true;
	        queue.addLast(graph.get(0).getVertex().getData());
	        int count = 1;

	        while (!queue.isEmpty()) {
	            E current = queue.removeFirst();
	            AdjList<E> adj = findVertex(current);

	            for (int i = 0; i < adj.getEdges().size(); i++) {
	                E neighbor = adj.getEdges().get(i).getDestination().getData();
	                int neighborIdx = getIndex(neighbor);
	                if (!visited[neighborIdx]) {
	                    visited[neighborIdx] = true;
	                    count++;
	                    queue.addLast(neighbor);
	                }
	            }
	        }

	        // si visitamos todos los vértices el grafo es conexo
	        return count == graph.size();

	    } catch (ExceptionIsEmpty e) {
	        e.printStackTrace();
	        return false;
	    }
	}
	
	//Retorna el índice de un vértice en la lista principal, -1 si no existe.
	private int getIndex(E data) {
	 for (int i = 0; i < graph.size(); i++)
	     if (graph.get(i).getVertex().getData().equals(data))
	         return i;
	 return -1;
	}

 
    //EJERCICIO 1
    
    public void insertEdgeWeight(E origin, E destination, int w) {
        AdjList<E> v1 = findVertex(origin);
        AdjList<E> v2 = findVertex(destination);
        if (v1 == null || v2 == null) return;

        v1.getEdges().insertLast(new Edge<>(v2.getVertex(), w));
        v2.getEdges().insertLast(new Edge<>(v1.getVertex(), w));
    }
    
	 public ArrayList<E> shortPath(E origin, E destination) {
	     ArrayList<E> path = new ArrayList<>();
	
	     if (findVertex(origin) == null || findVertex(destination) == null)
	         return path;
	
	     if (origin.equals(destination)) {
	         path.add(origin);
	         return path;
	     }
	
	     HashMap<E, E> prev = new HashMap<>();
	     DequeLink<E> queue = new DequeLink<>();
	
	     try {
	         queue.addLast(origin);
	         prev.put(origin, null);
	
	         while (!queue.isEmpty()) {
	             E current = queue.removeFirst();
	             AdjList<E> adj = findVertex(current);
	
	             for (int i = 0; i < adj.getEdges().size(); i++) {
	                 E neighbor = adj.getEdges().get(i).getDestination().getData();
	
	                 if (!prev.containsKey(neighbor)) {
	                     prev.put(neighbor, current);
	
	                     if (neighbor.equals(destination)) {
	                         // reconstruimos el camino de destino hacia origen
	                         E step = destination;
	                         while (step != null) {
	                             path.add(0, step);
	                             step = prev.get(step);
	                         }
	                         return path;
	                     }
	                     queue.addLast(neighbor);
	                 }
	             }
	         }
	     } catch (ExceptionIsEmpty e) {
	         e.printStackTrace();
	     }
	
	     return path;
	 }
	 
	 public boolean isConexo() {
		    return isConnected();
	}
	 
	 
	public StackLink<E> dijkstra(E origin, E destination) {
	    StackLink<E> result = new StackLink<>();

	    if (findVertex(origin) == null || findVertex(destination) == null)
	        return result;

	    int n = graph.size();

	    // distancias mínimas desde origin a cada vértice
	    int[] dist = new int[n];
	    // índice del vértice predecesor para reconstruir el camino
	    int[] prev = new int[n];
	    // vértices ya procesados
	    boolean[] visited = new boolean[n];

	    // inicializamos distancias en infinito
	    for (int i = 0; i < n; i++) {
	        dist[i] = Integer.MAX_VALUE;
	        prev[i] = -1;
	    }

	    // distancia al origen es 0
	    int startIdx = getIndex(origin);
	    dist[startIdx] = 0;

	    for (int i = 0; i < n; i++) {
	        // buscamos el vértice no visitado con menor distancia
	        int u = -1;
	        for (int j = 0; j < n; j++) {
	            if (!visited[j] && (u == -1 || dist[j] < dist[u]))
	                u = j;
	        }

	        if (dist[u] == Integer.MAX_VALUE) break; // resto inaccesible
	        visited[u] = true;

	        // actualizamos distancias de los vecinos
	        AdjList<E> adj = graph.get(u);
	        for (int j = 0; j < adj.getEdges().size(); j++) {
	            Edge<E> edge = adj.getEdges().get(j);
	            int v = getIndex(edge.getDestination().getData());
	            int newDist = dist[u] + edge.getWeight();

	            if (newDist < dist[v]) {
	                dist[v] = newDist;
	                prev[v] = u;
	            }
	        }
	    }
	   

	    // reconstruimos el camino desde destination hacia origin
	    int destIdx = getIndex(destination);
	    if (dist[destIdx] == Integer.MAX_VALUE) return result; // no hay camino

	    // apilamos el camino — al desapilar queda en orden origin → destination
	    for (int i = destIdx; i != -1; i = prev[i])
	        result.push(graph.get(i).getVertex().getData());

	    return result;
	}
	
	
	//Ejercicio3
	public boolean searchVertex(E data) {
	    return findVertex(data) != null;
	}
	
	// Retorna true si existe una arista entre origin y destination.
	public boolean searchEdge(E origin, E destination) {
	    AdjList<E> v1 = findVertex(origin);
	    if (v1 == null) return false;

	    for (int i = 0; i < v1.getEdges().size(); i++)
	        if (v1.getEdges().get(i).getDestination().getData().equals(destination))
	            return true;

	    return false;
	}
	
	public ArrayList<E> adjacentVertices(E data) {
	    ArrayList<E> adjacents = new ArrayList<>();
	    AdjList<E> adj = findVertex(data);
	    if (adj == null) return adjacents;

	    for (int i = 0; i < adj.getEdges().size(); i++)
	        adjacents.add(adj.getEdges().get(i).getDestination().getData());

	    return adjacents;
	}
	
	@Override
	public String toString() {
	    StringBuilder sb = new StringBuilder();
	    
	    // Bucle externo: recorre todos los vértices del grafo
	    for (int i = 0; i < graph.size(); i++) {
	        AdjList<E> adj = graph.get(i);
	        sb.append(adj.getVertex()).append(" -> ");
	        
	        // Bucle interno: recorre todas las aristas de ese vértice
	        for (int j = 0; j < adj.getEdges().size(); j++) {
	            sb.append(adj.getEdges().get(j)).append(" ");
	        } // <-- Aquí cerramos el bucle interno
	        
	        // El salto de línea va DESPUÉS de imprimir todas las aristas del vértice
	        sb.append("\n");
	        
	    }
	    
	    // El return va FUERA de los bucles, una vez que se ha construido todo el String
	    return sb.toString();
	}
}