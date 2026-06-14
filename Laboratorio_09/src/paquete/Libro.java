package paquete; // paquete donde se encuentra la clase Libro

public class Libro implements Comparable<Libro> { // clase Libro que se puede ordenar comparando instancias
    private String isbn; // campo privado que almacena el ISBN del libro
    private String titulo; // campo privado que almacena el título del libro
    private String autor; // campo privado que almacena el autor del libro
    private int anio; // campo privado que almacena el año de publicación

    public Libro(String isbn, String titulo, String autor, int anio) { // constructor que recibe todos los campos
        this.isbn = isbn; // asigna el ISBN recibido al campo interno
        this.titulo = titulo; // asigna el título recibido al campo interno
        this.autor = autor; // asigna el autor recibido al campo interno
        this.anio = anio; // asigna el año recibido al campo interno
    }

    @Override
    public int compareTo(Libro otro) { // compara este libro con otro Libro
        return this.isbn.compareTo(otro.isbn); // compara los ISBN como cadenas
    }

    @Override
    public String toString() { // convierte el libro a una representación de texto
        return isbn + " | " + titulo + " | " + autor + " | " + anio; // concatena campos separados por barras
    }

    public String getIsbn() { return isbn; } // devuelve el ISBN del libro
}