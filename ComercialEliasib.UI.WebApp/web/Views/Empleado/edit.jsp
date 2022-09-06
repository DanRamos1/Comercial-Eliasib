<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@page import="comercialeliasib.entidadesdenegocio.*"%>
<% Empleado empleado = (Empleado) request.getAttribute("empleado");%>
<!DOCTYPE html>
<html>
    <head>        
        <jsp:include page="/Views/Shared/title.jsp" />
        <title>Editar Empleado</title>
    </head>
  <body style="background-image: url(Views/img/wwe.jpg);background-size: 100% 100%;background-attachment: fixed">
        <jsp:include page="/Views/Shared/headerBody.jsp" />  
        <main class="container">   
            <h5 class="white-text">Editar Empleado</h5>
            <form action="Empleado" method="post" onsubmit="return  validarFormulario()">
                <input type="hidden" name="accion" value="<%=request.getAttribute("accion")%>"> 
                <input type="hidden" name="id" value="<%=empleado.getId()%>">  
                <div class="row">
                    <div class="input-field col l4 s12">
                        <input class="white-text" autocomplete="of" id="txtNombre" type="text" name="nombre" value="<%=empleado.getNombre()%>" required class="validate" maxlength="30">
                        <label class="white-text"  for="txtNombre">Nombre</label>
                    </div>                       
                    <div class="input-field col l4 s12">
                        <input  class="white-text" autocomplete="of" id="txtApellido" type="text" name="Apellido" value="<%=empleado.getApellidos()%>" required class="validate" maxlength="30">
                        <label class="white-text" for="txtApellido">Apellido</label>
                    </div> 
                    <div class="input-field col l4 s12">
                        <input class="white-text" autocomplete="of"  id="txtContacto" type="text" name="contacto" value="<%=empleado.getContacto()%>" required  class="validate" maxlength="25">
                        <label class="white-text" for="txtContacto">Contacto</label>
                    </div> 
                    <div class="input-field col l4 s12">
                        <input  class="white-text" autocomplete="of" id="txtnumeroDocumento" type="text" name="numeroDocumento" value="<%=empleado.getNumeroDocumento()%>" required  class="validate" maxlength="25">
                        <label class="white-text" for="txtnumeroDocumento">Numero Documento</label>
                    </div>  
                    <div class="input-field col l4 s12">   
                        <select id="slEstatus" name="estado" class="validate">
                            <option value="0" <%=(empleado.getEstado()== 10) ? "selected" : ""%>>SELECCIONAR</option>
                            <option value="<%=Estado.ACTIVO%>"  <%=(empleado.getEstado()== Estado.ACTIVO) ? "selected" : ""%>>ACTIVO</option>
                            <option value="<%=Estado.INACTIVO%>"  <%=(empleado.getEstado()== Estado.INACTIVO) ? "selected" : ""%>>INACTIVO</option>
                        </select>       
                        <label class="white-text" for="slEstatus">Estatus</label>
                        <span id="slEstatus_error" style="color:red" class="helper-text"></span>
                    </div>
                    <div class="input-field col l4 s12">   
                        <jsp:include page="/Views/Rol/select.jsp">                           
                            <jsp:param name="id" value="<%=empleado.getIdRol() %>" />  
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
        <script>
            function validarFormulario() {
                var result = true;
                var slEstatus = document.getElementById("slEstatus");
                var slEstatus_error = document.getElementById("slEstatus_error");
                var slRol = document.getElementById("slRol");
                var slRol_error = document.getElementById("slRol_error");
                if (slEstatus.value == 0) {
                    slEstatus_error.innerHTML = "El estatus es obligatorio";
                    result = false;
                } else {
                    slEstatus_error.innerHTML = "";
                }
                if (slRol.value == 0) {
                    slRol_error.innerHTML = "El Rol es obligatorio";
                    result = false;
                } else {
                    slRol_error.innerHTML = "";
                }

                return result;
            }
        </script>
    </body>
</html>
