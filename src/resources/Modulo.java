package resources;

import java.util.List;

public class Modulo {

    private Integer id;
    private String nombre;
    private List<Alumno> alumnos;
    private List<Notas> notas;

    public Modulo() {
    }

    public Modulo(Integer id, String nombre, List<Alumno> alumnos, List<Notas> notas) {
        this.id = id;
        this.nombre = nombre;
        this.alumnos = alumnos;
        this.notas = notas;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public List<Alumno> getAlumnos() {
        return alumnos;
    }

    public void setAlumnos(List<Alumno> alumnos) {
        this.alumnos = alumnos;
    }

    public List<Notas> getNotas() {
        return notas;
    }

    public void setNotas(List<Notas> notas) {
        this.notas = notas;
    }

    @Override
    public String toString() {
        return "Modulo{" + "id=" + id + ", nombre=" + nombre + ", alumnos=" + alumnos + ", notas=" + notas + '}';
    }

}
    