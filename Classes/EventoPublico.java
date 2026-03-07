package Classes;

import java.time.LocalDate;

public class EventoPublico extends Evento{
    private int capacidadAsistentes;
    private String patrocinador;

    protected EventoPublico(String nombreEvento, LocalDate fecha, Lugar lugar) {
        super(nombreEvento, fecha, lugar);
    }

    public EventoPublico(String nombreEvento, LocalDate fecha, Lugar lugar, int capacidadAsistentes, String patrocinador) {
        super(nombreEvento, fecha, lugar);
        this.capacidadAsistentes = capacidadAsistentes;
        this.patrocinador = patrocinador;
    }

    @Override
    public String mostrarDetalles() {
        // Reutilizamos baseDetalle() del padre y agregamos los campos propios
        return baseDetalle()
                + "Capacidad asistentes: " + capacidadAsistentes + "\n"
                + "Patrocinador: "         + patrocinador        + "\n";
    }

    @Override
    public String tipoEvento() {
        return "Publico";
    }

    // Para guardar en archivo plano
    public String toCSV() {
        return "PUBLICO," + nombreEvento + "," + Dates.format(fecha) + ","
                + (lugar != null ? lugar.getNombre() : "SinLugar") + ","
                + capacidadAsistentes + "," + patrocinador;
    }

    public int getCapacidadAsistentes() {
        return capacidadAsistentes;
    }

    public void setCapacidadAsistentes(int capacidadAsistentes) {
        this.capacidadAsistentes = capacidadAsistentes;
    }

    public String getPatrocinador() {
        return patrocinador;
    }

    public void setPatrocinador(String patrocinador) {
        this.patrocinador = patrocinador;
    }


}
