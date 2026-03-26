package paquete;

// Clase Golosina que permite comparaciones por orden natural
public class Golosina implements Comparable<Golosina> {
	private String nombre;
	private double peso;
	
	// Constructor que recibe nombre y peso en gramos
	public Golosina(String nombre, double peso) {
		this.nombre = nombre;
		this.peso = peso;
	}
	
	public String getNombre() {
		return this.nombre;
	}
	public void setNombre(String nombre) {
		this.nombre = nombre;
	}
	public double getPeso() {
		return this.peso;
	}
	public void setPeso(double peso) {
		this.peso = peso;
	}
	
	// Sobreescritura de equals para comparar contenido en lugar de referencias
	@Override
	public boolean equals(Object obj) {
		// Compara si son el mismo objeto en memoria
	    if (this == obj) return true;

	    // 'instanceof' con patrón (Java 16+): verifica tipo y crea la variable 'otra'
	    if (obj instanceof Golosina otra) {
	        // Son iguales si tienen el mismo nombre y el mismo peso
	        return Double.compare(otra.peso, peso) == 0 && this.nombre.equals(otra.nombre);
	    }
	    
	    return false;
	}
	
	// Define que el orden por defecto es alfabético según el nombre
	@Override
    public int compareTo(Golosina otra) {
        return this.nombre.compareTo(otra.nombre);
    }
	
	// Formato de texto para mostrar la golosina
	@Override
	public String toString() {
		return "Golosina: " + this.nombre + " (Peso: " + this.peso + "g)";
	}
}

