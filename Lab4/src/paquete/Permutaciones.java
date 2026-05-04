package paquete;

import java.util.*;

public class Permutaciones {
    
    // Motor recursivo de Backtracking para la generación de permutaciones.
    public static void permutar(int[] arr, List<Integer> actual, boolean[] usado) {
        
        // Caso base (Condición de éxito): La longitud de la ruta actual coincide 
        // con la del conjunto original. Se alcanzó una permutación completa.
        if (actual.size() == arr.length) {
            System.out.println(actual);
            return;
        }

        // Exploración de ramas: Se itera sobre todos los elementos del conjunto original.
        for (int i = 0; i < arr.length; i++) {
            
            // Poda de ramas: Solo se procesan los elementos que no están presentes 
            // en la permutación actual (no visitados).
            if (!usado[i]) { 
                
                // Fase de selección (Forward tracking): Se marca el elemento como visitado 
                // y se añade a la secuencia actual.
                usado[i] = true;        
                actual.add(arr[i]); 
                
                // Llamada recursiva: Descenso al siguiente nivel del árbol de estados.
                permutar(arr, actual, usado); 
                
                // Fase de retroceso (Backtracking): Se deshace la selección previa.
                // Esto libera el elemento para que pueda ser utilizado en ramas paralelas.
                actual.remove(actual.size() - 1); 
                usado[i] = false;                 
            }
        }
    }

    public static void main(String[] args) {
        // Conjunto de entrada a permutar.
        int[] arr = {1, 2, 3};
        
        // Arreglo de estado paralelo para rastrear la disponibilidad de cada elemento en O(1).
        boolean[] usado = new boolean[arr.length];
        
        // Llamada inicial al motor recursivo con una lista vacía.
        permutar(arr, new ArrayList<>(), usado);
    }
}