/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package controlador.admin;

import java.io.IOException;
import java.io.PrintWriter;
import java.time.LocalDate;
import java.time.YearMonth;
import java.util.Calendar;
import java.util.List;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import modelo.dao.ClientesJpaController;
import modelo.dao.PedidosJpaController;
import modelo.entidades.Clientes;
import modelo.entidades.Pedidos;

/**
 *
 * @author alanr
 */
public class Ganancias extends HttpServlet {

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
        String vista = "/admin/ganancias.jsp";
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("AllSportPU");
        PedidosJpaController pejc = new PedidosJpaController(emf);
        ClientesJpaController cjc = new ClientesJpaController(emf);
        double total = 0;
        double totalSubscripciones = 0;
        double totalPedidos = 0;
        List<Pedidos> pedido = pejc.findPedidosEntities();
        List<Clientes> cliente = cjc.findClientesEntities();

        YearMonth yearMonth = YearMonth.now();
        int mesActual = yearMonth.getMonthValue();
        // Recorremos los pedidos de este mes
        for (int i = 0; i < pedido.size(); i++) {
            if (pedido.get(i).getFechaPedido() != null) {

                LocalDate fechaPedido = pedido.get(i).getFechaPedido();
                int mesPedido = fechaPedido.getMonthValue();

                if (mesPedido == mesActual) { // La fecha del pedido está dentro del mes actual
                    totalPedidos += pedido.get(i).getTotal();
                }
            }

        }
        // Recorremos los clientes con la cuota activa (no hace falta comparar 
        // con el mes porque las subscripciones son mensuales)
        for (int i = 0; i < cliente.size(); i++) {
            if (cliente.get(i).getCuota().getId() != 100) {
                totalSubscripciones += cliente.get(i).getCuota().getPrecio();
            }
        }
        totalSubscripciones = Math.round(totalSubscripciones * 100.0) / 100.0;
        totalPedidos = Math.round(totalPedidos * 100.0) / 100.0;

        total = totalPedidos + totalSubscripciones;
        request.setAttribute("totalPedidos", totalPedidos);
        request.setAttribute("totalSubscripciones", totalSubscripciones);
        request.setAttribute("total", total);
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
