package Classes;
import Classes.Dates;

import java.time.LocalDate;

// EventoPublico hereda de Evento
// Evento abierto al publico general
public class EventoPublico extends Evento {

    private int    capacidadAsistentes;
    private String patrocinador;

    public EventoPublico(String nombreEvento, LocalDate fecha, Lugar lugar,
                         int capacidadAsistentes, String patrocinador) {
        super(nombreEvento, fecha, lugar);
        this.capacidadAsistentes = capacidadAsistentes;
        this.patrocinador        = patrocinador;
    }

    public int    getCapacidadAsistentes() { return capacidadAsistentes; }
    public String getPatrocinador()        { return patrocinador; }

    public void setCapacidadAsistentes(int c) { this.capacidadAsistentes = c; }
    public void setPatrocinador(String p)     { this.patrocinador = p; }

    @Override
    public String tipoEvento() { return "Publico"; }

    @Override
    public String mostrarDetalles() {
        // Reutilizamos baseDetalle() del padre y agregamos los campos propios
        return baseDetalle()
                + "Capacidad asistentes: " + capacidadAsistentes + "\n"
                + "Patrocinador: "         + patrocinador        + "\n";
    }

    // Para guardar en archivo plano
    public String toCSV() {
        return "PUBLICO," + nombreEvento + "," + Dates.format(fecha) + ","
                + (lugar != null ? lugar.getNombre() : "SinLugar") + ","
                + capacidadAsistentes + "," + patrocinador;
    }
}
