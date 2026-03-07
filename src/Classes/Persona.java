package src.Classes;

public abstract class Persona {
    protected String nombre;
    protected String identificacion;
    protected String contacto;

    public Persona(String nombre, String id, String contacto) {
        this.nombre = nombre;
        this.identificacion = id;
        this.contacto = contacto;
    }

    public String getNombre() {
        return nombre;
    }

    public String getIdentificacion() {
        return identificacion;
    }

    public String getContacto() {
        return contacto;
    }

    public abstract void mostrarInformacion();

}
