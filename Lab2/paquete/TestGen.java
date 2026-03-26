package paquete;

// Clase de prueba para ejercitar la Cajonería con diferentes tipos de objetos
public class TestGen {
    public static void main(String[] args) {
        // Creamos una cajonería específicamente para Golosinas con tope de 6
        Cajoneria<Golosina> miCajoneria = new Cajoneria<>(6);

        // Llenamos la cajonería con cajas de diferentes colores y golosinas
        miCajoneria.add(new Caja<>("Rojo", new Golosina("Ositos", 0.5)));
        miCajoneria.add(new Caja<>("Azul", new Golosina("Paleta", 1.6)));
        miCajoneria.add(new Caja<>("Verde", new Golosina("Chicle", 1.0)));
        miCajoneria.add(new Caja<>("Amarillo", new Golosina("Caramelo", 0.2)));
        miCajoneria.add(new Caja<>("Blanco", new Golosina("Chocolate", 2.5)));

        System.out.println("--- REPORTE INICIAL DE LA CAJONERÍA ---");
        // Imprime la tabla completa usando el método toString() de Cajoneria
        System.out.println(miCajoneria);

        System.out.println("--- PRUEBAS DE BÚSQUEDA ---");
        // Buscamos una golosina que SÍ existe (mismo nombre y peso)
        Golosina buscarG = new Golosina("Paleta", 1.6);
        System.out.println("Buscando Paleta 1.6g: " + miCajoneria.search(buscarG));

        // Buscamos una golosina que NO existe
        Golosina noExiste = new Golosina("Menta", 0.1);
        System.out.println("Buscando Menta 0.1g: " + miCajoneria.search(noExiste));

        System.out.println("\n--- PRUEBA DE ELIMINACIÓN ---");
        // Intentamos eliminar una golosina de la cajonería
        Golosina eliminada = miCajoneria.delete(new Golosina("Chicle", 1.0));
        
        if (eliminada != null) {
            System.out.println("Se eliminó con éxito: " + eliminada);
        } else {
            System.out.println("No se pudo eliminar (no encontrado)");
        }

        System.out.println("\n--- REPORTE FINAL TRAS ELIMINAR ---");
        System.out.println(miCajoneria);
        
        // Prueba de conteo de elementos repetidos
        miCajoneria.add(new Caja<>("Negro", new Golosina("Ositos", 0.5)));
        Golosina aContar = new Golosina("Ositos", 0.5);
        int repeticiones = miCajoneria.contar(aContar);
        
        System.out.println("La golosina 'Ositos 0.5g' aparece: " + repeticiones + " veces.");
    
        System.out.println("\n--- PRUEBAS CON CHOCOLATINAS ---");
        // La Cajonería es genérica, así que ahora la usamos con Chocolatina
        Cajoneria<Chocolatina> cajonChoco = new Cajoneria<>(5);

        cajonChoco.add(new Caja<>("Dorado", new Chocolatina("Ferrero")));
        cajonChoco.add(new Caja<>("Rojo", new Chocolatina("KitKat")));
        cajonChoco.add(new Caja<>("Azul", new Chocolatina("Sublime")));

        System.out.println(cajonChoco);
        
        // Búsqueda en la cajonería de chocolatinas
        Chocolatina buscarCh = new Chocolatina("Ferrero");
        System.out.println("Buscando Ferrero: " + cajonChoco.search(buscarCh));
    }
}