package paquete;

// Clase diseñada para gestionar arreglos de objetos.
public class ContainerRect {
    // Atributos privados (encapsulamiento). Se declaran arreglos para almacenar datos.
    private Rectangulo[] listaRect;
    private double[] distancias;
    private double[] areas;
    private int n; // Capacidad máxima.
    private static int numRec = 0; // Atributo de clase (estático) como contador global.

    // Constructor que inicializa los arreglos según la capacidad indicada.
    public ContainerRect(int n) {
        this.n = n;
        this.listaRect = new Rectangulo[n];
        this.distancias = new double[n];
        this.areas = new double[n];
    }

    // Método de instancia. Almacena un objeto en el arreglo si no se supera la capacidad.
    public void addRectangulo(Rectangulo r) {
        if (numRec < n) { 
            this.listaRect[numRec] = r;
            
            // Invocación a método de clase (estático) para calcular y guardar distancia.
            this.distancias[numRec] = Coordenada.distancia(r.getEsquina1(), r.getEsquina2());
            
            // Invocación a método de instancia para calcular y guardar área.
            this.areas[numRec] = r.calculoArea();
            
            numRec++;
        } else {
            System.out.println("Ya no es posible guardar más rectángulos. Capacidad máxima alcanzada.");
        }
    }

    // Método de instancia que recorre el arreglo de objetos para mostrar su contenido.
    public String toString() {
        String reporte = "Rectangulo\tCoordenadas\t\t\tDistancia\tArea\n";
        for (int i = 0; i < numRec; i++) {
            reporte += (i + 1) + "\t\t" + 
                       this.listaRect[i].toString() + "\t" + 
                       String.format("%.3f", this.distancias[i]) + "\t\t" + 
                       String.format("%.2f", this.areas[i]) + "\n";
        }
        return reporte;
    }
}