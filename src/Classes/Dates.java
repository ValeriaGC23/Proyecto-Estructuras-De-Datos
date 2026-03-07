package src.Classes;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

// Clase para formatear fechas
// Se usa en Evento.baseDetalle() y en la interfaz grafica
public class Dates {

    private static final DateTimeFormatter FORMATO = DateTimeFormatter.ofPattern("dd/MM/yyyy");

    // Convierte un LocalDate a String con formato dd/MM/yyyy
    public static String format(LocalDate fecha) {
        if (fecha == null) return "Sin fecha";
        return fecha.format(FORMATO);
    }

    // Convierte un String "dd/MM/yyyy" a LocalDate. Retorna null si falla.
    public static LocalDate parse(String texto) {
        try {
            return LocalDate.parse(texto.trim(), FORMATO);
        } catch (Exception e) {
            return null;
        }
    }

    // Construye un LocalDate desde dia, mes y año por separado
    public static LocalDate construir(int dia, int mes, int anio) {
        try {
            return LocalDate.of(anio, mes, dia);
        } catch (Exception e) {
            return null;
        }
    }
}

