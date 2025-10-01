/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Controladores;

import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

/**
 *
 * @author EverZr
 */
public class ReservacionesJpaController {
    
     private final EntityManagerFactory emf;

    public ReservacionesJpaController() {
        this.emf=Persistence.createEntityManagerFactory("pruebaJPAPU");
    }
    
}
