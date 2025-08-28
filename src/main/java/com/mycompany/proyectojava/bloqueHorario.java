
import java.util.List;

public class bloqueHorario {


    private boolean disponibilidad;
    private List <Socio> sociosAsistentes;
    private Actividad infoActividad;

    public void setDisponibilidad(boolean disponibilidad) {
        this.disponibilidad = disponibilidad;
    }

    public void setSociosAsistentes(List<Socio> sociosAsistentes) {
        this.sociosAsistentes = sociosAsistentes;
    }

    public void setDescripcion(Actividad infoActividad) {
        this.infoActividad = infoActividad;
    }
    
    public boolean isDisponibilidad() {
        return disponibilidad;
    }

    public List<Socio> getSociosAsistentes() {
        return sociosAsistentes;
    }

    public Actividad infoActividad() {
        return infoActividad;
    }


}
