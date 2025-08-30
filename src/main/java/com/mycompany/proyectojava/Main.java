public class Main {
    public static void main(String[] args) {
        ClubDeportivo club = new ClubDeportivo();
        MenuClub menu = new MenuClub(club);
        club.getSociosPorRut().put("21.784.233-6", new Socio("Mario Vidal", "20", "21.784.233-6"));
        club.getSociosPorRut().put("21.957.957-8", new Socio("Fabian Maulen", "19", "21.957.957-8"));
        club.getSociosPorRut().put("21.038.139-7", new Socio("Carlos Soto", "22", "21.038.139-7"));
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

        // Asignar fútbol a la cancha (martes, 10–12)
        for (int h = futbol.getHoraInicio(); h < futbol.getHoraFin(); h++) {
            bloqueHorario b = cancha.getPlanificacion()[futbol.getDia() - 1][h - 8];
            b.setDescripcion(futbol);
            b.setDisponibilidad(false);
        }

// Asignar natación a la piscina (viernes, 15–17)
        for (int h = natacion.getHoraInicio(); h < natacion.getHoraFin(); h++) {
            bloqueHorario b = piscina.getPlanificacion()[natacion.getDia() - 1][h - 8];
            b.setDescripcion(natacion);
            b.setDisponibilidad(false);
        }
        menu.iniciar();


    }
}