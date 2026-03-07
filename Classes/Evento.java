package Classes;
import Classes.Dates;

import java.time.LocalDate;

public abstract class Evento {
    protected String nombreEvento;
    protected LocalDate fecha;
    protected Lugar lugar;

    protected Modelo[] modelosParticipantes;
    protected int modelosCount;

    protected Fotografo[] fotografosAsignados;
    protected int fotografosCount;

    protected Evento(String nombreEvento, LocalDate fecha, Lugar lugar) {
        this.nombreEvento = nombreEvento;
        this.fecha = fecha;
        this.lugar = lugar;

        this.modelosParticipantes = new Modelo[10];
        this.fotografosAsignados = new Fotografo[10];
        this.modelosCount = 0;
        this.fotografosCount = 0;
    }

    public String getNombreEvento() { return nombreEvento; }
    public LocalDate getFecha() { return fecha; }
    public Lugar getLugar() { return lugar; }

    public void setLugar(Lugar lugar) { this.lugar = lugar; }

    public int getCantidadModelos() { return modelosCount; }
    public int getCantidadFotografos() { return fotografosCount; }

    public Modelo getModelo(int i) {
        if (i < 0 || i >= modelosCount) return null;
        return modelosParticipantes[i];
    }

    public Fotografo getFotografo(int i) {
        if (i < 0 || i >= fotografosCount) return null;
        return fotografosAsignados[i];
    }

    public boolean agregarModelo(Modelo m) {
        if (m == null) return false;
        // evitar duplicados por código
        for (int i = 0; i < modelosCount; i++) {
            if (modelosParticipantes[i] != null &&
                    modelosParticipantes[i].getCodigoModelo().equalsIgnoreCase(m.getCodigoModelo())) {
                return false;
            }
        }
        ensureModelosCapacity(modelosCount + 1);
        modelosParticipantes[modelosCount] = m;
        modelosCount++;
        return true;
    }

    public boolean agregarFotografo(Fotografo f) {
        if (f == null) return false;
        // evitar duplicados por id
        for (int i = 0; i < fotografosCount; i++) {
            if (fotografosAsignados[i] != null &&
                    fotografosAsignados[i].getIdentificacion().equalsIgnoreCase(f.getIdentificacion())) {
                return false;
            }
        }
        ensureFotografosCapacity(fotografosCount + 1);
        fotografosAsignados[fotografosCount] = f;
        fotografosCount++;
        return true;
    }

    private void ensureModelosCapacity(int needed) {
        if (needed <= modelosParticipantes.length) return;
        int newCap = modelosParticipantes.length * 2;
        if (newCap < needed) newCap = needed;
        Modelo[] nuevo = new Modelo[newCap];
        for (int i = 0; i < modelosCount; i++) nuevo[i] = modelosParticipantes[i];
        modelosParticipantes = nuevo;
    }

    private void ensureFotografosCapacity(int needed) {
        if (needed <= fotografosAsignados.length) return;
        int newCap = fotografosAsignados.length * 2;
        if (newCap < needed) newCap = needed;
        Fotografo[] nuevo = new Fotografo[newCap];
        for (int i = 0; i < fotografosCount; i++) nuevo[i] = fotografosAsignados[i];
        fotografosAsignados = nuevo;
    }

    public abstract String mostrarDetalles();
    public abstract String tipoEvento();

    protected String baseDetalle() {
        StringBuilder sb = new StringBuilder();
        sb.append("Evento ").append(tipoEvento()).append(": ").append(nombreEvento).append("\n");
        sb.append("Fecha: ").append(Dates.format(fecha)).append("\n");
        sb.append("Lugar: ").append(lugar == null ? "N/A" : lugar.toString()).append("\n");
        sb.append("Modelos (").append(modelosCount).append("):\n");
        for (int i = 0; i < modelosCount; i++) {
            sb.append(" - ").append(modelosParticipantes[i].toString()).append("\n");
        }
        sb.append("Fotógrafos (").append(fotografosCount).append("):\n");
        for (int i = 0; i < fotografosCount; i++) {
            sb.append(" - ").append(fotografosAsignados[i].toString()).append("\n");
        }
        return sb.toString();
    }
}
