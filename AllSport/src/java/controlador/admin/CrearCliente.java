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
import javax.persistence.RollbackException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import modelo.dao.ClientesJpaController;
import modelo.dao.CuotasJpaController;
import modelo.entidades.Clientes;

/**
 *
 * @author alanr
 */
public class CrearCliente extends HttpServlet {

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
        String vista = "/admin/crearCliente.jsp";
        String error = "";

        String nombre = "";
        String apellidos = "";
        String tipoUsuario = "";
        long tipoCuota = 0;
        String telefono = "";
        String email = "";
        String rutaImg = "";
        String observaciones = "";
        LocalDate fechaNacimiento;
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("AllSportPU");
        CuotasJpaController cjc = new CuotasJpaController(emf);
        if (request.getParameter("nombre") != null) {
            nombre = request.getParameter("nombre");
            apellidos = request.getParameter("apellidos");
            tipoUsuario = request.getParameter("tipoUsuario");
            tipoCuota = Long.parseLong(request.getParameter("tipoCuota"));
            telefono = request.getParameter("telefono");
            email = request.getParameter("email");
            //rutaImg = request.getParameter("rutaImg");
            observaciones = request.getParameter("observaciones");
            fechaNacimiento = LocalDate.parse(request.getParameter("fechaNacimiento"));
            nombre = nombre.trim();
            if (nombre.isEmpty() || apellidos.isEmpty() || telefono.isEmpty() || fechaNacimiento == null) {
                error = "los campos del usuario no puede estar vac�o";
            } else {
                Clientes c = new Clientes();
                c.setNombre(nombre);
                c.setApellidos(apellidos);
                c.setTipoUsuario(tipoUsuario);
                c.setTelefono(telefono);
                c.setEmail(email);
                //c.setRutaImg(rutaImg);
                c.setObservaciones(observaciones);
                c.setFechaNacimiento(fechaNacimiento);
                c.setCuota(cjc.findCuotas(tipoCuota));
                c.setFechaAlta(LocalDate.now());
                c.setFechaPago(LocalDate.now());
                c.setEstadoMembresia("Activo");
                ClientesJpaController djc = new ClientesJpaController(emf);
                try {
                    djc.create(c);
                    response.sendRedirect("MenuClientes");
                    return;
                } catch (RollbackException e) {
                    error = "El usuario \"" + nombre + "\" ya existe. \n O este correo \"" + email + "\" ya esta en uso";
                }
            }
        }
        if (!error.isEmpty()) {
            request.setAttribute("error", error);
            request.setAttribute("nombre", nombre);
            request.setAttribute("password", apellidos);
            request.setAttribute("tipo", tipoUsuario);
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
