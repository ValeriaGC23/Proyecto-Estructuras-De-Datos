public class Fotografo extends Persona {
    private String especialidad;
    private int aniosExperiencia;

    public Fotografo(String nombre, String id, String contacto, String especialidad, int aniosExperiencia) {
        super(nombre, id, contacto);
        this.especialidad = especialidad;
        this.aniosExperiencia = aniosExperiencia;
    }

    public String getEspecialidad() {
        return especialidad;
    }

    public int getAniosExperiencia() {
        return aniosExperiencia;
    }

    @Override
    public void mostrarInformacion() {
        System.out.println("Nombre: " + nombre + " Id: " + id + " Contacto: " + contacto + " Especialidad: " + especialidad + " Años Experiencia: " + aniosExperiencia);
    }
}
