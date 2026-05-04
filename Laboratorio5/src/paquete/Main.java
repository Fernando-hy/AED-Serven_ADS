package paquete;

/**
 * Clase Principal (Main)
 * Aquí se ejecutan todas las pruebas de los algoritmos y estructuras 
 * que hemos definido en los otros archivos.
 */
public class Main {

	public static void main(String[] args) {
		// --- PRUEBA DEL GESTOR DE TAREAS ---
		System.out.println("--- GESTOR DE TAREAS ---");
		GestorDeTareas<Tarea> gestor = new GestorDeTareas<>();
		
		// Agregamos varias tareas con diferentes niveles de prioridad (1 es más urgente)
		gestor.agregarTarea(new Tarea("Diseñar BD", 2, "pendiente"));
		gestor.agregarTarea(new Tarea("Deploy produccion", 1, "pendiente"));
		gestor.agregarTarea(new Tarea("Documentar API", 3, "completada"));
		gestor.agregarTarea(new Tarea("Code review", 2, "pendiente"));
		gestor.agregarTarea(new Tarea("Corregir bug #42", 1, "completada"));
		
		// Imprimimos la lista tal como se insertó
		gestor.imprimirTareas();
		
		// Probamos el algoritmo de búsqueda de la tarea más prioritaria
		System.out.println("Mas prioritaria: " + gestor.obtenerTareaMasPrioritaria());
		
		// Probamos la inversión de la lista
		System.out.println("\nInvirtiendo el orden de las tareas...");
		gestor.invertirTareas();
		gestor.imprimirTareas();
		
		// --- PRUEBAS DE LOS EJERCICIOS 1 AL 6 ---
		System.out.println("\n--- PRUEBAS EJERCICIOS 1-6 ---");
 
        // Creamos listas de números para probar los algoritmos genéricos
        ListLinked<Integer> lista1 = new ListLinked<>();
        lista1.insertLast(1); lista1.insertLast(2); lista1.insertLast(3);
 
        ListLinked<Integer> lista2 = new ListLinked<>();
        lista2.insertLast(1); lista2.insertLast(2); lista2.insertLast(3);
 
        ListLinked<Integer> lista3 = new ListLinked<>();
        lista3.insertLast(4); lista3.insertLast(5);
 
        System.out.print("Lista 1: "); lista1.print();
        System.out.print("Lista 2: "); lista2.print();
        System.out.print("Lista 3: "); lista3.print();
 
        // Prueba Ejercicio 1: Buscar
        System.out.println("Ej 1 - buscarElemento(lista1, 2): " + Ejercicios.buscarElemento(lista1, 2));
        System.out.println("Ej 1 - buscarElemento(lista1, 9): " + Ejercicios.buscarElemento(lista1, 9));
 
        // Prueba Ejercicio 2: Invertir (creando nueva lista)
        ListLinked<Integer> invertida = Ejercicios.invertirLista(lista1);
        System.out.print("Ej 2 - invertirLista(lista1): "); invertida.print();
 
        // Prueba Ejercicio 4: Contar
        System.out.println("Ej 4 - contarNodos(lista1): " + Ejercicios.contarNodos(lista1.getFirst()));
 
        // Prueba Ejercicio 5: Comparar si son iguales
        System.out.println("Ej 5 - sonIguales(lista1, lista2): " + Ejercicios.sonIguales(lista1, lista2));
        System.out.println("Ej 5 - sonIguales(lista1, lista3): " + Ejercicios.sonIguales(lista1, lista3));
 
        // Prueba Ejercicio 6: Concatenar (Unir dos listas)
        ListLinked<Integer> concat = Ejercicios.concatenarListas(lista1, lista3);
        System.out.print("Ej 6 - concatenarListas(lista1, lista3): "); concat.print();
 
        // --- PRUEBA EJERCICIO 7: LISTA SIEMPRE ORDENADA ---
        System.out.println("\n--- PRUEBA EJERCICIO 7 (SortedListLinked) ---");
        
        SortedListLinked<Tarea> listOrdenada = new SortedListLinked<>();
        // Insertamos tareas desordenadas, pero la lista las acomodará por prioridad automáticamente
        listOrdenada.insertOrden(new Tarea("Diseñar BD",        2, "pendiente"));
        listOrdenada.insertOrden(new Tarea("Deploy produccion", 1, "pendiente"));
        listOrdenada.insertOrden(new Tarea("Documentar API",    3, "completada"));
        listOrdenada.insertOrden(new Tarea("Code review",       2, "pendiente"));
        listOrdenada.insertOrden(new Tarea("Corregir bug #42",  1, "completada"));
        
        System.out.println("Tareas en orden ascendente de prioridad (1 es primero):");
        listOrdenada.print();
 
        // --- PRUEBA EJERCICIO 8: SPOTIFY SHUFFLE ---
        System.out.println("\n--- PRUEBA EJERCICIO 8 (Cola de Reproducción) ---");
        
        ColaReproduccion<Cancion> cola = new ColaReproduccion<>();
        cola.agregarCancion(new Cancion("Bohemian Rhapsody",  "Queen",      354));
        cola.agregarCancion(new Cancion("Blinding Lights",    "The Weeknd", 200));
        cola.agregarCancion(new Cancion("Shape of You",       "Ed Sheeran", 234));
        cola.agregarCancion(new Cancion("Hotel California",   "Eagles",     391));
        cola.agregarCancion(new Cancion("Smells Like Teen Spirit", "Nirvana", 301));
        cola.agregarCancion(new Cancion("Rolling in the Deep","Adele",      228));
 
        System.out.println("Cola de Reproducción Inicial:");
        cola.mostrarCola();
 
        // Navegación hacia adelante
        System.out.println("\nSimulando 'Siguiente' 3 veces:");
        cola.reproducirSiguiente();
        cola.reproducirSiguiente();
        cola.reproducirSiguiente();
        cola.mostrarCola();
 
        // Navegación hacia atrás
        System.out.println("\nSimulando 'Anterior' 1 vez:");
        cola.reproducirAnterior();
        cola.mostrarCola();
 
        // Mezcla aleatoria
        System.out.println("\nActivando Modo Mezcla (Shuffle):");
        cola.mezclar();
        cola.mostrarCola();
 
        // Cálculo de tiempo total
        int totalSeg = cola.duracionTotal();
        System.out.println("\nDuración total de la cola: " + ColaReproduccion.formatearDuracion(totalSeg));
	}
}
