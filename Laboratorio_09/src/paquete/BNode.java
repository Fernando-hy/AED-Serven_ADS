package paquete;

import java.util.ArrayList;

public class BNode<E> {
    private static int nodeCount = 0;              	// contador compartido por todos los nodos
    protected ArrayList<E> keys;                   	// arreglo de claves del nodo
    protected ArrayList<BNode<E>> childs;          	// arreglo de hijos del nodo
    protected int count;                           	// número de claves actualmente en el nodo
    protected int idNode;                          	// identificador único de este nodo

    public BNode(int n) {
        this.keys = new ArrayList<E>(n);           	// capacidad n (n-1 útiles + 1 overflow)
        this.childs = new ArrayList<BNode<E>>(n);  	// capacidad n (mismo tamaño que keys)
        this.count = 0;                            	// nodo recién creado, sin claves
        this.idNode = ++nodeCount;                 	// asigna el siguiente id disponible
        for (int i = 0; i < n; i++) {
            this.keys.add(null);                   	// rellena con nulls para permitir set()
            this.childs.add(null);					// rellena con nulls para permitir set()
        }
    }

    public boolean nodeFull(int maxKeys) {
        return this.count == maxKeys;              	// lleno cuando count alcanza n-1
    }

    public boolean nodeEmpty() {
        return this.count == 0;                    	// vacío cuando no hay ninguna clave
    }

    // retorna true si cl está en este nodo, pos[0] = índice donde se encontró
    // retorna false si no está,pos[0] = índice del hijo donde descender
    @SuppressWarnings("unchecked")
	public boolean searchNode(E key, int[] pos) {
        pos[0] = 0;
        while (pos[0] < this.count && ((Comparable<E>) key).compareTo(this.keys.get(pos[0])) > 0)
            pos[0]++;
        if (pos[0] < this.count && ((Comparable<E>) key).compareTo(this.keys.get(pos[0])) == 0) {
            return true;
        }
        return false;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("Node ").append(idNode).append(": [");
        for (int i = 0; i < this.count; i++) {
            sb.append(this.keys.get(i));
            if (i < this.count - 1) sb.append(", "); // separador entre claves
        }
        sb.append("]");
        return sb.toString();
    }

    public static void resetCounter() {
        nodeCount = 0;                             // reinicia ids (útil para tests)
    }
}