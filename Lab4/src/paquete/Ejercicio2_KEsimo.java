package paquete;

public class Ejercicio2_KEsimo {
    public static void main(String[] args) {
        int[] arr = {4, 2, 7, 10, 4, 17};
        int k = 3; 
        
        // Conversión lógica: Para encontrar el k-ésimo elemento MÁS GRANDE, 
        // se busca el índice equivalente en un arreglo teóricamente ordenado de forma ascendente.
        // Fórmula: índice_objetivo = longitud_total - k.
        int resultado = quickSelect(arr, 0, arr.length - 1, arr.length - k);
        
        System.out.println("El " + k + "° elemento más grande es: " + resultado); // Salida esperada: 7
    }

    // Motor recursivo de selección. Localiza un índice específico sin ordenar todo el arreglo.
    public static int quickSelect(int[] arr, int left, int right, int kIndex) {
        // Caso base: el subarreglo se ha reducido a un único elemento.
        if (left == right) return arr[left];

        // Llamada a la partición. Devuelve la posición definitiva y ordenada del pivote.
        int pivotIndex = particion(arr, left, right);

        // Evaluación de escenarios de recursión:
        if (kIndex == pivotIndex) {
            // Éxito: el pivote cayó exactamente en el índice buscado.
            return arr[kIndex]; 
        } else if (kIndex < pivotIndex) {
            // Descarte de la mitad derecha: el objetivo está en el subarreglo izquierdo.
            return quickSelect(arr, left, pivotIndex - 1, kIndex);
        } else {
            // Descarte de la mitad izquierda: el objetivo está en el subarreglo derecho.
            return quickSelect(arr, pivotIndex + 1, right, kIndex);
        }
    }

    // Algoritmo de partición (Esquema de Lomuto).
    // Segmenta el subarreglo in-place dejando los menores al pivote a su izquierda y los mayores a su derecha.
    private static int particion(int[] arr, int left, int right) {
        // Se selecciona el último elemento del subarreglo como pivote predeterminado.
        int pivot = arr[right];
        
        // El puntero 'i' rastrea el límite superior de los elementos menores al pivote.
        int i = left;
        
        // El puntero 'j' escanea el arreglo linealmente.
        for (int j = left; j < right; j++) {
            if (arr[j] <= pivot) {
                // Intercambio: se mueve el elemento menor a la sección izquierda.
                int temp = arr[i];
                arr[i] = arr[j];
                arr[j] = temp;
                i++; // Se expande el límite de la sección de menores.
            }
        }
        
        // Ubicación final del pivote: se intercambia con el primer elemento de la sección de mayores.
        int temp = arr[i];
        arr[i] = arr[right];
        arr[right] = temp;
        
        // Retorna la posición absoluta del pivote en el arreglo.
        return i;
    }
}