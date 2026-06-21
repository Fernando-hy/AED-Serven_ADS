package ejercicio4;

import graph.GraphListEdge;

public class TestGraphListEdge {
    public static void main(String[] args) {

        //isConexo
        GraphListEdge<String> g1 = new GraphListEdge<>();
        g1.insertVertex("A"); g1.insertVertex("B");
        g1.insertVertex("C"); g1.insertVertex("D");
        g1.insertEdge("A", "B");
        g1.insertEdge("B", "C");
        g1.insertEdge("C", "D");

        System.out.println("=== isConexo ===");
        System.out.println("g1 conexo: " + g1.isConexo()); // true

        GraphListEdge<String> g2 = new GraphListEdge<>();
        g2.insertVertex("A"); g2.insertVertex("B");
        g2.insertVertex("C"); g2.insertVertex("D");
        g2.insertEdge("A", "B");
        // C y D quedan aislados

        System.out.println("g2 conexo: " + g2.isConexo()); // false

        //isIsomorfo
        GraphListEdge<String> g3 = new GraphListEdge<>();
        g3.insertVertex("X"); g3.insertVertex("Y");
        g3.insertVertex("Z");
        g3.insertEdge("X", "Y");
        g3.insertEdge("Y", "Z");

        GraphListEdge<String> g4 = new GraphListEdge<>();
        g4.insertVertex("1"); g4.insertVertex("2");
        g4.insertVertex("3");
        g4.insertEdge("1", "2");
        g4.insertEdge("2", "3");

        System.out.println("\n=== isIsomorfo ===");
        System.out.println("g3 isomorfo a g4: " + g3.isIsomorfo(g4)); // true

        //Test isPlano
        System.out.println("\n=== isPlano ===");
        System.out.println("g1 plano: " + g1.isPlano()); // true

        //Test isAutoComplementario
        // un ciclo de 5 vértices es auto complementario
        GraphListEdge<String> g5 = new GraphListEdge<>();
        g5.insertVertex("A"); g5.insertVertex("B");
        g5.insertVertex("C"); g5.insertVertex("D");
        g5.insertVertex("E");
        g5.insertEdge("A", "B"); g5.insertEdge("B", "C");
        g5.insertEdge("C", "D"); g5.insertEdge("D", "E");
        g5.insertEdge("E", "A");

        System.out.println("\n=== isAutoComplementario ===");
        System.out.println("g5 auto complementario: " + g5.isAutoComplementario()); // true
    }
}