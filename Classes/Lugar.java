package Classes;
public class Lugar {
    private String nombre;
    private String direccion;
    private String ciudad;
    private int capacidad;
    private String tipoLugar;

    public Lugar(String nombre, String direccion, String ciudad, int capacidad, String tipoLugar) {
        this.nombre = nombre;
        this.direccion = direccion;
        this.ciudad = ciudad;
        this.capacidad = capacidad;
        this.tipoLugar = tipoLugar;
    }

    public String getNombre() { return nombre; }
    public String getDireccion() { return direccion; }
    public String getCiudad() { return ciudad; }
    public int getCapacidad() { return capacidad; }
    public String getTipoLugar() { return tipoLugar; }

    public void setNombre(String nombre) { this.nombre = nombre; }
    public void setDireccion(String direccion) { this.direccion = direccion; }
    public void setCiudad(String ciudad) { this.ciudad = ciudad; }
    public void setCapacidad(int capacidad) { this.capacidad = capacidad; }
    public void setTipoLugar(String tipoLugar) { this.tipoLugar = tipoLugar; }

    @Override
    public String toString() {
        return nombre + " - " + ciudad + " (" + tipoLugar + ", cap: " + capacidad + ")";
    }
    //Guardar plano
    public String toCSV() {
        return nombre + "," + direccion + "," + ciudad
                + "," + capacidad + "," + tipoLugar;
    }
}
