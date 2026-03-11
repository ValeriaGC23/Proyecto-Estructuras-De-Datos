package src.Classes;
import java.io.Serializable;

// Clase abstracta Evento
// Es la clase base para EventoPublico y EventoPrivado
// Usa arreglos para guardar modelos y fotografos (no se permite ArrayList)
public abstract class Evento implements Serializable {

    private static final long serialVersionUID = 4L;

    protected String nombreEvento;
    protected String fecha;           // Guardamos la fecha como String para simplificar
    protected Lugar lugar;
    protected Modelo[] modelosParticipantes;
    protected Fotografo[] fotografosAsignados;

    // Contadores para saber cuantos hay actualmente en el arreglo
    protected int cantidadModelos;
    protected int cantidadFotografos;

    // Tamano maximo de los arreglos
    private static final int MAX_MODELOS = 50;
    private static final int MAX_FOTOGRAFOS = 20;

    // Constructor de Evento
    public Evento(String nombreEvento, String fecha, Lugar lugar) {
        this.nombreEvento = nombreEvento;
        this.fecha = fecha;
        this.lugar = lugar;

        // Inicializamos los arreglos con tamano maximo
        this.modelosParticipantes = new Modelo[MAX_MODELOS];
        this.fotografosAsignados = new Fotografo[MAX_FOTOGRAFOS];
        this.cantidadModelos = 0;
        this.cantidadFotografos = 0;
    }

    // Getters
    public String getNombreEvento() {
        return nombreEvento;
    }

    public String getFecha() {
        return fecha;
    }

    public Lugar getLugar() {
        return lugar;
    }

    public Modelo[] getModelosParticipantes() {
        return modelosParticipantes;
    }

    public Fotografo[] getFotografosAsignados() {
        return fotografosAsignados;
    }

    public int getCantidadModelos() {
        return cantidadModelos;
    }

    public int getCantidadFotografos() {
        return cantidadFotografos;
    }

    // Setters
    public void setNombreEvento(String nombreEvento) {
        this.nombreEvento = nombreEvento;
    }

    public void setFecha(String fecha) {
        this.fecha = fecha;
    }

    public void setLugar(Lugar lugar) {
        this.lugar = lugar;
    }

    // Metodo para agregar un modelo al evento
    public void agregarModeloParticipante(Modelo modelo) {
        if (cantidadModelos < modelosParticipantes.length) {
            modelosParticipantes[cantidadModelos] = modelo;
            cantidadModelos++;
        } else {
            System.out.println("No se pueden agregar mas modelos al evento.");
        }
    }

    // Metodo para agregar un fotografo al evento
    public void agregarFotografoAsignado(Fotografo fotografo) {
        if (cantidadFotografos < fotografosAsignados.length) {
            fotografosAsignados[cantidadFotografos] = fotografo;
            cantidadFotografos++;
        } else {
            System.out.println("No se pueden agregar mas fotografos al evento.");
        }
    }

    // Metodo abstracto: cada subclase lo implementa
    public abstract void mostrarDetalles();

    // Metodo abstracto: devuelve el tipo de evento
    public abstract String tipoEvento();
}