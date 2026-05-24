package paquete;
import exceptions.ItemDuplicated;

/**
 * Ejercicio 4 del laboratorio: Recorrido por Amplitud (BFS - Breadth First Search).
 * Realiza pruebas exhaustivas de recorridos sobre múltiples árboles AVL para comprobar
 * la consistencia de:
 * 1) Recorrido por amplitud recursivo (bfsRecursivo)
 * 2) Recorrido por amplitud iterativo usando colas (bfsCola)
 * 3) Recorridos clásicos (Inorden, Preorden, Postorden)
 */
public class Ejercicio4BFS {
    public static void main(String[] args) {

        // =====================================================================
        // PRUEBA CON EL ÁRBOL AVL 1
        // =====================================================================
        System.out.println("=================================================");
        System.out.println("  ÁRBOL DE PRUEBA 1  ");
        System.out.println("=================================================");
        AVLTree<Integer> avl1 = new AVLTree<>();
        int[] vals1 = {50, 30, 70, 20, 40, 60, 80, 10, 25};
        for (int v : vals1) {
            try { avl1.insert(v); }
            catch (ItemDuplicated e) { System.out.println("[!] " + e.getMessage()); }
        }
        avl1.printTree();
        System.out.println("\nRecorrido por amplitud (BFS Recursivo):");
        avl1.bfsRecursivo();
        System.out.print("Inorden:  "); avl1.inOrder();
        System.out.print("Preorden: "); avl1.preOrder();

        // =====================================================================
        // PRUEBA CON EL ÁRBOL AVL 2
        // =====================================================================
        System.out.println("\n=================================================");
        System.out.println("  ÁRBOL DE PRUEBA 2  ");
        System.out.println("=================================================");
        AVLTree<Integer> avl2 = new AVLTree<>();
        int[] vals2 = {10, 20, 30, 40, 50, 60, 70};
        for (int v : vals2) {
            try { avl2.insert(v); }
            catch (ItemDuplicated e) { System.out.println("[!] " + e.getMessage()); }
        }
        avl2.printTree();
        System.out.println("\nRecorrido por amplitud (BFS Recursivo):");
        avl2.bfsRecursivo();
        System.out.print("Inorden:  "); avl2.inOrder();
        System.out.print("Preorden: "); avl2.preOrder();
        
        // =====================================================================
        // PRUEBA ADICIONAL 5
        // =====================================================================
        System.out.println("\n=================================================");
        System.out.println("  ÁRBOL ADICIONAL DE PRUEBA 3  ");
        System.out.println("=================================================");
        AVLTree<Integer> avl = new AVLTree<>();
        int[] vals = {50, 30, 70, 20, 40, 60, 80, 10, 25, 65};
        for (int v : vals) {
            try { avl.insert(v); }
            catch (ItemDuplicated e) { System.out.println("[!] " + e.getMessage()); }
        }
        avl.printTree();
        System.out.println("\nRecorrido por amplitud (BFS Recursivo):");
        avl.bfsRecursivo();
        
        // =====================================================================
        // PRUEBA EJERCICIO 5 (BFS con Cola)
        // =====================================================================
        System.out.println("\n=================================================");
        System.out.println("  EJERCICIO 5: RECORRIDO POR AMPLITUD USANDO COLAS  ");
        System.out.println("=================================================");
        AVLTree<Integer> avl3 = new AVLTree<>();
        int[] vals3 = {50, 30, 70, 20, 40, 60, 80, 10, 25, 65};
        for (int v : vals3) {
            try { avl3.insert(v); }
            catch (ItemDuplicated e) { System.out.println("[!] " + e.getMessage()); }
        }
        avl3.printTree();
        System.out.print("BFS con cola (Iterativo O(n)): ");
        avl3.bfsCola();
        
        // =====================================================================
        // PRUEBA EJERCICIO 6 (Comparación de Recorridos en Profundidad)
        // =====================================================================
        System.out.println("\n=================================================");
        System.out.println("  EJERCICIO 6: COMPARATIVA DE RECORRIDOS PROFUNDOS  ");
        System.out.println("=================================================");
        AVLTree<Integer> avl4 = new AVLTree<>();
        int[] vals4 = {50, 30, 70, 20, 40, 60, 80, 10, 25};
        for (int v : vals4) {
            try { avl4.insert(v); }
            catch (ItemDuplicated e) { System.out.println("[!] " + e.getMessage()); }
        }
        avl4.printTree();
        System.out.print("Preorden  (Raíz - Izq - Der): "); avl4.preOrder();
        System.out.print("Inorden   (Izq - Raíz - Der): "); avl4.inOrder();
        System.out.print("Postorden (Izq - Der - Raíz): "); avl4.postOrder();

        // =====================================================================
        // PRUEBA DE RECORRIDOS CON ÁRBOL 2
        // =====================================================================
        System.out.println("\n=================================================");
        System.out.println("  COMPARATIVA EN ÁRBOL 2  ");
        System.out.println("=================================================");
        AVLTree<Integer> avl5 = new AVLTree<>();
        int[] vals5 = {10, 20, 30, 40, 50};
        for (int v : vals5) {
            try { avl5.insert(v); }
            catch (ItemDuplicated e) { System.out.println("[!] " + e.getMessage()); }
        }
        avl5.printTree();
        System.out.print("Preorden:  "); avl5.preOrder();
        System.out.print("Inorden:   "); avl5.inOrder();
        System.out.print("Postorden: "); avl5.postOrder();
    }
}