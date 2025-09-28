package Ventanas;

import ArchivosProyecto.PersistenciaSocio;
import Principal.*;
import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;

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

        //etiqueta Principal.Socio
        JLabel lblSocio = new JLabel("Socio: " + socio.getNombre());
        lblSocio.setBounds(100, 20, 300, 30);
        lblSocio.setFont(new Font("Arial", Font.BOLD, 16));
        panel.add(lblSocio);

        JLabel lblRutAdmin = new JLabel("Rut: " + RutSocio);
        lblRutAdmin.setBounds(100, 40, 300, 30);
        lblRutAdmin.setFont(new Font("Arial", Font.BOLD, 16));
        panel.add(lblRutAdmin);


        //funcionalidades del Socio

        //Boton Modificar Datos
        JButton btnModificarDatos = new JButton("Modificar Mis Datos");
        btnModificarDatos.setBounds(300, 140, 160, 30);
        btnModificarDatos.setToolTipText("Editar nombre, correo y edad");
        btnModificarDatos.addActionListener(e -> {
            JTextField txtNombre = new JTextField(socio.getNombre());
            JTextField txtCorreo = new JTextField(socio.getEmail());
            JTextField txtEdad = new JTextField(socio.getEdad());

            JPanel panelMod = new JPanel(new GridLayout(0, 1));
            panelMod.add(new JLabel("Nombre:"));
            panelMod.add(txtNombre);
            panelMod.add(new JLabel("Correo:"));
            panelMod.add(txtCorreo);
            panelMod.add(new JLabel("Edad:"));
            panelMod.add(txtEdad);

            int result = JOptionPane.showConfirmDialog(this, panelMod, "Modificar Mis Datos", JOptionPane.OK_CANCEL_OPTION);

            if (result == JOptionPane.OK_OPTION) {
                socio.setNombre(txtNombre.getText().trim());
                socio.setEmail(txtCorreo.getText().trim());
                socio.setEdad(txtEdad.getText().trim());

                club.getSociosPorRut().put(socio.getRut(), socio);
                PersistenciaSocio.guardarTodos(new ArrayList<>(club.getSociosPorRut().values()));

                JOptionPane.showMessageDialog(this, " Tus datos fueron modificados correctamente.");
            }
        });
        panel.add(btnModificarDatos);

        //boton crear Actividad
        JButton btnCrearAct = new JButton("Crear Actividad");
        btnCrearAct.setBounds(100, 100, 160, 30);
        btnCrearAct.setFocusPainted(false);
        btnCrearAct.setToolTipText("Crear Actividad");
        btnCrearAct.addActionListener(e -> {
            new VentanaCrearActividad(club);
        });
        panel.add(btnCrearAct);

        //boton Inscribirse a una actividad
        JButton btnInscribirseAct = new JButton("Inscribirse a una Actividad");
        btnInscribirseAct.setBounds(100, 140, 160, 30);
        btnInscribirseAct.setFocusPainted(false);
        btnInscribirseAct.setToolTipText("Inscribirse a una Actividad");
        btnInscribirseAct.addActionListener(e -> {
            ArrayList<Actividad> actividadesDisponibles = new ArrayList<>();
            ArrayList<String> opciones = new ArrayList<>();

            for (Instalacion inst : club.getInstalaciones()) {
                bloqueHorario[][] planificacion = inst.getPlanificacion();

                for (int dia = 0; dia < 7; dia++) {
                    for (int hora = 0; hora < 12; hora++) {
                        bloqueHorario bloque = planificacion[dia][hora];
                        Actividad act = bloque.getInfoActividad();

                        if (act != null && hora == act.getHoraInicio() - 8) {
                            actividadesDisponibles.add(act);
                            String nombreDia;
                            switch (act.getDia()) {
                                case 1:
                                    nombreDia = "Lunes";
                                    break;
                                case 2:
                                    nombreDia = "Martes";
                                    break;
                                case 3:
                                    nombreDia = "Miércoles";
                                    break;
                                case 4:
                                    nombreDia = "Jueves";
                                    break;
                                case 5:
                                    nombreDia = "Viernes";
                                    break;
                                case 6:
                                    nombreDia = "Sábado";
                                    break;
                                case 7:
                                    nombreDia = "Domingo";
                                    break;
                                default:
                                    nombreDia = "Desconocido";
                                    break;
                            }
                            opciones.add("[" + inst.getTipo() + "] " + act.getDescripcion() +
                                    " - " + nombreDia + " de " + act.getHoraInicio() + ":00 a " + act.getHoraFin() + ":00");
                        }
                    }
                }
            }

            if (actividadesDisponibles.isEmpty()) {
                JOptionPane.showMessageDialog(this, "No hay actividades disponibles.");
                return;
            }

            String seleccion = (String) JOptionPane.showInputDialog(
                    this,
                    "Seleccione una actividad:",
                    "Inscripción",
                    JOptionPane.PLAIN_MESSAGE,
                    null,
                    opciones.toArray(),
                    opciones.get(0)
            );

            if (seleccion != null) {
                int index = opciones.indexOf(seleccion);
                Actividad actividadSeleccionada = actividadesDisponibles.get(index);

                // Buscar instalación y asignar socio
                for (Instalacion inst : club.getInstalaciones()) {
                    bloqueHorario[][] planificacion = inst.getPlanificacion();
                    for (int h = actividadSeleccionada.getHoraInicio(); h < actividadSeleccionada.getHoraFin(); h++) {
                        bloqueHorario bloque = planificacion[actividadSeleccionada.getDia() - 1][h - 8];
                        if (bloque.getInfoActividad() == actividadSeleccionada) {
                            if (!bloque.getSociosAsistentes().contains(Socio)) {
                                bloque.getSociosAsistentes().add(Socio);
                                JOptionPane.showMessageDialog(this, "Te has inscrito correctamente en la actividad.");
                            } else {
                                JOptionPane.showMessageDialog(this, "Ya estás inscrito en esta actividad.");
                            }
                        }
                    }
                }
            }
        });
        panel.add(btnInscribirseAct);

        //boton Mostrar Actividad
        JButton btnMostrarAct = new JButton("Mostrar Actividad");
        btnMostrarAct.setBounds(100, 180, 160, 30);
        btnMostrarAct.setFocusPainted(false);
        btnMostrarAct.setToolTipText("Mostrar Actividad");
        btnMostrarAct.addActionListener(e -> {
            StringBuilder info = new StringBuilder();
            int contador = 1;

            for(Instalacion inst : club.getInstalaciones()) {
                bloqueHorario[][] planificacion =  inst.getPlanificacion();

                for(int dia = 0 ; dia < planificacion.length ; dia++) {
                    for(int hora = 0 ; hora < planificacion[0].length ; hora++) {
                        bloqueHorario bloque = planificacion[dia][hora];
                        Actividad act = bloque.getInfoActividad();

                        if(act != null && hora == act.getHoraInicio() - 8) {
                            info.append("Actividad ").append(contador++).append(":\n");
                            info.append("   - Instalación: ").append(inst.getTipo()).append("\n");
                            int diaAct = act.getDia();
                            switch(diaAct) {
                                case 1:
                                    info.append("   - Dia: Lunes").append("\n");
                                    break;
                                case 2:
                                    info.append("   - Dia: Martes").append("\n");
                                    break;
                                case 3:
                                    info.append("   - Dia: Miercoles").append("\n");
                                    break;
                                case 4:
                                    info.append("   - Dia: Jueves").append("\n");
                                    break;
                                case 5:
                                    info.append("   - Dia: Viernes").append("\n");
                                    break;
                                case 6:
                                    info.append("   - Dia: Sabado").append("\n");
                                    break;
                                case 7:
                                    info.append("   - Dia: Domingo").append("\n");
                                    break;
                            }
                            info.append("   - Horario: ").append(act.getHoraInicio()).append(":00 a ").append(act.getHoraFin()).append(":00\n");
                            info.append("   - Descripción: ").append(act.getDescripcion()).append("\n\n");
                        }
                    }
                }
            }

            if(info.length() == 0) {
                JOptionPane.showMessageDialog(this, "No hay actividades registradas.");
            } else {
                JOptionPane.showMessageDialog(this, info.toString(), "Actividades registradas", JOptionPane.INFORMATION_MESSAGE);
            }
        });
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
            StringBuilder info = new StringBuilder();

            for (int i = 0; i < club.getInstalaciones().size(); i++) {
                Instalacion inst = club.getInstalaciones().get(i);
                info.append("Instalación ").append(i + 1).append(": ").append(inst.getTipo()).append(" - ").append(inst.getDireccion()).append("\n");
            }
            JOptionPane.showMessageDialog(this, info.toString(), "Instalaciones", JOptionPane.INFORMATION_MESSAGE);
        });
        panel.add(btnMostrarInstalaciones);


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
