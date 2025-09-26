package Principal;

public class Socio {
    private String Nombre;
    private String Rut;
    private String email;
    private String edad;
    private String NroCelular;

    public Socio(String nom, String rut, String correo, String anios, String numCel){
        this.Nombre = nom;
        this.Rut = rut;
        this.email = correo;
        this.edad  = anios ;
        this.NroCelular = numCel;
    }

    public Socio(){}

    public String getNombre() {
        return Nombre;
    }
    public void setNombre(String Nombre) {
        this.Nombre = Nombre;
    }
    public String getRut() {
        return Rut;
    }
    public void setRut(String Rut) {
        this.Rut = Rut;
    }
    public String getEmail() {
        return email;
    }
    public  void setEmail(String email) {
        this.email = email;
    }
    public  String getEdad() {
        return edad;
    }
    public void setEdad(String edad) {
        this.edad = edad;
    }
    public String getNroCelular() {
        return NroCelular;
    }
    public void setNroCelular(String NroCelular) {
        this.NroCelular = NroCelular;
    }
    public Socio(String nombre, String edad, String rut) {
        this.Nombre = nombre;
        this.edad = edad;
        this.Rut = rut;
    }
    @Override
    public String toString() {
        return "Socio: " + Nombre + " | RUT: " + Rut + " | Edad: " + edad  ;
    }
}