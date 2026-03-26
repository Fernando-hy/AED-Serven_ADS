package paquete;

import java.util.ArrayList;
import java.util.Iterator;

// Clase genérica Bolsa que puede contener cualquier tipo de objeto T
public class Bolsa <T> implements Iterable <T> {
	// Lista interna para almacenar los elementos de tipo T
	private ArrayList <T> lista = new ArrayList <T>();
	// Límite máximo de elementos que puede contener la bolsa
	private int tope;
	
	// Constructor que recibe el tope de la bolsa
	public Bolsa(int tope) {
		this.tope = tope;
	}
	
	// Método para añadir un objeto a la bolsa
	public void add(T objeto) {
		// Verifica si aún hay espacio (el tamaño actual es menor al tope)
		if (lista.size() < tope) {
			lista.add(objeto); // Añade el objeto a la lista
		} else {
			// Si no hay espacio, lanza una excepción (error)
			throw new RuntimeException("no caben más");
		}
	}
	
	// Implementación de la interfaz Iterable para usar en bucles for-each
	@Override
	public Iterator <T> iterator() {
		return lista.iterator(); // Retorna el iterador de la lista interna
	}
	
	// Método para imprimir todos los elementos contenidos en la bolsa
	public void mostrarElementos() {
		// Usa el iterador (for-each) de esta misma clase (this)
		for(T elemento : this) 
			System.out.println(elemento); // Imprime cada elemento
	}
	
	// Método estático genérico para imprimir cualquier bolsa recibida como parámetro
	public static <T> void imprimirBolsa(Bolsa<T> bolsa) {
	    for (T elemento : bolsa) {
	        System.out.println(elemento); // Llama al toString() del objeto contenido
	    }
	}
}