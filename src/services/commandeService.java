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





import entities.Commande;
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
import tools.DataSource;



public class commandeService {
    
Connection cnx=DataSource.getInstance().getConnection();
    
    
    /**
     *111111111111111111111
     * @param C     */
    public void ajouterCommande (Commande C){
        
        
            try {
            String req = "INSERT INTO `Commande`(`delaisLivraison`,`FraisdePort`,`montant`) VALUES ('"+C.getDelaisLivraison()+"','"+C.getFraisdePort()+"','"+C.getMontant() +"')";
            Statement st = cnx.createStatement();
            st.executeUpdate(req);
            System.out.println("Commande Added successfully!");
            }
            catch (SQLException ex) {
           System.out.println("failed!"); 
        }

           }
    
    Statement ste;
Connection conn = DataSource.getInstance().getConnection();
    
    
    public void modifier(Commande C) {

        try {
            String req = "UPDATE `commande`SET `delaisLivraison` = '" + C.getDelaisLivraison()+ "', `FraisdePort` = '" + C.getFraisdePort()+"', `montant` = '"+C.getMontant()+"' WHERE `commande`.`refCommande` = " + C.getRefCommande();
                
            Statement st = conn.createStatement();
            st.executeUpdate(req);
            System.out.println("Commande Modifié avec succès");
        } catch (SQLException ex) {
            System.out.println(ex);
        }
        
        
    }
    
    

    public void supprimercommande( int refCommande) {
     
          
               try {
            String req = "DELETE FROM `commande` WHERE refCommande = " + refCommande;
            Statement st = conn.createStatement();
            st.executeUpdate(req);
            System.out.println("Commande deleted !");
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }

    }
        public List<Commande> afficherCommande() {
      //  obList.clear();

       List<Commande> list = new ArrayList<>();
        try {
            String req = "Select * from commande";
            Statement st = conn.createStatement();
         
            ResultSet RS= st.executeQuery(req);
            while(RS.next()){

             Commande A = new Commande();
             A.setDelaisLivraison(RS.getString("delaisLivraison"));
             A.setRefCommande(RS.getInt("refCommande"));
             A.setFraisdePort(RS.getDouble("FraisdePort"));
             A.setMontant(RS.getDouble("montant"));
             list.add(A);
             
            }
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }

        return list;
        
        
    }
        
 
                 
                


}




       

   
   

    







          
   
    