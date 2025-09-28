package Ventanas;
import ArchivosProyecto.Reporte;
import Principal.*;

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
        btnCrearAct.setToolTipText("Crear Actividad");
        btnCrearAct.addActionListener(e -> {
            new VentanaCrearActividad(club);
        });
        panel.add(btnCrearAct);

        //boton crear nueva instalacion
        JButton btnCrearInstalacion = new JButton("Crear Instalacion");
        btnCrearInstalacion.setBounds(100, 140, 160, 30);
        btnCrearInstalacion.setFocusPainted(false);
        btnCrearInstalacion.setToolTipText("Crear Instalacion");
        btnCrearInstalacion.addActionListener(e -> {
            new VentanaCrearInstalacion(club);
        });
        panel.add(btnCrearInstalacion);

        //boton mostrar Socios
        JButton btnMostrarSocios = new JButton("Mostrar Socios");
        btnMostrarSocios.setBounds(100, 180, 160, 30);
        btnMostrarSocios.setFocusPainted(false);
        btnMostrarSocios.setToolTipText("Mostrar Socios");
        btnMostrarSocios.addActionListener(e -> {
            if (club.getSociosPorRut().isEmpty()) {
                JOptionPane.showMessageDialog(this, "No hay socios registrados.");
                return;
            }

            StringBuilder info = new StringBuilder();
            int contador = 1;

            for (Socio socio : club.getSociosPorRut().values()) {  //--> bucle for-each para recorrer el hashMap de socios
                info.append("Socio ").append(contador++).append(": ").append(socio.getNombre()).append(" - Rut: ").append(socio.getRut()).append("\n");
            }
            JOptionPane.showMessageDialog(this, info.toString(), "Socios registrados", JOptionPane.INFORMATION_MESSAGE);
        });
        panel.add(btnMostrarSocios);

        //boton mostrar actividades
        JButton btnMostrarActividad = new JButton("Mostrar Actividad");
        btnMostrarActividad.setBounds(300, 100, 160, 30);
        btnMostrarActividad.setFocusPainted(false);
        btnMostrarActividad.setToolTipText("Mostrar Actividad");
        btnMostrarActividad.addActionListener(e -> {
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
                            info.append("   - Instalación: ").append(inst.getTipo()).append("\n");int diaAct = act.getDia();
                            int diaActividad = act.getDia();
                            switch(diaActividad) {
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
        panel.add(btnMostrarActividad);

        //boton mostrar Inslataciones
        JButton btnMostrarInstalaciones = new JButton("Mostrar Instalaciones");
        btnMostrarInstalaciones.setBounds(300, 140, 160, 30);
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

        //boton generar reporte
        JButton btnGenerarReporte = new JButton("Generar Reporte del Club");
        btnGenerarReporte.setBounds(300, 180, 160, 30); // Puedes ajustar posición y tamaño
        btnGenerarReporte.setFocusPainted(false);
        btnGenerarReporte.setToolTipText("Generar Reporte del Club");

        btnGenerarReporte.addActionListener(e -> {
            String ruta = "src/main/java/com/mycompany/proyectojava/ArchivosProyecto/ReporteClub.txt";

            try {
                Reporte.generarReporteClub(club, ruta);
                JOptionPane.showMessageDialog(this,
                        "Reporte generado correctamente.\nArchivo: " + ruta,
                        "Reporte del Club",
                        JOptionPane.INFORMATION_MESSAGE);
            } catch (Exception ex) {
                JOptionPane.showMessageDialog(this,
                        "Error al generar el reporte:\n" + ex.getMessage(),
                        "Error",
                        JOptionPane.ERROR_MESSAGE);
            }
        });
        panel.add(btnGenerarReporte);

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
        setVisible(true); //--> necesario para que la ventana sea visible
    }
}