<%@ page contentType="text/html;charset=UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
  <title>Lista de Categorías</title>
  <script src="https://cdn.tailwindcss.com"></script>
  <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.4.0/css/all.min.css">
</head>
<body class="bg-gradient-to-r from-indigo-50 to-purple-50 p-8">
<div class="max-w-6xl mx-auto bg-white rounded-xl shadow-lg overflow-hidden">
  <div class="bg-gradient-to-r from-purple-600 to-indigo-700 p-6">
    <h1 class="text-3xl font-extrabold text-white text-center">
      <i class="fas fa-layer-group mr-2"></i>Catálogo de Categorías
    </h1>
  </div>

  <div class="p-6">
    <div class="flex justify-end mb-6">
      <a href="CategoriaServlet?action=agregar"
         class="bg-indigo-600 hover:bg-indigo-800 text-white font-bold py-2 px-6 rounded-full transition-all duration-300 flex items-center">
        <i class="fas fa-plus-circle mr-2"></i>Nueva Categoría
      </a>
    </div>

    <div class="overflow-x-auto">
      <table class="w-full border-collapse">
        <thead>
        <tr class="bg-gray-100">
          <th class="py-3 px-6 text-left text-gray-600 font-semibold uppercase text-sm tracking-wider border-b-2 border-gray-300">
            <i class="fas fa-hashtag mr-1"></i>ID
          </th>
          <th class="py-3 px-6 text-left text-gray-600 font-semibold uppercase text-sm tracking-wider border-b-2 border-gray-300">
            <i class="fas fa-tag mr-1"></i>Nombre
          </th>
          <th class="py-3 px-6 text-left text-gray-600 font-semibold uppercase text-sm tracking-wider border-b-2 border-gray-300">
            <i class="fas fa-align-left mr-1"></i>Descripción
          </th>
          <th class="py-3 px-6 text-center text-gray-600 font-semibold uppercase text-sm tracking-wider border-b-2 border-gray-300">
            <i class="fas fa-cogs mr-1"></i>Acciones
          </th>
        </tr>
        </thead>
        <tbody>
        <c:forEach var="cat" items="${listaCategorias}" varStatus="status">
          <tr class="${status.index % 2 == 0 ? 'bg-white' : 'bg-gray-50'} hover:bg-indigo-50 transition-colors duration-200">
            <td class="py-3 px-6 border-b border-gray-200 font-medium text-indigo-700">
              <c:out value="${cat.id}" />
            </td>
            <td class="py-3 px-6 border-b border-gray-200">
              <c:out value="${cat.nombre}" />
            </td>
            <td class="py-3 px-6 border-b border-gray-200">
              <c:out value="${cat.descripcion}" />
            </td>
            <td class="py-3 px-6 border-b border-gray-200 text-center">
              <div class="flex justify-center gap-2">
                <a href="CategoriaServlet?action=editar&id=${cat.id}"
                   class="text-blue-600 hover:text-blue-800 transition-colors duration-200 flex items-center gap-1 bg-blue-50 hover:bg-blue-100 p-2 rounded-md">
                  <i class="fas fa-edit"></i>
                  <span>Editar</span>
                </a>
                <a href="CategoriaServlet?action=eliminar&id=${cat.id}"
                   class="text-red-600 hover:text-red-800 transition-colors duration-200 flex items-center gap-1 bg-red-50 hover:bg-red-100 p-2 rounded-md"
                   onclick="return confirm('¿Está seguro de eliminar esta categoría?')">
                  <i class="fas fa-trash-alt"></i>
                  <span>Eliminar</span>
                </a>
              </div>
            </td>
          </tr>
        </c:forEach>

        <c:if test="${empty listaCategorias}">
          <tr>
            <td colspan="4" class="py-8 text-center text-gray-500 italic border-b border-gray-200">
              <i class="fas fa-info-circle mr-2"></i>No hay categorías disponibles
            </td>
          </tr>
        </c:if>
        </tbody>
      </table>
    </div>

    <div class="mt-6 text-center text-gray-500 text-sm">
      <p>Total de categorías:
        <span class="font-semibold text-indigo-600">
                    <c:out value="${listaCategorias.size()}" default="0"/>
                </span>
      </p>
    </div>
  </div>
</div>
</body>
</html>