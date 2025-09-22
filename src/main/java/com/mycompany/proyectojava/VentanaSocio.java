import javax.swing.*;
import java.awt.*;

public class VentanaSocio extends JFrame {
    private ClubDeportivo club;
    private String RutSocio;
    private Socio Socio;

    public VentanaSocio(Socio socio, ClubDeportivo club) {
        this.Socio = socio;
        this.club = club;
        this.RutSocio = socio.getRut();

        setTitle("Panel socio");
        setSize(700, 300);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        //Panel socio
        JPanel panel = new JPanel();
        panel.setLayout(null);

        //etiqueta Socio
        JLabel lblSocio = new JLabel("Socio: " + socio.getNombre());
        lblSocio.setBounds(100, 20, 300, 30);
        lblSocio.setFont(new Font("Arial", Font.BOLD, 16));
        panel.add(lblSocio);

        JLabel lblRutAdmin = new JLabel("Rut: " + RutSocio);
        lblRutAdmin.setBounds(100, 40, 300, 30);
        lblRutAdmin.setFont(new Font("Arial", Font.BOLD, 16));
        panel.add(lblRutAdmin);


        //funcionalidades del Socio

        //boton crear Actividad
        JButton btnCrearAct = new JButton("Crear Actividad");
        btnCrearAct.setBounds(100, 100, 160, 30);
        btnCrearAct.setFocusPainted(false);
        btnCrearAct.setToolTipText("Crear Actividad");
        panel.add(btnCrearAct);

        //boton Inscribirse a una actividad
        JButton btnInscribirseAct = new JButton("Inscribirse a una Actividad");
        btnInscribirseAct.setBounds(100, 140, 160, 30);
        btnInscribirseAct.setFocusPainted(false);
        btnInscribirseAct.setToolTipText("Inscribirse a una Actividad");
        panel.add(btnInscribirseAct);

        //boton Mostrar Actividad
        JButton btnMostrarAct = new JButton("Mostrar Actividad");
        btnMostrarAct.setBounds(100, 180, 160, 30);
        btnMostrarAct.setFocusPainted(false);
        btnMostrarAct.setToolTipText("Mostrar Actividad");
        panel.add(btnMostrarAct);

        //boton Mostrar Instalaciones
        JButton btnMostrarInstalaciones= new JButton("Mostrar Instalaciones");
        btnMostrarInstalaciones.setBounds(300, 100, 160, 30);
        btnMostrarInstalaciones.setFocusPainted(false);
        btnMostrarInstalaciones.setToolTipText("Mostrar Instalaciones");
        btnMostrarInstalaciones.addActionListener(e -> {
            if (club.getInstalaciones().isEmpty()) {
                JOptionPane.showMessageDialog(this, "No hay instalaciones registradas.");
                return;
            }
            StringBuilder info = new StringBuilder(); // ← aquí lo creas

            for (int i = 0; i < club.getInstalaciones().size(); i++) {
                Instalacion inst = club.getInstalaciones().get(i);
                info.append("Instalación ").append(i + 1).append(": ").append(inst.getTipo()).append(" - ").append(inst.getDireccion()).append("\n");
            }
            JOptionPane.showMessageDialog(this, info.toString(), "Instalaciones", JOptionPane.INFORMATION_MESSAGE);
        });
        panel.add(btnMostrarInstalaciones);

        //boton Consultar por bloque horario
        JButton btnBloqueHorario = new JButton("informacion de bloque horario");
        btnBloqueHorario.setBounds(300, 140, 160, 30);
        btnBloqueHorario.setFocusPainted(false);
        btnBloqueHorario.setToolTipText("informacion de bloque horario");
        panel.add(btnBloqueHorario);

        //boton Cerrar Sesion
        JButton btnCerrarSesion = new JButton("Cerrar  sesion");
        btnCerrarSesion.setBounds(550, 225, 120, 25);
        btnCerrarSesion.setBackground(Color.PINK);
        btnCerrarSesion.setFocusPainted(false);
        btnCerrarSesion.addActionListener(e -> {
            int respuesta = JOptionPane.showConfirmDialog(
                    this,
                    "Estas seguro que deseas cerrar Sesion?",
                    "Confirmar cierre de sesion",
                    JOptionPane.YES_NO_OPTION
            );

            if(respuesta == JOptionPane.YES_OPTION){
                dispose();
                new VentanaLogin(club);
            }
        });
        panel.add(btnCerrarSesion);

        add(panel);
        setVisible(true);

    }
}
