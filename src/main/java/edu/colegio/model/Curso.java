
package edu.colegio.model;

public class Curso {
    private int id;
    private String nombre;

    public Curso() {}
    public Curso(int id, String nombre) {
        this.id = id; this.nombre = nombre;
    }
    public int getId() { return id; }
    public String getNombre() { return nombre; }
    public void setId(int id) { this.id = id; }
    public void setNombre(String nombre) { this.nombre = nombre; }
    @Override public String toString() { return id + " - " + nombre; }
}
