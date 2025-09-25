package Principal;

public class Admin {
    
    private String nombre;
    private String correo;
    private String rut;

    public Admin(String nombre, String correo, String rut) {
        this.nombre = nombre;
        this.correo = correo;
        this.rut = rut;
    }
    
    public String getNombre() {
        return nombre;
    }

    public String getCorreo() {
        return correo;
    }

    public  String getRut() {
        return rut;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public void setCorreo(String correo) {
        this.correo = correo;
    }

    public void setRut(String rut) {
        this.rut = rut;
    }

    public boolean isAdmin(String rut) {

        return rut.equals("11.111.111-1");

    }

}
