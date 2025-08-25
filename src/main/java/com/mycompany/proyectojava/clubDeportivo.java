import java.util.*;

 public class clubDeportivo {

    private List <Socio> socios;
    private Admin administrador;
    private Instalacion[] instalaciones = new Instalacion[6];

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

    public void setAdministrador(Admin administrador) {
        this.administrador = administrador;
    }

    public void setInstalaciones(Instalacion[] instalaciones) {
        this.instalaciones = instalaciones;
    }
    
}
