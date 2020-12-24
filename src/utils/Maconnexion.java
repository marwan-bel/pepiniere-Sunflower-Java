/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package utils;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author gazzah
 */
public class Maconnexion {
          private static String url = "jdbc:mysql://127.0.0.1:3306/pepiniere";
    private static String user = "root";
    private static String password ="";

    public static Object createStatement() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
    private Connection conn;
    
    static  Maconnexion inst;

    private Maconnexion() {
        try {
            conn = DriverManager.getConnection(url, user, password);
            
            System.out.println("connexion établie");
            
        } catch (SQLException ex) {
            Logger.getLogger(Maconnexion.class.getName()).log(Level.SEVERE, null, ex);
        }
    }


    public Connection getConn() {
        return conn;
    }
    
    
     public static Maconnexion getinstance()
    {
        if(inst == null)
        {
            inst = new Maconnexion();
        }
        return inst;

    }
    
}
