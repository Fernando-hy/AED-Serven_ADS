package paquete;

// Representa un rectángulo aplicando composición (uso de objetos Coordenada).
public class Rectangulo {
    // Atributos de tipo objeto (composición/agregación).
    private Coordenada esquina1;
    private Coordenada esquina2;

    // Constructor que inicializa el objeto usando otros objetos preexistentes.
    public Rectangulo(Coordenada c1, Coordenada c2) {
        setEsquina1(c1);
        setEsquina2(c2);
    }

    // Métodos de instancia. Operan sobre los datos del propio objeto.
    public double getMinX() {
        return Math.min(this.esquina1.getX(), this.esquina2.getX());
    }

    public double getMaxX() {
        return Math.max(this.esquina1.getX(), this.esquina2.getX());
    }

    public double getMinY() {
        return Math.min(this.esquina1.getY(), this.esquina2.getY());
    }

    public double getMaxY() {
        return Math.max(this.esquina1.getY(), this.esquina2.getY());
    }

    // Métodos de clase (estáticos). Reciben un objeto como parámetro en lugar de usar 'this'.
    public static double getMinX(Rectangulo r) {
        return r.getMinX();
    }

    public static double getMaxX(Rectangulo r) {
        return r.getMaxX();
    }

    public static double getMinY(Rectangulo r) {
        return r.getMinY();
    }

    public static double getMaxY(Rectangulo r) {
        return r.getMaxY();
    }

    // Método de instancia para calcular el área del objeto actual.
    public double calculoArea() {
        double base = this.getMaxX() - this.getMinX();
        double altura = this.getMaxY() - this.getMinY();
        return base * altura;
    }

    // Métodos accesores y mutadores para los objetos internos.
    public void setEsquina1(Coordenada coo) {
        this.esquina1 = coo;
    }

    public Coordenada getEsquina1() {
        return this.esquina1;
    }

    public void setEsquina2(Coordenada coo) {
        this.esquina2 = coo;
    }

    public Coordenada getEsquina2() {
        return this.esquina2;
    }

    // Representación en texto del rectángulo.
    public String toString() {
        return "([" + this.esquina1.getX() + ", " + this.esquina1.getY() + "], [" + 
                      this.esquina2.getX() + ", " + this.esquina2.getY() + "])";
    }
}