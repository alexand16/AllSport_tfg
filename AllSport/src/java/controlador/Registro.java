/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package controlador;

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
import static modelo.entidades.Clientes.getMD5;
import modelo.entidades.Cuotas;

/**
 *
 * @author alanr
 */
public class Registro extends HttpServlet {

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
        String vista = "/registro.jsp";
        String error = "";
        String Usuario = "";
        String Contraseña = "";
        String nombre = "";
        String apellidos = "";
        String telefono = "";
        String email = "";
        long cuota = 100;
        LocalDate fechaNacimiento = null;
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("AllSportPU");
        if (request.getParameter("nombre") != null) {
            Usuario = request.getParameter("usuario");
            Contraseña = getMD5(request.getParameter("pwd"));
            nombre = request.getParameter("nombre");
            apellidos = request.getParameter("apellidos");
            telefono = request.getParameter("telefono");
            email = request.getParameter("email");

            fechaNacimiento = LocalDate.parse(request.getParameter("fechaNacimiento"));
            CuotasJpaController cjc = new CuotasJpaController(emf);
            if (nombre.isEmpty() || apellidos.isEmpty() || telefono.isEmpty() || fechaNacimiento == null) {
                error = "los campos del usuario no puede estar vacío";
            } else {
                Clientes c = new Clientes();
                c.setUsuario(Usuario);
                c.setContrasena(Contraseña);
                c.setNombre(nombre);
                c.setApellidos(apellidos);
                c.setTelefono(telefono);
                c.setEmail(email);
                c.setFechaNacimiento(fechaNacimiento);
                c.setTipoUsuario("Normal");
                c.setObservaciones("");
                c.setRutaImg("");
                c.setFechaAlta(LocalDate.now());
                c.setCuota(cjc.findCuotas(cuota));
                c.setFechaPago(LocalDate.now());
                c.setEstadoMembresia("Inactivo");
                ClientesJpaController djc = new ClientesJpaController(emf);
                try {
                    djc.create(c);
                    response.sendRedirect("index.jsp");
                    return;
                } catch (RollbackException e) {
                    error = "El usuario \"" + Usuario + "\" ya existe.";
                    //error += e.getMessage();
                }
            }
        }
        if (!error.isEmpty()) {
            request.setAttribute("error", error);
            request.setAttribute("nombre", nombre);
            request.setAttribute("usuario", Usuario);
            request.setAttribute("apellidos", apellidos);
            request.setAttribute("telefono", telefono);
            request.setAttribute("email", email);
            request.setAttribute("fechaNacimiento", fechaNacimiento);
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
