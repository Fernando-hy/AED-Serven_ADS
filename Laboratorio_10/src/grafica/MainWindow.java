package grafica;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;
import java.util.List;
import org.jgrapht.Graph;
import org.jgrapht.GraphPath;
import org.jgrapht.alg.shortestpath.DijkstraShortestPath;
import org.jgrapht.graph.DefaultWeightedEdge;
import org.jgrapht.graph.SimpleWeightedGraph;

public class MainWindow extends JFrame {

    public MainWindow() {
        setTitle("Visualizador de Estructuras de Datos: Grafos");
        setSize(1100, 750);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        // Cambiar estilo visual
        try {
            UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
        } catch (Exception ignored) {}

        // Encabezado
        JPanel headerPanel = new JPanel(new BorderLayout());
        headerPanel.setBackground(new Color(36, 47, 65));
        headerPanel.setBorder(BorderFactory.createEmptyBorder(15, 20, 15, 20));

        JLabel titleLabel = new JLabel("Laboratorio 10: Aplicaciones de Grafos");
        titleLabel.setFont(new Font("SansSerif", Font.BOLD, 22));
        titleLabel.setForeground(Color.WHITE);
        headerPanel.add(titleLabel, BorderLayout.WEST);

        JLabel subtitleLabel = new JLabel("Visualizador Interactivo de Ejercicios");
        subtitleLabel.setFont(new Font("SansSerif", Font.ITALIC, 14));
        subtitleLabel.setForeground(new Color(180, 195, 210));
        headerPanel.add(subtitleLabel, BorderLayout.EAST);

        // Pestañas
        JTabbedPane tabbedPane = new JTabbedPane();
        tabbedPane.setFont(new Font("SansSerif", Font.BOLD, 13));

        // Pestaña 1: Visualizador GraphLink
        tabbedPane.addTab("1. Visualizador Interactivo (GraphLink)", createGraphLinkPanel());

        // Pestaña 2: Comprobador GraphListEdge
        tabbedPane.addTab("2. Propiedades de Grafos (GraphListEdge)", new GraphPropertiesPanel());

        // Pestaña 3: Red de Ciudades (JGraphT)
        tabbedPane.addTab("3. Red de Ciudades (JGraphT)", createCityNetworkPanel());

        getContentPane().add(headerPanel, BorderLayout.NORTH);
        getContentPane().add(tabbedPane, BorderLayout.CENTER);
    }

    private JPanel createGraphLinkPanel() {
        JPanel panel = new JPanel(new BorderLayout(10, 10));
        panel.setBackground(new Color(245, 247, 250));

        GraphCanvas canvas = new GraphCanvas();
        panel.add(canvas, BorderLayout.CENTER);

        // Panel lateral de control
        JPanel sidebar = new JPanel();
        sidebar.setLayout(new BoxLayout(sidebar, BoxLayout.Y_AXIS));
        sidebar.setBorder(BorderFactory.createEmptyBorder(15, 15, 15, 15));
        sidebar.setBackground(Color.WHITE);
        sidebar.setPreferredSize(new Dimension(280, 0));

        JLabel lblInst = new JLabel("<html><b>Instrucciones:</b><br>"
                + "• Doble clic para añadir Vértice.<br>"
                + "• Shift+Clic (o Clic Derecho) de un nodo a otro para conectar con peso.<br>"
                + "• Arrastrar para mover nodos.</html>");
        lblInst.setFont(new Font("SansSerif", Font.PLAIN, 12));
        lblInst.setBorder(BorderFactory.createEmptyBorder(0, 0, 15, 0));
        sidebar.add(lblInst);

        // Campos y botones
        sidebar.add(new JLabel("Nodo Inicial:"));
        JTextField txtStart = new JTextField();
        txtStart.setMaximumSize(new Dimension(Integer.MAX_VALUE, 30));
        sidebar.add(txtStart);
        sidebar.add(Box.createVerticalStrut(10));

        sidebar.add(new JLabel("Nodo Destino (solo Dijkstra):"));
        JTextField txtEnd = new JTextField();
        txtEnd.setMaximumSize(new Dimension(Integer.MAX_VALUE, 30));
        sidebar.add(txtEnd);
        sidebar.add(Box.createVerticalStrut(15));

        JButton btnBFS = new JButton("Ejecutar BFS");
        JButton btnDFS = new JButton("Ejecutar DFS");
        JButton btnDijkstra = new JButton("Ejecutar Dijkstra");
        JButton btnClear = new JButton("Limpiar Lienzo");

        styleSidebarButton(btnBFS, new Color(70, 130, 180));
        styleSidebarButton(btnDFS, new Color(70, 130, 180));
        styleSidebarButton(btnDijkstra, new Color(50, 150, 100));
        styleSidebarButton(btnClear, new Color(180, 50, 50));

        sidebar.add(btnBFS);
        sidebar.add(Box.createVerticalStrut(10));
        sidebar.add(btnDFS);
        sidebar.add(Box.createVerticalStrut(10));
        sidebar.add(btnDijkstra);
        sidebar.add(Box.createVerticalStrut(20));
        sidebar.add(btnClear);

        panel.add(sidebar, BorderLayout.EAST);

        // Acciones
        btnBFS.addActionListener(e -> {
            String start = txtStart.getText().trim();
            if (start.isEmpty()) {
                JOptionPane.showMessageDialog(this, "Ingrese el nodo inicial.");
                return;
            }
            canvas.runBFS(start);
        });

        btnDFS.addActionListener(e -> {
            String start = txtStart.getText().trim();
            if (start.isEmpty()) {
                JOptionPane.showMessageDialog(this, "Ingrese el nodo inicial.");
                return;
            }
            canvas.runDFS(start);
        });

        btnDijkstra.addActionListener(e -> {
            String start = txtStart.getText().trim();
            String end = txtEnd.getText().trim();
            if (start.isEmpty() || end.isEmpty()) {
                JOptionPane.showMessageDialog(this, "Ingrese el nodo inicial y de destino.");
                return;
            }
            canvas.runDijkstra(start, end);
        });

        btnClear.addActionListener(e -> canvas.clearGraph());

        return panel;
    }

    private void styleSidebarButton(JButton btn, Color bg) {
        btn.setAlignmentX(Component.CENTER_ALIGNMENT);
        btn.setMaximumSize(new Dimension(Integer.MAX_VALUE, 35));
        btn.setBackground(bg);
        btn.setForeground(Color.WHITE);
        btn.setFocusPainted(false);
        btn.setFont(new Font("SansSerif", Font.BOLD, 12));
    }

    // Panel personalizado para representar la Red de Ciudades de JGraphT
    private JPanel createCityNetworkPanel() {
        JPanel mainPanel = new JPanel(new BorderLayout());
        mainPanel.setBackground(new Color(245, 247, 250));

        // Inicializamos el grafo JGraphT
        Graph<String, DefaultWeightedEdge> graph = new SimpleWeightedGraph<>(DefaultWeightedEdge.class);
        graph.addVertex("Arequipa");
        graph.addVertex("Cusco");
        graph.addVertex("Puno");
        graph.addVertex("Tacna");
        graph.addVertex("Moquegua");

        graph.setEdgeWeight(graph.addEdge("Arequipa", "Cusco"),     510);
        graph.setEdgeWeight(graph.addEdge("Arequipa", "Moquegua"),  230);
        graph.setEdgeWeight(graph.addEdge("Moquegua", "Tacna"),     160);
        graph.setEdgeWeight(graph.addEdge("Cusco",    "Puno"),      390);
        graph.setEdgeWeight(graph.addEdge("Puno",     "Tacna"),     420);

        // Coordenadas manuales fijas en el lienzo para las ciudades (aprox geográfico)
        // Arequipa (centro), Cusco (norte), Puno (este), Tacna (sur), Moquegua (suroeste)
        class CityPos {
            String name;
            int x, y;
            CityPos(String name, int x, int y) {
                this.name = name; this.x = x; this.y = y;
            }
        }
        List<CityPos> positions = new ArrayList<>();
        positions.add(new CityPos("Arequipa", 250, 250));
        positions.add(new CityPos("Cusco",    380, 100));
        positions.add(new CityPos("Moquegua", 210, 380));
        positions.add(new CityPos("Tacna",    280, 500));
        positions.add(new CityPos("Puno",     480, 320));

        // Canvas personalizado interno para dibujar
        class CityCanvas extends JPanel {
            List<String> pathVertices = new ArrayList<>();

            @Override
            protected void paintComponent(Graphics g) {
                super.paintComponent(g);
                Graphics2D g2 = (Graphics2D) g;
                g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
                g2.setRenderingHint(RenderingHints.KEY_TEXT_ANTIALIASING, RenderingHints.VALUE_TEXT_ANTIALIAS_ON);

                // Dibujar aristas
                g2.setStroke(new BasicStroke(2.5f));
                for (DefaultWeightedEdge edge : graph.edgeSet()) {
                    String source = graph.getEdgeSource(edge);
                    String target = graph.getEdgeTarget(edge);
                    int w = (int) graph.getEdgeWeight(edge);

                    CityPos p1 = getPos(source);
                    CityPos p2 = getPos(target);

                    if (p1 != null && p2 != null) {
                        // Comprobar si pertenece al camino más corto
                        boolean inPath = false;
                        for (int i = 0; i < pathVertices.size() - 1; i++) {
                            String s1 = pathVertices.get(i);
                            String s2 = pathVertices.get(i + 1);
                            if ((s1.equals(source) && s2.equals(target)) || (s1.equals(target) && s2.equals(source))) {
                                inPath = true;
                                break;
                            }
                        }

                        if (inPath) {
                            g2.setColor(new Color(255, 69, 0));
                            g2.setStroke(new BasicStroke(4.0f));
                        } else {
                            g2.setColor(new Color(180, 190, 200));
                            g2.setStroke(new BasicStroke(2.5f));
                        }

                        g2.drawLine(p1.x, p1.y, p2.x, p2.y);

                        // Escribir km en el medio
                        int mx = (p1.x + p2.x) / 2;
                        int my = (p1.y + p2.y) / 2;
                        g2.setColor(Color.WHITE);
                        g2.fillRect(mx - 22, my - 10, 44, 18);
                        g2.setColor(Color.DARK_GRAY);
                        g2.drawRect(mx - 22, my - 10, 44, 18);
                        g2.setColor(Color.BLACK);
                        g2.setFont(new Font("SansSerif", Font.PLAIN, 11));
                        g2.drawString(w + " km", mx - 18, my + 3);
                    }
                }

                // Dibujar ciudades (vértices)
                int r = 25;
                for (CityPos pos : positions) {
                    // Sombra
                    g2.setColor(new Color(0, 0, 0, 30));
                    g2.fillOval(pos.x - r + 2, pos.y - r + 2, r * 2, r * 2);

                    // Nodo principal
                    boolean inPath = pathVertices.contains(pos.name);
                    if (inPath) {
                        g2.setPaint(new GradientPaint(pos.x, pos.y - r, new Color(255, 120, 50), pos.x, pos.y + r, new Color(220, 60, 20)));
                    } else {
                        g2.setPaint(new GradientPaint(pos.x, pos.y - r, new Color(70, 130, 180), pos.x, pos.y + r, new Color(40, 80, 130)));
                    }
                    g2.fillOval(pos.x - r, pos.y - r, r * 2, r * 2);

                    g2.setColor(Color.WHITE);
                    g2.setStroke(new BasicStroke(1.5f));
                    g2.drawOval(pos.x - r, pos.y - r, r * 2, r * 2);

                    // Texto de la ciudad
                    g2.setColor(Color.WHITE);
                    g2.setFont(new Font("SansSerif", Font.BOLD, 12));
                    FontMetrics fm = g2.getFontMetrics();
                    g2.drawString(pos.name, pos.x - fm.stringWidth(pos.name) / 2, pos.y + fm.getAscent() / 2 - 1);
                }
            }

            private CityPos getPos(String name) {
                for (CityPos cp : positions) {
                    if (cp.name.equals(name)) return cp;
                }
                return null;
            }
        }

        CityCanvas canvas = new CityCanvas();
        canvas.setBackground(new Color(245, 247, 250));

        JPanel controlPanel = new JPanel();
        controlPanel.setLayout(new BoxLayout(controlPanel, BoxLayout.Y_AXIS));
        controlPanel.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));
        controlPanel.setBackground(Color.WHITE);
        controlPanel.setPreferredSize(new Dimension(300, 0));

        JLabel info = new JLabel("<html><h3>Red del Sur Peruano</h3>"
                + "Mapa pre-cargado de ciudades usando la biblioteca <b>JGraphT</b>.<br><br>"
                + "Calcula y resalta el camino mínimo usando el algoritmo Dijkstra de la biblioteca.</html>");
        info.setFont(new Font("SansSerif", Font.PLAIN, 13));
        controlPanel.add(info);
        controlPanel.add(Box.createVerticalStrut(30));

        JButton btnRoute = new JButton("Calcular Ruta: Arequipa → Tacna");
        styleSidebarButton(btnRoute, new Color(50, 150, 100));
        controlPanel.add(btnRoute);
        controlPanel.add(Box.createVerticalStrut(15));

        JButton btnReset = new JButton("Restablecer Vista");
        styleSidebarButton(btnReset, new Color(120, 130, 140));
        controlPanel.add(btnReset);

        JTextArea pathDetails = new JTextArea(6, 20);
        pathDetails.setEditable(false);
        pathDetails.setFont(new Font("SansSerif", Font.PLAIN, 12));
        pathDetails.setBorder(BorderFactory.createTitledBorder("Detalle de la ruta"));
        controlPanel.add(Box.createVerticalStrut(30));
        controlPanel.add(new JScrollPane(pathDetails));

        mainPanel.add(canvas, BorderLayout.CENTER);
        mainPanel.add(controlPanel, BorderLayout.EAST);

        // Acciones
        btnRoute.addActionListener(e -> {
            DijkstraShortestPath<String, DefaultWeightedEdge> dijkstra = new DijkstraShortestPath<>(graph);
            GraphPath<String, DefaultWeightedEdge> path = dijkstra.getPath("Arequipa", "Tacna");

            canvas.pathVertices = path.getVertexList();
            pathDetails.setText("Camino mínimo encontrado:\n"
                    + String.join(" ➔ ", path.getVertexList()) + "\n\n"
                    + "Distancia Total: " + (int) path.getWeight() + " km");
            canvas.repaint();
        });

        btnReset.addActionListener(e -> {
            canvas.pathVertices = new ArrayList<>();
            pathDetails.setText("");
            canvas.repaint();
        });

        return mainPanel;
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            MainWindow mw = new MainWindow();
            mw.setVisible(true);
        });
    }
}
