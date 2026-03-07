package src.Classes;
// Clase Modelo que hereda de Persona
public class Modelo extends Persona {

    private String  codigoModelo;
    private double  estatura;
    private String  categoria;
    private boolean disponible;

    public Modelo(String nombre, String identificacion, String contacto,
                  String codigoModelo, double estatura, String categoria, boolean disponible) {
        super(nombre, identificacion, contacto);
        this.codigoModelo = codigoModelo;
        this.estatura     = estatura;
        this.categoria    = categoria;
        this.disponible   = disponible;
    }

    public String  getCodigoModelo() { return codigoModelo; }
    public double  getEstatura()     { return estatura; }
    public String  getCategoria()    { return categoria; }
    public boolean isDisponible()    { return disponible; }

    public void setDisponible(boolean disponible) { this.disponible = disponible; }
    public void setCategoria(String categoria)    { this.categoria  = categoria; }

    @Override
    public void mostrarInformacion() {
        System.out.println("=== MODELO ===");
        System.out.println("Nombre: "    + nombre);
        System.out.println("ID: "        + identificacion);
        System.out.println("Contacto: "  + contacto);
        System.out.println("Codigo: "    + codigoModelo);
        System.out.println("Estatura: "  + estatura + " m");
        System.out.println("Categoria: " + categoria);
        System.out.println("Disponible: "+ (disponible ? "Si" : "No"));
    }

    // Usado en Evento.baseDetalle()
    @Override
    public String toString() {
        return nombre + " [" + codigoModelo + "] - " + categoria + " - " + estatura + "m";
    }

    // Para guardar en archivo plano
    public String toCSV() {
        return nombre + "," + identificacion + "," + contacto + ","
                + codigoModelo + "," + estatura + "," + categoria + "," + disponible;
    }

    // Para reconstruir desde archivo plano
    public static Modelo fromCSV(String linea) {
        String[] p = linea.split(",");
        if (p.length < 7) return null;
        return new Modelo(p[0], p[1], p[2], p[3],
                Double.parseDouble(p[4].trim()), p[5], Boolean.parseBoolean(p[6].trim()));
    }
}
