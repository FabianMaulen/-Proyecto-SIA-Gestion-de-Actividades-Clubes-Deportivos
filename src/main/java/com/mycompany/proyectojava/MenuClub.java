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
            System.out.println("2. Mostrar Instalaciones");
            System.out.println("3. Crear Nueva Instalacion");
            System.out.println("4. MostrarActividad");
            System.out.println("5. Mostrar Socio");
            System.out.println("0. Cerrar sesión");
            System.out.print("Seleccione una opción: ");

            String entrada = sc.nextLine();

            switch (entrada) {
                case "0":
                    System.out.println("Cerrando Sesion...");
                    return;

                case "1":
                    if (club.agregarActividadDisponible(rut)) {
                        System.out.println(" Actividad creada y asignada correctamente.");
                    } else {
                        System.out.println("️ No se pudo completar el proceso.");
                    }
                    break;

                case "2":
                    club.mostrarInstalaciones();
                    break;

                case "3":
                    Instalacion nuevaInst = club.crearInstalacion();
                    if (club.agregarInstalacion(nuevaInst)) {
                        System.out.println("Instalacion agregada correctamente.");
                    } else {
                        System.out.println("La instalacion ya existe o no se pudo agregar.");
                    }
                    break;

                case "4":
                    ArrayList<Instalacion> lista = club.getInstalaciones();

                    System.out.println("\n--- Instalaciones disponibles ---");
                    for (int i = 0; i < lista.size(); i++) {
                        System.out.println(i+  ". " + lista.get(i).getTipo() + " | Dirección: " + lista.get(i).getDireccion());
                    }

                    System.out.print("Seleccione instalación por índice: ");
                    int index = Integer.parseInt(sc.nextLine()) ;

                    Instalacion seleccionada = lista.get(index);
                    club.mostrarActividadDia(seleccionada);
                    break;

                case "5":
                    club.mostrarSocio(club.getSociosPorRut());
                    break;

                default:
                    System.out.println("Opcion invalida. Intente nuevamente.");
            }


        }
    }

    public void mostrarMenuSocio(String rut) {
        Scanner sc = new Scanner(System.in);

        while (true) {
            System.out.println("\n--- Menú Socio ---");
            System.out.println("1. Crear actividad");
            System.out.println("2. Mostrar Instalaciones");
            System.out.println("3. MostrarActividad");
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

                case "2":
                    club.mostrarInstalaciones();
                    break;

                case "3":
                    ArrayList<Instalacion> lista = club.getInstalaciones();

                    System.out.println("\n--- Instalaciones disponibles ---");
                    for (int i = 0; i < lista.size(); i++) {
                        System.out.println(i +". " + lista.get(i).getTipo() + " | Dirección: " + lista.get(i).getDireccion());
                    }

                    System.out.print("Seleccione instalación por índice: ");
                    int index = Integer.parseInt(sc.nextLine());

                    Instalacion seleccionada = lista.get(index);
                    club.mostrarActividadDia(seleccionada);
                    break;

                default:
                    System.out.println("Opcion invalida. Intente nuevamente.");
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
                mostrarMenuSocio(rut);
            } else {
                System.out.println("RUT no registrado en el sistema.");
                System.out.print("¿Desea registrarse como nuevo socio? (Si/No): ");
                String respuesta = sc.nextLine();

                if (respuesta.equalsIgnoreCase("SI")) {
                    Socio nuevo = club.crearSocio();
                    if (club.agregarSocio(nuevo)) {
                        System.out.println("Registro exitoso. Bienvenido, " + nuevo.getNombre() + ".");
                        mostrarMenuSocio(nuevo.getRut());
                    } else {
                        System.out.println("No se pudo registrar el socio. El RUT ya está en uso.");
                    }
                } else {
                    System.out.println("Volviendo al inicio...");
                }
            }
        }
    }
}
