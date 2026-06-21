package ejercicio2;

import org.jgrapht.Graph;
import org.jgrapht.GraphPath;
import org.jgrapht.alg.shortestpath.DijkstraShortestPath;
import org.jgrapht.graph.DefaultWeightedEdge;
import org.jgrapht.graph.SimpleWeightedGraph;

public class CityNetwork {
    public static void main(String[] args) {

        // creamos un grafo ponderado no dirigido
        Graph<String, DefaultWeightedEdge> graph = 
            new SimpleWeightedGraph<>(DefaultWeightedEdge.class);

        // agregamos ciudades (vértices)
        graph.addVertex("Arequipa");
        graph.addVertex("Cusco");
        graph.addVertex("Puno");
        graph.addVertex("Tacna");
        graph.addVertex("Moquegua");

        // agregamos carreteras con distancia (aristas ponderadas)
        graph.setEdgeWeight(graph.addEdge("Arequipa", "Cusco"),     510);
        graph.setEdgeWeight(graph.addEdge("Arequipa", "Moquegua"),  230);
        graph.setEdgeWeight(graph.addEdge("Moquegua", "Tacna"),     160);
        graph.setEdgeWeight(graph.addEdge("Cusco",    "Puno"),      390);
        graph.setEdgeWeight(graph.addEdge("Puno",     "Tacna"),     420);

        // mostramos ciudades
        System.out.println("=== Ciudades ===");
        for (String city : graph.vertexSet())
            System.out.println(" - " + city);

        // mostramos carreteras
        System.out.println("\n=== Carreteras ===");
        for (DefaultWeightedEdge edge : graph.edgeSet())
            System.out.println(" - " + graph.getEdgeSource(edge) 
                + " <-> " + graph.getEdgeTarget(edge) 
                + " : " + (int) graph.getEdgeWeight(edge) + " km");

        // calculamos ruta más corta entre dos ciudades con Dijkstra
        System.out.println("\n=== Ruta más corta: Arequipa → Tacna ===");
        DijkstraShortestPath<String, DefaultWeightedEdge> dijkstra = 
            new DijkstraShortestPath<>(graph);

        GraphPath<String, DefaultWeightedEdge> path = 
            dijkstra.getPath("Arequipa", "Tacna");

        System.out.println(" Camino: " + path.getVertexList());
        System.out.println(" Costo total: " + (int) path.getWeight() + " km");
    }
}
