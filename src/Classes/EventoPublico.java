package src.Classes;
import java.io.Serializable;

// Clase EventoPublico que hereda de Evento
// Evento abierto al publico general
public class EventoPublico extends Evento implements Serializable {

    private static final long serialVersionUID = 5L;

    private int capacidadAsistentes;
    private String patrocinador;

    // Constructor de EventoPublico
    public EventoPublico(String nombreEvento, String fecha, Lugar lugar,
                         int capacidadAsistentes, String patrocinador) {
        // Llamamos al constructor del padre (Evento)
        super(nombreEvento, fecha, lugar);
        this.capacidadAsistentes = capacidadAsistentes;
        this.patrocinador = patrocinador;
    }

    // Getters
    public int getCapacidadAsistentes() {
        return capacidadAsistentes;
    }

    public String getPatrocinador() {
        return patrocinador;
    }

    // Setters
    public void setCapacidadAsistentes(int capacidadAsistentes) {
        this.capacidadAsistentes = capacidadAsistentes;
    }

    public void setPatrocinador(String patrocinador) {
        this.patrocinador = patrocinador;
    }

    // Implementacion del metodo abstracto mostrarDetalles
    @Override
    public void mostrarDetalles() {
        System.out.println("=== EVENTO PUBLICO ===");
        System.out.println("Nombre: " + nombreEvento);
        System.out.println("Fecha: " + fecha);
        System.out.println("Lugar: " + (lugar != null ? lugar.getNombre() : "Sin asignar"));
        System.out.println("Capacidad de asistentes: " + capacidadAsistentes);
        System.out.println("Patrocinador: " + patrocinador);
        System.out.println("Modelos participantes: " + cantidadModelos);
        System.out.println("Fotografos asignados: " + cantidadFotografos);
    }

    // Implementacion del metodo abstracto tipoEvento
    @Override
    public String tipoEvento() {
        return "Publico";
    }

    // toString para guardar en archivo plano
    @Override
    public String toString() {
        return "PUBLICO," + nombreEvento + "," + fecha + ","
                + (lugar != null ? lugar.getNombre() : "SinLugar") + ","
                + capacidadAsistentes + "," + patrocinador;
    }
}