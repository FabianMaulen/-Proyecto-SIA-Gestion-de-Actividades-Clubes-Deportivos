package ArchivosProyecto;

import Principal.*;
import java.io.*;
import java.util.*;

public class PersistenciaActividad {

    private static final String RUTA = "src/main/java/com/mycompany/proyectojava/ArchivosProyecto/actividades.csv";

    public static void guardarActividades(ClubDeportivo club) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(RUTA))) {
            for (Instalacion instalacion : club.getInstalaciones()) {
                bloqueHorario[][] matriz = instalacion.getPlanificacion();
                for (int dia = 0; dia < 7; dia++) {
                    for (int hora = 0; hora < 12; hora++) {
                        bloqueHorario bloque = matriz[dia][hora];
                        Actividad actividad = bloque.getInfoActividad();
                        if (actividad != null) {
                            String linea = instalacion.getTipo() + "," +
                                    actividad.getDia() + "," +
                                    actividad.getHoraInicio() + "," +
                                    actividad.getHoraFin() + "," +
                                    actividad.getDescripcion();
                            writer.write(linea);
                            writer.newLine();
                        }
                    }
                }
            }
        } catch (IOException e) {
            System.out.println("Error al guardar actividades: " + e.getMessage());
        }
    }

    public static void cargarActividades(ClubDeportivo club) {
        try (BufferedReader reader = new BufferedReader(new FileReader(RUTA))) {
            String linea;
            while ((linea = reader.readLine()) != null) {
                String[] partes = linea.split(",");
                if (partes.length == 5) {
                    String tipoInstalacion = partes[0];
                    int dia = Integer.parseInt(partes[1]);
                    int horaInicio = Integer.parseInt(partes[2]);
                    int horaFin = Integer.parseInt(partes[3]);
                    String descripcion = partes[4];

                    Instalacion instalacion = club.buscarInstalacionPorTipo(tipoInstalacion);
                    if (instalacion != null) {
                        int h = horaInicio - 8;
                        if (dia >= 1 && dia <= 7 && h >= 0 && h < 12) {
                            bloqueHorario bloque = instalacion.getPlanificacion()[dia - 1][h];
                            Actividad actividad = new Actividad();
                            actividad.setDia(dia);
                            actividad.setHoraInicio(horaInicio);
                            actividad.setHoraFin(horaFin);
                            actividad.setDescripcion(descripcion);
                            bloque.setInfoActividad(actividad);
                        }
                    }
                }
            }
        } catch (IOException e) {
            System.out.println("Error al cargar actividades: " + e.getMessage());
        }
    }

    public static void eliminarActividad(Actividad actividadAEliminar, ClubDeportivo club) {
        ArrayList<String> lineasFiltradas = new ArrayList<>();

        for (Instalacion inst : club.getInstalaciones()) {
            bloqueHorario[][] matriz = inst.getPlanificacion();
            for (int d = 0; d < 7; d++) {
                for (int h = 0; h < 12; h++) {
                    Actividad act = matriz[d][h].getInfoActividad();
                    if (act != null && !act.equals(actividadAEliminar)) {
                        String linea = inst.getTipo() + "," +
                                act.getDia() + "," +
                                act.getHoraInicio() + "," +
                                act.getHoraFin() + "," +
                                act.getDescripcion();
                        lineasFiltradas.add(linea);
                    }
                }
            }
        }

        try (BufferedWriter writer = new BufferedWriter(new FileWriter(RUTA))) {
            for (String linea : lineasFiltradas) {
                writer.write(linea);
                writer.newLine();
            }
        } catch (IOException e) {
            System.out.println("Error al eliminar actividad: " + e.getMessage());
        }
    }
}






