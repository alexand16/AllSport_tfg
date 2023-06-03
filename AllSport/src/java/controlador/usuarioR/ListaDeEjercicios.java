/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package controlador.usuarioR;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.List;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import modelo.dao.EjerciciosJpaController;
import modelo.dao.Lista_EjerciciosJpaController;
import modelo.entidades.Clientes;
import modelo.entidades.Ejercicios;
import modelo.entidades.Lista_Ejercicios;

/**
 *
 * @author alanr
 */
public class ListaDeEjercicios extends HttpServlet {

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
        String vista = "/usuarioR/ListaDeEjercicios.jsp";
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("AllSportPU");
        EjerciciosJpaController ejc = new EjerciciosJpaController(emf);
        Lista_EjerciciosJpaController lejc = new Lista_EjerciciosJpaController(emf);
        HttpSession session = request.getSession();
        Clientes cliente = (Clientes) session.getAttribute("usuario");
        String grupoMuscular = request.getParameter("grupoMuscular");
        String tipoEntreno = request.getParameter("tipoEntreno");
        //Resultado final, esta lista sera el return para la vista
        List<Lista_Ejercicios> lista = new ArrayList<>();

        if (lejc.findLista_EjerciciosEntitiesByIdCliente(cliente.getId()).size() == 0) {
            if (grupoMuscular != null && tipoEntreno != null) {
                int contadorBase = 0;
                int contadorAuxiliar = 0;
                //todos los ejercicios de la DB
                List<Ejercicios> ejercicios = ejc.findEjerciciosEntities();
                //ejercicios que pertenecen al grupo muscular
                List<Ejercicios> ejerciciosCandidatos = new ArrayList<>();

                //rellenamos la lista con los ejercicios que pertenecen al grupo muscular elegido
                for (int i = 0; i < ejercicios.size(); i++) {
                    if (ejercicios.get(i).getGrupoMuscular().equals(request.getParameter("grupoMuscular"))) {
                        ejerciciosCandidatos.add(ejercicios.get(i));
                    }
                }
                Collections.shuffle(ejerciciosCandidatos);

                for (int i = 0; i < ejerciciosCandidatos.size(); i++) {
                    if (contadorBase == 3 && contadorAuxiliar == 1) {
                        break;
                    }

                    Ejercicios ejercicio = ejerciciosCandidatos.get(i);
                    String tipo = ejercicio.getTipo();

                    if (tipo.equals("Base") && contadorBase < 3) {
                        Lista_Ejercicios ejercicioTratado = new Lista_Ejercicios();
                        ejercicioTratado.setEjercicio(ejercicio);
                        ejercicioTratado.setUsuario(cliente);
                        if (tipoEntreno.equals("Volumen")) {
                            ejercicioTratado.setSeries(3);
                            ejercicioTratado.setRepeticiones(12);
                        } else if (tipoEntreno.equals("FuerzaVolumen")) {
                            ejercicioTratado.setSeries(3);
                            ejercicioTratado.setRepeticiones(10);
                        } else { // Definicion
                            ejercicioTratado.setSeries(4);
                            ejercicioTratado.setRepeticiones(20);
                        }
                        contadorBase++;
                        try {
                            lejc.create(ejercicioTratado);
                        } catch (Exception e) {
                            System.err.println(e.getMessage());
                        }
                    } else if (tipo.equals("Auxiliar") && contadorAuxiliar < 1) {
                        Lista_Ejercicios ejercicioTratado = new Lista_Ejercicios();
                        ejercicioTratado.setEjercicio(ejercicio);
                        ejercicioTratado.setUsuario(cliente);
                        if (tipoEntreno.equals("Volumen")) {
                            ejercicioTratado.setSeries(3);
                            ejercicioTratado.setRepeticiones(12);
                        } else if (tipoEntreno.equals("FuerzaVolumen")) {
                            ejercicioTratado.setSeries(3);
                            ejercicioTratado.setRepeticiones(10);//12-10-8
                        } else { // Definicion
                            ejercicioTratado.setSeries(4);
                            ejercicioTratado.setRepeticiones(20);
                        }
                        contadorAuxiliar++;
                        try {
                            lejc.create(ejercicioTratado);
                        } catch (Exception e) {
                            System.err.println(e.getMessage());
                        }
                    }
                }
                lista = lejc.findLista_EjerciciosEntitiesByIdCliente(cliente.getId());
                Collections.reverse(lista);
                request.setAttribute("lista", lista);
            }
        } else {
            lista = lejc.findLista_EjerciciosEntitiesByIdCliente(cliente.getId());
            Collections.reverse(lista);
            request.setAttribute("lista", lista);
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
