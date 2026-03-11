package src.Classes;
import java.io.Serializable;

// Clase Fotografo que hereda de Persona
// Representa a los fotografos que trabajan con la agencia
public class Fotografo extends Persona implements Serializable {

    private static final long serialVersionUID = 3L;

    private String especialidad;
    private int anosExperiencia;

    // Constructor de Fotografo
    public Fotografo(String nombre, String id, String contacto,
                     String especialidad, int anosExperiencia) {
        // Llamamos al constructor del padre (Persona)
        super(nombre, id, contacto);
        this.especialidad = especialidad;
        this.anosExperiencia = anosExperiencia;
    }

    // Getters
    public String getEspecialidad() {
        return especialidad;
    }

    public int getAnosExperiencia() {
        return anosExperiencia;
    }

    // Implementacion del metodo abstracto de Persona
    @Override
    public void mostrarInformacion() {
        System.out.println("=== FOTOGRAFO ===");
        System.out.println("Nombre: " + nombre);
        System.out.println("ID: " + id);
        System.out.println("Contacto: " + contacto);
        System.out.println("Especialidad: " + especialidad);
        System.out.println("Anos de experiencia: " + anosExperiencia);
    }

    // toString para guardar en archivo plano
    @Override
    public String toString() {
        return nombre + "," + id + "," + contacto + "," + especialidad + "," + anosExperiencia;
    }
}