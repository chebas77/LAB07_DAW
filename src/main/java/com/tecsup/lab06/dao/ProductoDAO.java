package com.tecsup.lab06.dao;

import com.tecsup.lab06.model.Categoria;
import com.tecsup.lab06.model.Producto;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class ProductoDAO {

    private Connection obtenerConexion() throws SQLException {
        Connection con = null;
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            con = DriverManager.getConnection("jdbc:mysql://localhost:3306/escuela?useSSL=false&serverTimezone=UTC", "root", "");
            return con;
        } catch (ClassNotFoundException e) {
            throw new SQLException("Error al cargar el driver de MySQL", e);
        }
    }

    public List<Producto> listarProductos() {
        List<Producto> productos = new ArrayList<>();

        try (Connection con = obtenerConexion();
             CallableStatement cs = con.prepareCall("{call sp_listar_productos()}");
             ResultSet rs = cs.executeQuery()) {

            while (rs.next()) {
                Producto producto = new Producto();
                producto.setId(rs.getInt("id"));
                producto.setNombre(rs.getString("nombre"));
                producto.setDescripcion(rs.getString("descripcion"));
                producto.setPrecio(rs.getDouble("precio"));
                producto.setStock(rs.getInt("stock"));
                producto.setCategoriaId(rs.getInt("categoria_id"));

                // Crear y asignar la categoría
                Categoria categoria = new Categoria();
                categoria.setId(rs.getInt("categoria_id"));
                categoria.setNombre(rs.getString("categoria_nombre"));
                categoria.setDescripcion(rs.getString("categoria_descripcion"));
                producto.setCategoria(categoria);

                productos.add(producto);
            }

        } catch (SQLException e) {
            System.out.println("Error al listar productos: " + e.getMessage());
        }

        return productos;
    }

    public List<Producto> listarProductosPorCategoria(int categoriaId) {
        List<Producto> productos = new ArrayList<>();

        try (Connection con = obtenerConexion();
             CallableStatement cs = con.prepareCall("{call sp_listar_productos_por_categoria(?)}")) {

            cs.setInt(1, categoriaId);

            try (ResultSet rs = cs.executeQuery()) {
                while (rs.next()) {
                    Producto producto = new Producto();
                    producto.setId(rs.getInt("id"));
                    producto.setNombre(rs.getString("nombre"));
                    producto.setDescripcion(rs.getString("descripcion"));
                    producto.setPrecio(rs.getDouble("precio"));
                    producto.setStock(rs.getInt("stock"));
                    producto.setCategoriaId(rs.getInt("categoria_id"));

                    // Crear y asignar la categoría
                    Categoria categoria = new Categoria();
                    categoria.setId(rs.getInt("categoria_id"));
                    categoria.setNombre(rs.getString("categoria_nombre"));
                    categoria.setDescripcion(rs.getString("categoria_descripcion"));
                    producto.setCategoria(categoria);

                    productos.add(producto);
                }
            }

        } catch (SQLException e) {
            System.out.println("Error al listar productos por categoría: " + e.getMessage());
        }

        return productos;
    }

    public Producto obtenerProductoPorId(int id) {
        Producto producto = null;

        try (Connection con = obtenerConexion();
             CallableStatement cs = con.prepareCall("{call sp_obtener_producto_por_id(?)}")) {

            cs.setInt(1, id);

            try (ResultSet rs = cs.executeQuery()) {
                if (rs.next()) {
                    producto = new Producto();
                    producto.setId(rs.getInt("id"));
                    producto.setNombre(rs.getString("nombre"));
                    producto.setDescripcion(rs.getString("descripcion"));
                    producto.setPrecio(rs.getDouble("precio"));
                    producto.setStock(rs.getInt("stock"));
                    producto.setCategoriaId(rs.getInt("categoria_id"));

                    // Crear y asignar la categoría
                    Categoria categoria = new Categoria();
                    categoria.setId(rs.getInt("categoria_id"));
                    categoria.setNombre(rs.getString("categoria_nombre"));
                    categoria.setDescripcion(rs.getString("categoria_descripcion"));
                    producto.setCategoria(categoria);
                }
            }

        } catch (SQLException e) {
            System.out.println("Error al obtener producto: " + e.getMessage());
        }

        return producto;
    }

    public int insertarProducto(Producto producto) {
        int idGenerado = 0;

        try (Connection con = obtenerConexion();
             CallableStatement cs = con.prepareCall("{call sp_insertar_producto(?, ?, ?, ?, ?, ?)}")) {

            cs.setString(1, producto.getNombre());
            cs.setString(2, producto.getDescripcion());
            cs.setDouble(3, producto.getPrecio());
            cs.setInt(4, producto.getStock());
            cs.setInt(5, producto.getCategoriaId());
            cs.registerOutParameter(6, java.sql.Types.INTEGER);

            cs.execute();
            idGenerado = cs.getInt(6);

        } catch (SQLException e) {
            System.out.println("Error al insertar producto: " + e.getMessage());
        }

        return idGenerado;
    }

    public boolean actualizarProducto(Producto producto) {
        boolean actualizado = false;

        try (Connection con = obtenerConexion();
             CallableStatement cs = con.prepareCall("{call sp_actualizar_producto(?, ?, ?, ?, ?, ?)}")) {

            cs.setInt(1, producto.getId());
            cs.setString(2, producto.getNombre());
            cs.setString(3, producto.getDescripcion());
            cs.setDouble(4, producto.getPrecio());
            cs.setInt(5, producto.getStock());
            cs.setInt(6, producto.getCategoriaId());

            int filasAfectadas = cs.executeUpdate();
            actualizado = filasAfectadas > 0;

        } catch (SQLException e) {
            System.out.println("Error al actualizar producto: " + e.getMessage());
        }

        return actualizado;
    }

    public boolean eliminarProducto(int id) {
        boolean eliminado = false;

        try (Connection con = obtenerConexion();
             CallableStatement cs = con.prepareCall("{call sp_eliminar_producto(?)}")) {

            cs.setInt(1, id);

            int filasAfectadas = cs.executeUpdate();
            eliminado = filasAfectadas > 0;

        } catch (SQLException e) {
            System.out.println("Error al eliminar producto: " + e.getMessage());
        }

        return eliminado;
    }
}