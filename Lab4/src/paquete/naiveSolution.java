package paquete;

public class naiveSolution {
    
    // Motor recursivo de fuerza bruta para maximizar la ganancia del corte de varilla.
    static int getValue(int[] values, int length){ 
        
        // Caso base: Si la longitud restante es 0 o menor, no hay ganancia posible.
        if (length <= 0)
            return 0;
            
        // Variable local para rastrear la ganancia máxima en la iteración actual de la recursión.
        int tmpMax = -1;
        
        // Bucle iterativo que prueba cada posible posición para el primer corte.
        for (int i = 0; i < length; i++) {
            // Se calcula el máximo entre el valor actual registrado y la suma de:
            // 1. El valor de la pieza recién cortada (values[i]).
            // 2. El cálculo recursivo de la ganancia máxima para el resto de la varilla (length - i - 1).
            tmpMax = Math.max(tmpMax, values[i] + getValue(values, length - i - 1));
        }
        
        // Retorna la ganancia máxima encontrada para la longitud solicitada en esta llamada.
        return tmpMax;
    }
    
    public static void main(String[] args){ 
        // Arreglo de precios: el índice 'i' corresponde al precio de un bloque de longitud 'i+1'.
        int[] values = new int[]{3, 7, 1, 3, 9}; 
        int rodLength = values.length;
        
        // Ejecución del algoritmo recursivo.
        System.out.println("El valor maximo:" + getValue(values, rodLength));
    }
}