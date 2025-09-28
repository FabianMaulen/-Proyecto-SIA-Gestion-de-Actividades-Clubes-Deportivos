package Principal;

public class Persona {
    protected String nombre;
    protected String rut;
    protected String email;

    public Persona(String nombre, String rut, String email) {
        this.nombre = nombre;
        this.rut = rut;
        this.email = email;
    }

    public Persona(String nombre, String rut) {
        this.nombre = nombre;
        this.rut = rut;
    }


    public Persona() {}

    // Getters y setters
    public String getNombre() {
        return nombre;
    }
    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getRut() {
        return rut;
    }
    public void setRut(String rut) {
        this.rut = rut;
    }

    public String getEmail() {
        return email;
    }
    public void setEmail(String email) {
        this.email = email;
    }

    @Override
    public String toString() {
        return "Nombre: " + nombre + " | RUT: " + rut + " | Email: " + email;
    }
}
