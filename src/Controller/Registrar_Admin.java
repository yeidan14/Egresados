package Controller;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import Conexion.Conexion;
import Dao.ProgramaJpaController;
import Dao.UsuarioJpaController;
import Dto.Programa;
import Dto.Usuario;

/**
 * Servlet implementation class Registrar_Admin
 */
@WebServlet("/Registrar_Admin.do")
public class Registrar_Admin extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public Registrar_Admin() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		String user = request.getParameter("user");
        String nombre = request.getParameter("name");
        String email = request.getParameter("email");
        String pass = request.getParameter("pass");
        int programa =Integer.parseInt(request.getParameter("programa")) ;
        int supe =Integer.parseInt(request.getParameter("supe")) ;
		   Conexion con =Conexion.getConexion();
	    	Usuario u= new Usuario();
	    	UsuarioJpaController uss=new UsuarioJpaController(con.getBd());
	    	
	    	ProgramaJpaController prog=new ProgramaJpaController(con.getBd());
	    	Programa p= prog.findPrograma(programa);
	    	u.setNombre(nombre);
	    	u.setUsuario(user);
	    	u.setEmail(email);
	    	u.setClave(pass);
	    	u.setPrograma(p);
	    	u.setSuperadmin(supe);
	    	 Usuario bus=uss.findUsuario(user);	   	
	    	
	    	 
	    	try {
	    		if(bus!=null) {
	    			String tipo = "Existe";
	                request.setAttribute("alerta", tipo);
	                request.getRequestDispatcher("Registro_Admin.jsp").forward(request, response);
	                
	    		}else {	    			
	               uss.create(u);
					String tipo = "Creada";
	                request.setAttribute("alerta", tipo);
	                request.getRequestDispatcher("Registro_Admin.jsp").forward(request, response);
	    		}
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
				System.out.println("no registra");
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
