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
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import modelo.dao.ValoracionesJpaController;
import modelo.entidades.Clientes;
import modelo.entidades.Valoraciones;

/**
 *
 * @author alanr
 */
public class Valorar extends HttpServlet {

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
        String vista = "/usuarioR/valorar.jsp";
        String error = "";
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("AllSportPU");
        ValoracionesJpaController vjc = new ValoracionesJpaController(emf);
        int puntuacion = 0;
        HttpSession session = request.getSession();
        Clientes cliente = (Clientes) session.getAttribute("usuario");
        if (request.getParameter("comentario") != null) {
            if (request.getParameter("rating") == null) {
                puntuacion = Integer.parseInt("5");
            } else {
                puntuacion = Integer.parseInt(request.getParameter("rating"));
            }
            String comentario = request.getParameter("comentario");
            Valoraciones valoracion = new Valoraciones();
            valoracion.setPuntuacion(puntuacion);
            valoracion.setComentario(comentario);
            valoracion.setFechaValoracion(LocalDate.now());
            valoracion.setCliente(cliente);
            try {
                vjc.create(valoracion);
                response.sendRedirect("RecargarCliente");
                return;
            } catch (Exception e) {
                error = "error al procesar su valoración";
            }

        }
        if (!error.isEmpty()) {
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
