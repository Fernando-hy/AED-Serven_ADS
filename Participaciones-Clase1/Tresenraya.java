package paquete;

import java.util.Scanner;

public class Tresenraya {

    static void mostrarMatriz(String[][] matriz) {
        for(int i = 0; i < matriz.length; i++) {
            for(int j = 0; j < matriz[i].length; j++) {
                System.out.print(matriz[i][j] + " ");
            }
            System.out.println();
        }
        System.out.println();
    }

    // RECURSIVO: verifica si el tablero está lleno
    static boolean tableroLleno(String[][] matriz, int i, int j) {
        if(i == 3) return true;

        if(matriz[i][j].equals("i")) return false;

        if(j == 2)
            return tableroLleno(matriz, i + 1, 0);
        else
            return tableroLleno(matriz, i, j + 1);
    }

    // Verifica ganador
    static boolean hayGanador(String[][] m, String jugador) {

        for(int i = 0; i < 3; i++)
            if(m[i][0].equals(jugador) && m[i][1].equals(jugador) && m[i][2].equals(jugador))
                return true;

        for(int j = 0; j < 3; j++)
            if(m[0][j].equals(jugador) && m[1][j].equals(jugador) && m[2][j].equals(jugador))
                return true;

        if(m[0][0].equals(jugador) && m[1][1].equals(jugador) && m[2][2].equals(jugador))
            return true;

        if(m[0][2].equals(jugador) && m[1][1].equals(jugador) && m[2][0].equals(jugador))
            return true;

        return false;
    }

    // TURNO RECURSIVO
    static void turnoRecursivo(String[][] matriz, String turno, Scanner entrada) {

        if(tableroLleno(matriz,0,0)) {
            System.out.println("Empate");
            return;
        }

        System.out.println("Turno de " + turno);

        int fila, columna;

        do {
            System.out.print("Fila (0-2): ");
            fila = entrada.nextInt();

            System.out.print("Columna (0-2): ");
            columna = entrada.nextInt();

        } while(fila < 0 || fila > 2 || columna < 0 || columna > 2 || !matriz[fila][columna].equals("i"));

        matriz[fila][columna] = turno;

        mostrarMatriz(matriz);

        if(hayGanador(matriz, turno)) {
            System.out.println("Ganó " + turno);
            return;
        }

        String siguiente = turno.equals("X") ? "O" : "X";

        turnoRecursivo(matriz, siguiente, entrada);
    }

    public static void main(String[] args) {

        Scanner entrada = new Scanner(System.in);

        String[][] matriz = new String[3][3];

        for(int i = 0; i < 3; i++)
            for(int j = 0; j < 3; j++)
                matriz[i][j] = "i";

        mostrarMatriz(matriz);

        turnoRecursivo(matriz, "X", entrada);

        entrada.close();
    }
}
