<%-- 
    Document   : title
    Created on : 29 ago 2022, 10:12:19
    Author     : Admin
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@page import="comercialeliasib.ui.webapp.utils.Utilidad"%>
<%@page import="javax.servlet.http.HttpServletRequest"%>
<!--Import Google Icon Font-->
<link href="https://fonts.googleapis.com/icon?family=Material+Icons" rel="stylesheet">
<!-- Compiled and minified CSS -->
<!--<link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/materialize/1.0.0/css/materialize.min.css">-->
<link rel="stylesheet" href="<%=Utilidad.ObtenerRuta(request, "/wwwroot/lib/materialize/css/materialize.min.css") %>">
<script
  src="https://code.jquery.com/jquery-3.3.1.js"
  integrity="sha256-2Kok7MbOyxpgUVvAk/HJ2jigOSYS2auK4Pfzbm7uH60=" crossorigin="anonymous"></script>
<!-- Compiled and minified JavaScript -->
 <!--<script src="https://cdnjs.cloudflare.com/ajax/libs/materialize/1.0.0/js/materialize.min.js"></script>-->
<script src="<%=Utilidad.ObtenerRuta(request, "/wwwroot/lib/materialize/js/materialize.min.js") %>"></script>
<!--Let browser know website is optimized for mobile-->
<meta name="viewport" content="width=device-width, initial-scale=1.0"/>