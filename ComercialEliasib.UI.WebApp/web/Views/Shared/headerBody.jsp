<%-- 
    Document   : headerBody
    Created on : 29 ago 2022, 10:11:55
    Author     : Admin
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@page import="comercialeliasib.ui.webapp.utils.*"%>
<%@page import="javax.servlet.http.HttpServletRequest"%>

<nav>
    <div class="nav-wrapper #e57373 red lighten-2">
        <a href="Home" class="brand-logo">Comercial Eliasib</a>
        <a href="#" data-target="mobile-demo" class="sidenav-trigger"><i class="material-icons">menu</i></a>       
        <ul class="right hide-on-med-and-down">  
            <% if (SessionUser.isAuth(request)) {  %>
            <li><a href="Home">Inicio</a></li>
            <li><a href="Producto">Productos</a></li>
            <li><a href="Usuario">Usuarios</a></li>
            <li><a href="Rol">Roles</a></li>
            <li><a href="Marca">Marcas</a></li>
            <li><a href="Empreado">Empleados</a></li>
            <li><a href="Categoria">Categorias</a></li>
            <li><a href="Usuario?accion=cambiarpass">Cambiar Contraseña</a></li>
            <li><a href="Usuario?accion=login">Cerrar sesión</a></li>
            <%}%>
        </ul>
    </div>
</nav>

<ul class="sidenav" id="mobile-demo">
     <% if (SessionUser.isAuth(request)) {  %>
     <li><a href="Home">Inicio</a></li>
     <li><a href="Producto">Productos</a></li>
     <li><a href="Empleados">Empleados</a></li>
     <li><a href="Usuario">Usuarios</a></li>
     <li><a href="Rol">Roles</a></li>
     <li><a href="Categoria">Categorias</a></li>
     <li><a href="Usuario?accion=cambiarpass">Cambiar Contraseña</a></li>
     <li><a href="Usuario?accion=login">Cerrar sesión</a></li>
     <%}%>
</ul>