import java.util.Scanner;
import java.util.Random;

public class Juego {

    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);
        Random rand = new Random();

        int size = 10;
        char[][] tablero = new char[size][size];

        for(int i = 0; i < size; i++)
            for(int j = 0; j < size; j++)
                tablero[i][j] = '~';

        int barcos = 5;
        for(int b = 0; b < barcos; b++) {
            int fila, col;
            do {
                fila = rand.nextInt(size);
                col = rand.nextInt(size);
            } while(tablero[fila][col] == 'B');
            tablero[fila][col] = 'B';
        }

        System.out.println("¡Bienvenido al tablero de Batalla Naval!");

        while(true) {

            mostrarTablero(tablero);

            System.out.print("Ingresa una fila (0-9): ");
            int fila = sc.nextInt();
            System.out.print("Ingresa una columna (0-9): ");
            int col = sc.nextInt();

            if(fila < 0 || fila >= size || col < 0 || col >= size) {
                System.out.println("Coordenadas inválidas.");
                continue;
            }

            if(tablero[fila][col] == 'B') {
                System.out.println("Destruiste un barco");
                tablero[fila][col] = 'X';
            } 
            else if(tablero[fila][col] == '~') {
                System.out.println("Tiraste al agua");
                tablero[fila][col] = 'O';
            } 
            else {
                System.out.println("Ya disparaste ahí");
            }

            if(verificarVictoria(tablero)) {
                mostrarTablero(tablero);
                System.out.println("¡Ganaste! Hundiste todos los barcos");
                break;
            }
        }
    }

    public static void mostrarTablero(char[][] tablero) {
        System.out.println("\nTABLERO:");
        for(int i = 0; i < tablero.length; i++) {
            for(int j = 0; j < tablero[i].length; j++) {
                if(tablero[i][j] == 'B')
                    System.out.print("~ ");
                else
                    System.out.print(tablero[i][j] + " ");
            }
            System.out.println();
        }
    }

    public static boolean verificarVictoria(char[][] tablero) {
        for(int i = 0; i < tablero.length; i++)
            for(int j = 0; j < tablero[i].length; j++)
                if(tablero[i][j] == 'B')
                    return false;
        return true;
    }
}
