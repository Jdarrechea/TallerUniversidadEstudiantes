
package edu.colegio.model;

public class Calificacion {
    private int id;
    private int estudianteId;
    private int componenteId;
    private double nota;
    private String comentarios;

    public Calificacion() {}
    public Calificacion(int id, int estudianteId, int componenteId, double nota, String comentarios) {
        this.id = id; this.estudianteId = estudianteId; this.componenteId = componenteId;
        this.nota = nota; this.comentarios = comentarios;
    }

    public int getId() { return id; }
    public int getEstudianteId() { return estudianteId; }
    public int getComponenteId() { return componenteId; }
    public double getNota() { return nota; }
    public String getComentarios() { return comentarios; }

    public void setId(int id) { this.id = id; }
    public void setEstudianteId(int estudianteId) { this.estudianteId = estudianteId; }
    public void setComponenteId(int componenteId) { this.componenteId = componenteId; }
    public void setNota(double nota) { this.nota = nota; }
    public void setComentarios(String comentarios) { this.comentarios = comentarios; }
}
