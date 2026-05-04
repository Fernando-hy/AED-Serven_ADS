package paquete;

public class Hanoi {
    public static void main(String[] args){ 
        // Punto de entrada. Ejecución con 3 discos.
        // Mapeo lógico: torre1 (Origen) = 1, torre2 (Auxiliar) = 2, torre3 (Destino) = 3.
        torresHanoi(3, 1, 2, 3);
    }
        
    // Motor recursivo basado en el paradigma "Divide y Vencerás".
    public static void torresHanoi(int discos, int torre1, int torre2, int torre3){
        
        // Caso base: Subproblema mínimo.
        // Si solo hay un disco, se traslada directamente del origen al destino sin intermediarios.
        if (discos == 1){
            System.out.println("Mover disco de torre " + torre1 + " a torre " + torre3);
        } else {
            
            // Paso 1: Trasladar el bloque superior de (n-1) discos del Origen a la torre Auxiliar.
            // Se utiliza el Destino como espacio de soporte temporal.
            torresHanoi(discos - 1, torre1, torre3, torre2);
            
            // Paso 2: Trasladar el disco restante (el más grande de la iteración actual) 
            // directamente del Origen al Destino.
            System.out.println("mover disco de torre " + torre1 + " a torre " + torre3);
            
            // Paso 3: Trasladar el bloque de (n-1) discos desde la torre Auxiliar hacia el Destino.
            // Se utiliza el Origen original como nuevo espacio de soporte temporal.
            torresHanoi(discos - 1, torre2, torre1, torre3);
        }
    }
}