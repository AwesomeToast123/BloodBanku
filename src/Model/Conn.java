/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Model;

import com.mysql.jdbc.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 *
 * @author Michael Cabusas
 */
public class Conn {
    private Connection conn = null;
    
    
    public Connection getConn() throws ClassNotFoundException
    {
        try{
            Class.forName("com.mysql.jdbc.Driver");
            conn = (Connection) DriverManager.getConnection("jdbc:mysql://localhost:3306/bloodbank","root","");
        
        }catch(SQLException e){
            System.out.println(e);
        }
        
        return conn;
    }
}
