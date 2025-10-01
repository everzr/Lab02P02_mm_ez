/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package servlets_sis;

import Entity.ControllerDetalleReservacion;
import Entity.ControllerReservaciones;
import Entity.DetalleReservacion;
import java.util.List;
import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author EverZr
 */
@WebServlet(name = "servelet_detalle_reservacion", urlPatterns = {"/servelet_detalle_reservacion"})
public class servelet_detalle_reservacion extends HttpServlet {
    
    ControllerDetalleReservacion cdetalle_reservaciones = new ControllerDetalleReservacion();
    DetalleReservacion detallereservacion = new DetalleReservacion();

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
            out.println("<title>Servlet servelet_detalle_reservacion</title>");
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet servelet_detalle_reservacion at " + request.getContextPath() + "</h1>");
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

        List<DetalleReservacion> consultaGeneral = this.cdetalle_reservaciones.traerListaDetalleReservaciones();
        if (accion == null) accion = "con";

        switch (accion) {
            case "con":
                request.setAttribute("detalles", consultaGeneral);
                request.getRequestDispatcher("Vistas/view_detalle_reservacion.jsp").forward(request, response);
                break;
            case "mod":
                DetalleReservacion d = this.cdetalle_reservaciones.traerDetalleReservacion(id);
                request.setAttribute("detalle", d);
                request.getRequestDispatcher("Vistas/upd_detalle_reservacion.jsp").forward(request, response);
                break;
            case "del":
                this.cdetalle_reservaciones.eliminarDetalleReservacion(id);
                consultaGeneral = this.cdetalle_reservaciones.traerListaDetalleReservaciones();
                request.setAttribute("detalles", consultaGeneral);
                request.getRequestDispatcher("Vistas/view_detalle_reservacion.jsp").forward(request, response);
                break;
            case "add":
                List<DetalleReservacion> consultaUltimos = this.cdetalle_reservaciones.consultaUltimosDetalleReservacion(5, 1);
                request.setAttribute("detalles", consultaUltimos);
                request.getRequestDispatcher("Vistas/add_detalle_reservacion.jsp").forward(request, response);
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

        String asiento = request.getParameter("txtAsiento");
        String clase = request.getParameter("txtClase");
        // Relaciones (ID_Reservacion, ID_Vuelo) pueden parsearse si vienen: txtIdReservacion, txtIdVuelo
        // Por ahora dejamos que se asignen en otra capa si se requiere.

        detallereservacion.setAsiento(nullIfEmpty(asiento));
        detallereservacion.setClase(nullIfEmpty(clase));

        String btnAgregar = request.getParameter("btnAgregar");
        String btnUpdate = request.getParameter("btnUpdate");

        if (btnAgregar != null && !btnAgregar.isEmpty()) {
            this.cdetalle_reservaciones.crearDetalleReservacion(detallereservacion);
        } else if (btnUpdate != null && !btnUpdate.isEmpty()) {
            int id = 0;
            try { id = Integer.parseInt(request.getParameter("txtid")); } catch (NumberFormatException ignored) {}
            if (id > 0) detallereservacion.setIDDetalle(id);
            this.cdetalle_reservaciones.editarDetalleReservacion(detallereservacion);
        }

        List<DetalleReservacion> consultaUltimos = this.cdetalle_reservaciones.consultaUltimosDetalleReservacion(5, 1);
        request.setAttribute("detalles", consultaUltimos);
        request.getRequestDispatcher("Vistas/add_detalle_reservacion.jsp").forward(request, response);
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
