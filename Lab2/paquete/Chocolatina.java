package paquete;

// Clase Chocolatina que puede ser comparada con otras chocolatinas
public class Chocolatina implements Comparable<Chocolatina> {
	// Atributo marca de la chocolatina
	private String marca;
	
	// Constructor: inicializa la marca
	public Chocolatina(String marca) {
		this.marca = marca;
	}
	
	// Getter de marca
	public String getMarca() {
		return this.marca;
	}
	
	// Setter de marca
	public void setMarca(String marca) {
		this.marca = marca;
	}

	// Comprobación de igualdad: vital para que los métodos genéricos como exist() funcionen
	@Override
	public boolean equals(Object obj) {
		// Si es la misma dirección de memoria, son iguales
	    if (this == obj) return true;
	    // Si el otro objeto es una Chocolatina, comparamos sus marcas
	    if (obj instanceof Chocolatina otra) {
	        return this.marca.equals(otra.marca);
	    }
	    // Si no es chocolatina o es null, son diferentes
	    return false;
	}
	
	// Método obligatorio de Comparable: define el orden natural (por marca alfabéticamente)
	@Override
    public int compareTo(Chocolatina otra) {
        return this.marca.compareTo(otra.marca);
    }
	
	// Representación textual de la chocolatina
	@Override
	public String toString() {
		return "Chocolatina: "+ this.marca;
	}
}

