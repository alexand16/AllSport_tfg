/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package controlador;

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
import javax.servlet.http.HttpSession;
import modelo.dao.ActividadesPorCuotaJpaController;
import modelo.dao.CuotasJpaController;
import modelo.dao.PedidosJpaController;
import modelo.dao.ProductosJpaController;
import modelo.dao.Productos_PedidosJpaController;
import modelo.entidades.Clientes;
import modelo.entidades.Pedidos;
import modelo.entidades.Productos;
import modelo.entidades.Productos_Pedidos;

/**
 *
 * @author alanr
 */
public class Subscripciones extends HttpServlet {

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
        String vista = "/subscripciones.jsp";
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("AllSportPU");
        ActividadesPorCuotaJpaController apcjc = new ActividadesPorCuotaJpaController(emf);

        HttpSession session = request.getSession();
        Clientes cliente = (Clientes) session.getAttribute("usuario");
        request.setAttribute("cuotas", apcjc.findActividadesPorCuotaEntities());
        request.setAttribute("cliente", cliente);
        if (request.getParameter("error") != null) {
            request.setAttribute("error", request.getParameter("error"));
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
