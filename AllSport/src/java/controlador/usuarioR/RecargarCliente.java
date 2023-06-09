/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package controlador.usuarioR;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import modelo.dao.ClientesJpaController;
import modelo.dao.ValoracionesJpaController;
import modelo.entidades.Clientes;
import modelo.entidades.Valoraciones;

/**
 *
 * @author alanr
 */
public class RecargarCliente extends HttpServlet {

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
        Clientes cliente = (Clientes) request.getSession().getAttribute("usuario");
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("AllSportPU");
        ClientesJpaController cjc = new ClientesJpaController(emf);
        cliente = cjc.findClientes(cliente.getId());
        request.getSession().setAttribute("usuario", cliente);
        boolean TieneValoracion = true;
        ValoracionesJpaController vjc = new ValoracionesJpaController(emf);
        List<Valoraciones> valoraciones = vjc.findValoracionesEntities();
        for (int i = 0; i < valoraciones.size(); i++) {
            if (cliente.getId() == valoraciones.get(i).getCliente().getId()) {
                TieneValoracion = false;
            }

        }
        request.getSession().setAttribute("TieneValoracion", TieneValoracion);
        response.sendRedirect("../index.jsp");
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
