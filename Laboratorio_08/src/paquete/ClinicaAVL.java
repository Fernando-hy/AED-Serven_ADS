package paquete;

import exceptions.ItemDuplicated;
import exceptions.ItemNotFound;
import exceptions.ExceptionIsEmpty;

/**
 * Clase ejecutable para la simulación del sistema de atención clínica usando Árboles AVL.
 * Demuestra de forma simple en consola el flujo de registrar turnos de pacientes,
 * realizar búsquedas rápidas y eliminaciones (atención de pacientes),
 * mostrando el árbol rebalanceado en cada paso.
 */
public class ClinicaAVL {
    public static void main(String[] args) {

        // Inicializar el árbol AVL para los turnos de la clínica
        AVLTree<Integer> turnos = new AVLTree<>();

        // --- REGISTRO DE TURNOS ---
        System.out.println("=== REGISTRANDO TURNOS DE PACIENTES ===");
        int[] nuevos = {15, 5, 20, 3, 10, 30};
        for (int t : nuevos) {
            try {
                turnos.insert(t);
                System.out.println("✅ Turno " + t + " registrado.");
            } catch (ItemDuplicated e) {
                System.out.println("[!] Error al registrar " + t + ": " + e.getMessage());
            }
        }
        
        // Mostrar estructura del árbol resultante de las inserciones
        System.out.println("\nEstructura jerárquica del Árbol AVL de turnos:");
        turnos.printTree();
        System.out.print("Turnos activos (Inorden): "); 
        turnos.inOrder();

        // --- BÚSQUEDA DE TURNOS ---
        System.out.println("\n=== BUSCANDO ESTADO DE TURNOS ===");
        int[] buscar = {10, 99};
        for (int t : buscar) {
            try {
                boolean found = turnos.search(t);
                System.out.println("🔍 Turno " + t + ": " + (found ? "EN ESPERA" : "NO existe"));
            } catch (ItemNotFound e) {
                System.out.println("🔍 Turno " + t + ": NO existe");
            }
        }

        // --- ATENDER (ELIMINAR) TURNOS ---
        System.out.println("\n=== ATENDIENDO PACIENTES (ELIMINACIÓN AVL) ===");
        int[] atendidos = {3, 15, 20};
        for (int t : atendidos) {
            try {
                turnos.delete(t);
                System.out.println("👨‍⚕️ Turno " + t + " atendido y eliminado.");
                turnos.printTree();
                System.out.print("Cola restante (Inorden): "); 
                turnos.inOrder();
            } catch (ExceptionIsEmpty | ItemNotFound e) {
                System.out.println("[!] Error al atender turno " + t + ": " + e.getMessage());
            }
        }
    }
}