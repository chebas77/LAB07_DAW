<%@ page contentType="text/html;charset=UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
  <title>Agregar Categoría</title>
  <script src="https://cdn.tailwindcss.com"></script>
  <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.4.0/css/all.min.css">
</head>
<body class="bg-gradient-to-r from-indigo-50 to-purple-50 flex flex-col items-center justify-center min-h-screen p-4">
<div class="w-full max-w-md">
  <div class="bg-white rounded-xl shadow-lg overflow-hidden">
    <div class="bg-gradient-to-r from-purple-500 to-violet-600 p-6">
      <h1 class="text-2xl font-extrabold text-white text-center flex items-center justify-center">
        <i class="fas fa-layer-group mr-3"></i>Nueva Categoría
      </h1>
    </div>

    <form action="CategoriaServlet?action=insertar" method="post" class="p-6">
      <div class="space-y-4">
        <div>
          <label class="block text-gray-700 text-sm font-bold mb-2" for="nombre">
            <i class="fas fa-tag mr-1"></i>Nombre
          </label>
          <input
                  type="text"
                  id="nombre"
                  name="nombre"
                  required
                  placeholder="Ej: Electrónicos"
                  class="w-full px-4 py-2 border border-gray-300 rounded-lg focus:outline-none focus:ring-2 focus:ring-purple-500"
          >
          <p class="text-xs text-gray-500 mt-1">Utilice un nombre descriptivo y único</p>
        </div>

        <div>
          <label class="block text-gray-700 text-sm font-bold mb-2" for="descripcion">
            <i class="fas fa-align-left mr-1"></i>Descripción
          </label>
          <textarea
                  id="descripcion"
                  name="descripcion"
                  placeholder="Describa la categoría y sus características"
                  class="w-full px-4 py-2 border border-gray-300 rounded-lg focus:outline-none focus:ring-2 focus:ring-purple-500"
                  rows="4"
          ></textarea>
        </div>
      </div>

      <div class="mt-8 flex gap-4">
        <a href="CategoriaServlet?action=listar"
           class="flex-1 bg-gray-300 hover:bg-gray-400 text-gray-800 font-bold py-3 px-4 rounded-lg transition duration-300 flex items-center justify-center">
          <i class="fas fa-arrow-left mr-2"></i>Cancelar
        </a>
        <button
                type="submit"
                class="flex-1 bg-purple-500 hover:bg-purple-700 text-white font-bold py-3 px-4 rounded-lg transition duration-300 flex items-center justify-center">
          <i class="fas fa-save mr-2"></i>Guardar
        </button>
      </div>
    </form>

    <div class="bg-gray-50 px-6 py-4 border-t">
      <div class="flex items-center justify-center gap-2">
        <i class="fas fa-info-circle text-purple-500"></i>
        <p class="text-sm text-gray-600">El nombre de la categoría es obligatorio</p>
      </div>
    </div>
  </div>

  <!-- Formulario Tips -->
  <div class="mt-4 bg-white p-4 rounded-lg shadow-md">
    <h2 class="text-sm font-bold text-gray-700 mb-2 flex items-center">
      <i class="fas fa-lightbulb text-yellow-500 mr-2"></i>Consejos
    </h2>
    <ul class="text-xs text-gray-600 space-y-1">
      <li class="flex items-start">
        <i class="fas fa-check-circle text-green-500 mr-1 mt-0.5"></i>
        <span>Las categorías ayudan a organizar los productos de manera eficiente</span>
      </li>
      <li class="flex items-start">
        <i class="fas fa-check-circle text-green-500 mr-1 mt-0.5"></i>
        <span>Utilice nombres cortos pero descriptivos para facilitar la búsqueda</span>
      </li>
      <li class="flex items-start">
        <i class="fas fa-check-circle text-green-500 mr-1 mt-0.5"></i>
        <span>Una buena descripción facilita la comprensión del propósito de la categoría</span>
      </li>
    </ul>
  </div>
</div>
</body>
</html>