package ArchivosProyecto;

import Principal.ClubDeportivo;
import Principal.Socio;
import Principal.bloqueHorario;
import Principal.Instalacion;


import java.io.*;
import java.util.ArrayList;

public class PersistenciaSocio {

    private static final String RUTA_SOCIOS = "src/main/java/com/mycompany/proyectojava/ArchivosProyecto/socios.csv";

    public static void guardarSocio(Socio socio) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(RUTA_SOCIOS, true))) {
            String linea = socio.getNombre() + "," +
                    socio.getEdad() + "," +
                    socio.getRut();
            writer.write(linea);
            writer.newLine();
        } catch (IOException e) {
            System.out.println("Error al guardar socio: " + e.getMessage());
        }
    }

    public static void guardarTodos(ArrayList<Socio> socios) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(RUTA_SOCIOS))) {
            for (Socio s : socios) {
                String linea = s.getNombre() + "," + s.getEdad() + "," + s.getRut();
                writer.write(linea);
                writer.newLine();
            }
        } catch (IOException e) {
            System.out.println("Error al guardar todos los socios: " + e.getMessage());
        }
    }

    public static void cargarSocios(ClubDeportivo club) {
        try (BufferedReader reader = new BufferedReader(new FileReader(RUTA_SOCIOS))) {
            String linea;
            while ((linea = reader.readLine()) != null) {
                String[] partes = linea.split(",");
                if (partes.length == 3) {
                    String nombre = partes[0];
                    String edad = partes[1];
                    String rut = partes[2];
                    club.agregarSocio(nombre, edad, rut);
                }
            }
        } catch (IOException e) {
            System.out.println("Error al cargar socios: " + e.getMessage());
        }
    }

    public static void eliminarSocio(String rutAEliminar, ClubDeportivo club) {
        ArrayList<Socio> socios = new ArrayList<>(club.getSociosPorRut().values());

        try (BufferedWriter writer = new BufferedWriter(new FileWriter(RUTA_SOCIOS))) {
            for (Socio s : socios) {
                if (!s.getRut().equals(rutAEliminar)) {
                    eliminarSocioActividades(club, s);
                    String linea = s.getNombre() + "," + s.getEdad() + "," + s.getRut();
                    writer.write(linea);
                    writer.newLine();
                }
            }
        } catch (IOException e) {
            System.out.println("Error al eliminar socio: " + e.getMessage());
        }
    }

    public static void eliminarSocioActividades(ClubDeportivo club, Socio s) {
        for (Instalacion instalacion : club.getInstalaciones()) {
            bloqueHorario[][] planificacion = instalacion.getPlanificacion();

            for (int i = 0; i < 7; i++) {
                for (int j = 0; j < 12; j++) {
                    planificacion[i][j].getSociosAsistentes().removeIf(socio -> socio.equals(s));
                }
            }
        }
    }

}