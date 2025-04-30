package com.tecsup.lab06.dao;

import com.tecsup.lab06.model.Curso;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class CursoDAO {

    private final String url = "jdbc:mysql://localhost:3306/escuela";
    private final String user = "root";
    private final String pass = "";

    public Connection conectar() throws SQLException {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver"); // NECESARIO para registrar el driver
        } catch (ClassNotFoundException e) {
            System.out.println("❌ Error: No se encontró el driver de MySQL");
            e.printStackTrace();
        }
        return DriverManager.getConnection(url, user, pass);
    }

    public List<Curso> listar() {
        List<Curso> lista = new ArrayList<>();
        String sql = "SELECT * FROM curso";

        try (Connection conn = conectar();
             PreparedStatement ps = conn.prepareStatement(sql);
             ResultSet rs = ps.executeQuery()) {

            while (rs.next()) {
                Curso c = new Curso();
                c.setCodigo(rs.getString("chrCurCodigo"));
                c.setNombre(rs.getString("vchCurNombre"));
                c.setCreditos(rs.getInt("intCurCreditos"));
                lista.add(c);
            }

        } catch (SQLException e) {
            System.out.println("❌ Error SQL al listar cursos: " + e.getMessage());
            e.printStackTrace();
        }

        return lista;
    }

    public boolean insertar(Curso curso) {
        String sql = "INSERT INTO curso (chrCurCodigo, vchCurNombre, intCurCreditos) VALUES (?, ?, ?)";

        try (Connection conn = conectar();
             PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setString(1, curso.getCodigo());
            ps.setString(2, curso.getNombre());
            ps.setInt(3, curso.getCreditos());

            int filasAfectadas = ps.executeUpdate();
            System.out.println("✅ Filas afectadas al insertar: " + filasAfectadas);
            return filasAfectadas > 0;

        } catch (SQLException e) {
            System.out.println("❌ Error SQL al insertar curso: " + e.getMessage());
            e.printStackTrace();
        }

        return false;
    }

    public boolean actualizar(Curso curso) {
        String sql = "UPDATE curso SET vchCurNombre = ?, intCurCreditos = ? WHERE chrCurCodigo = ?";

        try (Connection conn = conectar();
             PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setString(1, curso.getNombre());
            ps.setInt(2, curso.getCreditos());
            ps.setString(3, curso.getCodigo());

            int filasAfectadas = ps.executeUpdate();
            System.out.println("✅ Filas afectadas al actualizar: " + filasAfectadas);
            return filasAfectadas > 0;

        } catch (SQLException e) {
            System.out.println("❌ Error SQL al actualizar curso: " + e.getMessage());
            e.printStackTrace();
        }

        return false;
    }

    public boolean eliminar(String codigo) {
        String sql = "DELETE FROM curso WHERE chrCurCodigo = ?";

        try (Connection conn = conectar();
             PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setString(1, codigo);
            int filasAfectadas = ps.executeUpdate();
            System.out.println("✅ Filas afectadas al eliminar: " + filasAfectadas);
            return filasAfectadas > 0;

        } catch (SQLException e) {
            System.out.println("❌ Error SQL al eliminar curso: " + e.getMessage());
            e.printStackTrace();
        }

        return false;
    }

    public Curso buscarPorCodigo(String codigo) {
        String sql = "SELECT * FROM curso WHERE chrCurCodigo = ?";
        Curso curso = null;

        try (Connection conn = conectar();
             PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setString(1, codigo);
            ResultSet rs = ps.executeQuery();

            if (rs.next()) {
                curso = new Curso();
                curso.setCodigo(rs.getString("chrCurCodigo"));
                curso.setNombre(rs.getString("vchCurNombre"));
                curso.setCreditos(rs.getInt("intCurCreditos"));
            }

        } catch (SQLException e) {
            System.out.println("❌ Error SQL al buscar curso: " + e.getMessage());
            e.printStackTrace();
        }

        return curso;
    }
}
