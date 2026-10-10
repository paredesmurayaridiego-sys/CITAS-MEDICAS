package util;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.List;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

/** Ventana reutilizable para consultar los registros guardados en los archivos del sistema. */
public final class TablaRegistros {
    private TablaRegistros() {}
    public static void mostrar(JFrame padre, String titulo, String archivo, String[] columnas) {
        DefaultTableModel modelo = new DefaultTableModel(columnas, 0) {
            @Override public boolean isCellEditable(int fila, int columna) { return false; }
        };
        File f = new File(archivo);
        if (f.exists()) {
            try (BufferedReader br = new BufferedReader(new FileReader(f))) {
                String linea;
                while ((linea = br.readLine()) != null) {
                    if (linea.trim().isEmpty()) continue;
                    String[] valores = linea.split("\\|", -1);
                    if (valores.length == 1 && linea.contains(",")) valores = linea.split(",", -1);
                    Object[] fila = new Object[columnas.length];
                    for (int i = 0; i < fila.length; i++) fila[i] = i < valores.length ? valores[i].trim() : "";
                    modelo.addRow(fila);
                }
            } catch (Exception ex) {
                JOptionPane.showMessageDialog(padre, "No se pudieron leer los registros: " + ex.getMessage());
                return;
            }
        }
        JTable tabla = new JTable(modelo);
        tabla.setFillsViewportHeight(true);
        tabla.setAutoCreateRowSorter(true);
        JFrame ventana = new JFrame(titulo);
        ventana.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        ventana.add(new JScrollPane(tabla));
        ventana.setSize(760, 360);
        ventana.setLocationRelativeTo(padre);
        ventana.setVisible(true);
    }
}
