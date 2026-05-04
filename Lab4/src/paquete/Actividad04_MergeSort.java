package paquete;

import java.util.Arrays;

public class Actividad04_MergeSort {

    public static void main(String[] args) {
        // Definición de casos de prueba con arreglos de distintas dimensiones.
        int[] arr5 = {38, 27, 43, 3, 9};
        int[] arr8 = {8, 3, 5, 2, 9, 1, 4, 7};
        int[] arr10 = {100, 23, 45, 12, 8, 99, 54, 31, 2, 77};

        ordenarYMostrar(arr5, "5 elementos");
        ordenarYMostrar(arr8, "8 elementos");
        ordenarYMostrar(arr10, "10 elementos");
    }

    // Método envolvente para separar la salida por consola de la lógica de ordenamiento.
    private static void ordenarYMostrar(int[] arr, String etiqueta) {
        System.out.println("Arreglo de " + etiqueta);
        System.out.println("Antes: " + Arrays.toString(arr));
        
        mergeSort(arr, 0, arr.length - 1);
        
        System.out.println("Después: " + Arrays.toString(arr) + "\n");
    }

    // Implementación recursiva del paradigma "Divide y Vencerás" (Fase de división).
    public static void mergeSort(int[] arr, int left, int right) {
        // Caso base: se detiene si el subarreglo tiene 1 o 0 elementos.
        if (left < right) {
            
            // Cálculo del punto medio. Optimizado para evitar desbordamiento de enteros.
            int mid = left + (right - left) / 2;

            // División recursiva en mitades izquierda y derecha.
            mergeSort(arr, left, mid);
            mergeSort(arr, mid + 1, right);
            
            // Llamada al método de fusión una vez alcanzado el nivel de unidad.
            merge(arr, left, mid, right);   
        }
    }

    // Fase de conquista. Fusiona dos subarreglos ordenados en uno solo.
    private static void merge(int[] arr, int left, int mid, int right) {
        int n1 = mid - left + 1;
        int n2 = right - mid;

        // Complejidad espacial O(n): requiere instanciar arreglos temporales.
        int[] L = new int[n1];
        int[] R = new int[n2];
        
        // Copia de los elementos a los arreglos auxiliares.
        for (int i = 0; i < n1; ++i) L[i] = arr[left + i];
        for (int j = 0; j < n2; ++j) R[j] = arr[mid + 1 + j];

        int i = 0, j = 0, k = left;
        
        // Comparación iterativa: el elemento menor se asigna al arreglo original.
        while (i < n1 && j < n2) {
            if (L[i] <= R[j]) {
                arr[k] = L[i++]; 
            } else {
                arr[k] = R[j++]; 
            }
            k++;
        }

        // Vaciado de elementos residuales en caso de tamaños asimétricos.
        while (i < n1) arr[k++] = L[i++];
        while (j < n2) arr[k++] = R[j++];
    }
}