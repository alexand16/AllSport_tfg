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
import javax.servlet.http.HttpSession;
import modelo.dao.ClientesJpaController;
import modelo.dao.PedidosJpaController;
import modelo.dao.ProductosJpaController;
import modelo.dao.Productos_PedidosJpaController;
import modelo.entidades.Clientes;
import modelo.entidades.Pedidos;
import modelo.entidades.Productos;
import modelo.entidades.Productos_Pedidos;
import java.text.DecimalFormat;

/**
 *
 * @author alanr
 */
public class Cesta extends HttpServlet {

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
        String vista = "/usuarioR/cesta.jsp";
        HttpSession session = request.getSession();
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("AllSportPU");
        double totalAPagar = 0.0; // Variable para almacenar el total a pagar
        PedidosJpaController pejc = new PedidosJpaController(emf);
        Productos_PedidosJpaController ppjc = new Productos_PedidosJpaController(emf);
        ProductosJpaController pjc = new ProductosJpaController(emf);
        ArrayList<Productos> cesta = new ArrayList<>();
        Pedidos ultimoPedido = new Pedidos();
        Clientes cliente = (Clientes) session.getAttribute("usuario");
        long idCliente = cliente.getId();
        List<Pedidos> pedidosCliente = pejc.findPedidosByIDCliente(idCliente);
        if (pedidosCliente.size() > 0) {
            //obtiene el ultimo pedido del cliente
            ultimoPedido = pedidosCliente.get(pedidosCliente.size() - 1);
            List<Productos_Pedidos> productosPedidos = ppjc.findProductosPedidosByIDPedido(ultimoPedido.getId());
         
            for (Productos_Pedidos pp : productosPedidos) {
                double subtotal = pp.getProducto().getPrecio() * pp.getCantidad(); // Cálculo del subtotal por cada producto
                totalAPagar += subtotal; // Acumula el subtotal al total a pagar
            }
           
            DecimalFormat decimalFormat = new DecimalFormat("#.00");
            String totalFormateado = decimalFormat.format(totalAPagar);
            totalFormateado = totalFormateado.replace(",", ".");
            session.setAttribute("totalAPagar", totalFormateado);
            for (int i = 0; i < productosPedidos.size(); i++) {
                cesta.add(productosPedidos.get(i).getProducto());
            }
            
            session.setAttribute("idPedido", ultimoPedido.getId());
            session.setAttribute("productosPedidos", productosPedidos);
            //pejc.generarFactura(ultimoPedido.getId(), productosPedidos, cliente);
            
            request.setAttribute("productosPedidos", productosPedidos);
        }
        if (request.getParameter("error") != null) {
            request.setAttribute("error", request.getParameter("error"));
        }
        if (request.getParameter("productId") != null) {
            Productos_Pedidos pp = new Productos_Pedidos();
            pp = ppjc.findProductosPedidosByIDProducto(Long.parseLong(request.getParameter("productId")), ultimoPedido.getId());
            pp.setCantidad(Integer.parseInt(request.getParameter("quantity")));
            try {
                ppjc.edit(pp);
                response.sendRedirect("Cesta");
                return;
            } catch (Exception e) {
                request.setAttribute("error", "Error al actualizar la cantidad");
            }

        }
        
        request.setAttribute("cesta", cesta);

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
