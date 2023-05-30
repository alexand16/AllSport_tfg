/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package controlador.admin;

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
import modelo.dao.PedidosJpaController;
import modelo.entidades.Pedidos;

/**
 *
 * @author alanr
 */
public class GestionarPedidos extends HttpServlet {

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
        String vista = "/admin/gestionarPedidos.jsp";
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("AllSportPU");
        PedidosJpaController pejc = new PedidosJpaController(emf);
        List<Pedidos> pedidos = pejc.findPedidosEntities();
        List<Pedidos> pedidosCompletos = new ArrayList<>();
        for (int i = 0; i < pedidos.size(); i++) {
            if (pedidos.get(i).getEstadoPedido() != null) {
                if (pedidos.get(i).getEstadoPedido().equals("en proceso") || pedidos.get(i).getEstadoPedido().equals("en envio")) {
                    pedidosCompletos.add(pedidos.get(i));
                }
            }
        }
        if (request.getParameter("pedidoId") != null) {
            Pedidos pedido = new Pedidos();
            pedido = pejc.findPedidos(Long.parseLong(request.getParameter("pedidoId")));
            pedido.setEstadoPedido(request.getParameter("estado"));
            try {
                pejc.edit(pedido);
                response.sendRedirect("GestionarPedidos");
                return;
            } catch (Exception e) {
                request.setAttribute("error", "Error al actualizar la cantidad");
            }

        }
        request.setAttribute("pedidosCompletos", pedidosCompletos);
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
