package Controller;

import java.io.IOException;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import Conexion.Conexion;
import Dao.EgresadoJpaController;
import Dao.UsuarioJpaController;
import Dto.Egresado;
import Dto.Usuario;

/**
 * Servlet implementation class Validar_admin
 */
@WebServlet("/Validar_admin.do")
public class Validar_admin extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public Validar_admin() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String user = request.getParameter("user");
        String pass = request.getParameter("pass");

        Conexion con = Conexion.getConexion();
        UsuarioJpaController reg = new UsuarioJpaController(con.getBd());
        EgresadoJpaController bdegr = new EgresadoJpaController(con.getBd());
        
        List<Usuario> us=reg.findUsuarioEntities();     
        List<Egresado> egr=bdegr.findEgresadoEntities();    
     
   
               try {
        
        	
        	for(Usuario use: us) {
                
                if(use.getUsuario().equals(user)&& use.getSuperadmin()==1 && use.getClave().equals(pass)){
                	 HttpSession misession = request.getSession(true);
                     misession.setAttribute("Usuario", use.getUsuario());
                     response.sendRedirect("index_supeadmin.jsp");
                } else if(use.getUsuario().equals(user)&& use.getSuperadmin()==2 && use.getClave().equals(pass)){
               	 HttpSession misession = request.getSession(true);
                 misession.setAttribute("Usuario",use.getUsuario());            

                 response.sendRedirect("indexadmin_.jsp");
            } 
            }
        	
             for(Egresado eg: egr) {
                
                if(eg.getDocumento().equalsIgnoreCase(user) && eg.getClave().equals(pass)){
                	 HttpSession misession = request.getSession(true);
                     misession.setAttribute("Usuario", eg.getDocumento());
                     response.sendRedirect("index_egresado.jsp");
                }
            }
 
            
                String tipo = "NoExiste";
                    request.setAttribute("alerta", tipo);
                    request.getRequestDispatcher("index.jsp").forward(request, response);
            

//        
        } catch (Exception ex) {
            Logger.getLogger(Validar_admin.class.getName()).log(Level.SEVERE, null, ex);
            
        }
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
