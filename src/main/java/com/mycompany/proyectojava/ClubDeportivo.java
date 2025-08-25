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

    }
