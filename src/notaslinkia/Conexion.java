package notaslinkia;

import com.db4o.ObjectContainer;

public class Conexion {


    // Atributos de la clase
    private static String ruta = "C:\\Users\\Usuario\\Documents\\NetBeansProjects\\NotasLinkia\\src\\notaslinkia\\bd.db4o";
    private com.db4o.ObjectContainer db;
    private static final ObjectContainer OPEN_FILE = com.db4o.Db4o.openFile(ruta);

    // Constructor de la clase
    public Conexion() {
        // Abrimos la conexión
        abrirConexion();
    }

    // Método para abrir la conexión
    public void abrirConexion() {
        // Abrimos la conexión
        db = OPEN_FILE;
    }

    // Método para cerrar la conexión
    public void cerrarConexion() {
        // Cerramos la conexión
        db.close();
    }

    // Método para obtener la conexión
    public com.db4o.ObjectContainer getDb() {
        return db;
    }

    // Método para establecer la conexión
    public void setDb(com.db4o.ObjectContainer db) {
        this.db = db;
    }
}
