package paquete;

import exceptions.ItemDuplicated;
import exceptions.ItemNotFound;
import exceptions.ExceptionIsEmpty;

/**
 * Aplicación de simulación: Gestor de Tickets de Atención al Cliente mediante Árbol AVL.
 * Demuestra la inserción de tickets, su ordenamiento automático (recorrido inorden),
 * la búsqueda rápida de la existencia de un ticket, y la cancelación o atención (eliminación)
 * de los mismos de forma estructurada en consola.
 */
public class GestorTicketsAVL {
    public static void main(String[] args) {

        // Inicializar el árbol AVL que gestionará las prioridades o identificadores de tickets
        AVLTree<Integer> gestor = new AVLTree<>();

        // --- INSERCIÓN DE TICKETS ---
        System.out.println("=================================================");
        System.out.println("  FASE 1: INSERCIÓN DE TICKETS DE ATENCIÓN  ");
        System.out.println("=================================================");
        int[] tickets = {30, 10, 20, 40, 50, 25};
        for (int t : tickets) {
            try {
                System.out.println("🎟️ Insertando ticket número: " + t);
                gestor.insert(t);
                gestor.printTree();
                System.out.println();
            } catch (ItemDuplicated e) {
                System.out.println("[!] Error duplicado: " + e.getMessage());
            }
        }

        // --- RECORRIDO INORDEN (Tickets Ordenados) ---
        System.out.println("=================================================");
        System.out.println("  FASE 2: RECORRIDO DE TICKETS ACTIVOS  ");
        System.out.println("=================================================");
        System.out.print("📋 Lista de espera ordenada (Inorden): ");
        gestor.inOrder();
        System.out.println();

        // --- BÚSQUEDA DE TICKETS ---
        System.out.println("=================================================");
        System.out.println("  FASE 3: BÚSQUEDA DE TICKETS EN COLA  ");
        System.out.println("=================================================");
        int[] buscar = {20, 60};
        for (int b : buscar) {
            try {
                boolean encontrado = gestor.search(b);
                System.out.println("🔍 Consultando ticket " + b + " → " + 
                    (encontrado ? "EN ESPERA (ACTIVO)" : "NO ENCONTRADO"));
            } catch (ItemNotFound e) {
                System.out.println("🔍 Consultando ticket " + b + " → NO ENCONTRADO (" + e.getMessage() + ")");
            }
        }
        System.out.println();

        // --- ELIMINACIÓN DE TICKETS ---
        System.out.println("=================================================");
        System.out.println("  FASE 4: ATENCIÓN / CANCELACIÓN DE TICKETS  ");
        System.out.println("=================================================");
        int[] eliminar = {10, 40, 30};
        for (int e : eliminar) {
            try {
                System.out.println("👨‍💼 Atendiendo/Eliminando ticket número: " + e);
                gestor.delete(e);
                gestor.printTree();
                System.out.print("📋 Tickets restantes activos: ");
                gestor.inOrder();
                System.out.println();
            } catch (ExceptionIsEmpty | ItemNotFound ex) {
                System.out.println("[!] Error al procesar ticket " + e + ": " + ex.getMessage());
            }
        }

        // --- ESTADO FINAL ---
        System.out.println("=================================================");
        System.out.println("  ESTADO FINAL DEL GESTOR DE TICKETS  ");
        System.out.println("=================================================");
        System.out.println("Estructura final del Árbol AVL:");
        gestor.printTree();
        System.out.print("📋 Listado final de tickets en cola: ");
        gestor.inOrder();
        System.out.println("📐 Altura final de la estructura: " + gestor.height());
    }
}