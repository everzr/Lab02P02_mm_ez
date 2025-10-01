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
public class ControllerPasajeros {
    private final ControladoraPersistencia cont_persis;

    public ControllerPasajeros() {
        this.cont_persis = new ControladoraPersistencia();
    }

    // Crear
    public void crearPasajero(Pasajeros pasajero) {
        this.cont_persis.crearPasajero(pasajero);
    }

    // Listar todos
    public List<Pasajeros> traerListaPasajeros() {
        return this.cont_persis.traerListaPasajeros();
    }

    // Traer por id
    public Pasajeros traerPasajero(int id) {
        return this.cont_persis.traerPasajero(id);
    }

    // Consultar últimos N (se ignora firstResult para mantener paridad con el ejemplo)
    public List<Pasajeros> consultaUltimosPasajeros(int maxResults, int firstResult) {
        return this.cont_persis.consultaUltimosPasajeros(maxResults);
    }

    // Editar
    public void editarPasajero(Pasajeros pasajero) {
        this.cont_persis.editarPasajero(pasajero);
    }

    // Eliminar
    public void eliminarPasajero(int id) {
        this.cont_persis.eliminarPasajero(id);
    }
}
