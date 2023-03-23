package notaslinkia;

import com.db4o.Db4oEmbedded;
import com.db4o.ObjectContainer;
import com.db4o.ObjectSet;
import com.db4o.query.Query;

import resources.Historial;


import java.util.List;
import java.util.Scanner;


public class HistorialBDOR {
    // Atributo para la entrada de datos por teclado
    private Scanner sc = new Scanner(System.in);

    // Atributo para la persistencia de OO
    private ObjectContainer db;


    public HistorialBDOR () {
     
    }


    public void insertarHistorial(Historial historial) {
        db.store(historial);
    }

    public void eliminarHistorial(Historial historial) {
        db.delete(historial);
    }

    public void modificarHistorial(Historial historial) {
        db.store(historial);
    }

    public List<Historial> listarHistorial() {
        try{
            Query query = db.query();
            query.constrain(Historial.class);
            ObjectSet<Historial> result = query.execute();

            return result;
        }catch(Exception e){

            return null;
           
        }
    }
    

    public Historial buscarHistorial(int id) {
        Query query = db.query();
        query.constrain(Historial.class);
        query.descend("id").constrain(id);
        ObjectSet<Historial> result = query.execute();
        if (result.size() > 0) {
            return result.get(0);
        } else {
            return null;
        }
    }

    public Historial buscarHistorial(String tipo) {
        Query query = db.query();
        query.constrain(Historial.class);
        query.descend("tipo").constrain(tipo);
        ObjectSet<Historial> result = query.execute();
        if (result.size() > 0) {
            return result.get(0);
        } else {
            return null;
        }
    }

    public Historial buscarHistorial(int user, String tipo) {
        Query query = db.query();
        query.constrain(Historial.class);
        query.descend("user").constrain(user);
        query.descend("tipo").constrain(tipo);
        ObjectSet<Historial> result = query.execute();
        if (result.size() > 0) {
            return result.get(0);
        } else {
            return null;
        }
    }

    public Historial buscarHistorial(int user, String tipo, String detalle) {
        Query query = db.query();
        query.constrain(Historial.class);
        query.descend("user").constrain(user);
        query.descend("tipo").constrain(tipo);
        query.descend("detalle").constrain(detalle);
        ObjectSet<Historial> result = query.execute();
        if (result.size() > 0) {
            return result.get(0);
        } else {
            return null;
        }
    }

}
