package actividad1;

/**
 * Excepción personalizada para manejar los casos en que se intente
 * extraer u observar elementos de una estructura de datos vacía.
 */
public class ExceptionIsEmpty extends Exception {
    /**
     * Constructor de la excepción.
     * @param message Mensaje detallando el error.
     */
    public ExceptionIsEmpty(String message) {
        super(message); // Llama al constructor de la clase base (Exception)
    }
}