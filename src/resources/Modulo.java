package resources;

import java.util.List;

public class Modulo {

    private Integer id;
    private String nombre;
    //private List<Alumno> alumnos;
   // private List<Notas> notas;

    public Modulo() {
    }

    public Modulo(Integer id, String nombre) {
        this.id = id;
        this.nombre = nombre;
        //this.alumnos = alumnos;
        //this.notas = notas;
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

    @Override
    public String toString() {
        return "Modulo{" + "id=" + id + ", nombre=" + nombre + '}';
    }

}

    