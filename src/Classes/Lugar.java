package src.Classes;
import java.io.Serializable;

// Clase Lugar
// Representa el sitio donde se realiza un evento
public class Lugar implements Serializable {

    private static final long serialVersionUID = 7L;

    private String nombre;
    private String direccion;
    private String ciudad;
    private int capacidad;
    private String tipoLugar;

    // Constructor de Lugar
    public Lugar(String nombre, String direccion, String ciudad,
                 int capacidad, String tipoLugar) {
        this.nombre = nombre;
        this.direccion = direccion;
        this.ciudad = ciudad;
        this.capacidad = capacidad;
        this.tipoLugar = tipoLugar;
    }

    // Getters
    public String getNombre() {
        return nombre;
    }

    public String getDireccion() {
        return direccion;
    }

    public String getCiudad() {
        return ciudad;
    }

    public int getCapacidad() {
        return capacidad;
    }

    public String getTipoLugar() {
        return tipoLugar;
    }

    // Setters
    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public void setDireccion(String direccion) {
        this.direccion = direccion;
    }

    public void setCiudad(String ciudad) {
        this.ciudad = ciudad;
    }

    public void setCapacidad(int capacidad) {
        this.capacidad = capacidad;
    }

    public void setTipoLugar(String tipoLugar) {
        this.tipoLugar = tipoLugar;
    }

    // toString para guardar en archivo plano y para mostrar en pantalla
    @Override
    public String toString() {
        return nombre + "," + direccion + "," + ciudad + "," + capacidad + "," + tipoLugar;
    }
}