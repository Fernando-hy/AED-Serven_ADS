package paquete;

// Clase utilitaria sin atributos de instancia.
public class Verificador {

    // Método de clase (estático). Evalúa dos objetos sin necesidad de instanciar la clase Verificador.
    public static boolean esSobrePos(Rectangulo r1, Rectangulo r2) {
        // Se sobreponen si hay una intersección real en ambos ejes (área > 0)
        boolean sobreponeX = r1.getMinX() < r2.getMaxX() && r1.getMaxX() > r2.getMinX();
        boolean sobreponeY = r1.getMinY() < r2.getMaxY() && r1.getMaxY() > r2.getMinY();

        return sobreponeX && sobreponeY;
    }

    // Método de clase que invoca internamente otro método estático.
    public static boolean esJunto(Rectangulo r1, Rectangulo r2) {
        // Si ya se sobreponen, no pueden estar "solo" juntos
        if (esSobrePos(r1, r2)) {
            return false;
        }

        // Comparten un eje X y sus rangos en Y coinciden o se tocan
        boolean tocanLado = (r1.getMaxX() == r2.getMinX() || r2.getMaxX() == r1.getMinX()) &&
                            (r1.getMinY() <= r2.getMaxY() && r1.getMaxY() >= r2.getMinY());

        // Comparten un eje Y y sus rangos en Y coinciden o se tocan
        boolean tocanBase = (r1.getMaxY() == r2.getMinY() || r2.getMaxY() == r1.getMinY()) &&
                            (r1.getMinX() <= r2.getMaxX() && r1.getMaxX() >= r2.getMinX());

        return tocanLado || tocanBase;
    }

    // Método de clase por exclusión.
    public static boolean esDisjunto(Rectangulo r1, Rectangulo r2) {
        //Exclusion de los otros casos
        return !esSobrePos(r1, r2) && !esJunto(r1, r2);
    }
}