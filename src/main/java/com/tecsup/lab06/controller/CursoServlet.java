package com.tecsup.lab06.controller;

import com.tecsup.lab06.dao.CursoDAO;
import com.tecsup.lab06.model.Curso;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.*;
import java.io.IOException;
import java.util.List;

@WebServlet("/CursoServlet")
public class CursoServlet extends HttpServlet {

    private void mostrarFormularioAgregar(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        request.getRequestDispatcher("WEB-INF/agregar.jsp").forward(request, response);
    }

    private CursoDAO dao = new CursoDAO();

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        String action = request.getParameter("action");

        if (action == null) {
            action = "listar";
        }

        switch (action) {
            case "listar":
                listarCursos(request, response);
                break;
            case "editar":
                mostrarFormularioEditar(request, response);
                break;
            case "eliminar":
                eliminarCurso(request, response);
                break;
            case "agregar":
                mostrarFormularioAgregar(request, response);
                break;
            default:
                listarCursos(request, response);
                break;
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        String action = request.getParameter("action");

        if ("insertar".equals(action)) {
            insertarCurso(request, response);
        } else if ("actualizar".equals(action)) {
            actualizarCurso(request, response);
        }
    }

    private void listarCursos(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        List<Curso> listaCursos = dao.listar();
        System.out.println("✅ Cursos obtenidos: " + listaCursos.size());

        request.setAttribute("listaCursos", listaCursos);
        request.getRequestDispatcher("WEB-INF/listar.jsp").forward(request, response);
    }

    private void insertarCurso(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        String codigo = request.getParameter("codigo");
        String nombre = request.getParameter("nombre");
        int creditos = Integer.parseInt(request.getParameter("creditos"));

        Curso nuevoCurso = new Curso();
        nuevoCurso.setCodigo(codigo);
        nuevoCurso.setNombre(nombre);
        nuevoCurso.setCreditos(creditos);

        dao.insertar(nuevoCurso);

        response.sendRedirect("CursoServlet?action=listar");
    }

    private void mostrarFormularioEditar(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        String codigo = request.getParameter("codigo");
        Curso curso = dao.buscarPorCodigo(codigo);

        request.setAttribute("curso", curso);
        request.getRequestDispatcher("WEB-INF/editar.jsp").forward(request, response);
    }

    private void actualizarCurso(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        String codigo = request.getParameter("codigo");
        String nombre = request.getParameter("nombre");
        int creditos = Integer.parseInt(request.getParameter("creditos"));

        Curso curso = new Curso();
        curso.setCodigo(codigo);
        curso.setNombre(nombre);
        curso.setCreditos(creditos);

        dao.actualizar(curso);

        response.sendRedirect("CursoServlet?action=listar");
    }

    private void eliminarCurso(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        String codigo = request.getParameter("codigo");

        dao.eliminar(codigo);

        response.sendRedirect("CursoServlet?action=listar");
    }
}


