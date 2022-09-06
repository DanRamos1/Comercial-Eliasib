<%@page import="comercialeliasib.entidadesdenegocio.Estado"%>
<%@page import="comercialeliasib.entidadesdenegocio.Empleado"%>
<% Empleado empleado = (Empleado) request.getAttribute("empleado");%>
<!DOCTYPE html>
<html>
    <head>        
        <jsp:include page="/Views/Shared/title.jsp" />
        <title>Eliminar Empleado</title>
    </head>
    <body style="background-image: url(Views/img/wwe.jpg);background-size: 100% 100%;background-attachment: fixed">
        <jsp:include page="/Views/Shared/headerBody.jsp" />  
        <main class="container">   
            <h5 class="red-text">Eliminar Empleado</h5>
            <form action="Empleado" method="post">  
                <input type="hidden" name="accion" value="<%=request.getAttribute("accion")%>"> 
                <input type="hidden" name="id" value="<%=empleado.getId()%>">  
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
                        <select id="slEstatus" name="estatus" class="white-text" disabled>
                            <option value="0" <%=(empleado.getEstado() == 10) ? "selected" : ""%>>SELECCIONAR</option>
                            <option value="<%=Estado.ACTIVO%>"  <%=(empleado.getEstado() == Estado.ACTIVO) ? "selected" : ""%>>ACTIVO</option>
                            <option value="<%=Estado.INACTIVO%>"  <%=(empleado.getEstado() == Estado.INACTIVO) ? "selected" : ""%>>INACTIVO</option>
                        </select>       
                        <label class="white-text" for="slEstatus">Estado</label>                       
                    </div>
                    <div class="input-field col l4 s12">
                        <input id="txtRol" type="text" value="<%=empleado.getRol().getNombre()%>" disabled>
                        <label for="txtRol" class="white-text">Rol</label>
                    </div> 
                </div>
                <div class="row">
                    <div class="col l12 s12">
                        <button type="sutmit" class="waves-effect waves-light btn #e1bee7 purple lighten-4"><i class="material-icons right">delete</i>Eliminar</button>
                        <a href="Empleado" class="waves-effect waves-light btn #e1bee7 purple lighten-4"><i class="material-icons right">list</i>Cancelar</a>                          
                    </div>
                </div>
            </form>          
        </main>
        <jsp:include page="/Views/Shared/footerBody.jsp" />         
    </body>
</html>
