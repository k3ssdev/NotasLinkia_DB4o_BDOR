package notaslinkia;

import com.db4o.ObjectSet;
import com.db4o.query.Query;

import resources.Alumno;
import resources.Modulo;
import resources.Notas;
import resources.Profesor;

import java.util.List;


/**
 *
 * @author alber
 */
public class NotasORM {

    // Constructor de la clase
    public NotasORM() {

    }

    // Método para insertar un profesor en la base de datos
    public void insertarProfesor(Profesor profesor) {
        // Abrimos la conexión
        Conexion conexion = new Conexion();
        conexion.abrirConexion();
        // Insertamos el profesor
        conexion.getDb().store(profesor);
        // Cerramos la conexión
        conexion.cerrarConexion();
    }

    // Metodo para eliminar un profesor de la base de datos
    public void eliminarProfesor(Profesor profesor) {
        // Abrimos la conexión
        Conexion conexion = new Conexion();
        conexion.abrirConexion();
        // Eliminamos el profesor
        conexion.getDb().delete(profesor);
        // Cerramos la conexión
        conexion.cerrarConexion();
    }

    // Validar login de profesor
    public boolean validarLoginProfesor(Profesor profesor) {
        // Abrimos la conexión
        Conexion conexion = new Conexion();
        conexion.abrirConexion();
        // Creamos la consulta
        Query query = conexion.getDb().query();
        query.constrain(Profesor.class);
        query.descend("nomUser").constrain(profesor.getNomUser());
        query.descend("password").constrain(profesor.getPassword());
        // Ejecutamos la consulta
        ObjectSet<Profesor> resultado = query.execute();
        // Cerramos la conexión
        conexion.cerrarConexion();
        // Devolvemos el resultado
        return resultado.size() > 0;
    }

    // Validar login de alumno
    public boolean validarLoginAlumno(Alumno alumno) {
        // Abrimos la conexión
        Conexion conexion = new Conexion();
        conexion.abrirConexion();
        // Creamos la consulta
        Query query = conexion.getDb().query();
        query.constrain(Alumno.class);
        query.descend("nomUser").constrain(alumno.getNomUser());
        query.descend("password").constrain(alumno.getPassword());
        // Ejecutamos la consulta
        ObjectSet<Alumno> resultado = query.execute();
        // Cerramos la conexión
        conexion.cerrarConexion();
        // Devolvemos el resultado
        return resultado.size() > 0;
    }

    // Metodo para insertar modulo
    public void insertarModulo(Modulo modulo) {
        // Abrimos la conexión
        Conexion conexion = new Conexion();
        conexion.abrirConexion();
        // Insertamos el modulo
        conexion.getDb().store(modulo);
        // Cerramos la conexión
        conexion.cerrarConexion();
    }

    // Metodo para eliminar modulo
    public void eliminarModulo(Modulo modulo) {
        // Abrimos la conexión
        Conexion conexion = new Conexion();
        conexion.abrirConexion();
        // Eliminamos el modulo
        conexion.getDb().delete(modulo);
        // Cerramos la conexión
        conexion.cerrarConexion();
    }

    // Metodo par alistar todos los modulos
    public List<Modulo> listarModulos() {
        // Abrimos la conexión
        Conexion conexion = new Conexion();
        conexion.abrirConexion();
        // Creamos la consulta
        Query query = conexion.getDb().query();
        query.constrain(Modulo.class);
        // Ejecutamos la consulta
        ObjectSet<Modulo> resultado = query.execute();
        // Cerramos la conexión
        conexion.cerrarConexion();
        // Devolvemos el resultado
        return resultado;
    }

    // Metodo para insertar alumno
    public void insertarAlumno(Alumno alumno) {
        // Abrimos la conexión
        Conexion conexion = new Conexion();
        conexion.abrirConexion();
        // Insertamos el alumno
        conexion.getDb().store(alumno);
        // Cerramos la conexión
        conexion.cerrarConexion();
    }

    // Metodo para eliminar alumno
    public void eliminarAlumno(Alumno alumno) {
        // Abrimos la conexión
        Conexion conexion = new Conexion();
        conexion.abrirConexion();
        // Eliminamos el alumno
        conexion.getDb().delete(alumno);
        // Cerramos la conexión
        conexion.cerrarConexion();
    }

    // Metodo para listar todos los alumnos
    public List<Alumno> listarAlumnos() {
        // Abrimos la conexión
        Conexion conexion = new Conexion();
        conexion.abrirConexion();
        // Creamos la consulta
        Query query = conexion.getDb().query();
        query.constrain(Alumno.class);
        // Ejecutamos la consulta
        ObjectSet<Alumno> resultado = query.execute();
        // Cerramos la conexión
        conexion.cerrarConexion();
        // Devolvemos el resultado
        return resultado;
    }

    // Metodo para listar alumnos por modulo
    public List<Alumno> listarAlumnosPorModulo(Modulo modulo) {
        // Abrimos la conexión
        Conexion conexion = new Conexion();
        conexion.abrirConexion();
        // Creamos la consulta
        Query query = conexion.getDb().query();
        query.constrain(Alumno.class);
        query.descend("modulos").constrain(modulo);
        // Ejecutamos la consulta
        ObjectSet<Alumno> resultado = query.execute();
        // Cerramos la conexión
        conexion.cerrarConexion();
        // Devolvemos el resultado
        return resultado;
    }

    // Metodo para insertar nota
    public void insertarNota(Notas nota) {
        // Abrimos la conexión
        Conexion conexion = new Conexion();
        conexion.abrirConexion();
        // Insertamos la nota
        conexion.getDb().store(nota);
        // Cerramos la conexión
        conexion.cerrarConexion();
    }

    // Metodo para eliminar nota
    public void eliminarNota(Notas nota) {
        // Abrimos la conexión
        Conexion conexion = new Conexion();
        conexion.abrirConexion();
        // Eliminamos la nota
        conexion.getDb().delete(nota);
        // Cerramos la conexión
        conexion.cerrarConexion();
    }

    // Metodo para listar notas
    public List<Notas> listarNotas() {
        // Abrimos la conexión
        Conexion conexion = new Conexion();
        conexion.abrirConexion();
        // Creamos la consulta
        Query query = conexion.getDb().query();
        query.constrain(Notas.class);
        // Ejecutamos la consulta
        ObjectSet<Notas> resultado = query.execute();
        // Cerramos la conexión
        conexion.cerrarConexion();
        // Devolvemos el resultado
        return resultado;
    }

    // Metodo para listar notas por alumno
    public List<Notas> listarNotasPorAlumno(Alumno alumno) {
        // Abrimos la conexión
        Conexion conexion = new Conexion();
        conexion.abrirConexion();
        // Creamos la consulta
        Query query = conexion.getDb().query();
        query.constrain(Notas.class);
        query.descend("alumno").constrain(alumno);
        // Ejecutamos la consulta
        ObjectSet<Notas> resultado = query.execute();
        // Cerramos la conexión
        conexion.cerrarConexion();
        // Devolvemos el resultado
        return resultado;
    }

}