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
 * Servlet implementation class Agregar_Programa
 */
@WebServlet("/Agregar_Programa.do")
public class Agregar_Programa extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public Agregar_Programa() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		
		doPost(request, response);
	    	
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub		
		String codigo = request.getParameter("codigo");
        String nombre = request.getParameter("nombre");
		   Conexion con =Conexion.getConexion();
	    	Programa p= new Programa();
	    	ProgramaJpaController prog=new ProgramaJpaController(con.getBd());
	    	p.setCodigo(Integer.parseInt(codigo));
	    	p.setNombre(nombre);
	    	 Programa per=prog.findPrograma(Integer.parseInt(codigo));
	    	try {
	    		if(per!=null) {
	    			String tipo = "Existe";
	                request.setAttribute("alerta", tipo);
	                request.getRequestDispatcher("Registro_Programa.jsp").forward(request, response);
	                
	    		}else {	    			
	                prog.create(p);
					String tipo = "Creada";
	                request.setAttribute("alerta", tipo);
	                request.getRequestDispatcher("Registro_Programa.jsp").forward(request, response);
	    		}
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
				System.out.println("no registra");
			}
	    	
	}

}
