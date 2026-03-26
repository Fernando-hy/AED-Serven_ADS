package paquete;

import java.util.ArrayList;
import java.util.Iterator;

// Clase genérica Cajoneria que maneja una colección de objetos Caja<T>
public class Cajoneria<T> implements Iterable<Caja<T>> {
    // Lista que almacena objetos de tipo Caja, que a su vez contienen el objeto T
    private ArrayList<Caja<T>> lista = new ArrayList<>();
    // Límite de cajas que caben en la cajonería
    private int tope;

    // Constructor que define el tamaño máximo de la cajonería
    public Cajoneria(int tope) {
        this.tope = tope;
    }

    // Método para añadir una caja a la lista
    public void add(Caja<T> caja) {
        // Verifica si hay espacio disponible
        if (lista.size() < tope) {
            lista.add(caja); // Añade la caja completa
        } else {
            // Lanza error si se intenta superar el límite
            throw new RuntimeException("Cajonería llena");
        }
    }

    // Método para buscar un elemento dentro de las cajas
    public String search(T elemento) {
        for (int i = 0; i < lista.size(); i++) {
            // Obtiene el contenido de la caja en la posición i y lo compara con equals
            if (lista.get(i).getContenido().equals(elemento)) {
                // Si lo encuentra, retorna la posición y el color de la caja que lo contiene
                return "Posición: " + (i) + " Color de Caja: " + lista.get(i).getColor();
            }
        }
        // Retorna mensaje si no se encuentra tras recorrer toda la lista
        return "El elemento no existe en la cajonería.";
    }

    // Método para eliminar una caja basándose en su contenido
    public T delete(T elemento) {
        for (int i = 0; i < lista.size(); i++) {
            // Busca la caja cuyo contenido coincida con el elemento buscado
            if (lista.get(i).getContenido().equals(elemento)) {
                // Elimina la caja de la lista y retorna solo el objeto T que estaba dentro
                return lista.remove(i).getContenido();
            }
        }
        return null; // Retorna null si no encontró nada para borrar
    }
    
    // Método para contar cuántas cajas contienen un elemento específico
    public int contar(T elemento) {
        int contador = 0;
        for (Caja<T> caja : lista) {
            // Si el contenido de la caja coincide con el buscado, incrementa el contador
            if (caja.getContenido().equals(elemento)) {
                contador++;
            }
        }
        return contador; // Retorna el total de coincidencias
    }

    // Representación en formato tabla de la cajonería completa
    @Override
    public String toString() {
        String reporte = "Posición\tColor Caja\tObjeto\n";
        reporte += "----------------------------------------------\n";
        
        for (int i = 0; i < lista.size(); i++) {
            Caja<T> caja = lista.get(i);
            // Muestra posición (1-indexed), color y el toString del contenido
            reporte += (i + 1) + "\t\t" + caja.getColor() + "\t\t" + caja.getContenido() + "\n";
        }
        return reporte;
    }

    // Implementación obligatoria para el Iterable (permite for-each de cajas)
    @Override
    public Iterator<Caja<T>> iterator() {
        return lista.iterator();
    }
}