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
 * Servlet implementation class estudiosEgresado
 */
@WebServlet("/estudiosEgresado")
public class estudiosEgresado extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public estudiosEgresado() {
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
		//doGet(request, response);
		
		String titulo= request.getParameter("titulo");
		String periodo= request.getParameter("periodo");
		String lugar= request.getParameter("lugar");
		String nivel= request.getParameter("nivel");
		
		System.out.println(nivel);
		Conexion connect = Conexion.getConexion();
		
		Egresado e = new Egresado();
		Nivel n = new Nivel();
		n.setId(Integer.parseInt(nivel));
		Estudio estudio = new Estudio(titulo, periodo, lugar, n, e);
		
		try {
			
			EstudioJpaController econ = new EstudioJpaController(connect.getBd());
			econ.create(estudio);
			
			request.getRequestDispatcher("viewEgresado").forward(request, response);
			
		} catch (Exception e2) {
			System.out.println(e2.getMessage());
		}
		
		
		
		
	}

}
