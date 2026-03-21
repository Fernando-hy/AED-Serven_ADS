package paquete;

// Clase utilitaria. Contiene exclusivamente métodos estáticos.
public class Verificador {

    // Método de clase. Evalúa atributos de dos objetos sin instanciar 'Verificador'.
    public static boolean esSobrePos(Rectangulo r1, Rectangulo r2) {
        boolean sobreponeX = r1.getMinX() < r2.getMaxX() && r1.getMaxX() > r2.getMinX();
        boolean sobreponeY = r1.getMinY() < r2.getMaxY() && r1.getMaxY() > r2.getMinY();

        return sobreponeX && sobreponeY;
    }

    // Método de clase. Invocación de otro método estático internamente.
    public static boolean esJunto(Rectangulo r1, Rectangulo r2) {
        if (esSobrePos(r1, r2)) {
            return false;
        }

        boolean tocanLado = (r1.getMaxX() == r2.getMinX() || r2.getMaxX() == r1.getMinX()) &&
                            (r1.getMinY() <= r2.getMaxY() && r1.getMaxY() >= r2.getMinY());

        boolean tocanBase = (r1.getMaxY() == r2.getMinY() || r2.getMaxY() == r1.getMinY()) &&
                            (r1.getMinX() <= r2.getMaxX() && r1.getMaxX() >= r2.getMinX());

        return tocanLado || tocanBase;
    }

    // Método de clase.
    public static boolean esDisjunto(Rectangulo r1, Rectangulo r2) {
        return !esSobrePos(r1, r2) && !esJunto(r1, r2);
    }
}