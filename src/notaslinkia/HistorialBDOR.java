package notaslinkia;

import com.db4o.Db4oEmbedded;
import com.db4o.ObjectContainer;
import com.db4o.ObjectSet;
import com.db4o.query.Query;

import resources.Alumno;
import resources.Historial;
import resources.Profesor;

import java.util.List;
import java.util.Scanner;

public class HistorialBDOR {
    // Atributo para la entrada de datos por teclado
    private Scanner sc = new Scanner(System.in);

    // Atributo para la persistencia de OO
    private ObjectContainer db;

    public HistorialBDOR() {

        db = Db4oEmbedded.openFile(Db4oEmbedded.newConfiguration(), "historial.db4o");
    }

    // Método para cerrar la base de datos
    public void cerrarBD() {
        db.close();
    }

    // Método para insertar un objeto en la base de datos
    public void insertar(Historial historial) {
        db.store(historial);
    }

    // Método para eliminar un objeto de la base de datos
    public void eliminar(Historial historial) {
        db.delete(historial);
    }

    // Método para modificar un objeto de la base de datos
    public void modificar(Historial historial) {
        db.store(historial);
    }

    // Método para listar todos los objetos de la base de datos
    public void listar() {
        try {
            Query query = db.query();
            query.constrain(Historial.class);
            ObjectSet<Historial> result = query.execute();
            // Imprimir los encabezados en tabla con formato
            System.out.println(
                    "+--------+-------------+-------------+-------------------------------------------+-----------------------+");
            System.out.printf(
                    "| \033[38;5;206m%-5s\033[0m  | \033[38;5;206m%-10s\033[0m  | \033[38;5;206m%-10s\033[0m  | \033[38;5;206m%-40s\033[0m  | \033[38;5;206m%-20s\033[0m  | %n",
                    "ID", "TIPO", "USER", "DETALLE", "TIEMPO STAMP");
            System.out.println(
                    "+--------+-------------+-------------+-------------------------------------------+-----------------------+");
            // Imprimir los datos de los objetos en tabla con formato
            while (result.hasNext()) {
                Historial historial = result.next();
                System.out.printf("| %-6s | %-11s | %-11s | %-41s | %-21s | %n", historial.getId(), historial.getTipo(),
                        historial.getUser(), historial.getDetalle(),
                        historial.getTiempoStamp());
            }
            System.out.println(
                    "+--------+-------------+-------------+-------------------------------------------+-----------------------+");
        } catch (Exception e) {
            System.out.println("No se ha podido listar los historiales");
        }
    }

    // Calcular el número de objetos de la base de datos
    public int contar() {
        Query query = db.query();
        query.constrain(Historial.class);
        ObjectSet<Historial> result = query.execute();
        return result.size();
    }

    public void insertarHistorial(String user, String tipo, String detalle) {
        try {
            String timeStamp = new java.text.SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new java.util.Date());
            Historial historial = new Historial();
            historial.setId(contar() + 1);
            historial.setTipo(tipo);
            historial.setUser(1);
            historial.setDetalle(detalle);
            historial.setTiempoStamp(timeStamp);
            insertar(historial);
        } catch (Exception e) {
            System.out.println("No se ha podido insertar el historial");
        }
    }

}
