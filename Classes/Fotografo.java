package Classes;
// Clase Fotografo que hereda de Persona
public class Fotografo extends Persona {

    private String especialidad;
    private int    anosExperiencia;

    public Fotografo(String nombre, String identificacion, String contacto,
                     String especialidad, int anosExperiencia) {
        super(nombre, identificacion, contacto);
        this.especialidad    = especialidad;
        this.anosExperiencia = anosExperiencia;
    }

    public String getEspecialidad()  { return especialidad; }
    public int    getAnosExperiencia() { return anosExperiencia; }

    @Override
    public void mostrarInformacion() {
        System.out.println("=== FOTOGRAFO ===");
        System.out.println("Nombre: "           + nombre);
        System.out.println("ID: "               + identificacion);
        System.out.println("Contacto: "         + contacto);
        System.out.println("Especialidad: "     + especialidad);
        System.out.println("Anos experiencia: " + anosExperiencia);
    }

    // Usado en Evento.baseDetalle()
    @Override
    public String toString() {
        return nombre + " [" + identificacion + "] - " + especialidad;
    }

    public String toCSV() {
        return nombre + "," + identificacion + "," + contacto + ","
                + especialidad + "," + anosExperiencia;
    }

    public static Fotografo fromCSV(String linea) {
        String[] p = linea.split(",");
        if (p.length < 5) return null;
        return new Fotografo(p[0], p[1], p[2], p[3], Integer.parseInt(p[4].trim()));
    }
}
