package Classes;
import Classes.Dates;

import java.time.LocalDate;

// EventoPrivado hereda de Evento
// Evento exclusivo para clientes VIP o marcas
public class EventoPrivado extends Evento {

    private String cliente;
    private String nivelConfidencialidad;

    public EventoPrivado(String nombreEvento, LocalDate fecha, Lugar lugar,
                         String cliente, String nivelConfidencialidad) {
        super(nombreEvento, fecha, lugar);
        this.cliente               = cliente;
        this.nivelConfidencialidad = nivelConfidencialidad;
    }

    public String getCliente()                { return cliente; }
    public String getNivelConfidencialidad()  { return nivelConfidencialidad; }

    public void setCliente(String c)               { this.cliente = c; }
    public void setNivelConfidencialidad(String n) { this.nivelConfidencialidad = n; }

    @Override
    public String tipoEvento() { return "Privado"; }

    @Override
    public String mostrarDetalles() {
        return baseDetalle()
                + "Cliente: "                + cliente               + "\n"
                + "Nivel confidencialidad: " + nivelConfidencialidad + "\n";
    }

    public String toCSV() {
        return "PRIVADO," + nombreEvento + "," + Dates.format(fecha) + ","
                + (lugar != null ? lugar.getNombre() : "SinLugar") + ","
                + cliente + "," + nivelConfidencialidad;
    }
}
