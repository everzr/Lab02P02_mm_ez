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
public class ControllerReservaciones {
    ControladoraPersistencia cont_persis;
    
    public void crearReservacion(Reservaciones reservacion){
        this.cont_persis.crearReservacion(reservacion);
 }
    
}
