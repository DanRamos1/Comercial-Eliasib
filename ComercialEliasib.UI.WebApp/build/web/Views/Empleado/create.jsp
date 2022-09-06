<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@page import="comercialeliasib.entidadesdenegocio.Empleado"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <jsp:include page="/Views/Shared/title.jsp" />
        <title>Crear Empleado</title>
    </head>
    <body style="background-image: url(Views/img/wwe.jpg);background-size: 100% 100%;background-attachment: fixed">
        <jsp:include page="/Views/Shared/headerBody.jsp" />  
        <main class="container">   
            <h5 class="white-text">Crear Empleado</h5>
            <form action="Empleado" method="post">
                <input type="hidden" name="accion" value="<%=request.getAttribute("accion")%>">                
                <div class="row">
                    <div class="input-field col l4 s12">
                        <input class="white-text" autocomplete="of" id="txtNombre" type="text" name="nombre" required class="validate" maxlength="30">
                        <label class="white-text" for="txtNombre">Nombre</label>
                    </div> 
                    <div class="input-field col l4 s12">
                        <input autocomplete="of" class="white-text" id="txtApellido" type="text" name="apellido" required class="validate" maxlength="30">
                        <label for="txtApellido" class="white-text">Apellido</label>
                    </div>  
                    <div class="input-field col l4 s12">
                        <input  id="txtContacto" class="white-text" autocomplete="of" type="text" name="contacto" required class="validate" maxlength="30">
                        <label for="txtContacto" class="white-text">Contacto</label>
                    </div> 
                    <div class="input-field col l4 s12">
                        <input  id="txtnumeroDocumento" class="white-text" autocomplete="of" type="text" name="numeroDocumento" required class="validate" maxlength="30">
                        <label for="txtnumeroDocumento" class="white-text">Numero Documento</label>
                    </div> 
                    <div class="input-field col l4 s12">   
                        <select id="slEstatus" name="estado" class="white-text" class="validate">
                            <option value="0">SELECCIONAR</option>
                            <option value="<%=Empleado.EstatusEmpleado.ACTIVO%>">ACTIVO</option>
                            <option value="<%=Empleado.EstatusEmpleado.INACTIVO%>">INACTIVO</option>
                        </select>       
                        <label for="slEstatus" class="white-text">Estatus</label>
                        <span id="slEstatus_error" style="color:red" class="helper-text"></span>
                    </div>
                    <div class="input-field col l4 s12">   
                        <jsp:include page="/Views/Rol/select.jsp">                           
                            <jsp:param name="id" value="0" />  
                        </jsp:include>  
                        <span id="slRol_error" style="color:red" class="helper-text"></span>
                    </div>
                </div>
                <div class="row">
                    <div class="col l12 s12">
                        <button type="sutmit" class="waves-effect waves-light btn #e1bee7 purple lighten-4"><i class="material-icons right">save</i>Guardar</button>
                        <a href="Empleado" class="waves-effect waves-light btn #e1bee7 purple lighten-4"><i class="material-icons right">list</i>Cancelar</a>                          
                    </div>
                </div>
            </form>          
        </main>
        <jsp:include page="/Views/Shared/footerBody.jsp" />
    </body>
</html>