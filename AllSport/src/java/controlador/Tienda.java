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
import modelo.dao.ClientesJpaController;
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
public class Tienda extends HttpServlet {

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
        String vista = "/tienda.jsp";
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("AllSportPU");
        ProductosJpaController pjc = new ProductosJpaController(emf);
        Productos_PedidosJpaController ppjc = new Productos_PedidosJpaController(emf);
        PedidosJpaController pejc = new PedidosJpaController(emf);
        HttpSession session = request.getSession();
        request.setAttribute("productos", pjc.findProductosEntities());
        if (request.getParameter("error") != null) {
            request.setAttribute("error", request.getParameter("error"));
        }

        if (request.getParameter("id") != null) {
            Pedidos ultimoPedido = new Pedidos();
            Clientes cliente = (Clientes) session.getAttribute("usuario");
            long idCliente = cliente.getId();
            List<Pedidos> pedidosCliente = pejc.findPedidosByIDCliente(idCliente);
            //comprueba si el pedido esta hecho para crearlo otro o no
            if (pedidosCliente.size() > 0) {
                ultimoPedido = pedidosCliente.get(pedidosCliente.size() - 1);
            } else {
                //si el cliente no tiene pedidos
                Pedidos pedido = new Pedidos();
                pedido.setCliente((Clientes) session.getAttribute("usuario"));
                pejc.create(pedido);//cuando compre edita y añade los datos necesarios
            }
            //recogemos el producto seleccionado
            Productos producto = pjc.findProductos(Long.parseLong(request.getParameter("id")));
            //lo añadimos a la lista del usuario (Producto_Pedido)
            ArrayList<Productos> cesta = new ArrayList<>();
            cesta.add(producto);
            Productos_Pedidos productosP = new Productos_Pedidos();
            for (int i = 0; i < cesta.size(); i++) {
                if (cesta.get(i) == producto) {//esto esta raro!!!
                    productosP.setProducto(producto);
                    productosP.setPedido(ultimoPedido);
                    //productos.setCantidad(); la cantidad se obtiene de la cesta editando este Producto_pedido
                    ppjc.create(productosP);
                }
            }
            //avisamos al usuario de que se ha añadido a la cesta

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
