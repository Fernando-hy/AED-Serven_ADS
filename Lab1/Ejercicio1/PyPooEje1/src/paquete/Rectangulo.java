package paquete;

// Clase que aplica composición (tiene objetos Coordenada como atributos).
public class Rectangulo {
    private Coordenada esquina1;
    private Coordenada esquina2;

    // Constructor que recibe objetos de otra clase.
    public Rectangulo(Coordenada c1, Coordenada c2) {
        setEsquina1(c1);
        setEsquina2(c2);
    }

    // Métodos de instancia. Usan los atributos del objeto actual.
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

    // Métodos de clase (estáticos). Reciben la instancia por parámetro.
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

    // Método de instancia para procesar datos propios.
    public double calculoArea() {
        double base = this.getMaxX() - this.getMinX();
        double altura = this.getMaxY() - this.getMinY();
        return base * altura;
    }

    // Accesores y mutadores.
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

    // Representación en cadena del objeto.
    public String toString() {
        return "([" + this.esquina1.getX() + ", " + this.esquina1.getY() + "], [" + 
                      this.esquina2.getX() + ", " + this.esquina2.getY() + "])";
    }
}