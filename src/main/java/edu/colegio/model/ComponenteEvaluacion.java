
package edu.colegio.model;

public class ComponenteEvaluacion {
    private int id;
    private int corteId;
    private String nombre;
    private double porcentaje; // % del corte

    public ComponenteEvaluacion() {}
    public ComponenteEvaluacion(int id, int corteId, String nombre, double porcentaje) {
        this.id = id; this.corteId = corteId; this.nombre = nombre; this.porcentaje = porcentaje;
    }
    public int getId() { return id; }
    public int getCorteId() { return corteId; }
    public String getNombre() { return nombre; }
    public double getPorcentaje() { return porcentaje; }
    public void setId(int id) { this.id = id; }
    public void setCorteId(int corteId) { this.corteId = corteId; }
    public void setNombre(String nombre) { this.nombre = nombre; }
    public void setPorcentaje(double porcentaje) { this.porcentaje = porcentaje; }
}
