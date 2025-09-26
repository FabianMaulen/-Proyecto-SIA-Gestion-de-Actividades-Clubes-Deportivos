package Principal;
import ArchivosProyecto.*;
import javax.swing.*;
import java.awt.*;

public class VentanaCrearInstalacion extends JFrame {
    private ClubDeportivo club;

    private JTextField txtTipo;
    private JSpinner spnCapacidad;
    private JTextField txtDireccion;
    private JTextArea txtAreaInfo;

    public VentanaCrearInstalacion(ClubDeportivo club) {
        this.club = club;

        setTitle("Crear Instalacion");
        setSize(500, 500);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        JPanel panel = new JPanel();
        panel.setLayout(null);

        //Titulo
        JLabel lblTitulo = new JLabel("Crear nueva Instalacion");
        lblTitulo.setBounds(150, 20, 200, 30);
        lblTitulo.setFont(new Font("Arial", Font.BOLD, 16));
        panel.add(lblTitulo);

        //Tipo
        JLabel lblTipo = new JLabel("Tipo de instalacion ");
        lblTipo.setBounds(50, 100, 200,25);
        panel.add(lblTipo);

        txtTipo = new JTextField();
        txtTipo.setBounds(50,125,300,25);
        panel.add(txtTipo);

        //Capacidad
        JLabel lblCapacidad = new JLabel("Capacidad de la instalacion ");
        lblCapacidad.setBounds(50,160,200,25);
        panel.add(lblCapacidad);

        spnCapacidad = new JSpinner(new SpinnerNumberModel(1,1,1000,1));
        spnCapacidad.setBounds(50,180,75,25);
        panel.add(spnCapacidad);

        //Direccion
        JLabel lblDireccion = new JLabel("Direccion de la nueva instalacion ");
        lblDireccion.setBounds(50,220, 200, 25);
        panel.add(lblDireccion);

        txtDireccion = new JTextField();
        txtDireccion.setBounds(50, 245, 300, 25);
        panel.add(txtDireccion);

        //Area mensajes
        txtAreaInfo = new JTextArea();
        txtAreaInfo.setEditable(false);
        txtAreaInfo.setBackground(Color.LIGHT_GRAY);
        JScrollPane scrollInfo = new JScrollPane(txtAreaInfo);
        scrollInfo.setBounds(50, 300, 380, 90);
        panel.add(scrollInfo);

        //boton crearInstalacion
        JButton btnCrearIns = new JButton("Crear Instalacion");
        btnCrearIns.setBounds(50, 400, 130, 30);
        btnCrearIns.addActionListener( e -> crearInstalacion());
        panel.add(btnCrearIns);

        //boton cerrar ventana
        JButton btnCerrar = new JButton("Cerrar");
        btnCerrar.setBounds(195, 400, 80, 30);
        btnCerrar.addActionListener(e -> dispose());
        panel.add(btnCerrar);

        add(panel);
        setVisible(true);
    }

    private void crearInstalacion() {
        txtAreaInfo.setText("");

        String tipo = txtTipo.getText();
        String direccion = txtDireccion.getText();
        int capacidad = (int)spnCapacidad.getValue();

        if(tipo.isEmpty() || direccion.isEmpty()) {
            txtAreaInfo.append("ERROR: los campos no pueden estar vacios");
            return;
        }

        Instalacion instalacion = new Instalacion();
        instalacion.setCapacidad(capacidad);
        instalacion.setTipo(tipo);
        instalacion.setDireccion(direccion);

        club.agregarInstalacion(instalacion);
        PersistenciaClub.guardarClub(club);

        txtAreaInfo.append("✓ Instalacion creada con exito!: \n");
        txtAreaInfo.append("   - Tipo: " + tipo + "\n");
        txtAreaInfo.append("   - Direccion: " + direccion + "\n");
        txtAreaInfo.append("   - Capacidad: " + capacidad + "\n");

        //persistencia de instalaciones

        limpiarCampos();
    }

    private void limpiarCampos() {
        txtTipo.setText("");
        txtDireccion.setText("");
        spnCapacidad.setValue(1);
    }
}
