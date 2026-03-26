package paquete;

// Clase para demostrar el uso de métodos genéricos independientes
public class DemoMetodoGenerico {
	
	// Método genérico para comparar si dos arreglos son iguales elemento por elemento
	// T debe implementar Comparable para asegurar coherencia, aunque aquí se usa .equals()
	static <T extends Comparable<T>> boolean igualArrays(T[] x, T[] y) {
		// Si los tamaños son distintos, los arreglos no pueden ser iguales
		if (x.length != y.length)
			return false;
		
		for (int i=0; i<x.length; i++) {
			// Compara el contenido de cada posición usando equals del objeto T
			if(!x[i].equals(y[i]))
				return false;
		}
		// Si termina el ciclo sin encontrar diferencias, son iguales
		return true;
	}

	// Método genérico para verificar si un elemento existe en un arreglo
    public static <T> boolean exist(T[] arreglo, T elemento) {
        for (T item : arreglo) {
            // Importante: se usa equals() para comparar el contenido real de los objetos
            if (item.equals(elemento)) {
                return true;
            }
        }
        return false; // No se encontró tras recorrer todo
    }

	// Método genérico para intercambiar dos elementos de posición en un arreglo
    public static <T> void swap(T[] arreglo, int i, int j) {
        // Validación de seguridad de los índices
        if (i < 0 || i >= arreglo.length || j < 0 || j >= arreglo.length) {
            System.out.println("Índices fuera de rango.");
            return;
        }

        // Intercambio clásico usando una variable temporal de tipo T
        T temp = arreglo[i];
        arreglo[i] = arreglo[j];
        arreglo[j] = temp;
    }

	// Punto de entrada para probar los métodos de esta clase
	public static void main(String[] args) {
		// Pruebas con arreglos de Integers (clase envolvente de int)
		Integer nums[] = {1, 2, 3, 4, 5};
		Integer nums2[] = {1, 2, 3, 4, 5};
		Integer nums3[] = {1, 2, 7, 4, 5};
		
		if (igualArrays(nums, nums))
			System.out.println("nums es igual a nums");
		if (igualArrays(nums, nums2))
			System.out.println("nums es igual a nums2");
		if (igualArrays(nums, nums3))
			System.out.println("nums es igual a num3");
		
		// Pruebas con clases personalizadas (Chocolatina y Golosina)
		Chocolatina chocolates[] = {new Chocolatina("canonazo"), new Chocolatina("triangulo"), new Chocolatina("sublime")};
		Golosina golosinas[] = {new Golosina("Ositos", 0.5), new Golosina("Chicle", 1.0), new Golosina("Paleta", 1.6)};
		
		// Prueba del método exist
        System.out.println("¿Existe canonazo en chocolates?: " + exist(chocolates, new Chocolatina("canonazo")));
        System.out.println("¿Existe Paleta 1.6g en golosinas?: " + exist(golosinas, new Golosina("Paleta", 1.6)));
        
        // Pruebas del método swap (intercambio)
        Integer[] num = {10, 99};
        swap(num, 0, 1);
        System.out.println("Integer intercambiado: " + java.util.Arrays.toString(num));

        String[] txt = {"Hola", "Mundo"};
        swap(txt, 0, 1);
        System.out.println("String intercambiado:  " + java.util.Arrays.toString(txt));

        // Ejemplo con cajas genéricas
        Caja<Golosina>[] cajas = new Caja[] { 
        	new Caja<>("Rojo", new Golosina("Osito", 0.5)), 
        	new Caja<>("Azul", new Golosina("Paleta", 1.6)) 
        };
        swap(cajas, 0, 1);
        System.out.println("Color de Caja en posición 0 tras swap: " + cajas[0].getColor());
	}
}
