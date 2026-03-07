package src.Classes;

import java.io.File;
import java.io.FileWriter;
import java.io.PrintWriter;
import java.util.Scanner;

// Clase principal del sistema
// Gestiona modelos, fotografos, eventos y lugares usando arreglos
public class Agencia {

    private Modelo[]    modelos;
    private Fotografo[] fotografos;
    private Evento[]    eventos;
    private Lugar[]     lugares;

    private int cantidadModelos;
    private int cantidadFotografos;
    private int cantidadEventos;
    private int cantidadLugares;

    // Los archivos se guardan en la carpeta "datos/" dentro del proyecto
    private static final String CARPETA_DATOS      = "datos/";
    private static final String ARCHIVO_MODELOS    = CARPETA_DATOS + "modelos.txt";
    private static final String ARCHIVO_FOTOGRAFOS = CARPETA_DATOS + "fotografos.txt";
    private static final String ARCHIVO_EVENTOS    = CARPETA_DATOS + "eventos.txt";
    private static final String ARCHIVO_LUGARES    = CARPETA_DATOS + "lugares.txt";

    public Agencia(int capModelos, int capFotografos, int capEventos, int capLugares) {
        modelos    = new Modelo[capModelos];
        fotografos = new Fotografo[capFotografos];
        eventos    = new Evento[capEventos];
        lugares    = new Lugar[capLugares];
    }

    //Getters de contadores y acceso por indice
    public int getCantidadModelos()    { return cantidadModelos; }
    public int getCantidadFotografos() { return cantidadFotografos; }
    public int getCantidadEventos()    { return cantidadEventos; }
    public int getCantidadLugares()    { return cantidadLugares; }

    public Modelo    getModelo(int i)    { return modelos[i]; }
    public Fotografo getFotografo(int i) { return fotografos[i]; }
    public Evento    getEvento(int i)    { return eventos[i]; }
    public Lugar     getLugar(int i)     { return lugares[i]; }

    //Modelos
    public boolean agregarModelo(Modelo m) {
        if (m == null) return false;
        if (cantidadModelos == modelos.length) modelos = crecerModelos();
        modelos[cantidadModelos++] = m;
        return true;
    }

    private Modelo[] crecerModelos() {
        Modelo[] nuevo = new Modelo[modelos.length * 2];
        for (int i = 0; i < cantidadModelos; i++) nuevo[i] = modelos[i];
        return nuevo;
    }

    public boolean eliminarModelo(String codigo) {
        for (int i = 0; i < cantidadModelos; i++) {
            if (modelos[i].getCodigoModelo().equalsIgnoreCase(codigo)) {
                for (int j = i; j < cantidadModelos - 1; j++) modelos[j] = modelos[j + 1];
                modelos[--cantidadModelos] = null;
                return true;
            }
        }
        return false;
    }

    public Modelo buscarModeloPorCodigo(String codigo) {
        for (int i = 0; i < cantidadModelos; i++)
            if (modelos[i].getCodigoModelo().equalsIgnoreCase(codigo)) return modelos[i];
        return null;
    }

    //Fotografos
    public boolean agregarFotografo(Fotografo f) {
        if (f == null) return false;
        if (cantidadFotografos == fotografos.length) fotografos = crecerFotografos();
        fotografos[cantidadFotografos++] = f;
        return true;
    }

    private Fotografo[] crecerFotografos() {
        Fotografo[] nuevo = new Fotografo[fotografos.length * 2];
        for (int i = 0; i < cantidadFotografos; i++) nuevo[i] = fotografos[i];
        return nuevo;
    }

    public boolean eliminarFotografo(String identificacion) {
        for (int i = 0; i < cantidadFotografos; i++) {
            if (fotografos[i].getIdentificacion().equalsIgnoreCase(identificacion)) {
                for (int j = i; j < cantidadFotografos - 1; j++) fotografos[j] = fotografos[j + 1];
                fotografos[--cantidadFotografos] = null;
                return true;
            }
        }
        return false;
    }

    public Fotografo buscarFotografoPorId(String identificacion) {
        for (int i = 0; i < cantidadFotografos; i++)
            if (fotografos[i].getIdentificacion().equalsIgnoreCase(identificacion)) return fotografos[i];
        return null;
    }

    //Eventos
    public boolean agregarEvento(Evento e) {
        if (e == null) return false;
        if (cantidadEventos == eventos.length) eventos = crecerEventos();
        eventos[cantidadEventos++] = e;
        return true;
    }

    private Evento[] crecerEventos() {
        Evento[] nuevo = new Evento[eventos.length * 2];
        for (int i = 0; i < cantidadEventos; i++) nuevo[i] = eventos[i];
        return nuevo;
    }

    public boolean cancelarEvento(String nombre) {
        for (int i = 0; i < cantidadEventos; i++) {
            if (eventos[i].getNombreEvento().equalsIgnoreCase(nombre)) {
                for (int j = i; j < cantidadEventos - 1; j++) eventos[j] = eventos[j + 1];
                eventos[--cantidadEventos] = null;
                return true;
            }
        }
        return false;
    }

    public Evento buscarEventoPorNombre(String nombre) {
        for (int i = 0; i < cantidadEventos; i++)
            if (eventos[i].getNombreEvento().equalsIgnoreCase(nombre)) return eventos[i];
        return null;
    }

    //Lugares
    public boolean agregarLugar(Lugar l) {
        if (l == null) return false;
        if (cantidadLugares == lugares.length) lugares = crecerLugares();
        lugares[cantidadLugares++] = l;
        return true;
    }

    private Lugar[] crecerLugares() {
        Lugar[] nuevo = new Lugar[lugares.length * 2];
        for (int i = 0; i < cantidadLugares; i++) nuevo[i] = lugares[i];
        return nuevo;
    }

    public Lugar buscarLugarPorNombre(String nombre) {
        for (int i = 0; i < cantidadLugares; i++)
            if (lugares[i].getNombre().equalsIgnoreCase(nombre)) return lugares[i];
        return null;
    }

    //Persistencia
    public void guardarDatos() {
        new File(CARPETA_DATOS).mkdirs();

        try (PrintWriter pw = new PrintWriter(new FileWriter(ARCHIVO_MODELOS))) {
            for (int i = 0; i < cantidadModelos; i++) {
                pw.println(modelos[i].toCSV());
                pw.println("---");
            }
        } catch (Exception e) { System.out.println("Error guardando modelos: " + e.getMessage()); }

        try (PrintWriter pw = new PrintWriter(new FileWriter(ARCHIVO_FOTOGRAFOS))) {
            for (int i = 0; i < cantidadFotografos; i++) {
                pw.println(fotografos[i].toCSV());
                pw.println("---");
            }
        } catch (Exception e) { System.out.println("Error guardando fotografos: " + e.getMessage()); }

        try (PrintWriter pw = new PrintWriter(new FileWriter(ARCHIVO_LUGARES))) {
            for (int i = 0; i < cantidadLugares; i++) {
                pw.println(lugares[i].toCSV());
                pw.println("---");
            }
        } catch (Exception e) { System.out.println("Error guardando lugares: " + e.getMessage()); }

        try (PrintWriter pw = new PrintWriter(new FileWriter(ARCHIVO_EVENTOS))) {
            for (int i = 0; i < cantidadEventos; i++) {
                Evento ev = eventos[i];
                if      (ev instanceof EventoPublico) pw.println(((EventoPublico) ev).toCSV());
                else if (ev instanceof EventoPrivado) pw.println(((EventoPrivado) ev).toCSV());

                // Guardar modelos asignados
                for (int j = 0; j < ev.getCantidadModelos(); j++)
                    pw.println("ASIG_MODELO," + ev.getModelo(j).getCodigoModelo());

                // Guardar fotografos asignados
                for (int j = 0; j < ev.getCantidadFotografos(); j++)
                    pw.println("ASIG_FOTO," + ev.getFotografo(j).getIdentificacion());

                pw.println("---");
            }
        } catch (Exception e) { System.out.println("Error guardando eventos: " + e.getMessage()); }

        System.out.println("Archivos guardados: " + ARCHIVO_MODELOS + ", " + ARCHIVO_FOTOGRAFOS
                + ", " + ARCHIVO_LUGARES + ", " + ARCHIVO_EVENTOS);
    }

    public void cargarDatos() {
        // Orden: primero modelos/fotografos/lugares, luego eventos (que los referencian)
        cargarModelos();
        cargarFotografos();
        cargarLugares();
        cargarEventos();
        System.out.println("Archivos cargados: " + cantidadModelos + " modelos, "
                + cantidadFotografos + " fotografos, "
                + cantidadLugares    + " lugares, "
                + cantidadEventos    + " eventos.");
    }

    private void cargarModelos() {
        try {
            File f = new File(ARCHIVO_MODELOS);
            if (!f.exists()) return;
            Scanner sc = new Scanner(f);
            while (sc.hasNextLine()) {
                String linea = sc.nextLine().trim();
                if (!linea.isEmpty() && !linea.equals("---")) {
                    Modelo m = Modelo.fromCSV(linea);
                    if (m != null) agregarModelo(m);
                }
            }
            sc.close();
        } catch (Exception e) { System.out.println("Error cargando modelos: " + e.getMessage()); }
    }

    private void cargarFotografos() {
        try {
            File f = new File(ARCHIVO_FOTOGRAFOS);
            if (!f.exists()) return;
            Scanner sc = new Scanner(f);
            while (sc.hasNextLine()) {
                String linea = sc.nextLine().trim();
                if (!linea.isEmpty() && !linea.equals("---")) {
                    Fotografo fo = Fotografo.fromCSV(linea);
                    if (fo != null) agregarFotografo(fo);
                }
            }
            sc.close();
        } catch (Exception e) { System.out.println("Error cargando fotografos: " + e.getMessage()); }
    }

    private void cargarLugares() {
        try {
            File f = new File(ARCHIVO_LUGARES);
            if (!f.exists()) return;
            Scanner sc = new Scanner(f);
            while (sc.hasNextLine()) {
                String linea = sc.nextLine().trim();
                if (!linea.isEmpty() && !linea.equals("---")) {
                    String[] p = linea.split(",");
                    if (p.length >= 5)
                        agregarLugar(new Lugar(p[0], p[1], p[2],
                                Integer.parseInt(p[3].trim()), p[4]));
                }
            }
            sc.close();
        } catch (Exception e) { System.out.println("Error cargando lugares: " + e.getMessage()); }
    }

    private void cargarEventos() {
        try {
            File f = new File(ARCHIVO_EVENTOS);
            if (!f.exists()) return;
            Scanner sc = new Scanner(f);
            Evento eventoActual = null;

            while (sc.hasNextLine()) {
                String linea = sc.nextLine().trim();
                if (linea.isEmpty()) continue;

                if (linea.equals("---")) {
                    // Fin del bloque del evento actual
                    eventoActual = null;
                    continue;
                }

                String[] p = linea.split(",");

                if (p[0].equals("PUBLICO") && p.length >= 6) {
                    java.time.LocalDate fecha = Dates.parse(p[2]);
                    Lugar lugar = buscarLugarPorNombre(p[3]);
                    eventoActual = new EventoPublico(p[1], fecha, lugar,
                            Integer.parseInt(p[4].trim()), p[5]);
                    agregarEvento(eventoActual);

                } else if (p[0].equals("PRIVADO") && p.length >= 6) {
                    java.time.LocalDate fecha = Dates.parse(p[2]);
                    Lugar lugar = buscarLugarPorNombre(p[3]);
                    eventoActual = new EventoPrivado(p[1], fecha, lugar, p[4], p[5]);
                    agregarEvento(eventoActual);

                } else if (p[0].equals("ASIG_MODELO") && p.length >= 2 && eventoActual != null) {
                    // Reconstruir la asignacion buscando el modelo ya cargado
                    Modelo m = buscarModeloPorCodigo(p[1].trim());
                    if (m != null) eventoActual.agregarModelo(m);

                } else if (p[0].equals("ASIG_FOTO") && p.length >= 2 && eventoActual != null) {
                    // Reconstruir la asignacion buscando el fotografo ya cargado
                    Fotografo fo = buscarFotografoPorId(p[1].trim());
                    if (fo != null) eventoActual.agregarFotografo(fo);
                }
            }
            sc.close();
        } catch (Exception e) { System.out.println("Error cargando eventos: " + e.getMessage()); }
    }
}