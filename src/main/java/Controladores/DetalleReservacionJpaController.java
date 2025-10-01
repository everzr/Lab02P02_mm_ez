/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Controladores;

import java.io.Serializable;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

/**
 *
 * @author EverZr
 */
public class DetalleReservacionJpaController implements Serializable {

    private final EntityManagerFactory emf;

    public DetalleReservacionJpaController() {
        this.emf=Persistence.createEntityManagerFactory("pruebaJPAPU");
    }
    
    
    
}
