/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package servlets_sis;

import Entity.ControllerPasajeros;
import Entity.Pasajeros;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author EverZr
 */
@WebServlet(name = "servlet_pasajeros", urlPatterns = {"/servlet_pasajeros"})
public class servlet_pasajeros extends HttpServlet {
    
    ControllerPasajeros cpasajeros = new ControllerPasajeros();
    Pasajeros pasajero = new Pasajeros();

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
        response.setContentType("text/html;charset=UTF-8");
        try (PrintWriter out = response.getWriter()) {
            /* TODO output your page here. You may use following sample code. */
            out.println("<!DOCTYPE html>");
            out.println("<html>");
            out.println("<head>");
            out.println("<title>Servlet servlet_pasajeros</title>");
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet servlet_pasajeros at " + request.getContextPath() + "</h1>");
            out.println("</body>");
            out.println("</html>");
        }
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
        String accion = request.getParameter("accion");
        int id = 0;
        if (request.getParameter("id") != null) {
            try { id = Integer.parseInt(request.getParameter("id")); } catch (NumberFormatException ignored) {}
        }

        List<Pasajeros> consultaGeneral = this.cpasajeros.traerListaPasajeros();
        if (accion == null) accion = "con";

        switch (accion) {
            case "con":
                request.setAttribute("pasajeros", consultaGeneral);
                request.getRequestDispatcher("Vistas/view_pasajeros.jsp").forward(request, response);
                break;
            case "mod":
                Pasajeros p = this.cpasajeros.traerPasajero(id);
                request.setAttribute("pasajero", p);
                request.getRequestDispatcher("Vistas/upd_pasajeros.jsp").forward(request, response);
                break;
            case "del":
                this.cpasajeros.eliminarPasajero(id);
                consultaGeneral = this.cpasajeros.traerListaPasajeros();
                request.setAttribute("pasajeros", consultaGeneral);
                request.getRequestDispatcher("Vistas/view_pasajeros.jsp").forward(request, response);
                break;
            case "add":
                List<Pasajeros> consultaUltimos = this.cpasajeros.consultaUltimosPasajeros(5, 1);
                request.setAttribute("pasajeros", consultaUltimos);
                request.getRequestDispatcher("Vistas/add_pasajeros.jsp").forward(request, response);
                break;
        }
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
        request.setCharacterEncoding("UTF-8");

        String nombre = request.getParameter("txtNombrePasajero");
        String nacionalidad = request.getParameter("txtNacionalidad");
        String pasaporte = request.getParameter("txtPasaporte");

        // Poblar entidad
        pasajero.setNombrePasajero(nullIfEmpty(nombre));
        pasajero.setNacionalidad(nullIfEmpty(nacionalidad));
        pasajero.setPasaporte(nullIfEmpty(pasaporte));

        String btnAgregar = request.getParameter("btnAgregar");
        String btnUpdate = request.getParameter("btnUpdate");

        if (btnAgregar != null && !btnAgregar.isEmpty()) {
            this.cpasajeros.crearPasajero(pasajero);
        } else if (btnUpdate != null && !btnUpdate.isEmpty()) {
            int id = 0;
            try { id = Integer.parseInt(request.getParameter("txtid")); } catch (NumberFormatException ignored) {}
            if (id > 0) pasajero.setIDPasajero(id);
            this.cpasajeros.editarPasajero(pasajero);
        }

        List<Pasajeros> consultaUltimos = this.cpasajeros.consultaUltimosPasajeros(5, 1);
        request.setAttribute("pasajeros", consultaUltimos);
        request.getRequestDispatcher("Vistas/add_pasajeros.jsp").forward(request, response);
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

    private static String nullIfEmpty(String v) {
        return (v == null || v.trim().isEmpty()) ? null : v.trim();
    }

}
