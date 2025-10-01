/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Entity;

import Controladores.ControladoraPersistencia;
import java.util.List;

/**
 *
 * @author EverZr
 */
public class ControllerReservaciones {
    private final ControladoraPersistencia cont_persis;

    public ControllerReservaciones() {
        this.cont_persis = new ControladoraPersistencia();
    }

    // Crear
    public void crearReservacion(Reservaciones reservacion) {
        this.cont_persis.crearReservacion(reservacion);
    }

    // Listar todos
    public List<Reservaciones> traerListaReservaciones() {
        return this.cont_persis.traerListaReservaciones();
    }

    // Traer por id
    public Reservaciones traerReservacion(int id) {
        return this.cont_persis.traerReservacion(id);
    }

    // Consultar últimos N (se ignora firstResult para mantener paridad con el ejemplo)
    public List<Reservaciones> consultaUltimasReservaciones(int maxResults, int firstResult) {
        return this.cont_persis.consultaUltimasReservaciones(maxResults);
    }

    // Editar
    public void editarReservacion(Reservaciones reservacion) {
        this.cont_persis.editarReservacion(reservacion);
    }

    // Eliminar
    public void eliminarReservacion(int id) {
        this.cont_persis.eliminarReservacion(id);
    }
}
