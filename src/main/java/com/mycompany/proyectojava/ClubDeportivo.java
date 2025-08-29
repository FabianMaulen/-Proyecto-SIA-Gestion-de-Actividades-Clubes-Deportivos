import java.util.*;


public class ClubDeportivo {


        private HashMap<String,Socio> sociosPorRut;
        private Admin administrador;
        private ArrayList<Instalacion> instalaciones;



        public ClubDeportivo() {
            this.administrador = new Admin("Fabian", "admin@club.cl", "11.111.111-1");
            this.sociosPorRut = new HashMap<String,Socio>();
            this.instalaciones = new ArrayList<Instalacion>();

        }

        public Admin getAdministrador() {
            return administrador;
        }

        public ArrayList<Instalacion> getInstalaciones() {
            return instalaciones;
        }

        public HashMap<String, Socio> getSociosPorRut() {
        return sociosPorRut;
    }

        //Función para crear socio que posteriormente se agregara a nuestro arraylist
        public Socio crearSocio(){

            Scanner sc = new Scanner(System.in);
            Socio s = new Socio();

            System.out.print("Ingresa tu nombre: ");
            s.setNombre(sc.nextLine());

            System.out.print("Ingresa tu rut: ");
            s.setRut(sc.nextLine());

            System.out.print("Ingresa tu edad: ");
            s.setEdad(sc.nextLine());

            System.out.print("Ingresa tu email: ");
            s.setEmail(sc.nextLine());

            System.out.print("Ingresa tu número celular: ");
            s.setNroCelular(sc.nextLine());

            return s;
        }

        public boolean agregarSocio(Socio s){

            if (sociosPorRut.containsKey(s.getRut())){
                return false;
            }
            sociosPorRut.put(s.getRut(),s);
            return true;
        }

        public Socio buscarSocioPorRut(String rut) {
            return sociosPorRut.get(rut);

        }

        public Instalacion crearInstalacion(){
            Scanner sc = new Scanner(System.in);
            Instalacion i = new Instalacion();

            System.out.print("Ingresa tipo de instalacion: ");
            i.setTipo(sc.nextLine());
            System.out.print("Ingresa capacidad de nueva instalacion: ");
            i.setCapacidad(Integer.parseInt(sc.nextLine()));

            return i;

        }



        //Funcion para agregar una instalacion a mi arraylist
        public boolean agregarInstalacion(Instalacion i){
            if (instalaciones.contains(i)){
                return false;
            }
            instalaciones.add(i);
            return true;
        }



        public void mostrarInstalaciones() {
        int contador = 1;
        for (Instalacion i : instalaciones) {
            System.out.println("Instalación " + contador + " : "
                    + i.getTipo() + " con dirección en " + i.getDireccion() + ".");
            contador++;
        }
    }


    public Actividad crearActividadDesdeConsola(String rut) {
        Scanner sc = new Scanner(System.in);

        // Verificar si el RUT pertenece al sistema
        boolean existe = false;

        if (sociosPorRut.containsKey(rut)) {
            System.out.println("RUT pertenece a un socio registrado.");
            existe = true;
        } else if (administrador.getRut().equals(rut)) {
            System.out.println("RUT pertenece al administrador.");
            existe = true;
        }

        if (!existe) {
            System.out.println("RUT no registrado. No se puede crear actividad.");
            return null;
        }

        // Crear actividad
        Actividad a = new Actividad();

        System.out.print("Descripción de la actividad: ");
        a.setDescripcion(sc.nextLine());

        System.out.print("Día (1=Lunes a 7=Domingo): ");
        int dia = sc.nextInt();
        if (dia < 1 || dia > 7) {
            System.out.println("Día inválido.");
            return null;
        }

        System.out.print("Hora de inicio (8 a 19): ");
        int horaInicio = sc.nextInt();

        System.out.print("Hora de término (debe ser mayor a inicio, máximo 20): ");
        int horaFin = sc.nextInt();
        sc.nextLine();

        if (horaInicio < 8 || horaFin > 20 || horaFin <= horaInicio) {
            System.out.println("Rango horario inválido.");
            return null;
        }

        a.setDia(dia);
        a.setHoraInicio(horaInicio);
        a.setHoraFin(horaFin);

        return a;
    }

    public boolean agregarActividadDisponible(String rut) {
        Actividad a = crearActividadDesdeConsola(rut); // ← se crea dentro del método

        if (a == null) {
            System.out.println("No se pudo crear la actividad.");
            return false;
        }

        Scanner sc = new Scanner(System.in);
        mostrarInstalaciones();

        System.out.print("Seleccione instalacion por indice: ");
        int index = sc.nextInt();
        sc.nextLine();

        if (index < 0 || index >= instalaciones.size()) {
            System.out.println("Indice invalido.");
            return false;
        }

        Instalacion inst = instalaciones.get(index);
        int dia = a.getDia();

        boolean disponible = inst.estaDisponible(dia, a.getHoraInicio(), a.getHoraFin());

        if (!disponible) {
            System.out.println("No hay disponibilidad en ese rango.");
            return false;
        }

        for (int h = a.getHoraInicio(); h < a.getHoraFin(); h++) {
            bloqueHorario b = inst.getPlanificacion()[dia - 1][h - 8];
            b.setDescripcion(a); // guarda la actividad en el bloque
            b.setDisponibilidad(false); // marca el bloque como ocupado
        }

        System.out.println("Actividad creada y asignada correctamente.");
        return true;
    }

    public void mostrarActividadDia( Instalacion ins) {
        Scanner sc = new Scanner(System.in);
        System.out.print("Ingrese el día (1 a 7): ");
        int dia = sc.nextInt();
        if (dia <= 0 || dia > 7) {
            System.out.println("Parametros de busqueda invalidos");
            return;
        }

        System.out.printf("%-6s | %-20s%n", "Hora", "Actividad");
        System.out.println("----------------------------");
        for (int i = 0; i < 12; i++) {
            if((ins.getPlanificacion()[dia-1][i].getInfoActividad()).equals("")){
                System.out.printf("%-6s | %-20s%n", (i+8)+":00", "Sin actividad");
            }
            else
                System.out.printf("%-6s | %-20s%n", (i+8)+":00", ins.getPlanificacion()[dia-1][i].getInfoActividad());
        }
    }


    public void mostrarSocio(HashMap<String, Socio> sociosPorRut) {
        Scanner sc = new Scanner(System.in);

        System.out.print("Ingrese el RUT del socio a buscar (formato 11.111.111-1): ");
        String rut = sc.nextLine();

        if (sociosPorRut.containsKey(rut)) {
            Socio socio = sociosPorRut.get(rut);

            System.out.printf("%-12s | %-20s | %-5s | %-12s | %-25s%n",
                    "RUT", "Nombre", "Edad", "Celular", "Email");
            System.out.println("-----------------------------------------------------------------------------------------");

            System.out.printf("%-12s | %-20s | %-5d | %-12s | %-25s%n",
                    socio.getRut(),
                    socio.getNombre(),
                    socio.getEdad(),
                    socio.getNroCelular(),
                    socio.getEmail());
        } else {


            System.out.println("No se encontró ningún socio con el RUT: " + rut);
        }
    }


}
