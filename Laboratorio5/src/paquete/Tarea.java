package paquete;

/**
 * Clase que representa una Tarea pendiente.
 * Implementa 'Comparable<Tarea>' para que las listas ordenadas sepan 
 * decidir cuál tarea va antes que otra basándose en su prioridad.
 */
public class Tarea implements Comparable<Tarea> {
    private String titulo;   // Descripción de la tarea
    private int prioridad;  // Nivel de importancia (ej: 1 es más alta que 5)
    private String estado;   // Si está "Pendiente", "En curso" o "Terminada"
    
    /**
     * Constructor: Crea una nueva tarea.
     * @param titulo Nombre de la tarea.
     * @param prioridad Número que indica la importancia.
     * @param estado Situación actual de la tarea.
     */
    public Tarea(String titulo, int prioridad, String estado) {
        this.titulo = titulo;
        this.prioridad = prioridad;
        this.estado = estado;
    }

    // --- GETTERS Y SETTERS ---
    // Métodos para leer y escribir en los atributos privados.

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public int getPrioridad() {
        return prioridad;
    }

    public void setPrioridad(int prioridad) {
        this.prioridad = prioridad;
    }

    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }

    /**
     * El método compareTo es OBLIGATORIO por implementar 'Comparable'.
     * Decide el criterio de ordenación.
     * @param otra La tarea con la que nos estamos comparando.
     * @return un número negativo si somos "menores" (más prioridad), 
     *         positivo si somos "mayores", o cero si somos iguales.
     */
    @Override
    public int compareTo(Tarea otra) {
        // Comparamos los números de prioridad.
        return Integer.compare(this.prioridad, otra.prioridad);
    }

    /**
     * Define cómo se imprime la tarea en consola.
     */
    @Override
    public String toString() {
        // Formato: "[Estudiar | Prioridad: 1 | Estado: Pendiente]"
        return "[" + titulo + " | Prioridad: " + prioridad + " | Estado: " + estado + "]";
    }
}