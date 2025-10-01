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
public class VuelosJpaController {
    
     private final EntityManagerFactory emf;

    public VuelosJpaController() {
        this.emf=Persistence.createEntityManagerFactory("pruebaJPAPU");
    }
    
}
