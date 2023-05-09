/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package controlador.usuarioR;

import java.io.IOException;
import java.io.PrintWriter;
import java.time.LocalDate;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.RollbackException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import modelo.dao.ClientesJpaController;
import modelo.dao.PostsJpaController;
import modelo.dao.RespuestasJpaController;
import modelo.entidades.Clientes;
import modelo.entidades.Posts;
import modelo.entidades.Respuestas;

/**
 *
 * @author alanr
 */
public class CrearRespuesta extends HttpServlet {

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
        String vista = "/usuarioR/crearRespuesta.jsp";
        String error = "";

        EntityManagerFactory emf = Persistence.createEntityManagerFactory("AllSportPU");
        ClientesJpaController cjc = new ClientesJpaController(emf);
        PostsJpaController pjc = new PostsJpaController(emf);

// Obtener el valor de post del atributo de sesión, si está disponible
        Long post = (Long) request.getSession().getAttribute("postId");

// Si no hay ningún valor de post en la sesión, obtenerlo del parámetro de solicitud
        if (post == null) {
            post = Long.parseLong(request.getParameter("id"));
        }

        String contenido = request.getParameter("contenido");
        LocalDate fechaCreacion = LocalDate.now();

        if (contenido != null) {
            if (request.getSession().getAttribute("usuario") != null) {
                Clientes aux = (Clientes) request.getSession().getAttribute("usuario");
                long idUsuario = aux.getId();
                Clientes usuario = cjc.findClientes(idUsuario);
                if (contenido.equals("")) {
                    error = "los campos no pueden estar vacíos";
                } else {
                    Respuestas r = new Respuestas();
                    r.setContenido(contenido);
                    r.setUsuario(usuario);
                    r.setFechaCreacion(fechaCreacion);
                    r.setPost(pjc.findPosts(post));
                    RespuestasJpaController rjc = new RespuestasJpaController(emf);
                    try {
                        rjc.create(r);
                        response.sendRedirect("Blog");
                        return;
                    } catch (RollbackException e) {
                        error = "Se ha producido un error al crear su respuesta";
                    }
                }
            } else {
                error = "debes iniciar sesión";
            }
        }

        if (!error.isEmpty()) {
            request.setAttribute("error", error);
            request.setAttribute("contenido", contenido);
        }

// Guardar el valor de post en el atributo de sesión para su uso futuro
        request.getSession().setAttribute("postId", post);

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
