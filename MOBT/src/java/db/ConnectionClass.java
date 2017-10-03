/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package db;
import java.sql.*;

public class ConnectionClass {
    Connection con = null;
    Statement st = null;
    ResultSet rs = null;

    public ConnectionClass() {
        try {
            Class.forName("com.mysql.jdbc.Driver");
            con = DriverManager.getConnection("jdbc:mysql://127.0.0.1:3306/db_mobt", "root", "root");
            st = con.createStatement();
        } catch (Exception ex) {
            System.out.println(ex);
        }
    }



    public boolean executeCommand(String str) {
        boolean bol = false;
        try {

          
            st.executeUpdate(str);
            bol = true;

        } catch (SQLException ex) {
            System.out.println(ex);
        }
        return bol;
    }


    public ResultSet selectCommand(String selQry) {
        try {
            
            rs = st.executeQuery(selQry);
        } catch (SQLException ex) {
            System.out.println(ex);
        }
        return rs;
    }
}



