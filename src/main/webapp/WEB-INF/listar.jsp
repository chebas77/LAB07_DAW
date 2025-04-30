<%@ page contentType="text/html;charset=UTF-8" %>
<%@ page import="com.tecsup.lab06.model.Curso" %>
<%@ page import="java.util.List" %>
<html>
<head>
    <title>Lista de Cursos</title>
    <script src="https://cdn.tailwindcss.com"></script>
</head>
<body class="bg-gray-100 p-10">
<h1 class="text-3xl font-bold mb-6 text-center text-blue-700">Lista de Cursos 📚</h1>
<div class="flex justify-center mb-6">
    <a href="CursoServlet?action=agregar" class="bg-green-500 hover:bg-green-700 text-white font-bold py-2 px-4 rounded">Agregar Nuevo Curso</a>
</div>

<div class="overflow-x-auto">
    <table class="min-w-full bg-white rounded shadow-md">
        <thead>
        <tr>
            <th class="py-2 px-4 border-b">Código</th>
            <th class="py-2 px-4 border-b">Nombre</th>
            <th class="py-2 px-4 border-b">Créditos</th>
            <th class="py-2 px-4 border-b">Acciones</th>
        </tr>
        </thead>
        <tbody>
        <%
            List<Curso> lista = (List<Curso>) request.getAttribute("listaCursos");
            if (lista != null) {
                for (Curso c : lista) {
        %>
        <tr class="text-center">
            <td class="py-2 px-4 border-b"><%= c.getCodigo() %></td>
            <td class="py-2 px-4 border-b"><%= c.getNombre() %></td>
            <td class="py-2 px-4 border-b"><%= c.getCreditos() %></td>
            <td class="py-2 px-4 border-b">
                <a href="CursoServlet?action=editar&codigo=<%= c.getCodigo() %>" class="text-blue-500 hover:underline mr-2">Editar</a>
                <a href="CursoServlet?action=eliminar&codigo=<%= c.getCodigo() %>" class="text-red-500 hover:underline">Eliminar</a>
            </td>
        </tr>
        <%
                }
            }
        %>
        </tbody>
    </table>
</div>
</body>
</html>
