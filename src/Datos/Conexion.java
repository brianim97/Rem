package Datos;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
 
/**
 *
 * @author sqlitetutorial.net
 */
public class Conexion {
     /**
     * Connect to a sample database
     * @return 
     */
    String url = "jdbc:sqlite:DB\\remiseria.db";
    Connection connect;

    public void close(){
        try {
            connect().close();
        }
         catch (SQLException ex) {
            Logger.getLogger(Conexion.class.getName()).log(Level.SEVERE,null,ex);
        }
    }
    public Connection connect() {
        // SQLite connection string
        Connection conn = null;
        try {
            conn = DriverManager.getConnection(url);
            System.out.println("Conexion establecida");
        } catch (SQLException e) {
            System.out.println("Error en conexion");
            System.out.println(e.getMessage());
        }
        return conn;
    }
    
    
}
    
       
    