package Vaccination_Folder;
import java.sql.*;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class ChildRegister
 */
public class ChildRegister extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ChildRegister() {
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
		try {
            String ChildName = request.getParameter("childName");
            String ParentName = request.getParameter("parentName");
            String dobStr = request.getParameter("dob");
            String mobileStr = request.getParameter("parentContact");
            String Address = request.getParameter("address");
            int id = 0;

            // Parse the date of birth
            SimpleDateFormat formatter = new SimpleDateFormat("dd-MM-yy");
            java.sql.Date DOB = null;
            try {
                Date parsedDate = formatter.parse(dobStr);
                DOB = new java.sql.Date(parsedDate.getTime());
            } catch (ParseException e) {
                e.printStackTrace();
                // Handle the error appropriately, e.g., send an error response or set an error message
                response.sendError(HttpServletResponse.SC_BAD_REQUEST, "Invalid date format");
                return;
            }

            // Validate and parse the mobile number
            long mobileNo;
            try {
                mobileNo = Long.parseLong(mobileStr);
            } catch (NumberFormatException e) {
                e.printStackTrace();
                // Handle the error appropriately, e.g., send an error response or set an error message
                response.sendError(HttpServletResponse.SC_BAD_REQUEST, "Invalid mobile number format");
                return;
            }

            // Proceed with further processing, e.g., saving the data to a database
            Connection con = DbVaccine.connect();

            PreparedStatement pstmt = con.prepareStatement("insert into child values(?,?,?,?,?,?)");
            pstmt.setInt(1, id);
            pstmt.setString(2, ChildName);
            pstmt.setString(3, ParentName);
            pstmt.setDate(4, DOB);
            pstmt.setLong(5, mobileNo);
            pstmt.setString(6, Address);
            int i = pstmt.executeUpdate();
            if (i > 0) {
                response.sendRedirect("User_dashboard.html");
            }

        } catch (SQLException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
            response.sendError(HttpServletResponse.SC_INTERNAL_SERVER_ERROR, "Database error");
        }
		
		
		
		doGet(request, response);
	}

}
