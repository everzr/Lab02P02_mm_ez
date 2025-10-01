/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package servlets_sis;

import Entity.ControllerVuelos;
import Entity.Vuelos;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Date;
import java.sql.Time;
import java.time.LocalDate;
import java.time.LocalTime;
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
@WebServlet(name = "servlet_vuelos", urlPatterns = {"/servlet_vuelos"})
public class servlet_vuelos extends HttpServlet {
    
    ControllerVuelos cvuelos = new ControllerVuelos();
    Vuelos vuelo = new Vuelos();

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
            out.println("<title>Servlet servlet_vuelos</title>");
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet servlet_vuelos at " + request.getContextPath() + "</h1>");
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
            try {
                id = Integer.parseInt(request.getParameter("id"));
            } catch (NumberFormatException ignored) {}
        }

        List<Vuelos> consultaGeneral = this.cvuelos.traerListaVuelos();
        if (accion == null) accion = "con"; // por defecto mostrar consulta

        switch (accion) {
            case "con":
                request.setAttribute("vuelos", consultaGeneral);
                request.getRequestDispatcher("Vistas/view_vuelos.jsp").forward(request, response);
                break;

            case "mod":
                Vuelos v = this.cvuelos.traerVuelo(id);
                request.setAttribute("vuelo", v);
                request.getRequestDispatcher("Vistas/upd_vuelos.jsp").forward(request, response);
                break;

            case "del":
                this.cvuelos.eliminarVuelo(id);
                consultaGeneral = this.cvuelos.traerListaVuelos();
                request.setAttribute("vuelos", consultaGeneral);
                request.getRequestDispatcher("Vistas/view_vuelos.jsp").forward(request, response);
                break;

            case "add":
                List<Vuelos> consultaUltimos = this.cvuelos.consultaUltimosVuelos(5, 1);
                request.setAttribute("vuelos", consultaUltimos);
                request.getRequestDispatcher("Vistas/add_vuelos.jsp").forward(request, response);
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

        String numeroVuelo = request.getParameter("txtNumeroVuelo");
        String aerolinea = request.getParameter("txtAerolinea");
        String origen = request.getParameter("txtOrigen");
        String destino = request.getParameter("txtDestino");
        String fechaSalidaStr = paramTrim(request, "txtFechaSalida");    // yyyy-MM-dd
        String horaSalidaStr = paramTrim(request, "txtHoraSalida");      // HH:mm or HH:mm:ss
        String fechaLlegadaStr = paramTrim(request, "txtFechaLlegada");  // yyyy-MM-dd
        String horaLlegadaStr = paramTrim(request, "txtHoraLlegada");    // HH:mm or HH:mm:ss
        String avion = request.getParameter("txtAvion");

        // Formatos
        DateTimeFormatter fFecha = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        DateTimeFormatter fHora = DateTimeFormatter.ofPattern(horaSalidaStr != null && horaSalidaStr.length() == 8 ? "HH:mm:ss" : "HH:mm");

        // Parseo fechas/horas (utilizando java.sql.* que extiende java.util.Date)
        Date fechaSalida = null;
        Date fechaLlegada = null;
        Time horaSalida = null;
        Time horaLlegada = null;
        if (fechaSalidaStr != null && !fechaSalidaStr.isEmpty()) {
            LocalDate ld = LocalDate.parse(fechaSalidaStr, fFecha);
            fechaSalida = Date.valueOf(ld);
        }
        if (fechaLlegadaStr != null && !fechaLlegadaStr.isEmpty()) {
            LocalDate ld = LocalDate.parse(fechaLlegadaStr, fFecha);
            fechaLlegada = Date.valueOf(ld);
        }
        if (horaSalidaStr != null && !horaSalidaStr.isEmpty()) {
            LocalTime lt = LocalTime.parse(horaSalidaStr, fHora);
            horaSalida = Time.valueOf(lt);
        }
        if (horaLlegadaStr != null && !horaLlegadaStr.isEmpty()) {
            LocalTime lt = LocalTime.parse(horaLlegadaStr, fHora);
            horaLlegada = Time.valueOf(lt);
        }

        // Poblar entidad
        vuelo.setNumeroVuelo(nullIfEmpty(numeroVuelo));
        vuelo.setAerolinea(nullIfEmpty(aerolinea));
        vuelo.setOrigen(nullIfEmpty(origen));
        vuelo.setDestino(nullIfEmpty(destino));
        if (fechaSalida != null) vuelo.setFechaSalida(fechaSalida);
        if (horaSalida != null) vuelo.setHoraSalida(horaSalida);
        if (fechaLlegada != null) vuelo.setFechaLlegada(fechaLlegada);
        if (horaLlegada != null) vuelo.setHoraLlegada(horaLlegada);
        vuelo.setAvion(nullIfEmpty(avion));

        String btnAgregar = request.getParameter("btnAgregar");
        String btnUpdate = request.getParameter("btnUpdate");

        if (btnAgregar != null && !btnAgregar.isEmpty()) {
            // Agregar
            this.cvuelos.crearVuelo(vuelo);
        } else if (btnUpdate != null && !btnUpdate.isEmpty()) {
            int id = 0;
            try {
                id = Integer.parseInt(request.getParameter("txtid"));
            } catch (NumberFormatException ignored) {}
            if (id > 0) vuelo.setIDVuelo(id);
            this.cvuelos.editarVuelo(vuelo);
        }

        List<Vuelos> consultaUltimos = this.cvuelos.consultaUltimosVuelos(5, 1);
        request.setAttribute("vuelos", consultaUltimos);
        request.getRequestDispatcher("Vistas/add_vuelos.jsp").forward(request, response);
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

    private static String nullIfEmpty(String v) {
        return (v == null || v.trim().isEmpty()) ? null : v.trim();
    }

}
