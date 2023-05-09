/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package controlador.admin;

import java.io.IOException;
import java.io.PrintWriter;
import java.time.LocalDate;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import modelo.dao.ClientesJpaController;
import modelo.dao.PostsJpaController;
import modelo.entidades.Clientes;
import modelo.entidades.Posts;

/**
 *
 * @author alanr
 */
public class CrearPost extends HttpServlet {

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
        String vista = "/admin/crearPost.jsp";
        String error = "";

        String titulo = "";
        String contenido = "";
        LocalDate fechaCreacion = LocalDate.now();

        EntityManagerFactory emf = Persistence.createEntityManagerFactory("AllSportPU");
        ClientesJpaController cjc = new ClientesJpaController(emf);
        if (request.getParameter("contenido") != null) {
            if (request.getSession().getAttribute("usuario") != null) {
                Clientes aux = (Clientes) request.getSession().getAttribute("usuario");
                long idUsuario = aux.getId();
                Clientes administrador = cjc.findClientes(idUsuario);
                titulo = request.getParameter("titulo");
                contenido = request.getParameter("contenido");
                if (titulo.equals("") || contenido.equals("")) {
                    error = "los campos no pueden estar vacíos";
                } else {
                    Posts p = new Posts();
                    p.setTitulo(titulo);
                    p.setContenido(contenido);
                    p.setAdministrador(administrador);
                    p.setFechaCreacion(fechaCreacion);
                    PostsJpaController pjc = new PostsJpaController(emf);
                    try {
                        pjc.create(p);
                        response.sendRedirect("Blog");
                        return;
                    } catch (Exception e) {
                        request.setAttribute("error", "Error al crear el Post");
                    }
                }
            }
        }
        if (!error.isEmpty()) {
            request.setAttribute("error", error);
            request.setAttribute("titulo", titulo);
            request.setAttribute("contenido", contenido);

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
