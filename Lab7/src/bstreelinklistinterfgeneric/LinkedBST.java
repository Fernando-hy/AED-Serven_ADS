package bstreelinklistinterfgeneric;

import exceptions.ItemDuplicated;
import as.Queue;
import asd.DequeLink;
import bstreeInterface.BinarySearchTree;
import exceptions.ExceptionIsEmpty;
import exceptions.ItemNotFound;

// ============================================================================
// ESTRUCTURA DEL ÁRBOL BINARIO DE BÚSQUEDA (BST)
// Esta clase implementa un árbol de búsqueda binaria genérico.
// Los elementos deben ser comparables (extends Comparable).
// ============================================================================
public class LinkedBST<E extends Comparable<E>> implements BinarySearchTree<E> {
	private Node<E> root;
	
	public LinkedBST() {
		this.root=null;
	}
	
	public boolean isEmpty() {
		return this.root==null;
	}
	
	public Node<E> getRoot() {
	    return this.root;
	}
	
	// ------------------------------------------------------------------------
	// GRUPO 2: OPERACIONES DE BÚSQUEDA
	// ------------------------------------------------------------------------
	
	// Busca un elemento en el árbol. Lanza excepción si está vacío.
	public boolean search(E d) throws ItemNotFound {
	    // Si la raíz es nula, el árbol está vacío
	    if (root == null) {
	        throw new ItemNotFound("El árbol está vacío");
	    }
	    // Llama al método recursivo empezando desde la raíz
	    return searchRec(root, d);
	}
	
	// Método recursivo para buscar un elemento
	public boolean searchRec(Node<E> n,E d) {
	    // Si llegamos a un nodo nulo, el elemento no está
		if (n==null){
			return false;
		}
		// Compara el elemento buscado con el dato del nodo actual
		int comp = d.compareTo(n.getData());
		// Si es mayor, busca en el subárbol derecho
		if(comp>0) {
			return searchRec(n.getRight(), d);
		}
		// Si es menor, busca en el subárbol izquierdo
		else if(comp<0) {
			return searchRec(n.getLeft(), d);
		}
		// Si es igual, lo encontró
		else {
			return true;	
		}
	}
	
	// ------------------------------------------------------------------------
	// GRUPO 3: OPERACIONES DE INSERCIÓN
	// ------------------------------------------------------------------------
	
	// Inserta un nuevo elemento en el árbol. No permite duplicados.
	public void insert(E d) throws ItemDuplicated {
	    // Actualiza la raíz con el resultado de la inserción recursiva
		root=insertRec(root,d);
	}
	
	// Método recursivo para insertar un elemento
	public Node<E> insertRec(Node<E> n,E d) throws ItemDuplicated {
	    // Si el nodo actual es nulo, creamos el nuevo nodo aquí
		if (n==null) {
			return new Node<E>(d);
		}
		// Compara el dato a insertar con el del nodo actual
		int comp=d.compareTo(n.getData());
		// Si es mayor, se inserta en el subárbol derecho
		if (comp>0)
		{
			n.setRight(insertRec(n.getRight(),d));
		}
		// Si es menor, se inserta en el subárbol izquierdo
		else if(comp<0) {
			n.setLeft(insertRec(n.getLeft(),d));
		}
		// Si es igual, lanzamos excepción (no duplicados)
		else {
			throw new ItemDuplicated("No se puede 2 iguales");
		}
		// Retorna el nodo actual (con sus enlaces actualizados)
		return n;
	}

	// ------------------------------------------------------------------------
	// GRUPO 4: OPERACIONES DE ELIMINACIÓN
	// ------------------------------------------------------------------------
	
	// Elimina un elemento del árbol. Maneja los casos de nodos con 0, 1 o 2 hijos.
	public void delete(E d) throws ExceptionIsEmpty, ItemNotFound {
	    // Si la raíz es nula, el árbol está vacío
	    if (root == null) {
	        throw new ExceptionIsEmpty("El árbol está vacío");
	    }
	    // Actualiza la raíz con el resultado de la eliminación
	    root = deleteRec(root, d);
	}

	// Método recursivo para eliminar un nodo
	public Node<E> deleteRec(Node<E> n, E d) throws ItemNotFound {
	    // Si el nodo es nulo, el elemento no se encontró
	    if (n == null) {
	        throw new ItemNotFound("El elemento no existe");
	    }
	    // Compara el dato a eliminar con el del nodo actual
	    int comp = d.compareTo(n.getData());
	    if (comp > 0) {
	        // Busca en el subárbol derecho
	        n.setRight(deleteRec(n.getRight(), d));
	    } else if (comp < 0) {
	        // Busca en el subárbol izquierdo
	        n.setLeft(deleteRec(n.getLeft(), d));
	    } else {
	        // ¡Encontrado! Caso 1: Sin hijo izquierdo
	        if (n.getLeft() == null) {
	            return n.getRight();
	        } 
	        // Caso 2: Sin hijo derecho
	        else if (n.getRight() == null) {
	            return n.getLeft();
	        } 
	        // Caso 3: Dos hijos
	        else {
	            // Busca el sucesor (mínimo en el subárbol derecho)
	            E sucesor = findMinNode(n.getRight());
	            // Reemplaza el dato del nodo actual con el del sucesor
	            n.setData(sucesor);
	            // Elimina el sucesor del subárbol derecho
	            n.setRight(deleteRec(n.getRight(), sucesor));
	        }
	    }
	    return n;
	}
	
	public E findMinNode(Node<E> n) throws ItemNotFound {
	    if (n == null) throw new ItemNotFound("Nodo nulo");
	    while (n.getLeft() != null) {
	        n = n.getLeft();
	    }
	    return n.getData();
	}

	public E findMaxNode(Node<E> n) throws ItemNotFound {
	    if (n == null) throw new ItemNotFound("Nodo nulo");
	    while (n.getRight() != null) {
	        n = n.getRight();
	    }
	    return n.getData();
	}
	
	public String toString() {
	    if (root == null) return "BST vacío";
	    return toStringRec(root);
	}

	private String toStringRec(Node<E> n) {
	    if (n == null) return "";
	    return toStringRec(n.getLeft()) + n.getData() + " " + toStringRec(n.getRight());
	}

	// ------------------------------------------------------------------------
	// GRUPO 5: RECORRIDOS DEL ÁRBOL
	// ------------------------------------------------------------------------
	
	// Recorrido In-Orden (Izquierda, Raíz, Derecha) - Produce salida ordenada.
	public void inOrder(Node<E> n) {
	    // Caso base: si el nodo es nulo, termina la recursión
	    if (n == null) return;
	    // Visita el subárbol izquierdo
	    inOrder(n.getLeft());
	    // Procesa (imprime) el nodo actual
	    System.out.print(n.getData() + " ");
	    // Visita el subárbol derecho
	    inOrder(n.getRight());
	}

	// Recorrido Pre-Orden (Raíz, Izquierda, Derecha)
	public void preOrder(Node<E> n) {
	    // Caso base
	    if (n == null) return;
	    // Procesa (imprime) el nodo actual primero
	    System.out.print(n.getData() + " ");
	    // Visita subárbol izquierdo
	    preOrder(n.getLeft());
	    // Visita subárbol derecho
	    preOrder(n.getRight());
	}

	// Recorrido Post-Orden (Izquierda, Derecha, Raíz)
	public void postOrder(Node<E> n) {
	    // Caso base
	    if (n == null) return;
	    // Visita subárbol izquierdo
	    postOrder(n.getLeft());
	    // Visita subárbol derecho
	    postOrder(n.getRight());
	    // Procesa (imprime) el nodo actual al final
	    System.out.print(n.getData() + " ");
	}
	// ============================================================================
	// EJERCICIOS Y OPERACIONES AVANZADAS
	// ============================================================================
	
	// --- Ejercicio 2: Destrucción y Conteo ---
	
	// Vacía el árbol eliminando la referencia a la raíz.
	public void destroyNodes() throws ExceptionIsEmpty {
	    if (isEmpty()) {
	        throw new ExceptionIsEmpty("El árbol ya está vacío");
	    }
	    this.root = null;
	}
	public int countAllNodes() {
	    return countNonLeafNodes(root);
	}

	private int countNonLeafNodes(Node<E> n) {
	    if (n == null || (n.getLeft() == null && n.getRight() == null)) {
	        return 0;
	    }
	    return 1 + countNonLeafNodes(n.getLeft()) + countNonLeafNodes(n.getRight());
	}
	
	public int countNodes() {
	    return countAllNodes();
	}
	
	public int height(E x) {
	    // 1. Buscar el nodo con valor x
	    Node<E> nodoX = root;
	    while (nodoX != null) {
	        int comp = x.compareTo(nodoX.getData());
	        if (comp > 0) nodoX = nodoX.getRight();
	        else if (comp < 0) nodoX = nodoX.getLeft();
	        else break;
	    }
	    if (nodoX == null) return -1; // no existe

	    // 2. Calcular altura iterativamente con cola
	    DequeLink<Node<E>> cola = new DequeLink<>();
	    cola.addLast(nodoX);
	    int altura = -1;

	    try {
	        while (!cola.isEmpty()) {
	            int tam = cola.size(); // nodos en el nivel actual
	            altura++;
	            for (int i = 0; i < tam; i++) {
	                Node<E> actual = cola.removeFirst();
	                if (actual.getLeft() != null)  cola.addLast(actual.getLeft());
	                if (actual.getRight() != null) cola.addLast(actual.getRight());
	            }
	        }
	    } catch (as.ExceptionIsEmpty e) {}

	    return altura;
	}
	
	public int amplitude(int nivel) {
	    if (root == null) return 0;
	    
	    DequeLink<Node<E>> cola = new DequeLink<>();
	    cola.addLast(root);
	    int nivelActual = 0;
	    int maxNodos = 0;

	    try {
	        while (!cola.isEmpty()) {
	            int tam = cola.size(); // nodos en este nivel
	            if (tam > maxNodos) maxNodos = tam;
	            
	            if (nivelActual == nivel) return tam; // retorna el nivel pedido
	            
	            nivelActual++;
	            for (int i = 0; i < tam; i++) {
	                Node<E> actual = cola.removeFirst();
	                if (actual.getLeft() != null)  cola.addLast(actual.getLeft());
	                if (actual.getRight() != null) cola.addLast(actual.getRight());
	            }
	        }
	    } catch (as.ExceptionIsEmpty e) {}

	    return 0; // nivel no existe
	}
	
	// --- Ejercicio 3: Área del Árbol ---
	
	// Calcula el "área" del árbol como (número de hojas * altura).
	public int areaBST() {
	    // Si el árbol está vacío, el área es 0
	    if (root == null) return 0;

	    // Usamos una cola para recorrer el árbol por niveles (amplitud)
	    DequeLink<Node<E>> cola = new DequeLink<>();
	    cola.addLast(root); // Encolamos la raíz
	    int hojas = 0;
	    int altura = -1;

	    try {
	        while (!cola.isEmpty()) {
	            int tam = cola.size(); // Nodos en el nivel actual
	            altura++; // Incrementamos la altura por cada nivel procesado
	            for (int i = 0; i < tam; i++) {
	                Node<E> actual = cola.removeFirst(); // Desencolamos
	                // Si no tiene hijos, es un nodo hoja
	                if (actual.getLeft() == null && actual.getRight() == null) {
	                    hojas++; 
	                }
	                // Encolamos los hijos si existen
	                if (actual.getLeft() != null)  cola.addLast(actual.getLeft());
	                if (actual.getRight() != null) cola.addLast(actual.getRight());
	            }
	        }
	    } catch (as.ExceptionIsEmpty e) {}

	    // El área es el producto de hojas por la altura máxima
	    return hojas * altura;
	}
	
	public void drawBST() {
	    if (root == null) {
	        System.out.println("BST vacío");
	        return;
	    }

	    DequeLink<Node<E>> cola = new DequeLink<>();
	    cola.addLast(root);
	    int nivel = 0;

	    try {
	        while (!cola.isEmpty()) {
	            int tam = cola.size();
	            // sangría según nivel
	            for (int s = 0; s < (int) Math.pow(2, 4 - nivel); s++) {
	                System.out.print(" ");
	            }
	            for (int i = 0; i < tam; i++) {
	                Node<E> actual = cola.removeFirst();
	                System.out.print(actual.getData() + " ");
	                if (actual.getLeft() != null)  cola.addLast(actual.getLeft());
	                if (actual.getRight() != null) cola.addLast(actual.getRight());
	            }
	            System.out.println();
	            nivel++;
	        }
	    } catch (as.ExceptionIsEmpty e) {}
	}
	
	public static boolean sameArea(LinkedBST<Integer> bst1, LinkedBST<Integer> bst2) {
	    return bst1.areaBST() == bst2.areaBST();
	}
	// --- Ejercicio 4: Representación y Validación ---
	
	// Imprime el árbol con una estructura de paréntesis y sangría.
	public void parenthesize() {
	    if (root == null) {
	        System.out.println("BST vacío");
	        return;
	    }
	    parenthesizeRec(root, 0);
	}

	//4
	private void parenthesizeRec(Node<E> n, int nivel) {
	    if (n == null) return;
	    String sangria = "";
	    for (int i = 0; i < nivel; i++) sangria += "    ";
	    
	    if (n.getLeft() == null && n.getRight() == null) {
	        System.out.println(sangria + n.getData());
	    } else {
	        System.out.println(sangria + n.getData() + " (");
	        parenthesizeRec(n.getLeft(), nivel + 1);
	        parenthesizeRec(n.getRight(), nivel + 1);
	        System.out.println(sangria + ")");
	    }
	}
	// Verifica si el árbol es un BST válido (cumple la propiedad de orden)
	public boolean isValidBST() {
	    // Inicialmente no hay límites mínimos ni máximos
	    return isValidRec(root, null, null);
	}

	// Método recursivo con límites para validación
	private boolean isValidRec(Node<E> n, E min, E max) {
	    // Un árbol vacío es válido
	    if (n == null) 
	    	return true;
	    
	    // El dato debe ser mayor que el mínimo permitido
	    if (min != null && n.getData().compareTo(min) <= 0) 
	    	return false;
	    // El dato debe ser menor que el máximo permitido
	    if (max != null && n.getData().compareTo(max) >= 0) 
	    	return false;
	    
	    // Valida subárbol izquierdo (actualiza el máximo) y derecho (actualiza el mínimo)
	    return isValidRec(n.getLeft(), min, n.getData()) &&
	           isValidRec(n.getRight(), n.getData(), max);
	}
	
	// --- Ejercicio 5: Búsqueda por Rango y Otros ---
	
	// Busca e imprime los elementos que están dentro del rango [min, max].
	public void searchRange(E min, E max) {
	    if (root == null) {
	        System.out.println("BST vacío");
	        return;
	    }
	    System.out.print("Rango [" + min + ", " + max + "]: ");
	    searchRangeRec(root, min, max);
	    System.out.println();
	}

	private void searchRangeRec(Node<E> n, E min, E max) {
	    if (n == null) return;
	    if (n.getData().compareTo(min) > 0)
	        searchRangeRec(n.getLeft(), min, max);
	    if (n.getData().compareTo(min) >= 0 && n.getData().compareTo(max) <= 0)
	        System.out.print(n.getData() + " ");
	    if (n.getData().compareTo(max) < 0)
	        searchRangeRec(n.getRight(), min, max);
	}
	
	public int countLeaves() {
	    if (root == null) return 0;
	    return countLeavesRec(root);
	}

	private int countLeavesRec(Node<E> n) {
	    if (n == null) return 0;
	    if (n.getLeft() == null && n.getRight() == null) return 1;
	    return countLeavesRec(n.getLeft()) + countLeavesRec(n.getRight());
	}
	
	public void printDescending(Node<E> n) {
	    if (n == null) return;
	    printDescending(n.getRight());
	    System.out.print(n.getData() + " ");
	    printDescending(n.getLeft());
	}
}
