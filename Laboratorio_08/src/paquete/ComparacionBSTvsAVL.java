package paquete;

import exceptions.ItemDuplicated;
import exceptions.ItemNotFound;

/**
 * Clase que realiza una comparación empírica y visual entre un Árbol BST estándar
 * y un Árbol AVL bajo dos escenarios diferentes:
 * 1) Inserción de una secuencia totalmente ordenada (caso peor para BST donde se degenera en lista).
 * 2) Inserción de una secuencia aleatoria.
 * Muestra el contraste de alturas y eficiencia en la búsqueda.
 */
public class ComparacionBSTvsAVL {
    public static void main(String[] args) {	
        
        // =====================================================================
        // CASO 1: SECUENCIA TOTALMENTE ORDENADA (Peor caso para un BST estándar)
        // =====================================================================
        System.out.println("=================================================");
        System.out.println("CASO 1: SECUENCIA ORDENADA DE MENOR A MAYOR");
        System.out.println("Secuencia de entrada: 10, 20, 30, 40, 50");
        System.out.println("=================================================");

        BSTree<Integer> bst1 = new BSTree<>();
        AVLTree<Integer> avl1 = new AVLTree<>();
        int[] sec1 = {10, 20, 30, 40, 50};

        // Inserción en ambas estructuras
        for (int v : sec1) {
            try { 
                bst1.insert(v); 
                avl1.insert(v); 
            } catch (ItemDuplicated e) { 
                System.out.println("[!] " + e.getMessage()); 
            }
        }

        // Mostrar estructura del BST (debe observarse una línea diagonal derecha)
        System.out.println("\n--- ÁRBOL BST (Sufre degeneramiento en lista) ---");
        bst1.printTree();
        System.out.print("Inorden BST: "); bst1.inOrder();
        System.out.println("Altura BST: " + bst1.height());

        // Mostrar estructura del AVL (debe observarse balanceado gracias a rotaciones RSL)
        System.out.println("\n--- ÁRBOL AVL (Se mantiene balanceado, O(log n)) ---");
        avl1.printTree();
        System.out.print("Inorden AVL: "); avl1.inOrder();
        System.out.println("Altura AVL: " + avl1.height());

        // Búsqueda en ambos árboles
        System.out.println("\n🔍 Buscando elemento 30:");
        try { 
            System.out.println("  BST: " + (bst1.search(30) ? "Encontrado" : "No encontrado")); 
        } catch (ItemNotFound e) { 
            System.out.println("  BST: " + e.getMessage()); 
        }
        
        try { 
            System.out.println("  AVL: " + (avl1.search(30) ? "Encontrado" : "No encontrado")); 
        } catch (ItemNotFound e) { 
            System.out.println("  AVL: " + e.getMessage()); 
        }

        // =====================================================================
        // CASO 2: SECUENCIA DE ENTRADA ALEATORIA
        // =====================================================================
        System.out.println("\n=================================================");
        System.out.println("CASO 2: SECUENCIA ALEATORIA");
        System.out.println("Secuencia de entrada: 50, 20, 70, 10, 30, 60, 80");
        System.out.println("=================================================");

        BSTree<Integer> bst2 = new BSTree<>();
        AVLTree<Integer> avl2 = new AVLTree<>();
        int[] sec2 = {50, 20, 70, 10, 30, 60, 80};

        // Inserción en ambas estructuras
        for (int v : sec2) {
            try { 
                bst2.insert(v); 
                avl2.insert(v); 
            } catch (ItemDuplicated e) { 
                System.out.println("[!] " + e.getMessage()); 
            }
        }

        // Mostrar estructura BST
        System.out.println("\n--- ÁRBOL BST ---");
        bst2.printTree();
        System.out.print("Inorden BST: "); bst2.inOrder();
        System.out.println("Altura BST: " + bst2.height());

        // Mostrar estructura AVL
        System.out.println("\n--- ÁRBOL AVL ---");
        avl2.printTree();
        System.out.print("Inorden AVL: "); avl2.inOrder();
        System.out.println("Altura AVL: " + avl2.height());

        // Búsqueda en ambos
        System.out.println("\n🔍 Buscando elemento 60:");
        try { 
            System.out.println("  BST: " + (bst2.search(60) ? "Encontrado" : "No encontrado")); 
        } catch (ItemNotFound e) { 
            System.out.println("  BST: " + e.getMessage()); 
        }
        
        try { 
            System.out.println("  AVL: " + (avl2.search(60) ? "Encontrado" : "No encontrado")); 
        } catch (ItemNotFound e) { 
            System.out.println("  AVL: " + e.getMessage()); 
        }

        // --- CONCLUSIÓN FINAL ---
        System.out.println("\n================ RESUMEN DE ALTURAS ================");
        System.out.println("Caso 1 (Secuencia Ordenada)  - Altura BST: " + bst1.height() + " | Altura AVL: " + avl1.height());
        System.out.println("Caso 2 (Secuencia Aleatoria) - Altura BST: " + bst2.height() + " | Altura AVL: " + avl2.height());
        System.out.println("----------------------------------------------------");
        System.out.println("Conclusión: En el Caso 1, el BST estándar degenera completamente");
        System.out.println("en una lista enlazada (altura = " + bst1.height() + "), perdiendo eficiencia O(log n).");
        System.out.println("En cambio, el AVL mantiene una altura óptima y balanceada (altura = " + avl1.height() + ")");
        System.out.println("gracias a las autorotaciones instantáneas en la inserción.");
        System.out.println("====================================================");
    }
}