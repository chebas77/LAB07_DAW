package com.tecsup.lab06.controller;

import com.tecsup.lab06.dao.CategoriaDAO;
import com.tecsup.lab06.model.Categoria;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

@WebServlet(name = "CategoriaServlet", urlPatterns = {"/CategoriaServlet"})
public class CategoriaServlet extends HttpServlet {

    private CategoriaDAO categoriaDAO = new CategoriaDAO();

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        String action = request.getParameter("action");
        if (action == null) {
            action = "listar"; // Acción por defecto
        }

        switch (action) {
            case "listar":
                listarCategorias(request, response);
                break;
            case "agregar":
                mostrarFormularioAgregar(request, response);
                break;
            case "insertar":
                insertarCategoria(request, response);
                break;
            case "editar":
                mostrarFormularioEditar(request, response);
                break;
            case "actualizar":
                actualizarCategoria(request, response);
                break;
            case "eliminar":
                eliminarCategoria(request, response);
                break;
            default:
                listarCategorias(request, response);
        }
    }

    private void listarCategorias(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        List<Categoria> listaCategorias = categoriaDAO.listarCategorias();
        request.setAttribute("listaCategorias", listaCategorias);
        request.getRequestDispatcher("/categorias/listar.jsp").forward(request, response);
    }

    private void mostrarFormularioAgregar(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        request.getRequestDispatcher("/categorias/agregar.jsp").forward(request, response);
    }

    private void insertarCategoria(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String nombre = request.getParameter("nombre");
        String descripcion = request.getParameter("descripcion");

        Categoria categoria = new Categoria();
        categoria.setNombre(nombre);
        categoria.setDescripcion(descripcion);

        int id = categoriaDAO.insertarCategoria(categoria);

        if (id > 0) {
            // Éxito - redirigir a la lista de categorías
            response.sendRedirect("CategoriaServlet?action=listar");
        } else {
            // Error - volver al formulario con mensaje de error
            request.setAttribute("mensaje", "Error al insertar la categoría");
            request.setAttribute("categoria", categoria);
            request.getRequestDispatcher("/categorias/agregar.jsp").forward(request, response);
        }
    }

    private void mostrarFormularioEditar(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        int id = Integer.parseInt(request.getParameter("id"));
        Categoria categoria = categoriaDAO.obtenerCategoriaPorId(id);

        if (categoria != null) {
            request.setAttribute("categoria", categoria);
            request.getRequestDispatcher("/categorias/editar.jsp").forward(request, response);
        } else {
            request.setAttribute("mensaje", "La categoría no existe");
            listarCategorias(request, response);
        }
    }

    private void actualizarCategoria(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        int id = Integer.parseInt(request.getParameter("id"));
        String nombre = request.getParameter("nombre");
        String descripcion = request.getParameter("descripcion");

        Categoria categoria = new Categoria();
        categoria.setId(id);
        categoria.setNombre(nombre);
        categoria.setDescripcion(descripcion);

        boolean actualizado = categoriaDAO.actualizarCategoria(categoria);

        if (actualizado) {
            // Éxito - redirigir a la lista de categorías
            response.sendRedirect("CategoriaServlet?action=listar");
        } else {
            // Error - volver al formulario con mensaje de error
            request.setAttribute("mensaje", "Error al actualizar la categoría");
            request.setAttribute("categoria", categoria);
            request.getRequestDispatcher("/categorias/editar.jsp").forward(request, response);
        }
    }

    private void eliminarCategoria(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        int id = Integer.parseInt(request.getParameter("id"));

        boolean eliminado = categoriaDAO.eliminarCategoria(id);

        if (!eliminado) {
            request.setAttribute("mensaje", "No se pudo eliminar la categoría. Es posible que tenga productos asociados.");
        }

        listarCategorias(request, response);
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
        return "Servlet para gestionar categorías";
    }
}