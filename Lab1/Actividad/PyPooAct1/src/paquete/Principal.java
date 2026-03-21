package paquete;

import java.util.Scanner;

// Clase principal que ejecuta el programa.
public class Principal {

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        System.out.println("Ingrese una esquina del 1er rectángulo (x y):");
        // Instanciación de objetos Coordenada con el operador 'new'.
        Coordenada a1 = new Coordenada(sc.nextDouble(), sc.nextDouble());
        System.out.println("Ingrese la esquina opuesta del 1er rectángulo (x y):");
        Coordenada a2 = new Coordenada(sc.nextDouble(), sc.nextDouble());
        
        // Instanciación de Rectangulo usando los objetos Coordenada previamente creados (Composición).
        Rectangulo rectA = new Rectangulo(a1, a2);

        System.out.println("Ingrese una esquina del 2do rectángulo (x y):");
        Coordenada b1 = new Coordenada(sc.nextDouble(), sc.nextDouble());
        System.out.println("Ingrese la esquina opuesta del 2do rectángulo (x y):");
        Coordenada b2 = new Coordenada(sc.nextDouble(), sc.nextDouble());
        Rectangulo rectB = new Rectangulo(b1, b2);

        // Invocación de métodos de instancia.
        System.out.println("Rectangulo A = " + rectA.toString());
        System.out.println("Rectangulo B = " + rectB.toString());

        // Invocación de métodos de clase a través del nombre de la clase 'Verificador'.
        if (Verificador.esSobrePos(rectA, rectB)) {
            System.out.println("Rectangulos A y B se sobreponen.");
            
            // Llamada a método estático local que retorna un nuevo objeto.
            Rectangulo inter = rectanguloSobre(rectA, rectB);
            
            // Invocación de método de instancia sobre el nuevo objeto.
            System.out.printf("Area de sobreposicion = %.2f\n", inter.calculoArea());
            
        } else if (Verificador.esJunto(rectA, rectB)) {
            System.out.println("Rectangulos A y B se juntan.");
        } else {
            System.out.println("Rectangulos A y B son disjuntos.");
        }
        
        sc.close();
    }

    // Método de clase propio de Principal. Recibe y devuelve objetos.
    public static Rectangulo rectanguloSobre(Rectangulo r1, Rectangulo r2) {
        
        double interMinX = Math.max(r1.getMinX(), r2.getMinX());
        double interMaxX = Math.min(r1.getMaxX(), r2.getMaxX());
        double interMinY = Math.max(r1.getMinY(), r2.getMinY());
        double interMaxY = Math.min(r1.getMaxY(), r2.getMaxY());

        // Creación de objetos temporales para estructurar el retorno.
        Coordenada c1 = new Coordenada(interMinX, interMinY);
        Coordenada c2 = new Coordenada(interMaxX, interMaxY);

        return new Rectangulo(c1, c2);
    }
}