
package edu.colegio.dao;

import edu.colegio.config.DBConnection;
import edu.colegio.model.Calificacion;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class CalificacionDAO {

    public List<Calificacion> listarPorCursoYEstudiante(int cursoId, int estudianteId) {
        String sql = "SELECT cal.calificacion_id, cal.estudiante_id, cal.componente_evaluacion_id, cal.nota, cal.comentarios_calificacion " +
                     "FROM calificaciones cal " +
                     "JOIN componentes_evaluacion comp ON comp.componente_evaluacion_id = cal.componente_evaluacion_id " +
                     "JOIN cortes_evaluacion ce ON ce.corte_evaluacion_id = comp.corte_evaluacion_id " +
                     "WHERE ce.curso_id = ? AND cal.estudiante_id = ? " +
                     "ORDER BY ce.corte_evaluacion_id, comp.componente_evaluacion_id";
        List<Calificacion> out = new ArrayList<>();
        try (Connection cn = DBConnection.getConnection();
             PreparedStatement ps = cn.prepareStatement(sql)) {
            ps.setInt(1, cursoId);
            ps.setInt(2, estudianteId);
            try (ResultSet rs = ps.executeQuery()) {
                while (rs.next()) {
                    out.add(new Calificacion(
                        rs.getInt(1), rs.getInt(2), rs.getInt(3),
                        rs.getDouble(4), rs.getString(5)
                    ));
                }
            }
        } catch (SQLException e) {
            throw new RuntimeException("Error listando calificaciones", e);
        }
        return out;
    }

    public void upsert(Calificacion c) {
        String sql = "INSERT INTO calificaciones (estudiante_id, componente_evaluacion_id, nota, comentarios_calificacion) " +
                     "VALUES (?, ?, ?, ?) " +
                     "ON DUPLICATE KEY UPDATE nota = VALUES(nota), comentarios_calificacion = VALUES(comentarios_calificacion)";
        try (Connection cn = DBConnection.getConnection();
             PreparedStatement ps = cn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {
            ps.setInt(1, c.getEstudianteId());
            ps.setInt(2, c.getComponenteId());
            ps.setDouble(3, c.getNota());
            ps.setString(4, c.getComentarios());
            ps.executeUpdate();

            try (ResultSet rs = ps.getGeneratedKeys()) {
                if (rs.next()) c.setId(rs.getInt(1));
            }
        } catch (SQLException e) {
            throw new RuntimeException("Error guardando calificación", e);
        }
    }
}
