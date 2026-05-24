package paquete;

import exceptions.ItemDuplicated;
import exceptions.ItemNotFound;
import exceptions.ExceptionIsEmpty;

/**
 * Representa un Árbol AVL (Adelson-Velsky y Landis), que es un Árbol Binario de Búsqueda
 * Auto-Balanceado. Mantiene una altura máxima de O(log n) garantizando búsquedas, inserciones
 * y eliminaciones eficientes. 
 * El factor de equilibrio (bf) se calcula como: altura(derecha) - altura(izquierda).
 * Un nodo se considera desbalanceado si su bf es menor que -1 o mayor que 1.
 * 
 * @param <E> El tipo de elemento comparable almacenado en el árbol AVL.
 */
public class AVLTree<E extends Comparable<E>> extends BSTree<E> {

    // Bandera para rastrear cambios en la altura del árbol durante la inserción/eliminación
    private boolean height;

    /**
     * Constructor por defecto que inicializa el árbol AVL llamando al constructor de BSTree.
     */
    public AVLTree() {
        super();
        this.height = false;
    }

    /**
     * Inserta un elemento en el árbol AVL y rebalancea la estructura en caso necesario.
     * Sobrescribe el método insert de BSTree.
     * @param x El elemento a insertar.
     * @throws ItemDuplicated Si el elemento ya existe en el árbol AVL.
     */
    @Override
    public void insert(E x) throws ItemDuplicated {
        this.height = false;
        this.root = insert(x, this.root);
    }

    /**
     * Inserción recursiva con balanceo AVL dinámico.
     * Actualiza los factores de equilibrio (bf) y ejecuta las rotaciones adecuadas si
     * se detecta un desequilibrio.
     * @param x Elemento a insertar.
     * @param node Nodo actual examinado en la recursión.
     * @return El nodo raíz del subárbol balanceado.
     * @throws ItemDuplicated Si el elemento es un duplicado.
     */
    private Node<E> insert(E x, Node<E> node) throws ItemDuplicated {
        Node<E> fat = node;
        if (node == null) {
            this.height = true; // Se crea una nueva hoja: incrementa la altura
            fat = new Node<>(x);
        } else {
            int resC = node.getData().compareTo(x);
            if (resC == 0)
                throw new ItemDuplicated(x + " ya se encuentra en el arbol.");
            if (resC > 0) { // Insertar en el subárbol izquierdo
                fat.setLeft(insert(x, node.getLeft()));
                if (this.height) { // Si la altura del subárbol izquierdo aumentó
                    switch (fat.get_bf()) {
                        case 1:  
                            // Estaba cargado a la derecha (+1), ahora se equilibra (0)
                        	fat.set_bf(0);  
                        	this.height = false; 
                        	break;
                        case 0:  
                            // Estaba equilibrado (0), ahora se carga a la izquierda (-1)
                        	fat.set_bf(-1); 
                        	this.height = true;  
                        	break;
                        case -1: 
                            // Estaba cargado a la izquierda (-1), ahora se desbalancea (-2). Requiere rotación a la derecha.
                        	fat = balanceToRight(fat); 
                        	this.height = false; 
                        	break;
                    }
                }
            } else { // Insertar en el subárbol derecho
                fat.setRight(insert(x, node.getRight()));
                if (this.height) { // Si la altura del subárbol derecho aumentó
                    switch (fat.get_bf()) {
                        case -1: 
                            // Estaba cargado a la izquierda (-1), ahora se equilibra (0)
                        	fat.set_bf(0); 
                        	this.height = false; 
                        	break;
                        case 0:  
                            // Estaba equilibrado (0), ahora se carga a la derecha (+1)
                        	fat.set_bf(1); 
                        	this.height = true;  
                        	break;
                        case 1:  
                            // Estaba cargado a la derecha (+1), ahora se desbalancea (+2). Requiere rotación a la izquierda.
                        	fat = balanceToLeft(fat); 
                        	this.height = false; 
                        	break;
                    }
                }
            }
        }
        return fat;
    }

    /**
     * Elimina un elemento del árbol AVL y rebalancea de ser necesario.
     * Sobrescribe el método delete de BSTree.
     * @param d El elemento a eliminar.
     * @throws ExceptionIsEmpty Si el árbol está vacío.
     * @throws ItemNotFound Si el elemento no existe en el árbol AVL.
     */
    @Override
    public void delete(E d) throws ExceptionIsEmpty, ItemNotFound {
        if (root == null) throw new ExceptionIsEmpty("El árbol está vacío");
        this.height = false;
        root = deleteRec(root, d);
    }

    /**
     * Eliminación recursiva y balanceo en cascada tras remover un nodo.
     * Sobrescribe deleteRec de BSTree para ajustar factores de equilibrio y aplicar rotaciones.
     * @param n Nodo actual.
     * @param d Elemento a eliminar.
     * @return El nodo raíz del subárbol balanceado.
     * @throws ItemNotFound Si el elemento no existe.
     */
    @Override
    protected Node<E> deleteRec(Node<E> n, E d) throws ItemNotFound {
        if (n == null) 
        	throw new ItemNotFound("El elemento " + d + " no existe");
        Node<E> fat = n;
        int comp = d.compareTo(n.getData());
        if (comp < 0) { // Eliminar en el subárbol izquierdo
            fat.setLeft(deleteRec(n.getLeft(), d));
            if (this.height) { // Si la altura del subárbol izquierdo disminuyó
                switch (fat.get_bf()) {
                    case -1: 
                        // Estaba cargado a la izquierda, ahora se equilibra (0) y se propaga reducción de altura
                    	fat.set_bf(0);  
                    	this.height = true;  
                    	break;
                    case 0: 
                        // Estaba equilibrado, ahora se carga a la derecha (+1) sin reducir altura global
                    	fat.set_bf(1);  
                    	this.height = false; 
                    	break;
                    case 1:  
                        // Estaba cargado a la derecha, se desbalancea (+2). Balancear hacia la izquierda.
                    	fat = balanceToLeft(fat);
                    	break;
                }
            }
        } else if (comp > 0) { // Eliminar en el subárbol derecho
            fat.setRight(deleteRec(n.getRight(), d));
            if (this.height) { // Si la altura del subárbol derecho disminuyó
                switch (fat.get_bf()) {
                    case 1:  
                        // Estaba cargado a la derecha, ahora se equilibra (0) y se propaga reducción de altura
                    	fat.set_bf(0);  
                    	this.height = true;  
                    	break;
                    case 0:  
                        // Estaba equilibrado, ahora se carga a la izquierda (-1) sin reducir altura global
                    	fat.set_bf(-1); 
                    	this.height = false; 
                    	break;
                    case -1: 
                        // Estaba cargado a la izquierda, se desbalancea (-2). Balancear hacia la derecha.
                    	fat = balanceToRight(fat); 
                    	break;
                }
            }
        } else { // Se encontró el nodo a eliminar
            if (n.getLeft() == null && n.getRight() == null) {
                this.height = true; return null; // Nodo hoja
            }
            if (n.getLeft() == null)  { 
            	this.height = true; 
            	return n.getRight(); // Nodo con hijo único derecho
            }
            if (n.getRight() == null) { 
            	this.height = true; 
            	return n.getLeft(); // Nodo con hijo único izquierdo
            }
            // Nodo con dos hijos: Buscar sucesor inorden (mínimo del subárbol derecho)
            E sucesor = findMinNode(n.getRight());
            fat.setData(sucesor);
            fat.setRight(deleteRec(n.getRight(), sucesor));
            if (this.height) {
                switch (fat.get_bf()) {
                    case 1:  
                    	fat.set_bf(0);  
                    	this.height = true;  
                    	break;
                    case 0:  
                    	fat.set_bf(-1); 
                    	this.height = false; 
                    	break;
                    case -1: 
                    	fat = balanceToRight(fat); 
                    	break;
                }
            }
        }
        return fat;
    }

    /**
     * Aplica rotaciones para restablecer el balance hacia la izquierda (subárbol derecho pesado).
     * @param node Nodo desbalanceado (bf = +2).
     * @return El nodo raíz del subárbol rebalanceado.
     */
    private Node<E> balanceToLeft(Node<E> node) {
        Node<E> hijo = node.getRight();
        switch (hijo.get_bf()) {
            case 1:
                // Caso Derecha-Derecha (DD): Se soluciona con una Rotación Simple Izquierda (RSL)
                node.set_bf(0); hijo.set_bf(0);
                node = rotateSL(node);
                break;
            case -1:
                // Caso Derecha-Izquierda (DI): Se soluciona con una Rotación Doble Izquierda (RDL)
                Node<E> nieto = hijo.getLeft();
                switch (nieto.get_bf()) {
                    case -1: node.set_bf(0);  hijo.set_bf(1);  break;
                    case  0: node.set_bf(0);  hijo.set_bf(0);  break;
                    case  1: node.set_bf(-1); hijo.set_bf(0);  break;
                }
                nieto.set_bf(0);
                node.setRight(rotateSR(hijo)); // Rotación simple derecha en el hijo
                node = rotateSL(node);        // Rotación simple izquierda en el nodo desbalanceado
                break;
            case 0:
                // Caso especial durante eliminaciones
                node.set_bf(1); hijo.set_bf(-1);
                node = rotateSL(node);
                break;
        }
        return node;
    }

    /**
     * Aplica rotaciones para restablecer el balance hacia la derecha (subárbol izquierdo pesado).
     * @param node Nodo desbalanceado (bf = -2).
     * @return El nodo raíz del subárbol rebalanceado.
     */
    private Node<E> balanceToRight(Node<E> node) {
        Node<E> hijo = node.getLeft();
        switch (hijo.get_bf()) {
            case -1:
                // Caso Izquierda-Izquierda (II): Se soluciona con una Rotación Simple Derecha (RSR)
                node.set_bf(0); hijo.set_bf(0);
                node = rotateSR(node);
                break;
            case 1:
                // Caso Izquierda-Derecha (ID): Se soluciona con una Rotación Doble Derecha (RDR)
                Node<E> nieto = hijo.getRight();
                switch (nieto.get_bf()) {
                    case  1: 
                    	node.set_bf(0);  
                    	hijo.set_bf(-1); 
                    	break;
                    case  0: 
                    	node.set_bf(0);  
                    	hijo.set_bf(0); 
                    	break;
                    case -1: 
                    	node.set_bf(1);  
                    	hijo.set_bf(0);  
                    	break;
                }
                nieto.set_bf(0);
                node.setLeft(rotateSL(hijo));  // Rotación simple izquierda en el hijo
                node = rotateSR(node);        // Rotación simple derecha en el nodo desbalanceado
                break;
            case 0:
                // Caso especial durante eliminaciones
                node.set_bf(-1); hijo.set_bf(1);
                node = rotateSR(node);
                break;
        }
        return node;
    }
    
    /**
     * Rotación Simple Izquierda (RSL - Rotate Single Left).
     * Desplaza los nodos para balancear un subárbol pesado a la derecha.
     * @param node Nodo raíz del desbalance.
     * @return Nueva raíz del subárbol.
     */
    private Node<E> rotateSL(Node<E> node) {
        Node<E> p = node.getRight();
        node.setRight(p.getLeft());
        p.setLeft(node);
        return p;
    }

    /**
     * Rotación Simple Derecha (RSR - Rotate Single Right).
     * Desplaza los nodos para balancear un subárbol pesado a la izquierda.
     * @param node Nodo raíz del desbalance.
     * @return Nueva raíz del subárbol.
     */
    private Node<E> rotateSR(Node<E> node) {
        Node<E> p = node.getLeft();
        node.setLeft(p.getRight());
        p.setRight(node);
        return p;
    }
    
    /**
     * Recorrido Inorden público que imprime en consola.
     */
    @Override
    public void inOrder() { 
    	inOrder(root); 
    	System.out.println(); 
    }
    
    /**
     * Recorrido Inorden recursivo para imprimir datos.
     */
    @Override
    protected void inOrder(Node<E> n) {
        if (n == null) return;
        inOrder(n.getLeft());
        System.out.print(n.getData() + " ");
        inOrder(n.getRight());
    }

    /**
     * Recorrido Preorden público que imprime en consola.
     */
    @Override
    public void preOrder() { 
    	preOrder(root); 
    	System.out.println(); 
    }
    
    /**
     * Recorrido Preorden recursivo para imprimir datos.
     */
    @Override
    protected void preOrder(Node<E> n) {
        if (n == null) return;
        System.out.print(n.getData() + " ");
        preOrder(n.getLeft());
        preOrder(n.getRight());
    }

    /**
     * Recorrido Postorden público que imprime en consola.
     */
    @Override
    public void postOrder() { 
    	postOrder(root); 
    	System.out.println(); 
    }
    
    /**
     * Recorrido Postorden recursivo para imprimir datos.
     */
    @Override
    protected void postOrder(Node<E> n) {
        if (n == null) return;
        postOrder(n.getLeft());
        postOrder(n.getRight());
        System.out.print(n.getData() + " ");
    }
    
    /**
     * Ejercicio 4: Recorrido por amplitud (BFS) recursivo usando nivel por nivel.
     * Imprime los elementos nivel a nivel.
     */
    public void bfsRecursivo() {
        int h = height();
        for (int nivel = 0; nivel <= h; nivel++) {
            System.out.print("Nivel " + nivel + ": ");
            imprimirNivel(root, nivel);
            System.out.println();
        }
    }

    /**
     * Imprime recursivamente los nodos que corresponden a un nivel específico del árbol.
     * @param n Nodo actual.
     * @param nivel Nivel objetivo (0 para el nodo actual).
     */
    private void imprimirNivel(Node<E> n, int nivel) {
        if (n == null) return;
        if (nivel == 0) {
            System.out.print(n.getData() + " ");
        } else {
            imprimirNivel(n.getLeft(),  nivel - 1);
            imprimirNivel(n.getRight(), nivel - 1);
        }
    }
    
    /**
     * Ejercicio 5: Recorrido por amplitud (BFS) utilizando una Cola (DequeLink).
     * Procesa los nodos iterativamente nivel por nivel.
     */
    public void bfsCola() {
        if (root == null) {
            System.out.println("El árbol está vacío");
            return;
        }

        asd.DequeLink<Node<E>> cola = new asd.DequeLink<>();
        cola.addLast(root);

        while (!cola.isEmpty()) {
            try {
                Node<E> actual = cola.removeFirst();
                System.out.print(actual.getData() + " ");

                if (actual.getLeft()  != null) cola.addLast(actual.getLeft());
                if (actual.getRight() != null) cola.addLast(actual.getRight());

            } catch (as.ExceptionIsEmpty e) {
                System.out.println("[!] " + e.getMessage());
            }
        }
        System.out.println();
    }

    /**
     * Retorna la raíz principal del árbol AVL.
     * @return El nodo raíz.
     */
	public Node<E> getRoot() {
		return this.root;
	}
}