package Vaccination_Folder;

import java.sql.*;
import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class Changepass_admin extends HttpServlet {
    private static final long serialVersionUID = 1L;

    public Changepass_admin() {
        super();
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.getWriter().append("Served at: ").append(request.getContextPath());
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
            pstmt = con.prepareStatement("UPDATE hospital SET password = ? WHERE email = ?");
            pstmt.setString(1, loginepass);
            pstmt.setString(2, loginemail);
            
            int rowsUpdated = pstmt.executeUpdate();

            if (rowsUpdated > 0) {
                // Password updated successfully
                response.sendRedirect("hospital_admin_login_01.html");
            } else {
                // Email not found, redirect to change password page
                response.sendRedirect("hospital_admin_change_password_10.html");
            }
            
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            try {
                if (con != null) {
                    con.close();
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }

        doGet(request, response);
    }
}