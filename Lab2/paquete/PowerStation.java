package paquete;

import java.util.ArrayList;

// Clase genérica delimitada: T solo puede ser una clase que implemente 'Cargable'
public class PowerStation <T extends Cargable> {
    // Lista de dispositivos conectados a la estación
    private ArrayList<T> dispositivos = new ArrayList<>();

    // "Enchufa" un dispositivo a la estación
    public void conectar(T dispositivo) {
        dispositivos.add(dispositivo);
    }

    // Suma el consumo de todos los dispositivos conectados actualmente
    public double calcularConsumoTotal() {
        double total = 0;
        for (T d : dispositivos) {
            // Podemos llamar a getConsumoVatios() porque T hereda de Cargable
            total += d.getConsumoVatios();
        }
        return total;
    }

    // Busca un dispositivo específico y retorna su índice (posición en la lista)
    public int buscarDispositivo(T prototipo) {
        for (int i = 0; i < dispositivos.size(); i++) {
            // Usa el equals() definido en la clase específica (Smartphone/Laptop)
            if (dispositivos.get(i).equals(prototipo)) {
                return i; // Encontrado
            }
        }
        return -1; // No encontrado
    }

    // Imprime un reporte detallado en consola de todos los dispositivos
    public void mostrarReporte() {
        System.out.println("Posición\tConsumo (W)\tDispositivo");
        System.out.println("----------------------------------------------");
        for (int i = 0; i < dispositivos.size(); i++) {
            T d = dispositivos.get(i);
            // Muestra posición, consumo y la info de toString()
            System.out.println((i + 1) + "\t\t" + d.getConsumoVatios() + "\t\t" + d);
        }
    }
}