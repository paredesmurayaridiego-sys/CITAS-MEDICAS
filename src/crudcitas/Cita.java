package crudcitas;

public class Cita {
    private String id;
    private String dniPaciente;
    private String dniDoctor;
    private String fecha;
    private String hora;
    private String motivo;

    public Cita(String id, String dniPaciente, String dniDoctor, String fecha, String hora, String motivo) {
        this.id = id;
        this.dniPaciente = dniPaciente;
        this.dniDoctor = dniDoctor;
        this.fecha = fecha;
        this.hora = hora;
        this.motivo = motivo;
    }

    public String getId() { return id; }
    public String getDniPaciente() { return dniPaciente; }
    public String getDniDoctor() { return dniDoctor; }
    public String getFecha() { return fecha; }
    public String getHora() { return hora; }
    public String getMotivo() { return motivo; }

    public void setDniPaciente(String v) { dniPaciente = v; }
    public void setDniDoctor(String v) { dniDoctor = v; }
    public void setFecha(String v) { fecha = v; }
    public void setHora(String v) { hora = v; }
    public void setMotivo(String v) { motivo = v; }

    @Override
    public String toString() {
        return id + ";" + dniPaciente + ";" + dniDoctor + ";" + fecha + ";" + hora + ";" + motivo;
    }
}
