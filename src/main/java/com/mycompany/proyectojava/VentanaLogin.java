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
        btnLogin.setBounds(100,70,100,30); // Ubicación y tamaño

        // Acción cuando se hace clic en el botón
        btnLogin.addActionListener(e -> {
            // Obtener lo que el usuario escribió en las casillas
            String rutIngresado = txtUsuario.getText();

            // Validar con los datos del administrador
            if(admin.isAdmin(rutIngresado)) {
                JOptionPane.showMessageDialog(null, "Bienvenido " + admin.getNombre());
                new VentanaAdmin(club);
                dispose();
            } else {
                // Si no coincide → error
                JOptionPane.showMessageDialog(
                        this,
                        "Usuario Incorrecto ",
                        "Error de Acceso",
                        JOptionPane.ERROR_MESSAGE
                );
            }
        });

        // Agregar el botón al panel
        panel.add(btnLogin);

        // Agregar el panel a la ventana
        add(panel);

        // Mostrar la ventana (si no pones esto, la ventana no aparece)
        setVisible(true);
    }
}