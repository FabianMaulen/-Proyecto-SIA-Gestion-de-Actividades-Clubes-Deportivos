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
        //cancha.inicializarHorario();

        Instalacion piscina = new Instalacion();
        piscina.setTipo("Piscina olimpica");
        piscina.setDireccion("Calle Agua Clara 456");
        piscina.setCapacidad(30);
       //1 piscina.inicializarHorario();

        club.agregarInstalacion(cancha);
        club.agregarInstalacion(piscina);


        menu.iniciar();


    }
}