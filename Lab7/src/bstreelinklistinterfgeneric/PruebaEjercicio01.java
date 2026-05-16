package bstreelinklistinterfgeneric;

import exceptions.ItemDuplicated;
import exceptions.ItemNotFound;

public class PruebaEjercicio01 {
    public static void main(String[] args) {
        LinkedBST<Integer> bst = new LinkedBST<>();
        int[] datos = {15, 8, 22, 5, 12, 18, 30};

        for (int x : datos) {
            try {
                bst.insert(x);
            } catch (ItemDuplicated e) {}
        }

        System.out.print("InOrden: ");
        bst.inOrder(bst.getRoot());
        System.out.print("\nPreOrden: ");
        bst.preOrder(bst.getRoot());
        System.out.print("\nPostOrder: ");
        bst.postOrder(bst.getRoot());

        System.out.println("\n\nBusqueda 12:");
        buscarPasoAPaso(bst, 12);
        System.out.println("\nBusqueda 21:");
        buscarPasoAPaso(bst, 21);

        try {
            System.out.println("\nAltura: " + calcularAltura(bst.getRoot()));
            System.out.println("Hojas: " + contarHojas(bst.getRoot()));
            System.out.println("Minimo: " + bst.findMinNode(bst.getRoot()));
            System.out.println("Maximo: " + bst.findMaxNode(bst.getRoot()));
        } catch (ItemNotFound e) {}
    }

    public static void buscarPasoAPaso(LinkedBST<Integer> bst, Integer x) {
        Node<Integer> aux = bst.getRoot();
        while (aux != null) {
            System.out.println("Comparando " + x + " con " + aux.getData());
            int comp = x.compareTo(aux.getData());
            if (comp == 0) {
                System.out.println("Encontrado");
                return;
            }
            aux = (comp < 0) ? aux.getLeft() : aux.getRight();
        }
        System.out.println("No encontrado (null)");
    }

    public static int calcularAltura(Node<Integer> n) {
        if (n == null) return -1;
        return 1 + Math.max(calcularAltura(n.getLeft()), calcularAltura(n.getRight()));
    }

    public static int contarHojas(Node<Integer> n) {
        if (n == null) return 0;
        if (n.getLeft() == null && n.getRight() == null) return 1;
        return contarHojas(n.getLeft()) + contarHojas(n.getRight());
    }
}