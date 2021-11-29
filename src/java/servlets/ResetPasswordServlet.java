
package servlets;

import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import services.AccountService;


public class ResetPasswordServlet extends HttpServlet {


    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String uuid = request.getParameter("uuid");
        
        if (uuid == null || uuid.equals("")) {           
            getServletContext().getRequestDispatcher("/WEB-INF/reset.jsp").forward(request, response);
            return;
        }
        else {
            request.setAttribute("uuid", uuid);
            getServletContext().getRequestDispatcher("/WEB-INF/resetPassword.jsp").forward(request, response);
            return;
        }
    }

 
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        AccountService as = new AccountService();
        if (request.getParameter("uuid") == null) {
            
            String email = request.getParameter("email");
            String url = request.getRequestURL().toString();
            String path = getServletContext().getRealPath("/WEB-INF");
            as.resetPassword(email, path, url);
        }
        else {
            String password = request.getParameter("password");
            String uuid = request.getParameter("uuid");
            as.changePassword(uuid, password);
        }
        
        response.sendRedirect("login");
    }


}
