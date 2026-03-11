package src.Classes;

import java.io.File;
import java.io.FileOutputStream;
import java.io.FileInputStream;
import java.io.ObjectOutputStream;
import java.io.ObjectInputStream;

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
    private static final String ARCHIVO_MODELOS    = CARPETA_DATOS + "modelos.dat";
    private static final String ARCHIVO_FOTOGRAFOS = CARPETA_DATOS + "fotografos.dat";
    private static final String ARCHIVO_EVENTOS    = CARPETA_DATOS + "eventos.dat";
    private static final String ARCHIVO_LUGARES    = CARPETA_DATOS + "lugares.dat";

    public Agencia(int capModelos, int capFotografos, int capEventos, int capLugares) {
        modelos    = new Modelo[capModelos];
        fotografos = new Fotografo[capFotografos];
        eventos    = new Evento[capEventos];
        lugares    = new Lugar[capLugares];
    }

    // Getters de contadores y acceso por indice 
    public int getCantidadModelos()    { return cantidadModelos; }
    public int getCantidadFotografos() { return cantidadFotografos; }
    public int getCantidadEventos()    { return cantidadEventos; }
    public int getCantidadLugares()    { return cantidadLugares; }

    public Modelo    getModelo(int i)    { return modelos[i]; }
    public Fotografo getFotografo(int i) { return fotografos[i]; }
    public Evento    getEvento(int i)    { return eventos[i]; }
    public Lugar     getLugar(int i)     { return lugares[i]; }

    // Modelos 
    public boolean agregarModelo(Modelo m) {
        if (m == null) return false;
        // No permitir duplicados por identificacion o por codigo
        for (int i = 0; i < cantidadModelos; i++) {
            if (modelos[i].getIdentificacion().equalsIgnoreCase(m.getIdentificacion())) return false;
            if (modelos[i].getCodigoModelo().equalsIgnoreCase(m.getCodigoModelo())) return false;
        }
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

    // Fotografos 
    public boolean agregarFotografo(Fotografo f) {
        if (f == null) return false;
        // No permitir duplicados por identificacion
        for (int i = 0; i < cantidadFotografos; i++) {
            if (fotografos[i].getIdentificacion().equalsIgnoreCase(f.getIdentificacion())) return false;
        }
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

    // Eventos 
    public boolean agregarEvento(Evento e) {
        if (e == null) return false;
        // No permitir duplicados por nombre
        for (int i = 0; i < cantidadEventos; i++) {
            if (eventos[i].getNombreEvento().equalsIgnoreCase(e.getNombreEvento())) return false;
        }
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

    // Lugares 
    public boolean agregarLugar(Lugar l) {
        if (l == null) return false;
        // No permitir duplicados por nombre
        for (int i = 0; i < cantidadLugares; i++) {
            if (lugares[i].getNombre().equalsIgnoreCase(l.getNombre())) return false;
        }
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

    // Persistencia con Serializable 
    // Cada arreglo se guarda en su propio archivo usando ObjectOutputStream.
    // Los objetos deben implementar Serializable para poder ser serializados.

    public void guardarDatos() {
        new File(CARPETA_DATOS).mkdirs();

        //GUARDAR MODELOS
        try (ObjectOutputStream oos = new ObjectOutputStream(
                new FileOutputStream(ARCHIVO_MODELOS))) {
            // Primero escribimos cuantos hay, luego cada objeto
            oos.writeInt(cantidadModelos);
            for (int i = 0; i < cantidadModelos; i++) {
                oos.writeObject(modelos[i]);
            }
        } catch (Exception e) { System.out.println("Error guardando modelos: " + e.getMessage()); }

        //GUARDAR FOTOGRAFOS
        try (ObjectOutputStream oos = new ObjectOutputStream(
                new FileOutputStream(ARCHIVO_FOTOGRAFOS))) {
            oos.writeInt(cantidadFotografos);
            for (int i = 0; i < cantidadFotografos; i++) {
                oos.writeObject(fotografos[i]);
            }
        } catch (Exception e) { System.out.println("Error guardando fotografos: " + e.getMessage()); }

        //GUARDAR LUGARES
        try (ObjectOutputStream oos = new ObjectOutputStream(
                new FileOutputStream(ARCHIVO_LUGARES))) {
            oos.writeInt(cantidadLugares);
            for (int i = 0; i < cantidadLugares; i++) {
                oos.writeObject(lugares[i]);
            }
        } catch (Exception e) { System.out.println("Error guardando lugares: " + e.getMessage()); }

        //GUARDAR EVENTOS (incluye modelos y fotografos asignados dentro del objeto)
        try (ObjectOutputStream oos = new ObjectOutputStream(
                new FileOutputStream(ARCHIVO_EVENTOS))) {
            oos.writeInt(cantidadEventos);
            for (int i = 0; i < cantidadEventos; i++) {
                oos.writeObject(eventos[i]);
            }
        } catch (Exception e) { System.out.println("Error guardando eventos: " + e.getMessage()); }

        System.out.println("Datos serializados: " + cantidadModelos + " modelos, "
                + cantidadFotografos + " fotografos, "
                + cantidadLugares    + " lugares, "
                + cantidadEventos    + " eventos.");
    }

    public void cargarDatos() {
        //CARGAR MODELOS
        try (ObjectInputStream ois = new ObjectInputStream(
                new FileInputStream(ARCHIVO_MODELOS))) {
            int cantidad = ois.readInt();
            for (int i = 0; i < cantidad; i++) {
                Modelo m = (Modelo) ois.readObject();
                agregarModelo(m);
            }
        } catch (Exception e) { System.out.println("No se encontraron modelos guardados."); }

        //CARGAR FOTOGRAFOS
        try (ObjectInputStream ois = new ObjectInputStream(
                new FileInputStream(ARCHIVO_FOTOGRAFOS))) {
            int cantidad = ois.readInt();
            for (int i = 0; i < cantidad; i++) {
                Fotografo f = (Fotografo) ois.readObject();
                agregarFotografo(f);
            }
        } catch (Exception e) { System.out.println("No se encontraron fotografos guardados."); }

        //CARGAR LUGARES
        try (ObjectInputStream ois = new ObjectInputStream(
                new FileInputStream(ARCHIVO_LUGARES))) {
            int cantidad = ois.readInt();
            for (int i = 0; i < cantidad; i++) {
                Lugar l = (Lugar) ois.readObject();
                agregarLugar(l);
            }
        } catch (Exception e) { System.out.println("No se encontraron lugares guardados."); }

        //CARGAR EVENTOS (ya traen sus modelos y fotografos asignados dentro del objeto)
        try (ObjectInputStream ois = new ObjectInputStream(
                new FileInputStream(ARCHIVO_EVENTOS))) {
            int cantidad = ois.readInt();
            for (int i = 0; i < cantidad; i++) {
                Evento ev = (Evento) ois.readObject();
                agregarEvento(ev);
            }
        } catch (Exception e) { System.out.println("No se encontraron eventos guardados."); }

        System.out.println("Datos cargados: " + cantidadModelos + " modelos, "
                + cantidadFotografos + " fotografos, "
                + cantidadLugares    + " lugares, "
                + cantidadEventos    + " eventos.");
    }
}