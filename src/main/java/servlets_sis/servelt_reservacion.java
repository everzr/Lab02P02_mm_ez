/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package servlets_sis;

import Entity.ControllerReservaciones;
import Entity.Reservaciones;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Date;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
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
@WebServlet(name = "servelt_reservacion", urlPatterns = {"/servelt_reservacion"})
public class servelt_reservacion extends HttpServlet {
    
    ControllerReservaciones creservaciones = new ControllerReservaciones();
    Reservaciones reservacion = new Reservaciones();

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
            out.println("<title>Servlet servelt_reservacion</title>");
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet servelt_reservacion at " + request.getContextPath() + "</h1>");
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

        List<Reservaciones> consultaGeneral = this.creservaciones.traerListaReservaciones();
        if (accion == null) accion = "con";

        switch (accion) {
            case "con":
                request.setAttribute("reservaciones", consultaGeneral);
                request.getRequestDispatcher("Vistas/view_reservaciones.jsp").forward(request, response);
                break;
            case "mod":
                Reservaciones r = this.creservaciones.traerReservacion(id);
                request.setAttribute("reservacion", r);
                request.getRequestDispatcher("Vistas/upd_reservaciones.jsp").forward(request, response);
                break;
            case "del":
                this.creservaciones.eliminarReservacion(id);
                consultaGeneral = this.creservaciones.traerListaReservaciones();
                request.setAttribute("reservaciones", consultaGeneral);
                request.getRequestDispatcher("Vistas/view_reservaciones.jsp").forward(request, response);
                break;
            case "add":
                List<Reservaciones> consultaUltimos = this.creservaciones.consultaUltimasReservaciones(5, 1);
                request.setAttribute("reservaciones", consultaUltimos);
                request.getRequestDispatcher("Vistas/add_reservaciones.jsp").forward(request, response);
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

        String fechaReservacionStr = paramTrim(request, "txtFechaReservacion"); // yyyy-MM-dd

        DateTimeFormatter fFecha = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        Date fechaReservacion = null;
        if (fechaReservacionStr != null && !fechaReservacionStr.isEmpty()) {
            LocalDate ld = LocalDate.parse(fechaReservacionStr, fFecha);
            fechaReservacion = Date.valueOf(ld);
        }

        if (fechaReservacion != null) reservacion.setFechaReservacion(fechaReservacion);

        String btnAgregar = request.getParameter("btnAgregar");
        String btnUpdate = request.getParameter("btnUpdate");

        if (btnAgregar != null && !btnAgregar.isEmpty()) {
            this.creservaciones.crearReservacion(reservacion);
        } else if (btnUpdate != null && !btnUpdate.isEmpty()) {
            int id = 0;
            try { id = Integer.parseInt(request.getParameter("txtid")); } catch (NumberFormatException ignored) {}
            if (id > 0) reservacion.setIDReservacion(id);
            this.creservaciones.editarReservacion(reservacion);
        }

        List<Reservaciones> consultaUltimos = this.creservaciones.consultaUltimasReservaciones(5, 1);
        request.setAttribute("reservaciones", consultaUltimos);
        request.getRequestDispatcher("Vistas/add_reservaciones.jsp").forward(request, response);
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

    private static String paramTrim(HttpServletRequest req, String name) {
        String v = req.getParameter(name);
        return v == null ? null : v.trim();
    }

}
