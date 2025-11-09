
package edu.colegio.model;

public class CorteEvaluacion {
    private int id;
    private int cursoId;
    private String nombre;
    private double porcentaje; // % del curso
    public CorteEvaluacion() {}
    public CorteEvaluacion(int id, int cursoId, String nombre, double porcentaje) {
        this.id = id; this.cursoId = cursoId; this.nombre = nombre; this.porcentaje = porcentaje;
    }
    public int getId() { return id; }
    public int getCursoId() { return cursoId; }
    public String getNombre() { return nombre; }
    public double getPorcentaje() { return porcentaje; }
    public void setId(int id) { this.id = id; }
    public void setCursoId(int cursoId) { this.cursoId = cursoId; }
    public void setNombre(String nombre) { this.nombre = nombre; }
    public void setPorcentaje(double porcentaje) { this.porcentaje = porcentaje; }
}
