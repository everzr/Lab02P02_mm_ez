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
public class ControllerDetalleReservacion {

    private final ControladoraPersistencia cont_persis;

    public ControllerDetalleReservacion() {
        this.cont_persis = new ControladoraPersistencia();
    }

    // Crear
    public void crearDetalleReservacion(DetalleReservacion detalleReservacion) {
        this.cont_persis.crearDetalleReservacion(detalleReservacion);
    }

    // Listar todos
    public List<DetalleReservacion> traerListaDetalleReservaciones() {
        return this.cont_persis.traerListaDetalleReservaciones();
    }

    // Traer por id
    public DetalleReservacion traerDetalleReservacion(int id) {
        return this.cont_persis.traerDetalleReservacion(id);
    }

    // Consultar últimos N (se ignora firstResult para mantener paridad con el ejemplo)
    public List<DetalleReservacion> consultaUltimosDetalleReservacion(int maxResults, int firstResult) {
        return this.cont_persis.consultaUltimosDetalleReservacion(maxResults);
    }

    // Editar
    public void editarDetalleReservacion(DetalleReservacion detalleReservacion) {
        this.cont_persis.editarDetalleReservacion(detalleReservacion);
    }

    // Eliminar
    public void eliminarDetalleReservacion(int id) {
        this.cont_persis.eliminarDetalleReservacion(id);
    }
}
