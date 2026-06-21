package grafica;

import javax.swing.*;
import java.awt.*;
import graph.GraphListEdge;

public class GraphPropertiesPanel extends JPanel {
    private GraphListEdge<String> graphA;
    private GraphListEdge<String> graphB;

    private JTextArea areaA;
    private JTextArea areaB;
    private JTextArea areaResults;

    public GraphPropertiesPanel() {
        graphA = new GraphListEdge<>();
        graphB = new GraphListEdge<>();

        setLayout(new BorderLayout(10, 10));
        setBorder(BorderFactory.createEmptyBorder(15, 15, 15, 15));
        setBackground(new Color(245, 247, 250));

        // Subpanel para construir los dos grafos
        JPanel buildPanel = new JPanel(new GridLayout(1, 2, 15, 0));
        buildPanel.setOpaque(false);

        // Panel Grafo A
        JPanel panelA = createGraphBuildPanel("Grafo A", graphA, areaA = new JTextArea(8, 20));
        // Panel Grafo B
        JPanel panelB = createGraphBuildPanel("Grafo B", graphB, areaB = new JTextArea(8, 20));

        buildPanel.add(panelA);
        buildPanel.add(panelB);

        // Panel inferior de acciones y resultados
        JPanel resultsPanel = new JPanel(new BorderLayout(10, 10));
        resultsPanel.setOpaque(false);

        JPanel btnPanel = new JPanel(new FlowLayout(FlowLayout.CENTER, 10, 10));
        btnPanel.setOpaque(false);

        JButton btnVerify = new JButton("Verificar Propiedades y Relaciones");
        styleButton(btnVerify, new Color(50, 150, 100));
        
        JButton btnClear = new JButton("Limpiar Ambos Grafos");
        styleButton(btnClear, new Color(180, 50, 50));

        btnPanel.add(btnVerify);
        btnPanel.add(btnClear);

        areaResults = new JTextArea(8, 40);
        areaResults.setEditable(false);
        areaResults.setFont(new Font("Monospaced", Font.PLAIN, 13));
        areaResults.setBorder(BorderFactory.createCompoundBorder(
                BorderFactory.createLineBorder(new Color(200, 200, 200)),
                BorderFactory.createEmptyBorder(10, 10, 10, 10)
        ));
        JScrollPane scrollResults = new JScrollPane(areaResults);

        resultsPanel.add(btnPanel, BorderLayout.NORTH);
        resultsPanel.add(scrollResults, BorderLayout.CENTER);

        add(buildPanel, BorderLayout.CENTER);
        add(resultsPanel, BorderLayout.SOUTH);

        // Eventos
        btnVerify.addActionListener(e -> verifyProperties());
        btnClear.addActionListener(e -> {
            graphA = new GraphListEdge<>();
            graphB = new GraphListEdge<>();
            areaA.setText("");
            areaB.setText("");
            areaResults.setText("Grafos limpiados.");
        });
    }

    private JPanel createGraphBuildPanel(String title, GraphListEdge<String> graph, JTextArea area) {
        JPanel panel = new JPanel(new BorderLayout(5, 5));
        panel.setBorder(BorderFactory.createTitledBorder(
                BorderFactory.createLineBorder(new Color(150, 170, 190), 2, true), title
        ));
        panel.setBackground(Color.WHITE);

        area.setEditable(false);
        area.setFont(new Font("Monospaced", Font.PLAIN, 12));
        JScrollPane scroll = new JScrollPane(area);

        JPanel actions = new JPanel(new GridLayout(2, 2, 5, 5));
        actions.setOpaque(false);

        JButton btnAddVertex = new JButton("Agregar Vértice");
        JButton btnAddEdge = new JButton("Agregar Arista");
        JButton btnRemoveVertex = new JButton("Eliminar Vértice");
        JButton btnRemoveEdge = new JButton("Eliminar Arista");

        styleButton(btnAddVertex, new Color(70, 130, 180));
        styleButton(btnAddEdge, new Color(70, 130, 180));
        styleButton(btnRemoveVertex, new Color(120, 130, 140));
        styleButton(btnRemoveEdge, new Color(120, 130, 140));

        actions.add(btnAddVertex);
        actions.add(btnAddEdge);
        actions.add(btnRemoveVertex);
        actions.add(btnRemoveEdge);

        panel.add(scroll, BorderLayout.CENTER);
        panel.add(actions, BorderLayout.SOUTH);

        // Eventos de botones
        btnAddVertex.addActionListener(e -> {
            String name = JOptionPane.showInputDialog(this, "Nombre del vértice para " + title + ":");
            if (name != null && !name.trim().isEmpty()) {
                graph.insertVertex(name.trim());
                updateTextArea(graph, area);
            }
        });

        btnAddEdge.addActionListener(e -> {
            String origin = JOptionPane.showInputDialog(this, "Vértice de origen:");
            if (origin == null || origin.trim().isEmpty()) return;
            String destination = JOptionPane.showInputDialog(this, "Vértice de destino:");
            if (destination == null || destination.trim().isEmpty()) return;

            graph.insertEdge(origin.trim(), destination.trim());
            updateTextArea(graph, area);
        });

        btnRemoveVertex.addActionListener(e -> {
            String name = JOptionPane.showInputDialog(this, "Vértice a eliminar:");
            if (name != null && !name.trim().isEmpty()) {
                graph.removeVertex(name.trim());
                updateTextArea(graph, area);
            }
        });

        btnRemoveEdge.addActionListener(e -> {
            String origin = JOptionPane.showInputDialog(this, "Vértice de origen de la arista:");
            if (origin == null || origin.trim().isEmpty()) return;
            String destination = JOptionPane.showInputDialog(this, "Vértice de destino de la arista:");
            if (destination == null || destination.trim().isEmpty()) return;

            graph.removeEdge(origin.trim(), destination.trim());
            updateTextArea(graph, area);
        });

        return panel;
    }

    private void updateTextArea(GraphListEdge<String> graph, JTextArea area) {
        area.setText(graph.toString());
    }

    private void verifyProperties() {
        StringBuilder sb = new StringBuilder();
        sb.append("=================== ANÁLISIS DE PROPIEDADES ===================\n\n");

        sb.append("--- PROPIEDADES DE GRAFO A ---\n");
        sb.append("¿Es Conexo?: ").append(graphA.isConexo() ? "SÍ" : "NO").append("\n");
        sb.append("¿Es Plano? (Euler): ").append(graphA.isPlano() ? "SÍ" : "NO").append("\n");
        sb.append("¿Es Auto-Complementario?: ").append(graphA.isAutoComplementario() ? "SÍ" : "NO").append("\n\n");

        sb.append("--- PROPIEDADES DE GRAFO B ---\n");
        sb.append("¿Es Conexo?: ").append(graphB.isConexo() ? "SÍ" : "NO").append("\n");
        sb.append("¿Es Plano? (Euler): ").append(graphB.isPlano() ? "SÍ" : "NO").append("\n");
        sb.append("¿Es Auto-Complementario?: ").append(graphB.isAutoComplementario() ? "SÍ" : "NO").append("\n\n");

        sb.append("--- RELACIONES --- \n");
        sb.append("¿Son Isomorfos (Grafo A ≅ Grafo B)?: ").append(graphA.isIsomorfo(graphB) ? "SÍ" : "NO").append("\n");

        areaResults.setText(sb.toString());
    }

    private void styleButton(JButton btn, Color bgColor) {
        btn.setBackground(bgColor);
        btn.setForeground(Color.WHITE);
        btn.setFocusPainted(false);
        btn.setFont(new Font("SansSerif", Font.BOLD, 12));
        btn.setBorder(BorderFactory.createEmptyBorder(6, 12, 6, 12));
    }
}
