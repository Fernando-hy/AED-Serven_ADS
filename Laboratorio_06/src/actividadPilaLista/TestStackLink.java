package actividadPilaLista;

import actividad1.ExceptionIsEmpty;

/**
 * Clase para probar la implementación de Pila con lista enlazada.
 */
public class TestStackLink {
    public static void main(String[] args) throws ExceptionIsEmpty {

        // --- Prueba con Pila de Enteros ---
        StackLink<Integer> pi = new StackLink<>();

        pi.push(10);
        pi.push(20);
        pi.push(30); // 30 será el tope
        
        System.out.println("Pila:  " + pi);
        System.out.println("Tope:  " + pi.top());
        
        // Se extraen los últimos insertados (LIFO: 30, luego 20)
        System.out.println("pop:   " + pi.pop());
        System.out.println("pop:   " + pi.pop());
        System.out.println("Pila:  " + pi);

        // --- Prueba con Pila de Strings ---
        StackLink<String> ps = new StackLink<>();

        ps.push("A");
        ps.push("B");
        ps.push("C");
        
        System.out.println("Pila:  " + ps);
        
        // Vaciar la pila completa
        while (!ps.isEmpty())
            System.out.println("pop -> " + ps.pop());

        // Manejo de Excepción
        try {
            ps.pop();
        } catch (ExceptionIsEmpty e) {
            System.out.println("Excepción: " + e.getMessage());
        }
    }
}