<%@ page contentType="text/html;charset=UTF-8" %>
<%@ page import="com.tecsup.lab06.model.Curso" %>
<html>
<head>
    <title>Editar Curso</title>
    <script src="https://cdn.tailwindcss.com"></script>
</head>
<body class="bg-gray-100 flex flex-col items-center justify-center min-h-screen">
<h1 class="text-3xl font-bold mb-6 text-blue-600">Editar Curso ✏️</h1>
<%
    Curso curso = (Curso) request.getAttribute("curso");
%>
<form action="CursoServlet?action=actualizar" method="post" class="bg-white p-8 rounded shadow-md w-80">
    <div class="mb-4">
        <label class="block text-gray-700">Código:</label>
        <input type="text" name="codigo" value="<%= curso.getCodigo() %>" readonly class="w-full px-3 py-2 border rounded">
    </div>
    <div class="mb-4">
        <label class="block text-gray-700">Nombre:</label>
        <input type="text" name="nombre" value="<%= curso.getNombre() %>" required class="w-full px-3 py-2 border rounded">
    </div>
    <div class="mb-6">
        <label class="block text-gray-700">Créditos:</label>
        <input type="number" name="creditos" value="<%= curso.getCreditos() %>" required class="w-full px-3 py-2 border rounded">
    </div>
    <button type="submit" class="w-full bg-blue-500 hover:bg-blue-700 text-white font-bold py-2 px-4 rounded">
        Actualizar
    </button>
</form>
</body>
</html>
