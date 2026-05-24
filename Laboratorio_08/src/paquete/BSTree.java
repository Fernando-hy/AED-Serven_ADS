package paquete;

import exceptions.ItemDuplicated;
import exceptions.ItemNotFound;
import exceptions.ExceptionIsEmpty;

/**
 * Representa un Árbol Binario de Búsqueda (BST - Binary Search Tree).
 * Mantiene la propiedad de ordenamiento donde, para cada nodo,
 * los elementos a la izquierda son menores y los de la derecha son mayores.
 * 
 * @param <E> El tipo de elemento comparable almacenado en el árbol.
 */
public class BSTree<E extends Comparable<E>> {
    protected Node<E> root; // La raíz principal del árbol.

    /**
     * Constructor por defecto que inicializa un árbol vacío.
     */
    public BSTree() {
        this.root = null;
    }

    /**
     * Comprueba si el árbol no contiene ningún elemento.
     * @return true si la raíz es nula.
     */
    public boolean isEmpty() {
        return this.root == null;
    }

    /**
     * Inserta un nuevo elemento en el árbol.
     * @param d El elemento a insertar.
     * @throws ItemDuplicated Si el elemento ya se encuentra en el árbol.
     */
    public void insert(E d) throws ItemDuplicated {
        root = insertRec(root, d);
    }

    /**
     * Método auxiliar recursivo para realizar la inserción respetando el ordenamiento BST.
     * @param n Nodo actual de la recursión.
     * @param d Elemento a insertar.
     * @return El nodo actualizado (generalmente el mismo nodo o uno nuevo).
     * @throws ItemDuplicated Si se detecta un valor duplicado.
     */
    protected Node<E> insertRec(Node<E> n, E d) throws ItemDuplicated {
        if (n == null) return new Node<>(d);
        int comp = d.compareTo(n.getData());
        if (comp > 0) {
            n.setRight(insertRec(n.getRight(), d));
        } else if (comp < 0) {
            n.setLeft(insertRec(n.getLeft(), d));
        } else {
            throw new ItemDuplicated("No se permiten duplicados");
        }
        return n;
    }

    /**
     * Busca un elemento en el árbol de forma recursiva.
     * @param d El elemento a buscar.
     * @return true si el elemento se encuentra, false si no.
     * @throws ItemNotFound Si el árbol está completamente vacío.
     */
    public boolean search(E d) throws ItemNotFound {
        if (root == null) throw new ItemNotFound("El árbol está vacío");
        return searchRec(root, d);
    }

    /**
     * Método auxiliar recursivo para realizar la búsqueda O(log n) promedio.
     * @param n Nodo actual examinado.
     * @param d Elemento buscado.
     * @return true si se encuentra en este subárbol.
     */
    protected boolean searchRec(Node<E> n, E d) {
        if (n == null) return false;
        int comp = d.compareTo(n.getData());
        if (comp > 0) return searchRec(n.getRight(), d);
        else if (comp < 0) return searchRec(n.getLeft(), d);
        else return true;
    }

    /**
     * Elimina un elemento del árbol BST de manera recursiva.
     * @param d El elemento a eliminar.
     * @throws ExceptionIsEmpty Si el árbol se encuentra vacío.
     * @throws ItemNotFound Si el elemento no existe en el árbol.
     */
    public void delete(E d) throws ExceptionIsEmpty, ItemNotFound {
        if (root == null) throw new ExceptionIsEmpty("El árbol está vacío");
        root = deleteRec(root, d);
    }

    /**
     * Método auxiliar recursivo para realizar la eliminación.
     * Maneja tres casos de eliminación:
     * - Caso 1: El nodo a eliminar es una hoja (sin hijos).
     * - Caso 2: El nodo a eliminar tiene un solo hijo (izquierdo o derecho).
     * - Caso 3: El nodo a eliminar tiene dos hijos (se reemplaza por el sucesor inorden).
     * @param n Nodo actual de la recursión.
     * @param d Elemento a eliminar.
     * @return El nodo actualizado tras la eliminación.
     * @throws ItemNotFound Si el elemento no se encuentra.
     */
    protected Node<E> deleteRec(Node<E> n, E d) throws ItemNotFound {
        if (n == null) 
        	throw new ItemNotFound("El elemento " + d + " no existe");
        int comp = d.compareTo(n.getData());
        if (comp > 0) {
            n.setRight(deleteRec(n.getRight(), d));
        } else if (comp < 0) {
            n.setLeft(deleteRec(n.getLeft(), d));
        } else {
            // Se encontró el nodo a eliminar
            if (n.getLeft() == null && n.getRight() == null) return null; // Caso 1: Hoja
            if (n.getLeft() == null) return n.getRight();                 // Caso 2: Un hijo (derecho)
            if (n.getRight() == null) return n.getLeft();                // Caso 2: Un hijo (izquierdo)
            
            // Caso 3: Dos hijos. Buscamos el nodo mínimo del subárbol derecho (sucesor inorden)
            E sucesor = findMinNode(n.getRight());
            n.setData(sucesor); // Reemplazamos el dato por el del sucesor
            n.setRight(deleteRec(n.getRight(), sucesor)); // Eliminamos recursivamente al sucesor inorden
        }
        return n;
    }

    /**
     * Busca recursivamente el nodo con el valor mínimo en el subárbol dado.
     * @param n Nodo raíz del subárbol.
     * @return El valor mínimo.
     * @throws ItemNotFound Si el nodo de inicio es nulo.
     */
    protected E findMinNode(Node<E> n) throws ItemNotFound {
        if (n == null) throw new ItemNotFound("Nodo nulo");
        while (n.getLeft() != null) n = n.getLeft();
        return n.getData();
    }

    /**
     * Realiza un recorrido Inorden del árbol (Izquierda, Raíz, Derecha).
     * Muestra los elementos del árbol ordenados de menor a mayor.
     */
    public void inOrder() { inOrder(root); System.out.println(); }
    protected void inOrder(Node<E> n) {
        if (n == null) return;
        inOrder(n.getLeft());
        System.out.print(n.getData() + " ");
        inOrder(n.getRight());
    }

    /**
     * Realiza un recorrido Preorden del árbol (Raíz, Izquierda, Derecha).
     */
    public void preOrder() { preOrder(root); System.out.println(); }
    protected void preOrder(Node<E> n) {
        if (n == null) return;
        System.out.print(n.getData() + " ");
        preOrder(n.getLeft());
        preOrder(n.getRight());
    }

    /**
     * Realiza un recorrido Postorden del árbol (Izquierda, Derecha, Raíz).
     */
    public void postOrder() { postOrder(root); System.out.println(); }
    protected void postOrder(Node<E> n) {
        if (n == null) return;
        postOrder(n.getLeft());
        postOrder(n.getRight());
        System.out.print(n.getData() + " ");
    }

    /**
     * Calcula la altura del árbol recursivamente.
     * @return Altura del árbol (cantidad máxima de niveles desde la raíz hasta las hojas).
     */
    public int height() { return heightRec(root); }
    protected int heightRec(Node<E> n) {
        if (n == null) return 0;
        return 1 + Math.max(heightRec(n.getLeft()), heightRec(n.getRight()));
    }

    /**
     * Imprime de forma indentada la estructura jerárquica del árbol en consola.
     * Útil para depurar y visualizar la estructura física de nodos y balanceos.
     */
    public void printTree() { printTree(root, "", true); }
    protected void printTree(Node<E> n, String prefix, boolean isRoot) {
        if (n != null) {
            System.out.println(prefix + (isRoot ? "└── " : "├── ")
                + n.getData() + "(bf=" + n.get_bf() + ")");
            printTree(n.getLeft(),  prefix + "    ", false);
            printTree(n.getRight(), prefix + "    ", false);
        }
    }
}