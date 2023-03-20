package notaslinkia;

import com.db4o.ObjectSet;
import com.db4o.query.Query;

import resources.Historial;

import java.util.List;


public class HistorialBDOR {

    // Constructor de la clase
    public HistorialBDOR() {
        
    }

    // Insertar un historial en la base de datos
    public void insertarHistorial(Historial historial) {
        // Abrimos la conexión
        Conexion conexion = new Conexion();
        conexion.abrirConexion();
        // Insertamos el historial
        conexion.getDb().store(historial);
        // Cerramos la conexión
        conexion.cerrarConexion();
    }

    // Listar historial de cambios
    public List<Historial> listarHistorial() {
        // Abrimos la conexión
        Conexion conexion = new Conexion();
        conexion.abrirConexion();
        // Creamos la consulta
        Query query = conexion.getDb().query();
        query.constrain(Historial.class);
        // Ejecutamos la consulta
        ObjectSet<Historial> resultado = query.execute();
        // Cerramos la conexión
        conexion.cerrarConexion();
        // Devolvemos el resultado
        return resultado;
    }

    // Listar historial por tipo de cambio
    public List<Historial> listarHistorialPorTipo(String tipo) {
        // Abrimos la conexión
        Conexion conexion = new Conexion();
        conexion.abrirConexion();
        // Creamos la consulta
        Query query = conexion.getDb().query();
        query.constrain(Historial.class);
        query.descend("tipo").constrain(tipo);
        // Ejecutamos la consulta
        ObjectSet<Historial> resultado = query.execute();
        // Cerramos la conexión
        conexion.cerrarConexion();
        // Devolvemos el resultado
        return resultado;
    }

}