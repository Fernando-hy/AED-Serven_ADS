package paquete;

public class dpSolution {
    
    // Método basado en Programación Dinámica para maximizar el valor de la varilla.
    static int getValue(int[] values, int rodLength){ 
        
        // Arreglo de tabulación. 
        // subSolutions[i] almacenará la ganancia máxima posible para una varilla de longitud 'i'.
        int[] subSolutions = new int[rodLength + 1];
        
        // Bucle externo: Construye las soluciones de manera ascendente (desde longitud 1 hasta rodLength).
        for (int i = 1; i <= rodLength; i++){ 
            int tmpMax = -1;
            
            // Bucle interno: Evalúa todos los primeros cortes posibles para la longitud actual 'i'.
            for (int j = 0; j < i; j++)
                // Se compara el máximo actual con la suma de:
                // 1. El precio del fragmento cortado (values[j]).
                // 2. El valor óptimo ya calculado para el fragmento sobrante (subSolutions[i - j - 1]).
                tmpMax = Math.max(tmpMax, values[j] + subSolutions[i - j - 1]);
            
            // Se registra la solución óptima encontrada para la longitud 'i'.
            subSolutions[i] = tmpMax;
        }
        
        // Se retorna el valor óptimo calculado para la longitud total solicitada.
        return subSolutions[rodLength];
    }
    
    public static void main(String[] args){ 
        // Arreglo de precios. El índice 0 equivale al precio de una varilla de longitud 1, y así sucesivamente.
        int[] values = new int[]{3, 7, 1, 3, 9};
        int rodLength = values.length;
        
        // Ejecución del algoritmo de programación dinámica.
        System.out.println("El valor maximo: " + getValue(values, rodLength));
    }
}