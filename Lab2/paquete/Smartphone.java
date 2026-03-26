package paquete;

// Clase Smartphone que implementa la interfaz Cargable
public class Smartphone implements Cargable {
    private String modelo;
    private double consumoVatios;
    private int nivelBateria;

    // Constructor: inicializa modelo, consumo y batería al 0%
    public Smartphone(String modelo, double consumoVatios) {
        this.modelo = modelo;
        this.consumoVatios = consumoVatios;
        this.nivelBateria = 0; 
    }

    // Método de la interfaz: retorna vatios consumidos
    @Override
    public double getConsumoVatios() { 
        return this.consumoVatios; 
    }
    
    // Método de la interfaz: retorna nivel actual
    @Override
    public int getNivelBateria() { 
        return this.nivelBateria;
    }

    public void setNivelBateria(int nivelBateria) {
        this.nivelBateria = nivelBateria;
    }

    public String getModelo() { 
        return this.modelo;
    }
    
    public void setModelo(String modelo) {
        this.modelo = modelo;
    }

    public void setConsumoVatios(double consumoVatios) {
        this.consumoVatios = consumoVatios;
    }

    // Método de la interfaz: carga el dispositivo hasta un máximo de 100
    @Override
    public void cargar(int cantidad) {
        this.nivelBateria += cantidad;
        if (this.nivelBateria > 100) this.nivelBateria = 100;
    }

    // Define igualdad basada en el modelo del teléfono
    @Override
    public boolean equals(Object obj) {
        if (obj instanceof Smartphone otro) {
            return this.modelo.equals(otro.modelo);
        }
        return false;
    }

    // Texto para ser mostrado en listas o reportes
    @Override
    public String toString() {
        return "Smartphone: " + modelo + " (Batería: " + nivelBateria + "%)";
    }
}