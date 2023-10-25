
/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package services;

/**
 *
 * @author gheri
 */

import entities.Livraison;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

 import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.sql.Date;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import tools.DataSource;


public class livraisonService {
    
Connection cnx=DataSource.getInstance().getConnection();
    

    /**
     *
     * @param C
     */
    public void ajouterLivraison (Livraison L){
        
        
            try {
            String req = "INSERT INTO `livraison`( `date_livraison`,`refCommande`) VALUES ('"+L.getDate_livraison()+"','"+L.getRefCommande()+"')";
            Statement st = cnx.createStatement();
            st.executeUpdate(req);
            System.out.println("Livraison Added successfully!");
            }
            catch (SQLException ex) {
           System.out.println("failed!"); 
        }

           }
    
        Statement ste;
Connection conn = DataSource.getInstance().getConnection();
    
//    
//    public void modifierLivraison(Livraison L) {
//
//        try {
//            String req = "UPDATE `livraison`SET `date_livraison` = '" + L.getDate_livraison()+ "', `refCommande` = '" + L.getRefCommande()+"' WHERE `livraison`.`ref_livraison` = " + L.getRef_livraison();
//                
//            Statement st = conn.createStatement();
//            st.executeUpdate(req);
//            System.out.println("Livraison Modifié avec succès");
//        } catch (SQLException ex) {
//            System.out.println(ex);
//        }
//        
//        
//    }
    
    public void modifierLivraison(Livraison L) {
    try {
        // Utilisation de PreparedStatement pour éviter l'injection SQL
        String req = "UPDATE livraison SET date_livraison = ?, refCommande = ? WHERE ref_livraison = ?";

        // Préparation de la requête
        PreparedStatement pstmt = conn.prepareStatement(req);
        pstmt.setDate(1, L.getDate_livraison());
        pstmt.setString(2, L.getRefCommande());
        pstmt.setInt(3, L.getRef_livraison());

        // Exécution de la requête
        int rowsAffected = pstmt.executeUpdate();

        if (rowsAffected > 0) {
            System.out.println("Livraison modifiée avec succès");
        } else {
            System.out.println("Aucune modification effectuée");
        }

        // Fermeture du PreparedStatement
        pstmt.close();
    } catch (SQLException ex) {
        System.out.println(ex);
    }
}

    
    
    public void supprimerLivraison( int ref_livraison) {
        try {
            String req = "DELETE FROM `livraison` WHERE ref_livraison=?";
            PreparedStatement st = cnx.prepareStatement(req);
            st.setInt(1, ref_livraison);
            st.executeUpdate();
            System.out.println("Livraison supprimer avec succès");
        } catch (SQLException ex) {
            System.out.println(ex);
        }
    }
    public List<Livraison> afficher(){
        List<Livraison> livraison = new ArrayList<>();
         //1
         String req = "SELECT * FROM livraison";
        try {
            //2
            Statement st = cnx.createStatement();
            //3
            ResultSet rs = st.executeQuery(req);
            while (rs.next()) {
                Livraison E = new Livraison ();
                E.setRef_livraison(rs.getInt("ref_livraison"));

                E.setDate_livraison(rs.getDate("date_livraison"));
                            E.setRefCommande(rs.getString("refCommande"));

              
                
  
                
                 
                
                livraison.add(E);
            }
            
            
        } catch (SQLException ex) {
            Logger.getLogger(livraisonService.class.getName()).log(Level.SEVERE, null, ex);
        }
         
         return livraison ;
    }
    
       
    
               ObservableList<Livraison>obList = FXCollections.observableArrayList();

           public ObservableList<Livraison> afficherLivraison2() {
        String sql = "SELECT * FROM livraison";
        List<Livraison> listeCatg = new ArrayList<>();

        try {
            Statement statement = cnx.createStatement();
            ResultSet result = statement.executeQuery(sql);
            while (result.next()) {
                int ref_livraison = result.getInt(1);
                Date date_livraison=result.getDate(2);
                String refCommande=result.getString(3);
                Livraison c = new Livraison(ref_livraison, date_livraison,refCommande);
                obList.add(c);
            }
        } catch (SQLException ex) {
            System.out.println(ex);
        }
        return obList;
    }
        

}




       

  