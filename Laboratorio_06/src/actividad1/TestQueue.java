package actividad1;

/**
 * Clase de prueba para verificar el funcionamiento de QueueArray 
 * con diferentes tipos de datos (Integer y String).
 */
public class TestQueue {
    /**
     * Método principal que ejecuta las pruebas.
     * @param args Argumentos de línea de comandos.
     * @throws ExceptionIsEmpty Si ocurre un error de extracción.
     */
    public static void main(String[] args) throws ExceptionIsEmpty {

        // --- Prueba con cola de Enteros ---
        QueueArray<Integer> qi = new QueueArray<>(5);
        qi.enqueue(10);
        qi.enqueue(20);
        qi.enqueue(30);
        System.out.println("Cola:    " + qi);
        System.out.println("Frente:  " + qi.front());
        System.out.println("dequeue: " + qi.dequeue());
        System.out.println("Cola:    " + qi);

        // --- Prueba con cola de Cadenas de Texto ---
        QueueArray<String> qs = new QueueArray<>(3);
        qs.enqueue("Ana");
        qs.enqueue("Luis");
        qs.enqueue("Pedro");
        qs.enqueue("Maria"); // debe imprimir "Cola llena" porque la capacidad es 3
        System.out.println("Cola:    " + qs);
        System.out.println("Frente:  " + qs.front());
        System.out.println("dequeue: " + qs.dequeue());
        System.out.println("Cola:    " + qs);
    }
}