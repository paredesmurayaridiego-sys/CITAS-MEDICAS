package cruddoctores;

public class Doctor {

    private String tipo;
    private String nombre;
    private String apellido;
    private String dni;
    private String especialidad;

    public Doctor(String tipo, String nombre, String apellido, String dni, String especialidad) {
        this.tipo = tipo;
        this.nombre = nombre;
        this.apellido = apellido;
        this.dni = dni;
        this.especialidad = especialidad;
    }

    // Constructor anterior para no tener problemas con registros antiguos
    public Doctor(String nombre, String apellido, String dni, String especialidad) {
        this.tipo = "Doctor";
        this.nombre = nombre;
        this.apellido = apellido;
        this.dni = dni;
        this.especialidad = especialidad;
    }

    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getApellido() {
        return apellido;
    }

    public void setApellido(String apellido) {
        this.apellido = apellido;
    }

    public String getDni() {
        return dni;
    }

    public void setDni(String dni) {
        this.dni = dni;
    }

    public String getEspecialidad() {
        return especialidad;
    }

    public void setEspecialidad(String especialidad) {
        this.especialidad = especialidad;
    }

    @Override
    public String toString() {
        return tipo + ";" + nombre + ";" + apellido + ";" + dni + ";" + especialidad;
    }
}