package paquete;

import java.util.*;

public class Subconjuntos {
    
    // Motor recursivo para la generación combinatoria mediante un árbol binario de decisiones.
    public static void generarSubconjuntos(int[] arr, List<Integer> actual, int i) {
        
        // Caso base (Hoja del árbol): El índice de exploración alcanzó la longitud del arreglo.
        // Significa que se ha tomado una decisión (incluir/excluir) para todos los elementos.
        if (i == arr.length) {
            System.out.println(actual);
            return;
        }
        
        // Rama 1 (Inclusión): Se agrega el elemento actual al subconjunto.
        actual.add(arr[i]);
        
        // Llamada recursiva avanzando al siguiente nivel (siguiente índice).
        generarSubconjuntos(arr, actual, i + 1);
        
        // Backtracking (Retroceso): Se elimina el elemento recién agregado.
        // Esto restaura el estado de la lista para permitir la exploración de la ruta alternativa.
        actual.remove(actual.size() - 1);
        
        // Rama 2 (Exclusión): Se avanza al siguiente nivel sin incluir el elemento actual.
        generarSubconjuntos(arr, actual, i + 1);
    }
    
    public static void main(String[] args) {
        // Conjunto de entrada.
        int[] arr = {1, 2, 3};
        
        // Despliegue del algoritmo iniciando desde el índice 0 con una estructura vacía.
        generarSubconjuntos(arr, new ArrayList<>(), 0);
    }
}