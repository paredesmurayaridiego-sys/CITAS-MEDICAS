import java.awt.BorderLayout;
import java.awt.Font;
import java.awt.GridLayout;
import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.SwingConstants;

public class MenuPrincipal extends JFrame {

    public MenuPrincipal() {
        setTitle("SISTEMA DE SALUD - MENÚ PRINCIPAL");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(520, 430);
        setLocationRelativeTo(null);
        setResizable(false);

        JLabel titulo = new JLabel("SISTEMA DE SALUD", SwingConstants.CENTER);
        titulo.setFont(new Font("Arial", Font.BOLD, 24));
        titulo.setBorder(BorderFactory.createEmptyBorder(20, 10, 20, 10));

        JPanel botones = new JPanel(new GridLayout(6, 1, 12, 12));
        botones.setBorder(BorderFactory.createEmptyBorder(10, 60, 25, 60));

        JButton pacientes = new JButton("GESTIÓN DE PACIENTES");
        JButton usuarios = new JButton("GESTIÓN DE USUARIOS");
        JButton doctores = new JButton("GESTIÓN DE PERSONAL MÉDICO");
        JButton medicamentos = new JButton("GESTIÓN DE MEDICAMENTOS");
        JButton citas = new JButton("GESTIÓN DE CITAS MÉDICAS");
        JButton salir = new JButton("CERRAR SESIÓN");

        pacientes.addActionListener(e -> abrir(new FormularioPacientes()));
        usuarios.addActionListener(e -> abrir(new crudusuarios.FrmUsuarios()));
        doctores.addActionListener(e -> abrir(new cruddoctores.frmDoctores()));
        medicamentos.addActionListener(e -> abrir(new frmMedicamentos()));
        citas.addActionListener(e -> abrir(new crudcitas.FrmCitas()));
        salir.addActionListener(e -> {
            dispose();
            new usuario().setVisible(true);
        });

        botones.add(pacientes);
        botones.add(usuarios);
        botones.add(doctores);
        botones.add(medicamentos);
        botones.add(citas);
        botones.add(salir);

        setLayout(new BorderLayout());
        add(titulo, BorderLayout.NORTH);
        add(botones, BorderLayout.CENTER);
    }

    private void abrir(JFrame formulario) {
        formulario.setLocationRelativeTo(this);
        formulario.setVisible(true);
    }
}
