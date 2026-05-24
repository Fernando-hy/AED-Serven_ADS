package paquete;

import exceptions.ItemDuplicated;
import exceptions.ItemNotFound;
import exceptions.ExceptionIsEmpty;

/**
 * Ejercicio 7 del laboratorio: Validación completa de inserciones y eliminaciones
 * que provocan rebalanceos en cascada en un Árbol AVL.
 * Muestra paso a paso el estado físico del árbol ante cada operación
 * para comprobar el correcto funcionamiento matemático del algoritmo AVL.
 */
public class Ejercicio7 {
    public static void main(String[] args) {

        AVLTree<Integer> avl = new AVLTree<>();

        // --- PRUEBA DE INSERCIONES DE NÚMEROS ---
        System.out.println("=================================================");
        System.out.println("  FASE 1: INSERCIÓN DE NÚMEROS Y ROTACIONES  ");
        System.out.println("=================================================");
        int[] insertar = {50, 30, 70, 20, 40, 60, 80, 10, 25, 35, 45};
        for (int v : insertar) {
            try {
                System.out.println("\n--- Insertando valor: " + v + " ---");
                avl.insert(v);
                avl.printTree();
            } catch (ItemDuplicated e) {
                System.out.println("[!] Error: " + e.getMessage());
            }
        }

        // Resumen del árbol resultante tras todas las inserciones
        System.out.println("\n=================================================");
        System.out.println("  ESTADO DEL ÁRBOL AVL TRAS LAS INSERCIONES  ");
        System.out.println("=================================================");
        System.out.print("Inorden final:  "); avl.inOrder();
        System.out.print("Preorden final: "); avl.preOrder();
        System.out.println("Altura actual:  " + avl.height());

        // --- PRUEBA DE ELIMINACIONES SUCESIVAS ---
        System.out.println("\n=================================================");
        System.out.println("  FASE 2: ELIMINACIÓN Y ROTACIONES DE BALANCEO  ");
        System.out.println("=================================================");
        int[] eliminar = {10, 80, 30, 50};
        for (int v : eliminar) {
            try {
                System.out.println("\n--- Eliminando valor: " + v + " ---");
                avl.delete(v);
                avl.printTree();
                System.out.print("Inorden actual: "); avl.inOrder();
            } catch (ExceptionIsEmpty | ItemNotFound e) {
                System.out.println("[!] Error al eliminar: " + e.getMessage());
            }
        }

        // Resumen final de la estructura AVL
        System.out.println("\n=================================================");
        System.out.println("  ESTADO FINAL DEL ÁRBOL AVL  ");
        System.out.println("=================================================");
        avl.printTree();
        System.out.print("Inorden final: "); avl.inOrder();
        System.out.println("Altura final:  " + avl.height());
    }
}