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
                if (pedidosCliente.get(pedidosCliente.size() - 1).getEstadoPedido() == null) {
                    ultimoPedido = pedidosCliente.get(pedidosCliente.size() - 1);
                } else {
                    ultimoPedido.setCliente((Clientes) session.getAttribute("usuario"));
                    pejc.create(ultimoPedido);//cuando compre edita y añade los datos necesarios
                }
            } else {
                //si el cliente no tiene pedidos
                ultimoPedido.setCliente((Clientes) session.getAttribute("usuario"));
                pejc.create(ultimoPedido);//cuando compre edita y añade los datos necesarios
            }
            // Recogemos el producto seleccionado
            Productos producto = pjc.findProductos(Long.parseLong(request.getParameter("id")));

            // Obtenemos la lista de la cesta del usuario (Producto_Pedido)
            ArrayList<Productos> cesta;
            if (ppjc.findProductosPedidosByIDPedido(ultimoPedido.getId()).size() > 0) {
                ArrayList<Productos> cestaAux = new ArrayList<Productos>();
                for (int i = 0; i < ppjc.findProductosPedidosByIDPedido(ultimoPedido.getId()).size(); i++) {
                    cestaAux.add(ppjc.findProductosPedidosByIDPedido(ultimoPedido.getId()).get(i).getProducto());
                }
                cesta = cestaAux;
            } else {
                if (session.getAttribute("cesta") != null) {
                    cesta = (ArrayList<Productos>) session.getAttribute("cesta");
                } else {
                    cesta = new ArrayList<Productos>();
                }
            }
            if (producto.getCantidadStock() != 0) {
                // Verificamos si el producto ya está en la cesta
                boolean productoExistente = false;
                for (Productos p : cesta) {
                    if (p.equals(producto)) {
                        productoExistente = true;
                        break;
                    }
                }

                if (!productoExistente) {
                    Productos_Pedidos productosP = new Productos_Pedidos();
                    productosP.setProducto(producto);
                    productosP.setPedido(ultimoPedido);
                    productosP.setCantidad(1);//por defecto 1 si se quiere cambiar se debe editar en la cesta
                    ppjc.create(productosP);

                    // Añadimos el producto a la cesta
                    cesta.add(producto);
                    // Avisamos al usuario de que se ha añadido a la cesta
                    // Guardamos un mensaje en la variable de sesión
                    session.setAttribute("mensaje", "Producto añadido a la cesta.");
                } else {
                    // Si el producto ya está en la cesta, mostramos un mensaje de error
                    session.setAttribute("mensaje", "El producto ya está en la cesta.");
                }
            } else {
                // Si el producto no tiene stock
                session.setAttribute("mensaje", "No queda en nuestros almacenes de este producto.");
            }
            session.setAttribute("cesta", cesta);

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
