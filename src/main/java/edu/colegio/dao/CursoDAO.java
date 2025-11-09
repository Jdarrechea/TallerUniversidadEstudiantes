
package edu.colegio.dao;

import edu.colegio.config.DBConnection;
import edu.colegio.model.Curso;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class CursoDAO {
    public List<Curso> listar() {
        List<Curso> lista = new ArrayList<>();
        String sql = "SELECT curso_id, nombre_curso FROM cursos ORDER BY nombre_curso";
        try (Connection cn = DBConnection.getConnection();
             Statement st = cn.createStatement();
             ResultSet rs = st.executeQuery(sql)) {
            while (rs.next()) {
                lista.add(new Curso(rs.getInt(1), rs.getString(2)));
            }
        } catch (SQLException e) {
            throw new RuntimeException("Error listando cursos", e);
        }
        return lista;
    }
}
