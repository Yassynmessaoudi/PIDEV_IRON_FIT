/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package services;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

import models.Abonnement;
import util.DataSource;

public class ServicesAbonnement implements IServiceAbonnement<Abonnement> {
     Connection cnx; 
 Statement ste;
  PreparedStatement preparedStatement;
    public ServicesAbonnement() {
        this.cnx= DataSource.getInstance().getConnection();
    }

    @Override
   // String req = "INSERT INTO `abonnement`(`id`, `type`, `dateDebut`, `dateFin`, `prix`, `idSalleDeSport`) VALUES (?, ?, ?, ?, ?, ?)";
 public void ajouterAbonnement(Abonnement ab, String nomSalleDeSport) {
    String req = "INSERT INTO `abonnement`(`id`, `type`, `dateDebut`, `dateFin`, `prix`, `idSalleDeSport`) " +
                 "VALUES (?, ?, ?, ?, ?, " +
                 "(SELECT `idSalleDeSport` FROM `SalleDeSport` WHERE `nom` = ? LIMIT 1))";
    try {
        PreparedStatement ps = cnx.prepareStatement(req);
        ps.setInt(1, ab.getId());
        ps.setString(2, ab.getType());
        ps.setDate(3, Date.valueOf(ab.getDateDebut()));
        ps.setDate(4, Date.valueOf(ab.getDateFin()));
        ps.setDouble(5, ab.getPrix());
        ps.setString(6, nomSalleDeSport); // Utilisez le nom de la salle de sport ici
        ps.executeUpdate();
        System.out.println("Abonnement ajouté avec succès !");
    } catch (SQLException ex) {
        System.out.println(ex.getMessage());
    }
}



   public int getIdSalleDeSportByNom(String nomSalleDeSport) {
    String req = "SELECT idSalleDeSport FROM SalleDeSport WHERE nom = ?";
    try {
        PreparedStatement ps = cnx.prepareStatement(req);
        ps.setString(1, nomSalleDeSport);
        ResultSet rs = ps.executeQuery();
        if (rs.next()) {
            return rs.getInt("idSalleDeSport");
        }
    } catch (SQLException ex) {
        System.out.println(ex.getMessage());
    }
    return -1; // Retourne -1 si le nom de la salle de sport n'est pas trouvé
}

public void associerAbonnementASalleDeSport(Abonnement abonnement, String nomSalleDeSport) {
    // Recherchez l'ID de la salle de sport en fonction du nom
    int idSalleDeSport = getIdSalleDeSportByNom(nomSalleDeSport);
    
    if (idSalleDeSport != -1) {
        // Si l'ID de la salle de sport est trouvé, associez-le à l'abonnement
        abonnement.setIdSalleDeSport(idSalleDeSport);
    } else {
        // Gérez le cas où le nom de la salle de sport n'est pas trouvé
        System.out.println("Salle de sport '" + nomSalleDeSport + "' non trouvée.");
    }
}

  

    



   @Override
     public void modifierAbonnement(Abonnement ab) {
    String req = "UPDATE `abonnement` SET `type` = ?, `dateDebut` = ?, `dateFin` = ?, `prix` = ?, `idSalleDeSport` = ? WHERE `id` = ?";
    try {
        PreparedStatement ps = cnx.prepareStatement(req);
        ps.setString(1, ab.getType());
        ps.setDate(2, Date.valueOf(ab.getDateDebut()));
        ps.setDate(3, Date.valueOf(ab.getDateFin()));
        ps.setDouble(4, ab.getPrix());
        ps.setInt(5, ab.getIdSalleDeSport()); // Ajoutez l'ID de la salle de sport
        ps.setInt(6, ab.getId());
        ps.executeUpdate();
        System.out.println("Abonnement modifié avec succès!");
    } catch (SQLException ex) {
        System.out.println(ex.getMessage());
    }
     }
     @Override
     public void supprimerAbonnement(int id, int idSalleDeSport) {
    String req = "DELETE FROM `abonnement` WHERE `id` = ? AND `idSalleDeSport` = ?";
    try {
        PreparedStatement ps = cnx.prepareStatement(req);
        ps.setInt(1, id);
        ps.setInt(2, idSalleDeSport);
        int rowCount = ps.executeUpdate();
        
        if (rowCount > 0) {
            System.out.println("Abonnement supprimé avec succès!");
        } else {
            System.out.println("Aucun abonnement trouvé avec l'ID " + id + " et l'ID de salle de sport " + idSalleDeSport);
        }
    } catch (SQLException ex) {
        System.out.println(ex.getMessage());
    }
}
    
   



     @Override
     public List<Abonnement> afficherAbonnement() throws SQLException {
    List<Abonnement> abonnements = new ArrayList<>();
    
    try {
         preparedStatement = cnx.prepareStatement("" + "SELECT * FROM Abonnement AS a" + "INNER JOIN salledesport AS s ON a.idSalleDeSport = s.idSalleDeSport");
        ResultSet resultSet = preparedStatement.executeQuery();
        while (resultSet.next()) {
            Abonnement ab = new Abonnement();
            ab.setType(resultSet.getString("type"));
            ab.setDateDebut(resultSet.getDate("DateDebut").toLocalDate());
            ab.setDateFin(resultSet.getDate("DateFin").toLocalDate());
            ab.setPrix(resultSet.getDouble("Prix"));
            ab.setIdSalleDeSport(resultSet.getInt("idSalleDeSport")); 
            abonnements.add(ab);
        }
    } catch (SQLException exception) {
            System.out.println("Error (recuperer) Abonnement : " + exception.getMessage());
    }finally {
        if (preparedStatement != null) {
            preparedStatement.close();
        }
    return abonnements;
}
/*
         @Override
     public Abonnement getOneAbonnement(Abonnement ab) {
    Abonnement resultat = null;
    String req = "SELECT * FROM `abonnement` WHERE `id` = ?";
    try {
        PreparedStatement ps = cnx.prepareStatement(req);
        ps.setInt(1, ab.getId());
        ResultSet rs = ps.executeQuery();
        if (rs.next()) {
            resultat = new Abonnement();
            resultat.setId(rs.getInt(1));
            resultat.setType(rs.getString("Type"));
            resultat.setDateDebut(rs.getObject("dateDebut", LocalDate.class));
            resultat.setDateFin(rs.getObject("dateFin", LocalDate.class));
            resultat.setPrix(rs.getFloat("prix"));
            resultat.setIdSalleDeSport(rs.getInt("idSalleDeSport")); // Ajoutez cette ligne pour récupérer idSalleDeSport
        }
    } catch (SQLException ex) {
        System.out.println(ex.getMessage());
    }
    return resultat;
}
public List<Abonnement> getAllAbonnement() {
    String req = "SELECT * FROM `abonnement`";
    ArrayList<Abonnement> Abonnements = new ArrayList<>();
    try {
        PreparedStatement ps = cnx.prepareStatement(req);
        ResultSet rs = ps.executeQuery();
        while (rs.next()) {
            Abonnement abonnement = new Abonnement();
            abonnement.setId(rs.getInt("id"));
            abonnement.setType(rs.getString("type"));
            abonnement.setDateDebut(rs.getObject("dateDebut", LocalDate.class));
            abonnement.setDateFin(rs.getObject("dateFin", LocalDate.class));
            abonnement.setPrix(rs.getDouble("prix"));
            abonnement.setIdSalleDeSport(rs.getInt("idSalleDeSport")); // Ajout de l'ID de salle de sport

            Abonnements.add(abonnement);
        }
    } catch (SQLException ex) {
        System.out.println(ex.getMessage());
    }
    return Abonnements;
}
    
    public List<Abonnement> afficherAbonnement(){
        List<Abonnement> abonnements = new ArrayList<>();
         //1
         String req = "SELECT * FROM Abonnement";
        try {
            //2
            Statement st = cnx.createStatement();
            //3
            ResultSet rs = st.executeQuery(req);
            while (rs.next()) {
                Abonnement ab = new Abonnement();
                ab.setType(rs.getString("type"));
                ab.setDateDebut(rs.getDate("DateDebut"));
                ab.setDateFin(rs.getDate("DateFin"));
                ab.setPrix(rs.getDouble("Prix"));
                abonnements.add(ab);
            }
            
            
        } catch (SQLException ex) {
            Logger.getLogger(services.class.getName()).log(Level.SEVERE, null, ex);
        }
         
         return abonnements;
    }*/

   






   /* @Override
    public void supprimerAbonnement(int id) {
        String req = "DELETE FROM `abonnement` WHERE `id` = ?";
        try {
            PreparedStatement ps = cnx.prepareStatement(req);
            ps.setInt(1, id);
            ps.executeUpdate();
            System.out.println("Abonnement supprimé avec succès!");
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
    }

    @Override
    public Abonnement getOneAbonnement(Abonnement ab) {
        Abonnement resultat = null;
        String req = "SELECT * FROM `abonnement` WHERE `id` = ?";
        try {
            PreparedStatement ps = cnx.prepareStatement(req);
            ps.setInt(1, ab.getId());
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                resultat = new Abonnement();
                resultat.setId(rs.getInt(1));
                resultat.setType(rs.getString("Type"));
                resultat.setDateDebut(rs.getObject("dateDebut", LocalDate.class));
                resultat.setDateFin(rs.getObject("dateFin", LocalDate.class));
                resultat.setPrix(rs.getFloat("prix"));
            }
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
        return resultat;
    }

    @Override
    public List<Abonnement> getAllAbonnement(Abonnement ab) {
        String req = "SELECT * FROM `abonnement`";
        ArrayList<Abonnement> Abonnements = new ArrayList<>();
        try {
          ste   = cnx.createStatement();
            ResultSet rs = ste.executeQuery(req);
            while (rs.next()) {
               Abonnements.add(
                       new Abonnement(
               rs.getInt("id"),rs.getString("type"),LocalDate.parse(String.valueOf(rs.getDate("datedebut"))),LocalDate.parse(String.valueOf(rs.getDate("dateFin")))
,rs.getInt("prix")
                ));
            }
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
        return Abonnements;
    }
    public Abonnement getOneAbonnementById(int id) {
    String req = "SELECT * FROM abonnement WHERE id = ?";
    try {
        PreparedStatement ps = cnx.prepareStatement(req);
        ps.setInt(1, id);
        ResultSet rs = ps.executeQuery();
        if (rs.next()) {
            // Créer un nouvel objet Abonnement avec les données de la base de données
            int abonnementId = rs.getInt("id");
            String type = rs.getString("type");
            LocalDate dateDebut = rs.getDate("dateDebut").toLocalDate();
            LocalDate dateFin = rs.getDate("dateFin").toLocalDate();
            double prix = rs.getDouble("prix");
            
            return new Abonnement(abonnementId, type, dateDebut, dateFin, prix);
        }
    } catch (SQLException ex) {
        System.out.println(ex.getMessage());
    }
    return null; // Retourne null si l'abonnement n'est pas trouvé ou s'il y a une erreur
}
}


      /*public void ajouterAbonnement2(Abonnement ab){
        String req = "INSERT INTO `Abonnement`(`type`, `dateDebut`, `dateFin` ,`prix` ) VALUES (?,?,?,?)";
        try {
            PreparedStatement ps = cnx.prepareStatement(req);
            ps.setString(1, ab.getType());
            ps.setDate(2, (Date) ab.getDateDebut());
            ps.setDate(3, (Date) ab.getDateFin());
            ps.setDouble(4,ab.getPrix());                
            ps.executeUpdate();
            System.out.println("Personne ajoutée avec succes!");
        } catch (SQLException ex) {
            Logger.getLogger(services.class.getName()).log(Level.SEVERE, null, ex);
        }
    }*/
     
     
    /* public void ajouterSalleDeSport(SalleDeSport ss){
        String req = "INSERT INTO `SalleDeSport`(`nom`, `adresse`, `capacite` , `specialite`) VALUES (?,?,?,?)";
        try {
            PreparedStatement ps = cnx.prepareStatement(req);
            ps.setString(1, ss.getNom());
            ps.setString(2, ss.getAdresse());
            ps.setInt(3, ss.getCapacite());
            ps.setString(4, ss.getSpecialite());
            ps.executeUpdate();
            System.out.println("Salle de sport ajoutée avec succes!");
        } catch (SQLException ex) {
            Logger.getLogger(services.class.getName()).log(Level.SEVERE, null, ex);
        }
    }*/
    
     /*public List<Abonnement> afficherAbonnement(){
        List<Abonnement> abonnements = new ArrayList<>();
         //1
         String req = "SELECT * FROM Abonnement";
        try {
            //2
            Statement st = cnx.createStatement();
            //3
            ResultSet rs = st.executeQuery(req);
            while (rs.next()) {
                Abonnement ab = new Abonnement();
                ab.setType(rs.getString("type"));
                ab.setDateDebut(rs.getDate("DateDebut"));
                ab.setDateFin(rs.getDate("DateFin"));
                ab.setPrix(rs.getDouble("Prix"));
                abonnements.add(ab);
            }
            
            
        } catch (SQLException ex) {
            Logger.getLogger(services.class.getName()).log(Level.SEVERE, null, ex);
        }
         
         return abonnements;
    }*/
     
    /* public List<SalleDeSport> afficherSalleDeSport(){
        List<SalleDeSport> SalleDeSportt = new ArrayList<>();
         //1
         String req = "SELECT * FROM salledesport";
        try {
            //2
            Statement st = cnx.createStatement();
            //3
            ResultSet rs = st.executeQuery(req);
            while (rs.next()) {
                SalleDeSport ss = new SalleDeSport();
                ss.setNom(rs.getString("nom"));
                ss.setAdresse(rs.getString("Adresse"));
                ss.setCapacite(rs.getInt("capacite"));
                ss.setSpecialite(rs.getString("specialite"));
                SalleDeSportt.add(ss);
            }
            
            
        } catch (SQLException ex) {
            Logger.getLogger(services.class.getName()).log(Level.SEVERE, null, ex);
        }
         
         return SalleDeSportt;
    }*/
     }}
   
     
     
     

