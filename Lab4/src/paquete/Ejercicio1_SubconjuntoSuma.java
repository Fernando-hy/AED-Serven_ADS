package paquete;

import java.util.ArrayList;
import java.util.List;

public class Ejercicio1_SubconjuntoSuma {
    public static void main(String[] args) {
        // Casos de prueba estáticos para validar la lógica de restricciones y suma.
        int[] arr1 = {3, 4, 6, 7, 2};
        System.out.println("Salida 1: " + verificarSuma(arr1, 20)); // Esperado: true 

        int[] arr2 = {3, 4, 6, 7, 8};
        System.out.println("Salida 2: " + verificarSuma(arr2, 18)); // Esperado: false
    }

    // Fase 1: Preprocesamiento y filtrado de restricciones en tiempo O(n).
    public static boolean verificarSuma(int[] arr, int objetivo) {
        int sumaObligatoria = 0;
        List<Integer> opcionales = new ArrayList<>();

        for (int i = 0; i < arr.length; i++) {
            // Regla 1: Los múltiplos de 3 se suman incondicionalmente al acumulado.
            if (arr[i] % 3 == 0) {
                sumaObligatoria += arr[i];
            } else {
                // Regla 2: Exclusión de elementos pares si están seguidos por otro par.
                // Se evalúa el índice actual y el adyacente derecho para evitar desbordamientos.
                boolean prohibido = false;
                if (arr[i] % 2 == 0 && i < arr.length - 1 && arr[i + 1] % 2 == 0) {
                    prohibido = true;
                }
                
                // Si el elemento no es múltiplo de 3 ni rompe la regla de pares, es evaluable.
                if (!prohibido) {
                    opcionales.add(arr[i]);
                }
            }
        }
        // Se delega la resolución al motor recursivo, descontando la suma obligatoria del objetivo.
        return backtrackingSuma(opcionales, 0, objetivo - sumaObligatoria);
    }

    // Fase 2: Motor de búsqueda exhaustiva mediante Backtracking (Árbol de decisión binario).
    private static boolean backtrackingSuma(List<Integer> opcionales, int index, int objetivoRestante) {
        // Caso base 1 (Éxito): La combinación exacta alcanza el objetivo.
        if (objetivoRestante == 0) return true; 
        
        // Caso base 2 (Fallo): Se agotaron los elementos o se excedió el objetivo temporal.
        if (index >= opcionales.size() || objetivoRestante < 0) return false;

        // Ramificación recursiva: Se evalúan dos caminos lógicos independientes (OR).
        // 1. Incluir el elemento actual (se resta su valor al objetivo restante).
        // 2. Omitir el elemento actual (el objetivo restante se mantiene intacto).
        return backtrackingSuma(opcionales, index + 1, objetivoRestante - opcionales.get(index))
                || backtrackingSuma(opcionales, index + 1, objetivoRestante);
    }
}