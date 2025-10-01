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
public class ControllerDetalleReservacion {
    
    ControladoraPersistencia cont_persis;
    
    
    public void crearDetalleReservacion(DetalleReservacion detalleReservacion){
        this.cont_persis.crearDetalleReservacion(detalleReservacion);
 }
}
