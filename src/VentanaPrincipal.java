package src;
import src.Classes.*;

import javax.swing.*;
import javax.swing.border.*;
import javax.swing.table.*;
import java.awt.*;
import java.awt.event.*;
import java.time.LocalDate;

public class VentanaPrincipal extends JFrame {

    // PALETA DE COLORES — tema oscuro con dorado
    private static final Color C_FONDO        = new Color(10,  10,  12);
    private static final Color C_SUPERFICIE   = new Color(20,  20,  24);
    private static final Color C_TARJETA      = new Color(28,  28,  34);
    private static final Color C_BORDE        = new Color(55,  55,  65);
    private static final Color C_DORADO       = new Color(212, 175, 95);
    private static final Color C_DORADO_CLARO = new Color(240, 210, 130);
    private static final Color C_TEXTO        = new Color(230, 225, 215);
    private static final Color C_TEXTO_DIM    = new Color(140, 135, 125);
    private static final Color C_PELIGRO      = new Color(180, 70,  70);

    private Agencia agencia;
    private JTabbedPane pestanas;

    // Modelos
    private JTextField txtNombreModelo, txtIdModelo, txtContactoModelo;
    private JTextField txtCodigoModelo, txtEstaturaModelo, txtCategoriaModelo;
    private JCheckBox  chkDisponible;
    private DefaultTableModel modeloTablaModelos;

    // Fotografos
    private JTextField txtNombreFoto, txtIdFoto, txtContactoFoto;
    private JTextField txtEspecialidadFoto, txtAnosFoto;
    private DefaultTableModel modeloTablaFotografos;

    // Lugares
    private JTextField txtNombreLugar, txtDireccionLugar, txtCiudadLugar;
    private JTextField txtCapacidadLugar, txtTipoLugar;
    private DefaultTableModel modeloTablaLugares;

    // Eventos
    private JTextField        txtNombreEvento, txtLugarEvento, txtExtra1, txtExtra2;
    private JLabel            lblExtra1, lblExtra2;
    private JComboBox<String> comboTipoEvento;
    private JSpinner          spDia, spMes, spAnio;
    private DefaultTableModel modeloTablaEventos;

    // Asignacion
    private JTextField        txtEventoAsignar, txtPersonaId;
    private JComboBox<String> comboTipoPersona;
    private JTextArea         areaAsignacion;

    // Busca el logo probando rutas posibles (funciona en Intellij y en .zip de GitHub)
    private ImageIcon cargarLogo(int ancho, int alto) {
        String[] rutas = {
                "src/recursos/logo.png",
                "Proyecto-Estructuras-De-Datos-main/src/recursos/logo.png"
        };
        for (String ruta : rutas) {
            java.io.File f = new java.io.File(ruta);
            if (f.exists()) {
                Image img = new ImageIcon(f.getAbsolutePath()).getImage()
                        .getScaledInstance(ancho, alto, Image.SCALE_SMOOTH);
                return new ImageIcon(img);
            }
        }
        return null;
    }

    //SPLASH SCREEN
    private void mostrarSplash() {
        JWindow splash = new JWindow();
        splash.setSize(500, 300);
        splash.setLocationRelativeTo(null);

        JPanel panel = new JPanel(new BorderLayout()) {
            @Override protected void paintComponent(Graphics g) {
                Graphics2D g2 = (Graphics2D) g;
                g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
                GradientPaint grad = new GradientPaint(0, 0, new Color(8, 8, 10), getWidth(), getHeight(), new Color(30, 25, 15));
                g2.setPaint(grad);
                g2.fillRect(0, 0, getWidth(), getHeight());
                g2.setColor(C_DORADO);
                g2.setStroke(new BasicStroke(2f));
                g2.drawRect(1, 1, getWidth()-3, getHeight()-3);
                g2.setStroke(new BasicStroke(1f));
                g2.setColor(new Color(212, 175, 95, 60));
                g2.drawLine(40, 22, getWidth()-40, 22);
                g2.drawLine(40, getHeight()-22, getWidth()-40, getHeight()-22);
            }
        };
        panel.setOpaque(false);

        JPanel contenido = new JPanel();
        contenido.setLayout(new BoxLayout(contenido, BoxLayout.Y_AXIS));
        contenido.setOpaque(false);
        contenido.setBorder(BorderFactory.createEmptyBorder(45, 40, 30, 40));

        // Intentamos cargar el logo
        JLabel lblEmoji;
        ImageIcon iconoSplash = cargarLogo(180, 130);
        if (iconoSplash != null) {
            lblEmoji = new JLabel(iconoSplash, SwingConstants.CENTER);
        } else {
            lblEmoji = new JLabel("", SwingConstants.CENTER);
            lblEmoji.setFont(new Font("Segoe UI Emoji", Font.PLAIN, 48));
        }
        lblEmoji.setAlignmentX(Component.CENTER_ALIGNMENT);

        JLabel lblTitulo = new JLabel("NO MÁS ENANOS", SwingConstants.CENTER);
        lblTitulo.setFont(new Font("Georgia", Font.BOLD, 26));
        lblTitulo.setForeground(C_DORADO);
        lblTitulo.setAlignmentX(Component.CENTER_ALIGNMENT);

        JLabel lblSubtitulo = new JLabel("POR FAVOR", SwingConstants.CENTER);
        lblSubtitulo.setFont(new Font("Georgia", Font.ITALIC, 16));
        lblSubtitulo.setForeground(C_DORADO_CLARO);
        lblSubtitulo.setAlignmentX(Component.CENTER_ALIGNMENT);

        JLabel lblSep = new JLabel("— Agencia de Modelaje —", SwingConstants.CENTER);
        lblSep.setFont(new Font("Georgia", Font.PLAIN, 12));
        lblSep.setForeground(C_TEXTO_DIM);
        lblSep.setAlignmentX(Component.CENTER_ALIGNMENT);

        JLabel lblCargando = new JLabel("Iniciando sistema...", SwingConstants.CENTER);
        lblCargando.setFont(new Font("Monospaced", Font.PLAIN, 11));
        lblCargando.setForeground(new Color(212, 175, 95, 140));
        lblCargando.setAlignmentX(Component.CENTER_ALIGNMENT);

        contenido.add(lblEmoji);
        contenido.add(Box.createVerticalStrut(8));
        contenido.add(lblTitulo);
        contenido.add(Box.createVerticalStrut(4));
        contenido.add(lblSubtitulo);
        contenido.add(Box.createVerticalStrut(14));
        contenido.add(lblSep);
        contenido.add(Box.createVerticalStrut(28));
        contenido.add(lblCargando);
        panel.add(contenido, BorderLayout.CENTER);
        splash.add(panel);
        splash.setVisible(true);

        Timer timer = new Timer(2200, e -> { splash.dispose(); iniciarVentanaPrincipal(); });
        timer.setRepeats(false);
        timer.start();
    }

    //CONSTRUCTOR Y ARRANQUE
    public VentanaPrincipal() { mostrarSplash(); }

    private void iniciarVentanaPrincipal() {
        agencia = new Agencia(100, 50, 50, 30);
        agencia.cargarDatos();

        setTitle("No Más Enanos Por Favor  —  Agencia de Modelaje");
        setSize(1050, 720);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        getContentPane().setBackground(C_FONDO);

        addWindowListener(new WindowAdapter() {
            @Override public void windowClosing(WindowEvent e) { agencia.guardarDatos(); }
        });

        pestanas = new JTabbedPane();
        pestanas.setBackground(C_FONDO);
        pestanas.setForeground(C_TEXTO_DIM);
        pestanas.setFont(new Font("Georgia", Font.PLAIN, 13));
        pestanas.setBorder(BorderFactory.createEmptyBorder(0, 10, 10, 10));

        // Colores personalizados de pestanas
        UIManager.put("TabbedPane.selected",           C_TARJETA);
        UIManager.put("TabbedPane.background",         C_FONDO);
        UIManager.put("TabbedPane.foreground",         C_TEXTO_DIM);
        UIManager.put("TabbedPane.selectedForeground", C_DORADO);
        UIManager.put("TabbedPane.tabAreaBackground",  C_FONDO);
        UIManager.put("TabbedPane.unselectedBackground", C_SUPERFICIE);

        pestanas.addTab("Modelos",    crearPanelModelos());
        pestanas.addTab("Fotografos", crearPanelFotografos());
        pestanas.addTab("Lugares",    crearPanelLugares());
        pestanas.addTab("Eventos",    crearPanelEventos());
        pestanas.addTab("Asignar",    crearPanelAsignacion());

        JPanel contenedor = new JPanel(new BorderLayout());
        contenedor.setBackground(C_FONDO);
        contenedor.add(crearHeader(), BorderLayout.NORTH);
        contenedor.add(pestanas,      BorderLayout.CENTER);
        add(contenedor);
        setVisible(true);
    }

    //HEADER

    private JPanel crearHeader() {
        JPanel h = new JPanel(new BorderLayout()) {
            @Override protected void paintComponent(Graphics g) {
                Graphics2D g2 = (Graphics2D) g;
                GradientPaint gp = new GradientPaint(0,0,new Color(18,15,8), getWidth(),0, new Color(10,10,12));
                g2.setPaint(gp); g2.fillRect(0,0,getWidth(),getHeight());
                g2.setColor(C_DORADO); g2.setStroke(new BasicStroke(1.5f));
                g2.drawLine(0, getHeight()-1, getWidth(), getHeight()-1);
            }
        };
        h.setPreferredSize(new Dimension(0, 62));
        h.setBorder(BorderFactory.createEmptyBorder(0, 20, 0, 20));

        // Logo en el header
        JLabel emoji;
        ImageIcon iconoHeader = cargarLogo(-1, 50);
        if (iconoHeader != null) {
            emoji = new JLabel(iconoHeader);
        } else {
            emoji = new JLabel("");
            emoji.setFont(new Font("Segoe UI Emoji", Font.PLAIN, 28));
        }
        emoji.setBorder(BorderFactory.createEmptyBorder(0, 0, 0, 12));

        JPanel textos = new JPanel();
        textos.setLayout(new BoxLayout(textos, BoxLayout.Y_AXIS));
        textos.setOpaque(false);
        JLabel t1 = new JLabel("NO MÁS ENANOS POR FAVOR");
        t1.setFont(new Font("Georgia", Font.BOLD, 17)); t1.setForeground(C_DORADO);
        JLabel t2 = new JLabel("Sistema de Gestión — Agencia de Modelaje");
        t2.setFont(new Font("Georgia", Font.ITALIC, 11)); t2.setForeground(C_TEXTO_DIM);
        textos.add(t1); textos.add(t2);

        h.add(emoji,  BorderLayout.WEST);
        h.add(textos, BorderLayout.CENTER);
        return h;
    }

    //HELPERS DE ESTILO
    private JPanel tarjeta(String titulo) {
        JPanel p = new JPanel();
        p.setBackground(C_TARJETA);
        p.setBorder(BorderFactory.createCompoundBorder(
                BorderFactory.createEmptyBorder(8,8,4,8),
                BorderFactory.createTitledBorder(
                        BorderFactory.createLineBorder(C_DORADO, 1), titulo,
                        TitledBorder.LEFT, TitledBorder.TOP,
                        new Font("Georgia", Font.BOLD, 12), C_DORADO)));
        return p;
    }

    private JTextField campo() {
        JTextField tf = new JTextField();
        tf.setBackground(new Color(35,35,42)); tf.setForeground(C_TEXTO);
        tf.setCaretColor(C_DORADO);
        tf.setBorder(BorderFactory.createCompoundBorder(
                BorderFactory.createLineBorder(C_BORDE),
                BorderFactory.createEmptyBorder(4,8,4,8)));
        tf.setFont(new Font("Monospaced", Font.PLAIN, 13));
        tf.addFocusListener(new FocusAdapter() {
            @Override public void focusGained(FocusEvent e) {
                tf.setBorder(BorderFactory.createCompoundBorder(
                        BorderFactory.createLineBorder(C_DORADO),
                        BorderFactory.createEmptyBorder(4,8,4,8)));
            }
            @Override public void focusLost(FocusEvent e) {
                tf.setBorder(BorderFactory.createCompoundBorder(
                        BorderFactory.createLineBorder(C_BORDE),
                        BorderFactory.createEmptyBorder(4,8,4,8)));
            }
        });
        return tf;
    }

    private JLabel etiqueta(String t) {
        JLabel l = new JLabel(t); l.setForeground(C_TEXTO_DIM);
        l.setFont(new Font("Georgia", Font.PLAIN, 12)); return l;
    }

    private JButton boton(String texto, Color fondo) {
        JButton btn = new JButton(texto) {
            @Override protected void paintComponent(Graphics g) {
                Graphics2D g2 = (Graphics2D) g.create();
                g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
                Color c = getModel().isPressed() ? fondo.darker() : getModel().isRollover() ? fondo.brighter() : fondo;
                g2.setColor(c); g2.fillRoundRect(0,0,getWidth(),getHeight(),8,8); g2.dispose();
                super.paintComponent(g);
            }
        };
        btn.setFont(new Font("Georgia", Font.BOLD, 12));
        btn.setBorder(BorderFactory.createEmptyBorder(7,16,7,16));
        btn.setFocusPainted(false); btn.setContentAreaFilled(false);
        btn.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        return btn;
    }

    private JSpinner spinnerNum(int val, int min, int max) {
        JSpinner sp = new JSpinner(new SpinnerNumberModel(val, min, max, 1));
        JComponent ed = sp.getEditor();
        if (ed instanceof JSpinner.NumberEditor) {
            JTextField tf = ((JSpinner.NumberEditor)ed).getTextField();
            tf.setBackground(new Color(35,35,42)); tf.setForeground(C_TEXTO);
            tf.setCaretColor(C_DORADO);
        }
        return sp;
    }

    private JTable crearTabla(DefaultTableModel modelo) {
        JTable t = new JTable(modelo) {
            @Override public boolean isCellEditable(int r, int c) { return false; }
        };
        t.setBackground(C_SUPERFICIE); t.setForeground(C_TEXTO);
        t.setGridColor(C_BORDE); t.setRowHeight(26);
        t.setFont(new Font("Monospaced", Font.PLAIN, 12));
        t.setSelectionBackground(new Color(212,175,95,60));
        t.setSelectionForeground(C_DORADO_CLARO);
        t.setShowHorizontalLines(true); t.setShowVerticalLines(false);
        t.setIntercellSpacing(new Dimension(0,1));
        JTableHeader h = t.getTableHeader();
        h.setBackground(new Color(15,12,5)); h.setForeground(C_DORADO);
        h.setFont(new Font("Georgia", Font.BOLD, 12));
        h.setBorder(BorderFactory.createMatteBorder(0,0,1,0,C_DORADO));
        t.setDefaultRenderer(Object.class, new DefaultTableCellRenderer() {
            @Override public Component getTableCellRendererComponent(
                    JTable tbl, Object val, boolean sel, boolean foc, int row, int col) {
                super.getTableCellRendererComponent(tbl,val,sel,foc,row,col);
                setBackground(sel ? new Color(212,175,95,60) : (row%2==0 ? C_SUPERFICIE : new Color(24,24,30)));
                setForeground(sel ? C_DORADO_CLARO : C_TEXTO);
                setBorder(BorderFactory.createEmptyBorder(0,8,0,8));
                return this;
            }
        });
        return t;
    }

    private JScrollPane scroll(Component c, String titulo) {
        JScrollPane sp = new JScrollPane(c);
        sp.setBackground(C_SUPERFICIE); sp.getViewport().setBackground(C_SUPERFICIE);
        sp.setBorder(BorderFactory.createCompoundBorder(
                BorderFactory.createEmptyBorder(4,0,0,0),
                BorderFactory.createTitledBorder(
                        BorderFactory.createLineBorder(C_BORDE), titulo,
                        TitledBorder.LEFT, TitledBorder.TOP,
                        new Font("Georgia", Font.PLAIN, 11), C_TEXTO_DIM)));
        return sp;
    }

    private <T> JComboBox<T> combo(T[] opciones) {
        JComboBox<T> cb = new JComboBox<>(opciones);
        cb.setBackground(new Color(35,35,42)); cb.setForeground(C_TEXTO);
        cb.setFont(new Font("Georgia", Font.PLAIN, 12));
        return cb;
    }

    private JPanel panelBotones(JButton... btns) {
        JPanel p = new JPanel(new FlowLayout(FlowLayout.LEFT, 8, 8));
        p.setBackground(C_FONDO);
        for (JButton b : btns) p.add(b);
        return p;
    }

    //PANEL MODELOS
    private JPanel crearPanelModelos() {
        JPanel panel = new JPanel(new BorderLayout(8,8));
        panel.setBackground(C_FONDO);
        panel.setBorder(BorderFactory.createEmptyBorder(10,10,10,10));

        JPanel form = tarjeta("  Registrar Modelo");
        form.setLayout(new GridLayout(0, 4, 8, 8));
        txtNombreModelo=campo(); txtIdModelo=campo(); txtContactoModelo=campo();
        txtCodigoModelo=campo(); txtEstaturaModelo=campo(); txtCategoriaModelo=campo();
        chkDisponible = new JCheckBox("Disponible", true);
        chkDisponible.setBackground(C_TARJETA); chkDisponible.setForeground(C_TEXTO);
        chkDisponible.setFont(new Font("Georgia", Font.PLAIN, 12));

        form.add(etiqueta("Nombre:"));         form.add(txtNombreModelo);
        form.add(etiqueta("Identificacion:")); form.add(txtIdModelo);
        form.add(etiqueta("Contacto:"));       form.add(txtContactoModelo);
        form.add(etiqueta("Codigo Modelo:"));  form.add(txtCodigoModelo);
        form.add(etiqueta("Estatura (m):"));   form.add(txtEstaturaModelo);
        form.add(etiqueta("Categoria:"));      form.add(txtCategoriaModelo);
        form.add(etiqueta(""));                form.add(chkDisponible);

        JButton btnA = boton("Agregar",  C_DORADO);   btnA.setForeground(C_FONDO);
        JButton btnE = boton("Eliminar", C_PELIGRO);  btnE.setForeground(Color.WHITE);
        JButton btnL = boton("Limpiar",  new Color(60,60,75)); btnL.setForeground(C_TEXTO);
        btnA.addActionListener(e -> agregarModelo());
        btnE.addActionListener(e -> eliminarModelo());
        btnL.addActionListener(e -> limpiarModelo());

        JPanel norte = new JPanel(new BorderLayout());
        norte.setBackground(C_FONDO);
        norte.add(form, BorderLayout.CENTER);
        norte.add(panelBotones(btnA,btnE,btnL), BorderLayout.SOUTH);

        modeloTablaModelos = new DefaultTableModel(
                new String[]{"Nombre","ID","Codigo","Estatura","Categoria","Disponible"}, 0);
        panel.add(norte, BorderLayout.NORTH);
        panel.add(scroll(crearTabla(modeloTablaModelos), "  Modelos registrados"), BorderLayout.CENTER);
        actualizarModelos(); return panel;
    }

    private void agregarModelo() {
        try {
            String nombre=txtNombreModelo.getText().trim(), id=txtIdModelo.getText().trim();
            String contacto=txtContactoModelo.getText().trim(), codigo=txtCodigoModelo.getText().trim();
            double estatura=Double.parseDouble(txtEstaturaModelo.getText().trim());
            String categoria=txtCategoriaModelo.getText().trim();
            boolean disp=chkDisponible.isSelected();
            if (nombre.isEmpty()||id.isEmpty()||codigo.isEmpty()) { mostrarError("Nombre, ID y Código son obligatorios."); return; }
            if (estatura < 1.50) {
                JOptionPane.showMessageDialog(this,
                        "No se puede registrar el modelo.\nLa estatura mínima permitida es 1.50 m.",
                        "No más enanos por favor", JOptionPane.WARNING_MESSAGE); return;
            }
            agencia.agregarModelo(new Modelo(nombre,id,contacto,codigo,estatura,categoria,disp));
            actualizarModelos(); limpiarModelo(); mostrarExito("Modelo registrado.");
        } catch (NumberFormatException ex) { mostrarError("La estatura debe ser un número. Ejemplo: 1.75"); }
    }

    private void eliminarModelo() {
        String cod = JOptionPane.showInputDialog(this,"Código del modelo a eliminar:");
        if (cod==null||cod.trim().isEmpty()) return;
        boolean ok=agencia.eliminarModelo(cod.trim());
        if(ok) mostrarExito("Modelo eliminado."); else mostrarError("No se encontró el modelo.");
        actualizarModelos();
    }

    private void actualizarModelos() {
        modeloTablaModelos.setRowCount(0);
        for (int i=0;i<agencia.getCantidadModelos();i++) {
            Modelo m=agencia.getModelo(i);
            modeloTablaModelos.addRow(new Object[]{
                    m.getNombre(),m.getIdentificacion(),m.getCodigoModelo(),
                    String.format("%.2f m",m.getEstatura()),m.getCategoria(),
                    m.isDisponible()?"Si":"No"});
        }
    }

    private void limpiarModelo() {
        txtNombreModelo.setText(""); txtIdModelo.setText(""); txtContactoModelo.setText("");
        txtCodigoModelo.setText(""); txtEstaturaModelo.setText(""); txtCategoriaModelo.setText("");
        chkDisponible.setSelected(true);
    }

    //PANEL FOTOGRAFOS
    private JPanel crearPanelFotografos() {
        JPanel panel = new JPanel(new BorderLayout(8,8));
        panel.setBackground(C_FONDO);
        panel.setBorder(BorderFactory.createEmptyBorder(10,10,10,10));

        JPanel form = tarjeta("  Registrar Fotógrafo");
        form.setLayout(new GridLayout(0,4,8,8));
        txtNombreFoto=campo(); txtIdFoto=campo(); txtContactoFoto=campo();
        txtEspecialidadFoto=campo(); txtAnosFoto=campo();

        form.add(etiqueta("Nombre:"));           form.add(txtNombreFoto);
        form.add(etiqueta("Identificacion:"));   form.add(txtIdFoto);
        form.add(etiqueta("Contacto:"));         form.add(txtContactoFoto);
        form.add(etiqueta("Especialidad:"));     form.add(txtEspecialidadFoto);
        form.add(etiqueta("Años experiencia:")); form.add(txtAnosFoto);
        form.add(new JLabel()); form.add(new JLabel());

        JButton btnA=boton("Agregar",C_DORADO);   btnA.setForeground(C_FONDO);
        JButton btnE=boton("Eliminar",C_PELIGRO); btnE.setForeground(Color.WHITE);
        JButton btnL=boton("Limpiar",new Color(60,60,75)); btnL.setForeground(C_TEXTO);
        btnA.addActionListener(e->agregarFotografo());
        btnE.addActionListener(e->eliminarFotografo());
        btnL.addActionListener(e->limpiarFotografo());

        JPanel norte = new JPanel(new BorderLayout()); norte.setBackground(C_FONDO);
        norte.add(form, BorderLayout.CENTER);
        norte.add(panelBotones(btnA,btnE,btnL), BorderLayout.SOUTH);

        modeloTablaFotografos = new DefaultTableModel(
                new String[]{"Nombre","ID","Contacto","Especialidad","Años exp."}, 0);
        panel.add(norte, BorderLayout.NORTH);
        panel.add(scroll(crearTabla(modeloTablaFotografos),"  Fotógrafos registrados"), BorderLayout.CENTER);
        actualizarFotografos(); return panel;
    }

    private void agregarFotografo() {
        try {
            String nombre=txtNombreFoto.getText().trim(), id=txtIdFoto.getText().trim();
            String contacto=txtContactoFoto.getText().trim(), esp=txtEspecialidadFoto.getText().trim();
            int anos=Integer.parseInt(txtAnosFoto.getText().trim());
            if(nombre.isEmpty()||id.isEmpty()) { mostrarError("Nombre e ID son obligatorios."); return; }
            agencia.agregarFotografo(new Fotografo(nombre,id,contacto,esp,anos));
            actualizarFotografos(); limpiarFotografo(); mostrarExito("Fotografo registrado.");
        } catch (NumberFormatException ex) { mostrarError("Los años de experiencia deben ser un número entero."); }
    }

    private void eliminarFotografo() {
        String id=JOptionPane.showInputDialog(this,"ID del fotógrafo a eliminar:");
        if(id==null||id.trim().isEmpty()) return;
        boolean ok=agencia.eliminarFotografo(id.trim());
        if(ok) mostrarExito("Fotografo eliminado."); else mostrarError("No se encontró.");
        actualizarFotografos();
    }

    private void actualizarFotografos() {
        modeloTablaFotografos.setRowCount(0);
        for(int i=0;i<agencia.getCantidadFotografos();i++) {
            Fotografo f=agencia.getFotografo(i);
            modeloTablaFotografos.addRow(new Object[]{
                    f.getNombre(),f.getIdentificacion(),f.getContacto(),
                    f.getEspecialidad(),f.getAnosExperiencia()+" años"});
        }
    }

    private void limpiarFotografo() {
        txtNombreFoto.setText(""); txtIdFoto.setText(""); txtContactoFoto.setText("");
        txtEspecialidadFoto.setText(""); txtAnosFoto.setText("");
    }

    //PANEL LUGARES
    private JPanel crearPanelLugares() {
        JPanel panel = new JPanel(new BorderLayout(8,8));
        panel.setBackground(C_FONDO);
        panel.setBorder(BorderFactory.createEmptyBorder(10,10,10,10));

        JPanel form = tarjeta("  Registrar Lugar");
        form.setLayout(new GridLayout(0,4,8,8));
        txtNombreLugar=campo(); txtDireccionLugar=campo(); txtCiudadLugar=campo();
        txtCapacidadLugar=campo(); txtTipoLugar=campo();

        form.add(etiqueta("Nombre:"));    form.add(txtNombreLugar);
        form.add(etiqueta("Dirección:")); form.add(txtDireccionLugar);
        form.add(etiqueta("Ciudad:"));    form.add(txtCiudadLugar);
        form.add(etiqueta("Capacidad:")); form.add(txtCapacidadLugar);
        form.add(etiqueta("Tipo:"));      form.add(txtTipoLugar);
        form.add(new JLabel()); form.add(new JLabel());

        JButton btnA=boton("Agregar",C_DORADO); btnA.setForeground(C_FONDO);
        JButton btnL=boton("Limpiar",new Color(60,60,75)); btnL.setForeground(C_TEXTO);
        btnA.addActionListener(e->agregarLugar()); btnL.addActionListener(e->limpiarLugar());

        JPanel norte=new JPanel(new BorderLayout()); norte.setBackground(C_FONDO);
        norte.add(form, BorderLayout.CENTER);
        norte.add(panelBotones(btnA,btnL), BorderLayout.SOUTH);

        modeloTablaLugares = new DefaultTableModel(
                new String[]{"Nombre","Dirección","Ciudad","Capacidad","Tipo"}, 0);
        panel.add(norte, BorderLayout.NORTH);
        panel.add(scroll(crearTabla(modeloTablaLugares),"  Lugares registrados"), BorderLayout.CENTER);
        actualizarLugares(); return panel;
    }

    private void agregarLugar() {
        try {
            String nombre=txtNombreLugar.getText().trim(), dir=txtDireccionLugar.getText().trim();
            String ciudad=txtCiudadLugar.getText().trim(), tipo=txtTipoLugar.getText().trim();
            int cap=Integer.parseInt(txtCapacidadLugar.getText().trim());
            if(nombre.isEmpty()) { mostrarError("El nombre del lugar es obligatorio."); return; }
            agencia.agregarLugar(new Lugar(nombre,dir,ciudad,cap,tipo));
            actualizarLugares(); limpiarLugar(); mostrarExito("Lugar registrado.");
        } catch (NumberFormatException ex) { mostrarError("La capacidad debe ser un número entero."); }
    }

    private void actualizarLugares() {
        modeloTablaLugares.setRowCount(0);
        for(int i=0;i<agencia.getCantidadLugares();i++) {
            Lugar l=agencia.getLugar(i);
            modeloTablaLugares.addRow(new Object[]{
                    l.getNombre(),l.getDireccion(),l.getCiudad(),l.getCapacidad(),l.getTipoLugar()});
        }
    }

    private void limpiarLugar() {
        txtNombreLugar.setText(""); txtDireccionLugar.setText("");
        txtCiudadLugar.setText(""); txtCapacidadLugar.setText(""); txtTipoLugar.setText("");
    }

    //PANEL EVENTOS
    private JPanel crearPanelEventos() {
        JPanel panel = new JPanel(new BorderLayout(8,8));
        panel.setBackground(C_FONDO);
        panel.setBorder(BorderFactory.createEmptyBorder(10,10,10,10));

        JPanel form = tarjeta("  Crear Evento");
        form.setLayout(new GridLayout(0,4,8,8));
        txtNombreEvento=campo(); txtLugarEvento=campo(); txtExtra1=campo(); txtExtra2=campo();
        comboTipoEvento=combo(new String[]{"Publico","Privado"});

        LocalDate hoy=LocalDate.now();
        spDia=spinnerNum(hoy.getDayOfMonth(),1,31);
        spMes=spinnerNum(hoy.getMonthValue(),1,12);
        spAnio=spinnerNum(hoy.getYear(),2000,2100);
        ((JSpinner.NumberEditor)spAnio.getEditor()).getFormat().setGroupingUsed(false);
        spDia.setPreferredSize(new Dimension(55,28));
        spMes.setPreferredSize(new Dimension(55,28));
        spAnio.setPreferredSize(new Dimension(75,28));

        JPanel dp=new JPanel(new FlowLayout(FlowLayout.LEFT,3,0)); dp.setBackground(C_TARJETA);
        JLabel s1=new JLabel("/"); s1.setForeground(C_DORADO);
        JLabel s2=new JLabel("/"); s2.setForeground(C_DORADO);
        dp.add(spDia); dp.add(s1); dp.add(spMes); dp.add(s2); dp.add(spAnio);

        lblExtra1=etiqueta("Capacidad asistentes:"); lblExtra2=etiqueta("Patrocinador:");
        comboTipoEvento.addActionListener(e -> {
            boolean pub=comboTipoEvento.getSelectedItem().equals("Publico");
            lblExtra1.setText(pub?"Capacidad asistentes:":"Cliente:");
            lblExtra2.setText(pub?"Patrocinador:":"Nivel confidencialidad:");
        });

        form.add(etiqueta("Nombre Evento:"));     form.add(txtNombreEvento);
        form.add(etiqueta("Fecha (d/m/a):"));     form.add(dp);
        form.add(etiqueta("Nombre del Lugar:"));  form.add(txtLugarEvento);
        form.add(etiqueta("Tipo:"));              form.add(comboTipoEvento);
        form.add(lblExtra1); form.add(txtExtra1);
        form.add(lblExtra2); form.add(txtExtra2);

        JButton btnC=boton("Crear Evento",C_DORADO);      btnC.setForeground(C_FONDO);
        JButton btnX=boton("Cancelar Evento",C_PELIGRO);  btnX.setForeground(Color.WHITE);
        JButton btnL=boton("Limpiar",new Color(60,60,75)); btnL.setForeground(C_TEXTO);
        btnC.addActionListener(e->crearEvento());
        btnX.addActionListener(e->cancelarEvento());
        btnL.addActionListener(e->limpiarEvento());

        JPanel norte=new JPanel(new BorderLayout()); norte.setBackground(C_FONDO);
        norte.add(form, BorderLayout.CENTER);
        norte.add(panelBotones(btnC,btnX,btnL), BorderLayout.SOUTH);

        modeloTablaEventos = new DefaultTableModel(
                new String[]{"Nombre","Fecha","Lugar","Tipo","Modelos","Fotógrafos"}, 0);
        panel.add(norte, BorderLayout.NORTH);
        panel.add(scroll(crearTabla(modeloTablaEventos),"  Eventos registrados"), BorderLayout.CENTER);
        actualizarEventos(); return panel;
    }

    private void crearEvento() {
        String nombre=txtNombreEvento.getText().trim(), nomL=txtLugarEvento.getText().trim();
        String e1=txtExtra1.getText().trim(), e2=txtExtra2.getText().trim();
        String tipo=(String)comboTipoEvento.getSelectedItem();
        if(nombre.isEmpty()) { mostrarError("El nombre del evento es obligatorio."); return; }
        LocalDate fecha=Dates.construir((int)spDia.getValue(),(int)spMes.getValue(),(int)spAnio.getValue());
        if(fecha==null) { mostrarError("La fecha ingresada no es válida."); return; }
        Lugar lugar=null;
        if(!nomL.isEmpty()) {
            lugar=agencia.buscarLugarPorNombre(nomL);
            if(lugar==null) { mostrarError("Lugar '"+nomL+"' no encontrado.\nRegístrelo primero en Lugares."); return; }
        }
        if(tipo.equals("Publico")) {
            try {
                int capacidadEvento = Integer.parseInt(e1);
                // Validar que la capacidad del evento no supere la del lugar
                if (lugar != null && capacidadEvento > lugar.getCapacidad()) {
                    mostrarError("La capacidad del evento (" + capacidadEvento + ") supera\n"
                            + "la capacidad máxima del lugar (" + lugar.getCapacidad() + ").");
                    return;
                }
                agencia.agregarEvento(new EventoPublico(nombre,fecha,lugar,capacidadEvento,e2));
                mostrarExito("Evento Publico creado.");
            } catch(NumberFormatException ex) { mostrarError("La capacidad debe ser un número entero."); return; }
        } else {
            agencia.agregarEvento(new EventoPrivado(nombre,fecha,lugar,e1,e2));
            mostrarExito("Evento Privado creado.");
        }
        actualizarEventos(); limpiarEvento();
    }

    private void cancelarEvento() {
        String nombre=JOptionPane.showInputDialog(this,"Nombre del evento a cancelar:");
        if(nombre==null||nombre.trim().isEmpty()) return;
        boolean ok=agencia.cancelarEvento(nombre.trim());
        if(ok) mostrarExito("Evento cancelado."); else mostrarError("No se encontró el evento.");
        actualizarEventos();
    }

    private void actualizarEventos() {
        modeloTablaEventos.setRowCount(0);
        for(int i=0;i<agencia.getCantidadEventos();i++) {
            Evento ev=agencia.getEvento(i);
            String nl=ev.getLugar()!=null?ev.getLugar().getNombre():"—";
            modeloTablaEventos.addRow(new Object[]{
                    ev.getNombreEvento(), Dates.format(ev.getFecha()), nl,
                    ev.tipoEvento().equals("Publico")?"Publico":"Privado",
                    ev.getCantidadModelos(), ev.getCantidadFotografos()});
        }
    }

    private void limpiarEvento() {
        txtNombreEvento.setText(""); txtLugarEvento.setText("");
        txtExtra1.setText(""); txtExtra2.setText("");
    }

    //PANEL ASIGNACION

    private JPanel crearPanelAsignacion() {
        JPanel panel = new JPanel(new BorderLayout(8,8));
        panel.setBackground(C_FONDO);
        panel.setBorder(BorderFactory.createEmptyBorder(10,10,10,10));

        JPanel form = tarjeta("  Asignar Persona a Evento");
        form.setLayout(new GridLayout(0,4,8,8));
        txtEventoAsignar=campo(); txtPersonaId=campo();
        comboTipoPersona=combo(new String[]{"Modelo (codigo)","Fotógrafo (ID)"});

        form.add(etiqueta("Nombre del Evento:")); form.add(txtEventoAsignar);
        form.add(etiqueta("Tipo de persona:"));   form.add(comboTipoPersona);
        form.add(etiqueta("Código o ID:"));       form.add(txtPersonaId);
        form.add(new JLabel()); form.add(new JLabel());

        JButton btnA=boton("Asignar",C_DORADO);                     btnA.setForeground(C_FONDO);
        JButton btnV=boton("Ver detalles",new Color(50,80,110));     btnV.setForeground(Color.WHITE);
        btnA.addActionListener(e->asignarPersona());
        btnV.addActionListener(e->verDetalles());

        JPanel norte=new JPanel(new BorderLayout()); norte.setBackground(C_FONDO);
        norte.add(form, BorderLayout.CENTER);
        norte.add(panelBotones(btnA,btnV), BorderLayout.SOUTH);

        areaAsignacion=new JTextArea();
        areaAsignacion.setEditable(false);
        areaAsignacion.setBackground(C_SUPERFICIE); areaAsignacion.setForeground(C_TEXTO);
        areaAsignacion.setFont(new Font("Monospaced",Font.PLAIN,12));
        areaAsignacion.setBorder(BorderFactory.createEmptyBorder(8,8,8,8));

        panel.add(norte, BorderLayout.NORTH);
        panel.add(scroll(areaAsignacion,"  Detalle de eventos"), BorderLayout.CENTER);
        return panel;
    }

    private void asignarPersona() {
        String nomEvento=txtEventoAsignar.getText().trim(), codigoId=txtPersonaId.getText().trim();
        String tipo=(String)comboTipoPersona.getSelectedItem();
        if(nomEvento.isEmpty()||codigoId.isEmpty()) { mostrarError("Complete todos los campos."); return; }
        Evento evento=agencia.buscarEventoPorNombre(nomEvento);
        if(evento==null) { mostrarError("Evento no encontrado: "+nomEvento); return; }
        if(tipo.startsWith("Modelo")) {
            Modelo m=agencia.buscarModeloPorCodigo(codigoId);
            if(m==null) { mostrarError("Modelo no encontrado: "+codigoId); return; }
            boolean ok=evento.agregarModelo(m);
            if(ok) mostrarExito("Modelo '"+m.getNombre()+"' asignado.");
            else   mostrarError("El modelo ya estaba asignado.");
        } else {
            Fotografo f=agencia.buscarFotografoPorId(codigoId);
            if(f==null) { mostrarError("Fotógrafo no encontrado: "+codigoId); return; }
            boolean ok=evento.agregarFotografo(f);
            if(ok) mostrarExito("Fotógrafo '"+f.getNombre()+"' asignado.");
            else   mostrarError("El fotógrafo ya estaba asignado.");
        }
        actualizarEventos(); // Actualiza conteo en pestaña Eventos
        verDetalles();
    }

    private void verDetalles() {
        StringBuilder sb=new StringBuilder();
        if(agencia.getCantidadEventos()==0) sb.append("No hay eventos registrados.");
        for(int i=0;i<agencia.getCantidadEventos();i++) {
            sb.append(agencia.getEvento(i).mostrarDetalles()).append("\n");
        }
        areaAsignacion.setText(sb.toString());
        areaAsignacion.setCaretPosition(0);
    }

    //HELPERS DIALOGO
    private void mostrarError(String msg) {
        JOptionPane.showMessageDialog(this,msg,"Error",JOptionPane.ERROR_MESSAGE);
    }
    private void mostrarExito(String msg) {
        JOptionPane.showMessageDialog(this,msg,"Listo",JOptionPane.INFORMATION_MESSAGE);
    }

    //MAIN
    public static void main(String[] args) {
        System.setProperty("awt.useSystemAAFontSettings","on");
        System.setProperty("swing.aatext","true");
        System.setProperty("java.awt.headless","false");
        SwingUtilities.invokeLater(VentanaPrincipal::new);
    }
}