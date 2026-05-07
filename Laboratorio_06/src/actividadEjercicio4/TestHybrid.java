package actividadEjercicio4;

import actividad1.ExceptionIsEmpty;

/**
 * Clase principal de pruebas para PriorityQueueHybrid.
 */
public class TestHybrid {
    public static void main(String[] args) throws ExceptionIsEmpty {

        System.out.println("=== Ejercicio 4: Cola Híbrida ===\n");
        // Cola con 3 niveles (0, 1 y 2)
        PriorityQueueHybrid<String, Integer> pq = new PriorityQueueHybrid<>(3);

        // Bloque 1: Inserción de elementos
        // Estructura: (Elemento, Nivel de prioridad, Valor secundario de desempate)
        pq.enqueue("A", 2, 5);
        pq.enqueue("B", 2, 1);
        pq.enqueue("C", 1, 3);
        pq.enqueue("D", 2, 3);

        System.out.println("Estado interno:");
        System.out.print(pq);

        // Bloque 2: Extracción ordenada
        // Debería sacar primero los del Nivel 2 ordenados (B, D, A), y luego los del Nivel 1 (C).
        System.out.println("Orden de salida:");
        while (!pq.isEmpty())
            System.out.println("dequeue -> " + pq.dequeue());

        // Bloque 3: Manejo de Excepción
        System.out.println("\n=== Cola vacía ===");
        try {
            pq.dequeue();
        } catch (ExceptionIsEmpty e) {
            System.out.println("Excepción: " + e.getMessage());
        }
    }
}