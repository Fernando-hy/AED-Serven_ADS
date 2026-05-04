package paquete;

/**
 * Clase genérica para una Lista Enlazada Simple.
 * <T extends Comparable<T>> significa que el tipo de dato T debe poder compararse entre sí.
 */
public class ListLinked<T extends Comparable <T>>{
    // El puntero que señala al inicio de la lista. Si es null, la lista está vacía.
    private Node<T> first;

    /**
     * Constructor: Inicializa una lista nueva y vacía.
     */
    public ListLinked() { 
        this.first = null; // Al principio no hay ningún nodo
    }

    /**
     * Verifica si la lista no tiene elementos.
     * @return true si está vacía, false si tiene al menos un nodo.
     */
    public boolean isEmptyList() {
        return this.first == null; 
    }

    /**
     * Inserta un nuevo elemento al principio de la lista.
     * @param value El dato que queremos guardar.
     */
    public void insertFirst(T value) {
        Node<T> newNode = new Node<>(value); // Creamos el nuevo eslabón
        if (!isEmptyList()) {
            newNode.setNext(this.first); // El nuevo apunta al que antes era el primero
        }
        this.first = newNode; // Ahora el nuevo es oficialmente el primero
    }

    /**
     * Inserta un nuevo elemento al final de la lista.
     * @param value El dato que queremos guardar.
     */
    public void insertLast(T value) {
        Node<T> newNode = new Node<>(value); // Creamos el nuevo eslabón
        if (isEmptyList()) {
            // Si la lista está vacía, insertar al final es lo mismo que insertar al principio
        	insertFirst(value);
        } else {
            // Si hay elementos, debemos caminar hasta el último
            Node<T> actual = this.first;
            while (actual.getNext() != null) { // Mientras el siguiente NO sea nulo...
                actual = actual.getNext();     // ... seguimos avanzando
            }
            // Al salir del bucle, 'actual' es el último nodo.
            actual.setNext(newNode); // Conectamos el último con el nuevo
        }
    }

    /**
     * Busca y elimina un nodo de la lista según su valor.
     * @param value El dato que queremos borrar.
     * @return true si se borró con éxito, false si no se encontró.
     */
    public boolean removeNode(T value) {
    	if (isEmptyList()) {
        	return false; // No hay nada que borrar
        }
        // CASO ESPECIAL: Si el elemento a borrar es el primero
    	if (this.first.getValue().compareTo(value) == 0) {
            this.first = this.first.getNext(); // Saltamos el primer nodo
            return true;
    	}
        	
        // CASO GENERAL: Buscar en el resto de la lista
        Node<T> actual = this.first;
        // Buscamos mientras el siguiente exista y NO sea el que buscamos
        while (actual.getNext()!=null && actual.getNext().getValue().compareTo(value)!=0) {
        	actual = actual.getNext();
        }
        
        // Si llegamos al final y no lo encontramos
        if(actual.getNext()==null) {
            return false;
        }
        
        // El "puente": El nodo actual apunta al que sigue del que queremos borrar
        actual.setNext(actual.getNext().getNext());
        return true;
    }
    
    /**
     * Busca si un valor existe dentro de la lista.
     * @param value El dato que buscamos.
     * @return true si existe, false si no.
     */
    public boolean Search(T value) {
    	if (isEmptyList()) {
        	return false;
        } else {
            Node<T> actual = this.first;
            // Recorremos mientras no sea el final y no lo hayamos encontrado
            while (actual.getNext()!= null && actual.getValue().compareTo(value)!=0) {
                actual = actual.getNext();
            }
            // Verificamos si el nodo donde nos detuvimos tiene el valor buscado
            if(actual.getValue().compareTo(value)==0) {
            	return true;
            }
            return false;
        }
    }
    
    /**
     * Cuenta cuántos elementos hay en la lista.
     * @return El número total de nodos.
     */
    public int length() {
    	int cont = 0; // Contador de perlas
        Node<T> actual = this.first;
        while (actual != null) { // Mientras haya un nodo bajo nuestro dedo...
            cont++;             // ... sumamos uno
            actual = actual.getNext(); // ... pasamos al siguiente
        }
        return cont;
    }
    
    /**
     * Imprime todos los elementos de la lista en consola.
     */
    public void print() {
    	if (isEmptyList()) {
        	System.out.println("La lista no tiene elementos");
        } else {
            Node<T> actual = this.first;
            while (actual!= null) {
            	System.out.println(actual.getValue()+"-");
            	actual = actual.getNext();
            }
        }
    }
    
    /**
     * Invierte el orden de la lista (el último pasa a ser el primero).
     */
    public void reverse() {
    	if (isEmptyList()) {
        	return;
        } else {
        	Node<T> anterior = null;
            Node<T> actual = this.first; 	
        	Node<T> siguiente = null;
            
            while (actual!= null) {
            	siguiente = actual.getNext(); // Guardamos el siguiente para no perderlo
                actual.setNext(anterior);     // Giramos el puntero (hacia atrás)
                anterior = actual;            // Movemos 'anterior' un paso
                actual = siguiente;           // Movemos 'actual' un paso
            }
            this.first = anterior; // Al final, 'anterior' será la nueva cabeza
        }
    }
    
    /** @return El primer nodo de la lista */
    public Node<T> getFirst() {
        return this.first;
    }

    /**
     * Clase Interna: Representa un eslabón (Nodo) de la cadena simple.
     */
    public static class Node<T extends Comparable<T>> {
        private T value;     // El dato guardado
        private Node<T> next; // El hilo hacia el siguiente nodo

        /** Constructor del nodo */
        public Node(T value) {
            this.value = value;
            this.next = null;
        }

        /** Permite comparar el valor de este nodo con otro dato */
        public int compareTo(T dato) {
            return this.value.compareTo(dato);
        }

        // --- Getters y Setters del Nodo ---
        public T getValue() { return value; }
        public void setValue(T value) { this.value = value; }
        public Node<T> getNext() { return next; }
        public void setNext(Node<T> next) { this.next = next; }
    }
}