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
        return "";
    }

    @Override
    public String tipoEvento() {
        return "";
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
