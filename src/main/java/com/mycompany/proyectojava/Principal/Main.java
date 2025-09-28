package Principal;

import ArchivosProyecto.*;
import Ventanas.VentanaLogin;

import javax.swing.*;

public class Main {
    public static void main(String[] args) {
        // Crear el club y cargar instalaciones + actividades desde archivos
        ClubDeportivo club = new ClubDeportivo();
        PersistenciaClub.cargarClub(club);
        PersistenciaSocio.cargarSocios(club);
        // Iniciar la interfaz gráfica
        SwingUtilities.invokeLater(() -> new VentanaLogin(club));
    }
}