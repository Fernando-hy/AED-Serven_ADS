package actividad1;

/**
 * Clase principal para probar el funcionamiento de QueueArray en un escenario de tienda.
 */
public class Ejercicio2 {
    /**
     * Método principal que ejecuta la simulación de atención de clientes.
     * @param args Argumentos de línea de comandos.
     * @throws ExceptionIsEmpty Si se intenta extraer de una cola vacía.
     */
    public static void main(String[] args) throws ExceptionIsEmpty {

        System.out.println("tienda");
        // Se crea una cola con capacidad para 5 clientes
        QueueArray<Integer> clientes = new QueueArray<>(5);

        // Bloque 1: Encolar clientes iniciales
        clientes.enqueue(101);
        clientes.enqueue(102);
        clientes.enqueue(103);
        clientes.enqueue(104);
        clientes.enqueue(105);

        // Intento de encolar cuando la cola ya está llena (debe imprimir "Cola llena")
        clientes.enqueue(106);

        // Bloque 2: Atender (desencolar) a los primeros 2 clientes
        System.out.println("Atendiendo cliente: " + clientes.dequeue());
        System.out.println("Atendiendo cliente: " + clientes.dequeue());

        // Bloque 3: Ver quién es el próximo en la fila sin atenderlo
        System.out.println("Cliente en frente: " + clientes.front());

        // Bloque 4: Atender a los 3 clientes restantes de la primera tanda
        System.out.println("Atendiendo cliente: " + clientes.dequeue());
        System.out.println("Atendiendo cliente: " + clientes.dequeue());
        System.out.println("Atendiendo cliente: " + clientes.dequeue());

        // Bloque 5: Encolar 2 más para probar el comportamiento circular del arreglo
        // Como ya salieron 5, hay espacio al inicio del arreglo.
        clientes.enqueue(106);
        clientes.enqueue(107);

        // Bloque 6: Vaciar la cola atendiendo a los que quedan
        while (!clientes.isEmpty())
            System.out.println("Atendiendo cliente: " + clientes.dequeue());

        // Bloque 7: Probar el manejo de excepciones al desencolar de una cola vacía
        try {
            clientes.dequeue();
        } catch (ExceptionIsEmpty e) {
            System.out.println("Cola vacía");
        }
    }
}