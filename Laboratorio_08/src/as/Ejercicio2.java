package as;

/**
 * Clase que simula la atención de clientes en una tienda como parte del Ejercicio 2.
 * Demuestra el uso del comportamiento circular de la cola al reutilizar posiciones liberadas.
 */
public class Ejercicio2 {
    public static void main(String[] args) throws ExceptionIsEmpty {

        System.out.println("=== SIMULADOR DE TIENDA CON COLA CIRCULAR ===");
        QueueArray<Integer> clientes = new QueueArray<>(5);

        // Encolar los primeros 5 clientes
        clientes.enqueue(101);
        clientes.enqueue(102);
        clientes.enqueue(103);
        clientes.enqueue(104);
        clientes.enqueue(105);

        // Intento de encolar un sexto cliente (debe fallar "Cola llena")
        clientes.enqueue(106);

        // Desencolar (atender) a 2 clientes del frente
        System.out.println("Atendiendo cliente: " + clientes.dequeue());
        System.out.println("Atendiendo cliente: " + clientes.dequeue());

        // Ver quién queda ahora en el frente de espera
        System.out.println("Cliente en frente: " + clientes.front());

        // Desencolar los clientes restantes actualmente en espera
        System.out.println("Atendiendo cliente: " + clientes.dequeue());
        System.out.println("Atendiendo cliente: " + clientes.dequeue());
        System.out.println("Atendiendo cliente: " + clientes.dequeue());

        // Encolar 2 nuevos clientes (demuestra que la cola circular reutiliza el espacio al inicio del arreglo)
        clientes.enqueue(106);
        clientes.enqueue(107);

        // Desencolar hasta vaciar por completo la cola
        while (!clientes.isEmpty())
            System.out.println("Atendiendo cliente: " + clientes.dequeue());

        // Intentar desencolar con la cola vacía para validar el lanzamiento de ExceptionIsEmpty
        try {
            clientes.dequeue();
        } catch (ExceptionIsEmpty e) {
            System.out.println("Validación exitosa: " + e.getMessage());
        }
    }
}