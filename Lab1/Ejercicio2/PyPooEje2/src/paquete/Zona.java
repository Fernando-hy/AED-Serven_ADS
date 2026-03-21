package paquete;

// Clase que aplica abstracción para representar una celda del terreno minero.
public class Zona {
    // Atributos privados para garantizar el encapsulamiento de los datos.
    private String mineral;
    private double cantidad;
    private double pureza;

    // Constructor para inicializar el objeto. Uso de 'this' para diferenciar atributos de parámetros.
    public Zona(String mineral, double cantidad, double pureza) {
        this.mineral = mineral;
        this.cantidad = cantidad;
        this.pureza = pureza;
    }

    // Métodos mutadores (setters) para modificar el estado encapsulado.
    public void setMineral(String mineral) { 
    	this.mineral = mineral; 
    }
    public void setCantidad(double cantidad) { 
    	this.cantidad = cantidad;
    }
    public void setPureza(double pureza) { 
    	this.pureza = pureza;
    }

    // Métodos accesores (getters) para obtener el valor de los atributos privados.
    public String getMineral() { return this.mineral; }
    public double getCantidad() { return this.cantidad; }
    public double getPureza() { return this.pureza; }

    // Método de instancia. Utiliza los atributos propios del objeto para un cálculo.
    public double calcularValorEconomico() {
        return this.cantidad * this.pureza;
    }
    
    // Método que retorna el estado del objeto en formato de texto.
    public String toString() {
        return this.mineral + " cantidad: " + this.cantidad + " pureza: " + this.pureza;
    }
}