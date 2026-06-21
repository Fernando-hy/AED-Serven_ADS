package grafica;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.awt.geom.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import graph.GraphLink;
import listlinked.StackLink;

public class GraphCanvas extends JPanel {
    private static final int VERTEX_RADIUS = 22;

    // Estructuras locales para rastrear posiciones y datos visuales
    public static class VisualVertex {
        public String label;
        public int x, y;
        public Color color;

        public VisualVertex(String label, int x, int y) {
            this.label = label;
            this.x = x;
            this.y = y;
            this.color = new Color(70, 130, 180); // Steel blue
        }
    }

    public static class VisualEdge {
        public VisualVertex v1, v2;
        public int weight;
        public boolean isHighlighted;

        public VisualEdge(VisualVertex v1, VisualVertex v2, int weight) {
            this.v1 = v1;
            this.v2 = v2;
            this.weight = weight;
            this.isHighlighted = false;
        }
    }

    private ArrayList<VisualVertex> visualVertices;
    private ArrayList<VisualEdge> visualEdges;
    private GraphLink<String> graphData;

    private VisualVertex selectedVertex = null;
    private VisualVertex hoveredVertex = null;
    private VisualVertex draggingVertex = null;

    // Para resaltar caminos
    private ArrayList<VisualVertex> highlightedVertices;

    public GraphCanvas() {
        visualVertices = new ArrayList<>();
        visualEdges = new ArrayList<>();
        graphData = new GraphLink<>();
        highlightedVertices = new ArrayList<>();

        setBackground(new Color(245, 247, 250)); // Color de fondo premium claro
        setDoubleBuffered(true);

        // Interacciones del mouse
        addMouseListener(new MouseAdapter() {
            @Override
            public void mousePressed(MouseEvent e) {
                VisualVertex clicked = getVertexAt(e.getX(), e.getY());
                if (clicked != null) {
                    if (SwingUtilities.isRightMouseButton(e) || e.isShiftDown()) {
                        // Comenzar o completar conexión
                        if (selectedVertex == null) {
                            selectedVertex = clicked;
                            selectedVertex.color = new Color(220, 100, 50); // Naranja selección
                        } else if (selectedVertex != clicked) {
                            // Crear arista
                            String weightStr = JOptionPane.showInputDialog(GraphCanvas.this, 
                                    "Ingrese el peso para la arista (" + selectedVertex.label + " - " + clicked.label + "):", 
                                    "Nueva Arista", JOptionPane.QUESTION_MESSAGE);
                            if (weightStr != null) {
                                try {
                                    int weight = Integer.parseInt(weightStr.trim());
                                    addEdge(selectedVertex.label, clicked.label, weight);
                                } catch (NumberFormatException ex) {
                                    // Por defecto peso 1
                                    addEdge(selectedVertex.label, clicked.label, 1);
                                }
                            }
                            selectedVertex.color = new Color(70, 130, 180);
                            selectedVertex = null;
                        } else {
                            // Cancelar selección si hace clic en el mismo
                            selectedVertex.color = new Color(70, 130, 180);
                            selectedVertex = null;
                        }
                    } else {
                        // Clic izquierdo para arrastrar
                        draggingVertex = clicked;
                    }
                } else {
                    if (selectedVertex != null) {
                        selectedVertex.color = new Color(70, 130, 180);
                        selectedVertex = null;
                    }
                }
                repaint();
            }

            @Override
            public void mouseReleased(MouseEvent e) {
                draggingVertex = null;
                repaint();
            }

            @Override
            public void mouseClicked(MouseEvent e) {
                if (e.getClickCount() == 2 && SwingUtilities.isLeftMouseButton(e)) {
                    // Doble clic para crear vértice
                    String name = JOptionPane.showInputDialog(GraphCanvas.this, 
                            "Nombre del nuevo vértice:", "Nuevo Vértice", JOptionPane.QUESTION_MESSAGE);
                    if (name != null && !name.trim().isEmpty()) {
                        addVertex(name.trim(), e.getX(), e.getY());
                    }
                }
            }
        });

        addMouseMotionListener(new MouseMotionAdapter() {
            @Override
            public void mouseDragged(MouseEvent e) {
                if (draggingVertex != null) {
                    draggingVertex.x = Math.max(VERTEX_RADIUS, Math.min(getWidth() - VERTEX_RADIUS, e.getX()));
                    draggingVertex.y = Math.max(VERTEX_RADIUS, Math.min(getHeight() - VERTEX_RADIUS, e.getY()));
                    repaint();
                }
            }

            @Override
            public void mouseMoved(MouseEvent e) {
                VisualVertex prevHover = hoveredVertex;
                hoveredVertex = getVertexAt(e.getX(), e.getY());
                if (prevHover != hoveredVertex) {
                    repaint();
                }
            }
        });
    }

    public synchronized void addVertex(String name, int x, int y) {
        // Evitar duplicados visuales y de datos
        for (VisualVertex vv : visualVertices) {
            if (vv.label.equalsIgnoreCase(name)) {
                JOptionPane.showMessageDialog(this, "El vértice con nombre '" + name + "' ya existe.");
                return;
            }
        }
        VisualVertex vv = new VisualVertex(name, x, y);
        visualVertices.add(vv);
        graphData.insertVertex(name);
        repaint();
    }

    public synchronized void addEdge(String from, String to, int weight) {
        VisualVertex v1 = findVisualVertex(from);
        VisualVertex v2 = findVisualVertex(to);
        if (v1 != null && v2 != null) {
            // Verificar si ya existe la arista
            for (VisualEdge edge : visualEdges) {
                if ((edge.v1 == v1 && edge.v2 == v2) || (edge.v1 == v2 && edge.v2 == v1)) {
                    edge.weight = weight;
                    graphData.insertEdgeWeight(from, to, weight);
                    repaint();
                    return;
                }
            }
            visualEdges.add(new VisualEdge(v1, v2, weight));
            graphData.insertEdgeWeight(from, to, weight);
            repaint();
        }
    }

    public synchronized void clearGraph() {
        visualVertices.clear();
        visualEdges.clear();
        graphData = new GraphLink<>();
        highlightedVertices.clear();
        selectedVertex = null;
        hoveredVertex = null;
        repaint();
    }

    private VisualVertex getVertexAt(int x, int y) {
        for (VisualVertex vv : visualVertices) {
            double dist = Point2D.distance(vv.x, vv.y, x, y);
            if (dist <= VERTEX_RADIUS) {
                return vv;
            }
        }
        return null;
    }

    private VisualVertex findVisualVertex(String label) {
        for (VisualVertex vv : visualVertices) {
            if (vv.label.equals(label)) return vv;
        }
        return null;
    }

    public ArrayList<VisualVertex> getVisualVertices() {
        return visualVertices;
    }

    public ArrayList<VisualEdge> getVisualEdges() {
        return visualEdges;
    }

    // Algoritmos con resaltado visual
    public void runBFS(String startLabel) {
        clearHighlights();
        if (findVisualVertex(startLabel) == null) return;
        
        // Ejecutamos BFS de GraphLink de forma secuencial para registrar el orden
        // Como el BFS original de GraphLink imprime a consola, vamos a simularlo aquí
        // para poder colorear en el orden correspondiente.
        ArrayList<String> order = new ArrayList<>();
        ArrayList<String> queue = new ArrayList<>();
        ArrayList<String> visited = new ArrayList<>();

        queue.add(startLabel);
        visited.add(startLabel);

        while (!queue.isEmpty()) {
            String curr = queue.remove(0);
            order.add(curr);
            ArrayList<String> adj = graphData.adjacentVertices(curr);
            for (String neighbor : adj) {
                if (!visited.contains(neighbor)) {
                    visited.add(neighbor);
                    queue.add(neighbor);
                }
            }
        }

        animateTraversal(order);
    }

    public void runDFS(String startLabel) {
        clearHighlights();
        if (findVisualVertex(startLabel) == null) return;

        ArrayList<String> order = new ArrayList<>();
        ArrayList<String> stack = new ArrayList<>();
        ArrayList<String> visited = new ArrayList<>();

        stack.add(startLabel);

        while (!stack.isEmpty()) {
            String curr = stack.remove(stack.size() - 1);
            if (!visited.contains(curr)) {
                visited.add(curr);
                order.add(curr);

                ArrayList<String> adj = graphData.adjacentVertices(curr);
                // Apilamos en orden inverso
                for (int i = adj.size() - 1; i >= 0; i--) {
                    String neighbor = adj.get(i);
                    if (!visited.contains(neighbor)) {
                        stack.add(neighbor);
                    }
                }
            }
        }

        animateTraversal(order);
    }

    public void runDijkstra(String from, String to) {
        clearHighlights();
        StackLink<String> pathStack = graphData.dijkstra(from, to);
        ArrayList<String> path = new ArrayList<>();
        
        try {
            while (!pathStack.isEmpty()) {
                path.add(pathStack.pop());
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        if (path.isEmpty()) {
            JOptionPane.showMessageDialog(this, "No se encontró un camino entre " + from + " y " + to);
            return;
        }

        // Resaltar vértices
        for (String node : path) {
            VisualVertex vv = findVisualVertex(node);
            if (vv != null) {
                highlightedVertices.add(vv);
            }
        }

        // Resaltar aristas del camino
        for (int i = 0; i < path.size() - 1; i++) {
            VisualVertex vv1 = findVisualVertex(path.get(i));
            VisualVertex vv2 = findVisualVertex(path.get(i + 1));
            for (VisualEdge edge : visualEdges) {
                if ((edge.v1 == vv1 && edge.v2 == vv2) || (edge.v1 == vv2 && edge.v2 == vv1)) {
                    edge.isHighlighted = true;
                }
            }
        }

        repaint();
    }

    private void clearHighlights() {
        highlightedVertices.clear();
        for (VisualEdge edge : visualEdges) {
            edge.isHighlighted = false;
        }
        for (VisualVertex vv : visualVertices) {
            vv.color = new Color(70, 130, 180);
        }
        repaint();
    }

    private void animateTraversal(ArrayList<String> order) {
        Timer timer = new Timer(500, null);
        final int[] idx = {0};
        timer.addActionListener(e -> {
            if (idx[0] < order.size()) {
                VisualVertex vv = findVisualVertex(order.get(idx[0]));
                if (vv != null) {
                    vv.color = new Color(50, 205, 50); // Verde brillante
                    highlightedVertices.add(vv);
                }
                idx[0]++;
                repaint();
            } else {
                timer.stop();
            }
        });
        timer.start();
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        Graphics2D g2 = (Graphics2D) g;
        // Calidad de renderizado excelente con antialiasing
        g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
        g2.setRenderingHint(RenderingHints.KEY_TEXT_ANTIALIASING, RenderingHints.VALUE_TEXT_ANTIALIAS_ON);

        // Dibujar aristas
        for (VisualEdge edge : visualEdges) {
            if (edge.isHighlighted) {
                g2.setColor(new Color(255, 69, 0)); // Naranja-Rojo para caminos resaltados
                g2.setStroke(new BasicStroke(3.5f));
            } else {
                g2.setColor(new Color(180, 190, 200));
                g2.setStroke(new BasicStroke(2.0f));
            }

            g2.drawLine(edge.v1.x, edge.v1.y, edge.v2.x, edge.v2.y);

            // Dibujar peso en el punto medio de la arista con un fondo blanco
            int midX = (edge.v1.x + edge.v2.x) / 2;
            int midY = (edge.v1.y + edge.v2.y) / 2;

            String weightText = String.valueOf(edge.weight);
            FontMetrics fm = g2.getFontMetrics();
            int rectW = fm.stringWidth(weightText) + 8;
            int rectH = fm.getHeight() + 4;

            g2.setColor(Color.WHITE);
            g2.fillRoundRect(midX - rectW / 2, midY - rectH / 2, rectW, rectH, 6, 6);
            g2.setColor(Color.DARK_GRAY);
            g2.drawRoundRect(midX - rectW / 2, midY - rectH / 2, rectW, rectH, 6, 6);
            g2.setColor(new Color(40, 40, 40));
            g2.drawString(weightText, midX - fm.stringWidth(weightText) / 2, midY + fm.getAscent() / 2 - 2);
        }

        // Dibujar vértices
        for (VisualVertex vv : visualVertices) {
            // Sombra suave
            g2.setColor(new Color(0, 0, 0, 30));
            g2.fillOval(vv.x - VERTEX_RADIUS + 2, vv.y - VERTEX_RADIUS + 2, VERTEX_RADIUS * 2, VERTEX_RADIUS * 2);

            // Gradiente para el cuerpo del círculo
            Color topColor = vv.color;
            Color bottomColor = vv.color.darker();
            if (vv == hoveredVertex) {
                topColor = vv.color.brighter();
                bottomColor = vv.color;
            }

            GradientPaint gradient = new GradientPaint(
                    vv.x, vv.y - VERTEX_RADIUS, topColor,
                    vv.x, vv.y + VERTEX_RADIUS, bottomColor
            );
            g2.setPaint(gradient);
            g2.fillOval(vv.x - VERTEX_RADIUS, vv.y - VERTEX_RADIUS, VERTEX_RADIUS * 2, VERTEX_RADIUS * 2);

            // Borde del círculo
            g2.setColor(Color.WHITE);
            g2.setStroke(new BasicStroke(1.5f));
            g2.drawOval(vv.x - VERTEX_RADIUS, vv.y - VERTEX_RADIUS, VERTEX_RADIUS * 2, VERTEX_RADIUS * 2);

            // Etiqueta del vértice
            g2.setColor(Color.WHITE);
            g2.setFont(new Font("SansSerif", Font.BOLD, 13));
            FontMetrics fm = g2.getFontMetrics();
            int textX = vv.x - fm.stringWidth(vv.label) / 2;
            int textY = vv.y + fm.getAscent() / 2 - 1;
            g2.drawString(vv.label, textX, textY);
        }
    }
}
