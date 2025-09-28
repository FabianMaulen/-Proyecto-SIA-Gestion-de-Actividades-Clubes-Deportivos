package Ventanas;

import ArchivosProyecto.*;
import Principal.ClubDeportivo;
import Principal.Socio;

import javax.swing.*;
import java.awt.*;

public class VentanaRegistrarSocio extends JFrame {
    private ClubDeportivo club;

    private JTextField txtNombreSocio;
    private JTextField txtRut;
    private JTextField txtCorreo;
    private JSpinner spnEdad;
    private JTextArea txtAreaInfo;

    public VentanaRegistrarSocio(ClubDeportivo club) {
        this.club = club;

        setTitle("Registrar Nuevo Socio");
        setSize(500, 530);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        JPanel panel = new JPanel();
        panel.setLayout(null);

        //Titulo
        JLabel lblTitulo = new JLabel("Registro");
        lblTitulo.setBounds(210, 20, 200, 30);
        lblTitulo.setFont(new Font("Arial", Font.BOLD, 18));
        panel.add(lblTitulo);

        //nombre del socio
        JLabel lblNombreSocio = new JLabel("Nombre y apellido");
        lblNombreSocio.setBounds(150, 60, 200, 30);
        panel.add(lblNombreSocio);

        txtNombreSocio = new JTextField();
        txtNombreSocio.setBounds(150, 85, 200, 30);
        panel.add(txtNombreSocio);

        //Rut del socio
        JLabel lblRut = new JLabel("Rut");
        lblRut.setBounds(150, 125, 200, 30);
        panel.add(lblRut);

        txtRut = new JTextField();
        txtRut.setBounds(150, 150, 200, 30);
        panel.add(txtRut);

        //Correo del socio
        JLabel lblCorreo = new JLabel("Correo");
        lblCorreo.setBounds(150, 190, 200, 30);
        panel.add(lblCorreo);

        txtCorreo = new JTextField();
        txtCorreo.setBounds(150, 215, 200, 30);
        panel.add(txtCorreo);

        //edad del socio
        JLabel lblEdad = new JLabel("Edad");
        lblEdad.setBounds(150, 250, 200, 30);
        panel.add(lblEdad);

        spnEdad = new JSpinner(new SpinnerNumberModel(18, 1, 120, 1));
        spnEdad.setBounds(150, 275, 75, 30);
        panel.add(spnEdad);

        // Área para mostrar mensajes y resultados
        txtAreaInfo = new JTextArea();
        txtAreaInfo.setEditable(false);
        txtAreaInfo.setBackground(Color.LIGHT_GRAY);
        JScrollPane scrollInfo = new JScrollPane(txtAreaInfo);
        scrollInfo.setBounds(50, 335, 380, 90);
        panel.add(scrollInfo);

        //boton Registrar Socio
        JButton btnRegistrarSocio = new JButton("Registrar Socio");
        btnRegistrarSocio.setBounds(100,435,125,30);
        btnRegistrarSocio.addActionListener(e -> RegistrarSocio());
        panel.add(btnRegistrarSocio);

        //boton cerrar
        JButton btnCerrar = new JButton("Cerrar");
        btnCerrar.setBounds(235,435,120,30);
        btnCerrar.addActionListener(e -> dispose());
        panel.add(btnCerrar);

        add(panel);
        setVisible(true);
    }

    public void RegistrarSocio() {
        txtAreaInfo.setText("");

        String NombreSocio = txtNombreSocio.getText();
        String Rut = txtRut.getText();
        String Correo = txtCorreo.getText();
        String edad = String.valueOf(spnEdad.getValue());

        if (NombreSocio.isEmpty() || Rut.isEmpty() || Correo.isEmpty() || edad.isEmpty()) {
            txtAreaInfo.append("ERROR: los campos no pueden estar vacíos\n");
            return;
        }

        Socio socio = new Socio();
        socio.setNombre(NombreSocio);
        socio.setRut(Rut);
        socio.setEmail(Correo);
        socio.setEdad(edad);

        club.agregarSocio(socio);
        PersistenciaSocio.guardarSocio(socio);

        txtAreaInfo.append("✓ Socio registrado con exito!: \n");
        txtAreaInfo.append("   - Nombre: " + NombreSocio + "\n");
        txtAreaInfo.append("   - Rut: " + Rut + "\n");
        txtAreaInfo.append("   - Correo: " + Correo + "\n");
        txtAreaInfo.append("   - Edad: " + edad + "\n");

        limpiarCampos();
    }

    private void limpiarCampos() {
        txtNombreSocio.setText("");
        txtCorreo.setText("");
        txtRut.setText("");
        spnEdad.setValue(0);
    }
}