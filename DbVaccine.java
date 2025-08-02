package Vaccination_Folder;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DbVaccine {
    public static Connection connect() {
        Connection con = null;
        try {
            Class.forName("com.mysql.jdbc.Driver");
            con = DriverManager.getConnection("jdbc:mysql://localhost:3306/vaccinedb", "root", "");
        } catch (SQLException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        return con;
    }
}