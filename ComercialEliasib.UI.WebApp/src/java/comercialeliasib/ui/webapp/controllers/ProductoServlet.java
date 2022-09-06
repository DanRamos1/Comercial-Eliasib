package aurasth.ventas.ui.webapp.controllers;

import comercialeliasib.accesoadatos.CategoriaDAL;
import comercialeliasib.accesoadatos.MarcaDAL;
import comercialeliasib.accesoadatos.ProductoDAL;
import comercialeliasib.entidadesdenegocio.*;
import comercialeliasib.ui.webapp.utils.SessionUser;
import comercialeliasib.ui.webapp.utils.Utilidad;
import java.io.IOException;
import java.util.ArrayList;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet(name = "ProductoServlet", urlPatterns = {"/Producto"})
public class ProductoServlet extends HttpServlet {
    
   
  private Producto obtenerProducto(HttpServletRequest request) {
        String accion = Utilidad.getParameter(request, "accion", "index");
        Producto producto = new Producto();
        producto.setNombre(Utilidad.getParameter(request, "nombre", ""));
        producto.setDescripcion(Utilidad.getParameter(request, "descripcion", ""));
        producto.setPrecio(Double.parseDouble(Utilidad.getParameter(request, "precio", "0")));
        producto.setStock(Integer.parseInt(Utilidad.getParameter(request, "stock", "0")));
        producto.setIdCategoria(Integer.parseInt(Utilidad.getParameter(request, "idCategoria", "0")));    
        producto.setIdMarca(Integer.parseInt(Utilidad.getParameter(request, "idMarca", "0")));
        producto.setEstado(Byte.parseByte(Utilidad.getParameter(request, "estado", "0")));
        
        if (accion.equals("index")) {
            producto.setTop_aux(Integer.parseInt(Utilidad.getParameter(request, "top_aux", "10")));
            producto.setTop_aux(producto.getTop_aux() == 0 ? Integer.MAX_VALUE : producto.getTop_aux());
        }else {
            producto.setId(Integer.parseInt(Utilidad.getParameter(request, "id", "0")));
        }
        return producto;
    }
    
    private void doGetRequestIndex(HttpServletRequest request, HttpServletResponse response) 
            throws ServletException, IOException {
        try {
            Producto producto = new Producto();
            producto.setTop_aux(10);
            ArrayList<Producto> productos = ProductoDAL.BuscarIncluirForaneas(producto);
            request.setAttribute("productos", productos);
            request.setAttribute("top_aux", producto.getTop_aux());
            request.getRequestDispatcher("Views/Producto/index.jsp").forward(request, response);
        } catch (Exception ex) {
            Utilidad.enviarError(ex.getMessage(), request, response);
        }
    }
    
    
    private void doPostRequestIndex(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        try {
            Producto producto = obtenerProducto(request);
            ArrayList<Producto> productos = ProductoDAL.BuscarIncluirForaneas(producto);
            request.setAttribute("productos", productos);    
            request.setAttribute("top_aux", producto.getTop_aux());
            request.getRequestDispatcher("Views/Producto/index.jsp").forward(request, response);
        } catch (Exception ex) {
            Utilidad.enviarError(ex.getMessage(), request, response);
        }
    }
    
    private void doGetRequestCreate(HttpServletRequest request, HttpServletResponse response) 
            throws ServletException, IOException {
        request.getRequestDispatcher("Views/Producto/create.jsp").forward(request, response);
    }
    
    private void doPostRequestCreate(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        try {
            Producto producto = obtenerProducto(request);
            int result = ProductoDAL.Crear(producto);
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
            Producto producto = obtenerProducto(request);
            Producto producto_result = ProductoDAL.ObtenerPorId(producto);
            if (producto_result.getId() > 0) {
                Marca marca = new Marca();
                marca.setId(producto_result.getIdMarca());
                producto_result.setMarca(MarcaDAL.ObtenerPorId(marca));
                Categoria categoria = new Categoria();
                categoria.setId(producto_result.getIdCategoria());
                producto_result.setCategoria(CategoriaDAL.ObtenerPorId(categoria));
                request.setAttribute("producto", producto_result);
            } else {
                Utilidad.enviarError("El Id:" + producto_result.getId() + 
                        " no existe en la tabla de Usuario", request, response);
            }
        } catch (Exception ex) {
            Utilidad.enviarError(ex.getMessage(), request, response);
        }
    }
        
    private void doGetRequestEdit(HttpServletRequest request, HttpServletResponse response) 
            throws ServletException, IOException {
        requestObtenerPorId(request, response);
        request.getRequestDispatcher("Views/Producto/edit.jsp").forward(request, response);
    }
    
    private void doPostRequestEdit(HttpServletRequest request, HttpServletResponse response) 
            throws ServletException, IOException {
        try {
            Producto producto = obtenerProducto(request);
            int result = ProductoDAL.Modificar(producto);
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
        request.getRequestDispatcher("Views/Producto/details.jsp").forward(request, response);
    } 
    
        private void doGetRequestDelete(HttpServletRequest request, HttpServletResponse response) 
                throws ServletException, IOException {
        requestObtenerPorId(request, response);
        request.getRequestDispatcher("Views/Producto/delete.jsp").forward(request, response);
    }

    private void doPostRequestDelete(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        try {
            Producto producto = obtenerProducto(request);
            int result = ProductoDAL.Eliminar(producto);
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