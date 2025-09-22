import javax.swing.*;
import java.awt.*;

public class VentanaAdmin extends JFrame {
    private Admin admin;
    private ClubDeportivo club;
    private String RutAdmin;


    public VentanaAdmin(ClubDeportivo club) {
        this.club = club;
        this.admin = club.getAdministrador();
        this.RutAdmin = admin.getRut();

        setTitle("Panel Administrador");
        setSize(700, 300);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        //creacion del panel
        JPanel panel = new JPanel();
        panel.setLayout(null);

        //etiqueta Administrador
        JLabel lblAdmin = new JLabel("Administrador: " + admin.getNombre());
        lblAdmin.setBounds(100, 20, 300, 30);
        lblAdmin.setFont(new Font("Arial", Font.BOLD, 16));
        panel.add(lblAdmin);

        JLabel lblRutAdmin = new JLabel("Rut: " + RutAdmin);
        lblRutAdmin.setBounds(100, 40, 300, 30);
        lblRutAdmin.setFont(new Font("Arial", Font.BOLD, 16));
        panel.add(lblRutAdmin);

        //funcionalidades del administrador

        //boton crear actividad
        JButton btnCrearAct = new JButton("Crear Actividad");
        btnCrearAct.setBounds(100, 100, 160, 30);
        btnCrearAct.setFocusPainted(false);
        panel.add(btnCrearAct);

        //boton crear nueva instalacion
        JButton btnCrearInstalacion = new JButton("Crear Instalacion");
        btnCrearInstalacion.setBounds(100, 140, 160, 30);
        btnCrearInstalacion.setFocusPainted(false);
        panel.add(btnCrearInstalacion);

        //boton mostrar Socios
        JButton btnMostrarSocios = new JButton("Mostrar Socios");
        btnMostrarSocios.setBounds(100, 180, 160, 30);
        btnMostrarSocios.setFocusPainted(false);
        panel.add(btnMostrarSocios);

        //boton mostrar actividades
        JButton btnMostrarActividad = new JButton("Mostrar Actividad");
        btnMostrarActividad.setBounds(300, 100, 160, 30);
        btnMostrarActividad.setFocusPainted(false);
        panel.add(btnMostrarActividad);

        //boton mostrar Inslataciones
        JButton btnMostrarInstalaciones = new JButton("Mostrar Instalaciones");
        btnMostrarInstalaciones.setBounds(300, 140, 160, 30);
        btnMostrarInstalaciones.setFocusPainted(false);
        panel.add(btnMostrarInstalaciones);

        //boton Cerrar Sesion
        JButton btnCerrarSesion = new JButton("Cerrar  sesion");
        btnCerrarSesion.setBounds(550, 225, 120, 25);
        btnCerrarSesion.setBackground(Color.PINK);
        btnCerrarSesion.setFocusPainted(false);
        panel.add(btnCerrarSesion);

        add(panel);
        setVisible(true); //--> necesario para que la ventana sea visible
    }
}