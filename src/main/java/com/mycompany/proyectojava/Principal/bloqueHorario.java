package Principal;

import java.util.ArrayList;
import java.util.List;

public class bloqueHorario {

    private boolean disponibilidad = true;
    private ArrayList<Socio> sociosAsistentes;
    private Actividad infoActividad;

    public void setDisponibilidad(boolean disponibilidad) {
        this.disponibilidad = disponibilidad;
    }

    public bloqueHorario() {
        this.sociosAsistentes = new ArrayList<Socio>();
    }

    public void setInfoActividad(Actividad infoActividad) {
        this.infoActividad = infoActividad;
    }

    public void setSociosAsistentes(ArrayList<Socio> sociosAsistentes) {
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

    public Actividad getInfoActividad() {
        return infoActividad;
    }
}