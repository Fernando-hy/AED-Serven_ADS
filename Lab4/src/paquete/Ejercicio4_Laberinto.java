package paquete;

public class Ejercicio4_Laberinto {
    public static void main(String[] args) {
        // Matriz de entrada: 0 representa un camino transitable y 1 un obstáculo.
        int[][] laberinto = {
            {0, 0, 1},
            {1, 0, 1},
            {1, 0, 0}
        };
        int n = laberinto.length;
        int m = laberinto[0].length;
        
        // Matriz de estado: Almacenará la ruta final exitosa (1 = parte de la ruta).
        int[][] solucion = new int[n][m];

        // Se inicia la búsqueda desde la coordenada origen (0,0).
        if (resolver(laberinto, 0, 0, solucion)) {
            System.out.println("Resultado: true"); 
            imprimirMatriz(solucion);
        } else {
            System.out.println("Resultado: false");
        }
    }

    // Motor recursivo de backtracking.
    public static boolean resolver(int[][] lab, int x, int y, int[][] sol) {
        int n = lab.length;
        int m = lab[0].length;

        // Caso base (Condición de éxito): Se alcanzó la coordenada destino de forma válida.
        if (x == n - 1 && y == m - 1 && lab[x][y] == 0) {
            sol[x][y] = 1;
            return true;
        }

        // Validación de restricciones de movimiento.
        if (esSeguro(lab, x, y, sol)) {
            
            // Se asume temporalmente que la celda actual es parte de la solución.
            sol[x][y] = 1; 

            // Exploración de ramas (DFS) en 4 direcciones.
            // Si alguna rama retorna true, la solución se propaga hacia arriba.
            if (resolver(lab, x + 1, y, sol)) return true; // Movimiento: Abajo 
            if (resolver(lab, x, y + 1, sol)) return true; // Movimiento: Derecha
            if (resolver(lab, x - 1, y, sol)) return true; // Movimiento: Arriba
            if (resolver(lab, x, y - 1, sol)) return true; // Movimiento: Izquierda

            // Fase de Backtracking: Ninguna de las 4 direcciones generó una ruta válida.
            // Se desmarca la celda actual para permitir su exploración en otras ramas recursivas.
            sol[x][y] = 0; 
            return false;
        }
        
        // Retorno por defecto si la coordenada es inválida, es un obstáculo o ya fue visitada.
        return false;
    }

    // Función de validación de límites y estados.
    private static boolean esSeguro(int[][] lab, int x, int y, int[][] sol) {
        // Retorna true solo si (x,y) está dentro de la matriz, es transitable (0)
        // y no ha sido visitada previamente en la rama actual (sol == 0).
        return (x >= 0 && x < lab.length && y >= 0 && y < lab[0].length 
                && lab[x][y] == 0 && sol[x][y] == 0);
    }

    // Método utilitario para la visualización por consola de la matriz resultante.
    private static void imprimirMatriz(int[][] sol) {
        for (int[] fila : sol) {
            for (int celda : fila) System.out.print(celda + " ");
            System.out.println();
        }
    }
}