package bstreelinklistinterfgeneric;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import exceptions.*;

public class MainFrame2 extends JFrame {
    private static final long serialVersionUID = 1L;
    private LinkedBST<Integer> bst;
    private TreePanel2<Integer> treePanel;
    private JTextField inputField;
    private JTextField minField;
    private JTextField maxField;
    private JLabel statusLabel;

    // Colores del tema oscuro (Paleta Premium)
    private final Color BG_DARK = new Color(24, 24, 24);
    private final Color BG_SIDEBAR = new Color(36, 36, 36);
    private final Color FG_LIGHT = new Color(220, 220, 220);
    private final Color ACCENT_BLUE = new Color(0, 150, 255);
    private final Color ACCENT_PURPLE = new Color(138, 43, 226);
    private final Color BG_CARD = new Color(45, 45, 45);

    public MainFrame2() {
        bst = new LinkedBST<>();
        setTitle("Binary Search Tree - Premium Edition");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(1200, 800);
        setLocationRelativeTo(null);
        initComponents();
    }

    private void initComponents() {
        setLayout(new BorderLayout());
        getContentPane().setBackground(BG_DARK);

        // --- PANEL LATERAL (SIDEBAR) ---
        JPanel sidebar = new JPanel();
        sidebar.setLayout(new BoxLayout(sidebar, BoxLayout.Y_AXIS));
        sidebar.setBackground(BG_SIDEBAR);
        sidebar.setBorder(new EmptyBorder(20, 15, 20, 15));
        sidebar.setPreferredSize(new Dimension(300, 800));

        // Título
        JLabel titleLabel = new JLabel("BST CONTROL");
        titleLabel.setFont(new Font("Segoe UI", Font.BOLD, 22));
        titleLabel.setForeground(ACCENT_BLUE);
        titleLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
        sidebar.add(titleLabel);
        sidebar.add(Box.createRigidArea(new Dimension(0, 25)));

        // Sección: Operaciones Básicas
        sidebar.add(createSectionLabel("OPERACIONES"));
        JPanel basicOps = new JPanel(new GridLayout(3, 1, 5, 5));
        basicOps.setBackground(BG_SIDEBAR);
        
        JPanel inputPanel = new JPanel(new BorderLayout(5, 5));
        inputPanel.setBackground(BG_SIDEBAR);
        JLabel valLabel = new JLabel("Valor:");
        valLabel.setForeground(FG_LIGHT);
        inputField = createStyledTextField();
        inputPanel.add(valLabel, BorderLayout.WEST);
        inputPanel.add(inputField, BorderLayout.CENTER);
        
        JButton btnInsert = createStyledButton("Insertar", ACCENT_BLUE);
        JButton btnDelete = createStyledButton("Eliminar", ACCENT_PURPLE);
        
        basicOps.add(inputPanel);
        basicOps.add(btnInsert);
        basicOps.add(btnDelete);
        sidebar.add(basicOps);
        sidebar.add(Box.createRigidArea(new Dimension(0, 20)));

        // Sección: Consultas
        sidebar.add(createSectionLabel("CONSULTAS"));
        JPanel queryOps = new JPanel(new GridLayout(2, 2, 5, 5));
        queryOps.setBackground(BG_SIDEBAR);
        JButton btnSearch = createStyledButton("Buscar", BG_CARD);
        JButton btnMin = createStyledButton("Mínimo", BG_CARD);
        JButton btnMax = createStyledButton("Máximo", BG_CARD);
        JButton btnValid = createStyledButton("¿Válido?", BG_CARD);
        queryOps.add(btnSearch);
        queryOps.add(btnMin);
        queryOps.add(btnMax);
        queryOps.add(btnValid);
        sidebar.add(queryOps);
        sidebar.add(Box.createRigidArea(new Dimension(0, 20)));

        // Sección: Recorridos
        sidebar.add(createSectionLabel("RECORRIDOS"));
        JPanel travelOps = new JPanel(new GridLayout(3, 1, 5, 5));
        travelOps.setBackground(BG_SIDEBAR);
        JButton btnInOrder = createStyledButton("In-Orden", BG_CARD);
        JButton btnPreOrder = createStyledButton("Pre-Orden", BG_CARD);
        JButton btnPostOrder = createStyledButton("Post-Orden", BG_CARD);
        travelOps.add(btnInOrder);
        travelOps.add(btnPreOrder);
        travelOps.add(btnPostOrder);
        sidebar.add(travelOps);
        sidebar.add(Box.createRigidArea(new Dimension(0, 20)));

        // Sección: Avanzado
        sidebar.add(createSectionLabel("AVANZADO"));
        JPanel advOps = new JPanel(new GridLayout(2, 1, 5, 5));
        advOps.setBackground(BG_SIDEBAR);
        
        JPanel rangePanel = new JPanel(new FlowLayout(FlowLayout.LEFT, 2, 2));
        rangePanel.setBackground(BG_SIDEBAR);
        minField = createStyledTextField(4);
        maxField = createStyledTextField(4);
        JLabel rLabel1 = new JLabel("Min:"); rLabel1.setForeground(FG_LIGHT);
        JLabel rLabel2 = new JLabel("Max:"); rLabel2.setForeground(FG_LIGHT);
        rangePanel.add(rLabel1);
        rangePanel.add(minField);
        rangePanel.add(rLabel2);
        rangePanel.add(maxField);
        
        JButton btnRange = createStyledButton("Buscar Rango", BG_CARD);
        advOps.add(rangePanel);
        advOps.add(btnRange);
        sidebar.add(advOps);
        
        sidebar.add(Box.createVerticalGlue());
        
        // Botón Limpiar al final
        JButton btnClear = createStyledButton("Limpiar Árbol", new Color(180, 40, 40));
        sidebar.add(btnClear);

        add(sidebar, BorderLayout.WEST);

        // --- ÁREA CENTRAL (CANVAS) ---
        JPanel centerPanel = new JPanel(new BorderLayout());
        centerPanel.setBackground(BG_DARK);
        
        treePanel = new TreePanel2<>(bst);
        treePanel.setBackground(BG_DARK);
        centerPanel.add(treePanel, BorderLayout.CENTER);

        // Barra de estado
        statusLabel = new JLabel("Sistema Listo", SwingConstants.LEFT);
        statusLabel.setForeground(FG_LIGHT);
        statusLabel.setBorder(new EmptyBorder(5, 10, 5, 10));
        statusLabel.setFont(new Font("Segoe UI", Font.ITALIC, 13));
        centerPanel.add(statusLabel, BorderLayout.SOUTH);

        add(centerPanel, BorderLayout.CENTER);

        // --- ACCIONES ---
        btnInsert.addActionListener(e -> handleInsert());
        btnDelete.addActionListener(e -> handleDelete());
        btnSearch.addActionListener(e -> handleSearch());
        btnMin.addActionListener(e -> handleMin());
        btnMax.addActionListener(e -> handleMax());
        btnValid.addActionListener(e -> handleValid());
        btnClear.addActionListener(e -> handleClear());
        btnInOrder.addActionListener(e -> handleInOrder());
        btnPreOrder.addActionListener(e -> handlePreOrder());
        btnPostOrder.addActionListener(e -> handlePostOrder());
        btnRange.addActionListener(e -> handleRange());

        inputField.addActionListener(e -> handleInsert());
    }

    // Métodos auxiliares para estilo
    private JLabel createSectionLabel(String text) {
        JLabel label = new JLabel(text);
        label.setFont(new Font("Segoe UI", Font.BOLD, 12));
        label.setForeground(new Color(100, 100, 100));
        label.setBorder(new EmptyBorder(10, 0, 5, 0));
        label.setAlignmentX(Component.CENTER_ALIGNMENT);
        return label;
    }

    private JButton createStyledButton(String text, Color bg) {
        JButton btn = new JButton(text);
        btn.setBackground(bg);
        btn.setForeground(FG_LIGHT);
        btn.setFocusPainted(false);
        btn.setBorder(BorderFactory.createLineBorder(new Color(60, 60, 60), 1));
        btn.setFont(new Font("Segoe UI", Font.BOLD, 12));
        btn.setCursor(new Cursor(Cursor.HAND_CURSOR));
        
        // Efecto hover simple
        btn.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                btn.setBackground(bg.brighter());
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                btn.setBackground(bg);
            }
        });
        return btn;
    }

    private JTextField createStyledTextField() {
        return createStyledTextField(10);
    }

    private JTextField createStyledTextField(int columns) {
        JTextField tf = new JTextField(columns);
        tf.setBackground(BG_CARD);
        tf.setForeground(FG_LIGHT);
        tf.setCaretColor(Color.WHITE);
        tf.setBorder(BorderFactory.createLineBorder(new Color(60, 60, 60), 1));
        tf.setFont(new Font("Segoe UI", Font.PLAIN, 13));
        return tf;
    }

    // --- MANEJADORES DE EVENTOS ---
    private void handleInsert() {
        try {
            int val = Integer.parseInt(inputField.getText().trim());
            bst.insert(val);
            treePanel.refresh();
            setStatus("Insertado: " + val, true);
            inputField.setText("");
        } catch (ItemDuplicated e) {
            setStatus("Duplicado: " + e.getMessage(), false);
        } catch (NumberFormatException e) {
            setStatus("Ingresa un número válido", false);
        }
    }

    private void handleDelete() {
        try {
            int val = Integer.parseInt(inputField.getText().trim());
            bst.delete(val);
            treePanel.refresh();
            setStatus("Eliminado: " + val, true);
            inputField.setText("");
        } catch (ExceptionIsEmpty e) {
            setStatus("Árbol vacío!", false);
        } catch (ItemNotFound e) {
            setStatus("No encontrado!", false);
        } catch (NumberFormatException e) {
            setStatus("Ingresa un número válido", false);
        }
    }

    private void handleSearch() {
        try {
            int val = Integer.parseInt(inputField.getText().trim());
            boolean found = bst.search(val);
            setStatus(found ? "Encontrado: " + val : "No existe: " + val, found);
        } catch (ItemNotFound e) {
            setStatus("Árbol vacío!", false);
        } catch (NumberFormatException e) {
            setStatus("Ingresa un número válido", false);
        }
    }

    private void handleMin() {
        try {
            setStatus("Mínimo: " + bst.findMinNode(bst.getRoot()), true);
        } catch (ItemNotFound e) {
            setStatus("Árbol vacío!", false);
        }
    }

    private void handleMax() {
        try {
            setStatus("Máximo: " + bst.findMaxNode(bst.getRoot()), true);
        } catch (ItemNotFound e) {
            setStatus("Árbol vacío!", false);
        }
    }

    private void handleValid() {
        boolean valid = bst.isValidBST();
        setStatus("¿Es BST válido?: " + (valid ? "Sí ✓" : "No ✗"), valid);
    }

    private void handleClear() {
        try {
            bst.destroyNodes();
            treePanel.refresh();
            setStatus("Árbol limpiado", true);
        } catch (ExceptionIsEmpty e) {
            setStatus("Ya está vacío!", false);
        }
    }

    private void handleInOrder() {
        JOptionPane.showMessageDialog(this,
            bst.toString(),
            "InOrden", JOptionPane.INFORMATION_MESSAGE);
    }

    private void handlePreOrder() {
        StringBuilder sb = new StringBuilder();
        preOrderToString(bst.getRoot(), sb);
        JOptionPane.showMessageDialog(this,
            "PreOrden: " + sb.toString().trim(),
            "PreOrden", JOptionPane.INFORMATION_MESSAGE);
    }

    private void preOrderToString(Node<Integer> n, StringBuilder sb) {
        if (n == null) return;
        sb.append(n.getData()).append(" ");
        preOrderToString(n.getLeft(), sb);
        preOrderToString(n.getRight(), sb);
    }

    private void handlePostOrder() {
        StringBuilder sb = new StringBuilder();
        postOrderToString(bst.getRoot(), sb);
        JOptionPane.showMessageDialog(this,
            "PostOrden: " + sb.toString().trim(),
            "PostOrden", JOptionPane.INFORMATION_MESSAGE);
    }

    private void postOrderToString(Node<Integer> n, StringBuilder sb) {
        if (n == null) return;
        postOrderToString(n.getLeft(), sb);
        postOrderToString(n.getRight(), sb);
        sb.append(n.getData()).append(" ");
    }

    private void handleRange() {
        try {
            int min = Integer.parseInt(minField.getText().trim());
            int max = Integer.parseInt(maxField.getText().trim());
            StringBuilder sb = new StringBuilder();
            searchRangeToString(bst.getRoot(), min, max, sb);
            JOptionPane.showMessageDialog(this,
                "Rango [" + min + ", " + max + "]: " + sb.toString().trim(),
                "SearchRange", JOptionPane.INFORMATION_MESSAGE);
        } catch (NumberFormatException e) {
            setStatus("Ingresa min y max válidos", false);
        }
    }

    private void searchRangeToString(Node<Integer> n, int min, int max, StringBuilder sb) {
        if (n == null) return;
        if (n.getData() > min) searchRangeToString(n.getLeft(), min, max, sb);
        if (n.getData() >= min && n.getData() <= max) sb.append(n.getData()).append(" ");
        if (n.getData() < max) searchRangeToString(n.getRight(), min, max, sb);
    }

    private void setStatus(String msg, boolean ok) {
        statusLabel.setForeground(ok ? new Color(0, 200, 100) : new Color(220, 50, 50));
        statusLabel.setText(msg);
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new MainFrame2().setVisible(true));
    }
}