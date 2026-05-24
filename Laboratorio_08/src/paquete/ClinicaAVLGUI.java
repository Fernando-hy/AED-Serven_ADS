package paquete;

import exceptions.ItemDuplicated;
import exceptions.ItemNotFound;
import exceptions.ExceptionIsEmpty;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import javax.swing.border.LineBorder;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.Random;

/**
 * Interfaz Gráfica Profesional y Completa para el Gestor de Turnos de la Clínica.
 * Utiliza un Árbol AVL para administrar la cola de espera de forma eficiente y auto-balanceada.
 * Ofrece:
 * - Lienzo de visualización del Árbol AVL en tiempo real con factores de equilibrio (bf).
 * - Tarjetas estadísticas de monitoreo del Árbol (Total, Altura, Siguiente, Estado).
 * - Generador de turnos aleatorios para pruebas rápidas de rebalanceo y rotaciones.
 * - Registro con formato premium de bitácora y lista ordenada de pacientes.
 * - Estética moderna inspirada en interfaces médicas con paleta azul-teal y anti-aliasing.
 */
public class ClinicaAVLGUI extends JFrame {
    
    // Núcleo del Árbol AVL
    private AVLTree<Integer> turnos;
    
    // Componentes del Panel de Controles
    private JTextField txtTurno;
    private JTextArea txtLog;
    private DefaultListModel<String> listModelPacientes;
    private JList<String> listPacientes;
    
    // Componente del Visualizador Gráfico del Árbol
    private AVLVisualizerPanel visualizerPanel;
    
    // Etiquetas de las Tarjetas de Estadísticas
    private JLabel lblTotalPacientes;
    private JLabel lblAlturaArbol;
    private JLabel lblSiguienteTurno;
    private JLabel lblEstadoBalance;
    
    // Variable para rastrear qué turno está siendo buscado e iluminarlo en el lienzo
    private Integer searchedTurn = null;

    /**
     * Constructor que inicializa y configura toda la interfaz profesional.
     */
    public ClinicaAVLGUI() {
        // Inicializar el árbol AVL
        turnos = new AVLTree<>();

        // Configuración básica de la ventana principal
        setTitle("Panel Clínico de Control - Gestor de Turnos (Árbol AVL)");
        setSize(1000, 680);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setMinimumSize(new Dimension(850, 580));
        setLocationRelativeTo(null); // Centrar en pantalla
        
        // Establecer el Layout Principal
        setLayout(new BorderLayout());
        getContentPane().setBackground(new Color(240, 244, 248)); // Fondo azul grisáceo muy suave

        // --- 1. CABECERA CON DEGRADADO MÉDICO ---
        JPanel panelCabecera = new JPanel() {
            @Override
            protected void paintComponent(Graphics g) {
                super.paintComponent(g);
                Graphics2D g2 = (Graphics2D) g.create();
                g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
                // Degradado elegante de Azul Profundo a Azul Clínico
                GradientPaint gp = new GradientPaint(0, 0, new Color(26, 54, 93), getWidth(), 0, new Color(43, 108, 176));
                g2.setPaint(gp);
                g2.fillRect(0, 0, getWidth(), getHeight());
                g2.dispose();
            }
        };
        panelCabecera.setLayout(new BorderLayout());
        panelCabecera.setBorder(new EmptyBorder(15, 25, 15, 25));
        panelCabecera.setPreferredSize(new Dimension(getWidth(), 80));
        
        JLabel lblTitulo = new JLabel("CENTRO MÉDICO - PANEL DE CONTROL AVL");
        lblTitulo.setFont(new Font("Segoe UI", Font.BOLD, 22));
        lblTitulo.setForeground(Color.WHITE);
        
        JLabel lblSubtitulo = new JLabel("Gestión Automática de Turnos con Auto-Balanceo de Prioridades");
        lblSubtitulo.setFont(new Font("Segoe UI", Font.PLAIN, 12));
        lblSubtitulo.setForeground(new Color(226, 232, 240));
        
        JPanel panelTextosCabecera = new JPanel(new GridLayout(2, 1, 2, 2));
        panelTextosCabecera.setOpaque(false);
        panelTextosCabecera.add(lblTitulo);
        panelTextosCabecera.add(lblSubtitulo);
        
        // Icono Clínico representativo (Pintado con texto/emoji)
        JLabel lblIcono = new JLabel("🏥 ");
        lblIcono.setFont(new Font("Segoe UI", Font.PLAIN, 28));
        lblIcono.setForeground(Color.WHITE);
        
        panelCabecera.add(panelTextosCabecera, BorderLayout.WEST);
        panelCabecera.add(lblIcono, BorderLayout.EAST);
        
        add(panelCabecera, BorderLayout.NORTH);

        // --- 2. CUERPO PRINCIPAL CON SPLIT PANE ---
        JSplitPane splitPane = new JSplitPane(JSplitPane.HORIZONTAL_SPLIT);
        splitPane.setBorder(null);
        splitPane.setDividerLocation(340);
        splitPane.setDividerSize(6);
        splitPane.setContinuousLayout(true);

        // --- PANEL IZQUIERDO: CONTROLES Y ESTADÍSTICAS ---
        JPanel panelIzquierdo = new JPanel();
        panelIzquierdo.setLayout(new BoxLayout(panelIzquierdo, BoxLayout.Y_AXIS));
        panelIzquierdo.setBackground(Color.WHITE);
        panelIzquierdo.setBorder(new EmptyBorder(15, 15, 15, 15));

        // -- Subpanel A: Controles de Turnos --
        JPanel panelEntrada = new JPanel(new BorderLayout(5, 5));
        panelEntrada.setOpaque(false);
        panelEntrada.setBorder(BorderFactory.createTitledBorder(
                BorderFactory.createLineBorder(new Color(226, 232, 240), 1, true), 
                " Registro y Búsqueda ", 0, 0, 
                new Font("Segoe UI", Font.BOLD, 12), new Color(74, 85, 104)));
        panelEntrada.setMaximumSize(new Dimension(400, 100));
        
        txtTurno = new JTextField();
        txtTurno.setFont(new Font("Segoe UI", Font.BOLD, 16));
        txtTurno.setBorder(BorderFactory.createCompoundBorder(
                new LineBorder(new Color(203, 213, 223), 1, true),
                new EmptyBorder(8, 10, 8, 10)
        ));
        
        // Añadir placeholder instructivo
        txtTurno.setToolTipText("Ingrese el número de turno");
        
        panelEntrada.add(new JLabel("Número de Turno: "), BorderLayout.NORTH);
        panelEntrada.add(txtTurno, BorderLayout.CENTER);
        
        panelIzquierdo.add(panelEntrada);
        panelIzquierdo.add(Box.createRigidArea(new Dimension(0, 12)));

        // -- Subpanel B: Botones de Acción (Grid elegante) --
        JPanel panelBotones = new JPanel(new GridLayout(3, 2, 8, 8));
        panelBotones.setOpaque(false);
        panelBotones.setMaximumSize(new Dimension(400, 140));
        
        JButton btnRegistrar = crearBoton("Registrar", new Color(49, 151, 149), Color.WHITE);
        JButton btnAtender = crearBoton("Atender Sig.", new Color(229, 62, 62), Color.WHITE);
        JButton btnBuscar = crearBoton("Buscar Turno", new Color(66, 153, 225), Color.WHITE);
        JButton btnAleatorio = crearBoton("G. Aleatorios", new Color(72, 187, 120), Color.WHITE);
        JButton btnLimpiarTodo = crearBoton("Limpiar Todo", new Color(113, 128, 150), Color.WHITE);
        JButton btnRestablecerBusq = crearBoton("Limpiar Busq.", new Color(237, 137, 54), Color.WHITE);
        
        panelBotones.add(btnRegistrar);
        panelBotones.add(btnAtender);
        panelBotones.add(btnBuscar);
        panelBotones.add(btnAleatorio);
        panelBotones.add(btnLimpiarTodo);
        panelBotones.add(btnRestablecerBusq);
        
        panelIzquierdo.add(panelBotones);
        panelIzquierdo.add(Box.createRigidArea(new Dimension(0, 15)));

        // -- Subpanel C: Tarjetas Estadísticas del Árbol AVL --
        JPanel panelMetricas = new JPanel(new GridLayout(2, 2, 10, 10));
        panelMetricas.setOpaque(false);
        panelMetricas.setBorder(BorderFactory.createTitledBorder(
                BorderFactory.createLineBorder(new Color(226, 232, 240), 1, true), 
                " Estado Clínico de Espera ", 0, 0, 
                new Font("Segoe UI", Font.BOLD, 12), new Color(74, 85, 104)));
        
        lblTotalPacientes = new JLabel("0", SwingConstants.CENTER);
        lblAlturaArbol = new JLabel("0", SwingConstants.CENTER);
        lblSiguienteTurno = new JLabel("-", SwingConstants.CENTER);
        lblEstadoBalance = new JLabel("Vacío", SwingConstants.CENTER);
        
        panelMetricas.add(crearTarjetaMetrica("Total Pacientes", lblTotalPacientes, new Color(49, 151, 149)));
        panelMetricas.add(crearTarjetaMetrica("Altura Árbol", lblAlturaArbol, new Color(66, 153, 225)));
        panelMetricas.add(crearTarjetaMetrica("Siguiente Turno", lblSiguienteTurno, new Color(229, 62, 62)));
        panelMetricas.add(crearTarjetaMetrica("Estado AVL", lblEstadoBalance, new Color(72, 187, 120)));
        
        panelIzquierdo.add(panelMetricas);
        panelIzquierdo.add(Box.createVerticalGlue()); // Empujar todo hacia arriba
        
        splitPane.setLeftComponent(panelIzquierdo);

        // --- PANEL DERECHO: VISUALIZACIÓN MULTI-PESTAÑA ---
        JTabbedPane tabbedPane = new JTabbedPane();
        tabbedPane.setFont(new Font("Segoe UI", Font.BOLD, 12));
        tabbedPane.setBackground(Color.WHITE);
        
        // Pestaña 1: Lienzo Gráfico
        visualizerPanel = new AVLVisualizerPanel();
        tabbedPane.addTab("🌳 Estructura AVL (Lienzo)", visualizerPanel);
        
        // Pestaña 2: Lista Ordenada de Pacientes
        listModelPacientes = new DefaultListModel<>();
        listPacientes = new JList<>(listModelPacientes);
        listPacientes.setFont(new Font("Segoe UI", Font.PLAIN, 14));
        listPacientes.setFixedCellHeight(30);
        listPacientes.setCellRenderer(new DefaultListCellRenderer() {
            @Override
            public Component getListCellRendererComponent(JList<?> list, Object value, int index, boolean isSelected, boolean cellHasFocus) {
                JLabel label = (JLabel) super.getListCellRendererComponent(list, value, index, isSelected, cellHasFocus);
                label.setBorder(new EmptyBorder(5, 10, 5, 10));
                // Estilizar de forma alterna para mejor legibilidad
                if (!isSelected) {
                    if (index % 2 == 0) {
                        label.setBackground(new Color(247, 250, 252));
                    } else {
                        label.setBackground(Color.WHITE);
                    }
                }
                return label;
            }
        });
        
        JScrollPane scrollPacientes = new JScrollPane(listPacientes);
        scrollPacientes.setBorder(new EmptyBorder(10, 10, 10, 10));
        tabbedPane.addTab("📋 Pacientes Activos", scrollPacientes);

        // Pestaña 3: Registro de Logs
        txtLog = new JTextArea();
        txtLog.setEditable(false);
        txtLog.setFont(new Font("Consolas", Font.PLAIN, 12));
        txtLog.setForeground(new Color(45, 55, 72));
        txtLog.setBackground(new Color(250, 250, 250));
        txtLog.setBorder(new EmptyBorder(10, 10, 10, 10));
        
        JScrollPane scrollLog = new JScrollPane(txtLog);
        scrollLog.setBorder(new EmptyBorder(10, 10, 10, 10));
        tabbedPane.addTab("💻 Historial Técnico", scrollLog);

        splitPane.setRightComponent(tabbedPane);
        add(splitPane, BorderLayout.CENTER);

        // --- ASIGNACIÓN DE ACCIONES ---
        btnRegistrar.addActionListener(e -> registrarTurno());
        btnAtender.addActionListener(e -> atenderTurno());
        btnBuscar.addActionListener(e -> buscarTurno());
        btnAleatorio.addActionListener(e -> generarAleatorios());
        btnLimpiarTodo.addActionListener(e -> limpiarTodo());
        btnRestablecerBusq.addActionListener(e -> limpiarBusqueda());
        
        // Permitir dar "Enter" en el campo de texto para registrar rápidamente
        txtTurno.addActionListener(e -> registrarTurno());
        
        // Mensaje de bienvenida inicial
        registrarMensaje("🏥 Sistema de Gestión de Pacientes AVL Iniciado.");
        registrarMensaje("ℹ️ Ingrese un número de turno y presione 'Registrar' para comenzar.");
        
        // Sincronizar la vista
        actualizarVista();
    }

    /**
     * Registra un turno ingresado por el usuario en el árbol AVL.
     */
    private void registrarTurno() {
        try {
            int t = obtenerTurnoInput();
            searchedTurn = null; // Limpiar resaltado de búsqueda previa
            turnos.insert(t);
            registrarMensaje("✅ Turno " + t + " registrado exitosamente en el Árbol AVL.");
            actualizarVista();
        } catch (NumberFormatException ex) {
            JOptionPane.showMessageDialog(this, "⚠️ Error: Por favor, ingrese un número entero válido.", "Turno Inválido", JOptionPane.WARNING_MESSAGE);
            registrarMensaje("⚠️ Error: Inserción fallida debido a número inválido.");
        } catch (ItemDuplicated ex) {
            JOptionPane.showMessageDialog(this, "❌ Error: " + ex.getMessage(), "Turno Duplicado", JOptionPane.ERROR_MESSAGE);
            registrarMensaje("❌ Error: " + ex.getMessage());
        }
        limpiarInput();
    }

    /**
     * Atiende un turno (lo elimina de la cola AVL).
     * Si el campo de texto está vacío, se busca y atiende automáticamente al siguiente paciente (mínimo).
     */
    private void atenderTurno() {
        try {
            int t;
            searchedTurn = null; // Limpiar resaltado previo
            
            // Si el campo de entrada está vacío, atendemos inteligentemente al siguiente paciente en la cola (mínimo)
            if (txtTurno.getText().trim().isEmpty()) {
                Integer min = obtenerMinTurno(turnos.getRoot());
                if (min == null) {
                    registrarMensaje("📋 No hay pacientes en espera para atender.");
                    JOptionPane.showMessageDialog(this, "📋 No hay pacientes en cola de espera actualmente.", "Cola Vacía", JOptionPane.INFORMATION_MESSAGE);
                    return;
                }
                t = min;
            } else {
                t = obtenerTurnoInput();
            }
            
            turnos.delete(t);
            registrarMensaje("👨‍⚕️ Turno " + t + " atendido. Eliminado exitosamente del Árbol AVL.");
            actualizarVista();
        } catch (NumberFormatException ex) {
            JOptionPane.showMessageDialog(this, "⚠️ Error: Por favor, ingrese un número entero válido.", "Turno Inválido", JOptionPane.WARNING_MESSAGE);
            registrarMensaje("⚠️ Error: Atención fallida debido a número inválido.");
        } catch (ExceptionIsEmpty | ItemNotFound ex) {
            JOptionPane.showMessageDialog(this, "❌ Error: " + ex.getMessage(), "No Encontrado", JOptionPane.ERROR_MESSAGE);
            registrarMensaje("❌ Error: " + ex.getMessage());
        }
        limpiarInput();
    }

    /**
     * Busca un turno en el árbol AVL y lo resalta en el visualizador gráfico.
     */
    private void buscarTurno() {
        try {
            int t = obtenerTurnoInput();
            boolean found = turnos.search(t);
            if (found) {
                searchedTurn = t; // Resalta el nodo en el lienzo
                registrarMensaje("🔍 Búsqueda: El Turno " + t + " se encuentra EN ESPERA.");
                JOptionPane.showMessageDialog(this, "🔍 ¡Turno " + t + " encontrado! Está en cola de espera activa.", "Búsqueda Exitosa", JOptionPane.INFORMATION_MESSAGE);
            } else {
                searchedTurn = null;
                registrarMensaje("🔍 Búsqueda: El Turno " + t + " NO existe en el sistema.");
                JOptionPane.showMessageDialog(this, "🔍 Turno " + t + " NO encontrado en la cola de espera.", "No Encontrado", JOptionPane.WARNING_MESSAGE);
            }
            actualizarVista();
        } catch (NumberFormatException ex) {
            JOptionPane.showMessageDialog(this, "⚠️ Error: Por favor, ingrese un número entero válido.", "Turno Inválido", JOptionPane.WARNING_MESSAGE);
        } catch (ItemNotFound ex) {
            searchedTurn = null;
            registrarMensaje("🔍 Búsqueda: El Turno NO existe."); 
            JOptionPane.showMessageDialog(this, "🔍 Turno NO encontrado.", "No Encontrado", JOptionPane.WARNING_MESSAGE);
            actualizarVista();
        }
        limpiarInput();
    }

    /**
     * Genera 5 turnos aleatorios únicos para poblar rápidamente el árbol AVL y validar rebalanceos.
     */
    private void generarAleatorios() {
        Random rand = new Random();
        int insertados = 0;
        searchedTurn = null;
        
        registrarMensaje("⚙️ Generando turnos aleatorios de demostración...");
        while (insertados < 5) {
            int num = rand.nextInt(90) + 10; // Números entre 10 y 99
            try {
                turnos.insert(num);
                registrarMensaje("📥 Generado: Turno " + num + " registrado.");
                insertados++;
            } catch (ItemDuplicated ignored) {
                // Si sale duplicado, reintentamos con otro
            }
        }
        actualizarVista();
    }

    /**
     * Limpia el estado de búsqueda (remueve el resplandor de resaltado del nodo buscado).
     */
    private void limpiarBusqueda() {
        searchedTurn = null;
        limpiarInput(); // Borrar el contenido del campo de texto de búsqueda
        registrarMensaje("🧹 Resaltado de búsqueda restablecido.");
        actualizarVista(); // Actualizar el lienzo y sincronizar
    }

    /**
     * Elimina todos los turnos del árbol AVL.
     */
    private void limpiarTodo() {
        int confirm = JOptionPane.showConfirmDialog(
                this, 
                "¿Está seguro de que desea limpiar todos los turnos de la cola?", 
                "Confirmar Limpieza", 
                JOptionPane.YES_NO_OPTION, 
                JOptionPane.QUESTION_MESSAGE
        );
        if (confirm == JOptionPane.YES_OPTION) {
            turnos = new AVLTree<>();
            searchedTurn = null;
            registrarMensaje("🧹 Todos los turnos han sido eliminados del sistema.");
            actualizarVista();
        }
    }

    /**
     * Obtiene el valor numérico del campo de texto de entrada.
     */
    private int obtenerTurnoInput() throws NumberFormatException {
        return Integer.parseInt(txtTurno.getText().trim());
    }

    /**
     * Limpia el campo de entrada y enfoca de nuevo el control.
     */
    private void limpiarInput() {
        txtTurno.setText("");
        txtTurno.requestFocus();
    }

    /**
     * Sincroniza todos los paneles del dashboard con el estado actual del árbol AVL.
     */
    private void actualizarVista() {
        // 1. Repintar Lienzo AVL
        visualizerPanel.repaint();

        // 2. Actualizar Lista Pestaña Pacientes Activos
        listModelPacientes.clear();
        String inOrden = recorrerInOrden(turnos.getRoot());
        if (!inOrden.isEmpty()) {
            String[] tokens = inOrden.trim().split(" ");
            for (String tok : tokens) {
                listModelPacientes.addElement("👤 Paciente con Turno #" + tok);
            }
        }

        // 3. Calcular Métricas para las Tarjetas
        int total = turnos.getRoot() != null ? countNodes(turnos.getRoot()) : 0;
        int altura = turnos.height();
        Integer siguiente = obtenerMinTurno(turnos.getRoot());
        
        lblTotalPacientes.setText(String.valueOf(total));
        lblAlturaArbol.setText(String.valueOf(altura));
        lblSiguienteTurno.setText(siguiente != null ? String.valueOf(siguiente) : "Ninguno");
        
        // Estado del balance
        if (total == 0) {
            lblEstadoBalance.setText("Vacío");
            lblEstadoBalance.setForeground(new Color(113, 128, 150));
        } else {
            lblEstadoBalance.setText("Balanceado ✓");
            lblEstadoBalance.setForeground(new Color(56, 161, 105)); // Verde agradable
        }

        // Registrar estado en el historial
        if (inOrden.isEmpty()) {
            registrarMensaje("📋 Cola actual: (Vacía)");
        } else {
            registrarMensaje("📋 Cola actual (Ordenada): " + inOrden);
        }
        registrarMensaje("---------------------------------------------------");
    }

    /**
     * Añade un mensaje descriptivo a la bitácora técnica de logs.
     */
    private void registrarMensaje(String mensaje) {
        txtLog.append(mensaje + "\n");
        txtLog.setCaretPosition(txtLog.getDocument().getLength()); // Auto-scroll
    }

    /**
     * Recorre el árbol AVL en orden (Inorden) de forma recursiva para devolver una representación
     * textual de los valores separados por espacio.
     */
    private String recorrerInOrden(Node<Integer> n) {
        if (n == null) return "";
        String izq = recorrerInOrden(n.getLeft());
        String actual = n.getData() + " ";
        String der = recorrerInOrden(n.getRight());
        return izq + actual + der;
    }

    /**
     * Cuenta recursivamente el número total de nodos en el subárbol.
     */
    private int countNodes(Node<Integer> n) {
        if (n == null) return 0;
        return 1 + countNodes(n.getLeft()) + countNodes(n.getRight());
    }

    /**
     * Obtiene recursivamente el valor del turno mínimo (siguiente paciente a ser atendido).
     */
    private Integer obtenerMinTurno(Node<Integer> n) {
        if (n == null) return null;
        while (n.getLeft() != null) {
            n = n.getLeft();
        }
        return n.getData();
    }

    // --- MÉTODOS DE ESTILIZACIÓN DE LA GUI ---

    /**
     * Helper para crear botones planos elegantes y modernos con soporte a eventos hover.
     */
    private JButton crearBoton(String texto, Color bg, Color fg) {
        JButton btn = new JButton(texto) {
            @Override
            protected void paintComponent(Graphics g) {
                Graphics2D g2 = (Graphics2D) g.create();
                g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
                
                // Determinar el color exacto según el estado
                Color activeBg = bg;
                if (getModel().isPressed()) {
                    activeBg = bg.darker();
                } else if (getModel().isRollover()) {
                    activeBg = bg.brighter();
                }
                
                // Dibujar fondo redondeado
                g2.setColor(activeBg);
                g2.fillRoundRect(0, 0, getWidth() - 1, getHeight() - 1, 8, 8);
                
                // Dibujar contorno sutil
                g2.setColor(activeBg.darker());
                g2.drawRoundRect(0, 0, getWidth() - 1, getHeight() - 1, 8, 8);
                
                g2.dispose();
                super.paintComponent(g);
            }
        };
        btn.setFont(new Font("Segoe UI", Font.BOLD, 12));
        btn.setForeground(fg);
        btn.setContentAreaFilled(false);
        btn.setBorderPainted(false);
        btn.setFocusPainted(false);
        btn.setOpaque(false);
        btn.setCursor(new Cursor(Cursor.HAND_CURSOR));
        btn.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
        
        return btn;
    }

    /**
     * Crea un panel con forma de tarjeta de indicador para las estadísticas (KPIs).
     */
    private JPanel crearTarjetaMetrica(String titulo, JLabel lblValor, Color colorBorde) {
        JPanel tarjeta = new JPanel(new BorderLayout(5, 5));
        tarjeta.setBackground(Color.WHITE);
        tarjeta.setBorder(BorderFactory.createCompoundBorder(
                BorderFactory.createLineBorder(new Color(237, 242, 247), 1, true),
                BorderFactory.createCompoundBorder(
                        BorderFactory.createMatteBorder(0, 4, 0, 0, colorBorde), // Barra vertical de acento
                        BorderFactory.createEmptyBorder(10, 12, 10, 12)
                )
        ));
        
        JLabel lblTitulo = new JLabel(titulo.toUpperCase());
        lblTitulo.setFont(new Font("Segoe UI", Font.BOLD, 10));
        lblTitulo.setForeground(new Color(160, 174, 192)); // Gris suave
        
        lblValor.setFont(new Font("Segoe UI", Font.BOLD, 18));
        lblValor.setForeground(new Color(45, 55, 72)); // Gris carbón
        
        tarjeta.add(lblTitulo, BorderLayout.NORTH);
        tarjeta.add(lblValor, BorderLayout.CENTER);
        
        return tarjeta;
    }

    // --- LIENZO PERSONALIZADO PARA DIBUJAR EL ÁRBOL AVL ---
    
    /**
     * Panel especializado que se encarga del renderizado 2D y anti-aliasing del árbol AVL.
     */
    private class AVLVisualizerPanel extends JPanel {
        
        public AVLVisualizerPanel() {
            setBackground(new Color(248, 250, 252)); // Color de fondo gris clínico claro
            setBorder(BorderFactory.createLineBorder(new Color(226, 232, 240), 1));
        }

        @Override
        protected void paintComponent(Graphics g) {
            super.paintComponent(g);
            
            Graphics2D g2 = (Graphics2D) g;
            // Garantizar la limpieza completa del fondo ante repintados del sistema
            g2.setColor(getBackground());
            g2.fillRect(0, 0, getWidth(), getHeight());
            
            // Configurar Anti-aliasing de gráficos y textos para máxima definición visual (Wow factor)
            g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
            g2.setRenderingHint(RenderingHints.KEY_TEXT_ANTIALIASING, RenderingHints.VALUE_TEXT_ANTIALIAS_ON);
            
            Node<Integer> root = turnos.getRoot();
            
            if (root == null) {
                // Mensaje en caso de árbol vacío
                g2.setColor(new Color(160, 174, 192));
                g2.setFont(new Font("Segoe UI", Font.BOLD, 16));
                String vacioMsg = "COLA VACÍA - NO HAY PACIENTES REGISTRADOS";
                FontMetrics fm = g2.getFontMetrics();
                int x = (getWidth() - fm.stringWidth(vacioMsg)) / 2;
                int y = getHeight() / 2;
                g2.drawString(vacioMsg, x, y);
            } else {
                // Iniciar dibujo recursivo centrado en la raíz
                int rootX = getWidth() / 2;
                int rootY = 50;
                // Offset inicial proporcional al tamaño del lienzo
                int xOffset = Math.max(getWidth() / 4, 100);
                drawTree(g2, root, rootX, rootY, xOffset);
            }
        }

        /**
         * Dibuja recursivamente el árbol AVL en el lienzo.
         * Dibuja primero las ramas (conectores) y luego los nodos (círculos) para mantener la jerarquía de capas.
         */
        private void drawTree(Graphics2D g2, Node<Integer> node, int x, int y, int xOffset) {
            if (node == null) return;
            
            int childY = y + 70; // Altura de nivel fija (70px)
            int nodeSize = 42;
            
            // 1. Dibujar ramas conectores hacia los hijos primero (capa inferior)
            g2.setStroke(new BasicStroke(2.0f));
            g2.setColor(new Color(160, 174, 192)); // Gris conector
            
            if (node.getLeft() != null) {
                int leftChildX = x - xOffset;
                g2.drawLine(x, y, leftChildX, childY);
                drawTree(g2, node.getLeft(), leftChildX, childY, xOffset / 2);
            }
            
            if (node.getRight() != null) {
                int rightChildX = x + xOffset;
                g2.drawLine(x, y, rightChildX, childY);
                drawTree(g2, node.getRight(), rightChildX, childY, xOffset / 2);
            }
            
            // 2. Dibujar el nodo actual (capa superior)
            boolean isHighlighted = (searchedTurn != null && searchedTurn.equals(node.getData()));
            
            // Degradado del nodo
            GradientPaint gradient;
            if (isHighlighted) {
                // Color dorado/amarillo brillante para nodo buscado
                gradient = new GradientPaint(
                        x - nodeSize/2, y - nodeSize/2, new Color(251, 191, 36),
                        x + nodeSize/2, y + nodeSize/2, new Color(217, 119, 6)
                );
            } else {
                // Color azul/teal elegante para nodos estándar
                gradient = new GradientPaint(
                        x - nodeSize/2, y - nodeSize/2, new Color(66, 153, 225),
                        x + nodeSize/2, y + nodeSize/2, new Color(43, 108, 176)
                );
            }
            
            g2.setPaint(gradient);
            g2.fillOval(x - nodeSize / 2, y - nodeSize / 2, nodeSize, nodeSize);
            
            // Borde del nodo
            if (isHighlighted) {
                g2.setColor(new Color(237, 137, 54)); // Borde naranja grueso
                g2.setStroke(new BasicStroke(3.0f));
            } else {
                g2.setColor(Color.WHITE); // Borde blanco
                g2.setStroke(new BasicStroke(2.0f));
            }
            g2.drawOval(x - nodeSize / 2, y - nodeSize / 2, nodeSize, nodeSize);
            
            // Texto dentro del nodo (número de turno)
            g2.setColor(Color.WHITE);
            g2.setFont(new Font("Segoe UI", Font.BOLD, 14));
            String strVal = String.valueOf(node.getData());
            FontMetrics fm = g2.getFontMetrics();
            int textX = x - fm.stringWidth(strVal) / 2;
            int textY = y + (fm.getAscent() - fm.getDescent()) / 2;
            g2.drawString(strVal, textX, textY);
            
            // 3. Dibujar etiqueta del Factor de Equilibrio (bf)
            g2.setFont(new Font("Segoe UI", Font.BOLD, 9));
            String strBf = "bf=" + node.get_bf();
            FontMetrics fmBf = g2.getFontMetrics();
            
            int bfW = fmBf.stringWidth(strBf);
            int bfH = fmBf.getHeight();
            int bfX = x - bfW / 2;
            int bfY = y - nodeSize / 2 - 6;
            
            // Fondo para la etiqueta bf (pequeño bocadillo redondeado)
            g2.setColor(new Color(255, 255, 255, 230));
            g2.fillRoundRect(bfX - 4, bfY - bfH + 2, bfW + 8, bfH + 2, 6, 6);
            
            // Contorno de la etiqueta bf
            g2.setColor(new Color(203, 213, 223));
            g2.setStroke(new BasicStroke(1.0f));
            g2.drawRoundRect(bfX - 4, bfY - bfH + 2, bfW + 8, bfH + 2, 6, 6);
            
            // Color del texto según el factor de equilibrio (Verde si es 0, Naranja si está cargado)
            if (node.get_bf() == 0) {
                g2.setColor(new Color(56, 161, 105)); // Verde
            } else {
                g2.setColor(new Color(221, 107, 32)); // Naranja
            }
            g2.drawString(strBf, bfX, bfY);
        }
    }

    // --- PUNTO DE ENTRADA PRINCIPAL ---
    public static void main(String[] args) {
        // Ejecutar con Look and Feel del sistema operativo para una integración perfecta
        try {
            UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
        } catch (Exception ignored) {
        }
        
        // Ejecutar en el Event Dispatch Thread (Buenas prácticas de robustez en Swing)
        SwingUtilities.invokeLater(() -> {
            ClinicaAVLGUI ventana = new ClinicaAVLGUI();
            ventana.setVisible(true);
        });
    }
}