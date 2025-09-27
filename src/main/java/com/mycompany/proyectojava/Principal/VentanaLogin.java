package Principal;
import Excepciones.ExcepcionRutInvalido;
import javax.swing.*;

public class VentanaLogin extends JFrame {

    private JTextField txtUsuario;
    private Admin admin;
    private ClubDeportivo club;

    public VentanaLogin(ClubDeportivo club) {
        this.club = club;
        this.admin = club.getAdministrador();

        setTitle("Gestion de Clubes Deportivos");
        setSize(300, 150);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        JPanel panel = new JPanel();
        panel.setLayout(null);

        // Etiqueta "Usuario"
        JLabel lblUsuario = new JLabel("Rut Usuario: ");
        lblUsuario.setBounds(20, 20, 80, 25);
        panel.add(lblUsuario); // Agregamos la etiqueta al panel

        // Casilla de texto para escribir el usuario
        txtUsuario = new JTextField();
        txtUsuario.setBounds(100, 20, 150, 25);
        panel.add(txtUsuario);

        // Botón "Ingresar"
        JButton btnLogin = new JButton("Ingresar");
        btnLogin.setBounds(35,70,100,30);// Ubicación y tamaño
        btnLogin.setFocusPainted(false);

        // Acción cuando se hace clic en el botón
        btnLogin.addActionListener(e -> {
            String rutIngresado = txtUsuario.getText().trim();

            try {
                // Validación básica de formato
                if (!rutIngresado.matches("\\d{2}\\.\\d{3}\\.\\d{3}-\\d")) {
                    throw new ExcepcionRutInvalido("Formato de RUT inválido. Ejemplo: 12.345.678-9");
                }

                if (admin.isAdmin(rutIngresado)) {
                    JOptionPane.showMessageDialog(null, "Bienvenido " + admin.getNombre());
                    new VentanaAdmin(club);
                    dispose();
                } else if (club.buscarSocioPorRut(rutIngresado) != null) {
                    Socio socio = club.buscarSocioPorRut(rutIngresado);
                    JOptionPane.showMessageDialog(null, "Bienvenido " + socio.getNombre());
                    new VentanaSocio(socio, club);
                    dispose();
                } else {
                    JOptionPane.showMessageDialog(this, "Usuario Inexistente", "Error de Acceso", JOptionPane.ERROR_MESSAGE);
                }

            } catch (ExcepcionRutInvalido ex) {
                JOptionPane.showMessageDialog(this, ex.getMessage(), "Error de RUT", JOptionPane.ERROR_MESSAGE);
            }
        });
        // Agregar el botón al panel
        panel.add(btnLogin);

        //boton registrar Usuario
        JButton btnRegistrar = new JButton("Registrar Socio");
        btnRegistrar.setBounds(150,70,100,30);
        btnRegistrar.setFocusPainted(false);
        btnRegistrar.setToolTipText("Registrar Socio");
        btnRegistrar.addActionListener(e -> {
            new VentanaRegistrarSocio(club);
        });
        panel.add(btnRegistrar);

        // Agregar el panel a la ventana
        add(panel);

        // Mostrar la ventana (si no pones esto, la ventana no aparece)
        setVisible(true);
    }
}