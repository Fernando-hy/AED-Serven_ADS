package bstreelinklistinterfgeneric;

import exceptions.ItemDuplicated;
import exceptions.ExceptionIsEmpty;
import exceptions.ItemNotFound;

public class Prueba {
    public static void main(String[] args) {
        LinkedBST<Integer> miArbol = new LinkedBST<>();
        int[] valores = {15, 8, 22, 5, 12, 18, 30};
        System.out.println("Insertando valores: 15, 8, 22, 5, 12, 18, 30...");

        for (int v : valores) {
            try {
                miArbol.insert(v);
            } catch (ItemDuplicated e) {
                System.out.println("Error: " + e.getMessage());
            }
        }

        System.out.print("InOrder:   ");
        miArbol.inOrder(miArbol.getRoot());

        System.out.print("\nPreOrder:  ");
        miArbol.preOrder(miArbol.getRoot());

        System.out.print("\nPostOrder: ");
        miArbol.postOrder(miArbol.getRoot());
        System.out.println();

        probarBusqueda(miArbol, 12);
        probarBusqueda(miArbol, 21);

        try {
            System.out.println("\nValor Mínimo: " + miArbol.findMinNode(miArbol.getRoot()));
            System.out.println("Valor Máximo: " + miArbol.findMaxNode(miArbol.getRoot()));
        } catch (ItemNotFound e) {
            System.out.println(e.getMessage());
        }

        try {
            System.out.println("Eliminando el 8 (nodo con dos hijos)...");
            miArbol.delete(8);
            System.out.print("InOrder tras eliminar 8: ");
            miArbol.inOrder(miArbol.getRoot());
            System.out.println();
        } catch (Exception e) {
            System.out.println("Error al eliminar: " + e.getMessage());
        }

        //3
        System.out.println("misma area");
        LinkedBST<Integer> bst1 = new LinkedBST<>();
        LinkedBST<Integer> bst2 = new LinkedBST<>();

        try {
            bst1.insert(8);
            bst1.insert(3);
            bst1.insert(1);
            bst1.insert(5);
            bst1.insert(20);
            bst1.insert(10);
            bst1.insert(4);

            bst2.insert(15);
            bst2.insert(8);
            bst2.insert(22);
            bst2.insert(5);
            bst2.insert(12);
            bst2.insert(18);
            bst2.insert(30);
        } catch (ItemDuplicated e) {
            System.out.println("Error: " + e.getMessage());
        }

        System.out.println("Área BST1: " + bst1.areaBST());
        System.out.println("Área BST2: " + bst2.areaBST());
        System.out.println("¿Misma área?: " + sameArea(bst1, bst2));
    
    
        //4
    	System.out.println("wwwwwww");
    	miArbol.parenthesize();
    	
    	
    	//5
    	System.out.println("wwwwww");
    	LinkedBST<Integer> inventario = new LinkedBST<>();

    	try {
    	    inventario.insert(100);
    	    inventario.insert(50);
    	    inventario.insert(150);
    	    inventario.insert(30);
    	    inventario.insert(70);
    	    inventario.insert(120);
    	    inventario.insert(200);
    	} catch (ItemDuplicated e) {
    	    System.out.println("Error: " + e.getMessage());
    	}

    	inventario.searchRange(50, 150);
    	System.out.println("Nodos hoja: " + inventario.countLeaves());
    	System.out.print("Descendente: ");
    	inventario.printDescending(inventario.getRoot());
    	System.out.println();
    }

    private static void probarBusqueda(LinkedBST<Integer> arbol, int valor) {
        try {
            boolean encontrado = arbol.search(valor);
            System.out.println("Buscar " + valor + ": " + (encontrado ? "Encontrado" : "No encontrado"));
        } catch (ItemNotFound e) {
            System.out.println("Buscar " + valor + ": " + e.getMessage());
        }
    }

    public static boolean sameArea(LinkedBST<Integer> bst1, LinkedBST<Integer> bst2) {
        return bst1.areaBST() == bst2.areaBST();
    } 
}