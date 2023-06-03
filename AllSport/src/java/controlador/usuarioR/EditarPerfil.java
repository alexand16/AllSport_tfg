/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package controlador.usuarioR;

import java.io.File;
import java.io.FileOutputStream;
import java.nio.file.StandardCopyOption;
import java.time.LocalDate;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.criteria.Path;

import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;

import javax.servlet.http.HttpSession;

import modelo.dao.ClientesJpaController;

import modelo.entidades.Clientes;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.nio.file.Files;
import java.nio.file.Paths;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Part;

/**
 *
 * @author alanr
 */
@WebServlet("/guardarPerfil")
@MultipartConfig(fileSizeThreshold = 1024 * 1024 * 2, // 2MB
        maxFileSize = 1024 * 1024 * 10, // 10MB
        maxRequestSize = 1024 * 1024 * 50) // 50MB
public class EditarPerfil extends HttpServlet {

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
        String vista = "/usuarioR/editarPerfil.jsp";
        HttpSession session = request.getSession();
        Object usuarioObj = session.getAttribute("usuario");
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("AllSportPU");
        ClientesJpaController djc = new ClientesJpaController(emf);
        Clientes cliente = (Clientes) usuarioObj;
        long id = cliente.getId();
        cliente = djc.findClientes(id);

        if (request.getParameter("nombre") != null) {
            // Editando
            String nombre = request.getParameter("nombre");
            String apellidos = request.getParameter("apellidos");
            String email = request.getParameter("email");
            LocalDate fechaNacimiento = LocalDate.parse(request.getParameter("fechaNacimiento"));
            String telefono = request.getParameter("telefono");
            Part imagenPart = request.getPart("imagenCliente");
            if (imagenPart != null && imagenPart.getSize() > 0) {
                // El usuario ha subido una nueva imagen
                String nombreArchivo = cliente.getId() + "_" + imagenPart.getSubmittedFileName();

                // Obtener la ruta absoluta del directorio donde se guardan las imágenes
                String directorioDestino = request.getServletContext().getRealPath("assets/imgClientes");

                // Crear el objeto File para la ruta completa del archivo
                File rutaCompleta = new File(directorioDestino, nombreArchivo);

                // Guardar la imagen con el nombre único en la ubicación deseada
                try (InputStream inputStream = imagenPart.getInputStream();
                        OutputStream outputStream = new FileOutputStream(rutaCompleta)) {

                    byte[] buffer = new byte[4096];
                    int bytesRead;
                    while ((bytesRead = inputStream.read(buffer)) != -1) {
                        outputStream.write(buffer, 0, bytesRead);
                    }
                } catch (IOException e) {
                    e.printStackTrace();
                }
                cliente.setRutaImg(nombreArchivo);
            }

            cliente.setNombre(nombre);
            cliente.setApellidos(apellidos);
            cliente.setEmail(email);
            cliente.setTelefono(telefono);
            cliente.setFechaNacimiento(fechaNacimiento);

            try {
                djc.edit(cliente);
                request.setAttribute("cliente", cliente);
                response.sendRedirect("RecargarCliente");
                return;
            } catch (Exception e) {
                request.setAttribute("error", e.getMessage());
                request.setAttribute("cliente", cliente);
            }
        } else {
            request.setAttribute("cliente", cliente);
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
