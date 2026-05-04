package paquete;

import java.util.ArrayList;
import java.util.List;

// Estructura de transferencia de datos (DTO). 
// Almacena índices de subarreglos sin duplicar los datos en memoria.
class Limits {
    int[] a;
    int prim, ult;
    public Limits(int[] a, int prim, int ult) {
        this.a = a;
        this.prim = prim;
        this.ult = ult;
    }
    public int length() { return ult - prim + 1; }
}

// Estructura auxiliar similar a una cola de prioridad.
// Gestiona los segmentos pendientes y extrae siempre el de mayor longitud.
class SetVectors {
    List<Limits> lista = new ArrayList<>();
    public void insertar(Limits p) { lista.add(p); }
    public boolean esVacio() { return lista.isEmpty(); }
    
    public int longMayor() {
        int max = 0;
        for (Limits l : lista) if (l.length() > max) max = l.length();
        return max;
    }
    
    // Devuelve y elimina el subarreglo de mayor tamaño de la lista.
    public Limits mayor() {
        if (lista.isEmpty()) return null;
        Limits maxL = lista.get(0);
        for (Limits l : lista) if (l.length() > maxL.length()) maxL = l;
        lista.remove(maxL); 
        return maxL;
    }
}

public class Actividad05 {

    // Solución 1: Fuerza Bruta.
    // Complejidad O(n^2). Itera sobre cada elemento y realiza un conteo exhaustivo.
    public static int moda1(int array[]) {
        int first = 0;
        int end = array.length - 1;
        if (first == end) return array[first];
        
        int moda = array[first];
        int maxfrec = frecuencia(array, first, end, moda);

        for (int i = first + 1; i < end; i++) {
            int frec = frecuencia(array, i, end, array[i]);
            if (frec > maxfrec) {
                maxfrec = frec;
                moda = array[i];
            }
        }
        return moda;
    }
    
    // Función auxiliar para conteo de ocurrencias de un elemento específico.
    private static int frecuencia(int[] array, int first, int end, int ele) {
        if (first > end) return 0;
        int suma = 0;
        for (int i = first; i <= end; i++)
            if (array[i] == ele) suma++;
        return suma;
    }

    // Solución 2: Búsqueda lineal.
    // Complejidad O(n). Requiere estrictamente que el arreglo de entrada esté ordenado.
    public static int moda2(int array[]) {
        int first = 1; 
        int p = 0; 
        int end = array.length - 1; 
        int moda = array[0];
        int frec = 1; 
        int maxfrec = 0;

        while (first <= end) {
            if (array[p] == array[first]) { 
                frec++;
                first++;
            } else {
                if (frec > maxfrec) {
                    maxfrec = frec;
                    moda = array[p];
                }
                p = first;
                first = p + 1;
                frec = 1; 
            }
        }
        return moda;
    }

    // Solución 3: Divide y Vencerás.
    // Complejidad O(n log n). Clasifica segmentos en homogéneos (iguales) y heterogéneos (mezclados).
    public static int moda3(int[] a, int prim, int ult) {
        SetVectors homogeneo = new SetVectors();
        SetVectors heterogeneo = new SetVectors();
        heterogeneo.insertar(new Limits(a, prim, ult));

        // Condición de parada: detiene el proceso si el segmento homogéneo actual es insuperable.
        while (heterogeneo.longMayor() > homogeneo.longMayor()) {
            Limits p = heterogeneo.mayor(); 
            int mediana = p.a[(p.prim + p.ult) / 2];

            // Retorna los límites del bloque central posterior a la partición.
            int[] limites = pivote2(p.a, mediana, p.prim, p.ult);
            int izq = limites[0];
            int der = limites[1];

            Limits p1 = new Limits(p.a, p.prim, izq - 1); // Subarreglo izquierdo (menores)
            Limits p3 = new Limits(p.a, der, p.ult);      // Subarreglo derecho (mayores)
            Limits p2 = new Limits(p.a, izq, der - 1);    // Subarreglo central (iguales)

            if (p1.prim <= p1.ult) heterogeneo.insertar(p1);
            if (p3.prim <= p3.ult) heterogeneo.insertar(p3);
            if (p2.prim <= p2.ult) homogeneo.insertar(p2);
        }

        Limits pModa = homogeneo.mayor();
        return pModa != null ? pModa.a[pModa.prim] : a[prim];
    }

    // Implementación de partición de 3 vías (Bandera Nacional Holandesa).
    // Evita procesar elementos duplicados agrupándolos en el centro.
    private static int[] pivote2(int[] a, int pivote, int prim, int ult) {
        int i = prim, j = prim, n = ult;
        while (j <= n) {
            if (a[j] < pivote) swap(a, i++, j++);
            else if (a[j] > pivote) swap(a, j, n--);
            else j++; 
        }
        return new int[]{i, j};
    }

    // Intercambio in-place de valores mediante variable temporal.
    private static void swap(int[] a, int i, int j) {
        int temp = a[i];
        a[i] = a[j];
        a[j] = temp;
    }

    public static void main(String[] args) {
        int[] datos = {3, 4, 6, 7, 2, 6, 6};
        System.out.println("Moda 1: " + moda1(datos));
        // Advertencia: moda2 requiere de un arreglo previamente ordenado.
        System.out.println("Moda 3: " + moda3(datos, 0, datos.length - 1));
    }
}