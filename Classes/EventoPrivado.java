package Classes;

import java.time.LocalDate;

public class EventoPrivado extends Evento {
    private String cliente;
    private String nivelConfidencialidad;

    protected EventoPrivado(String nombreEvento, LocalDate fecha, Lugar lugar) {
        super(nombreEvento, fecha, lugar);
    }

    public EventoPrivado(String nombreEvento, LocalDate fecha, Lugar lugar, String cliente, String nivelConfidencialidad) {
        super(nombreEvento, fecha, lugar);
        this.cliente = cliente;
        this.nivelConfidencialidad = nivelConfidencialidad;
    }

    @Override
    public String mostrarDetalles() {
        return "";
    }

    @Override
    public String tipoEvento() {
        return "";
    }

    public String getCliente() {
        return cliente;
    }

    public void setCliente(String cliente) {
        this.cliente = cliente;
    }

    public String getNivelConfidencialidad() {
        return nivelConfidencialidad;
    }

    public void setNivelConfidencialidad(String nivelConfidencialidad) {
        this.nivelConfidencialidad = nivelConfidencialidad;
    }
}
