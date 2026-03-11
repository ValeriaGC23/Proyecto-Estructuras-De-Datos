package src.Classes;

import java.io.Serializable;

// Clase EventoPrivado que hereda de Evento
// Evento exclusivo para clientes VIP o marcas
public class EventoPrivado extends Evento implements Serializable {

    private static final long serialVersionUID = 6L;

    private String cliente;
    private String nivelConfidencialidad;

    // Constructor de EventoPrivado
    public EventoPrivado(String nombreEvento, String fecha, Lugar lugar,
                         String cliente, String nivelConfidencialidad) {
        // Llamamos al constructor del padre (Evento)
        super(nombreEvento, fecha, lugar);
        this.cliente = cliente;
        this.nivelConfidencialidad = nivelConfidencialidad;
    }

    // Getters
    public String getCliente() {
        return cliente;
    }

    public String getNivelConfidencialidad() {
        return nivelConfidencialidad;
    }

    // Setters
    public void setCliente(String cliente) {
        this.cliente = cliente;
    }

    public void setNivelConfidencialidad(String nivelConfidencialidad) {
        this.nivelConfidencialidad = nivelConfidencialidad;
    }

    // Implementacion del metodo abstracto mostrarDetalles
    @Override
    public void mostrarDetalles() {
        System.out.println("=== EVENTO PRIVADO ===");
        System.out.println("Nombre: " + nombreEvento);
        System.out.println("Fecha: " + fecha);
        System.out.println("Lugar: " + (lugar != null ? lugar.getNombre() : "Sin asignar"));
        System.out.println("Cliente: " + cliente);
        System.out.println("Nivel de confidencialidad: " + nivelConfidencialidad);
        System.out.println("Modelos participantes: " + cantidadModelos);
        System.out.println("Fotografos asignados: " + cantidadFotografos);
    }

    // Implementacion del metodo abstracto tipoEvento
    @Override
    public String tipoEvento() {
        return "Privado";
    }

    // toString para guardar en archivo plano
    @Override
    public String toString() {
        return "PRIVADO," + nombreEvento + "," + fecha + ","
                + (lugar != null ? lugar.getNombre() : "SinLugar") + ","
                + cliente + "," + nivelConfidencialidad;
    }
}