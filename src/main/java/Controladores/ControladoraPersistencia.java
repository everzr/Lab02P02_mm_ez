/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Controladores;

import Entity.DetalleReservacion;
import Entity.Pasajeros;
import Entity.Reservaciones;
import Entity.Vuelos;

/**
 *
 * @author EverZr
 */
public class ControladoraPersistencia {
    
    public ControladoraPersistencia (){}
    
    DetalleReservacionJpaController detalle_reservacionJPA = new DetalleReservacionJpaController();
    PasajerosJpaController pasajerosJPA = new PasajerosJpaController();
    ReservacionesJpaController reservacionesJPA = new ReservacionesJpaController();
    VuelosJpaController vuelosJPA = new VuelosJpaController();

    public void crearVuelo(Vuelos vuelo) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    public void crearPasajero(Pasajeros pasajero) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    public void crearReservacion(Reservaciones reservacion) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    public void crearDetalleReservacion(DetalleReservacion detalleReservacion) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }
    
}
