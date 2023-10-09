/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;


/**
 *
 * @author tlili
 */
public class Relation {
    private Connection cnx;
    private static Relation instance;
    
    final String URL = "jdbc:mysql://localhost:3306/yassine";
    final String USR = "root";
    final String PWD = "";
    
   
    
    private Relation() {
        try {
            cnx = DriverManager.getConnection(URL, USR, PWD);
            System.out.println("Relation etablie avec succes!");
        } catch (SQLException ex) {
             System.out.println(ex.getMessage());
        }
        
    }
    public static Relation getInstance() {
        if(instance == null)
            instance = new Relation();
        return instance;
    }

    public Connection getConnection(){
        return this.cnx;
    }

}

