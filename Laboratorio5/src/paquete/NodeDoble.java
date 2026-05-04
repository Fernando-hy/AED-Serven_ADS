package paquete;

/**
 * Clase que representa un Nodo para una Lista Doblemente Enlazada.
 * Un nodo doble es como un eslabón que tiene dos "manos" para agarrar 
 * tanto al que viene después como al que estaba antes.
 */
public class NodeDoble<T> {
    // El valor o dato que almacena el nodo (puede ser una Canción, un String, etc.)
    private T value;
    
    // Puntero que apunta al siguiente nodo en la lista (hacia adelante)
    private NodeDoble<T> next;
    
    // Puntero que apunta al nodo anterior en la lista (hacia atrás)
    // Esto es lo que la hace "Doble" y permite retroceder
    private NodeDoble<T> prev;
 
    /**
     * Constructor del nodo. Se ejecuta al crear un nuevo nodo.
     * @param value El dato que queremos guardar dentro del nodo.
     */
    public NodeDoble(T value) {
        this.setValue(value); // Guardamos el valor recibido
        this.setNext(null);   // Al nacer, el nodo no tiene a nadie después (es null)
        this.setPrev(null);   // Al nacer, el nodo no tiene a nadie antes (es null)
    }

    // --- MÉTODOS GETTERS Y SETTERS ---
    // Sirven para leer (get) o modificar (set) los atributos privados desde fuera de la clase.

	/** @return El nodo que está antes de este */
	public NodeDoble<T> getPrev() {
		return prev;
	}

	/** @param prev El nuevo nodo que queremos colocar como "anterior" */
	public void setPrev(NodeDoble<T> prev) {
		this.prev = prev;
	}

	/** @return El nodo que está después de este */
	public NodeDoble<T> getNext() {
		return next;
	}

	/** @param next El nuevo nodo que queremos colocar como "siguiente" */
	public void setNext(NodeDoble<T> next) {
		this.next = next;
	}

	/** @return El dato real que está guardado en este nodo */
	public T getValue() {
		return value;
	}

	/** @param value El nuevo dato que queremos que guarde este nodo */
	public void setValue(T value) {
		this.value = value;
	}
}