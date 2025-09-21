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

        Actividad futbol = new Actividad();
        futbol.setDescripcion("Fútbol juvenil");
        futbol.setHoraInicio(10); // 10:00
        futbol.setHoraFin(12);    // 12:00
        futbol.setDia(2);         // Martes

        Actividad natacion = new Actividad();
        natacion.setDescripcion("Natación libre");
        natacion.setHoraInicio(15); // 15:00
        natacion.setHoraFin(17);    // 17:00
        natacion.setDia(5);         // Viernes

        club.agregarActividadDisponible("21.784.233-6", "Fútbol juvenil", 2, 10, 12, 0);
        club.agregarActividadDisponible("21.957.957-8", "Natación libre", 5, 15, 17, 1);

        SwingUtilities.invokeLater(() -> new VentanaLogin(club));
    }
}