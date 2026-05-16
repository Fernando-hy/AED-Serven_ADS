package bstreelinklistinterfgeneric;

import javax.swing.*;
import java.awt.*;
import exceptions.*;

public class MainFrame extends JFrame {
    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private LinkedBST<Integer> bst;
    private TreePanel<Integer> treePanel;
    private JTextField inputField;
    private JTextField minField;
    private JTextField maxField;
    private JLabel statusLabel;

    public MainFrame() {
        bst = new LinkedBST<>();
        setTitle("Binary Search Tree - UCSM");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(1000, 700);
        setLocationRelativeTo(null);
        initComponents();
    }

    private void initComponents() {
        JPanel topPanel = new JPanel(new GridLayout(4, 1, 5, 5));
        topPanel.setBorder(BorderFactory.createEmptyBorder(5, 5, 5, 5));

        // fila 1: input + insertar + eliminar
        JPanel fila1 = new JPanel(new FlowLayout(FlowLayout.LEFT));
        inputField = new JTextField(8);
        JButton btnInsert = new JButton("Insertar");
        JButton btnDelete = new JButton("Eliminar");
        JButton btnSearch = new JButton("Buscar");
        fila1.add(new JLabel("Valor:"));
        fila1.add(inputField);
        fila1.add(btnInsert);
        fila1.add(btnDelete);
        fila1.add(btnSearch);

        // fila 2: min, max, isValidBST
        JPanel fila2 = new JPanel(new FlowLayout(FlowLayout.LEFT));
        JButton btnMin    = new JButton("Min");
        JButton btnMax    = new JButton("Max");
        JButton btnValid  = new JButton("¿Es BST válido?");
        JButton btnClear  = new JButton("Limpiar");
        fila2.add(btnMin);
        fila2.add(btnMax);
        fila2.add(btnValid);
        fila2.add(btnClear);

        // fila 3: recorridos + parenthesize + drawBST
        JPanel fila3 = new JPanel(new FlowLayout(FlowLayout.LEFT));
        JButton btnInOrder    = new JButton("InOrden");
        JButton btnPreOrder   = new JButton("PreOrden");
        JButton btnPostOrder  = new JButton("PostOrden");
        JButton btnParenthes  = new JButton("Parenthesize");
        JButton btnDraw       = new JButton("DrawBST");
        fila3.add(btnInOrder);
        fila3.add(btnPreOrder);
        fila3.add(btnPostOrder);
        fila3.add(btnParenthes);
        fila3.add(btnDraw);

        // fila 4: searchRange
        JPanel fila4 = new JPanel(new FlowLayout(FlowLayout.LEFT));
        minField = new JTextField(5);
        maxField = new JTextField(5);
        JButton btnRange = new JButton("SearchRange");
        fila4.add(new JLabel("Min:"));
        fila4.add(minField);
        fila4.add(new JLabel("Max:"));
        fila4.add(maxField);
        fila4.add(btnRange);

        topPanel.add(fila1);
        topPanel.add(fila2);
        topPanel.add(fila3);
        topPanel.add(fila4);

        // status
        statusLabel = new JLabel("Listo", SwingConstants.CENTER);
        statusLabel.setFont(new Font("Arial", Font.ITALIC, 12));
        statusLabel.setBorder(BorderFactory.createEmptyBorder(2, 5, 2, 5));

        // panel árbol
        treePanel = new TreePanel<>(bst);
        treePanel.setBorder(BorderFactory.createTitledBorder("BST"));

        setLayout(new BorderLayout());
        add(topPanel, BorderLayout.NORTH);
        add(statusLabel, BorderLayout.CENTER);
        add(treePanel, BorderLayout.CENTER);

        // acciones
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
        btnParenthes.addActionListener(e -> handleParenthesize());
        btnDraw.addActionListener(e -> handleDraw());
        btnRange.addActionListener(e -> handleRange());

        inputField.addActionListener(e -> handleInsert());
    }

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
            setStatus("Ingresa un número", false);
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
            setStatus("Ingresa un número", false);
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
            setStatus("Ingresa un número", false);
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
        // usamos StringBuilder para capturar el print
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

    private void handleParenthesize() {
        StringBuilder sb = new StringBuilder();
        parenthesizeToString(bst.getRoot(), 0, sb);
        JOptionPane.showMessageDialog(this,
            sb.toString(),
            "Parenthesize", JOptionPane.INFORMATION_MESSAGE);
    }

    private void parenthesizeToString(Node<Integer> n, int nivel, StringBuilder sb) {
        if (n == null) return;
        String sangria = "    ".repeat(nivel);
        if (n.getLeft() == null && n.getRight() == null) {
            sb.append(sangria).append(n.getData()).append("\n");
        } else {
            sb.append(sangria).append(n.getData()).append(" (\n");
            parenthesizeToString(n.getLeft(), nivel + 1, sb);
            parenthesizeToString(n.getRight(), nivel + 1, sb);
            sb.append(sangria).append(")\n");
        }
    }

    private void handleDraw() {
        JOptionPane.showMessageDialog(this,
            bst.toString(),
            "DrawBST", JOptionPane.INFORMATION_MESSAGE);
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
        statusLabel.setForeground(ok ? new Color(0, 128, 0) : Color.RED);
        statusLabel.setText(msg);
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new MainFrame().setVisible(true));
    }
}