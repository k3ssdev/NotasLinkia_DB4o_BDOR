package resources;

import java.util.List;

public class Notas {

    private Integer idNotas;
    private Integer idAlumno;
    private Integer idModulo;
    private Double nota;
    private List<Alumno> alumnos;
    private List<Modulo> modulos;

    public Notas() {
    }

    public Notas(Integer idNotas, Integer idAlumno, Integer idModulo, Double nota) {
        this.idNotas = idNotas;
        this.idAlumno = idAlumno;
        this.idModulo = idModulo;
        this.nota = nota;
    }

    public Notas(Integer idNotas, Integer idAlumno, Integer idModulo, Double nota, List<Alumno> alumnos,
            List<Modulo> modulos) {
        this.idNotas = idNotas;
        this.idAlumno = idAlumno;
        this.idModulo = idModulo;
        this.nota = nota;
        this.alumnos = alumnos;
        this.modulos = modulos;
    }

    public Integer getIdNotas() {
        return idNotas;
    }

    public void setIdNotas(Integer idNotas) {
        this.idNotas = idNotas;
    }

    public Integer getIdAlumno() {
        return idAlumno;
    }

    public void setIdAlumno(Integer idAlumno) {
        this.idAlumno = idAlumno;
    }

    public Integer getIdModulo() {
        return idModulo;
    }

    public void setIdModulo(Integer idModulo) {
        this.idModulo = idModulo;
    }

    public Double getNota() {
        return nota;
    }

    public void setNota(Double nota) {
        this.nota = nota;
    }

    public List<Alumno> getAlumnos() {
        return alumnos;
    }

    public void setAlumnos(List<Alumno> alumnos) {
        this.alumnos = alumnos;
    }

    public List<Modulo> getModulos() {
        return modulos;
    }

    public void setModulos(List<Modulo> modulos) {
        this.modulos = modulos;
    }

    @Override
    public String toString() {
        return "Notas{" + "idNotas=" + idNotas + ", idAlumno=" + idAlumno + ", idModulo=" + idModulo + ", nota=" + nota
                + ", alumnos=" + alumnos + ", modulos=" + modulos + '}';
    }

}