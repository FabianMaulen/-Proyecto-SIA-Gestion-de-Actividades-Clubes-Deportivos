package Principal;

public class Admin extends Persona {

    public Admin(String nombre, String correo, String rut) {
        super(nombre, rut, correo);
    }

    public boolean isAdmin(String rut) {
        return this.rut.equals(rut);
    }

    @Override
    public String toString() {
        return "Admin: " + nombre + " | RUT: " + rut + " | Email: " + email;
    }
}
