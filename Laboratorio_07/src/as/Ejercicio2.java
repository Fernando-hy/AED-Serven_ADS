package as;

public class Ejercicio2 {
    public static void main(String[] args) throws ExceptionIsEmpty {

        System.out.println("tienda");
        QueueArray<Integer> clientes = new QueueArray<>(5);

        // Encolar clientes
        clientes.enqueue(101);
        clientes.enqueue(102);
        clientes.enqueue(103);
        clientes.enqueue(104);
        clientes.enqueue(105);

        //Llena
        clientes.enqueue(106);

        // Desencolar 2 clientes
        System.out.println("Atendiendo cliente: " + clientes.dequeue());
        System.out.println("Atendiendo cliente: " + clientes.dequeue());

        // Ver quién está al frente
        System.out.println("Cliente en frente: " + clientes.front());

        // Desencolar los restantes
        System.out.println("Atendiendo cliente: " + clientes.dequeue());
        System.out.println("Atendiendo cliente: " + clientes.dequeue());
        System.out.println("Atendiendo cliente: " + clientes.dequeue());

        // Encolar 2 más (comportamiento circular)
        clientes.enqueue(106);
        clientes.enqueue(107);

        // Desencolar hasta vaciar
        while (!clientes.isEmpty())
            System.out.println("Atendiendo cliente: " + clientes.dequeue());

        // Intentar desencolar con cola vacía
        try {
            clientes.dequeue();
        } catch (ExceptionIsEmpty e) {
            System.out.println("Cola vacía");
        }
    }
}