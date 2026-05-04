package paquete;

public class Ejercicio3_ViajeRio {
    public static void main(String[] args) {
        // Valor representativo de "Infinito" para bloquear rutas imposibles.
        int INF = 99999;
        
        // Matriz de adyacencia de costos. Es triangular superior porque el 
        // desplazamiento es unidireccional (no se puede retroceder en el río).
        int[][] T = {
            {0, 5, 15, 40},
            {INF, 0, 8, 25},
            {INF, INF, 0, 10},
            {INF, INF, INF, 0}
        };
        int n = T.length;
        
        System.out.println("Costo mínimo: " + costoMinimo(T, n));
    }

    // Motor de programación dinámica mediante enfoque Bottom-Up (Tabulación).
    public static int costoMinimo(int[][] T, int n) {
        // Arreglo de estados. C[j] almacenará el costo mínimo garantizado 
        // para viajar desde la estación 0 hasta la estación j.
        // Observación: Depende de una inicialización en INF para funcionar de forma lógica.
        int[] C = new int[n]; 

        // Bucle externo: define la estación de destino 'j' que se está evaluando actualmente.
        for (int j = 1; j < n; j++) {
            
            // Bucle interno: itera sobre todas las posibles estaciones de escala 'k'
            // que preceden a la estación de destino 'j'.
            for (int k = 0; k < j; k++) {
                
                // Proceso de Relajación (Ecuación de Bellman):
                // Compara el costo actual de llegar a 'j' contra una nueva ruta:
                // Costo óptimo de llegar a la escala 'k' + costo directo de 'k' hacia 'j'.
                if (C[k] + T[k][j] < C[j]) {
                    // Si la nueva ruta es más barata, se actualiza el costo mínimo.
                    C[j] = C[k] + T[k][j]; 
                }
            }
        }
        
        // El último elemento del arreglo contiene la solución global al problema.
        return C[n - 1];
    }
}