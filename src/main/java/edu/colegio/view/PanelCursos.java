
package edu.colegio.view;

import edu.colegio.controller.AppController;
import edu.colegio.model.Calificacion;
import edu.colegio.model.Curso;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.util.List;

public class PanelCursos extends JPanel {
    private final AppController controller = new AppController();

    private final JComboBox<Curso> cboCursos = new JComboBox<>();
    private final JTextField txtEstudianteId = new JTextField(6);
    private final JButton btnProbarConexion = new JButton("Probar conexión");
    private final JButton btnCargar = new JButton("Cargar Calificaciones");

    private final DefaultTableModel model = new DefaultTableModel(
        new String[]{"Calif.ID", "Comp.ID", "Nota", "Comentarios"}, 0
    );
    private final JTable table = new JTable(model);

    public PanelCursos() {
        setLayout(new BorderLayout());

        JPanel top = new JPanel(new FlowLayout(FlowLayout.LEFT));
        top.add(new JLabel("Curso:"));
        top.add(cboCursos);
        top.add(new JLabel("Estudiante ID:"));
        top.add(txtEstudianteId);
        top.add(btnCargar);
        top.add(btnProbarConexion);
        add(top, BorderLayout.NORTH);

        add(new JScrollPane(table), BorderLayout.CENTER);

        cargarCursos();

        btnCargar.addActionListener(e -> cargarCalificaciones());
        btnProbarConexion.addActionListener(e -> probarConexion());
    }

    private void cargarCursos() {
        cboCursos.removeAllItems();
        try {
            List<Curso> cursos = controller.listarCursos();
            for (Curso c : cursos) cboCursos.addItem(c);
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(this, "Error cargando cursos: " + ex.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    private void cargarCalificaciones() {
        model.setRowCount(0);
        Curso curso = (Curso) cboCursos.getSelectedItem();
        if (curso == null) return;
        int cursoId = curso.getId();
        int estudianteId;
        try {
            estudianteId = Integer.parseInt(txtEstudianteId.getText().trim());
        } catch (NumberFormatException ex) {
            JOptionPane.showMessageDialog(this, "Ingrese un Estudiante ID numérico.");
            return;
        }

        List<Calificacion> list = controller.obtenerCalificaciones(cursoId, estudianteId);
        for (Calificacion c : list) {
            model.addRow(new Object[]{c.getId(), c.getComponenteId(), c.getNota(), c.getComentarios()});
        }
    }

    private void probarConexion() {
        try {
            cargarCursos();
            JOptionPane.showMessageDialog(this, "Conexión OK y cursos cargados.");
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(this, "Error de conexión: " + ex.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
        }
    }
}
