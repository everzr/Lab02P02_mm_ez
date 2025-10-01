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
public class ControllerVuelos {

    private final ControladoraPersistencia cont_persis;

    public ControllerVuelos() {
        this.cont_persis = new ControladoraPersistencia();
    }

    // Crear
    public void crearVuelo(Vuelos vuelo) {
        this.cont_persis.crearVuelo(vuelo);
    }

    // Listar todos
    public List<Vuelos> traerListaVuelos() {
        return this.cont_persis.traerListaVuelos();
    }

    // Traer por id
    public Vuelos traerVuelo(int id) {
        return this.cont_persis.traerVuelo(id);
    }

    // Consultar últimos N (se ignora firstResult para mantener paridad con el ejemplo)
    public List<Vuelos> consultaUltimosVuelos(int maxResults, int firstResult) {
        return this.cont_persis.consultaUltimosVuelos(maxResults);
    }

    // Editar
    public void editarVuelo(Vuelos vuelo) {
        this.cont_persis.editarVuelo(vuelo);
    }

    // Eliminar
    public void eliminarVuelo(int id) {
        this.cont_persis.eliminarVuelo(id);
    }

}
