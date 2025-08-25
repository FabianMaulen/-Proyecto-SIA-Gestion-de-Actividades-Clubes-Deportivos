import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

    public class ClubDeportivo {

        private List<Socio> socios;
        private HashMap<String,Socio> sociosPorRut;
        private Admin administrador;
        private Instalacion[] instalaciones = new Instalacion[6];

        public ClubDeportivo(Admin administrador) {
            this.administrador = administrador;
            this.socios = new ArrayList<Socio>();
            this.sociosPorRut = new HashMap<String,Socio>();

        }

        public List<Socio> getSocios() {
            return socios;
        }

        public Admin getAdministrador() {
            return administrador;
        }

        public Instalacion[] getInstalaciones() {
            return instalaciones;
        }

        public void setSocios(List<Socio> socios) {
            this.socios = socios;
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
    }
