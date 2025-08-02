package Vaccination_Folder;

import java.sql.*;
import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class Admin_hospital_login extends HttpServlet {
    private static final long serialVersionUID = 1L;

    public Admin_hospital_login() {
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
        ResultSet rs;
        try {
            // Query the database to check if the email and password match
            pstmt = con.prepareStatement("SELECT * FROM hospital WHERE email = ? AND password = ?");
            pstmt.setString(1, loginemail);
            pstmt.setString(2, loginepass);
            rs = pstmt.executeQuery();

            if (rs.next()) {
                // Credentials match, redirect to the dashboard
                response.sendRedirect("hospital_admin_dashboard_02.html");
            } else {
                // Credentials don't match, redirect to the login page
                response.sendRedirect("hospital_admin_login_01.html");
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