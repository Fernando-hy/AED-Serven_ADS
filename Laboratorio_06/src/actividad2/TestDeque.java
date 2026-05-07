package actividad2;

import actividad1.ExceptionIsEmpty;

/**
 * Clase de prueba para verificar las operaciones de la estructura DequeLink.
 */
public class TestDeque {
    public static void main(String[] args) throws ExceptionIsEmpty {

        // --- Prueba con Deque de Enteros ---
        DequeLink<Integer> dq = new DequeLink<>();

        // Inserciones al final
        dq.addLast(10);
        dq.addLast(20);
        dq.addLast(30);
        // Inserción al inicio
        dq.addFirst(5);
        
        System.out.println("Deque:       " + dq.toString());
        System.out.println("Primero:     " + dq.getFirst());
        System.out.println("Último:      " + dq.getLast());
        
        // Extracciones
        System.out.println("removeFirst: " + dq.removeFirst());
        System.out.println("removeLast:  " + dq.removeLast());
        System.out.println("Deque final: " + dq);

        // --- Prueba con Deque de Strings ---
        DequeLink<String> dqs = new DequeLink<>();
        dqs.addLast("Ana");
        dqs.addLast("Luis");
        dqs.addFirst("Zoe");
        
        System.out.println("Deque:       " + dqs.toString());
        System.out.println("removeFirst: " + dqs.removeFirst());
        System.out.println("removeLast:  " + dqs.removeLast());
        System.out.println("Deque final: " + dqs);
    }
}