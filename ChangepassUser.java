package Vaccination_Folder;
import java.sql.*;
import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class ChangepassUser extends HttpServlet {
    private static final long serialVersionUID = 1L;

    public ChangepassUser() {
        super();
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String loginemail = request.getParameter("email");
        String loginepass = request.getParameter("password");
        System.out.println("username: " + loginemail);
        System.out.println("userpass: " + loginepass);

        Connection con = DbVaccine.connect();
        PreparedStatement pstmt;

        try {
            // Update the password if the email exists
            pstmt = con.prepareStatement("UPDATE parent SET password = ? WHERE email = ?");
            pstmt.setString(1, loginepass);
            pstmt.setString(2, loginemail);

            int rowsUpdated = pstmt.executeUpdate();

            if (rowsUpdated > 0) {
                // Password updated successfully
                response.sendRedirect("User_login.html");
            } else {
                // Email not found, redirect to change password page
                response.sendRedirect("User_Dashboard.html");
            }

        } catch (SQLException e) {
            e.printStackTrace();
            // Optionally redirect to an error page
            response.sendRedirect("error_page.html");
        } finally {
            try {
                if (con != null) {
                    con.close();
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.getWriter().append("Served at: ").append(request.getContextPath());
    }
}