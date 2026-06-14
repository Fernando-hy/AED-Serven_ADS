package aplicacion_visual_almacen;

import paquete.BTree;
import javax.swing.*;
import java.awt.*;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.io.FileWriter;
import java.util.List;

public class Minimarket extends JFrame {
    private BTree<Producto> inventario;
    private JTextArea txtLog; // Área de texto para mostrar las operaciones en vivo
    private JPanel panelAnaquel; // Panel donde dibujaremos los productos

    // Paleta de colores (Blanco a Azul)
    private Color colorFondo = new Color(234, 242, 248); // Azul muy claro
    private Color colorPrimario = new Color(41, 128, 185); // Azul estándar
    private Color colorOscuro = new Color(21, 67, 96); // Azul oscuro naval
    private Color colorBlanco = Color.WHITE;

    public Minimarket(int orden) {
        this.inventario = new BTree<>(orden);
        configurarVentana();
        cargarDatosPrueba();
    }

    private void configurarVentana() {
        setTitle("🛒 Sistema Minimarket (B-Tree)");
        setSize(900, 600);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null); // Centrar en la pantalla
        setLayout(new BorderLayout(10, 10));
        getContentPane().setBackground(colorFondo);

        // Título superior
        JLabel lblTitulo = new JLabel("Panel de Control del Cajero", SwingConstants.CENTER);
        lblTitulo.setFont(new Font("Arial", Font.BOLD, 24));
        lblTitulo.setOpaque(true);
        lblTitulo.setBackground(colorOscuro);
        lblTitulo.setForeground(colorBlanco);
        lblTitulo.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
        add(lblTitulo, BorderLayout.NORTH);

        // Pestañas Centrales (Log vs Anaquel)
        JTabbedPane panelPestañas = new JTabbedPane();
        panelPestañas.setFont(new Font("Arial", Font.BOLD, 14));

        // Pestaña 1: Registro
        txtLog = new JTextArea();
        txtLog.setEditable(false);
        txtLog.setFont(new Font("Consolas", Font.PLAIN, 14)); // Letra monoespaciada ideal para tablas
        JScrollPane scrollPane = new JScrollPane(txtLog);
        panelPestañas.addTab("📋 Registro / Caja", scrollPane);

        // Pestaña 2: Anaquel Visual
        panelAnaquel = new JPanel();
        panelAnaquel.setLayout(new GridLayout(0, 3, 15, 15)); // 3 columnas, espacio entre ellas
        panelAnaquel.setBackground(colorFondo);
        panelAnaquel.setBorder(BorderFactory.createEmptyBorder(15, 15, 15, 15));
        JScrollPane scrollAnaquel = new JScrollPane(panelAnaquel);
        panelPestañas.addTab("🏪 Anaquel de Productos", scrollAnaquel);

        add(panelPestañas, BorderLayout.CENTER);

        // Panel lateral derecho para los botones del menú
        JPanel panelBotones = new JPanel();
        panelBotones.setLayout(new GridLayout(6, 1, 10, 10));
        panelBotones.setBackground(colorFondo);
        panelBotones.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));

        JButton btnVender = new JButton("1. Vender Producto");
        JButton btnReabastecer = new JButton("2. Reabastecer Stock");
        JButton btnRegistrar = new JButton("3. Registrar Nuevo");
        JButton btnMostrar = new JButton("4. Mostrar Árbol B");
        JButton btnEliminar = new JButton("5. Eliminar Producto");
        JButton btnSalir = new JButton("6. Salir");

        // --- ASIGNACIÓN DE ACCIONES A LOS BOTONES ---
        btnVender.addActionListener(e -> accionVender());
        btnReabastecer.addActionListener(e -> accionReabastecer());
        btnRegistrar.addActionListener(e -> accionRegistrar());
        btnMostrar.addActionListener(e -> mostrarEstructura());
        btnEliminar.addActionListener(e -> accionEliminar());
        btnSalir.addActionListener(e -> System.exit(0));

        // Estilizar botones
        for (JButton btn : new JButton[] { btnVender, btnReabastecer, btnRegistrar, btnMostrar, btnEliminar,
                btnSalir }) {
            btn.setBackground(colorPrimario);
            btn.setForeground(colorBlanco);
            btn.setFont(new Font("Arial", Font.BOLD, 14));
            btn.setFocusPainted(false);
            btn.setOpaque(true); // Fuerza a que se pinte el fondo (Solución para Mac/Windows LAF)
            btn.setBorderPainted(false); // Le da un diseño más plano y moderno
            panelBotones.add(btn);
        }

        add(panelBotones, BorderLayout.EAST);
    }

    // --- LÓGICA DE NEGOCIO Y UTILIDADES UI ---

    private void refrescarAnaquel() {
        panelAnaquel.removeAll(); // Limpiar el anaquel actual
        List<Producto> productos = inventario.getAll(); // Extraer del Árbol B

        for (Producto p : productos) {
            JPanel card = new JPanel();
            card.setLayout(new BoxLayout(card, BoxLayout.Y_AXIS));
            card.setBackground(colorBlanco);
            card.setBorder(BorderFactory.createCompoundBorder(
                    BorderFactory.createLineBorder(colorPrimario, 2, true),
                    BorderFactory.createEmptyBorder(10, 10, 10, 10)));

            JLabel lblNombre = new JLabel(p.getNombre());
            lblNombre.setFont(new Font("Arial", Font.BOLD, 14));
            lblNombre.setAlignmentX(Component.CENTER_ALIGNMENT);
            lblNombre.setForeground(colorOscuro);

            JLabel lblIcono = new JLabel(p.getIcono());
            lblIcono.setFont(new Font("Segoe UI Emoji", Font.PLAIN, 40)); // Fuente especial para logos/emojis
            lblIcono.setAlignmentX(Component.CENTER_ALIGNMENT);

            JLabel lblStock = new JLabel(String.valueOf(p.getStock()));
            lblStock.setFont(new Font("Arial", Font.BOLD, 48)); // Tamaño gigante en medio
            lblStock.setForeground(colorPrimario);
            lblStock.setAlignmentX(Component.CENTER_ALIGNMENT);

            JLabel lblPrecio = new JLabel(String.format("Precio: $%.2f", p.getPrecio()));
            lblPrecio.setFont(new Font("Arial", Font.PLAIN, 12));
            lblPrecio.setAlignmentX(Component.CENTER_ALIGNMENT);

            JLabel lblCod = new JLabel("Cod: " + p.getCodigoBarras());
            lblCod.setFont(new Font("Consolas", Font.ITALIC, 11));
            lblCod.setAlignmentX(Component.CENTER_ALIGNMENT);
            lblCod.setForeground(Color.GRAY);

            card.add(lblNombre);
            card.add(Box.createVerticalStrut(10));
            card.add(lblIcono);
            card.add(Box.createVerticalStrut(5));
            card.add(lblStock);
            card.add(new JLabel("unidades disponibles", SwingConstants.CENTER) {
                {
                    setAlignmentX(Component.CENTER_ALIGNMENT);
                }
            });
            card.add(Box.createVerticalStrut(10));
            card.add(lblPrecio);
            card.add(lblCod);

            panelAnaquel.add(card);
        }
        panelAnaquel.revalidate(); // Avisar a Java que repinte la vista
        panelAnaquel.repaint();
    }

    private void log(String mensaje) {
        txtLog.append(mensaje + "\n");
        txtLog.setCaretPosition(txtLog.getDocument().getLength()); // Auto-scroll hacia abajo
    }

    private void cargarDatosPrueba() {
        String rutaArchivo = "productos.txt";
        try (BufferedReader br = new BufferedReader(new FileReader(rutaArchivo))) {
            String linea;
            while ((linea = br.readLine()) != null) {
                String[] datos = linea.split(",");
                if (datos.length == 6) {
                    Producto p = new Producto(datos[0], datos[1], Double.parseDouble(datos[2]),
                            Integer.parseInt(datos[3]), Integer.parseInt(datos[4]), datos[5]);
                    inventario.insert(p);
                }
            }
            log("✅ Base de datos cargada desde 'productos.txt'. (" + inventario.getAll().size() + " productos)");
        } catch (IOException e) {
            log("⚠️ No se encontró 'productos.txt'. Iniciando con inventario vacío.");
        }
        refrescarAnaquel();
    }

    // Guarda todo el Árbol B directamente al archivo .txt
    private void guardarDatos() {
        String rutaArchivo = "productos.txt";
        try (PrintWriter pw = new PrintWriter(new FileWriter(rutaArchivo))) {
            List<Producto> productos = inventario.getAll();
            for (Producto p : productos) {
                pw.println(p.getCodigoBarras() + "," + p.getNombre() + "," + p.getPrecio() + ","
                        + p.getStock() + "," + p.getPasillo() + "," + p.getIcono());
            }
        } catch (IOException e) {
            log("❌ Error al guardar en 'productos.txt': " + e.getMessage());
        }
    }

    public void registrarNuevoProducto(Producto producto) {
        inventario.insert(producto);
        log("✅ Guardado en catálogo: " + producto.getNombre());
        refrescarAnaquel();
        guardarDatos(); // Guardar en el .txt al instante
    }

    private void accionVender() {
        String codigo = JOptionPane.showInputDialog(this, "Escanear Código de Barras:", "Caja Registradora",
                JOptionPane.QUESTION_MESSAGE);
        if (codigo == null || codigo.trim().isEmpty())
            return;

        Producto productoEncontrado = inventario.get(Producto.soloParaBusqueda(codigo));
        if (productoEncontrado == null) {
            JOptionPane.showMessageDialog(this, "Producto no encontrado en el sistema.", "Error",
                    JOptionPane.ERROR_MESSAGE);
            return;
        }

        String cantStr = JOptionPane.showInputDialog(this,
                "¿Cantidad a vender de " + productoEncontrado.getNombre() + "?", "Venta", JOptionPane.QUESTION_MESSAGE);
        if (cantStr == null || cantStr.trim().isEmpty())
            return;

        try {
            int cantidad = Integer.parseInt(cantStr);
            if (productoEncontrado.getStock() >= cantidad) {
                productoEncontrado.setStock(productoEncontrado.getStock() - cantidad);
                log("🛒 TICKET: " + cantidad + "x " + productoEncontrado.getNombre() +
                        " | Cobrar: $" + String.format("%.2f", (productoEncontrado.getPrecio() * cantidad)) +
                        " (Quedan " + productoEncontrado.getStock() + ")");
                refrescarAnaquel();
                guardarDatos(); // Guardar cambios tras la venta
            } else {
                JOptionPane.showMessageDialog(this,
                        "Stock insuficiente. Solo hay " + productoEncontrado.getStock() + " disponibles.", "Alerta",
                        JOptionPane.WARNING_MESSAGE);
            }
        } catch (NumberFormatException ex) {
            JOptionPane.showMessageDialog(this, "Por favor ingrese un número válido.", "Error",
                    JOptionPane.ERROR_MESSAGE);
        }
    }

    private void accionReabastecer() {
        String codigo = JOptionPane.showInputDialog(this, "Escanear Código de Barras:", "Reabastecimiento",
                JOptionPane.QUESTION_MESSAGE);
        if (codigo == null || codigo.trim().isEmpty())
            return;

        Producto productoEncontrado = inventario.get(Producto.soloParaBusqueda(codigo));
        if (productoEncontrado == null) {
            JOptionPane.showMessageDialog(this, "El producto no está registrado. Seleccione 'Registrar Nuevo'.",
                    "Error", JOptionPane.ERROR_MESSAGE);
            return;
        }

        String cantStr = JOptionPane.showInputDialog(this, "¿Cuántas unidades entran al almacén?", "Ingreso de Stock",
                JOptionPane.QUESTION_MESSAGE);
        if (cantStr == null || cantStr.trim().isEmpty())
            return;

        try {
            int cantidad = Integer.parseInt(cantStr);
            productoEncontrado.setStock(productoEncontrado.getStock() + cantidad);
            log("📦 CAMIÓN: Llegaron " + cantidad + "x " + productoEncontrado.getNombre() + " | Stock actualizado: "
                    + productoEncontrado.getStock());
            refrescarAnaquel();
            guardarDatos(); // Guardar cambios tras reabastecer
        } catch (NumberFormatException ex) {
            JOptionPane.showMessageDialog(this, "Por favor ingrese un número válido.", "Error",
                    JOptionPane.ERROR_MESSAGE);
        }
    }

    private void accionRegistrar() {
        // Formulario a medida para capturar múltiples datos a la vez
        JTextField txtCodigo = new JTextField();
        JTextField txtNombre = new JTextField();
        JTextField txtPrecio = new JTextField();
        JTextField txtStock = new JTextField();
        JTextField txtPasillo = new JTextField();
        JTextField txtIcono = new JTextField();

        Object[] formulario = {
                "Código de Barras:", txtCodigo,
                "Nombre del Producto:", txtNombre,
                "Precio Unitario ($):", txtPrecio,
                "Stock Inicial:", txtStock,
                "Número de Pasillo:", txtPasillo,
                "Logo/Emoji (Ej. 🍬):", txtIcono
        };

        int opcion = JOptionPane.showConfirmDialog(this, formulario, "Alta de Nuevo Producto",
                JOptionPane.OK_CANCEL_OPTION);
        if (opcion == JOptionPane.OK_OPTION) {
            try {
                String cod = txtCodigo.getText().trim();
                String nom = txtNombre.getText().trim();
                double pre = Double.parseDouble(txtPrecio.getText().trim());
                int stk = Integer.parseInt(txtStock.getText().trim());
                int pas = Integer.parseInt(txtPasillo.getText().trim());
                String ico = txtIcono.getText().trim();

                registrarNuevoProducto(new Producto(cod, nom, pre, stk, pas, ico));
                JOptionPane.showMessageDialog(this, "Producto registrado correctamente en el sistema.", "Éxito",
                        JOptionPane.INFORMATION_MESSAGE);
            } catch (Exception ex) {
                log("❌ Error al registrar: Verifique que Precio, Stock y Pasillo sean números sin letras.");
                JOptionPane.showMessageDialog(this,
                        "Error en los datos. Revise que el precio y stock sean números válidos.", "Error de Formato",
                        JOptionPane.ERROR_MESSAGE);
            }
        }
    }

    private void accionEliminar() {
        String codigo = JOptionPane.showInputDialog(this, "Escanear Código de Barras a eliminar:", "Eliminar Producto",
                JOptionPane.WARNING_MESSAGE);
        if (codigo == null || codigo.trim().isEmpty())
            return;

        Producto productoEncontrado = inventario.get(Producto.soloParaBusqueda(codigo));
        if (productoEncontrado == null) {
            JOptionPane.showMessageDialog(this, "Producto no encontrado.", "Error", JOptionPane.ERROR_MESSAGE);
            return;
        }

        int confirm = JOptionPane.showConfirmDialog(this,
                "¿Seguro que desea eliminar '" + productoEncontrado.getNombre() + "' de forma permanente?",
                "Confirmar Eliminación", JOptionPane.YES_NO_OPTION);
        if (confirm == JOptionPane.YES_OPTION) {
            inventario.remove(productoEncontrado);
            log("🗑️ Producto eliminado: " + productoEncontrado.getNombre());
            refrescarAnaquel();
            guardarDatos(); // Borrar definitivamente del .txt
        }
    }

    public void mostrarEstructura() {
        log("=========================================================================");
        log("ESTRUCTURA INTERNA DEL ÁRBOL B:");
        log(inventario.toString());
        log("=========================================================================\n");
    }

    public static void main(String[] args) {
        // Cambiamos el "Look and Feel" (la apariencia visual) para que se vea nativo
        // como Windows/Mac
        try {
            UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
        } catch (Exception e) {
            e.printStackTrace();
        }

        // En Swing, lanzamos la aplicación visual en su propio hilo (Event Dispatch
        // Thread) para mayor fluidez y seguridad
        SwingUtilities.invokeLater(() -> {
            Minimarket app = new Minimarket(4); // Inicializamos el gerente con el Árbol B de orden 4
            app.setVisible(true); // ¡Mostramos la ventana!
        });
    }
}