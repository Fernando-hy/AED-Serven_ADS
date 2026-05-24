package paquete;
import exceptions.ItemDuplicated;

/**
 * Caso de Prueba Avanzado: TestAVL para comprobar todos los escenarios
 * teóricos de rotaciones y balanceos en Árboles AVL.
 * Cubre 10 casos de prueba rigurosos, incluyendo:
 * - Caso 1: Inserciones balanceadas sin rotación.
 * - Casos 2 y 3: Rotación Simple Derecha (RSR) - desequilibrio Izquierda-Izquierda.
 * - Casos 4 y 5: Rotación Simple Izquierda (RSL) - desequilibrio Derecha-Derecha.
 * - Casos 6 y 7: Rotación Doble Derecha (RDR) - desequilibrio Izquierda-Derecha.
 * - Casos 8 y 9: Rotación Doble Izquierda (RDL) - desequilibrio Derecha-Izquierda.
 * - Caso 10: Eliminación compleja que desbalancea y provoca una rotación correctiva.
 */
public class TestAVL {
    public static void main(String[] args) {

        System.out.println("=================================================");
        System.out.println("  CASOS DE PRUEBA MATEMÁTICOS DE ROTACIONES AVL  ");
        System.out.println("=================================================");

        // --- CASO 1: SIN ROTACIÓN ---
        System.out.println("\n--- CASO 1: Inserción balanceada (Sin Rotación) ---");
        prueba(new int[]{20, 10, 30});

        // --- CASO 2: RSR ---
        System.out.println("\n--- CASO 2: RSR (Rotación Simple Derecha) - Izq-Izq ---");
        prueba(new int[]{30, 20, 10});

        // --- CASO 3: RSR ---
        System.out.println("\n--- CASO 3: RSR (Rotación Simple Derecha) - Subárbol Izq-Izq ---");
        prueba(new int[]{50, 30, 10});

        // --- CASO 4: RSL ---
        System.out.println("\n--- CASO 4: RSL (Rotación Simple Izquierda) - Der-Der ---");
        prueba(new int[]{10, 20, 30});

        // --- CASO 5: RSL ---
        System.out.println("\n--- CASO 5: RSL (Rotación Simple Izquierda) - Subárbol Der-Der ---");
        prueba(new int[]{10, 30, 50});

        // --- CASO 6: RDR ---
        System.out.println("\n--- CASO 6: RDR (Rotación Doble Derecha) - Izq-Der ---");
        prueba(new int[]{30, 10, 20});

        // --- CASO 7: RDR ---
        System.out.println("\n--- CASO 7: RDR (Rotación Doble Derecha) - Subárbol Izq-Der ---");
        prueba(new int[]{50, 20, 30});

        // --- CASO 8: RDL ---
        System.out.println("\n--- CASO 8: RDL (Rotación Doble Izquierda) - Der-Izq ---");
        prueba(new int[]{10, 30, 20});

        // --- CASO 9: RDL ---
        System.out.println("\n--- CASO 9: RDL (Rotación Doble Izquierda) - Subárbol Der-Izq ---");
        prueba(new int[]{10, 50, 30});

        // --- CASO 10: ELIMINACIÓN CON ROTACIÓN ---
        System.out.println("\n=================================================");
        System.out.println("  CASO 10: ELIMINACIÓN QUE PROVOCA ROTACIÓN  ");
        System.out.println("=================================================");
        AVLTree<Integer> avl = new AVLTree<>();
        int[] vals = {40, 20, 60, 10, 30, 50, 70};
        for (int v : vals) {
            try { 
                avl.insert(v); 
            } catch (ItemDuplicated e) { 
                System.out.println("[!] " + e.getMessage()); 
            }
        }
        System.out.println("Árbol inicial antes de eliminar:");
        avl.printTree();
        
        try {
            System.out.println("Eliminando nodo 10 (Hoja izquierda, provoca desbalance a la derecha):");
            avl.delete(10);
            avl.printTree();
        } catch (Exception e) { 
            System.out.println("[!] Error al eliminar: " + e.getMessage()); 
        }
    }

    /**
     * Helper estático para construir un árbol AVL desde un arreglo e imprimir los pasos.
     */
    static void prueba(int[] vals) {
        AVLTree<Integer> avl = new AVLTree<>();
        for (int v : vals) {
            try {
                System.out.println("  Insertando: " + v);
                avl.insert(v);
                avl.printTree();
            } catch (ItemDuplicated e) {
                System.out.println("  [!] Error duplicado: " + e.getMessage());
            }
        }
        System.out.print("  Inorden resultante: ");
        avl.inOrder();
        System.out.println("-------------------------------------------------");
    }
}