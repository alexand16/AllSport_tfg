/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package controlador.usuarioR;

import java.io.IOException;
import java.io.PrintWriter;
import java.time.LocalDate;
import java.util.List;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
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
public class GraciasPorSuCompra extends HttpServlet {

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
        String vista = "/usuarioR/graciasPorSuCompra.jsp";
        String error = "";
        String nombreFactura = "";
        String ruta = getServletContext().getRealPath("assets/facturas");
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("AllSportPU");
        PedidosJpaController pejc = new PedidosJpaController(emf);
        ProductosJpaController pjc = new ProductosJpaController(emf);
        Productos_PedidosJpaController ppjc = new Productos_PedidosJpaController(emf);
        HttpSession session = request.getSession();
        List<Productos_Pedidos> productosPedidos = (List<Productos_Pedidos>) session.getAttribute("productosPedidos");
        long idPedido = Long.parseLong(session.getAttribute("idPedido").toString());
        Clientes cliente = (Clientes) session.getAttribute("usuario");
        //modificar stock
        Productos_Pedidos productoPedido = new Productos_Pedidos();
        Productos productoAEditar = new Productos();
        for (int i = 0; i < productosPedidos.size(); i++) {
            productoPedido = productosPedidos.get(i);
            productoAEditar = productoPedido.getProducto();
            productoAEditar.setCantidadStock(productoAEditar.getCantidadStock() - productoPedido.getCantidad());
            try {
                pjc.edit(productoAEditar);
            } catch (Exception e) {
                request.setAttribute("error", "Error al actualizar el stock");
            }

        }
        if (request.getParameter("error") != null) {
            request.setAttribute("error", request.getParameter("error"));
        }
        //crear factura y guardar nombre de la misma
        nombreFactura = pejc.generarFactura(idPedido, productosPedidos, cliente, ruta);
        request.setAttribute("nombreFactura", nombreFactura);
        request.setAttribute("ruta", ruta);

        Pedidos pedido = new Pedidos();
        pedido = pejc.findPedidos(idPedido);
        pedido.setFechaPedido(LocalDate.now());
        pedido.setEstadoPedido("en proceso");
        pedido.setRutaFactura(ruta + "\\" + nombreFactura);
        pedido.setTotal(Double.parseDouble((String) session.getAttribute("totalAPagar")));
        try {
            pejc.edit(pedido);
        } catch (Exception e) {
            request.setAttribute("error", "Error al actualizar el pedido");
        }

        //destruir cesta
        Productos_Pedidos productoPedidoAdestruir = new Productos_Pedidos();
        for (int i = 0; i < productosPedidos.size(); i++) {
            productoPedidoAdestruir = productosPedidos.get(i);
            try {
                ppjc.destroy(productoPedidoAdestruir.getId());
            } catch (Exception e) {
                //request.setAttribute("error", "Error al borrar la cesta");
            }

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
