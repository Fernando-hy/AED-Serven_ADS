package paquete;

import exceptions.ItemNotFound;

/**
 * Ejemplo práctico de gestión de inventario en un almacén utilizando un Árbol AVL.
 * Demuestra de forma exhaustiva la inserción secuencial de códigos de producto,
 * la visualización detallada del árbol (mostrando factores de equilibrio),
 * la realización de recorridos en profundidad y por niveles (BFS recursivo),
 * la búsqueda rápida de códigos y finalmente la descontinuación (eliminación) de productos.
 */
public class EjemploAlmacen {

    /**
     * Dibuja un título enmarcado en consola.
     */
    private static void titulo(String t) {
        System.out.println("\n╔══════════════════════════════════════════════════════╗");
        System.out.println("║  " + t);
        System.out.println("╚══════════════════════════════════════════════════════╝");
    }

    /**
     * Imprime un separador de sección en consola.
     */
    private static void seccion(String s) {
        System.out.println("\n── " + s + " ──────────────────────────────────────────────────");
    }

    public static void main(String[] args) throws ItemNotFound {
        titulo("SISTEMA DE GESTIÓN DE INVENTARIO (ALMACÉN AVL)");

        AVLTree<Integer> inventario = new AVLTree<>();

        // Secuencia diseñada para gatillar diferentes tipos de rotaciones (RSL, RSR, RDL y RDR)
        int[] codigos = {100, 120, 110, 130, 105, 115, 200, 190, 180, 175, 185, 210, 205};

        seccion("INSERCIÓN DE PRODUCTOS (CÓDIGOS DE INVENTARIO)");
        for (int cod : codigos) {
            try {
                System.out.print("  Insertando código de barras " + cod + " ... ");
                inventario.insert(cod);
                System.out.println("OK");
            } catch (Exception e) {
                System.out.println("ERROR: " + e.getMessage());
            }
        }

        seccion("ESTRUCTURA DEL ÁRBOL AVL (bf = factor de equilibrio)");
        inventario.printTree();

        seccion("INVENTARIO ORDENADO (Recorrido Inorden)");
        System.out.print("  Listado de productos: ");
        inventario.inOrder();

        seccion("ALTURA DE LA ESTRUCTURA AVL");
        System.out.println("  Altura máxima = " + inventario.height());

        seccion("RECORRIDO POR NIVELES (BFS Recursivo - Niveles de Estantería)");
        inventario.bfsRecursivo();

        seccion("BÚSQUEDA RÁPIDA DE PRODUCTOS (O(log n))");
        int[] buscar = {110, 175, 999, 205, 50};
        for (int cod : buscar) {
            System.out.printf("  Consultando código %4d → %s%n", cod,
                inventario.search(cod) ? "EN ALMACÉN" : "NO REGISTRADO");
        }

        seccion("ELIMINACIÓN DE PRODUCTOS (DESCONTINUADOS)");
        int[] eliminar = {110, 200, 100};
        for (int cod : eliminar) {
            try {
                System.out.println("  Eliminando código " + cod + "...");
                inventario.delete(cod);
                System.out.print("  Inventario restante (Inorden): ");
                inventario.inOrder();
            } catch (Exception e) {
                System.out.println("  ERROR al eliminar: " + e.getMessage());
            }
        }

        seccion("ESTRUCTURA FINAL DEL ÁRBOL AVL");
        inventario.printTree();
    }
}