package paquete;

// Clase de prueba para la estación de energía genérica delimitada
public class TestEstacion {
	
    public static void main(String[] args) {
        // La estación solo permite clases que implementen la interfaz 'Cargable'
        // En este caso, creamos una estación específicamente para Smartphones
        PowerStation<Smartphone> zonaCelulares = new PowerStation<>();

        // Creamos un par de teléfonos
        Smartphone s1 = new Smartphone("iPhone 15", 20.5);
        Smartphone s2 = new Smartphone("Galaxy S24", 25.0);

        // Conectamos los teléfonos a la estación
        zonaCelulares.conectar(s1);
        zonaCelulares.conectar(s2);

        // Simulamos la carga: aumentamos la batería de cada uno
        s1.cargar(50);
        s2.cargar(35);

        // Imprimimos el reporte final de la zona de carga
        System.out.println("--- REPORTE DE ESTACIÓN DE CARGA ---");
        zonaCelulares.mostrarReporte();
        
        // El consumo total es la suma de los consumos individuales de los aparatos conectados
        System.out.println("Consumo Total de la Estación: " + zonaCelulares.calcularConsumoTotal() + "W");

        // Probamos el método de búsqueda de dispositivos por su modelo
        System.out.println("Búsqueda iPhone 15: Posición (Base 1): " + (zonaCelulares.buscarDispositivo(new Smartphone("iPhone 15", 0)) + 1));
    }
}