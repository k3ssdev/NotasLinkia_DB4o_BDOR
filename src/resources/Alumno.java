package resources;

import java.util.List;

public class Alumno {

    private Integer idAlumno;
    private String nombre;
    private String nomUser;
    private String password;
    private List<Modulo> modulos;
    private List<Notas> notas;

    public Alumno() {
    }

    public Alumno(Integer idAlumno, String nombre, String nomUser, String password, List<Modulo> modulos,
            List<Notas> notas) {
        this.idAlumno = idAlumno;
        this.nombre = nombre;
        this.nomUser = nomUser;
        this.password = password;
        this.modulos = modulos;
        this.notas = notas;
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

    public List<Modulo> getModulos() {
        return modulos;
    }

    public void setModulos(List<Modulo> modulos) {
        this.modulos = modulos;
    }

    public List<Notas> getNotas() {
        return notas;
    }

    public void setNotas(List<Notas> notas) {
        this.notas = notas;
    }

    @Override
    public String toString() {
        return "Alumno{" + "idAlumno=" + idAlumno + ", nombre=" + nombre + ", nomUser=" + nomUser + ", password="
                + password + ", modulos=" + modulos + ", notas=" + notas + '}';
    }

}