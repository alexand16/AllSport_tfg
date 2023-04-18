/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package controlador.admin;

import java.io.IOException;
import java.io.PrintWriter;
import java.time.LocalDate;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import modelo.dao.ClientesJpaController;
import modelo.dao.CuotasJpaController;
import modelo.entidades.Clientes;
import modelo.entidades.Cuotas;

/**
 *
 * @author alanr
 */
public class EditarCliente extends HttpServlet {

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
        String vista = "/admin/editarCliente.jsp";
        long id = Long.parseLong(request.getParameter("id"));
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("AllSportPU");
        ClientesJpaController djc = new ClientesJpaController(emf);
        Clientes cliente = djc.findClientes(id);           
        CuotasJpaController cjc = new CuotasJpaController(emf);
        if (request.getParameter("nombre") != null) {
            // Editando
            String nombre = request.getParameter("nombre");
            String apellidos = request.getParameter("apellidos");
            String estadoMembresia = request.getParameter("estadoMembresia");
            LocalDate fechaPago = LocalDate.parse(request.getParameter("fechaPago"));
            long cuota = Long.parseLong(request.getParameter("tipoCuota"));
            LocalDate fechaNacimiento = LocalDate.parse(request.getParameter("fechaNacimiento"));
            int puntos = Integer.parseInt(request.getParameter("puntos"));
            String observaciones = request.getParameter("observaciones");
            
            cliente.setNombre(nombre);
            cliente.setApellidos(apellidos);
            cliente.setEstadoMembresia(estadoMembresia);
            cliente.setFechaPago(fechaPago);
            cliente.setCuota(cjc.findCuotas(cuota));
            cliente.setFechaNacimiento(fechaNacimiento);
            cliente.setPuntos(puntos);
            cliente.setObservaciones(observaciones);
            
            try {
                djc.edit(cliente);
                response.sendRedirect("MenuClientes");
                return;
            } catch (Exception e) {
                request.setAttribute("error", "Error al actualizar usuario");
                request.setAttribute("cliente", cliente);
            }
        } else {
            request.setAttribute("cliente", cliente);
        }
        request.setAttribute("cuotas", cjc.findCuotasEntities());
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
