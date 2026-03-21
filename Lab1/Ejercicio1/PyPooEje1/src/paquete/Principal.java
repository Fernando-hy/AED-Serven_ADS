package paquete;

import java.util.Scanner;

// Clase de ejecución del programa.
public class Principal {

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        
        // Instanciación de la clase contenedora de arreglos de objetos.
        ContainerRect contenedor = new ContainerRect(10);

        System.out.println("Ingrese una esquina del 1er rectángulo (x y):");
        // Creación de objetos ('new').
        Coordenada a1 = new Coordenada(sc.nextDouble(), sc.nextDouble());
        System.out.println("Ingrese la esquina opuesta del 1er rectángulo (x y):");
        Coordenada a2 = new Coordenada(sc.nextDouble(), sc.nextDouble());
        // Composición: creación de objeto pasando otros objetos como parámetros.
        Rectangulo rectA = new Rectangulo(a1, a2);
        
        // Llamada a método de instancia para almacenar el objeto en el arreglo.
        contenedor.addRectangulo(rectA);

        System.out.println("Ingrese una esquina del 2do rectángulo (x y):");
        Coordenada b1 = new Coordenada(sc.nextDouble(), sc.nextDouble());
        System.out.println("Ingrese la esquina opuesta del 2do rectángulo (x y):");
        Coordenada b2 = new Coordenada(sc.nextDouble(), sc.nextDouble());
        Rectangulo rectB = new Rectangulo(b1, b2);
        
        // Almacenamiento en el arreglo.
        contenedor.addRectangulo(rectB);

        System.out.println("Rectangulo A = " + rectA.toString());
        System.out.println("Rectangulo B = " + rectB.toString());

        // Llamada a método de clase (estático).
        if (Verificador.esSobrePos(rectA, rectB)) {
            System.out.println("Rectangulos A y B se sobreponen.");
            
            Rectangulo inter = rectanguloSobre(rectA, rectB);
            System.out.printf("Area de sobreposicion = %.2f\n", inter.calculoArea());
            
        } else if (Verificador.esJunto(rectA, rectB)) {
            System.out.println("Rectangulos A y B se juntan.");
        } else {
            System.out.println("Rectangulos A y B son disjuntos.");
        }
        
        // Invocación del método toString para imprimir los datos del arreglo de objetos.
        System.out.println("\n" + contenedor.toString());
        
        sc.close();
    }

    // Método de clase propio. Genera y retorna un nuevo objeto.
    public static Rectangulo rectanguloSobre(Rectangulo r1, Rectangulo r2) {
        double interMinX = Math.max(r1.getMinX(), r2.getMinX());
        double interMaxX = Math.min(r1.getMaxX(), r2.getMaxX());
        double interMinY = Math.max(r1.getMinY(), r2.getMinY());
        double interMaxY = Math.min(r1.getMaxY(), r2.getMaxY());

        Coordenada c1 = new Coordenada(interMinX, interMinY);
        Coordenada c2 = new Coordenada(interMaxX, interMaxY);

        return new Rectangulo(c1, c2);
    }
}