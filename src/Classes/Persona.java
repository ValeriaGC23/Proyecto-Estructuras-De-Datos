package src.Classes;
import java.io.Serializable;

// Clase abstracta Persona
// Es la clase base para Modelo y Fotografo
// No se puede instanciar directamente
public abstract class Persona implements Serializable {

    private static final long serialVersionUID = 1L;

    // Atributos protegidos para que las subclases puedan usarlos
    protected String nombre;
    protected String id;
    protected String contacto;

    // Constructor de Persona
    public Persona(String nombre, String id, String contacto) {
        this.nombre = nombre;
        this.id = id;
        this.contacto = contacto;
    }

    // Getters
    public String getNombre() {
        return nombre;
    }

    public String getId() {
        return id;
    }

    // Alias para compatibilidad con el resto del sistema
    public String getIdentificacion() {
        return id;
    }

    public String getContacto() {
        return contacto;
    }

    // Metodo abstracto: cada subclase lo implementa a su manera
    public abstract void mostrarInformacion();
}