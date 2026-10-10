package crudcitas;

import java.awt.GridLayout;
import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.Dimension;
import java.awt.Color;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;
import javax.swing.*;
import util.Validaciones;

public class FrmCitas extends JFrame {
    private final CRUDCitas crud = new CRUDCitas();
    private final JTextField txtId = new JTextField();
    private final JTextField txtPaciente = new JTextField();
    private final JTextField txtDoctor = new JTextField();
    private final JTextField txtFecha = new JTextField();
    private final JTextField txtHora = new JTextField();
    private final JTextField txtMotivo = new JTextField();

    public FrmCitas() {
        setTitle("GESTIÓN DE CITAS MÉDICAS");
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setSize(520, 360);
        setLocationRelativeTo(null);
        setResizable(false);
        ajustarAnchoCampos();
        instalarMenuRegistros();

        Validaciones.soloNumeros(txtId);
        Validaciones.soloNumeros(txtPaciente);
        Validaciones.soloNumeros(txtDoctor);
        Validaciones.fecha(txtFecha);
        Validaciones.hora(txtHora);
        Validaciones.soloLetras(txtMotivo);

        JPanel campos = new JPanel(new GridLayout(6, 2, 8, 8));
        campos.setBorder(BorderFactory.createEmptyBorder(18, 30, 10, 30));
        campos.add(new JLabel("N.º de cita:")); campos.add(txtId);
        campos.add(new JLabel("DNI paciente:")); campos.add(txtPaciente);
        campos.add(new JLabel("DNI doctor:")); campos.add(txtDoctor);

        // Se conservan los campos originales y se añaden botones para elegir fecha y hora.
        JPanel selectorFecha = new JPanel(new BorderLayout(5, 0));
        JButton btnCalendario = new JButton("📅");
        btnCalendario.setToolTipText("Seleccionar día, mes y año");
        selectorFecha.add(txtFecha, BorderLayout.CENTER);
        selectorFecha.add(btnCalendario, BorderLayout.EAST);
        btnCalendario.addActionListener(e -> seleccionarFecha());
        campos.add(new JLabel("Fecha (dd/mm/aaaa):")); campos.add(selectorFecha);

        JPanel selectorHora = new JPanel(new BorderLayout(5, 0));
        JButton btnReloj = new JButton("🕒");
        btnReloj.setToolTipText("Seleccionar hora y minutos");
        selectorHora.add(txtHora, BorderLayout.CENTER);
        selectorHora.add(btnReloj, BorderLayout.EAST);
        btnReloj.addActionListener(e -> seleccionarHora());
        campos.add(new JLabel("Hora (hh:mm):")); campos.add(selectorHora);
        campos.add(new JLabel("Motivo:")); campos.add(txtMotivo);

        JPanel botones = new JPanel(new GridLayout(1, 4, 8, 8));
        JButton guardar = new JButton("Sacar cita");
        JButton buscar = new JButton("Buscar");
        JButton modificar = new JButton("Modificar");
        JButton eliminar = new JButton("Eliminar");
        botones.add(guardar); botones.add(buscar); botones.add(modificar); botones.add(eliminar);

        guardar.addActionListener(e -> guardar());
        buscar.addActionListener(e -> buscar());
        modificar.addActionListener(e -> modificar());
        eliminar.addActionListener(e -> eliminar());

        setLayout(new java.awt.BorderLayout());
        add(campos, java.awt.BorderLayout.CENTER);
        add(botones, java.awt.BorderLayout.SOUTH);
    }



    private void instalarMenuRegistros() {
        javax.swing.JMenuBar barra = new javax.swing.JMenuBar();
        javax.swing.JButton ver = new javax.swing.JButton("Ver registros guardados");
        ver.setToolTipText("Abrir la tabla con los registros guardados");
        ver.addActionListener(e -> util.TablaRegistros.mostrar(this, "Citas guardadas", "citas.txt", new String[]{"N.º cita", "DNI paciente", "DNI doctor", "Fecha", "Hora", "Motivo"}));
        barra.add(ver);
        setJMenuBar(barra);
    }

    /** Muestra un calendario visual con selección de mes, año y día. */
    private void seleccionarFecha() {
        Calendar base = Calendar.getInstance();
        try {
            if (!txtFecha.getText().trim().isEmpty()) {
                Date fechaActual = new SimpleDateFormat("dd/MM/yyyy").parse(txtFecha.getText().trim());
                base.setTime(fechaActual);
            }
        } catch (java.text.ParseException ignored) { }

        final Calendar seleccion = (Calendar) base.clone();
        final JDialog dialogo = new JDialog(this, "Seleccionar fecha de la cita", true);
        dialogo.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
        dialogo.setLayout(new BorderLayout(8, 8));
        dialogo.getRootPane().setBorder(BorderFactory.createEmptyBorder(12, 12, 12, 12));

        String[] meses = {"Enero", "Febrero", "Marzo", "Abril", "Mayo", "Junio",
            "Julio", "Agosto", "Septiembre", "Octubre", "Noviembre", "Diciembre"};
        JComboBox<String> cmbMes = new JComboBox<>(meses);
        int anioActual = Calendar.getInstance().get(Calendar.YEAR);
        Integer[] anios = new Integer[151];
        for (int i = 0; i < anios.length; i++) anios[i] = anioActual - 100 + i;
        JComboBox<Integer> cmbAnio = new JComboBox<>(anios);
        cmbMes.setSelectedIndex(seleccion.get(Calendar.MONTH));
        cmbAnio.setSelectedItem(seleccion.get(Calendar.YEAR));

        JPanel encabezado = new JPanel(new BorderLayout(6, 0));
        JButton anterior = new JButton("◀");
        JButton siguiente = new JButton("▶");
        JPanel mesAnio = new JPanel(new FlowLayout(FlowLayout.CENTER, 6, 0));
        mesAnio.add(cmbMes); mesAnio.add(cmbAnio);
        encabezado.add(anterior, BorderLayout.WEST);
        encabezado.add(mesAnio, BorderLayout.CENTER);
        encabezado.add(siguiente, BorderLayout.EAST);
        dialogo.add(encabezado, BorderLayout.NORTH);

        JPanel calendario = new JPanel(new GridLayout(7, 7, 4, 4));
        calendario.setPreferredSize(new Dimension(350, 245));
        JPanel pie = new JPanel(new FlowLayout(FlowLayout.RIGHT));
        JButton hoy = new JButton("Hoy");
        JButton cancelar = new JButton("Cancelar");
        JButton aceptar = new JButton("Seleccionar");
        pie.add(hoy); pie.add(cancelar); pie.add(aceptar);
        dialogo.add(calendario, BorderLayout.CENTER);
        dialogo.add(pie, BorderLayout.SOUTH);

        final JButton[] diaSeleccionado = new JButton[1];
        Runnable dibujarCalendario = () -> {
            calendario.removeAll();
            String[] diasSemana = {"Do", "Lu", "Ma", "Mi", "Ju", "Vi", "Sá"};
            for (String dia : diasSemana) {
                JLabel etiqueta = new JLabel(dia, SwingConstants.CENTER);
                etiqueta.setFont(etiqueta.getFont().deriveFont(Font.BOLD));
                calendario.add(etiqueta);
            }
            Calendar primero = (Calendar) seleccion.clone();
            primero.set(Calendar.DAY_OF_MONTH, 1);
            int desplazamiento = primero.get(Calendar.DAY_OF_WEEK) - Calendar.SUNDAY;
            int cantidadDias = primero.getActualMaximum(Calendar.DAY_OF_MONTH);
            for (int i = 0; i < desplazamiento; i++) calendario.add(new JLabel(""));
            for (int dia = 1; dia <= cantidadDias; dia++) {
                final int numeroDia = dia;
                JButton botonDia = new JButton(String.valueOf(dia));
                botonDia.setMargin(new java.awt.Insets(2, 2, 2, 2));
                if (dia == seleccion.get(Calendar.DAY_OF_MONTH)) {
                    botonDia.setBackground(new Color(190, 230, 200));
                    botonDia.setOpaque(true);
                }
                botonDia.addActionListener(ev -> {
                    seleccion.set(Calendar.DAY_OF_MONTH, numeroDia);
                    if (diaSeleccionado[0] != null) diaSeleccionado[0].setBackground(null);
                    botonDia.setBackground(new Color(190, 230, 200));
                    botonDia.setOpaque(true);
                    diaSeleccionado[0] = botonDia;
                });
                calendario.add(botonDia);
            }
            while (calendario.getComponentCount() < 49) calendario.add(new JLabel(""));
            calendario.revalidate(); calendario.repaint();
        };
        Runnable actualizarMesAnio = () -> {
            seleccion.set(Calendar.MONTH, cmbMes.getSelectedIndex());
            seleccion.set(Calendar.YEAR, (Integer) cmbAnio.getSelectedItem());
            int max = seleccion.getActualMaximum(Calendar.DAY_OF_MONTH);
            if (seleccion.get(Calendar.DAY_OF_MONTH) > max) seleccion.set(Calendar.DAY_OF_MONTH, max);
            dibujarCalendario.run();
        };
        cmbMes.addActionListener(e -> actualizarMesAnio.run());
        cmbAnio.addActionListener(e -> actualizarMesAnio.run());
        anterior.addActionListener(e -> {
            int mes = cmbMes.getSelectedIndex() - 1;
            if (mes < 0) { mes = 11; cmbAnio.setSelectedIndex(Math.max(0, cmbAnio.getSelectedIndex() - 1)); }
            cmbMes.setSelectedIndex(mes);
        });
        siguiente.addActionListener(e -> {
            int mes = cmbMes.getSelectedIndex() + 1;
            if (mes > 11) { mes = 0; cmbAnio.setSelectedIndex(Math.min(anios.length - 1, cmbAnio.getSelectedIndex() + 1)); }
            cmbMes.setSelectedIndex(mes);
        });
        hoy.addActionListener(e -> {
            Calendar ahora = Calendar.getInstance();
            seleccion.setTimeInMillis(ahora.getTimeInMillis());
            cmbMes.setSelectedIndex(seleccion.get(Calendar.MONTH));
            cmbAnio.setSelectedItem(seleccion.get(Calendar.YEAR));
            dibujarCalendario.run();
        });
        cancelar.addActionListener(e -> dialogo.dispose());
        aceptar.addActionListener(e -> {
            txtFecha.setText(new SimpleDateFormat("dd/MM/yyyy").format(seleccion.getTime()));
            dialogo.dispose();
        });
        dibujarCalendario.run();
        dialogo.pack();
        dialogo.setLocationRelativeTo(this);
        dialogo.setVisible(true);
    }

    /** Abre un selector de reloj para escoger la hora y los minutos. */
    private void seleccionarHora() {
        int horaInicial = 9;
        int minutoInicial = 0;
        try {
            if (txtHora.getText().trim().matches("\\d{2}:\\d{2}")) {
                String[] partes = txtHora.getText().trim().split(":");
                horaInicial = Integer.parseInt(partes[0]);
                minutoInicial = Integer.parseInt(partes[1]);
                if (horaInicial > 23 || minutoInicial > 59) { horaInicial = 9; minutoInicial = 0; }
            }
        } catch (NumberFormatException ignored) { }

        JSpinner horas = new JSpinner(new SpinnerNumberModel(horaInicial, 0, 23, 1));
        JSpinner minutos = new JSpinner(new SpinnerNumberModel(minutoInicial, 0, 59, 1));
        horas.setEditor(new JSpinner.NumberEditor(horas, "00"));
        minutos.setEditor(new JSpinner.NumberEditor(minutos, "00"));
        JLabel iconoReloj = new JLabel("◷", SwingConstants.CENTER);
        iconoReloj.setFont(new Font(Font.SANS_SERIF, Font.PLAIN, 52));
        JLabel vistaHora = new JLabel(String.format("%02d:%02d", horaInicial, minutoInicial), SwingConstants.CENTER);
        vistaHora.setFont(new Font(Font.MONOSPACED, Font.BOLD, 28));
        JPanel valores = new JPanel(new GridLayout(2, 2, 12, 8));
        valores.add(new JLabel("Hora (00–23):", SwingConstants.CENTER));
        valores.add(new JLabel("Minutos (00–59):", SwingConstants.CENTER));
        valores.add(horas); valores.add(minutos);
        JPanel panel = new JPanel(new BorderLayout(8, 8));
        panel.setBorder(BorderFactory.createEmptyBorder(8, 12, 8, 12));
        panel.add(iconoReloj, BorderLayout.NORTH);
        panel.add(vistaHora, BorderLayout.CENTER);
        panel.add(valores, BorderLayout.SOUTH);
        Runnable actualizarVista = () -> vistaHora.setText(String.format("%02d:%02d", (Integer) horas.getValue(), (Integer) minutos.getValue()));
        horas.addChangeListener(e -> actualizarVista.run());
        minutos.addChangeListener(e -> actualizarVista.run());
        int opcion = JOptionPane.showConfirmDialog(this, panel, "Reloj: seleccionar hora y minutos",
                JOptionPane.OK_CANCEL_OPTION, JOptionPane.PLAIN_MESSAGE);
        if (opcion == JOptionPane.OK_OPTION) {
            txtHora.setText(String.format("%02d:%02d", (Integer) horas.getValue(), (Integer) minutos.getValue()));
        }
    }

    private boolean validar() {
        if (txtId.getText().trim().isEmpty() || txtPaciente.getText().trim().isEmpty() ||
            txtDoctor.getText().trim().isEmpty() || txtFecha.getText().trim().isEmpty() ||
            txtHora.getText().trim().isEmpty() || txtMotivo.getText().trim().isEmpty()) {
            JOptionPane.showMessageDialog(this, "Complete todos los campos."); return false;
        }
        if (!txtFecha.getText().matches("\\d{2}/\\d{2}/\\d{4}")) {
            JOptionPane.showMessageDialog(this, "La fecha debe tener formato dd/mm/aaaa."); return false;
        }
        if (!txtHora.getText().matches("\\d{2}:\\d{2}")) {
            JOptionPane.showMessageDialog(this, "La hora debe tener formato hh:mm."); return false;
        }
        return true;
    }

    private void guardar() {
        if (!validar()) return;
        if (crud.agregar(new Cita(txtId.getText().trim(), txtPaciente.getText().trim(), txtDoctor.getText().trim(), txtFecha.getText().trim(), txtHora.getText().trim(), txtMotivo.getText().trim()))) {
            JOptionPane.showMessageDialog(this, "Cita registrada correctamente."); limpiar();
        } else JOptionPane.showMessageDialog(this, "Ese número de cita ya existe.");
    }

    private void buscar() {
        String id = txtId.getText().trim();
        if (id.isEmpty()) { JOptionPane.showMessageDialog(this, "Ingrese el número de cita."); return; }
        Cita c = crud.buscar(id);
        if (c == null) { JOptionPane.showMessageDialog(this, "Cita no encontrada."); return; }
        txtPaciente.setText(c.getDniPaciente()); txtDoctor.setText(c.getDniDoctor()); txtFecha.setText(c.getFecha()); txtHora.setText(c.getHora()); txtMotivo.setText(c.getMotivo());
    }

    private void modificar() {
        if (!validar()) return;
        if (crud.actualizar(txtId.getText().trim(), txtPaciente.getText().trim(), txtDoctor.getText().trim(), txtFecha.getText().trim(), txtHora.getText().trim(), txtMotivo.getText().trim())) {
            JOptionPane.showMessageDialog(this, "Cita modificada correctamente."); limpiar();
        } else JOptionPane.showMessageDialog(this, "Cita no encontrada.");
    }

    private void eliminar() {
        String id = txtId.getText().trim();
        if (id.isEmpty()) { JOptionPane.showMessageDialog(this, "Ingrese el número de cita."); return; }
        if (crud.eliminar(id)) { JOptionPane.showMessageDialog(this, "Cita eliminada correctamente."); limpiar(); }
        else JOptionPane.showMessageDialog(this, "Cita no encontrada.");
    }

    private void limpiar() { txtId.setText(""); txtPaciente.setText(""); txtDoctor.setText(""); txtFecha.setText(""); txtHora.setText(""); txtMotivo.setText(""); }
/**
     * Garantiza campos amplios y legibles; algunos diseños de NetBeans los
     * dejaban con el ancho mínimo, por eso no se veía lo escrito.
     */
    private void ajustarAnchoCampos() {
        ajustarAnchoCamposEn(getContentPane());
        revalidate();
        repaint();
    }

    private void ajustarAnchoCamposEn(java.awt.Container contenedor) {
        for (java.awt.Component componente : contenedor.getComponents()) {
            if (componente instanceof javax.swing.JTextField) {
                javax.swing.JTextField campo = (javax.swing.JTextField) componente;
                campo.setColumns(22);
                campo.setMinimumSize(new java.awt.Dimension(190, 28));
                campo.setPreferredSize(new java.awt.Dimension(220, 28));
                campo.setForeground(java.awt.Color.BLACK);
                campo.setBackground(java.awt.Color.WHITE);
                campo.setCaretColor(java.awt.Color.BLACK);
                campo.setOpaque(true);
            }
            if (componente instanceof java.awt.Container) {
                ajustarAnchoCamposEn((java.awt.Container) componente);
            }
        }
    }
}
