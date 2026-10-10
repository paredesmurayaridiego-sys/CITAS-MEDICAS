package crudcitas;

import java.io.*;
import java.util.ArrayList;

public class CRUDCitas {
    private final ArrayList<Cita> citas = new ArrayList<>();
    private final String archivo = "citas.txt";

    public CRUDCitas() { cargar(); }

    public boolean agregar(Cita cita) {
        if (buscar(cita.getId()) != null) return false;
        citas.add(cita);
        guardar();
        return true;
    }

    public Cita buscar(String id) {
        for (Cita c : citas) if (c.getId().equals(id)) return c;
        return null;
    }

    public boolean actualizar(String id, String paciente, String doctor, String fecha, String hora, String motivo) {
        Cita c = buscar(id);
        if (c == null) return false;
        c.setDniPaciente(paciente); c.setDniDoctor(doctor); c.setFecha(fecha); c.setHora(hora); c.setMotivo(motivo);
        guardar();
        return true;
    }

    public boolean eliminar(String id) {
        Cita c = buscar(id);
        if (c == null) return false;
        citas.remove(c); guardar(); return true;
    }

    private void guardar() {
        try (PrintWriter pw = new PrintWriter(new FileWriter(archivo))) {
            for (Cita c : citas) pw.println(c);
        } catch (IOException e) { System.out.println("Error al guardar citas: " + e.getMessage()); }
    }

    private void cargar() {
        File f = new File(archivo);
        if (!f.exists()) return;
        try (BufferedReader br = new BufferedReader(new FileReader(f))) {
            String linea;
            while ((linea = br.readLine()) != null) {
                String[] d = linea.split(";", -1);
                if (d.length == 6) citas.add(new Cita(d[0], d[1], d[2], d[3], d[4], d[5]));
            }
        } catch (IOException e) { System.out.println("Error al cargar citas: " + e.getMessage()); }
    }
}
