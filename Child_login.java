package Vaccination_Folder;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class Child_login extends HttpServlet {
    private static final long serialVersionUID = 1L;

    public Child_login() {
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
        
        Connection con = null;
        PreparedStatement pstmt = null;
        ResultSet rs = null;

        try {
            con = DbVaccine.connect();
            String query = "SELECT * FROM parent WHERE email = ? AND password = ?";
            pstmt = con.prepareStatement(query);
            pstmt.setString(1, loginemail);
            pstmt.setString(2, loginepass);
            rs = pstmt.executeQuery();

            if (rs.next()) {
                // User found, redirect to dashboard
                response.sendRedirect("User_Dashboard.html");
            } else {
                // User not found, redirect to login page
                response.sendRedirect("User_login.html");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            try {
                if (rs != null) {
                    rs.close();
                }
                if (pstmt != null) {
                    pstmt.close();
                }
                if (con != null) {
                    con.close();
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }
}