package paquete;

/**
 * Clase que gestiona una lista de reproducción usando una lista doblemente enlazada.
 * Esto permite avanzar (next) y retroceder (prev) como en un reproductor real.
 */
public class ColaReproduccion<T> {

    // Atributos privados:
    private NodeDoble<T> first;  // Señala la primera canción de la lista
    private NodeDoble<T> finall; // Señala la última canción (para agregar rápido al final)
    private NodeDoble<T> actual; // Señala la canción que está "sonando" ahora mismo

    /**
     * Constructor: Crea una cola de reproducción nueva y vacía.
     */
    public ColaReproduccion() {
        this.first = null;
        this.finall = null;
        this.actual = null;
    }

    /**
     * Agrega una nueva canción al final de la cola.
     * @param cancion El objeto canción (o cualquier tipo T) a añadir.
     */
    public void agregarCancion(T cancion) {
        NodeDoble<T> newNode = new NodeDoble<>(cancion); // Fabricamos el nuevo nodo doble
        if (first == null) {
            // CASO 1: Si la lista está vacía, el nuevo nodo es el inicio, el fin y el actual.
            first = newNode;
            finall = newNode;
            actual = newNode;
        } else {
            // CASO 2: Si ya hay canciones, lo enganchamos al final.
            newNode.setPrev(finall); // El nuevo apunta hacia atrás al viejo último
            finall.setNext(newNode); // El viejo último apunta hacia adelante al nuevo
            finall = newNode;        // Ahora el nuevo nodo es oficialmente el último
        }
    }

    /**
     * Pasa a la siguiente canción en la lista.
     * @return El valor de la siguiente canción.
     */
    public T reproducirSiguiente() {
        // Verificamos si hay alguien después de la canción actual
        if (actual == null || actual.getNext() == null) {
            System.out.println("No hay siguiente canción.");
            return null;
        }
        actual = actual.getNext(); // Movemos el puntero 'actual' un paso adelante
        return actual.getValue();  // Devolvemos el dato de esa nueva canción
    }

    /**
     * Regresa a la canción anterior en la lista.
     * @return El valor de la canción anterior.
     */
    public T reproducirAnterior() {
        // Verificamos si hay alguien antes de la canción actual
        if (actual == null || actual.getPrev() == null) {
            System.out.println("No hay canción anterior.");
            return null;
        }
        actual = actual.getPrev(); // Movemos el puntero 'actual' un paso atrás
        return actual.getValue();  // Devolvemos el dato de la canción anterior
    }

    /**
     * Mezcla la lista de reproducción de forma aleatoria (Shuffle).
     * Usa el algoritmo de Fisher-Yates para asegurar una mezcla justa.
     */
    @SuppressWarnings("unchecked")
    public void mezclar() {
        // 1. Contamos cuántas canciones hay en total
        int n = 0;
        NodeDoble<T> temp = first;
        while (temp != null) { 
            n++; 
            temp = temp.getNext(); 
        }
        
        // Si hay 1 o 0 canciones, no tiene sentido mezclar
        if (n <= 1) {
            return;
        }

        // 2. Pasamos los valores de los nodos a un Array (lista fija)
        Object[] valores = new Object[n];
        temp = first;
        for (int i = 0; i < n; i++) {
            valores[i] = temp.getValue();
            temp = temp.getNext();
        }

        // 3. Algoritmo Fisher-Yates sobre el Array
        java.util.Random rng = new java.util.Random(); 
        for (int i = n - 1; i > 0; i--) {
            int j = rng.nextInt(i + 1); // elige una posición al azar entre 0 e i
            Object swap = valores[i];   // guarda el valor actual temporalmente
            valores[i] = valores[j];    // intercambia posiciones
            valores[j] = swap;
        }
        
        // 4. Devolvemos los valores ya mezclados a los nodos originales de la lista
        temp = first;
        for (int i = 0; i < n; i++) {
            temp.setValue((T) valores[i]); // Reemplazamos el dato del nodo
            temp = temp.getNext();
        }

        // Al terminar, la reproducción empieza desde el inicio de la nueva lista mezclada
        actual = first;
    }

    /**
     * Imprime la lista de reproducción en pantalla, marcando la canción actual.
     */
    public void mostrarCola() {
        NodeDoble<T> temp = first;
        int pos = 1;
        while (temp != null) {
            String indicador;
        	// Si el nodo es el que está sonando, le ponemos una flechita
            if (temp == actual) {
                indicador = "► ";
            } else {
                indicador = "  ";
            }
            System.out.println(indicador + pos + ". " + temp.getValue());
            temp = temp.getNext();
            pos++;
        }
    }

    /**
     * Calcula la suma de tiempo de todas las canciones.
     * @return Total en segundos.
     */
    public int duracionTotal() {
        int total = 0;
        NodeDoble<T> temp = first;
        while (temp != null) {
            // Verificamos si lo que hay dentro del nodo es una instancia de Cancion
            if (temp.getValue() instanceof Cancion) {
                // Hacemos un "cast" a Cancion para poder usar getDuracionSeg()
                total += ((Cancion) temp.getValue()).getDuracionSeg();
            }
            temp = temp.getNext();
        }
        return total;
    }

    /**
     * Convierte segundos a formato de texto mm:ss (ej: 125s -> "02:05").
     * @param segundos Tiempo total.
     * @return String formateado.
     */
    public static String formatearDuracion(int segundos) {
        return String.format("%02d:%02d", segundos / 60, segundos % 60);
    }
}
