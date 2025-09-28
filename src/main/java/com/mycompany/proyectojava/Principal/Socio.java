package Principal;

public class Socio extends Persona{
        private String edad;

        public Socio(String nombre, String edad, String rut) {
            super(nombre,rut); // llama al constructor de Persona
            this.edad = edad;
        }

        public Socio() {}

        // Getters y setters
        public String getEdad() {
            return edad;
        }
        public void setEdad(String edad) {
            this.edad = edad;
        }

        @Override
        public String toString() {
            return "Socio: " + nombre + " | RUT: " + rut + " | Edad: " + edad + " |";
        }
}