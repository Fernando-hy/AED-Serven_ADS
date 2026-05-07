package gui;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.geom.*;

import actividad1.QueueArray;
import actividad1.ExceptionIsEmpty;
import actividad2.DequeLink;
import actividad3.PriorityQueueLinkSort;
import actividadEjercicio4.PriorityQueueHybrid;
import actividadEjercicio4.SortedQueue;
import actividadEjercicio4.Entry;

/**
 * Dashboard principal interactivo de Estructuras de Datos (Laboratorio 6).
 * Proporciona una interfaz gráfica unificada en modo oscuro con visualizaciones 2D
 * detalladas para las cuatro actividades de colas.
 */
public class MainDashboard extends JFrame {

    public MainDashboard() {
        setTitle("Estructuras de Datos - Panel de Control Interactivo (Lab 06)");
        setSize(950, 680);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        // Configurar tema oscuro global
        try {
            UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
        } catch (Exception ignored) {}

        // Inicializar componentes principales
        JTabbedPane tabbedPane = new JTabbedPane();
        tabbedPane.setBackground(new Color(30, 30, 46));
        tabbedPane.setForeground(new Color(30, 30, 46)); // Color oscuro para legibilidad contra el fondo claro de pestañas de Windows
        tabbedPane.setFont(new Font("Segoe UI", Font.BOLD, 13));

        // Agregar pestañas de cada actividad
        tabbedPane.addTab("1. Cola Circular", createCircularQueuePanel());
        tabbedPane.addTab("2. Doble Cola (Deque)", createDequePanel());
        tabbedPane.addTab("3. Cola de Prioridad", createPriorityQueuePanel());
        tabbedPane.addTab("4. Cola HIbrida", createHybridQueuePanel());

        getContentPane().setBackground(new Color(30, 30, 46));
        add(tabbedPane);
    }

    // ==========================================
    // ESTILOS Y COMPONENTES REUTILIZABLES DE UI
    // ==========================================
    private static final Color BG_DARK = new Color(30, 30, 46);
    private static final Color BG_CARD = new Color(45, 45, 68);
    private static final Color TEXT_MAIN = new Color(205, 214, 244);
    private static final Color TEXT_MUTED = new Color(147, 153, 178);
    private static final Color ACCENT_BLUE = new Color(137, 180, 250);
    private static final Color ACCENT_GREEN = new Color(166, 227, 161);
    private static final Color ACCENT_ORANGE = new Color(250, 179, 135);
    private static final Color ACCENT_RED = new Color(243, 139, 168);
    private static final Color ACCENT_PURPLE = new Color(203, 166, 247);

    private static JPanel createStyledControlPanel() {
        JPanel panel = new JPanel();
        panel.setBackground(BG_CARD);
        panel.setBorder(BorderFactory.createCompoundBorder(
                BorderFactory.createLineBorder(new Color(60, 60, 90), 1),
                new EmptyBorder(10, 15, 10, 15)
        ));
        return panel;
    }

    private static JButton createStyledButton(String text, Color baseColor) {
        JButton btn = new JButton(text);
        btn.setFont(new Font("Segoe UI", Font.BOLD, 12));
        btn.setForeground(Color.WHITE);
        btn.setBackground(baseColor);
        btn.setFocusPainted(false);
        btn.setCursor(new Cursor(Cursor.HAND_CURSOR));

        // Forzar dibujo de color de fondo personalizado en Windows Look and Feel
        btn.setContentAreaFilled(false);
        btn.setOpaque(true);
        btn.setBorder(BorderFactory.createCompoundBorder(
                BorderFactory.createLineBorder(baseColor.darker(), 1),
                BorderFactory.createEmptyBorder(6, 12, 6, 12)
        ));

        // Efecto hover simple
        btn.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseEntered(MouseEvent e) {
                btn.setBackground(baseColor.brighter());
            }
            @Override
            public void mouseExited(MouseEvent e) {
                btn.setBackground(baseColor);
            }
        });
        return btn;
    }

    private static JTextField createStyledTextField(int cols) {
        JTextField tf = new JTextField(cols);
        tf.setFont(new Font("Segoe UI", Font.PLAIN, 13));
        tf.setBackground(new Color(25, 25, 38));
        tf.setForeground(Color.WHITE);
        tf.setCaretColor(Color.WHITE);
        tf.setBorder(BorderFactory.createCompoundBorder(
                BorderFactory.createLineBorder(new Color(80, 80, 110), 1),
                BorderFactory.createEmptyBorder(4, 6, 4, 6)
        ));
        return tf;
    }

    private static JLabel createStyledLabel(String text, boolean header) {
        JLabel label = new JLabel(text);
        label.setForeground(TEXT_MAIN);
        if (header) {
            label.setFont(new Font("Segoe UI", Font.BOLD, 14));
        } else {
            label.setFont(new Font("Segoe UI", Font.PLAIN, 12));
        }
        return label;
    }

    // ==========================================
    // Pestaña 1: COLA CIRCULAR (ACTIVIDAD 1)
    // ==========================================
    private JPanel createCircularQueuePanel() {
        JPanel mainPanel = new JPanel(new BorderLayout());
        mainPanel.setBackground(BG_DARK);

        // Controladores de Estado
        final QueueArray<String>[] queueHolder = new QueueArray[]{ new QueueArray<>(5) };
        
        // Area Gráfica
        class CircularDrawPanel extends JPanel {
            CircularDrawPanel() { setBackground(BG_DARK); }

            @Override
            protected void paintComponent(Graphics g) {
                super.paintComponent(g);
                Graphics2D g2 = (Graphics2D) g;
                g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
                g2.setRenderingHint(RenderingHints.KEY_TEXT_ANTIALIASING, RenderingHints.VALUE_TEXT_ANTIALIAS_ON);

                QueueArray<String> q = queueHolder[0];
                int cap = q.getCapacidad();
                int size = q.getSize();
                int front = q.getFrontIndex();
                int rear = q.getRearIndex();
                Object[] arr = q.getArray();

                int width = getWidth();
                int height = getHeight();
                int cx = width / 2;
                int cy = height / 2 - 20;
                int radius = 110;

                // Dibujar tItulo de la sección en el gráfico
                g2.setColor(TEXT_MUTED);
                g2.setFont(new Font("Segoe UI", Font.BOLD, 12));
                g2.drawString("Visualización Circular: Capacidad = " + cap + ", Tamaño = " + size, 20, 25);

                if (size == 0) {
                    g2.setFont(new Font("Segoe UI", Font.BOLD, 16));
                    g2.setColor(ACCENT_RED);
                    g2.drawString("[ COLA VACIA ]", cx - 60, cy);
                } else if (size == cap) {
                    g2.setFont(new Font("Segoe UI", Font.BOLD, 14));
                    g2.setColor(ACCENT_GREEN);
                    g2.drawString("[ COLA LLENA ]", cx - 55, cy + 120);
                }

                // Dibujar rueda circular
                for (int i = 0; i < cap; i++) {
                    double angle = i * 2 * Math.PI / cap - Math.PI / 2;
                    int x = cx + (int) (radius * Math.cos(angle));
                    int y = cy + (int) (radius * Math.sin(angle));

                    // Determinar si este casillero está ocupado actualmente
                    boolean occupied = false;
                    for (int k = 0; k < size; k++) {
                        if ((front + k) % cap == i) {
                            occupied = true;
                            break;
                        }
                    }

                    // Celda de fondo
                    int nodeSize = 50;
                    int nx = x - nodeSize / 2;
                    int ny = y - nodeSize / 2;

                    if (occupied) {
                        g2.setColor(BG_CARD);
                        g2.fillOval(nx, ny, nodeSize, nodeSize);
                        g2.setColor(ACCENT_BLUE);
                        g2.setStroke(new BasicStroke(3));
                        g2.drawOval(nx, ny, nodeSize, nodeSize);

                        // Escribir dato
                        g2.setColor(Color.WHITE);
                        g2.setFont(new Font("Segoe UI", Font.BOLD, 13));
                        String dataStr = String.valueOf(arr[i]);
                        FontMetrics fm = g2.getFontMetrics();
                        int tx = x - fm.stringWidth(dataStr) / 2;
                        int ty = y + fm.getAscent() / 2 - 2;
                        g2.drawString(dataStr, tx, ty);
                    } else {
                        // Celda vacIa
                        g2.setColor(new Color(50, 50, 70));
                        g2.fillOval(nx, ny, nodeSize, nodeSize);
                        g2.setColor(TEXT_MUTED);
                        g2.setStroke(new BasicStroke(1, BasicStroke.CAP_BUTT, BasicStroke.JOIN_BEVEL, 0, new float[]{5}, 0));
                        g2.drawOval(nx, ny, nodeSize, nodeSize);
                    }

                    // Dibujar Indice de celda
                    g2.setColor(TEXT_MUTED);
                    g2.setFont(new Font("Segoe UI", Font.PLAIN, 10));
                    g2.drawString("idx:" + i, nx + 12, ny - 5);

                    // Indicadores de Front y Rear
                    if (size > 0) {
                        if (i == front) {
                            g2.setColor(ACCENT_BLUE);
                            g2.setFont(new Font("Segoe UI", Font.BOLD, 11));
                            g2.drawString("FRONT (F)", nx - 20, ny + nodeSize + 15);
                            g2.setStroke(new BasicStroke(2));
                            g2.drawLine(x, y + nodeSize / 2, x, y + nodeSize / 2 + 10);
                        }
                        if (i == rear) {
                            g2.setColor(ACCENT_ORANGE);
                            g2.setFont(new Font("Segoe UI", Font.BOLD, 11));
                            g2.drawString("REAR (R)", nx + 15, ny + nodeSize + 28);
                            g2.setStroke(new BasicStroke(2));
                            g2.drawLine(x, y + nodeSize / 2, x, y + nodeSize / 2 + 20);
                        }
                    }
                }
            }
        }

        CircularDrawPanel drawPanel = new CircularDrawPanel();

        // Barra de Control Superior
        JPanel controlPanel = createStyledControlPanel();
        controlPanel.setLayout(new FlowLayout(FlowLayout.LEFT, 10, 5));

        JLabel capLabel = createStyledLabel("Capacidad original:", false);
        JTextField capField = createStyledTextField(3);
        capField.setText("5");
        JButton btnCreate = createStyledButton("Crear Cola", ACCENT_PURPLE);

        JLabel dataLabel = createStyledLabel("Dato a Encolar:", false);
        JTextField dataField = createStyledTextField(8);
        dataField.setText("Elem1");
        JButton btnEnqueue = createStyledButton("Enqueue (Encolar)", ACCENT_BLUE);
        JButton btnDequeue = createStyledButton("Dequeue (Desencolar)", ACCENT_ORANGE);
        JButton btnFront = createStyledButton("Consultar Frente", ACCENT_GREEN);

        controlPanel.add(capLabel);
        controlPanel.add(capField);
        controlPanel.add(btnCreate);
        controlPanel.add(new JSeparator(JSeparator.VERTICAL));
        controlPanel.add(dataLabel);
        controlPanel.add(dataField);
        controlPanel.add(btnEnqueue);
        controlPanel.add(btnDequeue);
        controlPanel.add(btnFront);

        // Barra de Estado inferior
        JLabel statusLabel = new JLabel(" Cola inicializada con capacidad 5. VacIa.");
        statusLabel.setForeground(TEXT_MUTED);
        statusLabel.setFont(new Font("Segoe UI", Font.ITALIC, 12));
        statusLabel.setBorder(BorderFactory.createEmptyBorder(5, 10, 5, 10));

        // Acciones de Botones
        btnCreate.addActionListener(e -> {
            try {
                int c = Integer.parseInt(capField.getText().trim());
                if (c <= 0 || c > 12) {
                    statusLabel.setText("Error: Capacidad debe estar entre 1 y 12.");
                    return;
                }
                queueHolder[0] = new QueueArray<>(c);
                statusLabel.setText("Cola vacIa creada con éxito. Capacidad: " + c);
                drawPanel.repaint();
            } catch (Exception ex) {
                statusLabel.setText("Ingrese un entero válido para capacidad.");
            }
        });

        btnEnqueue.addActionListener(e -> {
            String val = dataField.getText().trim();
            if (val.isEmpty()) {
                statusLabel.setText("Error: El dato a encolar no puede estar vacIo.");
                return;
            }
            QueueArray<String> q = queueHolder[0];
            if (q.isFull()) {
                statusLabel.setText("Error: ¡La cola está llena! No se puede encolar '" + val + "'");
            } else {
                q.enqueue(val);
                statusLabel.setText("Encolado: '" + val + "' | Estado de cola: " + q);
                drawPanel.repaint();
            }
        });

        btnDequeue.addActionListener(e -> {
            QueueArray<String> q = queueHolder[0];
            try {
                String val = q.dequeue();
                statusLabel.setText("Desencolado (dequeue): '" + val + "' exitosamente.");
                drawPanel.repaint();
            } catch (ExceptionIsEmpty ex) {
                statusLabel.setText("Error: " + ex.getMessage());
            }
        });

        btnFront.addActionListener(e -> {
            QueueArray<String> q = queueHolder[0];
            try {
                String val = q.front();
                statusLabel.setText("Elemento al Frente de la cola: '" + val + "'");
            } catch (ExceptionIsEmpty ex) {
                statusLabel.setText("Error: " + ex.getMessage());
            }
        });

        mainPanel.add(controlPanel, BorderLayout.NORTH);
        mainPanel.add(drawPanel, BorderLayout.CENTER);
        mainPanel.add(statusLabel, BorderLayout.SOUTH);
        return mainPanel;
    }

    // ==========================================
    // Pestaña 2: DOBLE COLA (ACTIVIDAD 2)
    // ==========================================
    private JPanel createDequePanel() {
        JPanel mainPanel = new JPanel(new BorderLayout());
        mainPanel.setBackground(BG_DARK);

        final DequeLink<String> deque = new DequeLink<>();

        class DequeDrawPanel extends JPanel {
            DequeDrawPanel() { setBackground(BG_DARK); }

            @Override
            protected void paintComponent(Graphics g) {
                super.paintComponent(g);
                Graphics2D g2 = (Graphics2D) g;
                g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
                g2.setRenderingHint(RenderingHints.KEY_TEXT_ANTIALIASING, RenderingHints.VALUE_TEXT_ANTIALIAS_ON);

                g2.setColor(TEXT_MUTED);
                g2.setFont(new Font("Segoe UI", Font.BOLD, 12));
                g2.drawString("Visualización de Nodos: Lista Simplemente Enlazada (Doble Entrada/Salida)", 20, 25);

                if (deque.isEmpty()) {
                    g2.setFont(new Font("Segoe UI", Font.BOLD, 16));
                    g2.setColor(ACCENT_RED);
                    g2.drawString("[ DEQUE VACIO ]", getWidth() / 2 - 70, getHeight() / 2);
                    return;
                }

                int startX = 60;
                int centerY = getHeight() / 2 - 20;
                int nodeW = 75;
                int nodeH = 40;
                int gap = 55; // Espacio para la flecha

                actividad2.Node<String> current = deque.getFirstNode();
                int idx = 0;

                while (current != null) {
                    int x = startX + idx * (nodeW + gap);
                    int y = centerY;

                    // Dibujar rectángulo del nodo
                    g2.setColor(BG_CARD);
                    g2.fillRoundRect(x, y, nodeW, nodeH, 8, 8);

                    // Bordes especiales para First y Last
                    if (current == deque.getFirstNode()) {
                        g2.setColor(ACCENT_BLUE);
                        g2.setStroke(new BasicStroke(2.5f));
                    } else if (current == deque.getLastNode()) {
                        g2.setColor(ACCENT_ORANGE);
                        g2.setStroke(new BasicStroke(2.5f));
                    } else {
                        g2.setColor(TEXT_MUTED);
                        g2.setStroke(new BasicStroke(1.5f));
                    }
                    g2.drawRoundRect(x, y, nodeW, nodeH, 8, 8);

                    // Dividir el nodo visualmente en [ dato | next ]
                    g2.setColor(new Color(60, 60, 90));
                    g2.setStroke(new BasicStroke(1));
                    g2.drawLine(x + nodeW - 20, y, x + nodeW - 20, y + nodeH);

                    // Dibujar el dato
                    g2.setColor(Color.WHITE);
                    g2.setFont(new Font("Segoe UI", Font.BOLD, 12));
                    String dataStr = current.getData();
                    FontMetrics fm = g2.getFontMetrics();
                    int dataW = fm.stringWidth(dataStr);
                    // truncar si el dato es muy largo
                    if (dataW > nodeW - 28) {
                        dataStr = dataStr.substring(0, Math.min(dataStr.length(), 4)) + "..";
                    }
                    g2.drawString(dataStr, x + (nodeW - 20) / 2 - g2.getFontMetrics().stringWidth(dataStr) / 2, y + nodeH / 2 + 4);

                    // Dibujar un cIrculo/punto en el compartimiento 'next'
                    g2.setColor(ACCENT_GREEN);
                    g2.fillOval(x + nodeW - 13, y + nodeH / 2 - 3, 6, 6);

                    // Dibujar flechas de enlace
                    if (current.getNext() != null) {
                        g2.setColor(ACCENT_GREEN);
                        g2.setStroke(new BasicStroke(2));
                        // Dibujar lInea de flecha
                        int startArrowX = x + nodeW - 10;
                        int endArrowX = x + nodeW + gap;
                        g2.drawLine(startArrowX, y + nodeH / 2, endArrowX, y + nodeH / 2);
                        // Punta de flecha
                        g2.drawLine(endArrowX - 6, y + nodeH / 2 - 4, endArrowX, y + nodeH / 2);
                        g2.drawLine(endArrowX - 6, y + nodeH / 2 + 4, endArrowX, y + nodeH / 2);
                    } else {
                        // Compartimiento Next apunta a NULL
                        g2.setColor(ACCENT_RED);
                        g2.setFont(new Font("Segoe UI", Font.BOLD, 10));
                        g2.drawString("null", x + nodeW - 17, y + nodeH / 2 + 15);
                    }

                    // Punteros Frente y Final
                    if (current == deque.getFirstNode()) {
                        g2.setColor(ACCENT_BLUE);
                        g2.setFont(new Font("Segoe UI", Font.BOLD, 11));
                        g2.drawString("FRENTE (first)", x + 2, y - 25);
                        g2.setStroke(new BasicStroke(1.5f));
                        g2.drawLine(x + nodeW / 2, y - 5, x + nodeW / 2, y - 20);
                        g2.drawLine(x + nodeW / 2 - 4, y - 9, x + nodeW / 2, y - 5);
                        g2.drawLine(x + nodeW / 2 + 4, y - 9, x + nodeW / 2, y - 5);
                    }
                    if (current == deque.getLastNode()) {
                        g2.setColor(ACCENT_ORANGE);
                        g2.setFont(new Font("Segoe UI", Font.BOLD, 11));
                        g2.drawString("FINAL (last)", x + 5, y + nodeH + 25);
                        g2.setStroke(new BasicStroke(1.5f));
                        g2.drawLine(x + nodeW / 2, y + nodeH + 5, x + nodeW / 2, y + nodeH + 20);
                        g2.drawLine(x + nodeW / 2 - 4, y + nodeH + 9, x + nodeW / 2, y + nodeH + 5);
                        g2.drawLine(x + nodeW / 2 + 4, y + nodeH + 9, x + nodeW / 2, y + nodeH + 5);
                    }

                    current = current.getNext();
                    idx++;
                }
            }
        }

        DequeDrawPanel drawPanel = new DequeDrawPanel();

        // Panel de Control Superior
        JPanel controlPanel = createStyledControlPanel();
        controlPanel.setLayout(new FlowLayout(FlowLayout.LEFT, 10, 5));

        JLabel dataLabel = createStyledLabel("Dato:", false);
        JTextField dataField = createStyledTextField(10);
        dataField.setText("Zoe");

        JButton btnAddFirst = createStyledButton("Añadir al Inicio (addFirst)", ACCENT_BLUE);
        JButton btnAddLast = createStyledButton("Añadir al Final (addLast)", ACCENT_PURPLE);
        JButton btnRemoveFirst = createStyledButton("Eliminar Inicio (removeFirst)", ACCENT_ORANGE);
        JButton btnRemoveLast = createStyledButton("Eliminar Final (removeLast)", ACCENT_RED);

        controlPanel.add(dataLabel);
        controlPanel.add(dataField);
        controlPanel.add(btnAddFirst);
        controlPanel.add(btnAddLast);
        controlPanel.add(new JSeparator(JSeparator.VERTICAL));
        controlPanel.add(btnRemoveFirst);
        controlPanel.add(btnRemoveLast);

        // Barra de estado
        JLabel statusLabel = new JLabel(" Doble Cola (Deque) inicializada vacIa.");
        statusLabel.setForeground(TEXT_MUTED);
        statusLabel.setFont(new Font("Segoe UI", Font.ITALIC, 12));
        statusLabel.setBorder(BorderFactory.createEmptyBorder(5, 10, 5, 10));

        // Acciones
        btnAddFirst.addActionListener(e -> {
            String val = dataField.getText().trim();
            if (val.isEmpty()) {
                statusLabel.setText("Ingrese un dato para insertar.");
                return;
            }
            deque.addFirst(val);
            statusLabel.setText("Insertado al inicio: '" + val + "'. Estado: " + deque);
            drawPanel.repaint();
        });

        btnAddLast.addActionListener(e -> {
            String val = dataField.getText().trim();
            if (val.isEmpty()) {
                statusLabel.setText("Ingrese un dato para insertar.");
                return;
            }
            deque.addLast(val);
            statusLabel.setText("Insertado al final: '" + val + "'. Estado: " + deque);
            drawPanel.repaint();
        });

        btnRemoveFirst.addActionListener(e -> {
            try {
                String val = deque.removeFirst();
                statusLabel.setText("Eliminado del inicio (removeFirst): '" + val + "' exitosamente.");
                drawPanel.repaint();
            } catch (ExceptionIsEmpty ex) {
                statusLabel.setText("Error: " + ex.getMessage());
            }
        });

        btnRemoveLast.addActionListener(e -> {
            try {
                String val = deque.removeLast();
                statusLabel.setText("Eliminado del final (removeLast): '" + val + "' exitosamente.");
                drawPanel.repaint();
            } catch (ExceptionIsEmpty ex) {
                statusLabel.setText("Error: " + ex.getMessage());
            }
        });

        mainPanel.add(controlPanel, BorderLayout.NORTH);
        mainPanel.add(drawPanel, BorderLayout.CENTER);
        mainPanel.add(statusLabel, BorderLayout.SOUTH);
        return mainPanel;
    }

    // ==========================================
    // Pestaña 3: COLA DE PRIORIDAD (ACTIVIDAD 3)
    // ==========================================
    private JPanel createPriorityQueuePanel() {
        JPanel mainPanel = new JPanel(new BorderLayout());
        mainPanel.setBackground(BG_DARK);

        final PriorityQueueLinkSort<String, Integer> pQueue = new PriorityQueueLinkSort<>();

        class PQDrawPanel extends JPanel {
            PQDrawPanel() { setBackground(BG_DARK); }

            @Override
            protected void paintComponent(Graphics g) {
                super.paintComponent(g);
                Graphics2D g2 = (Graphics2D) g;
                g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
                g2.setRenderingHint(RenderingHints.KEY_TEXT_ANTIALIASING, RenderingHints.VALUE_TEXT_ANTIALIAS_ON);

                g2.setColor(TEXT_MUTED);
                g2.setFont(new Font("Segoe UI", Font.BOLD, 12));
                g2.drawString("Orden de Prioridad: Los elementos se encolan automáticamente de Mayor a Menor prioridad.", 20, 25);

                if (pQueue.isEmpty()) {
                    g2.setFont(new Font("Segoe UI", Font.BOLD, 16));
                    g2.setColor(ACCENT_RED);
                    g2.drawString("[ COLA DE PRIORIDAD VACIA ]", getWidth() / 2 - 110, getHeight() / 2);
                    return;
                }

                int startX = 50;
                int centerY = getHeight() / 2 - 20;
                int nodeW = 100;
                int nodeH = 45;
                int gap = 50;

                actividad3.Node<PriorityQueueLinkSort<String, Integer>.EntryNode> current = pQueue.getFirstNode();
                int idx = 0;

                while (current != null) {
                    int x = startX + idx * (nodeW + gap);
                    int y = centerY;

                    // Dibujar tarjeta del nodo
                    g2.setColor(BG_CARD);
                    g2.fillRoundRect(x, y, nodeW, nodeH, 8, 8);

                    // Borde
                    if (current == pQueue.getFirstNode()) {
                        g2.setColor(ACCENT_BLUE);
                        g2.setStroke(new BasicStroke(2.5f));
                    } else if (current == pQueue.getLastNode()) {
                        g2.setColor(ACCENT_ORANGE);
                        g2.setStroke(new BasicStroke(2.5f));
                    } else {
                        g2.setColor(TEXT_MUTED);
                        g2.setStroke(new BasicStroke(1.5f));
                    }
                    g2.drawRoundRect(x, y, nodeW, nodeH, 8, 8);

                    // Dividir tarjeta en [ dato | priority ]
                    g2.setColor(new Color(60, 60, 90));
                    g2.setStroke(new BasicStroke(1));
                    g2.drawLine(x, y + nodeH - 18, x + nodeW, y + nodeH - 18);

                    // Escribir el dato en la sección superior
                    g2.setColor(Color.WHITE);
                    g2.setFont(new Font("Segoe UI", Font.BOLD, 12));
                    String dataStr = String.valueOf(current.getData().data);
                    FontMetrics fm = g2.getFontMetrics();
                    if (fm.stringWidth(dataStr) > nodeW - 10) {
                        dataStr = dataStr.substring(0, Math.min(dataStr.length(), 6)) + "..";
                    }
                    g2.drawString(dataStr, x + nodeW / 2 - g2.getFontMetrics().stringWidth(dataStr) / 2, y + 18);

                    // Escribir la prioridad en la sección inferior
                    g2.setColor(ACCENT_PURPLE);
                    g2.setFont(new Font("Segoe UI", Font.BOLD, 10));
                    String prioStr = "Prio: " + current.getData().priority;
                    g2.drawString(prioStr, x + nodeW / 2 - g2.getFontMetrics().stringWidth(prioStr) / 2, y + nodeH - 5);

                    // Flechas de Enlace
                    if (current.getNext() != null) {
                        g2.setColor(ACCENT_GREEN);
                        g2.setStroke(new BasicStroke(2));
                        int startArrowX = x + nodeW;
                        int endArrowX = x + nodeW + gap;
                        g2.drawLine(startArrowX, y + nodeH / 2, endArrowX, y + nodeH / 2);
                        g2.drawLine(endArrowX - 6, y + nodeH / 2 - 4, endArrowX, y + nodeH / 2);
                        g2.drawLine(endArrowX - 6, y + nodeH / 2 + 4, endArrowX, y + nodeH / 2);
                    }

                    // Punteros Frente y Fondo
                    if (current == pQueue.getFirstNode()) {
                        g2.setColor(ACCENT_BLUE);
                        g2.setFont(new Font("Segoe UI", Font.BOLD, 11));
                        g2.drawString("FRENTE (Max Prio)", x - 5, y - 25);
                        g2.setStroke(new BasicStroke(1.5f));
                        g2.drawLine(x + nodeW / 2, y - 5, x + nodeW / 2, y - 20);
                        g2.drawLine(x + nodeW / 2 - 4, y - 9, x + nodeW / 2, y - 5);
                        g2.drawLine(x + nodeW / 2 + 4, y - 9, x + nodeW / 2, y - 5);
                    }
                    if (current == pQueue.getLastNode()) {
                        g2.setColor(ACCENT_ORANGE);
                        g2.setFont(new Font("Segoe UI", Font.BOLD, 11));
                        g2.drawString("FONDO (Min Prio)", x - 2, y + nodeH + 25);
                        g2.setStroke(new BasicStroke(1.5f));
                        g2.drawLine(x + nodeW / 2, y + nodeH + 5, x + nodeW / 2, y + nodeH + 20);
                        g2.drawLine(x + nodeW / 2 - 4, y + nodeH + 9, x + nodeW / 2, y + nodeH + 5);
                        g2.drawLine(x + nodeW / 2 + 4, y + nodeH + 9, x + nodeW / 2, y + nodeH + 5);
                    }

                    current = current.getNext();
                    idx++;
                }
            }
        }

        PQDrawPanel drawPanel = new PQDrawPanel();

        // Control Panel
        JPanel controlPanel = createStyledControlPanel();
        controlPanel.setLayout(new FlowLayout(FlowLayout.LEFT, 10, 5));

        JLabel dataLabel = createStyledLabel("Dato (E):", false);
        JTextField dataField = createStyledTextField(10);
        dataField.setText("Tarea A");

        JLabel prioLabel = createStyledLabel("Prioridad (Entero):", false);
        JTextField prioField = createStyledTextField(4);
        prioField.setText("3");

        JButton btnEnqueue = createStyledButton("Enqueue (Encolar Ordenado)", ACCENT_BLUE);
        JButton btnDequeue = createStyledButton("Dequeue (Extraer Mayor Prio)", ACCENT_ORANGE);

        controlPanel.add(dataLabel);
        controlPanel.add(dataField);
        controlPanel.add(prioLabel);
        controlPanel.add(prioField);
        controlPanel.add(btnEnqueue);
        controlPanel.add(new JSeparator(JSeparator.VERTICAL));
        controlPanel.add(btnDequeue);

        // Status
        JLabel statusLabel = new JLabel(" Cola de Prioridad Ordenada inicializada.");
        statusLabel.setForeground(TEXT_MUTED);
        statusLabel.setFont(new Font("Segoe UI", Font.ITALIC, 12));
        statusLabel.setBorder(BorderFactory.createEmptyBorder(5, 10, 5, 10));

        // Eventos
        btnEnqueue.addActionListener(e -> {
            String val = dataField.getText().trim();
            String prioText = prioField.getText().trim();
            if (val.isEmpty()) {
                statusLabel.setText("Ingrese un dato.");
                return;
            }
            try {
                int p = Integer.parseInt(prioText);
                pQueue.enqueue(val, p);
                statusLabel.setText("Encolado: '" + val + "' con prioridad " + p);
                drawPanel.repaint();
            } catch (NumberFormatException ex) {
                statusLabel.setText("Error: La prioridad debe ser un número entero.");
            }
        });

        btnDequeue.addActionListener(e -> {
            try {
                String val = pQueue.dequeue();
                statusLabel.setText("Desencolado (dequeue): '" + val + "' (Se removió el elemento con mayor prioridad).");
                drawPanel.repaint();
            } catch (ExceptionIsEmpty ex) {
                statusLabel.setText("Error: " + ex.getMessage());
            }
        });

        mainPanel.add(controlPanel, BorderLayout.NORTH);
        mainPanel.add(drawPanel, BorderLayout.CENTER);
        mainPanel.add(statusLabel, BorderLayout.SOUTH);
        return mainPanel;
    }

    // ==========================================
    // Pestaña 4: COLA HIBRIDA MULTINIVEL (EJERCICIO 4)
    // ==========================================
    private JPanel createHybridQueuePanel() {
        JPanel mainPanel = new JPanel(new BorderLayout());
        mainPanel.setBackground(BG_DARK);

        // Inicializamos con 3 niveles
        final PriorityQueueHybrid<String, Integer>[] hybridHolder = new PriorityQueueHybrid[]{ new PriorityQueueHybrid<>(3) };

        class HybridDrawPanel extends JPanel {
            HybridDrawPanel() { setBackground(BG_DARK); }

            @Override
            protected void paintComponent(Graphics g) {
                super.paintComponent(g);
                Graphics2D g2 = (Graphics2D) g;
                g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
                g2.setRenderingHint(RenderingHints.KEY_TEXT_ANTIALIASING, RenderingHints.VALUE_TEXT_ANTIALIAS_ON);

                PriorityQueueHybrid<String, Integer> hq = hybridHolder[0];
                int numLevels = hq.getNumLevels();
                SortedQueue<String, Integer>[] levels = hq.getLevels();

                g2.setColor(TEXT_MUTED);
                g2.setFont(new Font("Segoe UI", Font.BOLD, 12));
                g2.drawString("Visualización Multinivel: Cada carril es un nivel. Los elementos se ordenan por su Valor Secundario.", 20, 25);

                if (hq.isEmpty()) {
                    g2.setFont(new Font("Segoe UI", Font.BOLD, 16));
                    g2.setColor(ACCENT_RED);
                    g2.drawString("[ COLA HIBRIDA VACIA ]", getWidth() / 2 - 90, getHeight() / 2);
                    return;
                }

                int startY = 60;
                int rowHeight = 100;
                int startX = 120;
                int nodeW = 100;
                int nodeH = 40;
                int gap = 35;

                for (int i = numLevels - 1; i >= 0; i--) {
                    int y = startY + (numLevels - 1 - i) * rowHeight;

                    // Dibujar etiqueta de nivel lateral
                    g2.setColor(ACCENT_BLUE);
                    g2.setFont(new Font("Segoe UI", Font.BOLD, 12));
                    g2.drawString("NIVEL " + i, 20, y + nodeH / 2 + 5);

                    // Dibujar carril/pista del nivel
                    g2.setColor(new Color(40, 40, 60));
                    g2.setStroke(new BasicStroke(1));
                    g2.drawRoundRect(10, y - 10, getWidth() - 30, nodeH + 20, 10, 10);

                    SortedQueue<String, Integer> sq = levels[i];
                    if (sq.isEmpty()) {
                        g2.setColor(TEXT_MUTED);
                        g2.setFont(new Font("Segoe UI", Font.ITALIC, 11));
                        g2.drawString("(vacIo)", startX, y + nodeH / 2 + 5);
                        continue;
                    }

                    // Recorrer SortedQueue de este nivel
                    actividadEjercicio4.Node<Entry<String, Integer>> current = sq.getHead();
                    int idx = 0;

                    while (current != null) {
                        int x = startX + idx * (nodeW + gap);

                        // Dibujar tarjeta de nodo
                        g2.setColor(BG_CARD);
                        g2.fillRoundRect(x, y, nodeW, nodeH, 8, 8);
                        g2.setColor(ACCENT_GREEN);
                        g2.setStroke(new BasicStroke(1.5f));
                        g2.drawRoundRect(x, y, nodeW, nodeH, 8, 8);

                        // Escribir dato y valor secundario
                        g2.setColor(Color.WHITE);
                        g2.setFont(new Font("Segoe UI", Font.BOLD, 11));
                        String dStr = current.getData().getData();
                        if (g2.getFontMetrics().stringWidth(dStr) > nodeW - 10) {
                            dStr = dStr.substring(0, Math.min(dStr.length(), 6)) + "..";
                        }
                        g2.drawString(dStr, x + nodeW / 2 - g2.getFontMetrics().stringWidth(dStr) / 2, y + 17);

                        g2.setColor(ACCENT_ORANGE);
                        g2.setFont(new Font("Segoe UI", Font.BOLD, 9));
                        String secStr = "Sec: " + current.getData().getValue();
                        g2.drawString(secStr, x + nodeW / 2 - g2.getFontMetrics().stringWidth(secStr) / 2, y + nodeH - 5);

                        // Enlace con flecha
                        if (current.getNext() != null) {
                            g2.setColor(ACCENT_GREEN);
                            g2.setStroke(new BasicStroke(1.5f));
                            int sxArrow = x + nodeW;
                            int exArrow = x + nodeW + gap;
                            g2.drawLine(sxArrow, y + nodeH / 2, exArrow, y + nodeH / 2);
                            g2.drawLine(exArrow - 5, y + nodeH / 2 - 3, exArrow, y + nodeH / 2);
                            g2.drawLine(exArrow - 5, y + nodeH / 2 + 3, exArrow, y + nodeH / 2);
                        }

                        current = current.getNext();
                        idx++;
                    }
                }
            }
        }

        HybridDrawPanel drawPanel = new HybridDrawPanel();

        // Panel de Control Superior
        JPanel controlPanel = createStyledControlPanel();
        controlPanel.setLayout(new FlowLayout(FlowLayout.LEFT, 10, 5));

        JLabel levelsLabel = createStyledLabel("Niveles:", false);
        JTextField levelsField = createStyledTextField(2);
        levelsField.setText("3");
        JButton btnCreate = createStyledButton("Crear Cola HIbrida", ACCENT_PURPLE);

        JLabel dataLabel = createStyledLabel("Dato:", false);
        JTextField dataField = createStyledTextField(6);
        dataField.setText("A");

        JLabel levelLabel = createStyledLabel("Nivel principal:", false);
        JTextField levelField = createStyledTextField(2);
        levelField.setText("2");

        JLabel secLabel = createStyledLabel("Val. Secundario:", false);
        JTextField secField = createStyledTextField(3);
        secField.setText("5");

        JButton btnEnqueue = createStyledButton("Enqueue (Encolar)", ACCENT_BLUE);
        JButton btnDequeue = createStyledButton("Dequeue (Extraer)", ACCENT_ORANGE);

        controlPanel.add(levelsLabel);
        controlPanel.add(levelsField);
        controlPanel.add(btnCreate);
        controlPanel.add(new JSeparator(JSeparator.VERTICAL));
        controlPanel.add(dataLabel);
        controlPanel.add(dataField);
        controlPanel.add(levelLabel);
        controlPanel.add(levelField);
        controlPanel.add(secLabel);
        controlPanel.add(secField);
        controlPanel.add(btnEnqueue);
        controlPanel.add(btnDequeue);

        // Estado
        JLabel statusLabel = new JLabel(" Cola HIbrida inicializada con 3 niveles.");
        statusLabel.setForeground(TEXT_MUTED);
        statusLabel.setFont(new Font("Segoe UI", Font.ITALIC, 12));
        statusLabel.setBorder(BorderFactory.createEmptyBorder(5, 10, 5, 10));

        // Eventos
        btnCreate.addActionListener(e -> {
            try {
                int L = Integer.parseInt(levelsField.getText().trim());
                if (L <= 0 || L > 8) {
                    statusLabel.setText("Error: Niveles deben estar entre 1 y 8.");
                    return;
                }
                hybridHolder[0] = new PriorityQueueHybrid<>(L);
                statusLabel.setText("Cola HIbrida creada con exitosamente con " + L + " niveles.");
                drawPanel.repaint();
            } catch (Exception ex) {
                statusLabel.setText("Ingrese un número válido de niveles.");
            }
        });

        btnEnqueue.addActionListener(e -> {
            String val = dataField.getText().trim();
            String levelText = levelField.getText().trim();
            String secText = secField.getText().trim();
            if (val.isEmpty()) {
                statusLabel.setText("Ingrese un dato.");
                return;
            }
            try {
                int lvl = Integer.parseInt(levelText);
                int sec = Integer.parseInt(secText);
                PriorityQueueHybrid<String, Integer> hq = hybridHolder[0];

                if (lvl < 0 || lvl >= hq.getNumLevels()) {
                    statusLabel.setText("Error: Nivel principal inválido. Debe ser de 0 a " + (hq.getNumLevels() - 1));
                    return;
                }

                hq.enqueue(val, lvl, sec);
                statusLabel.setText("Encolado en Nivel " + lvl + ": '" + val + "' con Valor Secundario de desempate: " + sec);
                drawPanel.repaint();
            } catch (NumberFormatException ex) {
                statusLabel.setText("Error: El nivel y el valor secundario deben ser números enteros.");
            }
        });

        btnDequeue.addActionListener(e -> {
            PriorityQueueHybrid<String, Integer> hq = hybridHolder[0];
            try {
                String val = hq.dequeue();
                statusLabel.setText("Desencolado global (dequeue): '" + val + "' (Del nivel superior que contenIa elementos).");
                drawPanel.repaint();
            } catch (ExceptionIsEmpty ex) {
                statusLabel.setText("Error: " + ex.getMessage());
            }
        });

        mainPanel.add(controlPanel, BorderLayout.NORTH);
        mainPanel.add(drawPanel, BorderLayout.CENTER);
        mainPanel.add(statusLabel, BorderLayout.SOUTH);
        return mainPanel;
    }

    // ==========================================
    // METODO PRINCIPAL DE LANZAMIENTO (MAIN)
    // ==========================================
    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            MainDashboard md = new MainDashboard();
            md.setVisible(true);
        });
    }
}