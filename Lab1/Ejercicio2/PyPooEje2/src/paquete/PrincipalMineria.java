package paquete;

import java.io.File;
import java.util.Scanner;
import java.util.Locale;

// Clase principal que encapsula la matriz para evitar variables globales.
public class PrincipalMineria {

    // Matriz bidimensional privada (Estructura de datos encapsulada).
    private Zona[][] matrizMinera;

    // Constructor. Inicializa la estructura en memoria.
    public PrincipalMineria(int filas, int columnas) {
        this.matrizMinera = new Zona[filas][columnas];
    }

    // Método de instancia para guardar un objeto Zona en la matriz.
    public void registrarZona(int fila, int columna, Zona zona) {
        this.matrizMinera[fila][columna] = zona;
    }

    // Método de instancia que recorre e imprime los objetos almacenados.
    public void mostrarMatriz() {
        for (int i = 0; i < this.matrizMinera.length; i++) {
            for (int j = 0; j < this.matrizMinera[i].length; j++) {
                System.out.print(this.matrizMinera[i][j].toString() + " | ");
            }
            System.out.println();
        }
    }

    // Algoritmo de búsqueda. 'k' es el tamaño de la submatriz cuadrada a buscar.
    public void encontrarRegionValiosa(int k) {
        double maxValor = -1;
        int filaInicio = 0;  
        int colInicio = 0; 
        
        // El límite "- k" evita salir de la matriz al buscar el punto de origen.
        for (int i = 0; i <= this.matrizMinera.length - k; i++) { 
            for (int j = 0; j <= this.matrizMinera[0].length - k; j++) {
                
                double sumaActual = 0;
                
                // El límite "+ k" restringe la suma estrictamente al área de la submatriz.
                for (int f = i; f < i + k; f++) {
                    for (int c = j; c < j + k; c++) {
                        sumaActual += this.matrizMinera[f][c].calcularValorEconomico();
                    }
                }

                // Guarda las coordenadas si esta submatriz es la más valiosa.
                if (sumaActual > maxValor) {
                    maxValor = sumaActual;
                    filaInicio = i;
                    colInicio = j;
                }
            }
        }
        generarReporte(filaInicio, colInicio, k, maxValor);
    }

    // Métodos privados. Solo accesibles internamente por el propio objeto.
    private void generarReporte(int fIni, int cIni, int k, double valorTotal) {
        System.out.println("\nRegión más valiosa encontrada:\n");
        System.out.println("Posición inicial: (" + fIni + "," + cIni + ")");
        System.out.println("Tamaño de la región: " + k + " x " + k + "\n");
        
        System.out.println("Zonas analizadas:");
        // Usa "+ k" nuevamente para imprimir solo la región ganadora.
        for (int i = fIni; i < fIni + k; i++) {
            for (int j = cIni; j < cIni + k; j++) {
                System.out.println("[ " + this.matrizMinera[i][j].toString() + " ]");
            }
        }

        System.out.println("\nValor total estimado: " + String.format(Locale.US, "%.2f", valorTotal));
        System.out.println("Mineral predominante en la región: " + determinarPredominante(fIni, cIni, k));
    }

    // Método privado que cuenta la frecuencia consultando el estado de cada objeto Zona.
    private String determinarPredominante(int fIni, int cIni, int k) {
        int oro = 0, plata = 0, cobre = 0;
        for (int i = fIni; i < fIni + k; i++) {
            for (int j = cIni; j < cIni + k; j++) {
                String m = this.matrizMinera[i][j].getMineral();
                if (m.equalsIgnoreCase("Oro")) oro++;
                else if (m.equalsIgnoreCase("Plata")) plata++;
                else if (m.equalsIgnoreCase("Cobre")) cobre++;
            }
        }
        if (oro >= plata && oro >= cobre) return "Oro";
        if (plata >= oro && plata >= cobre) return "Plata";
        return "Cobre";
    }

    // Punto de entrada.
    public static void main(String[] args) {
        String nombreArchivo = "datos.txt";
        
        try {
            File archivo = new File(nombreArchivo);
            Scanner lector = new Scanner(archivo);
            lector.useLocale(Locale.US); // Para leer decimales con punto

            int filas = lector.nextInt();
            int columnas = lector.nextInt();

            // Instanciación de la propia clase principal para evitar métodos estáticos sueltos.
            PrincipalMineria programa = new PrincipalMineria(filas, columnas);

            for (int i = 0; i < filas; i++) {
                for (int j = 0; j < columnas; j++) {
                    String mineral = lector.next();
                    double cantidad = lector.nextDouble();
                    double pureza = lector.nextDouble();

                    // Instanciación de objetos Zona (Composición).
                    Zona nuevaZona = new Zona(mineral, cantidad, pureza);
                    programa.registrarZona(i, j, nuevaZona);
                }
            }
            
            lector.close();
            System.out.println("Matriz cargada exitosamente de " + filas + "x" + columnas + "\n");

            // Ejecución de la lógica mediante los métodos de instancia.
            programa.mostrarMatriz();
            programa.encontrarRegionValiosa(2);

        } catch (Exception e) {
            System.out.println("Ocurrió un error en la ejecución: " + e.getMessage());
        }
    }
}