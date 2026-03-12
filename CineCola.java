import java.util.Scanner;

class Nodo {
    String nombre;
    Nodo siguiente;
    Nodo(String nombre) { this.nombre = nombre; this.siguiente = null; }
}

class Cola {
    Nodo frente, fin;
    void encolar(String nombre) {
        Nodo nuevo = new Nodo(nombre);
        if (fin == null) { frente = fin = nuevo; }
        else { fin.siguiente = nuevo; fin = nuevo; }
        System.out.println(nombre + " se unio a la cola.");
    }
    String desencolar() {
        if (frente == null) return null;
        String nombre = frente.nombre;
        frente = frente.siguiente;
        if (frente == null) fin = null;
        return nombre;
    }
    void mostrar() {
        if (frente == null) { System.out.println("Cola vacia."); return; }
        Nodo temp = frente;
        System.out.print("Fila: ");
        while (temp != null) { System.out.print("[" + temp.nombre + "] "); temp = temp.siguiente; }
        System.out.println();
    }
}

public class CineCola {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        Cola cola = new Cola();
        int op = 0;
        while (op != 4) {
            System.out.println("\n--- TAQUILLA CINE ---");
            System.out.println("1. Llegada de Cliente\n2. Atender Siguiente\n3. Ver Fila\n4. Salir");
            System.out.print("Opcion: ");
            op = sc.nextInt();
            sc.nextLine();
            switch (op) {
                case 1:
                    System.out.print("Nombre: ");
                    cola.encolar(sc.nextLine());
                    break;
                case 2:
                    String t = cola.desencolar();
                    if (t != null) System.out.println("Atendiendo a: " + t);
                    else System.out.println("Nadie en cola.");
                    break;
                case 3:
                    cola.mostrar();
                    break;
            }
        }
        sc.close();
    }
}
