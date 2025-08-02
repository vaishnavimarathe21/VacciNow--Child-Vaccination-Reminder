package Vaccination_Folder;
import java.sql.*;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class Updated_Vaccine_Price
 */
public class Updated_Vaccine_Price extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public Updated_Vaccine_Price() {
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
		
	    int Id=Integer.parseInt(request.getParameter("id"));
	    int Price=Integer.parseInt(request.getParameter("price"));
	    
	    Connection con=DbVaccine.connect();
	    
	    try {
			PreparedStatement pstmt=con.prepareStatement("select * from vaccine where id=? ");
		    pstmt.setInt(1,Id);
		   
		    ResultSet rs=pstmt.executeQuery();
		    
		    while(rs.next())
		    {
		    	int oldprice=rs.getInt(4);
		    	int totalprice=oldprice+Price;
		    	System.out.println("totalbalance"+totalprice);
		    	
		    	PreparedStatement pstmt2=con.prepareStatement("update  vaccine set price=? where id=?");
		    	pstmt2.setInt(1, totalprice);
		    	pstmt2.setInt(2,Id);
		    	
		    	int i=pstmt2.executeUpdate();
		    	if(i>0)
				{
					response.sendRedirect("hospital_admin_dashboard_02.html");
				}
		    	else
				{
					response.sendRedirect("hospital_admin_Update_vaccinePrice_11.html");
				}
		    }
		    
		    
		    
	    } catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		doGet(request, response);
	}

}
