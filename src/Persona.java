public abstract class Persona {
    protected String nombre;
    protected String id;
    protected String contacto;

    public Persona(String nombre, String id, String contacto) {
        this.nombre = nombre;
        this.id = id;
        this.contacto = contacto;
    }

    public String getNombre() {
        return nombre;
    }

    public String getId() {
        return id;
    }

    public String getContacto() {
        return contacto;
    }

    public abstract void mostrarInformacion();

}
