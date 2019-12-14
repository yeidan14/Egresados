/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Conexion;

import Dao.*;
import Dto.*;

public class Prueba {
    
    public static void main(String[] args) throws Exception {
    	   Egresado user = new Egresado();
           user.setNombre("---g2-2qw");
           
           Conexion con = Conexion.getConexion();
           EgresadoJpaController reg = new EgresadoJpaController(con.getBd());
           reg.create(user);
           System.out.println("registrado" + user.getNombre());
           
    }
    
}
