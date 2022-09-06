<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@page import="comercialeliasib.entidadesdenegocio.Estado"%>
<%@page import="comercialeliasib.entidadesdenegocio.Empleado"%>
<%@page import="comercialeliasib.entidadesdenegocio.Rol"%>
<%@page import="java.util.ArrayList"%>
<% ArrayList<Empleado> empleados = (ArrayList<Empleado>) request.getAttribute("empleados");
    int numPage = 1;
    int numReg = 10;
    int countReg = 0;
    if (empleados == null) {
        empleados = new ArrayList();
    } else if (empleados.size() > numReg) {
        double divNumPage = (double) empleados.size() / (double) numReg;
        numPage = (int) Math.ceil(divNumPage);
    }
    String strTop_aux = request.getParameter("top_aux");
    int top_aux = 10;
    if (strTop_aux != null && strTop_aux.trim().length() > 0) {
        top_aux = Integer.parseInt(strTop_aux);
    }
%>
<!DOCTYPE html>
<html>
    <head>        
        <jsp:include page="/Views/Shared/title.jsp" />
        <title>Buscar Empleados</title>

    </head>
    <body style="background-image: url(Views/img/wwe.jpg);background-size: 100% 100%;background-attachment: fixed">
        <jsp:include page="/Views/Shared/headerBody.jsp" />  
        <main class="container">   
            <h5 class="white-text">Buscar Empleados</h5>
            <form action="Empleado" method="post">
                <input type="hidden" name="accion" value="<%=request.getAttribute("accion")%>"> 
                <div class="row">
                    <div class="input-field col l4 s12">
                        <input  class="white-text" autocomplete="of" id="txtNombre" type="text" name="nombre">
                        <label for="txtNombre" class="white-text">Nombre</label>
                    </div>  
                    <div class="input-field col l4 s12">
                        <input  class="white-text" autocomplete="of" id="txtApellido" type="text" name="apellido">
                        <label for="txtApellido" class="white-text">Apellido</label>
                    </div> 
                    <div class="input-field col l4 s12">
                        <input  class="white-text" autocomplete="of" id="txtContacto" type="text" name="contacto">
                        <label for="txtContacto" class="white-text">Contacto</label>
                    </div>      
                    <div class="input-field col l4 s12">
                        <input  class="white-text" autocomplete="of" id="txtnumeroDocumento" type="text" name="numeroDocumento">
                        <label for="txtnumeroDocumento" class="white-text">N~ Documento</label>
                    </div> 
                    <div class="input-field col l4 s12">   
                        <select id="slEstatus" name="Estado">
                            <option value="0">SELECCIONAR</option>
                            <option value="<%=Empleado.EstatusEmpleado.ACTIVO%>">ACTIVO</option>
                            <option value="<%=Empleado.EstatusEmpleado.INACTIVO%>">INACTIVO</option>
                        </select>      
                        <label for="slEstatus" class="white-text">Estatus</label>
                    </div>
                    <div class="input-field col l4 s12">   
                        <jsp:include page="/Views/Rol/select.jsp">                           
                            <jsp:param name="id" value="0" />  
                        </jsp:include>                        
                    </div>
                    <div class="input-field col l4 s12 white-text">   
                        <jsp:include page="/Views/Shared/selectTop.jsp">
                            <jsp:param name="top_aux" value="<%=top_aux%>" />                        
                        </jsp:include>                        
                    </div> 
                </div>
                <div class="row">
                    <div class="col l12 s12">
                        <button type="sutmit" class="waves-effect waves-light btn #e1bee7 purple lighten-4"><i class="material-icons right">search</i>Buscar</button>
                        <a href="Empleado?accion=create" class="waves-effect waves-light btn #e1bee7 purple lighten-4"><i class="material-icons right">add</i>Crear</a>                          
                    </div>
                </div>
            </form>

            <div class="row">
                <div class="col l12 s12">
                    <div style="overflow: auto">
                        <table class="paginationjs striped highlight white-text">
                            <thead>
                                <tr>                                     
                                    <th>Nombre</th>  
                                    <th>Apellido</th> 
                                    <th>Contacto</th>
                                    <th>N~ Documento</th>  
                                    <th>Estado</th>  
                                    <th>Rol</th>   
                                    <th>Fecha registro</th>   
                                    <th>Acciones</th>
                                </tr>
                            </thead>                       
                            <tbody>                           
                                <% for (Empleado empleado : empleados) {
                                        int tempNumPage = numPage;
                                        if (numPage > 1) {
                                            countReg++;
                                            double divTempNumPage = (double) countReg / (double) numReg;
                                            tempNumPage = (int) Math.ceil(divTempNumPage);
                                        }
                                        String estado = "";
                                        switch (empleado.getEstado()) {
                                            case Estado.ACTIVO:
                                                estado = "ACTIVO";
                                                break;
                                            case Estado.INACTIVO:
                                                estado = "INACTIVO";
                                                break;
                                            default:
                                                estado = "";
                                        }
                                %>
                                <tr data-page="<%= tempNumPage%>">                                    
                                    <td><%=empleado.getNombre()%></td>  
                                    <td><%=empleado.getApellidos()%></td>
                                    <td><%=empleado.getContacto()%></td>
                                    <td><%=empleado.getNumeroDocumento()%></td>  
                                    <td><%=estado%></td>
                                    <td><%=empleado.getRol().getNombre()%></td> 
                                    <td><%=empleado.getFecha()%></td> 
                                    <td>
                                        <div style="display:flex">
                                             <a href="Empleado?accion=edit&id=<%=empleado.getId()%>" title="Modificar" class="waves-effect waves-light btn green">
                                            <i class="material-icons">edit</i>
                                        </a>
                                        <a href="Empleado?accion=details&id=<%=empleado.getId()%>" title="Ver" class="waves-effect waves-light btn blue">
                                            <i class="material-icons">description</i>
                                        </a>
                                        <a href="Empleado?accion=delete&id=<%=empleado.getId()%>" title="Eliminar" class="waves-effect waves-light btn red">
                                            <i class="material-icons">delete</i>
                                        </a>    
                                        </div>                                                                    
                                    </td>                                   
                                </tr>
                                <%}%>                                                       
                            </tbody>
                        </table>
                    </div>                  
                </div>
            </div>             
            <div class="row">
                <div class="col l12 s12">
                    <jsp:include page="/Views/Shared/paginacion.jsp">
                        <jsp:param name="numPage" value="<%= numPage%>" />                        
                    </jsp:include>
                </div>
            </div>
        </main>
        <jsp:include page="/Views/Shared/footerBody.jsp" />      
    </body>
</html>
