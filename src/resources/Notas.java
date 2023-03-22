package resources;

import java.util.List;

public class Notas {

    private Integer idNotas;
    private Integer idAlumno;
    private Integer idModulo;
    private Double nota;
    private Alumno alumnos;
    private Modulo modulos;

    public Notas() {
    }

    public Notas(Integer idNotas, Integer idAlumno, Integer idModulo, Double nota) {
        this.idNotas = idNotas;
        this.idAlumno = idAlumno;
        this.idModulo = idModulo;
        this.nota = nota;
    }

    public Notas(Integer idNotas, Integer idAlumno, Integer idModulo, Double nota, Alumno alumnos,
            Modulo modulos) {
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

    public Alumno getAlumnos() {
        return alumnos;
    }

    public void setAlumnos(Alumno alumnos) {
        this.alumnos = alumnos;
    }

    public Modulo getModulos() {
        return modulos;
    }

    public void setModulos(Modulo modulos) {
        this.modulos = modulos;
    }

    @Override
    public String toString() {
        return "Notas{" + "idNotas=" + idNotas + ", idAlumno=" + idAlumno + ", idModulo=" + idModulo + ", nota=" + nota
                + ", alumnos=" + alumnos + ", modulos=" + modulos + '}';
    }

}