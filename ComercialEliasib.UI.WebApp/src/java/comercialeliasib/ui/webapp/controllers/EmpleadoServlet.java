package comercialeliasib.ui.webapp.controllers;

import comercialeliasib.accesoadatos.*;
import comercialeliasib.entidadesdenegocio.Empleado;
import comercialeliasib.entidadesdenegocio.Rol;
import comercialeliasib.ui.webapp.utils.SessionUser;
import comercialeliasib.ui.webapp.utils.Utilidad;
import java.io.IOException;
import java.util.ArrayList;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


@WebServlet(name = "EmpleadoServlet", urlPatterns = {"/Empleado"})
public class EmpleadoServlet extends HttpServlet {
    
  private Empleado obtenerEmpleado(HttpServletRequest request) {
        String accion = Utilidad.getParameter(request, "accion", "index");
        Empleado empleado = new Empleado();
        empleado.setNombre(Utilidad.getParameter(request, "nombre", ""));
        empleado.setApellidos(Utilidad.getParameter(request, "Apellido", ""));
        empleado.setContacto(Utilidad.getParameter(request, "contacto", ""));
        empleado.setNumeroDocumento(Utilidad.getParameter(request, "numeroDocumento", ""));
        empleado.setIdRol(Integer.parseInt(Utilidad.getParameter(request, "idRol", "0")));
        empleado.setEstado(Byte.parseByte(Utilidad.getParameter(request, "estado", "0")));
        
        if (accion.equals("index")) {
            empleado.setTop_aux(Integer.parseInt(Utilidad.getParameter(request, "top_aux", "10")));
            empleado.setTop_aux(empleado.getTop_aux() == 0 ? Integer.MAX_VALUE : empleado.getTop_aux());
        }else {
            empleado.setId(Integer.parseInt(Utilidad.getParameter(request, "id", "0")));
        }
        return empleado;
    }
    
    private void doGetRequestIndex(HttpServletRequest request, HttpServletResponse response) 
            throws ServletException, IOException {
        try {
            Empleado empleado = new Empleado();
            empleado.setTop_aux(10);
            ArrayList<Empleado> empleados = EmpleadoDAL.BuscarIncluirRol(empleado);
            request.setAttribute("empleados", empleados);
            request.setAttribute("top_aux", empleado.getTop_aux());
            request.getRequestDispatcher("Views/Empleado/index.jsp").forward(request, response);
        } catch (Exception ex) {
            Utilidad.enviarError(ex.getMessage(), request, response);
        }
    }
    
    
    private void doPostRequestIndex(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        try {
            Empleado empleado = obtenerEmpleado(request);
            ArrayList<Empleado> empleados = EmpleadoDAL.BuscarIncluirRol(empleado);
            request.setAttribute("empleados", empleados);
            request.setAttribute("top_aux", empleado.getTop_aux());
            request.getRequestDispatcher("Views/Empleado/index.jsp").forward(request, response);
        } catch (Exception ex) {
            Utilidad.enviarError(ex.getMessage(), request, response);
        }
    }
    
    private void doGetRequestCreate(HttpServletRequest request, HttpServletResponse response) 
            throws ServletException, IOException {
        request.getRequestDispatcher("Views/Empleado/create.jsp").forward(request, response);
    }
    
    private void doPostRequestCreate(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        try {
            Empleado empleado = obtenerEmpleado(request);
            int result = EmpleadoDAL.Crear(empleado);
            if (result != 0) {
                request.setAttribute("accion", "index");
                doGetRequestIndex(request, response);
            } else {
                Utilidad.enviarError("No se logro registrar un nuevo registro", request, response);
            }
        } catch (Exception ex) {
            Utilidad.enviarError(ex.getMessage(), request, response);
        }
    }
    
        private void requestObtenerPorId(HttpServletRequest request, HttpServletResponse response) 
                throws ServletException, IOException {
        try {
            Empleado empleado = obtenerEmpleado(request);
            Empleado empleado_result = EmpleadoDAL.ObtenerPorId(empleado);
            if (empleado_result.getId() > 0) {
                Rol rol = new Rol();
                rol.setId(empleado_result.getIdRol());
                empleado_result.setRol(RolDAL.ObtenerPorId(rol));
                request.setAttribute("empleado", empleado_result);
            } else {
                Utilidad.enviarError("El Id:" + empleado_result.getId() + 
                        " no existe en la tabla de Usuario", request, response);
            }
        } catch (Exception ex) {
            Utilidad.enviarError(ex.getMessage(), request, response);
        }
    }
        
    private void doGetRequestEdit(HttpServletRequest request, HttpServletResponse response) 
            throws ServletException, IOException {
        requestObtenerPorId(request, response);
        request.getRequestDispatcher("Views/Empleado/edit.jsp").forward(request, response);
    }
    
    private void doPostRequestEdit(HttpServletRequest request, HttpServletResponse response) 
            throws ServletException, IOException {
        try {
            Empleado empleado = obtenerEmpleado(request);
            int result = EmpleadoDAL.Modificar(empleado);
            if (result != 0) {
                request.setAttribute("accion", "index");
                doGetRequestIndex(request, response);
            } else {
                Utilidad.enviarError("No se logro actualizar el registro", request, response);
            }
        } catch (Exception ex) {
            Utilidad.enviarError(ex.getMessage(), request, response);
        }
    }
    
    private void doGetRequestDetails(HttpServletRequest request, HttpServletResponse response) 
            throws ServletException, IOException {
        requestObtenerPorId(request, response);
        request.getRequestDispatcher("Views/Empleado/details.jsp").forward(request, response);
    } 
    
        private void doGetRequestDelete(HttpServletRequest request, HttpServletResponse response) 
                throws ServletException, IOException {
        requestObtenerPorId(request, response);
        request.getRequestDispatcher("Views/Empleado/delete.jsp").forward(request, response);
    }

    private void doPostRequestDelete(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        try {
            Empleado empleado = obtenerEmpleado(request);
            int result = EmpleadoDAL.Eliminar(empleado);
            if (result != 0) {
                request.setAttribute("accion", "index");
                doGetRequestIndex(request, response);
            } else {
                Utilidad.enviarError("No se logro eliminar el registro", request, response);
            }
        } catch (Exception ex) {
            Utilidad.enviarError(ex.getMessage(), request, response);
        }
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String accion = Utilidad.getParameter(request, "accion", "index");
            SessionUser.authorize(request, response, () -> {
                switch (accion) {
                    case "index":
                        request.setAttribute("accion", accion);
                        doGetRequestIndex(request, response);
                        break;
                    case "create":
                        request.setAttribute("accion", accion);
                        doGetRequestCreate(request, response);
                        break;
                    case "edit":
                        request.setAttribute("accion", accion);
                        doGetRequestEdit(request, response);
                        break;
                    case "delete":
                        request.setAttribute("accion", accion);
                        doGetRequestDelete(request, response);
                        break;
                    case "details":
                        request.setAttribute("accion", accion);
                        doGetRequestDetails(request, response);
                        break;
                    default:
                        request.setAttribute("accion", accion);
                        doGetRequestIndex(request, response);
                }
            });
      }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String accion = Utilidad.getParameter(request, "accion", "index");
        SessionUser.authorize(request, response, () -> {
            switch (accion) {
                case "index":
                    request.setAttribute("accion", accion);
                    doPostRequestIndex(request, response);
                    break;
                case "create":
                    request.setAttribute("accion", accion);
                    doPostRequestCreate(request, response);
                    break;
                case "edit":
                    request.setAttribute("accion", accion);
                    doPostRequestEdit(request, response);
                    break;
                case "delete":
                    request.setAttribute("accion", accion);
                    doPostRequestDelete(request, response);
                    break;
                default:
                    request.setAttribute("accion", accion);
                    doGetRequestIndex(request, response);
            }
        });
    }
}
