package crudusuarios;

import java.io.*;
import java.util.ArrayList;

public class CRUDUsuarios {

    private ArrayList<Usuario> usuarios = new ArrayList<>();
    private final String archivo = "usuarios.txt";

    public CRUDUsuarios() {
        cargarUsuarios();
    }

    // AGREGAR
    public void agregarUsuario(Usuario usuario) {
        usuarios.add(usuario);
        guardarUsuarios();
    }

    // LISTAR
    public ArrayList<Usuario> listarUsuarios() {
        return usuarios;
    }

    // BUSCAR POR DNI
    public Usuario buscarUsuario(String dni) {
        for (Usuario usuario : usuarios) {
            if (usuario.getDni().equals(dni)) {
                return usuario;
            }
        }
        return null;
    }

    // ACTUALIZAR
    public boolean actualizarUsuario(String dni, String nombre,
            String apellido, String correo) {

        Usuario usuario = buscarUsuario(dni);

        if (usuario != null) {
            usuario.setNombre(nombre);
            usuario.setApellido(apellido);
            usuario.setCorreo(correo);

            guardarUsuarios();
            return true;
        }

        return false;
    }

    // ELIMINAR
    public boolean eliminarUsuario(String dni) {

        Usuario usuario = buscarUsuario(dni);

        if (usuario != null) {
            usuarios.remove(usuario);
            guardarUsuarios();
            return true;
        }

        return false;
    }

    // GUARDAR
    private void guardarUsuarios() {

        try {
            PrintWriter escritor =
                    new PrintWriter(new FileWriter(archivo));

            for (Usuario usuario : usuarios) {
                escritor.println(usuario.toString());
            }

            escritor.close();

        } catch (IOException e) {
            System.out.println("Error al guardar usuarios.");
        }
    }

    // CARGAR
    private void cargarUsuarios() {

        File archivoFile = new File(archivo);

        if (!archivoFile.exists()) {
            return;
        }

        try {
            BufferedReader lector =
                    new BufferedReader(new FileReader(archivoFile));

            String linea;

            while ((linea = lector.readLine()) != null) {

                String[] datos = linea.split(";");

                if (datos.length == 4) {

                    Usuario usuario = new Usuario(
                            datos[0],
                            datos[1],
                            datos[2],
                            datos[3]
                    );

                    usuarios.add(usuario);
                }
            }

            lector.close();

        } catch (IOException e) {
            System.out.println("Error al cargar usuarios.");
        }
    }
}