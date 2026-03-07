public class Modelo extends Persona{
    private String codigo;
    private double estatura;
    private String categoria;
    private boolean disponible;

    public Modelo(String nombre, String id, String contacto, String codigo, double estatura, String categoria, boolean disponible) {
        super(nombre, id, contacto);
        this.codigo = codigo;
        this.estatura = estatura;
        this.categoria = categoria;
        this.disponible = disponible;
    }

    public String getCodigo() {
        return codigo;
    }

    public double getEstatura() {
        return estatura;
    }

    public String getCategoria() {
        return categoria;
    }

    public boolean isDisponible() {
        return disponible;
    }

    @Override
    public void mostrarInformacion() {
        System.out.println("Nombre: " + nombre + " Id: " + id + " Contacto: " + contacto + " Codigo: " + codigo + " Estatura: " + estatura + " Categoria: " + categoria + " Disponible: " + disponible);
    }
}
