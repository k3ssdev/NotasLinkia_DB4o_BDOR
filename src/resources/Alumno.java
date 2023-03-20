package resources;

import java.util.List;

public class Alumno {

    private Integer idAlumno;
    private String nombre;
    private String nomUser;
    private String password;


    public Alumno() {
    }

    public Alumno(Integer idAlumno, String nombre, String nomUser, String password) {
        this.idAlumno = idAlumno;
        this.nombre = nombre;
        this.nomUser = nomUser;
        this.password = password;
    }

    public Integer getIdAlumno() {
        return idAlumno;
    }

    public void setIdAlumno(Integer idAlumno) {
        this.idAlumno = idAlumno + 1;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getNomUser() {
        return nomUser;
    }

    public void setNomUser(String nomUser) {
        this.nomUser = nomUser;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    @Override
    public String toString() {
        return "Alumno{" + "idAlumno=" + idAlumno + ", nombre=" + nombre + ", nomUser=" + nomUser + ", password="
                + password + '}';
    }

}