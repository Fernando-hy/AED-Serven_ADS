package paquete;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

public class Biblioteca {
    private BTree<Libro> arbol;

    public Biblioteca(int orden) {
        this.arbol = new BTree<>(orden);
    }

    // agrega un libro al árbol
    public void agregar(Libro libro) {
        arbol.insert(libro);
    }

    // busca libro por ISBN
    public boolean buscar(String isbn) {
        return arbol.search(new Libro(isbn, "", "", 0));
    }

    // elimina libro por ISBN
    public void eliminar(String isbn) {
        arbol.remove(new Libro(isbn, "", "", 0));
    }

    // muestra todos los libros
    public void mostrar() {
        System.out.println(arbol);
    }

    // carga desde archivo biblioteca.txt
    public void cargarDesdeArchivo(String ruta) {
        try (BufferedReader br = new BufferedReader(new FileReader(ruta))) {
            int orden = Integer.parseInt(br.readLine().trim()); // primera línea = orden
            this.arbol = new BTree<>(orden);
            String linea;
            while ((linea = br.readLine()) != null) {
                String[] partes = linea.split(",");
                if (partes.length == 4) {
                    Libro libro = new Libro(
                        partes[0].trim(),
                        partes[1].trim(),
                        partes[2].trim(),
                        Integer.parseInt(partes[3].trim())
                    );
                    arbol.insert(libro);
                }
            }
            System.out.println("Archivo cargado correctamente.");
        } catch (IOException e) {
            System.out.println("Error al leer archivo: " + e.getMessage());
        }
    }

    public static void main(String[] args) {
        Biblioteca bib = new Biblioteca(4);

        // carga desde archivo
        bib.cargarDesdeArchivo("biblioteca.txt");
        bib.mostrar();

        // buscar
        System.out.println("--- Buscar ---");
        bib.buscar("9780132350884");  // existe
        bib.buscar("0000000000000"); // no existe

        // eliminar
        System.out.println("--- Eliminar ---");
        bib.eliminar("9780132350884");
        bib.mostrar();
    }
}