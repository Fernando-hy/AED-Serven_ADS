package as;

/**
 * Clase de prueba para validar la funcionalidad de la cola basada en arreglos circulares.
 * Realiza inserciones y eliminaciones con diferentes tipos genéricos (Integer y String).
 */
public class TestQueue {
    public static void main(String[] args) throws ExceptionIsEmpty {

        // --- PRUEBA CON ENTEROS ---
        System.out.println("=== PRUEBA DE COLA DE ENTEROS ===");
        QueueArray<Integer> qi = new QueueArray<>(5);
        qi.enqueue(10);
        qi.enqueue(20);
        qi.enqueue(30);
        System.out.println("Cola:    " + qi);
        System.out.println("Frente:  " + qi.front());
        System.out.println("dequeue: " + qi.dequeue());
        System.out.println("Cola:    " + qi);

        System.out.println();

        // --- PRUEBA CON CADENAS DE TEXTO (STRINGS) ---
        System.out.println("=== PRUEBA DE COLA DE STRINGS ===");
        QueueArray<String> qs = new QueueArray<>(3);
        qs.enqueue("Ana");
        qs.enqueue("Luis");
        qs.enqueue("Pedro");
        qs.enqueue("Maria"); // Debe imprimir "Cola llena" porque la capacidad es 3
        System.out.println("Cola:    " + qs);
        System.out.println("Frente:  " + qs.front());
        System.out.println("dequeue: " + qs.dequeue());
        System.out.println("Cola:    " + qs);
    }
}