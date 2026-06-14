package aplicacion_visual_almacen;

public class Producto implements Comparable<Producto> {
    private String codigoBarras;
    private String nombre;
    private double precio;
    private int stock;
    private int pasillo;
    private String icono; // Nuevo atributo para el logo (emoji)

    public Producto(String codigoBarras, String nombre, double precio, int stock, int pasillo, String icono) {
        this.codigoBarras = codigoBarras;
        this.nombre = nombre;
        this.precio = precio;
        this.stock = stock;
        this.pasillo = pasillo;
        this.icono = icono;
    }

    // --- Getters y Setters ---
    public String getCodigoBarras() {
        return codigoBarras;
    }

    public String getNombre() {
        return nombre;
    }

    public double getPrecio() {
        return precio;
    }

    public int getStock() {
        return stock;
    }

    public void setStock(int stock) {
        this.stock = stock;
    } // Fundamental para actualizar sin borrar

    public int getPasillo() {
        return pasillo;
    }

    public String getIcono() {
        return icono;
    }

    // --- Comparación para el Árbol B ---
    @Override
    public int compareTo(Producto otro) {
        // El Árbol B ordenará los productos según su código de barras
        return this.codigoBarras.compareTo(otro.codigoBarras);
    }

    @Override
    public String toString() {
        // Versión ultra compacta para que el Árbol B se dibuje de forma limpia e
        // ilustrada
        return codigoBarras + " " + icono;
    }

    // Constructor simplificado solo para búsquedas (usado en get/search/remove)
    public static Producto soloParaBusqueda(String codigoBarras) {
        return new Producto(codigoBarras, "", 0.0, 0, 0, "");
    }
}