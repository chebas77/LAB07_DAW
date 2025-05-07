<%@ page contentType="text/html;charset=UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
  <title>Editar Categoría</title>
  <script src="https://cdn.tailwindcss.com"></script>
  <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.4.0/css/all.min.css">
</head>
<body class="bg-gradient-to-r from-indigo-50 to-purple-50 flex flex-col items-center justify-center min-h-screen p-4">
<div class="w-full max-w-md">
  <div class="bg-white rounded-xl shadow-lg overflow-hidden">
    <div class="bg-gradient-to-r from-purple-600 to-violet-700 p-6">
      <h1 class="text-2xl font-extrabold text-white text-center flex items-center justify-center">
        <i class="fas fa-edit mr-3"></i>Editar Categoría
      </h1>
    </div>

    <form action="CategoriaServlet?action=actualizar" method="post" class="p-6">
      <input type="hidden" name="id" value="${categoria.id}">

      <div class="space-y-4">
        <div>
          <label class="block text-gray-700 text-sm font-bold mb-2" for="id_display">
            <i class="fas fa-hashtag mr-1"></i>ID
          </label>
          <input
                  type="text"
                  id="id_display"
                  value="<c:out value='${categoria.id}'/>"
                  readonly
                  class="w-full px-4 py-2 bg-gray-100 border border-gray-300 rounded-lg focus:outline-none focus:ring-2 focus:ring-purple-500 text-gray-600"
          >
          <p class="text-xs text-gray-500 mt-1">El ID no puede ser modificado</p>
        </div>

        <div>
          <label class="block text-gray-700 text-sm font-bold mb-2" for="nombre">
            <i class="fas fa-tag mr-1"></i>Nombre
          </label>
          <input
                  type="text"
                  id="nombre"
                  name="nombre"
                  value="<c:out value='${categoria.nombre}'/>"
                  required
                  class="w-full px-4 py-2 border border-gray-300 rounded-lg focus:outline-none focus:ring-2 focus:ring-purple-500"
          >
        </div>

        <div>
          <label class="block text-gray-700 text-sm font-bold mb-2" for="descripcion">
            <i class="fas fa-align-left mr-1"></i>Descripción
          </label>
          <textarea
                  id="descripcion"
                  name="descripcion"
                  class="w-full px-4 py-2 border border-gray-300 rounded-lg focus:outline-none focus:ring-2 focus:ring-purple-500"
                  rows="4"
          ><c:out value='${categoria.descripcion}'/></textarea>
        </div>
      </div>

      <div class="mt-8 flex gap-4">
        <a href="CategoriaServlet?action=listar"
           class="flex-1 bg-gray-300 hover:bg-gray-400 text-gray-800 font-bold py-3 px-4 rounded-lg transition duration-300 flex items-center justify-center">
          <i class="fas fa-arrow-left mr-2"></i>Cancelar
        </a>
        <button
                type="submit"
                class="flex-1 bg-purple-600 hover:bg-purple-800 text-white font-bold py-3 px-4 rounded-lg transition duration-300 flex items-center justify-center">
          <i class="fas fa-save mr-2"></i>Actualizar
        </button>
      </div>
    </form>

    <div class="bg-gray-50 px-6 py-4 border-t">
      <p class="text-xs text-center text-gray-600">
        <i class="fas fa-info-circle mr-1"></i>
        Los cambios se guardarán en la base de datos
      </p>
    </div>
  </div>
</div>
</body>
</html>