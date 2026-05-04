package paquete;

/**
 * Clase que agrupa diferentes ejercicios y algoritmos resueltos sobre listas enlazadas.
 * Todos los métodos son estáticos para poder usarse como utilidades.
 */
class Ejercicios {
	
	// --- EJERCICIO 1: BUSCAR ELEMENTO ---
    /**
     * Busca si un valor está presente en la lista recorriéndola de inicio a fin.
     */
    public static <T extends Comparable<T>> boolean buscarElemento(ListLinked<T> lista, T valor) {
        ListLinked.Node<T> actual = lista.getFirst(); // Empezamos en la cabeza
        while (actual != null) { // Mientras no se acabe la lista...
            // Si el valor del nodo es igual al que buscamos (compareTo devuelve 0)
            if (actual.getValue().compareTo(valor) == 0){
            	return true; // ¡Lo encontramos!
            }
            actual = actual.getNext(); // Pasamos al siguiente eslabón
        }
        return false; // Si llegamos aquí, recorrimos todo y no estaba
    }
    
    // --- EJERCICIO 2: INVERTIR LISTA ---
    /**
     * Crea una nueva lista con los elementos en orden inverso.
     */
    public static <T extends Comparable<T>> ListLinked<T> invertirLista(ListLinked<T> lista) {
        ListLinked<T> nueva = new ListLinked<>(); // Lista donde guardaremos el resultado
        ListLinked.Node<T> actual = lista.getFirst();
        while (actual != null) {
            // TRUCO: Al insertar siempre al principio (insertFirst), 
            // el último elemento que leamos de la lista original quedará el primero en la nueva.
            nueva.insertFirst(actual.getValue());
            actual = actual.getNext();
        }
        return nueva;
    }
    
    // --- EJERCICIO 3: INSERTAR AL FINAL (A NIVEL DE NODO) ---
    /**
     * Recibe la cabeza de una lista y un valor, y lo añade al final conectando nodos.
     */
    public static <T extends Comparable<T>> ListLinked.Node<T> insertarAlFinal(ListLinked.Node<T> head, T valor) {
        ListLinked.Node<T> newNode = new ListLinked.Node<>(valor); // Fabricamos el nodo
        if (head == null) {
        	return newNode; // Si no hay lista, el nuevo nodo es ahora el inicio
        }
        ListLinked.Node<T> actual = head;
        // Caminamos hasta encontrar el nodo que NO tiene siguiente
        while (actual.getNext() != null) {
            actual = actual.getNext();
        }
        // Conectamos ese último nodo con nuestro nuevo nodo
        actual.setNext(newNode);
        return head; // Devolvemos la cabeza original
    }
  
    // --- EJERCICIO 4: CONTAR NODOS ---
    /**
     * Cuenta cuántos nodos hay en una lista a partir de una cabeza dada.
     */
    public static <T extends Comparable<T>> int contarNodos(ListLinked.Node<T> head) {
        int count = 0; // Iniciamos el contador
        while (head != null) { // Mientras el nodo actual no sea nulo...
            count++;           // Sumamos uno
            head = head.getNext(); // Saltamos al siguiente
        }
        return count;
    }

    // --- EJERCICIO 5: SON IGUALES ---
    /**
     * Determina si dos listas tienen los mismos elementos y en el mismo orden.
     */
    public static <T extends Comparable<T>> boolean sonIguales(ListLinked<T> lista1, ListLinked<T> lista2) {
        ListLinked.Node<T> act1 = lista1.getFirst(); // Puntero lista 1
        ListLinked.Node<T> act2 = lista2.getFirst(); // Puntero lista 2
        
        // Recorremos mientras AMBAS tengan nodos
        while (act1 != null && act2 != null) {
            // Si en algún punto los valores son diferentes...
            if (act1.getValue().compareTo(act2.getValue()) != 0) {
            	return false; // ... entonces no son iguales
            }
            act1 = act1.getNext(); // Avanzamos lista 1
            act2 = act2.getNext(); // Avanzamos lista 2
        }
        // Al final, para que sean iguales, AMBAS deben haber terminado a la vez (ser null)
        return (act1 == null && act2 == null);
    }
  
    // --- EJERCICIO 6: CONCATENAR LISTAS ---
    /**
     * Crea una nueva lista uniendo los elementos de la lista1 seguidos de los de la lista2.
     */
    public static <T extends Comparable<T>> ListLinked<T> concatenarListas(ListLinked<T> lista1, ListLinked<T> lista2) {
        ListLinked<T> resultado = new ListLinked<>(); // Lista destino
        
        // Copiamos todos los de la lista 1
        ListLinked.Node<T> actual = lista1.getFirst();
        while (actual != null) {
            resultado.insertLast(actual.getValue());
            actual = actual.getNext();
        }
        
        // Copiamos todos los de la lista 2 (se añadirán al final de lo que ya hay)
        actual = lista2.getFirst();
        while (actual != null) {
            resultado.insertLast(actual.getValue());
            actual = actual.getNext();
        }
 
        return resultado;
    }
}