
package comercialeliasib.ui.webapp.utils;
import comercialeliasib.entidadesdenegocio.Usuario;
import java.io.IOException;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

public class SessionUser {
    public static void autenticarUser(HttpServletRequest request, Usuario pUsuario) {
        HttpSession session = (HttpSession) request.getSession();
        session.setMaxInactiveInterval(3600);
        session.setAttribute("auth", true);
        session.setAttribute("user", pUsuario.getLogin());
        session.setAttribute("rol", pUsuario.getRol().getNombre()); 
        
    }
    
    
    
    
}
