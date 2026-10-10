import crudcitas.Cita;
import crudcitas.CRUDCitas;

/** Pruebas básicas del módulo de citas médicas. Ejecutar desde una carpeta temporal
 * para no modificar los archivos de datos reales del proyecto. */
public class PruebaCRUDCitas {
    private static int aprobadas = 0;

    private static void comprobar(boolean condicion, String nombre) {
        if (!condicion) throw new AssertionError("FALLÓ: " + nombre);
        aprobadas++;
        System.out.println("OK: " + nombre);
    }

    public static void main(String[] args) {
        CRUDCitas crud = new CRUDCitas();
        Cita cita = new Cita("TEST-001", "12345678", "87654321", "2026-10-10", "09:00", "Consulta general");

        comprobar(crud.agregar(cita), "registrar una cita nueva");
        comprobar(!crud.agregar(cita), "rechazar un identificador duplicado");
        comprobar(crud.buscar("TEST-001") != null, "buscar una cita existente");
        comprobar(crud.buscar("NO-EXISTE") == null, "devolver null para una cita inexistente");
        comprobar(crud.actualizar("TEST-001", "11111111", "22222222", "2026-10-11", "10:30", "Control"), "actualizar una cita existente");
        comprobar("Control".equals(crud.buscar("TEST-001").getMotivo()), "verificar datos actualizados");
        comprobar(crud.eliminar("TEST-001"), "eliminar una cita existente");
        comprobar(!crud.eliminar("TEST-001"), "no eliminar una cita ya inexistente");

        System.out.println("Resultado: " + aprobadas + "/8 pruebas aprobadas.");
    }
}
