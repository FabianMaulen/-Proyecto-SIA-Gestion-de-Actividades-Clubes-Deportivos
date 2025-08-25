public class Instalacion {
    private String tipo;
    private int capacidad;
    private bloqueHorario [][] planificacion;

    public Instalacion(String tipo, int capacidad, bloqueHorario[][] planificacion) {
        this.tipo = tipo;
        this.capacidad = capacidad;
        this.planificacion = planificacion;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }

    public void setCapacidad(int capacidad) {
        this.capacidad = capacidad;
    }

    public void setPlanificacion(bloqueHorario[][] planificacion) {
        this.planificacion = planificacion;
    }

    public String getTipo() {
        return tipo;
    }

    public int getCapacidad() {
        return capacidad;
    }

    public bloqueHorario[][] getPlanificacion() {
        return planificacion;
    }
}
