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

        //boton Cerrar Sesion
        JButton btnCerrarSesion = new JButton("Cerrar  sesion");
        btnCerrarSesion.setBounds(550, 225, 120, 25);
        btnCerrarSesion.setBackground(Color.PINK);
        btnCerrarSesion.setFocusPainted(false);
        panel.add(btnCerrarSesion);

        add(panel);
        setVisible(true);

    }
}
