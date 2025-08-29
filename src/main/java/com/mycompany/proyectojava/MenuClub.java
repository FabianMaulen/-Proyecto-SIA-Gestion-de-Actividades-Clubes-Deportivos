import java.util.ArrayList;
import java.util.Scanner;

public class MenuClub {
    private ClubDeportivo club;

    public MenuClub(ClubDeportivo club) {
        this.club = club;
    }





    public void mostrarMenuAdmin(String rut) {
        Scanner sc = new Scanner(System.in);

        while (true) {
            System.out.println("\n--- Menú Administrador ---");
            System.out.println("1. Crear actividad");
            System.out.println("2. Asignar actividad al horario");
            System.out.println("3. Ver instalaciones");
            System.out.println("4. Crear nueva instalación");
            System.out.println("0. Cerrar sesión");
            System.out.print("Seleccione una opción: ");

            String entrada = sc.nextLine();

            switch (entrada) {
                case "1":
                    if (club.agregarActividadDisponible(rut)) {
                        System.out.println(" Actividad creada y asignada correctamente.");
                    } else {
                        System.out.println("️ No se pudo completar el proceso.");
                    }
                    break;

            }


        }
    }

    public void iniciar() {
        Scanner sc = new Scanner(System.in);
        System.out.println("=== Bienvenido al Club Deportivo ===");

        while (true) {
            System.out.print("\nIngrese su RUT (o escriba 'salir' para terminar): ");
            String rut = sc.nextLine();

            if (rut.equalsIgnoreCase("salir")) {
                System.out.println("Gracias por usar el sistema. ¡Hasta pronto!");
                break;
            }

            if (club.getAdministrador() != null && club.getAdministrador().getRut().equals(rut)) {
                System.out.println("Identificado como ADMINISTRADOR.");
                mostrarMenuAdmin(rut);
            } else if (club.buscarSocioPorRut(rut) != null) {
                System.out.println("Identificado como SOCIO.");
                //mostrarMenuSocio(rut);
            } else {
                System.out.println("RUT no registrado en el sistema.");
            }
        }
    }


}
