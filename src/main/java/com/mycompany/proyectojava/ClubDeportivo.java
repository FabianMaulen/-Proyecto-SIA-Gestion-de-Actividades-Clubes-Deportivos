import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Scanner;


    public class ClubDeportivo {

        private List<Socio> socios;
        private HashMap<String,Socio> sociosPorRut;
        private Admin administrador;
        private List<Instalacion> instalaciones;

        public ClubDeportivo(Admin administrador) {
            this.administrador = administrador;
            this.socios = new ArrayList<Socio>();
            this.sociosPorRut = new HashMap<String,Socio>();
            this.instalaciones = new ArrayList<Instalacion>();

        }

        public List<Socio> getSocios() {
            return socios;
        }

        public Admin getAdministrador() {
            return administrador;
        }

        public List<Instalacion> getInstalaciones() {
            return instalaciones;
        }

        public void setSocios(List<Socio> socios) {
            this.socios = socios;
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
            socios.add(s);
            return true;
        }

        public Socio buscarSocioPorRut(String rut) {
            return sociosPorRut.get(rut);

        }

        public boolean eliminarSocioPorRut(String rut){

            Socio s = sociosPorRut.remove(rut);
            if ( s!=null){
                socios.remove(s);
                return true;
            }
            return false;
        }


        public Instalacion crearInstalacion(){
            Scanner sc = new Scanner(System.in);
            Instalacion i = new Instalacion();

            System.out.print("Ingresa tipo de instalacion: ");
            i.setTipo(sc.nextLine());
            System.out.print("Ingresa capacidad de nueva instalacion: ");
            i.setCapacidad(sc.nextLine());

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



    }
