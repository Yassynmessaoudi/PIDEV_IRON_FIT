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
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import tools.DataSource;



public class commandeService {
    
Connection cnx=DataSource.getInstance().getConnection();
    
    
    /**
     *111111111111111111111
     * @param C     */
    public void ajouterCommande (Commande C){
        
        
            try {
            String req = "INSERT INTO `Commande`( `refCommande`,`delaisLivraison`,`FraisdePort`,`montant`) VALUES ('"+C.getRefCommande()+"','"+C.getDelaisLivraison()+"','"+C.getFraisdePort()+"','"+C.getMontant() +"')";
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
    
    
//    public void modifier(Commande C) {
//
//        try {
//            String req = "UPDATE `commande`SET `delaisLivraison` = '" + C.getDelaisLivraison()+ "', `FraisdePort` = '" + C.getFraisdePort()+"', `montant` = '"+C.getMontant()+"' WHERE `commande`.`refCommande` = " + C.getRefCommande();
//                
//            Statement st = conn.createStatement();
//            st.executeUpdate(req);
//            System.out.println("Commande Modifié avec succès");
//        } catch (SQLException ex) {
//            System.out.println(ex);
//        }
//        
//        
//    }
//    

public void modifier(Commande C) {
    try {
        String req = "UPDATE commande SET delaisLivraison = ?, FraisdePort = ?, montant = ? WHERE refCommande = ?";
        PreparedStatement preparedStatement = conn.prepareStatement(req);
        preparedStatement.setString(1, C.getDelaisLivraison());
        preparedStatement.setDouble(2, C.getFraisdePort());
        preparedStatement.setDouble(3, C.getMontant());
        preparedStatement.setString(4, C.getRefCommande());

        int rowsUpdated = preparedStatement.executeUpdate();

        if (rowsUpdated > 0) {
            System.out.println("Commande modifiée avec succès");
        } else {
            System.out.println("La commande avec la référence " + C.getRefCommande() + " n'a pas été trouvée.");
        }
    } catch (SQLException ex) {
        System.out.println("Erreur lors de la modification de la commande : " + ex.getMessage());
    }
}

   public void supprimercommande(String refCommande) {
    try {
        String req = "DELETE FROM commande WHERE refCommande = ?";
        PreparedStatement preparedStatement = conn.prepareStatement(req);
        preparedStatement.setString(1, refCommande);
        int rowsDeleted = preparedStatement.executeUpdate();

        if (rowsDeleted > 0) {
            System.out.println("Commande supprimée !");
        } else {
            System.out.println("La commande avec la référence " + refCommande + " n'a pas été trouvée.");
        }
    } catch (SQLException ex) {
        System.out.println("Erreur lors de la suppression de la commande : " + ex.getMessage());
    }
}
 

//    public void supprimercommande( String refCommande) {
//     
//          
//               try {
//            String req = "DELETE FROM `commande` WHERE refCommande = " + refCommande;
//            Statement st = conn.createStatement();
//            st.executeUpdate(req);
//            System.out.println("Commande deleted !");
//        } catch (SQLException ex) {
//            System.out.println(ex.getMessage());
//        }
//
//    }
        public List<Commande> afficherCommande() {
      //  obList.clear();

       List<Commande> list = new ArrayList<>();
        try {
            String req = "Select * from commande";
            Statement st = conn.createStatement();
         
            ResultSet RS= st.executeQuery(req);
            while(RS.next()){

             Commande A = new Commande();
                          A.setRefCommande(RS.getString("refCommande"));

             A.setDelaisLivraison(RS.getString("delaisLivraison"));
             A.setRefCommande(RS.getString("refCommande"));
             A.setFraisdePort(RS.getDouble("FraisdePort"));
             A.setMontant(RS.getDouble("montant"));
             list.add(A);
             
            }
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }

        return list;
        
        
    }
        
        
                public ArrayList<Commande> read() {
        ArrayList<Commande> tab = new ArrayList<>();
        String req = "SELECT * FROM `Commande`;";
        try {   
            Statement ste = cnx.createStatement();
            ResultSet result = ste.executeQuery(req);
            while (result.next()) {
                String refCommande = result.getString(1);
                String delaisLivraison = result.getString(2);
                double FraisdePort=result.getDouble(3);
                double montant=result.getDouble(4);
                Commande c = new Commande(refCommande, delaisLivraison,FraisdePort,montant);
             
                tab.add(c); 
                 
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally{
             return tab;
        }
                }
        
        
            ObservableList<Commande>obList = FXCollections.observableArrayList();

           public ObservableList<Commande> afficherCommande2() {
        String sql = "SELECT * FROM commande";
        List<Commande> listeCatg = new ArrayList<>();

        try {
            Statement statement = cnx.createStatement();
            ResultSet result = statement.executeQuery(sql);
            while (result.next()) {
                String refCommande = result.getString(1);
                String delaisLivraison = result.getString(2);
                double FraisdePort=result.getDouble(3);
                double montant=result.getDouble(4);
                Commande c = new Commande(refCommande, delaisLivraison,FraisdePort,montant);
                obList.add(c);
            }
        } catch (SQLException ex) {
            System.out.println(ex);
        }
        return obList;
    }
               
    public ArrayList<Commande> rechercheCommand( String x, Integer a) {
         ArrayList<Commande> part = new ArrayList<>();
        String req = "select * from Commande where  refCommand="+a+" and montant like "+x;
        System.out.println(req);
        PreparedStatement preparedStatement;
        try {
            preparedStatement = cnx.prepareStatement(req);
           // preparedStatement.setInt(1, r);
       
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
               Commande pa;
               pa = new Commande(resultSet.getString("refCommande"), resultSet.getString("delaisLivraison"),resultSet.getDouble("FraisdePort"), resultSet.getDouble("montant"));
                System.out.println(pa);
                part.add(pa);
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return part;
    }
       public boolean rech(int a, int x) throws SQLException{
           Statement stmt = cnx.createStatement();
         ResultSet RS = stmt.executeQuery("select * from Commande where refCommand="+x);
         if (RS.next())
         {//System.out.println("True");
             return true ;
         
         }
         //System.out.println("False");
         return false ;
       }
                
 public ArrayList<Commande> afficheroneacount(String refCommand) throws SQLException {
    ArrayList<Commande> commandes = new ArrayList<Commande>();
    String request = "SELECT * FROM Commande WHERE refCommand = '" + refCommand + "'";
    try {
        ResultSet rs = ste.executeQuery(request);
        while (rs.next()) {
            String delaisLivraison = rs.getString(2);
            double FraisdePort = rs.getDouble(3);
            double montant = rs.getDouble(4);
            Commande p = new Commande(refCommand, delaisLivraison, FraisdePort, montant);
            commandes.add(p);
        }
    } catch (SQLException ex) {
        ex.printStackTrace();
    }
    return commandes;
}



}




       

   
   

    







          
   
    