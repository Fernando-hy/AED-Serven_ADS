package paquete;

// Clase Principal para probar el funcionamiento de la Bolsa genérica
public class Principal {
	public static void main(String[] args) {
		// Creamos una bolsa que solo aceptará objetos de tipo Chocolatina
		// El límite de capacidad es 3
		Bolsa <Chocolatina> bolsaCho = new Bolsa <Chocolatina> (3);
		Chocolatina c = new Chocolatina("canonazo");
		Chocolatina c1 = new Chocolatina("canonazo");
		Chocolatina c2 = new Chocolatina("sublime");
		
		// Añadimos las chocolatinas a la bolsa
		bolsaCho.add(c);
		bolsaCho.add(c1);
		bolsaCho.add(c2);
		
		// Creamos otra bolsa, esta vez para objetos de tipo Golosina
		Bolsa <Golosina> bolsaGo = new Bolsa <Golosina> (3);
		Golosina g = new Golosina("Ositos", 0.5);
		Golosina g1 = new Golosina("Paleta", 1.6);
		Golosina g2 = new Golosina("Chicle", 1.0);
		
		// Añadimos las golosinas
		bolsaGo.add(g);
		bolsaGo.add(g1);
		bolsaGo.add(g2);
		
		// Mostramos el contenido de cada bolsa usando el método interno
		System.out.println("--- Contenido de la Bolsa de Chocolatinas ---");
		bolsaCho.mostrarElementos();
		
		System.out.println("--- Contenido de la Bolsa de Golosinas ---");
		bolsaGo.mostrarElementos();
		
		// También podemos usar el método estático genérico para imprimir
		System.out.println("--- Imprimiendo usando método estático ---");
		Bolsa.imprimirBolsa(bolsaGo);
		Bolsa.imprimirBolsa(bolsaCho);
	}
}

