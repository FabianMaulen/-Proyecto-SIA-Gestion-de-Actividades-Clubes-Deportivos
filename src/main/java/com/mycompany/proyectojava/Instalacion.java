public class Instalacion {
    private String tipo;
    private int capacidad;
    private bloqueHorario [][] planificacion = new bloqueHorario [7][12];

    public Instalacion(String tipo, int capacidad, bloqueHorario[][] planificacion) {
        this.tipo = tipo;
        this.capacidad = capacidad;
        this.planificacion = planificacion;
    }

    public Instalacion(){}
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

    //Dia es entero del 1 al 7 que sera convertido en base a strings. ej Lunes = 1.
    public boolean estaDisponible(int dia, int horaInicio, int horaFinal){
        int inicio = horaInicio - 8;
        int fin = horaFinal - 8;

        for(int i = inicio ; i<fin;i++){
            if (!planificacion[dia-1][i].isDisponibilidad()){
                return false;
            }
        }
        return true;
    }
}