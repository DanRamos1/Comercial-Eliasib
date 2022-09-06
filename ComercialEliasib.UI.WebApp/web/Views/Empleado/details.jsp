<%@page import="comercialeliasib.entidadesdenegocio.Estado"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@page import="comercialeliasib.entidadesdenegocio.Empleado"%>
<% Empleado empleado = (Empleado) request.getAttribute("empleado");%>
<!DOCTYPE html>
<html>
    <head>        
        <jsp:include page="/Views/Shared/title.jsp" />
        <title>Detalle de Empleado</title>
    </head>
    <body style="background-image: url(Views/img/wwe.jpg);background-size: 100% 100%;background-attachment: fixed">
        <jsp:include page="/Views/Shared/headerBody.jsp" />  
        <main class="container">   
            <h5 class="white-text">Detalle de Empleado</h5>
            <div class="row">
                <div class="input-field col l4 s12">
                    <input class="white-text" id="txtNombre" type="text" value="<%=empleado.getNombre()%>" disabled>
                    <label class="white-text" for="txtNombre">Nombre</label>
                </div>                       
                <div class="input-field col l4 s12">
                    <input class="white-text" id="txtApellido" type="text" value="<%=empleado.getApellidos()%>" disabled>
                    <label class="white-text" for="txtApellido">Apellido</label>
                </div> 
                <div class="input-field col l4 s12">
                    <input class="white-text" id="txtContacto" type="text" value="<%=empleado.getContacto()%>" disabled>
                    <label class="white-text" for="txtContacto">Contacto</label>
                </div>    
                <div class="input-field col l4 s12">
                    <input class="white-text" id="txtnumeroDocumento" type="text" value="<%=empleado.getNumeroDocumento()%>" disabled>
                    <label class="white-text" for="txtnumeroDocumento">Numero Documento</label>
                </div>    
                <div class="input-field col l4 s12">   
                    <select id="slEstatus" name="estatus" disabled>
                        <option value="0" <%=(empleado.getEstado() == 10) ? "selected" : ""%>>SELECCIONAR</option>
                        <option value="<%=Estado.ACTIVO%>"  <%=(empleado.getEstado() == Estado.ACTIVO) ? "selected" : ""%>>ACTIVO</option>
                        <option value="<%=Estado.INACTIVO%>"  <%=(empleado.getEstado() == Estado.INACTIVO) ? "selected" : ""%>>INACTIVO</option>
                    </select>       
                    <label for="slEstatus" class="white-text">Estatus</label>                       
                </div>
                <div class="input-field col l4 s12">
                    <input id="txtRol" type="text" value="<%=empleado.getRol().getNombre()%>" disabled>
                    <label for="txtRol">Rol</label>
                </div> 
                <div class="row">
                    <div class="col l12 s12">
                        <a href="Empleado?accion=edit&id=<%=empleado.getId()%>" class="waves-effect waves-light btn #e1bee7 purple lighten-4"><i class="material-icons right">edit</i>Ir modificar</a>            
                        <a href="Empleado" class="waves-effect waves-light btn #e1bee7 purple lighten-4"><i class="material-icons right">list</i>Cancelar</a>                          
                    </div>
                </div>          
        </main>
        <jsp:include page="/Views/Shared/footerBody.jsp" />         
    </body>
</html>
