package Principal;

public class Instalacion {
    private String tipo;
    private int capacidad;
    private String direccion;
    private bloqueHorario [][] planificacion = new bloqueHorario [7][12];

    public Instalacion(String tipo, int capacidad, bloqueHorario[][] planificacion,String direccion) {
        this.tipo = tipo;
        this.capacidad = capacidad;
        this.planificacion = planificacion;
        this.direccion = direccion ;
    }

    public Instalacion(){
        inicializarHorario();
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

    public String getDireccion() {
        return direccion;

    }

    public void setDireccion(String direccion) {
        this.direccion = direccion;
    }

    public void inicializarHorario() {
        for (int d = 0; d < 7; d++) {
            for (int h = 0; h < 12; h++) {
                planificacion[d][h] = new bloqueHorario();
            }
        }
    }

    public void borrarActividad(int dia, int horaInicio, int horaFin, bloqueHorario[][] matriz) {
        int inicio = horaInicio - 8;
        int fin = horaFin - 8;

        if (dia < 0 || dia >= 7 || inicio < 0 || fin >= 12) {
            System.out.println("Error: parámetros fuera de rango.");
            return;
        }

        for (int i = inicio; i < fin; i++) {
            matriz[dia-1][i] = null;
        }
        System.out.println("Principal.Actividad eliminada en día " + dia + " desde " + horaInicio + " hasta " + horaFin);
    }

    public bloqueHorario getBloque(int dia, int hora) {
        int h = hora - 8;
        if (dia < 1 || dia > 7 || h < 0 || h >= 12) {
            return null;
        }
        return planificacion[dia - 1][h];
    }



}