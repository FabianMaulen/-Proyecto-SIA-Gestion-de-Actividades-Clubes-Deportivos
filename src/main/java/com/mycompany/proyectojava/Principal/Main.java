package Principal;

import ArchivosProyecto.*;
import javax.swing.*;

public class Main {
    public static void main(String[] args) {
        // Crear el club y cargar instalaciones + actividades desde archivos
        ClubDeportivo club = new ClubDeportivo();
        PersistenciaClub.cargarClub(club);

        // Socios precargados (hardcodeados temporalmente)
        club.agregarSocio("Mario Vidal", "20", "21.784.233-6");
        club.agregarSocio("Fabian Maulen", "19", "21.957.957-8");
        club.agregarSocio("Carlos Soto", "22", "21.038.139-7");

        // Iniciar la interfaz gráfica
        SwingUtilities.invokeLater(() -> new VentanaLogin(club));
    }
}