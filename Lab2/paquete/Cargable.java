package paquete;

// Interfaz que define el comportamiento de cualquier objeto que pueda ser cargado
public interface Cargable {
    // Retorna el consumo en vatios del dispositivo
    double getConsumoVatios();
    // Retorna el porcentaje actual de batería (0-100)
    int getNivelBateria();
    // Aumenta el nivel de batería en una cantidad específica
    void cargar(int cantidad);
}