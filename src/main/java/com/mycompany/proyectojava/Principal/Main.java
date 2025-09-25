package Principal;

import ArchivosProyecto.PersistenciaActividad;

import javax.swing.*;

public class Main {
    public static void main(String[] args) {
        ClubDeportivo club = new ClubDeportivo();
        MenuClub menu = new MenuClub(club);

        club.agregarSocio("Mario Vidal", "20", "21.784.233-6");
        club.agregarSocio("Fabian Maulen", "19", "21.957.957-8");
        club.agregarSocio("Carlos Soto", "22", "21.038.139-7");

        Instalacion cancha = new Instalacion();
        cancha.setTipo("Cancha de futbol");
        cancha.setDireccion("Av. Las Torres 123");
        cancha.setCapacidad(26);
        Instalacion piscina = new Instalacion();
        piscina.setTipo("Piscina olimpica");
        piscina.setDireccion("Calle Agua Clara 456");
        piscina.setCapacidad(30);
        club.agregarInstalacion(cancha);
        club.agregarInstalacion(piscina);


        //club.agregarActividadDisponible("21.784.233-6", "Fútbol amistoso", 2, 10, 12, 0);
        //club.agregarActividadDisponible("21.957.957-8", "Natación libre", 5, 15, 17, 1);

        SwingUtilities.invokeLater(() -> new VentanaLogin(club));


        bloqueHorario[][] matriz = new bloqueHorario[7][12];
        for (int d = 0; d < 7; d++) {
            for (int h = 0; h < 12; h++) {
                matriz[d][h] = new bloqueHorario();
            }
        }

        // Crear instalación y agregarla al club
        Instalacion cancha2 = new Instalacion("Cancha", 20, matriz, "Av. Deportiva 123");
        club.agregarInstalacion(cancha); // Este metodo debe existir en ClubDeportivo

        // Asignar actividad a Martes 10:00
        bloqueHorario bloque = cancha.getPlanificacion()[1][2]; // Día 2 (Martes), hora 10 (índice 2)
        Actividad actividad = new Actividad();
        actividad.setDia(2);
        actividad.setHoraInicio(10);
        actividad.setHoraFin(11);
        actividad.setDescripcion("Fútbol amistoso");
        bloque.setInfoActividad(actividad);

        // Guardar en CSV
        PersistenciaActividad.guardarActividades(club);
        System.out.println(" Actividad guardada en CSV.");

        // Borrar y cargar desde CSV
        bloque.setInfoActividad(null);
        PersistenciaActividad.cargarActividades(club);

        Actividad recargada = cancha.getPlanificacion()[1][2].getInfoActividad();
        if (recargada != null) {
            System.out.println("Actividad cargada: " + recargada.getDescripcion());
        } else {
            System.out.println(" No se cargó ninguna actividad.");
        }


    }
}