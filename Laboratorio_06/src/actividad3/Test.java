package actividad3;

import actividad1.ExceptionIsEmpty;

/**
 * Clase principal para probar la funcionalidad de la cola de prioridad ordenada mediante lista enlazada.
 */
public class Test {
    public static void main(String[] args) throws ExceptionIsEmpty {

        // --- Prueba 1: Cola con prioridad Entera ---
        PriorityQueue<String, Integer> pq = new PriorityQueueLinkSort<>();

        // Encolar elementos con prioridad (mayor número = mayor prioridad)
        pq.enqueue("Tarea baja",   1);
        pq.enqueue("Tarea alta",   5);
        pq.enqueue("Tarea media",  3);
        pq.enqueue("Urgente",      5); // misma prioridad que "Tarea alta"
        pq.enqueue("Normal",       2);

        // Verificamos el estado
        System.out.println("Cola: " + pq);
        System.out.println("Frente (mayor prioridad): " + pq.front());
        System.out.println("Fondo  (menor prioridad): " + pq.back());

        // Extraer todo para comprobar orden
        while (!pq.isEmpty()) {
            System.out.println("dequeue -> " + pq.dequeue());
        }

        // Manejo de la excepción
        try {
            pq.dequeue();
        } catch (ExceptionIsEmpty e) {
            System.out.println("Excepción: " + e.getMessage());
        }
        
        // --- Prueba 2: Cola con prioridad String ---
        // (Las cadenas se ordenan alfabéticamente: "media" > "baja" > "alta", 
        // dependiendo de la implementación de String.compareTo)
        PriorityQueue<String, String> pq3 = new PriorityQueueLinkSort<>();
        pq3.enqueue("Paciente Ana",   "alta");
        pq3.enqueue("Paciente Luis",  "baja");
        pq3.enqueue("Paciente Pedro", "media");
        pq3.enqueue("Paciente Zoe",   "alta");
        pq3.enqueue("Paciente Maria", "baja");

        System.out.println("Cola:   " + pq3);
        System.out.println("Frente: " + pq3.front());
        System.out.println("Fondo:  " + pq3.back());

        System.out.println("\n--- Desencolando ---");
        while (!pq3.isEmpty())
            System.out.println("dequeue -> " + pq3.dequeue());
    }
}