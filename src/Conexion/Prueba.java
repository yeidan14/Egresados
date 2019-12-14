/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Conexion;
import Dao.UsuarioJpaController;
import Dto.Usuario;
import Dao.*;
import Dto.*;


public class Prueba {
    
    public static void main(String[] args) throws Exception {

    Conexion con =Conexion.getConexion();
    	Usuario u= new Usuario();
    	UsuarioJpaController user=new UsuarioJpaController(con.getBd());
    	u.setNombre("SuperAdmin");
    	u.setEmail("yeidan141995");
    	u.setUsuario("admin1");
    	u.setClave("12345");
    	u.setSuperadmin(1);
    	user.create(u);
    	System.out.println("UsuarioCreado");
      
    }
    
}
