package notaslinkia;

import com.db4o.Db4oEmbedded;
import com.db4o.ObjectContainer;
import com.db4o.ObjectSet;
import com.db4o.ext.Db4oException;
import com.db4o.query.Query;

import resources.Alumno;
import resources.Modulo;
import resources.Notas;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Scanner;

public class NotasORM {

    // Atributo para la entrada de datos por teclado
    private Scanner sc = new Scanner(System.in);

    // Atributo para la persistencia de OO
    private ObjectContainer db;

    public NotasORM() {
        db = Db4oEmbedded.openFile("notaslinkia.dat");
    }

    public void cerrarConexion() {
        db.close();
    }

    // Metodo pausa
    public void pausa() {
        Scanner sc = new Scanner(System.in);
        System.out.println("\033[32m");
        System.out.print("Pulsa una tecla para continuar...");
        System.out.print("\033[0m");
        sc.nextLine();
    }

    // Metodo para insertar un módulo
    public void insertarModulo() {
        Scanner sc = new Scanner(System.in);
        try {
            // Listar los módulos
            listarModulos();
            // Pedir los datos del módulo
            System.out.println("\033[32m");
            System.out.print("Introduce el código del módulo: ");
            System.out.print("\033[0m");
            String codModString = sc.nextLine();
            int codigoModulo = Integer.parseInt(codModString);
            System.out.println("\033[32m");
            System.out.print("Introduce el nombre del módulo: ");
            System.out.print("\033[0m");
            String nombreModulo = sc.nextLine();
            System.out.print("\033[32m");
            Modulo modulo = new Modulo(codigoModulo, nombreModulo);
            System.out.print("\033[0m");

            // Comprobar que no existe un módulo
            if (existeModulo(codigoModulo)) {
                System.out.println("\033[38;5;196m");
                System.out.println("Ya existe un módulo con ese código");
                System.out.print("\033[0m");
                pausa();
                return;
            }

            // Comprobar que no existe un módulo
            if (existeModulo(nombreModulo)) {
                System.out.println("\033[38;5;196m");
                System.out.println("Ya existe un módulo con ese nombre");
                System.out.print("\033[0m");
                pausa();
                return;
            }

            // Guardar el módulo
            db.store(modulo);
            System.out.println("\033[38;5;206m");
            System.out.println("Módulo insertado correctamente");
            System.out.print("\033[0m");
            pausa();

        } catch (Exception e) {
            System.out.println("\033[38;5;196m");
            System.out.println("Error al insertar el módulo");
            System.out.print("\033[0m");
            pausa();
        }
    }

    // Comprobar si existe un módulo por id
    public boolean existeModulo(int id) {
        Query q = db.query();
        q.constrain(Modulo.class);
        q.descend("idModulo").constrain(id);
        ObjectSet resultado = q.execute();
        return resultado.size() > 0;
    }

    // Comprobar si existe un módulo por nombre
    public boolean existeModulo(String nombre) {
        Query q = db.query();
        q.constrain(Modulo.class);
        q.descend("nombre").constrain(nombre);
        ObjectSet resultado = q.execute();
        return resultado.size() > 0;
    }



    public void listarModulos() {
        try {

            // Obtener todos los módulos
            List<Modulo> modulos = new ArrayList<>();
            Query q = db.query();
            // Constrain para que solo devuelva objetos de tipo Modulo
            q.constrain(Modulo.class);
            // Obtener el resultado de la consulta
            ObjectSet resultado = q.execute();
            // Recorrer el resultado y añadirlo a la lista
            while (resultado.hasNext()) {
                Modulo m = (Modulo) resultado.next();
                modulos.add(m);
            }
            // Ordenar por id
            Collections.sort(modulos, (m1, m2) -> m1.getIdModulo() - m2.getIdModulo());

            // Limpiar la pantalla
            System.out.print("\033[H\033[2J");

            // Imprimir encabezado de la tabla
            System.out.println("+-------+---------+");
            System.out.printf("| \033[38;5;206m%-5s\033[0m | \033[38;5;206m%-7s\033[0m |\n", "ID", "Nombre");
            System.out.println("+-------+---------+");

            // Imprimir cada registro en la tabla
            for (Modulo m : modulos) {
                System.out.printf("| %-5d | %-7s |\n", m.getIdModulo(), m.getNombre());
                System.out.println("+-------+---------+");
            }

        } catch (Exception e) {
            System.out.println("\033[38;5;196m");
            System.out.println("Error al listar los módulos");
            System.out.print("\033[0m");
            pausa();
        }
    }

    public void eliminarModulo() {
        try {
            System.out.print("\033[H\033[2J");
            listarModulos();
            System.out.println("\033[32m");
            System.out.print("Introduce el código del módulo a eliminar: ");
            System.out.print("\033[0m");
            int codigoModulo = sc.nextInt();
            // Query para comprobar si el módulo ya existe
            Query q = db.query();
            q.constrain(Modulo.class);
            q.descend("id").constrain(codigoModulo);
            ObjectSet resultado = q.execute();
            if (resultado.size() == 0) {
                System.out.println("\033[38;5;196m");
                System.out.println("El módulo no existe");
                System.out.print("\033[0m");
                pausa();
                return;
            }
            // Eliminar el módulo
            db.delete(resultado.get(0));
            System.out.println("\033[38;5;206m");
            System.out.println("Módulo eliminado correctamente");
            System.out.print("\033[0m");
            // Pausa
            System.out.println("\033[32m");
            System.out.print("Pulsa una tecla para continuar...");
            System.out.print("\033[0m");
            sc.nextLine();
        } catch (Exception e) {
            System.out.println("\033[38;5;196m");
            System.out.println("Error al eliminar el módulo");
            System.out.print("\033[0m");
            pausa();
        }
    }

    // Metodo para insertar un alumno
    public void insertarAlumno() {
        try {
            // Listar los alumnos
            listarAlumnos();
            // Pedir los datos del alumno
            System.out.println("\033[32m");
            System.out.print("Introduce el ID del alumno: ");
            System.out.print("\033[0m");
            int idAlumno = sc.nextInt();
            sc.nextLine();
            System.out.println("\033[32m");
            System.out.print("Introduce el nombre del alumno: ");
            System.out.print("\033[0m");
            String nombreAlumno = sc.nextLine();
            System.out.println("\033[32m");
            System.out.print("Introduce el nombre de usuario: ");
            System.out.print("\033[0m");
            String nombreUsuario = sc.nextLine();
            System.out.println("\033[32m");
            System.out.print("Introduce la contraseña: ");
            System.out.print("\033[0m");
            String contrasena = sc.nextLine();

            // Query para comprobar si el alumno ya existe
            Query q = db.query();
            q.constrain(Alumno.class);
            q.descend("id").constrain(idAlumno);
            ObjectSet resultado = q.execute();
            if (resultado.size() > 0) {
                System.out.println("\033[38;5;196m");
                System.out.println("El alumno ya existe");
                System.out.print("\033[0m");
                pausa();
                return;
            }
            // Guardar el alumno
            db.store(new Alumno(idAlumno, nombreAlumno, nombreUsuario, contrasena));
            System.out.println("\033[38;5;206m");
            System.out.println("Alumno insertado correctamente");
            System.out.print("\033[0m");
            pausa();

        } catch (Exception e) {
            System.out.println("\033[38;5;196m");
            System.out.println("Error al insertar el alumno");
            System.out.print("\033[0m");
            pausa();
        }
    }

    public void listarAlumnos() {
        try {
            System.out.print("\033[H\033[2J");
            // Obtener todos los alumnos
            List<Alumno> alumnos = new ArrayList<>();
            Query q = db.query();
            // Constrain para que solo devuelva objetos de tipo Alumno
            q.constrain(Alumno.class);
            // Obtener el resultado de la consulta
            ObjectSet resultado = q.execute();
            // Recorrer el resultado y añadirlo a la lista
            while (resultado.hasNext()) {
                Alumno a = (Alumno) resultado.next();
                alumnos.add(a);
            }
            // Ordenar por id
            Collections.sort(alumnos, (a1, a2) -> a1.getIdAlumno() - a2.getIdAlumno());

            // Imprimir encabezado de la tabla
            System.out.println("+-------+----------------------+----------------------+----------------------+");
            System.out.printf(
                    "| \033[38;5;206m%-5s\033[0m | \033[38;5;206m%-20s\033[0m | \033[38;5;206m%-20s\033[0m | \033[38;5;206m%-20s\033[0m |\n",
                    "ID", "Nombre", "Nombre de usuario", "Contraseña");
            System.out.println("+-------+----------------------+----------------------+----------------------+");

            // Imprimir cada registro en la tabla
            for (Alumno a : alumnos) {
                System.out.printf("| %-5d | %-20s | %-20s | %-20s |\n", a.getIdAlumno(), a.getNombre(), a.getNombre(),
                        a.getPassword());
                System.out.println("+-------+----------------------+----------------------+----------------------+");
            }

        } catch (Exception e) {
            System.out.println("\033[38;5;196m");
            System.out.println("Error al listar los alumnos");
            System.out.print("\033[0m");
            pausa();
        }
    }

    public void eliminarAlumno() {
        try {
            System.out.print("\033[H\033[2J");
            listarAlumnos();
            System.out.println("\033[32m");
            System.out.print("Introduce el código del alumno a eliminar: ");
            System.out.print("\033[0m");
            int codigoAlumno = sc.nextInt();
            // Query para comprobar si el alumno ya existe
            Query q = db.query();
            q.constrain(Alumno.class);
            q.descend("idAlumno").constrain(codigoAlumno);
            ObjectSet resultado = q.execute();
            if (resultado.size() == 0) {
                System.out.println("\033[38;5;196m");
                System.out.println("El alumno no existe");
                System.out.print("\033[0m");
                pausa();
                return;
            }
            // Eliminar el alumno
            db.delete(resultado.get(0));
            System.out.println("\033[38;5;206m");
            System.out.println("Alumno eliminado correctamente");
            System.out.print("\033[0m");
            // Pausa
            pausa();
        } catch (Exception e) {
            System.out.println("\033[38;5;196m");
            System.out.println("Error al eliminar el alumno");
            System.out.print("\033[0m");
            pausa();
        }
    }

    public void listarAlumnosPorModulo() {
    }

    public void listarNotas() {
        try {
            // Limpiar la pantalla
            System.out.print("\033[H\033[2J");
            // Listar tabla de notas (id, idAlumno, idModulo, nota)
            List<Notas> notas = new ArrayList<>();
            Query q = db.query();
            q.constrain(Notas.class);
            ObjectSet resultado = q.execute();
            while (resultado.hasNext()) {
                Notas n = (Notas) resultado.next();
                notas.add(n);
            }
            // Ordenar por id
            Collections.sort(notas, (n1, n2) -> n1.getIdNotas() - n2.getIdNotas());
            // Imprimir encabezado de la tabla
            System.out.println("+-------+------------+------------+-------+");
            System.out.printf(
                    "| \033[38;5;206m%-5s\033[0m | \033[38;5;206m%-5s\033[0m | \033[38;5;206m%-5s\033[0m | \033[38;5;206m%-5s\033[0m |\n",
                    "ID", "ID Alumno ", "ID Módulo ", "Nota ");
            System.out.println("+-------+------------+------------+-------+");
            // Imprimir cada registro en la tabla con for
            for (Notas n : notas) {
                System.out.printf("| %-5d | %-10d | %-10d | %-5.2f |\n", n.getIdNotas(), n.getIdAlumno(),
                        n.getIdModulo(),
                        n.getNota());
                System.out.println("+-------+------------+------------+-------+");
            }
        } catch (Exception e) {
            // detalles del error

            System.out.println("\033[38;5;196m");
            System.out.println("Error al listar las notas");
            e.printStackTrace();
            System.out.print("\033[0m");
            pausa();
        }
    }


    public void insertarNota() {
        try {
            listarNotas();
            System.out.println("\033[32m");
            System.out.print("Introduce el código de la nota: ");
            System.out.print("\033[0m");
            int codigoNota = sc.nextInt();
            // Limpiar pantalla
            System.out.print("\033[H\033[2J");
            listarAlumnos();
            System.out.println("\033[32m");
            System.out.print("Introduce el código del alumno: ");
            System.out.print("\033[0m");
            int codigoAlumno = sc.nextInt();
            // Limpiar pantalla
            System.out.print("\033[H\033[2J");
            listarModulos();
            System.out.println("\033[32m");
            System.out.print("Introduce el código del módulo: ");
            System.out.print("\033[0m");
            int codigoModulo = sc.nextInt();
            System.out.println("\033[32m");
            System.out.print("Introduce la nota: ");
            System.out.print("\033[0m");
            String notaString = sc.next();
            double nota = Double.parseDouble(notaString);

            // Double nota = sc.nextDouble();

            // Query para comprobar si la nota ya existe
            Query q = db.query();
            q.constrain(Notas.class);
            q.descend("idNotas").constrain(codigoNota);
            ObjectSet resultado = q.execute();
            if (resultado.size() > 0) {
                System.out.println("\033[38;5;196m");
                System.out.println("La nota ya existe");
                System.out.print("\033[0m");
                pausa();
                return;
            }
            // Insertar la nota
            Notas n = new Notas(codigoNota, codigoAlumno, codigoModulo, nota);
            db.store(n);
            System.out.println("\033[38;5;206m");
            System.out.println("Nota insertada correctamente");
            System.out.print("\033[0m");
            // Pausa
            pausa();

        } catch (Exception e) {
            System.out.println("\033[38;5;196m");
            System.out.println("Error al insertar la nota");
            e.printStackTrace();
            System.out.print("\033[0m");
            pausa();
        }
    }

    public void modificarNota() {
        // Listar notas
        listarNotas();
        // Pedir el código de la nota a modificar
        System.out.println("\033[32m");
        System.out.print("Introduce el código de la nota a modificar: ");
        System.out.print("\033[0m");
        int codigoNota = sc.nextInt();
        // Query para comprobar si la nota existe
        Query q = db.query();
        q.constrain(Notas.class);
        q.descend("idNotas").constrain(codigoNota);
        ObjectSet resultado = q.execute();
        if (resultado.size() == 0) {
            System.out.println("\033[38;5;196m");
            System.out.println("La nota no existe");
            System.out.print("\033[0m");
            pausa();
            return;
        }
        // Modificar la nota
        Notas n = (Notas) resultado.get(0);
        System.out.println("\033[32m");
        System.out.print("Introduce la nueva nota: ");
        System.out.print("\033[0m");
        String notaString = sc.next();
        double nota = Double.parseDouble(notaString);
        n.setNota(nota);
        db.store(n);
        System.out.println("\033[38;5;206m");
        System.out.println("Nota modificada correctamente");
        System.out.print("\033[0m");
        // Pausa
        pausa();
    }

    public void eliminarNota() {
        listarNotas();
        System.out.println("\033[32m");
        System.out.print("Introduce el código de la nota a eliminar: ");
        System.out.print("\033[0m");
        int codigoNota = sc.nextInt();
        // Query para comprobar si la nota existe
        Query q = db.query();
        q.constrain(Notas.class);
        q.descend("idNotas").constrain(codigoNota);
        ObjectSet resultado = q.execute();
        if (resultado.size() == 0) {
            System.out.println("\033[38;5;196m");
            System.out.println("La nota no existe");
            System.out.print("\033[0m");
            pausa();
            return;
        }
        // Eliminar la nota
        Notas n = (Notas) resultado.get(0);
        db.delete(n);
        System.out.println("\033[38;5;206m");
        System.out.println("Nota eliminada correctamente");
        System.out.print("\033[0m");
        // Pausa
        pausa();

    }

    public void listarNotasPorAlumno() {
        // Listar alumnos
        listarAlumnos();
        // Pedir el código del alumno
        System.out.println("\033[32m");
        System.out.print("Introduce el código del alumno: ");
        System.out.print("\033[0m");
        int codigoAlumno = sc.nextInt();
        // Query para comprobar si el alumno existe
        Query q = db.query();
        q.constrain(Alumno.class);
        q.descend("idAlumno").constrain(codigoAlumno);
        ObjectSet resultado = q.execute();
        if (resultado.size() == 0) {
            System.out.println("\033[38;5;196m");
            System.out.println("El alumno no existe");
            System.out.print("\033[0m");
            pausa();
            return;
        }
        // Query para listar las notas del alumno
        Query q2 = db.query();
        q2.constrain(Notas.class);
        q2.descend("idAlumno").constrain(codigoAlumno);
        ObjectSet resultado2 = q2.execute();
        // Imprimir cabecera de la tabla
        System.out.println("+--------+------------+------------+-------+");
        System.out.printf("| %-5s | %-10s | %-10s | %-5s |%n", "Código", "Alumno", "Módulo", "Nota");
        System.out.println("+--------+------------+------------+-------+");
        // Imprimir las notas
        for (Object o : resultado2) {
            Notas n = (Notas) o;
            System.out.printf("| %-6d | %-10d | %-10d | %-5.2f |%n", n.getIdNotas(), n.getIdAlumno(), n.getIdModulo(),
                    n.getNota());
        }
        // Imprimir pie de tabla
        System.out.println("+--------+------------+------------+-------+");
        // Pausa
        pausa();
    }

}