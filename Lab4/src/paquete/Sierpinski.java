package paquete;

import javax.swing.*;
import java.awt.*;

// La clase hereda de JPanel para actuar como un lienzo de dibujo personalizado.
public class Sierpinski extends JPanel {
    
    // Motor recursivo para el cálculo y renderizado geométrico.
    public void drawTriangle(Graphics g, int x1, int y1, int x2, int y2, int x3, int y3, int nivel) {
        
        // Caso base: Profundidad máxima alcanzada.
        // Se detiene la subdivisión y se renderiza físicamente el triángulo sólido.
        if (nivel == 0) {
            int[] xPoints = {x1, x2, x3};
            int[] yPoints = {y1, y2, y3};
            g.fillPolygon(xPoints, yPoints, 3);
        } else {
            // Fase de División: Cálculo analítico de los puntos medios de los tres lados
            // del triángulo actual utilizando geometría analítica básica.
            int mx12 = (x1 + x2) / 2;
            int my12 = (y1 + y2) / 2;
            
            int mx23 = (x2 + x3) / 2;
            int my23 = (y2 + y3) / 2;
            
            int mx31 = (x3 + x1) / 2;
            int my31 = (y3 + y1) / 2;
            
            // Llamadas recursivas: Se instancian 3 nuevos triángulos más pequeños 
            // ubicados en los vértices del triángulo original, reduciendo el nivel de profundidad.
            // (Nótese que el espacio central queda en blanco intencionalmente).
            drawTriangle(g, x1, y1, mx12, my12, mx31, my31, nivel - 1); // Triángulo inferior izquierdo
            drawTriangle(g, mx12, my12, x2, y2, mx23, my23, nivel - 1); // Triángulo inferior derecho
            drawTriangle(g, mx31, my31, mx23, my23, x3, y3, nivel - 1); // Triángulo superior
        }
    }
    
    // Método sobreescrito del ciclo de vida de Swing. 
    // Se ejecuta automáticamente cuando el panel necesita ser renderizado.
    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        // Llamada inicial con coordenadas base estáticas y una profundidad (nivel) de 5.
        drawTriangle(g, 100, 500, 500, 500, 300, 100, 5);
    }
    
    // Punto de entrada de la aplicación. Configuración del marco principal (Window).
    public static void main(String[] args) {
        JFrame frame = new JFrame("Triángulo de Sierpinski");
        Sierpinski panel = new Sierpinski();
        
        frame.add(panel);
        frame.setSize(600, 600); // Dimensiones de la ventana
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); // Liberación de recursos al cerrar
        frame.setVisible(true); // Desencadena la primera llamada a paintComponent()
    }
}