package notaslinkia;

import java.io.Console;
/**
 *
 * @author alber
 */
import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.Collection;
import java.util.List;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.xml.bind.annotation.adapters.CollapsedStringAdapter;

import resources.Modulo;

public class TestORM {

    public static void main(String[] args) {

        // Desactivar mensajes de registro de Hibernate
        Logger.getLogger("org.hibernate").setLevel(Level.OFF);

        // Crear el gestor de sesiones
        NotasORM gestor = new NotasORM();

        // Crear un objeto Scanner para leer de la consola
        Scanner sc = new Scanner(System.in);

        // Crear un objeto Timestamp para obtener la fecha y hora actual
        Timestamp timestamp = new Timestamp(System.currentTimeMillis());
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

        // Crear un objeto Console para leer la contraseña
        Console console = System.console();

        try (// Crear objeto scanner
                Scanner scanner = new Scanner(System.in)) {
            // Bucle infinito
            while (true) {
                // Limpiar pantalla
                System.out.print("\033[H\033[2J");

                // Banner en color verde
                System.out.println("\033[32m");
                System.out.print(
                        ".d88b. w       w                              8                           w   w                                  8                 w          \n");
                System.out.print(
                        "YPwww. w d88b w8ww .d88b 8d8b.d8b. .d88    .d88 .d88b    .d88 .d88b d88b w8ww w .d8b. 8d8b.    .d88 .d8b .d88 .d88 .d88b 8d8b.d8b. w .d8b .d88\n");
                System.out.print(
                        "    d8 8 `Yb.  8   8.dP' 8P Y8P Y8 8  8    8  8 8.dP'    8  8 8.dP' `Yb.  8   8 8' .8 8P Y8    8  8 8    8  8 8  8 8.dP' 8P Y8P Y8 8 8    8  8\n");
                System.out.print(
                        "`Y88P' 8 Y88P  Y8P `Y88P 8   8   8 `Y88    `Y88 `Y88P    `Y88 `Y88P Y88P  Y8P 8 `Y8P' 8   8    `Y88 `Y8P `Y88 `Y88 `Y88P 8   8   8 8 `Y8P `Y88\n");
                System.out.print(
                        "                                                         wwdP                                                                                 \n");
                System.out.println("\033[0m");
                System.out.print("\033[35m");
                System.out.println("Alberto Pérez del Río");
                System.out.println("DAM - M06");
                System.out.println("2023");

                System.out.println();
                System.out.println();

                System.out.println("\033[33m ┌──────────────────┐\033[0m");
                System.out.println("\033[33m │  MENU PRINCIPAL  │\033[0m");
                System.out.println("\033[33m └──────────────────┘\033[0m");
                System.out.println("\033[36m ╔══════════════════╗\033[0m");
                System.out.println("\033[36m ║ 1. Menú profesor ║\033[0m");
                System.out.println("\033[36m ║==================║\033[0m");
                System.out.println("\033[36m ║ 2. Menú alumno   ║\033[0m");
                System.out.println("\033[36m ╚══════════════════╝\033[0m");
                System.out.println("\n\033[35m ╔══════════════════╗\033[0m");
                System.out.println("\033[35m ║ 3. Menú admin    ║\033[0m");
                System.out.println("\033[35m ╚══════════════════╝\033[0m");
                System.out.println("\n\033[31m ╔═══════════════════╗\033[0m");
                System.out.println("\033[31m ║ 0. Salir          ║\033[0m");
                System.out.println("\033[31m ╚═══════════════════╝\033[0m");

                System.out.print("\n\033[32m Selecciona una opción: \033[0m");

                String opcion = sc.nextLine();

                switch (opcion) {

                    case "1":
                        // Limpiar pantalla
                        System.out.print("\033[H\033[2J");
                        System.out.print("\033[32m");
                        System.out.print("Ingrese su usuario: ");
                        System.out.print("\033[0m");
                        String userProf = sc.nextLine();
                        System.out.print("\033[32m");
                        char[] password = console.readPassword("Ingresa la contraseña: ");
                        String passProf = new String(password);
                        System.out.print("\033[0m");

                        // Comprobar si el usuario y la contraseña son correctos

                        // Bucle para mostrar el menú de profesor hasta que se elija la opción de salir

                        Boolean menuProfesor = true;

                        while (menuProfesor) {
                            // Limpiar pantalla
                            System.out.print("\033[H\033[2J");
                            System.out.print("\033[33m");
                            System.out.println(" Menú de profesor:\n");
                            System.out.print("\033[0m");
                            System.out.println(" 1. Insertar módulo");
                            System.out.println(" 2. Listar TODOS los módulos");
                            System.out.println(" 3. Eliminar módulo");
                            System.out.println(" 4. Insertar alumno");
                            System.out.println(" 5. Listar TODOS los alumnos");
                            System.out.println(" 6. Listar alumnos por módulo");
                            System.out.println(" 7. Eliminar alumno");
                            System.out.println(" 8. Listar notas");
                            System.out.println(" 9. Listar notas por alumno");
                            System.out.println(" 10. Añadir nota");
                            System.out.println(" 11. Modificar nota");
                            System.out.println(" 12. Eliminar nota");
                            System.out.print("\033[31m");
                            System.out.println("\n 0. Salir");
                            System.out.print("\033[0m");

                            System.out.print("\n\033[32m Selecciona una opción: \033[0m");
                            int opcionProfesor = sc.nextInt();
                            sc.nextLine();

                            switch (opcionProfesor) {

                                case 1:
                                    // Llamar a método para insertar módulo
                                    gestor.insertarModulo();
                                    break;
                                case 2:
                                    // Llamar a método para listar todos los módulos
                                    gestor.listarModulos();
                                    gestor.pausa();
                                    break;
                                case 3:
                                    // Llamar a método para eliminar módulo
                                    gestor.eliminarModulo();
                                    break;
                                case 4:
                                    // Llamar a método para insertar alumno
                                    gestor.insertarAlumno();
                                    break;
                                case 5:
                                    // Llamar a método para listar todos los alumnos
                                    gestor.listarAlumnos();
                                    gestor.pausa();
                                    break;
                                case 6:
                                    // Llamar a método para listar alumnos por módulo
                                    gestor.listarAlumnosPorModulo();
                                    break;
                                case 7:
                                    // Llamar a método para eliminar alumno
                                    gestor.eliminarAlumno();
                                    break;
                                case 8:
                                    // Listar notas
                                    gestor.listarNotas2();
                                    gestor.pausa();
                                    break;
                                case 9:
                                    // Listar notas por alumno
                                    gestor.listarNotasPorAlumno();
                                    break;
                                case 10:
                                    // Insertar notas
                                    gestor.insertarNota();
                                    break;
                                case 11:
                                    // Modificar notas
                                    gestor.modificarNota();
                                    break;
                                case 12:
                                    // Eliminar notas
                                    gestor.eliminarNota();
                                    break;

                                case 0:
                                    System.out.print("\033[H\033[2J");
                                    System.out.println("Saliendo...");

                                    menuProfesor = false;
                                    gestor.cerrarConexion();
                                    gestor.pausa();
                                    break;
                                default:
                                    System.out.println("Opción no válida.");
                                    break;
                            }
                        }

                        break;
                    case "2":
                        Boolean menuAlumno = true;

                        // Limpiar pantalla
                        System.out.print("\033[H\033[2J");
                        System.out.print("\033[32m");
                        System.out.print("Ingrese su usuario: ");
                        System.out.print("\033[0m");
                        String userAlumno = sc.nextLine();

                        // Comprobar si el usuario y la contraseña son correctos
                        Console alumnoConsole = System.console();
                        System.out.print("\033[32m");
                        char[] readPassword2 = alumnoConsole.readPassword("Ingresa la contraseña: ");
                        System.out.print("\033[0m");
                        String passAlumno = new String(readPassword2);

                        while (menuAlumno) {

                            // Limpiar pantalla
                            System.out.print("\033[H\033[2J");
                            System.out.print("\033[33m");
                            System.out.println("Menú de alumno:");
                            System.out.println("\033[0m");

                            System.out.println("1. Consultar notas");
                            System.out.println("2. Listar módulos de los que es alumno");
                            System.out.print("\033[33m");
                            System.out.println("\n0. Salir");
                            System.out.print("\033[0m");

                            System.out.print("\n\033[32mSelecciona una opción: \033[0m");
                            int opcionAlumno = sc.nextInt();
                            sc.nextLine();

                            switch (opcionAlumno) {
                                case 1:
                                    // Llamar a método para consultar notas
                                    System.out.print("\033[H\033[2J");

                                    System.out.println("\nPresione una tecla para continuar...");
                                    scanner.nextLine();
                                    break;
                                case 2:
                                    // Llamar a método para listar módulos de los que es alumno
                                    System.out.print("\033[H\033[2J");

                                    System.out.println("\nPresione una tecla para continuar...");
                                    scanner.nextLine();
                                    break;
                                case 0:
                                    System.out.print("\033[H\033[2J");
                                    System.out.println("Saliendo...");

                                    menuAlumno = false;
                                    break;
                                default:
                                    System.out.print("\033[H\033[2J");
                                    System.out.println("Opción no válida.");
                                    System.out.println("\nPresione una tecla para continuar...");
                                    scanner.nextLine();
                                    break;
                            }
                        }
                        break;
                    case "3":
                        Boolean menuAdmin = true;

                        // Limpiar pantalla
                        System.out.print("\033[H\033[2J");

                        // Comprobar si el usuario y la contraseña son correctos
                        Console adminConsole = System.console();
                        System.out.print("\033[32m");
                        char[] readPassword3 = adminConsole.readPassword("Ingresa la contraseña de administrador: ");
                        System.out.print("\033[0m");
                        String passAdmin = new String(readPassword3);
                        String userAdmin = "admin";

                        while (menuAdmin) {
                            System.out.print("\033[H\033[2J");
                            System.out.print("\033[33m");
                            System.out.println("Menú de administrador:");
                            System.out.println("\033[0m");
                            System.out.println("1. Listar tabla historial");
                            System.out.println("2. Insertar módulo");
                            System.out.println("3. Listar TODOS los módulos");
                            System.out.println("4. Eliminar módulo");
                            System.out.println("5. Insertar alumno");
                            System.out.println("6. Listar TODOS los alumnos");
                            System.out.println("7. Listar alumnos por módulo");
                            System.out.println("8. Eliminar alumno");
                            System.out.println("9. Listar tabla notas");
                            System.out.println("10. Listar tabla profesores");
                            System.out.println("11. Modificar profesor");
                            System.out.println("12. Eliminar profesor");
                            System.out.print("\033[31m");
                            System.out.println("\n0. Salir");

                            System.out.print("\n\033[32m Selecciona una opción: \033[0m");
                            int opcionAdmin = sc.nextInt();

                            switch (opcionAdmin) {
                                case 1:
                                    // Llamar a método para listar historial
                                    System.out.print("\033[H\033[2J");

                                    System.out.println("\nPresione una tecla para continuar...");
                                    scanner.nextLine();
                                    break;
                                case 2:
                                    // Llamar a método para insertar módulo
                                    System.out.print("\033[H\033[2J");

                                    System.out.println("\nPresione una tecla para continuar...");
                                    scanner.nextLine();
                                    break;
                                case 3:
                                    // Llamar a método para listar todos los módulos
                                    System.out.print("\033[H\033[2J");

                                    System.out.println("\nPresione una tecla para continuar...");
                                    scanner.nextLine();
                                    break;
                                case 4:
                                    // Llamar a método para eliminar módulo
                                    System.out.print("\033[H\033[2J");

                                    System.out.println("\nPresione una tecla para continuar...");
                                    scanner.nextLine();
                                    break;
                                case 5:
                                    // Llamar a método para insertar alumno
                                    System.out.print("\033[H\033[2J");

                                    System.out.println("\nPresione una tecla para continuar...");
                                    scanner.nextLine();
                                    break;
                                case 6:
                                    // Llamar a método para listar todos los alumnos
                                    System.out.print("\033[H\033[2J");

                                    System.out.println("\nPresione una tecla para continuar...");
                                    scanner.nextLine();
                                    break;
                                case 7:
                                    // Llamar a método para listar alumnos por módulo
                                    System.out.print("\033[H\033[2J");

                                    System.out.println("\nPresione una tecla para continuar...");
                                    scanner.nextLine();
                                    break;
                                case 8:
                                    // Llamar a método para eliminar alumno
                                    System.out.print("\033[H\033[2J");

                                    System.out.println("\nPresione una tecla para continuar...");
                                    scanner.nextLine();
                                    break;
                                case 9:
                                    // Llamar a método para listar tabla notas
                                    System.out.print("\033[H\033[2J");

                                    System.out.println("\nPresione una tecla para continuar...");
                                    scanner.nextLine();
                                    break;
                                case 10:
                                    // Llamar a método para listar tabla profesores
                                    System.out.print("\033[H\033[2J");

                                    System.out.println("\nPresione una tecla para continuar...");
                                    scanner.nextLine();
                                    break;
                                case 11:
                                    // Llamar a método para modificar profesor
                                    System.out.print("\033[H\033[2J");

                                    System.out.println("\nPresione una tecla para continuar...");
                                    scanner.nextLine();
                                    break;
                                case 12:
                                    // Llamar a método para eliminar profesor
                                    System.out.print("\033[H\033[2J");

                                    System.out.println("\nPresione una tecla para continuar...");
                                    scanner.nextLine();
                                    break;
                                case 0:
                                    System.out.print("\033[H\033[2J");
                                    System.out.println("Saliendo...");

                                    System.out.println("\nPresione una tecla para continuar...");
                                    scanner.nextLine();
                                    menuAdmin = false;
                                    break;
                                default:
                                    System.out.print("\033[H\033[2J");
                                    System.out.println("Opción no válida.");
                                    System.out.println("\nPresione una tecla para continuar...");
                                    scanner.nextLine();
                                    break;
                            }
                        }

                        break;
                    case "0":
                        System.out.println("Hasta luego.");
                        sc.close();
                        System.exit(0);
                        break;
                    default:
                        System.out.println("Opción no válida.");
                        break;
                }
            }
        }
    }
}