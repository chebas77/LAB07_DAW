<%@ page contentType="text/html;charset=UTF-8" %>
<html>
<head>
    <title>Agregar Curso</title>
    <script src="https://cdn.tailwindcss.com"></script>
</head>
<body class="bg-gray-100 flex flex-col items-center justify-center min-h-screen">
<h1 class="text-3xl font-bold mb-6 text-green-600">Agregar Nuevo Curso ➕</h1>
<form action="CursoServlet?action=insertar" method="post" class="bg-white p-8 rounded shadow-md w-80">
    <div class="mb-4">
        <label class="block text-gray-700">Código:</label>
        <input type="text" name="codigo" required class="w-full px-3 py-2 border rounded">
    </div>
    <div class="mb-4">
        <label class="block text-gray-700">Nombre:</label>
        <input type="text" name="nombre" required class="w-full px-3 py-2 border rounded">
    </div>
    <div class="mb-6">
        <label class="block text-gray-700">Créditos:</label>
        <input type="number" name="creditos" required class="w-full px-3 py-2 border rounded">
    </div>
    <button type="submit" class="w-full bg-green-500 hover:bg-green-700 text-white font-bold py-2 px-4 rounded">
        Guardar
    </button>
</form>
</body>
</html>
