package Controlador;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import Conexion.*;
import Dto.*;
import Dao.EgresadoJpaController;
import Dao.ProgramaJpaController;
/**
 * Servlet implementation class CrearEgresado
 */
@WebServlet("/CrearE.do")
public class CrearEgresado extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public CrearEgresado() {
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
		doGet(request, response);
		
		
		int id=Integer.parseInt(request.getParameter("ID"));
		String Nombre= request.getParameter("Nombre");
		String Doc=request.getParameter("Doc");
		String Tel=request.getParameter("Tel");
		String Correo=request.getParameter("Correo");
		String contra=request.getParameter("Contra");
		String Perfil=request.getParameter("Perfil");
		String cod=request.getParameter("Codigo");
		String Program=request.getParameter("Prog");
		
		
		Egresado E = new Egresado();
		
		E.setNombre(Nombre);
		E.setId(id);
		E.setClave(contra);
		E.setCodigo(cod);
		E.setDocumento(Doc);
		E.setEmail(Correo);
		E.setPerfil(Perfil);
		E.setTelefono(Tel);
		
		Conexion con =Conexion.getConexion();
		EgresadoJpaController reg = new EgresadoJpaController(con.getBd());
		ProgramaJpaController regP = new ProgramaJpaController(con.getBd());
		try {
			
			reg.create(E);
			List<Egresado> clientes=reg.findEgresadoEntities();
	        
	        request.getSession().setAttribute("Egresado", clientes);
	        
	           request.getRequestDispatcher("index.jsp").forward(request, response);

		} catch (Exception e2) {
			// TODO: handle exception
		}
		
		
	}

}
