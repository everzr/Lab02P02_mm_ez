/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Entity;

import Controladores.ControladoraPersistencia;

/**
 *
 * @author EverZr
 */
public class ControllerPasajeros {
    ControladoraPersistencia cont_persis;
     
    public void crearPasajero(Pasajeros pasajero){
        this.cont_persis.crearPasajero(pasajero);
 }
}
