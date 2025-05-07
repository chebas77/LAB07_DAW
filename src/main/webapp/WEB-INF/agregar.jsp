<%@ page contentType="text/html;charset=UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <title>Agregar Curso</title>
    <script src="https://cdn.tailwindcss.com"></script>
    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.4.0/css/all.min.css">
</head>
<body class="bg-gradient-to-r from-indigo-50 to-purple-50 flex flex-col items-center justify-center min-h-screen p-4">
<div class="w-full max-w-md">
    <div class="bg-white rounded-xl shadow-lg overflow-hidden">
        <div class="bg-gradient-to-r from-emerald-500 to-teal-600 p-6">
            <h1 class="text-2xl font-extrabold text-white text-center flex items-center justify-center">
                <i class="fas fa-plus-circle mr-3"></i>Nuevo Curso
            </h1>
        </div>

        <form action="CursoServlet?action=insertar" method="post" class="p-6">
            <div class="space-y-4">
                <div>
                    <label class="block text-gray-700 text-sm font-bold mb-2" for="codigo">
                        <i class="fas fa-hashtag mr-1"></i>Código
                    </label>
                    <input
                            type="text"
                            id="codigo"
                            name="codigo"
                            required
                            placeholder="Ej: CS101"
                            pattern="[A-Za-z0-9]+"
                            class="w-full px-4 py-2 border border-gray-300 rounded-lg focus:outline-none focus:ring-2 focus:ring-teal-500"
                    >
                    <p class="text-xs text-gray-500 mt-1">Use letras y números sin espacios</p>
                </div>

                <div>
                    <label class="block text-gray-700 text-sm font-bold mb-2" for="nombre">
                        <i class="fas fa-book mr-1"></i>Nombre
                    </label>
                    <input
                            type="text"
                            id="nombre"
                            name="nombre"
                            required
                            placeholder="Ej: Programación Web"
                            class="w-full px-4 py-2 border border-gray-300 rounded-lg focus:outline-none focus:ring-2 focus:ring-teal-500"
                    >
                </div>

                <div>
                    <label class="block text-gray-700 text-sm font-bold mb-2" for="creditos">
                        <i class="fas fa-star mr-1"></i>Créditos
                    </label>
                    <div class="relative">
                        <input
                                type="number"
                                id="creditos"
                                name="creditos"
                                required
                                min="1"
                                max="10"
                                placeholder="1-10"
                                class="w-full px-4 py-2 border border-gray-300 rounded-lg focus:outline-none focus:ring-2 focus:ring-teal-500"
                        >
                        <div class="absolute inset-y-0 right-0 flex items-center px-2 pointer-events-none">
                            <i class="fas fa-award text-gray-400"></i>
                        </div>
                    </div>
                </div>
            </div>

            <div class="mt-8 flex gap-4">
                <a href="CursoServlet?action=listar"
                   class="flex-1 bg-gray-300 hover:bg-gray-400 text-gray-800 font-bold py-3 px-4 rounded-lg transition duration-300 flex items-center justify-center">
                    <i class="fas fa-arrow-left mr-2"></i>Cancelar
                </a>
                <button
                        type="submit"
                        class="flex-1 bg-emerald-500 hover:bg-emerald-700 text-white font-bold py-3 px-4 rounded-lg transition duration-300 flex items-center justify-center">
                    <i class="fas fa-save mr-2"></i>Guardar
                </button>
            </div>
        </form>

        <div class="bg-gray-50 px-6 py-4 border-t">
            <div class="flex items-center justify-center gap-2">
                <i class="fas fa-info-circle text-teal-500"></i>
                <p class="text-sm text-gray-600">Todos los campos son obligatorios</p>
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
                <span>El código debe ser único para cada curso</span>
            </li>
            <li class="flex items-start">
                <i class="fas fa-check-circle text-green-500 mr-1 mt-0.5"></i>
                <span>Los créditos representan el peso académico del curso</span>
            </li>
            <li class="flex items-start">
                <i class="fas fa-check-circle text-green-500 mr-1 mt-0.5"></i>
                <span>Puede volver al listado de cursos usando el botón Cancelar</span>
            </li>
        </ul>
    </div>
</div>
</body>
</html>