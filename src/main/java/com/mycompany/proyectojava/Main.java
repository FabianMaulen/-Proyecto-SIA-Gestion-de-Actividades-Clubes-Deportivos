public class Main {
    public static void main(String[] args) {
        ClubDeportivo club = new ClubDeportivo();
        club.getSociosPorRut().put("21.784.233-6", new Socio("Mario Vidal", "20", "21.784.233-6"));
        club.getSociosPorRut().put("21.957.957-8", new Socio("Fabian Maulen", "19", "21.957.957-8"));
        club.getSociosPorRut().put("21.038.139-7", new Socio("Carlos Soto", "22", "21.038.139-7"));
        MenuClub menu = new MenuClub(club);
        menu.iniciar();


    }
}