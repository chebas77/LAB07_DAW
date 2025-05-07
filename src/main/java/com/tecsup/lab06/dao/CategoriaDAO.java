package com.tecsup.lab06.dao;

import com.tecsup.lab06.model.Categoria;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class CategoriaDAO {

    private static Connection obtenerConexion() throws SQLException {
        Connection con = null;
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            con = DriverManager.getConnection("jdbc:mysql://localhost:3306/escuela?useSSL=false&serverTimezone=UTC", "root", "");
            return con;
        } catch (ClassNotFoundException e) {
            throw new SQLException("Error al cargar el driver de MySQL", e);
        }
    }

    public List<Categoria> listarCategorias() {
        List<Categoria> categorias = new ArrayList<>();

        try (Connection con = obtenerConexion();
             CallableStatement cs = con.prepareCall("{call sp_listar_categorias()}");
             ResultSet rs = cs.executeQuery()) {

            while (rs.next()) {
                Categoria categoria = new Categoria();
                categoria.setId(rs.getInt("id"));
                categoria.setNombre(rs.getString("nombre"));
                categoria.setDescripcion(rs.getString("descripcion"));
                categorias.add(categoria);
            }

        } catch (SQLException e) {
            System.out.println("Error al listar categorías: " + e.getMessage());
        }

        return categorias;
    }

    public Categoria obtenerCategoriaPorId(int id) {
        Categoria categoria = null;

        try (Connection con = obtenerConexion();
             CallableStatement cs = con.prepareCall("{call sp_obtener_categoria_por_id(?)}")) {

            cs.setInt(1, id);

            try (ResultSet rs = cs.executeQuery()) {
                if (rs.next()) {
                    categoria = new Categoria();
                    categoria.setId(rs.getInt("id"));
                    categoria.setNombre(rs.getString("nombre"));
                    categoria.setDescripcion(rs.getString("descripcion"));
                }
            }

        } catch (SQLException e) {
            System.out.println("Error al obtener categoría: " + e.getMessage());
        }

        return categoria;
    }
    public static List<Categoria> listar() {
        List<Categoria> lista = new ArrayList<>();
        try (Connection con = obtenerConexion();
             PreparedStatement ps = con.prepareStatement("SELECT * FROM categoria");
             ResultSet rs = ps.executeQuery()) {

            while (rs.next()) {
                Categoria cat = new Categoria();
                cat.setId(rs.getInt("id"));
                cat.setNombre(rs.getString("nombre"));
                lista.add(cat);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return lista;
    }


    public int insertarCategoria(Categoria categoria) {
        int idGenerado = 0;

        try (Connection con = obtenerConexion();
             CallableStatement cs = con.prepareCall("{call sp_insertar_categoria(?, ?, ?)}")) {

            cs.setString(1, categoria.getNombre());
            cs.setString(2, categoria.getDescripcion());
            cs.registerOutParameter(3, java.sql.Types.INTEGER);

            cs.execute();
            idGenerado = cs.getInt(3);

        } catch (SQLException e) {
            System.out.println("Error al insertar categoría: " + e.getMessage());
        }

        return idGenerado;
    }

    public boolean actualizarCategoria(Categoria categoria) {
        boolean actualizado = false;

        try (Connection con = obtenerConexion();
             CallableStatement cs = con.prepareCall("{call sp_actualizar_categoria(?, ?, ?)}")) {

            cs.setInt(1, categoria.getId());
            cs.setString(2, categoria.getNombre());
            cs.setString(3, categoria.getDescripcion());

            int filasAfectadas = cs.executeUpdate();
            actualizado = filasAfectadas > 0;

        } catch (SQLException e) {
            System.out.println("Error al actualizar categoría: " + e.getMessage());
        }

        return actualizado;
    }

    public boolean eliminarCategoria(int id) {
        boolean eliminado = false;

        try (Connection con = obtenerConexion();
             CallableStatement cs = con.prepareCall("{call sp_eliminar_categoria(?)}")) {

            cs.setInt(1, id);

            int filasAfectadas = cs.executeUpdate();
            eliminado = filasAfectadas > 0;

        } catch (SQLException e) {
            System.out.println("Error al eliminar categoría: " + e.getMessage());
        }

        return eliminado;
    }
}