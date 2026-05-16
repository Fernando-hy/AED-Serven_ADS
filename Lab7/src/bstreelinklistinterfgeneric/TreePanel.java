package bstreelinklistinterfgeneric;

import javax.swing.*;
import java.awt.*;

public class TreePanel<E extends Comparable<E>> extends JPanel {
    private LinkedBST<E> bst;
    private static final int NODE_RADIUS = 20;
    private static final int LEVEL_HEIGHT = 70;

    public TreePanel(LinkedBST<E> bst) {
        this.bst = bst;
        setBackground(Color.WHITE);
    }

    public void refresh() {
        repaint();
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        Graphics2D g2 = (Graphics2D) g;
        g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
        if (bst.getRoot() != null) {
            drawNode(g2, bst.getRoot(), getWidth() / 2, 40, getWidth() / 4);
        }
    }

    private void drawNode(Graphics2D g, Node<E> n, int x, int y, int offset) {
        if (n == null) return;

        // Dibuja líneas a los hijos primero (quedan detrás del nodo)
        if (n.getLeft() != null) {
            g.setColor(Color.DARK_GRAY);
            g.drawLine(x, y, x - offset, y + LEVEL_HEIGHT);
            drawNode(g, n.getLeft(), x - offset, y + LEVEL_HEIGHT, offset / 2);
        }
        if (n.getRight() != null) {
            g.setColor(Color.DARK_GRAY);
            g.drawLine(x, y, x + offset, y + LEVEL_HEIGHT);
            drawNode(g, n.getRight(), x + offset, y + LEVEL_HEIGHT, offset / 2);
        }

        // Dibuja el círculo del nodo
        g.setColor(new Color(100, 149, 237)); // azul
        g.fillOval(x - NODE_RADIUS, y - NODE_RADIUS, NODE_RADIUS * 2, NODE_RADIUS * 2);
        g.setColor(Color.DARK_GRAY);
        g.drawOval(x - NODE_RADIUS, y - NODE_RADIUS, NODE_RADIUS * 2, NODE_RADIUS * 2);

        // Dibuja el valor dentro del nodo
        g.setColor(Color.WHITE);
        g.setFont(new Font("Arial", Font.BOLD, 13));
        String texto = n.getData().toString();
        FontMetrics fm = g.getFontMetrics();
        g.drawString(texto, x - fm.stringWidth(texto) / 2, y + fm.getAscent() / 2 - 1);
    }
}