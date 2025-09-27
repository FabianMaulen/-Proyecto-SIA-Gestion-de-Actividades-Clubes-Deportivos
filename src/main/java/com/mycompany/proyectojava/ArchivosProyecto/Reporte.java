package ArchivosProyecto;
import Principal.*;

import java.io.*;

public class Reporte {

    public static void generarReporteClub(ClubDeportivo club, String rutaArchivo) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(rutaArchivo))) {

            writer.write("===== REPORTE DEL CLUB DEPORTIVO =====\n");
            writer.write("Fecha de generación: " + java.time.LocalDateTime.now() + "\n\n");

            // --- Administrador ---
            writer.write("ADMINISTRADOR:\n");
            Admin admin = club.getAdministrador();
            writer.write("Nombre: " + admin.getNombre() + "\n");
            writer.write("RUT: " + admin.getRut() + "\n");
            writer.write("Correo: " + admin.getCorreo() + "\n\n");

            // --- Socios ---
            writer.write("SOCIOS REGISTRADOS:\n");
            if (club.getSociosPorRut().isEmpty()) {
                writer.write("  No hay socios registrados.\n\n");
            } else {
                for (Socio s : club.getSociosPorRut().values()) {
                    writer.write("  - " + s.toString() + "\n");
                }
                writer.write("  Total: " + club.getSociosPorRut().size() + " socios\n\n");
            }

            // --- Instalaciones ---
            writer.write("INSTALACIONES:\n");
            if (club.getInstalaciones().isEmpty()) {
                writer.write("  No hay instalaciones registradas.\n\n");
            } else {
                for (Instalacion inst : club.getInstalaciones()) {
                    writer.write("  - " + inst.getTipo() + " en " + inst.getDireccion() + " (Capacidad: " + inst.getCapacidad() + ")\n");
                }
                writer.write("  Total: " + club.getInstalaciones().size() + " instalaciones\n\n");
            }

            // --- Actividades ---
            writer.write("ACTIVIDADES:\n");
            boolean hayActividades = false;

            for (Instalacion inst : club.getInstalaciones()) {
                bloqueHorario[][] matriz = inst.getPlanificacion();
                for (int d = 0; d < 7; d++) {
                    for (int h = 0; h < 12; h++) {
                        Actividad a = matriz[d][h].getInfoActividad();
                        if (a != null) {
                            hayActividades = true;
                            writer.write("  - " + a.toString() + " | Instalación: " + inst.getTipo() + "\n");
                        }
                    }
                }
            }

            if (!hayActividades) {
                writer.write("  No hay actividades registradas.\n");
            }

            writer.write("\n===== FIN DEL REPORTE =====\n");

            System.out.println("Reporte generado correctamente en: " + rutaArchivo);

        } catch (IOException e) {
            System.err.println(" Error al generar reporte: " + e.getMessage());
        }
    }

}
