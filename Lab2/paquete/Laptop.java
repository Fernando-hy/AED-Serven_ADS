package paquete;

// Clase Laptop que implementa la interfaz Cargable
public class Laptop implements Cargable {
    private String marca;
    private double consumoVatios;
    // Nivel de batería como porcentaje (0 a 100)
    private int nivelBateria;

    // Constructor que define marca y consumo, e inicia la batería en 0
    public Laptop(String marca, double consumoVatios) {
        this.marca = marca;
        this.consumoVatios = consumoVatios;
        this.nivelBateria = 0;
    }

    // Implementación obligatoria de Cargable: retorna el consumo del equipo
    @Override
    public double getConsumoVatios() { 
        return this.consumoVatios; 
    }

    // Implementación obligatoria de Cargable: retorna el nivel actual de carga
    @Override
    public int getNivelBateria() { 
        return this.nivelBateria;
    }

    public void setNivelBateria(int nivelBateria) {
        this.nivelBateria = nivelBateria;
    }

    // Implementación obligatoria de Cargable: aumenta la batería sin pasar del 100%
    @Override
    public void cargar(int cantidad) {
        this.nivelBateria += cantidad;
        if (this.nivelBateria > 100) this.nivelBateria = 100;
    }

    public String getMarca() { 
        return this.marca;
    }
    
    public void setMarca(String marca) {
        this.marca = marca;
    }

    public void setConsumoVatios(double consumoVatios) {
        this.consumoVatios = consumoVatios;
    }

    // Comprobación de igualdad basada en la marca
    @Override
    public boolean equals(Object obj) {
        if (obj instanceof Laptop otra) {
            return this.marca.equals(otra.marca);
        }
        return false;
    }

    // Formato de texto para el reporte de la estación
    @Override
    public String toString() {
        return "Laptop: " + marca + " (Consumo: " + consumoVatios + "W, Batería: " + nivelBateria + "%)";
    }
}