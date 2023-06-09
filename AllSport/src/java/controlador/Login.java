/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package controlador;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import modelo.dao.ClientesJpaController;
import modelo.entidades.Clientes;
import static modelo.entidades.Clientes.getMD5;

/**
 *
 * @author alanr
 */
public class Login extends HttpServlet {

    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String vista = "/login.jsp";
        String error = "";

        //Si recibimos datos
        if (request.getParameter("usuario") != null && request.getParameter("contrasena") != null) {
            String usuario = request.getParameter("usuario");
            String contraseņa = request.getParameter("contrasena");
            contraseņa = getMD5(contraseņa);
            EntityManagerFactory emf = Persistence.createEntityManagerFactory("AllSportPU");
            ClientesJpaController cjc = new ClientesJpaController(emf);
            List<Clientes> Cliente = cjc.findClientesEntities();
            for (Clientes c : Cliente) {
                if (!(c.getUsuario() == null || c.getContrasena() == null)) {
                    if (c.getUsuario().equals(usuario) && c.getContrasena().equals(contraseņa)) {
                        request.getSession().setAttribute("usuario", c);
                        response.sendRedirect("usuarioR/RecargarCliente");
                        return;
                    }
                }
            }
            error = "Usuario o contraseņa incorrectos.";
            request.setAttribute("error", error);

        }

        getServletContext().getRequestDispatcher(vista).forward(request, response);
    }

    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    /**
     * Handles the HTTP <code>GET</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Handles the HTTP <code>POST</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Returns a short description of the servlet.
     *
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
