package asd;

import as.ExceptionIsEmpty;

/**
 * Clase de prueba para validar la funcionalidad del Deque (DequeLink)
 * tanto con enteros (Integer) como con cadenas de texto (String).
 */
public class TestDeque {
    public static void main(String[] args) throws ExceptionIsEmpty {

        // --- PRUEBA CON ENTEROS ---
        System.out.println("=== PRUEBA DE DEQUE CON ENTEROS ===");
        DequeLink<Integer> dq = new DequeLink<>();

        // Adición al final e inicio
        dq.addLast(10);
        dq.addLast(20);
        dq.addLast(30);
        dq.addFirst(5);
        System.out.println("Deque:       " + dq.toString());
        System.out.println("Primero:     " + dq.getFirst());
        System.out.println("Último:      " + dq.getLast());
        System.out.println("removeFirst: " + dq.removeFirst());
        System.out.println("removeLast:  " + dq.removeLast());
        System.out.println("Deque final: " + dq);

        System.out.println();

        // --- PRUEBA CON STRINGS ---
        System.out.println("=== PRUEBA DE DEQUE CON STRINGS ===");
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