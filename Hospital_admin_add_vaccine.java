package Vaccination_Folder;
import java.sql.*;
import Vaccination_Folder.DbVaccine;
import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;



/**
 * Servlet implementation class Hospital_admin_add_vaccine
 */
public class Hospital_admin_add_vaccine extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public Hospital_admin_add_vaccine() {
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
		
		String Vaccinename=request.getParameter("vaccineName");
		String VaccineDetails=request.getParameter("vaccineDetails");
		int VaccinePrice =Integer.parseInt(request.getParameter("price"));
		String Months=request.getParameter("months");
		int id=0;
		
		Connection con=DbVaccine.connect();
		
		try
		{
			PreparedStatement pstmt=con.prepareStatement("insert into vaccine values(?,?,?,?,?)");
			pstmt.setInt(1, id);
			pstmt.setString(2,Vaccinename);
			pstmt.setString(3,VaccineDetails);
			pstmt.setInt(4,VaccinePrice);
			pstmt.setString(5,Months);
			int i=pstmt.executeUpdate();
			if(i>0)
			{
		
				response.sendRedirect("hospital_admin_dashboard_02.html");
			}
	   
		} 
	catch (SQLException e) 
	{
		// TODO Auto-generated catch block
		e.printStackTrace();
	}
		
		doGet(request, response);
	}

}
