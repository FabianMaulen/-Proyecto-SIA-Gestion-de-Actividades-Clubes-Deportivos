package Ventanas;
import ArchivosProyecto.*;
import Principal.Actividad;
import Principal.ClubDeportivo;
import Principal.Instalacion;
import Principal.bloqueHorario;

import javax.swing.*;
import java.awt.*;

public class VentanaCrearActividad extends JFrame {
    private ClubDeportivo club;

    private JTextField txtDescripcion;
    private JTextField txtDia;
    private JTextField txtHoraInicio;
    private JTextField txtHoraFin;
    private JTextArea txtAreaInfo;

    public VentanaCrearActividad(ClubDeportivo club) {
        this.club = club;

        setTitle("Crear Actividad");
        setSize(500, 645);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        JPanel panel = new JPanel();
        panel.setLayout(null);

        //Titulo
        JLabel lblTitulo = new JLabel("Crear Nueva Actividad");
        lblTitulo.setBounds(150, 20, 200, 30);
        lblTitulo.setFont(new Font("Arial", Font.BOLD, 16));
        panel.add(lblTitulo);

        //Descripcion
        JLabel lblDescripcion = new JLabel("Descripcion de la actividad");
        lblDescripcion.setBounds(50, 100, 200, 25);
        panel.add(lblDescripcion);

        txtDescripcion = new JTextField();
        txtDescripcion.setBounds(50,125,300,25);
        panel.add(txtDescripcion);

        //Dia
        JLabel lblDia = new JLabel("Dia (1 = Lunes a 7 = Domingo)");
        lblDia.setBounds(50,160,200,25);
        panel.add(lblDia);

        txtDia = new JTextField();
        txtDia.setBounds(50, 185, 100, 25);
        panel.add(txtDia);

        //Hora Inicio
        JLabel lblHoraInicio = new JLabel("Hora de inicio (8 a 19):");
        lblHoraInicio.setBounds(50,220, 200, 25);
        panel.add(lblHoraInicio);

        txtHoraInicio = new JTextField();
        txtHoraInicio.setBounds(50, 245, 100, 25);
        panel.add(txtHoraInicio);

        //Hora Fin
        JLabel lblHoraFin = new JLabel("Hora de termino (maximo 20):");
        lblHoraFin.setBounds(50,280,200,25);
        panel.add(lblHoraFin);

        txtHoraFin = new JTextField();
        txtHoraFin.setBounds(50,305,100,25);
        panel.add(txtHoraFin);


        //comboBox para seleccionar instalacion
        JLabel lblInstalacion = new JLabel("Elija una instalacion");
        lblInstalacion.setBounds(50,340,200,25);
        panel.add(lblInstalacion);

        JComboBox<String> cboInstalaciones = new JComboBox<>();
        cboInstalaciones.setBounds(50,365,300,30);
        panel.add(cboInstalaciones);

        // Área para mostrar mensajes y resultados
        JLabel lblMensajes = new JLabel("Mensajes:");
        lblMensajes.setBounds(50, 405, 200, 25);
        panel.add(lblMensajes);

        txtAreaInfo = new JTextArea();
        txtAreaInfo.setEditable(false);
        txtAreaInfo.setBackground(Color.LIGHT_GRAY);
        JScrollPane scrollInfo = new JScrollPane(txtAreaInfo);
        scrollInfo.setBounds(50, 430, 380, 90);
        panel.add(scrollInfo);

        //boton crear Actividad
        JButton btnCrearActividad = new JButton("Crear Actividad");
        btnCrearActividad.setBounds(50,530,130,30);
        btnCrearActividad.addActionListener(e -> crearActividad(cboInstalaciones));
        panel.add(btnCrearActividad);

        //boton cerrar
        JButton btnCerrar = new JButton("Cerrar");
        btnCerrar.setBounds(220,530,80,30);
        btnCerrar.addActionListener(e -> dispose());
        panel.add(btnCerrar);

        add(panel);
        cargarInstalaciones(cboInstalaciones);
        setVisible(true);
    }

    private void cargarInstalaciones(JComboBox<String> cboInstalaciones) {
        cboInstalaciones.removeAllItems();

        if (club.getInstalaciones().isEmpty()) {
            cboInstalaciones.addItem("No hay instalaciones registradas");
            txtAreaInfo.setText("No hay instalaciones registradas.\n");
            return;
        }

        txtAreaInfo.setText("✓ Instalaciones cargadas correctamente.\n");

        for (int i = 0; i < club.getInstalaciones().size(); i++) {
            Instalacion inst = club.getInstalaciones().get(i);
            String item = (i + 1) + " - " + inst.getTipo() + " (" + inst.getDireccion() + ")";
            cboInstalaciones.addItem(item);
        }
    }

    private void crearActividad(JComboBox<String> cboInstalaciones) {
        try {
            txtAreaInfo.setText("");

            // Obtener y validar datos de los campos
            String descripcion = txtDescripcion.getText().trim();
            if (descripcion.isEmpty()) {
                txtAreaInfo.append("La descripción no puede estar vacía.\n");
                return;
            }

            int dia = Integer.parseInt(txtDia.getText());
            if (dia < 1 || dia > 7) {
                txtAreaInfo.append("Día inválido. Debe ser entre 1 y 7.\n");
                return;
            }

            int horaInicio = Integer.parseInt(txtHoraInicio.getText());
            int horaFin = Integer.parseInt(txtHoraFin.getText());

            if (horaInicio < 8 || horaFin > 20 || horaFin <= horaInicio) {
                txtAreaInfo.append("Rango horario inválido.\n");
                txtAreaInfo.append("   - Hora inicio: 8-19\n");
                txtAreaInfo.append("   - Hora fin: 9-20\n");
                txtAreaInfo.append("   - Hora fin debe ser mayor a hora inicio\n");
                return;
            }

            // Validar selección de instalación
            int indiceSeleccionado = cboInstalaciones.getSelectedIndex();

            if (indiceSeleccionado < 0 || club.getInstalaciones().isEmpty()) {
                txtAreaInfo.append("Debe seleccionar una instalación válida.\n");
                return;
            }

            Instalacion inst = club.getInstalaciones().get(indiceSeleccionado);

            // Verificar disponibilidad
            boolean disponible = inst.estaDisponible(dia, horaInicio, horaFin);

            if (!disponible) {
                txtAreaInfo.append("Error: El horario seleccionado no esta disponible!.\n");
                return;
            } else {

                // Crear actividad
                Actividad a = new Actividad();
                a.setDescripcion(descripcion);
                a.setDia(dia);
                a.setHoraInicio(horaInicio);
                a.setHoraFin(horaFin);

                for (Instalacion instal : club.getInstalaciones()) {
                    if (instal.getTipo().equals("Cancha")) {
                        for (int h = a.getHoraInicio(); h < a.getHoraFin(); h++) {
                            bloqueHorario b = inst.getPlanificacion()[a.getDia() - 1][h - 8];
                            b.setDisponibilidad(false);
                            b.setInfoActividad(a);
                        }
                    }
                }

                // Asignar actividad a bloques
                for (int h = horaInicio; h < horaFin; h++) {
                    bloqueHorario b = inst.getPlanificacion()[dia - 1][h - 8];
                    b.setDescripcion(a);
                    b.setDisponibilidad(false);
                    b.setInfoActividad(a);
                    txtAreaInfo.append("Bloque asignado: Día " + dia +
                            ", Hora " + h + ":00 → " + descripcion + "\n");
                }

                PersistenciaActividad.guardarActividades(club);
                txtAreaInfo.append("\nResumen de la actividad:\n" + a.toString() + "\n");
                txtAreaInfo.append("\n¡ACTIVIDAD CREADA Y ASIGNADA CORRECTAMENTE!\n");

                txtAreaInfo.append("Actividad creada correctamente.\n\n");
            }

            // Limpiar campos
            limpiarCampos();

        } catch (NumberFormatException ex) {
            txtAreaInfo.append("Error: Ingrese números válidos en los campos numéricos.\n");
        } catch (Exception ex) {
            txtAreaInfo.append("Error inesperado: " + ex.getMessage() + "\n");
        }
    }

    private void limpiarCampos() {
        txtDescripcion.setText("");
        txtDia.setText("");
        txtHoraInicio.setText("");
        txtHoraFin.setText("");
    }
}
