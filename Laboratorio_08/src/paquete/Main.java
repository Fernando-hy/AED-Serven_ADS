package paquete;

import exceptions.ItemDuplicated;
import exceptions.ItemNotFound;
import exceptions.ExceptionIsEmpty;

/**
 * Clase principal original de pruebas de Laboratorio.
 * Contiene:
 * - Actividad 1: Inserción sucesiva de valores en árbol AVL imprimiendo
 *   el árbol tras cada paso para validar las rotaciones automáticas.
 * - Actividad 2: Inserciones masivas seguidas de eliminaciones selectivas
 *   de nodos hoja y raíz, evaluando los factores de balanceo.
 */
public class Main {
    public static void main(String[] args) {
        
        // =====================================================================
        // ACTIVIDAD 1: INSERCIÓN SUCESIVA Y ROTACIONES
        // =====================================================================
        System.out.println("=================================================");
        System.out.println("  ACTIVIDAD 1: CONSTRUCCIÓN PASO A PASO AVL  ");
        System.out.println("=================================================");
        AVLTree<Integer> avl = new AVLTree<>();
        int[] valores = {30, 15, 20, 50, 40, 60, 70, 10, 25, 45, 55, 65, 75};

        for (int v : valores) {
            try {
                System.out.println("📥 Insertando valor: " + v);
                avl.insert(v);
                avl.printTree();
                System.out.println();
            } catch (ItemDuplicated e) {
                System.out.println("[!] Error duplicado: " + e.getMessage());
            }
        }
    
        // =====================================================================
        // ACTIVIDAD 2: INSERCIÓN MASIVA Y ELIMINACIONES MÚLTIPLES
        // =====================================================================
        System.out.println("\n=================================================");
        System.out.println("  ACTIVIDAD 2: INSERCIÓN MASIVA Y ELIMINACIÓN  ");
        System.out.println("=================================================");
        AVLTree<Integer> avl1 = new AVLTree<>();
        int[] valores1 = {33,20,45,12,26,41,56,6,15,24,35,44,48,59,17,38,46,53,65,50};
	
        // Realizar inserciones masivas
        for (int va : valores1) {
            try {
                avl1.insert(va);
            } catch (ItemDuplicated e) {
                System.out.println("[!] Error duplicado: " + e.getMessage());
            }
        }
	
        System.out.println("🌳 Árbol AVL Inicial (Actividad 2):");
        avl1.printTree();
        System.out.println();
	
        // Valores seleccionados para ser eliminados uno tras otro
        int[] eliminar = {12, 33, 46, 59, 45, 56};
        for (int v : eliminar) {
            try {
                System.out.println("📤 Eliminando valor: " + v);
                avl1.delete(v);
                avl1.printTree();
                System.out.println();
            } catch (Exception e) {
                System.out.println("[!] Error al eliminar: " + e.getMessage());
            }
        }
	}
}
