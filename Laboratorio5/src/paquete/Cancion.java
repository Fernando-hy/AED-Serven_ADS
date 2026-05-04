package paquete;

/**
 * Clase que representa una canción individual.
 * Es una clase de tipo "POJO" (Plain Old Java Object) que solo sirve para guardar datos.
 */
public class Cancion {
    // Atributos privados para cumplir con el encapsulamiento (seguridad de los datos)
    private String titulo;      // Nombre de la canción
    private String artista;     // Quién la canta
    private int duracionSeg;    // Cuánto dura en segundos (ej: 180 para 3 min)
 
    /**
     * Constructor: Se usa para crear una nueva canción con todos sus datos.
     * @param titulo El nombre que le daremos a la canción.
     * @param artista El nombre del artista.
     * @param duracionSeg El tiempo total en segundos.
     */
    public Cancion(String titulo, String artista, int duracionSeg) {
        this.titulo = titulo;        // Guardamos el título en el atributo de la clase
        this.artista = artista;      // Guardamos el artista
        this.duracionSeg = duracionSeg; // Guardamos la duración
    }
 
    // --- GETTERS ---
    // Métodos públicos para poder leer la información desde otras clases (como Main).

    /** @return El título de la canción */
    public String getTitulo()    { 
    	return titulo; 
    }

    /** @return El nombre del artista */
    public String getArtista()   { 
    	return artista; 
    }

    /** @return La duración en segundos (útil para cálculos matemáticos) */
    public int getDuracionSeg()  { 
    	return duracionSeg; 
    }
 
    /**
     * El método toString sirve para decidir CÓMO se verá la canción 
     * cuando la imprimamos con un System.out.println.
     */
    @Override
    public String toString() {
        // Devuelve un formato amigable como: "Blinding Lights - The Weeknd (200s)"
        return (titulo + " - " + artista + " (" + duracionSeg + "s)");
    }
}
