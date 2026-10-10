import java.util.Arrays;
import java.util.List;

public class PruebaFuncionesMedicamentos {
    public static void main(String[] args) {
        List<Integer> datos = Arrays.asList(10, 20, 30, 40);

        List<Integer> resultado = frmMedicamentos.filtrarMedicamentos(
                datos, numero -> numero >= 30);

        if (!resultado.equals(Arrays.asList(30, 40))) {
            throw new AssertionError("Error en el filtrado");
        }

        final int[] suma = {0};
        frmMedicamentos.recorrerMedicamentos(datos, numero -> suma[0] += numero);

        if (suma[0] != 100) {
            throw new AssertionError("Error en el recorrido");
        }

        System.out.println("PRUEBA 1 APROBADA: filtrado de datos");
        System.out.println("PRUEBA 2 APROBADA: recorrido de datos");
        System.out.println("RESULTADO: todas las pruebas finalizaron correctamente");
    }
}
