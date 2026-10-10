package cruddoctores;

import java.io.*;
import java.util.ArrayList;

public class CRUDDoctores {

    private ArrayList<Doctor> doctores = new ArrayList<>();
    private final String archivo = "doctores.txt";

    public CRUDDoctores() {
        cargarDoctores();
    }

    public void agregarDoctor(Doctor doctor) {
        doctores.add(doctor);
        guardarDoctores();
    }

    public ArrayList<Doctor> listarDoctores() {
        return doctores;
    }

    public Doctor buscarDoctor(String dni) {

        for (Doctor d : doctores) {

            if (d.getDni().equals(dni)) {
                return d;
            }
        }

        return null;
    }

    public boolean actualizarDoctor(String dni, String tipo,
                                    String nombre, String apellido,
                                    String especialidad) {

        Doctor doctor = buscarDoctor(dni);

        if (doctor != null) {

            doctor.setTipo(tipo);
            doctor.setNombre(nombre);
            doctor.setApellido(apellido);
            doctor.setEspecialidad(especialidad);

            guardarDoctores();

            return true;
        }

        return false;
    }

    public boolean eliminarDoctor(String dni) {

        Doctor doctor = buscarDoctor(dni);

        if (doctor != null) {

            doctores.remove(doctor);

            guardarDoctores();

            return true;
        }

        return false;
    }

    private void guardarDoctores() {

        try (PrintWriter pw = new PrintWriter(new FileWriter(archivo))) {

            for (Doctor d : doctores) {
                pw.println(d.toString());
            }

        } catch (IOException e) {

            System.out.println("Error al guardar: " + e.getMessage());
        }
    }

    private void cargarDoctores() {

        File f = new File(archivo);

        if (!f.exists()) {
            return;
        }

        try (BufferedReader br = new BufferedReader(new FileReader(archivo))) {

            String linea;

            while ((linea = br.readLine()) != null) {

                String[] partes = linea.split(";");

                // Registros nuevos:
                // tipo;nombre;apellido;dni;especialidad
                if (partes.length == 5) {

                    doctores.add(new Doctor(
                            partes[0],
                            partes[1],
                            partes[2],
                            partes[3],
                            partes[4]
                    ));

                // Registros antiguos:
                // nombre;apellido;dni;especialidad
                } else if (partes.length == 4) {

                    doctores.add(new Doctor(
                            partes[0],
                            partes[1],
                            partes[2],
                            partes[3]
                    ));
                }
            }

        } catch (IOException e) {

            System.out.println("Error al cargar: " + e.getMessage());
        }
    }
}