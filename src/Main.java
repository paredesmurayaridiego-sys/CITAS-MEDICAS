
import javax.swing.SwingUtilities;
import javax.swing.UIManager;
import javax.swing.JOptionPane;

public class Main {

    public static void main(String[] args) {
        configurarApariencia();

        SwingUtilities.invokeLater(() -> {
            try {
                new usuario().setVisible(true);
            } catch (Exception e) {
                JOptionPane.showMessageDialog(
                    null,
                    "No se pudo iniciar el sistema: " + e.getMessage(),
                    "Error de inicio",
                    JOptionPane.ERROR_MESSAGE
                );
            }
        });
    }

    private static void configurarApariencia() {
        try {
            for (UIManager.LookAndFeelInfo info : UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    UIManager.setLookAndFeel(info.getClassName());
                    return;
                }
            }
        } catch (Exception e) {
            System.err.println("No se pudo configurar Nimbus: " + e.getMessage());
        }
    }
}
