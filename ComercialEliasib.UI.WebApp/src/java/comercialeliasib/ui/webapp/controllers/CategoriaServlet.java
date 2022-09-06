/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package comercialeliasib.ui.webapp.controllers;

import comercialeliasib.accesoadatos.CategoriaDAL;
import comercialeliasib.entidadesdenegocio.Categoria;
import comercialeliasib.ui.webapp.utils.SessionUser;
import comercialeliasib.ui.webapp.utils.Utilidad;
import java.io.IOException;
import java.util.ArrayList;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author Admin
 */
@WebServlet(name = "CategoriaServlet", urlPatterns = {"/Categoria"})
public class CategoriaServlet extends HttpServlet {
    private Categoria obtenerCategoria(HttpServletRequest request){
        String accion = Utilidad.getParameter(request, "accion", "index");
        Categoria categoria = new Categoria();
        if (accion.equals("create")== false) {
            categoria.setId(Integer.parseInt(Utilidad.getParameter(request, "id", "0")));
        } 
        categoria.setNombre(Utilidad.getParameter(request, "nombre", ""));
        if(accion.equals("index")){
            categoria.setTop_aux(Integer.parseInt(Utilidad.getParameter(request, "top_aux", "10")));
            categoria.setTop_aux(categoria.getTop_aux() == 0 ? Integer.MAX_VALUE : categoria.getTop_aux());
        }
        return categoria;
    }

    private void doGetRequestIndex(HttpServletRequest request, HttpServletResponse response) 
            throws ServletException, IOException {
        try {
            Categoria categoria = new Categoria();
            categoria.setTop_aux(10);
            ArrayList<Categoria> categorias = CategoriaDAL.Buscar(categoria);
            request.setAttribute("categorias", categorias);
            request.setAttribute("top_aux", categoria.getTop_aux());
            request.getRequestDispatcher("Views/Categoria/index.jsp").forward(request, response);
        } catch (Exception ex) {
            Utilidad.enviarError(ex.getMessage(), request, response);
        }
    }
      
    private void doPostRequestIndex(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        try {
            Categoria categoria = obtenerCategoria(request);
            ArrayList<Categoria> categorias = CategoriaDAL.Buscar(categoria);
            request.setAttribute("categorias", categorias);
            request.setAttribute("top_aux", categoria.getTop_aux());
            request.getRequestDispatcher("Views/Categoria/index.jsp").forward(request, response);
        } catch (Exception ex) {
            Utilidad.enviarError(ex.getMessage(), request, response);
        }
    }

    private void doGetRequestCreate(HttpServletRequest request, HttpServletResponse response) 
            throws ServletException, IOException {
        request.getRequestDispatcher("Views/Categoria/create.jsp").forward(request, response);
    }
    
    private void doPostRequestCreate(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        try {
            Categoria categoria = obtenerCategoria(request);
            int result = CategoriaDAL.crear(categoria);
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
            Categoria categoria = obtenerCategoria(request);
            Categoria categoria_result = CategoriaDAL.ObtenerPorId(categoria);
            if (categoria_result.getId() > 0) {
                request.setAttribute("categoria", categoria_result);
            } else {
                Utilidad.enviarError("El Id:" + categoria.getId()
                        + " no existe en la tabla de Marca", request, response);
            }
        } catch (Exception ex) {
            Utilidad.enviarError(ex.getMessage(), request, response);
        }
    }

    private void doGetRequestEdit(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        requestObtenerPorId(request, response);
        request.getRequestDispatcher("Views/Categoria/edit.jsp").forward(request, response);
    }

    private void doPostRequestEdit(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        try {
            Categoria categoria = obtenerCategoria(request);
            int result = CategoriaDAL.Modificar(categoria);
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
        request.getRequestDispatcher("Views/Categoria/details.jsp").forward(request, response);
    }

    private void doGetRequestDelete(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        requestObtenerPorId(request, response);
        request.getRequestDispatcher("Views/Categoria/delete.jsp").forward(request, response);
    }

    private void doPostRequestDelete(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        try {
            Categoria categoria = obtenerCategoria(request);
            int result = CategoriaDAL.Eliminar(categoria);
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
        SessionUser.authorize(request, response, () -> {
            String accion = Utilidad.getParameter(request, "accion", "index");
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
        SessionUser.authorize(request, response, () -> {
            String accion = Utilidad.getParameter(request, "accion", "index");
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
