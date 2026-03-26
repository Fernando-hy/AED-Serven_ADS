package paquete;

// Clase genérica Caja que almacena un objeto de tipo T y un color
public class Caja<T> {
    // El contenido de la caja (cualquier objeto de tipo T)
    private T contenido;
    // Representa el color de la caja física
    private String color;

    // Constructor que inicializa el color y el objeto contenido
    public Caja(String color, T contenido) {
        this.color = color;
        this.contenido = contenido;
    }

    // Retorna el objeto genérico guardado en la caja
    public T getContenido() { 
    	return contenido; 
    }
    
    // Retorna el color de la caja
    public String getColor() { 
    	return color; 
    }

    // Sobreescritura del método toString para representar la caja como texto
    @Override
    public String toString() {
        return "Color: " + color + " Objeto: [" + contenido + "]";
    }
}