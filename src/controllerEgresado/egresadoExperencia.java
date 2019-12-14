package controllerEgresado;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import Conexion.Conexion;
import Dao.EstudioJpaController;
import Dto.Egresado;
import Dto.Estudio;
import Dto.Nivel;

/**
 * Servlet implementation class egresadoExperencia
 */
@WebServlet("/egresadoExperencia")
public class egresadoExperencia extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public egresadoExperencia() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		response.getWriter().append("Served at: ").append(request.getContextPath());
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		String funcion= request.getParameter("funcion");
		String incio= request.getParameter("periodoinicio");
		String descripcion= request.getParameter("descripcion");
		
		Conexion connect = Conexion.getConexion();
		
		Egresado e = new Egresado();
		
		
		try {
			
			EstudioJpaController econ = new EstudioJpaController(connect.getBd());
			
			request.getRequestDispatcher("viewEgresado").forward(request, response);
			
		} catch (Exception e2) {
			System.out.println(e2.getMessage());
		}
		
	
	}

}
