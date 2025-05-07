package com.tecsup.lab06.controller;

import com.tecsup.lab06.dao.CategoriaDAO;
import com.tecsup.lab06.dao.ProductoDAO;
import com.tecsup.lab06.model.Categoria;
import com.tecsup.lab06.model.Producto;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.util.List;

@WebServlet(name = "ProductoServlet", urlPatterns = {"/ProductoServlet"})
public class ProductoServlet extends HttpServlet {

    private final ProductoDAO productoDAO = new ProductoDAO();

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        String action = request.getParameter("action");
        if (action == null) {
            action = "listar"; // Acción por defecto
        }

        switch (action) {
            case "listar":
                listarProductos(request, response);
                break;
            case "agregar":
                List<Categoria> listaCategorias = CategoriaDAO.listar(); // Asegúrate de tener esto implementado
                request.setAttribute("listaCategorias", listaCategorias);
                request.getRequestDispatcher("/productos/agregar.jsp").forward(request, response);
                break;
            case "insertar":
                insertarProducto(request, response);
                break;
            case "editar":
                mostrarFormularioEditar(request, response);
                break;
            case "actualizar":
                actualizarProducto(request, response);
                break;
            case "eliminar":
                eliminarProducto(request, response);
                break;
            default:
                listarProductos(request, response);
        }
    }

    private void listarProductos(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        List<Producto> listaProductos = productoDAO.listarProductos();
        request.setAttribute("listaProductos", listaProductos);
        request.getRequestDispatcher("/productos/listar.jsp").forward(request, response);
    }

    private void mostrarFormularioAgregar(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        request.getRequestDispatcher("/productos/agregar.jsp").forward(request, response);
    }

    private void insertarProducto(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String nombre = request.getParameter("nombre");
        String descripcion = request.getParameter("descripcion");
        double precio = Double.parseDouble(request.getParameter("precio"));
        int stock = Integer.parseInt(request.getParameter("stock"));
        int categoriaId = Integer.parseInt(request.getParameter("categoria_id"));

        Producto producto = new Producto();
        producto.setNombre(nombre);
        producto.setDescripcion(descripcion);
        producto.setPrecio(precio);
        producto.setStock(stock);
        producto.setCategoriaId(categoriaId);

        int id = productoDAO.insertarProducto(producto);

        if (id > 0) {
            response.sendRedirect("ProductoServlet?action=listar");
        } else {
            request.setAttribute("mensaje", "Error al insertar el producto");
            request.setAttribute("producto", producto);
            request.getRequestDispatcher("/productos/agregar.jsp").forward(request, response);
        }
    }

    private void mostrarFormularioEditar(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        int id = Integer.parseInt(request.getParameter("id"));
        Producto producto = productoDAO.obtenerProductoPorId(id);

        if (producto != null) {
            request.setAttribute("producto", producto);
            request.getRequestDispatcher("/productos/editar.jsp").forward(request, response);
        } else {
            request.setAttribute("mensaje", "El producto no existe");
            listarProductos(request, response);
        }
    }

    private void actualizarProducto(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        int id = Integer.parseInt(request.getParameter("id"));
        String nombre = request.getParameter("nombre");
        String descripcion = request.getParameter("descripcion");
        double precio = Double.parseDouble(request.getParameter("precio"));
        int stock = Integer.parseInt(request.getParameter("stock"));
        int categoriaId = Integer.parseInt(request.getParameter("categoria_id"));

        Producto producto = new Producto();
        producto.setId(id);
        producto.setNombre(nombre);
        producto.setDescripcion(descripcion);
        producto.setPrecio(precio);
        producto.setStock(stock);
        producto.setCategoriaId(categoriaId);

        boolean actualizado = productoDAO.actualizarProducto(producto);

        if (actualizado) {
            response.sendRedirect("ProductoServlet?action=listar");
        } else {
            request.setAttribute("mensaje", "Error al actualizar el producto");
            request.setAttribute("producto", producto);
            request.getRequestDispatcher("/productos/editar.jsp").forward(request, response);
        }
    }

    private void eliminarProducto(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        int id = Integer.parseInt(request.getParameter("id"));
        boolean eliminado = productoDAO.eliminarProducto(id);

        if (!eliminado) {
            request.setAttribute("mensaje", "No se pudo eliminar el producto. Intenta nuevamente.");
        }

        listarProductos(request, response);
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    @Override
    public String getServletInfo() {
        return "Servlet para gestionar productos";
    }
}
