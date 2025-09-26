package ArchivosProyecto;
import java.io.*;
import java.util.*;
import Principal.*;


public class PersistenciaClub {
    private static final String RUTA_CLUB = "src/main/java/com/mycompany/proyectojava/ArchivosProyecto/club.csv";

    public static void guardarClub(ClubDeportivo club) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(RUTA_CLUB))) {
            for (Instalacion i : club.getInstalaciones()) {
                String linea = i.getTipo() + "," + i.getCapacidad() + "," + i.getDireccion();
                writer.write(linea);
                writer.newLine();
            }
        } catch (IOException e) {
            System.out.println("Error al guardar instalaciones: " + e.getMessage());
        }

        // Guardar actividades por separado
        PersistenciaActividad.guardarActividades(club);
    }

    public static void cargarClub(ClubDeportivo club) {
        ArrayList<Instalacion> instalaciones = new ArrayList<>();

        try (BufferedReader reader = new BufferedReader(new FileReader(RUTA_CLUB))) {
            String linea;
            while ((linea = reader.readLine()) != null) {
                String[] partes = linea.split(",");
                if (partes.length == 3) {
                    String tipo = partes[0];
                    int capacidad = Integer.parseInt(partes[1]);
                    String direccion = partes[2];

                    bloqueHorario[][] matriz = new bloqueHorario[7][12];
                    for (int d = 0; d < 7; d++) {
                        for (int h = 0; h < 12; h++) {
                            matriz[d][h] = new bloqueHorario();
                        }
                    }

                    Instalacion nueva = new Instalacion(tipo, capacidad, matriz, direccion);
                    instalaciones.add(nueva);
                }
            }
        } catch (IOException e) {
            System.out.println("Error al cargar instalaciones: " + e.getMessage());
        }

        club.setInstalaciones(instalaciones);

        PersistenciaActividad.cargarActividades(club);
    }


}
