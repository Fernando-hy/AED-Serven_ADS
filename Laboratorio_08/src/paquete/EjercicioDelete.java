package paquete;

import exceptions.ItemDuplicated;
import exceptions.ItemNotFound;
import exceptions.ExceptionIsEmpty;

/**
 * Ejercicio de Validación del Algoritmo de Eliminación (Delete) en Árboles AVL.
 * Comprueba de forma estructurada los tres grandes casos teóricos de eliminación de nodos:
 * - Caso 1: Eliminación de un nodo hoja.
 * - Caso 2: Eliminación de un nodo que tiene un único hijo.
 * - Caso 3: Eliminación de un nodo con dos hijos (requiere buscar el sucesor inorden).
 * - Caso Especial: Eliminación que propaga un desbalance y causa rotación AVL.
 */
public class EjercicioDelete {
    public static void main(String[] args) {

        AVLTree<Integer> avl = new AVLTree<>();
        int[] valores = {40, 20, 60, 10, 30, 50, 70, 5, 15, 25, 35};

        // Rellenar el árbol AVL inicial
        for (int v : valores) {
            try { 
                avl.insert(v); 
            } catch (ItemDuplicated e) { 
                System.out.println("[!] " + e.getMessage()); 
            }
        }

        System.out.println("=================================================");
        System.out.println("  ÁRBOL AVL INICIAL DE PRUEBA DE ELIMINACIONES  ");
        System.out.println("=================================================");
        avl.printTree();
        System.out.print("Inorden inicial: "); avl.inOrder();

        // --- CASO 1: ELIMINACIÓN DE HOJAS ---
        eliminar(avl, 5,  "Caso 1 - Eliminación de nodo HOJA izquierdo");
        eliminar(avl, 35, "Caso 1 - Eliminación de nodo HOJA derecho");

        // --- CASO 2: ELIMINACIÓN DE NODO CON UN HIJO ---
        eliminar(avl, 15, "Caso 2 - Nodo con UN HIJO único");

        // --- CASO 3: ELIMINACIÓN DE NODO CON DOS HIJOS ---
        eliminar(avl, 20, "Caso 3 - Nodo con DOS HIJOS (reemplazo por sucesor)");

        // --- CASO EXTRA: ELIMINACIÓN QUE PROVOCA ROTACIÓN DE BALANCEO ---
        eliminar(avl, 60, "Caso 3 - Nodo con dos hijos que provoca rebalanceo (rotación)");

        // --- ELIMINACIÓN DE LA RAÍZ ---
        eliminar(avl, 40, "Caso Especial - Eliminación de la RAÍZ principal del árbol");
    }

    /**
     * Helper estático para encapsular la llamada de eliminación, imprimir la descripción,
     * mostrar el árbol resultante y capturar posibles excepciones.
     */
    static void eliminar(AVLTree<Integer> avl, int v, String descripcion) {
        try {
            System.out.println("\n-------------------------------------------------");
            System.out.println(" ACCIÓN: " + descripcion);
            System.out.println(" Eliminando elemento: " + v);
            System.out.println("-------------------------------------------------");
            avl.delete(v);
            avl.printTree();
            System.out.print("Inorden actual: "); avl.inOrder();
        } catch (ExceptionIsEmpty | ItemNotFound e) {
            System.out.println("[!] ERROR al eliminar " + v + ": " + e.getMessage());
        }
    }
}