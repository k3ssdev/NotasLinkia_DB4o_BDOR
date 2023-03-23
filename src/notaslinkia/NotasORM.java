package notaslinkia;

import com.db4o.Db4oEmbedded;
import com.db4o.ObjectContainer;
import com.db4o.ObjectSet;
import com.db4o.ext.Db4oException;
import com.db4o.query.Query;

import resources.Alumno;
import resources.Historial;
import resources.Modulo;
import resources.Notas;
import resources.Profesor;

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
            listarModulos(null);
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

    public void listarModulos(Object object) {
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
            listarModulos(null);
            System.out.println("\033[32m");
            System.out.print("Introduce el código del módulo a eliminar: ");
            System.out.print("\033[0m");
            int codigoModulo = sc.nextInt();
            // Query para comprobar si el módulo ya existe
            Query q = db.query();
            q.constrain(Modulo.class);
            q.descend("idModulo").constrain(codigoModulo);
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

    public void listarAlumnos2() {
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

            // Imprimir encabezado de la tabla solo id y nombre
            System.out.println("+-------+----------------------+");
            System.out.printf("| \033[38;5;206m%-5s\033[0m | \033[38;5;206m%-20s\033[0m |\n", "ID", "Nombre");
            System.out.println("+-------+----------------------+");

            // Imprimir cada registro en la tabla
            for (Alumno a : alumnos) {
                System.out.printf("| %-5d | %-20s |\n", a.getIdAlumno(), a.getNombre());
                System.out.println("+-------+----------------------+");
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

    public void listarAlumnosPorModulo(String id) {
        // Listar alumnos
        listarModulos(null);
        // Pedir el código del alumno
        System.out.println("\033[32m");
        System.out.print("Introduce el código del modulo: ");
        System.out.print("\033[0m");
        int codigoModulo = sc.nextInt();
        // Query para comprobar si el alumno existe
        Query q = db.query();
        q.constrain(Modulo.class);
        q.descend("idModulo").constrain(codigoModulo);
        ObjectSet resultado = q.execute();
        if (resultado.size() == 0) {
            System.out.println("\033[38;5;196m");
            System.out.println("El modulo no existe");
            System.out.print("\033[0m");
            pausa();
            return;
        }
        // Query para listar las notas del alumno
        Query q2 = db.query();
        q2.constrain(Notas.class);
        q2.descend("idAlumno").constrain(codigoModulo);
        ObjectSet resultado2 = q2.execute();
        // Imprimir encabezado de la tabla con nombre alumno y modulo
        System.out.println("+-------+----------------------+----------------------+-------+");
        System.out.printf(
                "| \033[38;5;206m%-5s\033[0m | \033[38;5;206m%-20s\033[0m | \033[38;5;206m%-20s\033[0m | \033[38;5;206m%-5s\033[0m |\n",
                "ID", "Alumno", "Módulo", "Nota ");
        System.out.println("+-------+----------------------+----------------------+-------+");
        System.out.print("\033[0m");
        // Imprimir las notas
        for (int i = 0; i < resultado2.size(); i++) {
            Notas n = (Notas) resultado2.get(i);
            // Query para obtener el nombre del alumno
            Query q3 = db.query();
            q3.constrain(Alumno.class);
            q3.descend("idAlumno").constrain(n.getIdAlumno());
            ObjectSet resultado3 = q3.execute();
            Alumno a = (Alumno) resultado3.get(0);
            // Query para obtener el nombre del módulo
            Query q4 = db.query();
            q4.constrain(Modulo.class);
            q4.descend("idModulo").constrain(n.getIdModulo());
            ObjectSet resultado4 = q4.execute();
            Modulo m = (Modulo) resultado4.get(0);
            // Imprimir la nota
            System.out.printf(
                    "| \033[38;5;15m%-5d\033[0m | \033[38;5;15m%-20s\033[0m | \033[38;5;15m%-20s\033[0m | \033[38;5;15m%-5.2f\033[0m |\n",
                    n.getIdNotas(), a.getNombre(), m.getNombre(), n.getNota());
        }
        System.out.println("+-------+----------------------+----------------------+-------+");
        // Pausa
        pausa();
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

    public void listarNotas2() {
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
            // Imprimir encabezado de la tabla con nombre alumno y modulo
            System.out.println("+-------+----------------------+----------------------+-------+");
            System.out.printf(
                    "| \033[38;5;206m%-5s\033[0m | \033[38;5;206m%-20s\033[0m | \033[38;5;206m%-20s\033[0m | \033[38;5;206m%-5s\033[0m |\n",
                    "ID", "Alumno", "Módulo", "Nota ");
            System.out.println("+-------+----------------------+----------------------+-------+");
            // Imprimir cada registro en la tabla con for
            for (Notas n : notas) {
                // Obtener el nombre del alumno
                Query q2 = db.query();
                q2.constrain(Alumno.class);
                q2.descend("idAlumno").constrain(n.getIdAlumno());
                ObjectSet resultado2 = q2.execute();
                Alumno a = (Alumno) resultado2.get(0);
                // Obtener el nombre del módulo
                Query q3 = db.query();
                q3.constrain(Modulo.class);
                q3.descend("idModulo").constrain(n.getIdModulo());
                ObjectSet resultado3 = q3.execute();
                Modulo m = (Modulo) resultado3.get(0);
                System.out.printf("| %-5d | %-20s | %-20s | %-5.2f |\n", n.getIdNotas(), a.getNombre(),
                        m.getNombre(),
                        n.getNota());
                System.out.println("+-------+----------------------+----------------------+-------+");
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
            listarNotas2();
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
            listarModulos(null);
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

    public void listarNotasPorAlumno(int idAlumno) {
        if (idAlumno == 0) {
            // Listar alumnos
            listarAlumnos();
            // Pedir el código del alumno
            System.out.println("\033[32m");
            System.out.print("Introduce el código del alumno: ");
            System.out.print("\033[0m");
            idAlumno = sc.nextInt();
        }
        
        // Query para comprobar si el alumno existe
        Query q = db.query();
        q.constrain(Alumno.class);
        q.descend("idAlumno").constrain(idAlumno);
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
        q2.descend("idAlumno").constrain(idAlumno);
        ObjectSet resultado2 = q2.execute();
        // Imprimir encabezado de la tabla con nombre alumno y modulo
        System.out.println("+-------+----------------------+----------------------+-------+");
        System.out.printf(
                "| \033[38;5;206m%-5s\033[0m | \033[38;5;206m%-20s\033[0m | \033[38;5;206m%-20s\033[0m | \033[38;5;206m%-5s\033[0m |\n",
                "ID", "Alumno", "Módulo", "Nota ");
        System.out.println("+-------+----------------------+----------------------+-------+");
        System.out.print("\033[0m");
        // Imprimir las notas
        for (int i = 0; i < resultado2.size(); i++) {
            Notas n = (Notas) resultado2.get(i);
            // Query para obtener el nombre del alumno
            Query q3 = db.query();
            q3.constrain(Alumno.class);
            q3.descend("idAlumno").constrain(n.getIdAlumno());
            ObjectSet resultado3 = q3.execute();
            Alumno a = (Alumno) resultado3.get(0);
            // Query para obtener el nombre del módulo
            Query q4 = db.query();
            q4.constrain(Modulo.class);
            q4.descend("idModulo").constrain(n.getIdModulo());
            ObjectSet resultado4 = q4.execute();
            Modulo m = (Modulo) resultado4.get(0);
            // Imprimir la nota
            System.out.printf(
                    "| \033[38;5;15m%-5d\033[0m | \033[38;5;15m%-20s\033[0m | \033[38;5;15m%-20s\033[0m | \033[38;5;15m%-5.2f\033[0m |\n",
                    n.getIdNotas(), a.getNombre(), m.getNombre(), n.getNota());
        }
        System.out.println("+-------+----------------------+----------------------+-------+");
        // Pausa
        pausa();
    }

    public boolean comprobarAlumno(String user, String pass) {
        try {
            // Query para comprobar el profesor
            Query q = db.query();
            q.constrain(Alumno.class);
            q.descend("nomUser").constrain(user);
            q.descend("password").constrain(pass);
            ObjectSet resultado = q.execute();
            if (resultado.size() == 0) {
                return false;
            } else {
                return true;
            }
        } catch (Exception e) {
            return false;
        }
    }

    public void insertarProfesor(Profesor profesor) {

        db.store(profesor);
    }

    // Metodo para insertar un profesor
    public void insertarProfesor() {
        try {
            // Listar los profesores
            listarProfesores();
            // Pedir los datos del profesor
            System.out.println("\033[32m");
            System.out.print("Introduce el ID del profesor: ");
            System.out.print("\033[0m");
            int idProfesor = sc.nextInt();
            sc.nextLine();
            System.out.println("\033[32m");
            System.out.print("Introduce el nombre del profesor: ");
            System.out.print("\033[0m");
            String nombreProfesor = sc.nextLine();
            System.out.println("\033[32m");
            System.out.print("Introduce el user del profesor: ");
            System.out.print("\033[0m");
            String userProfesor = sc.nextLine();
            System.out.println("\033[32m");
            System.out.print("Introduce el password del profesor: ");
            System.out.print("\033[0m");
            String passProfesor = sc.nextLine();
            // Crear el objeto profesor
            Profesor profesor = new Profesor(idProfesor, nombreProfesor, userProfesor, passProfesor);
            // Insertar el profesor
            insertarProfesor(profesor);
            System.out.println("\033[38;5;206m");
            System.out.println("Profesor insertado correctamente");
            System.out.print("\033[0m");
            // Pausa
            pausa();
        } catch (Exception e) {
            System.out.println("\033[38;5;196m");
            System.out.println("Error al insertar el profesor");
            System.out.print("\033[0m");
            // Pausa
            pausa();
        }

    }

    public void insertarAlumno(Alumno alumno) {
        db.store(alumno);
    }

    public void insertarModulo(Modulo modulo) {
        db.store(modulo);
    }

    public void insertarNota(Notas nota) {
        db.store(nota);
    }

    public void listarProfesores() {
        // Query para listar los profesores
        Query q = db.query();
        q.constrain(Profesor.class);
        ObjectSet resultado = q.execute();
        // Imprimir encabezado de la tabla completa
        System.out.println("+-------+----------------------+----------------------+----------------------+");
        System.out.printf(
                "| \033[38;5;206m%-5s\033[0m | \033[38;5;206m%-20s\033[0m | \033[38;5;206m%-20s\033[0m | \033[38;5;206m%-20s\033[0m |\n",
                "ID", "Nombre", "User", "Password");
        System.out.println("+-------+----------------------+----------------------+----------------------+");
        System.out.print("\033[0m");
        // Imprimir los profesores
        for (int i = 0; i < resultado.size(); i++) {
            Profesor p = (Profesor) resultado.get(i);
            System.out.printf(
                    "| \033[38;5;15m%-5d\033[0m | \033[38;5;15m%-20s\033[0m | \033[38;5;15m%-20s\033[0m | \033[38;5;15m%-20s\033[0m |\n",
                    p.getId(), p.getNombre(), p.getNomUser(), p.getPassword());
        }
        System.out.println("+-------+----------------------+----------------------+----------------------+");
    }

    public void modificarProfesor() {
    
        // Listar profesores
        listarProfesores();
        // Pedir el código del profesor
        System.out.println("\033[32m");
        System.out.print("Introduce el código del profesor: ");
        System.out.print("\033[0m");
        String codProf = sc.nextLine();
        int codigo = Integer.parseInt(codProf);
        // Query para comprobar si la nota existe
        Query q = db.query();
        q.constrain(Profesor.class);
        q.descend("id").constrain(codigo);
        ObjectSet resultado = q.execute();
        if (resultado.size() == 0) {
            System.out.println("\033[38;5;196m");
            System.out.println("El profesor no existe");
            System.out.print("\033[0m");
            pausa();
            return;
        }
        // Modificar el profesor
        Profesor p = (Profesor) resultado.get(0);
        System.out.println("\033[32m");
        System.out.print("Introduce el nombre del profesor: ");
        System.out.print("\033[0m");
        String nombre = sc.nextLine();
        System.out.println("\033[32m");
        System.out.print("Introduce el user del profesor: ");
        System.out.print("\033[0m");
        String user = sc.nextLine();
        System.out.println("\033[32m");
        System.out.print("Introduce el password del profesor: ");
        System.out.print("\033[0m");
        String pass = sc.nextLine();
        p.setNombre(nombre);
        p.setNomUser(user);
        p.setPassword(pass);
        db.store(p);
        System.out.println("\033[38;5;206m");
        System.out.println("Profesor modificado correctamente");
        System.out.print("\033[0m");
        pausa();
    }


    public void eliminarProfesor() {
        // Listar los profesores
        listarProfesores();
        // Pedir el ID del profesor a eliminar
        System.out.println("\033[32m");
        System.out.print("Introduce el ID del profesor a eliminar: ");
        System.out.print("\033[0m");
        int idProfesor = sc.nextInt();
       
        // Query para comprobar el profesor
        Query q = db.query();
        q.constrain(Profesor.class);
        q.descend("id").constrain(idProfesor);
        ObjectSet resultado = q.execute();
        // Comprobar si existe el profesor
        if (resultado.size() == 1) {
            // Eliminar el profesor
            db.delete(resultado.get(0));
            System.out.println("\033[38;5;206m");
            System.out.println("Profesor eliminado correctamente");
            System.out.print("\033[0m");
            // Pausa
            pausa();
        } else {
            System.out.println("\033[38;5;196m");
            System.out.println("No existe el profesor");
            System.out.print("\033[0m");
            // Pausa
            pausa();
        }
    }

    public boolean comprobarProfesor(String userProf, String passProf) {
        try {
            // Query para comprobar el profesor
            Query q = db.query();
            q.constrain(Profesor.class);
            q.descend("nomUser").constrain(userProf);
            q.descend("password").constrain(passProf);
            ObjectSet resultado = q.execute();
            // Comprobar si existe el profesor
            if (resultado.size() == 1) {
                return true;
            } else {
                return false;
            }
        } catch (Exception e) {
            return false;
        }
    }

    public boolean comprobarAdmin(String userAdmin, String passAdmin) {
        try {
            // Query para comprobar el admin
            Query q = db.query();
            q.constrain(Profesor.class);
            q.descend("nomUser").constrain(userAdmin);
            q.descend("password").constrain(passAdmin);
            ObjectSet resultado = q.execute();
            // Comprobar si existe el registro admin
            if (resultado.size() == 1) {
                Profesor p = (Profesor) resultado.get(0);
                if (p.getId() == 1) {
                    return true;
                } else {
                    return false;
                }
            } else {
                return false;
            }
        } catch (Exception e) {
            return false;
        }
    }

    public int idAlumno(String user) {
        // Query para obtener el id del alumno
        Query q = db.query();
        q.constrain(Alumno.class);
        q.descend("nomUser").constrain(user);
        ObjectSet resultado = q.execute();
        Alumno a = (Alumno) resultado.get(0);
        return a.getIdAlumno();

    }

    public void listarModulosPorAlumno(String user) {
        // Query para listar los modulos por alumno
        Query q = db.query();
        q.constrain(Notas.class);
        q.descend("idAlumno").constrain(idAlumno(user));
        ObjectSet resultado = q.execute();
        // Imprimir encabezado de la tabla completa
        System.out.println("+-------+----------------------+");
        System.out.printf(
                "| \033[38;5;206m%-5s\033[0m | \033[38;5;206m%-20s\033[0m |\n",
                "ID", "Nombre");
        System.out.println("+-------+----------------------+");
        System.out.print("\033[0m");
        // Imprimir los modulos
        for (int i = 0; i < resultado.size(); i++) {
            Notas n = (Notas) resultado.get(i);
            System.out.printf(
                    "| \033[38;5;15m%-5d\033[0m | \033[38;5;15m%-20s\033[0m |\n",
                    n.getIdModulo(), n.getModulo());
        }
        System.out.println("+-------+----------------------+");
    }

}
