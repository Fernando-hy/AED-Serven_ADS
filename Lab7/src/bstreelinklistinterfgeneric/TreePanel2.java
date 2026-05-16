package bstreelinklistinterfgeneric;

import javax.swing.*;
import java.awt.*;

public class TreePanel2<E extends Comparable<E>> extends JPanel {
    private static final long serialVersionUID = 1L;
    private LinkedBST<E> bst;
    private static final int NODE_RADIUS = 22;
    private static final int LEVEL_HEIGHT = 80;

    // Colores del tema oscuro
    private final Color BG_DARK = new Color(24, 24, 24);
    private final Color ACCENT_BLUE = new Color(0, 150, 255);
    private final Color FG_LIGHT = new Color(220, 220, 220);
    private final Color LINE_COLOR = new Color(80, 80, 80);

    public TreePanel2(LinkedBST<E> bst) {
        this.bst = bst;
        setBackground(BG_DARK);
    }

    public void refresh() {
        repaint();
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        Graphics2D g2 = (Graphics2D) g;
        
        // Habilitar anti-aliasing para líneas y texto suaves
        g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
        g2.setRenderingHint(RenderingHints.KEY_TEXT_ANTIALIASING, RenderingHints.VALUE_TEXT_ANTIALIAS_ON);
        
        if (bst.getRoot() != null) {
            drawNode(g2, bst.getRoot(), getWidth() / 2, 50, getWidth() / 4);
        } else {
            // Mensaje si el árbol está vacío
            g2.setColor(new Color(100, 100, 100));
            g2.setFont(new Font("Segoe UI", Font.ITALIC, 16));
            String msg = "Árbol Vacío - Inserta nodos para comenzar";
            FontMetrics fm = g2.getFontMetrics();
            g2.drawString(msg, getWidth() / 2 - fm.stringWidth(msg) / 2, getHeight() / 2);
        }
    }

    private void drawNode(Graphics2D g, Node<E> n, int x, int y, int offset) {
        if (n == null) return;

        // 1. Dibujar líneas a los hijos primero (quedan detrás de los nodos)
        g.setStroke(new BasicStroke(2.0f)); // Líneas más gruesas
        
        if (n.getLeft() != null) {
            g.setColor(LINE_COLOR);
            g.drawLine(x, y, x - offset, y + LEVEL_HEIGHT);
            drawNode(g, n.getLeft(), x - offset, y + LEVEL_HEIGHT, offset / 2);
        }
        if (n.getRight() != null) {
            g.setColor(LINE_COLOR);
            g.drawLine(x, y, x + offset, y + LEVEL_HEIGHT);
            drawNode(g, n.getRight(), x + offset, y + LEVEL_HEIGHT, offset / 2);
        }

        // 2. Dibujar el nodo (Círculo)
        // Sombra o resplandor simulado (círculo más grande con transparencia)
        g.setColor(new Color(0, 150, 255, 50));
        g.fillOval(x - NODE_RADIUS - 4, y - NODE_RADIUS - 4, (NODE_RADIUS + 4) * 2, (NODE_RADIUS + 4) * 2);

        // Relleno del nodo
        g.setColor(ACCENT_BLUE);
        g.fillOval(x - NODE_RADIUS, y - NODE_RADIUS, NODE_RADIUS * 2, NODE_RADIUS * 2);
        
        // Borde del nodo
        g.setColor(Color.WHITE);
        g.setStroke(new BasicStroke(1.5f));
        g.drawOval(x - NODE_RADIUS, y - NODE_RADIUS, NODE_RADIUS * 2, NODE_RADIUS * 2);

        // 3. Dibujar el valor dentro del nodo
        g.setColor(Color.WHITE);
        g.setFont(new Font("Segoe UI", Font.BOLD, 14));
        String texto = n.getData().toString();
        FontMetrics fm = g.getFontMetrics();
        // Centrar el texto en el círculo
        int textX = x - fm.stringWidth(texto) / 2;
        int textY = y + fm.getAscent() / 2 - 2;
        g.drawString(texto, textX, textY);
    }
}