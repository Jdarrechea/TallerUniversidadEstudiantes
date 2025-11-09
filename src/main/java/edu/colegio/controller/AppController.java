
package edu.colegio.controller;

import edu.colegio.dao.CalificacionDAO;
import edu.colegio.dao.CursoDAO;
import edu.colegio.model.Calificacion;
import edu.colegio.model.Curso;

import java.util.List;

public class AppController {
    private final CursoDAO cursoDAO = new CursoDAO();
    private final CalificacionDAO calificacionDAO = new CalificacionDAO();

    public List<Curso> listarCursos() {
        return cursoDAO.listar();
    }

    public List<Calificacion> obtenerCalificaciones(int cursoId, int estudianteId) {
        return calificacionDAO.listarPorCursoYEstudiante(cursoId, estudianteId);
    }

    public void guardarCalificacion(Calificacion c) {
        calificacionDAO.upsert(c);
    }
}
