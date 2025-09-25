package Principal;

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

    public boolean agregarSocio(String nombre, String edad, String rut) {
        Socio nuevo = new Socio(nombre, edad, rut);
        return agregarSocio(nuevo); // ← llamada directa y válida
    }

    public boolean agregarSocioDesdeConsola() {
        Scanner sc = new Scanner(System.in);

        System.out.print("Nombre del socio: ");
        String nombre = sc.nextLine();

        System.out.print("Edad: ");
        String edad = sc.nextLine();

        System.out.print("RUT: ");
        String rut = sc.nextLine();

        return agregarSocio(nombre, edad, rut);
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
            System.out.print("Ingresa la Direccion de la instalaciom ");
            i.setDireccion(sc.nextLine());


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
    public Instalacion buscarInstalacionPorTipo(String tipo) {
        for (Instalacion i : instalaciones) {
            if (i.getTipo().equalsIgnoreCase(tipo)) {
                return i;
            }
        }
        return null;
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

    public boolean  agregarActividadDisponible(String rut) {
        Actividad a = crearActividadDesdeConsola(rut); // ← se crea dentro del metodo

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
            b.setInfoActividad(a);
            System.out.println("Bloque asignado: Día " + dia + ", Hora " + h + ":00 → " + a.getDescripcion());

        }
        System.out.println("Principal.Actividad creada y asignada correctamente.");

        return true;
    }

    public boolean agregarActividadDisponible(String rut, String descripcion, int dia, int horaInicio, int horaFin, int indexInstalacion) {
        if (!sociosPorRut.containsKey(rut) && !administrador.getRut().equals(rut)) {
            System.out.println("RUT no registrado.");
            return false;
        }

        if (dia < 1 || dia > 7 || horaInicio < 8 || horaFin > 20 || horaFin <= horaInicio) {
            System.out.println("Rango horario invalido.");
            return false;
        }

        if (indexInstalacion < 0 || indexInstalacion >= instalaciones.size()) {
            System.out.println("Principal.Instalacion invalida.");
            return false;
        }

        Actividad a = new Actividad();
        a.setDescripcion(descripcion);
        a.setDia(dia);
        a.setHoraInicio(horaInicio);
        a.setHoraFin(horaFin);

        Instalacion inst = instalaciones.get(indexInstalacion);
        boolean disponible = inst.estaDisponible(dia, horaInicio, horaFin);

        if (!disponible) {
            System.out.println("No hay disponibilidad en ese rango.");
            return false;
        }

        for (int h = horaInicio; h < horaFin; h++) {
            bloqueHorario b = inst.getPlanificacion()[dia - 1][h - 8];
            b.setDescripcion(a);
            b.setDisponibilidad(false);
            b.setInfoActividad(a);
        }

        System.out.println("Principal.Actividad '" + descripcion + "' creada y asignada correctamente.");
        return true;
    }



    public Boolean agregarSocioActividad(Socio socio, Instalacion ins, int dia, int hora) {
        if (ins == null || socio == null || dia < 1 || dia > 7 || hora < 8 || hora > 19) {
            return false;
        }

        bloqueHorario bloque = ins.getPlanificacion()[dia - 1][hora - 8];
        if (bloque == null || bloque.getInfoActividad() == null) {
            System.out.println("No hay actividad en el bloque [" + dia + "][" + hora + "].");
            return false;
        }

        bloque.getSociosAsistentes().add(socio);
        return true;
    }


    public void inscribirSocioEnActividadDesdeMenu() {
        Scanner sc = new Scanner(System.in);

        System.out.print("Ingrese RUT del socio: ");
        String rut = sc.nextLine();
        Socio socio = buscarSocioPorRut(rut);

        if (socio == null) {
            System.out.println("Principal.Socio no encontrado.");
            return;
        }

        mostrarInstalaciones(); // muestra índice y tipo

        System.out.print("Seleccione instalacion por indice: ");
        int numero = sc.nextInt();
        sc.nextLine();
        int index = numero - 1 ;


        if (index < 0 || index >= instalaciones.size()) {
            System.out.println("Indice invalido.");
            return;
        }

        Instalacion inst = instalaciones.get(index);

        System.out.print("Ingrese dia (1 a 7): ");
        int dia = sc.nextInt();

        System.out.print("Ingrese hora (8 a 19): ");
        int hora = sc.nextInt();

        boolean exito = agregarSocioActividad(socio, inst, dia, hora);


        if (exito) {
            System.out.println("Principal.Socio " + socio.getNombre() + " inscrito en actividad: " +
                    inst.getPlanificacion()[dia - 1][hora - 8].getInfoActividad().getDescripcion() +
                    " el día " + dia + " a las " + hora + ":00 en " + inst.getTipo());
        } else {
            System.out.println("No se pudo inscribir al socio en esa actividad.");
        }
    }

    public void mostrarActividadDia( Instalacion ins) {
        Scanner sc = new Scanner(System.in);
        System.out.print("Ingrese el día (1 a 7): ");
        int dia = sc.nextInt();
        if (dia <= 0 || dia > 7) {
            System.out.println("Parametros de busqueda invalidos");
            return;
        }

        System.out.printf("%-6s | %-20s%n", "Hora", "Principal.Actividad");
        System.out.println("----------------------------");
        for (int i = 0; i < 12; i++) {
            Actividad act = ins.getPlanificacion()[dia - 1][i].getInfoActividad();

            if (act == null || act.getDescripcion().equals("")) {
                System.out.printf("%-6s | %-20s%n", (i + 8) + ":00", "Sin actividad");
            } else {
                System.out.printf("%-6s | %-20s%n", (i + 8) + ":00", act.getDescripcion());
            }

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


            System.out.println("No se encontro ningun socio con el RUT: " + rut);
        }
    }



    public void mostrarBloqueHorarioDesdeConsola() {
        Scanner sc = new Scanner(System.in);
        mostrarInstalaciones();

        System.out.print("Seleccione instalacion por numero: ");
        int index = sc.nextInt() - 1;
        sc.nextLine();

        System.out.print("Ingrese dia (1 a 7): ");
        int dia = sc.nextInt();

        System.out.print("Ingrese hora (8 a 19): ");
        int hora = sc.nextInt();

        if (index < 0 || index >= instalaciones.size()) {
            System.out.println("Numero de instalacion invalido.");
            return;
        }

        Instalacion inst = instalaciones.get(index);
        bloqueHorario b = inst.getPlanificacion()[dia - 1][hora - 8];

        if (b == null || b.getInfoActividad() == null) {
            System.out.println("No hay actividad en ese bloque.");
            return;
        }

        System.out.println("Dia " + dia + " a las " + hora + ":00");
        System.out.println("Principal.Actividad: " + b.getInfoActividad().getDescripcion());
        System.out.println("Socios inscritos:");
        for (Socio s : b.getSociosAsistentes()) {
            System.out.println(" - " + s.getNombre() + " (" + s.getEdad() + " años)");
        }

        int cuposRestantes = inst.getCapacidad() - b.getSociosAsistentes().size();
        System.out.println("Cupos disponibles: " + cuposRestantes + " / " + inst.getCapacidad());
    }

}
