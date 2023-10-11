/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package services;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import models.SalleDeSport;
import util.DataSource;

/**
 *
 * @author tlili
 */
public class ServicesSalleDeSport implements IServiceSalleDeSport<SalleDeSport> {
    Connection cnx; 

    public ServicesSalleDeSport() {
        this.cnx= DataSource.getInstance().getConnection();
    }

  

   @Override
public void ajouterSalleDeSport(SalleDeSport ss) {
    // Fixez l'identifiant (id) à 0
    ss.setId(0);
    
    String req = "INSERT INTO `Salledesport`(`idSalleDeSport`, `nom`, `adresse`, `capacite`, `specialite`) VALUES (?, ?, ?, ?, ?)";
    try {
        PreparedStatement ps = cnx.prepareStatement(req);
        ps.setInt(1, ss.getId());
        ps.setString(2, ss.getNom());
        ps.setString(3, ss.getAdresse());
        ps.setInt(4, ss.getCapacite());
        ps.setString(5, ss.getSpecialite());
        ps.executeUpdate();
        System.out.println("Salle De Sport ajoutée avec succès!");
    } catch (SQLException ex) {
        System.out.println(ex.getMessage());
    }
}

    

    
    @Override
    public void modifierSalleDeSport(SalleDeSport ss) {
        String req = "UPDATE `Salledesport` SET `nom` = ?, `adresse` = ?, `capacite` = ?, `specialite` = ? WHERE `idSalleDeSport` = ?";
        try {
            PreparedStatement ps = cnx.prepareStatement(req);
            ps.setInt(1, ss.getId());
            ps.setString(2, ss.getNom());
            ps.setString(3, ss.getAdresse());
            ps.setInt(4, ss.getCapacite());
            ps.setString(5, ss.getSpecialite());
            ps.executeUpdate();
            System.out.println("Salle De Sport modifié avec succès!");
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
    }

    
    @Override
    public void supprimerSalleDeSport(int id) {
        String req = "DELETE FROM `Salledesport` WHERE `idSalleDeSport` = ?";
        try {
            PreparedStatement ps = cnx.prepareStatement(req);
            ps.setInt(1, id);
            ps.executeUpdate();
            System.out.println("Salle De Sport supprimé avec succès!");
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
    }

   
    @Override
    public SalleDeSport getOneSalleDeSport(SalleDeSport ss) {
        SalleDeSport resultat = null;
        String req = "SELECT * FROM `Salledesport` WHERE `idSalleDeSport` = ?";
        try {
            PreparedStatement ps = cnx.prepareStatement(req);
            ps.setInt(1, ss.getId());
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                resultat = new SalleDeSport();
                resultat.setId(rs.getInt(1));
                resultat.setNom(rs.getString("Nom"));
                resultat.setAdresse(rs.getString("Adresse"));
                resultat.setCapacite(rs.getInt(4));
                resultat.setSpecialite(rs.getString("Specialite"));
                
            }
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
        return resultat;
    }

 
    @Override
    public List<SalleDeSport> getAllSalleDeSport()  {
        String req = "SELECT * FROM `Salledesport`";
        ArrayList<SalleDeSport> SalleDeSports = new ArrayList<>();
        try {
            PreparedStatement ps = cnx.prepareStatement(req);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                SalleDeSport sss = new SalleDeSport();
                sss.setId(rs.getInt(1));
                sss.setNom(rs.getString("Nom"));
                sss.setAdresse(rs.getString("Adresse"));
                sss.setCapacite(rs.getInt(4));
                sss.setSpecialite(rs.getString("Specialite"));
                SalleDeSports.add(sss);
            }
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
        return SalleDeSports;
}
    public SalleDeSport getOneSalleDeSportById(int id) {
        SalleDeSport resultat = null;
        String req = "SELECT * FROM `Salledesport` WHERE `idSalleDeSport` = ?";
        try {
            PreparedStatement ps = cnx.prepareStatement(req);
            ps.setInt(1, id);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                resultat = new SalleDeSport();
                resultat.setId(rs.getInt(1));
                resultat.setNom(rs.getString("Nom"));
                resultat.setAdresse(rs.getString("Adresse"));
                resultat.setCapacite(rs.getInt(4));
                resultat.setSpecialite(rs.getString("Specialite"));
            }
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
        return resultat;
    }
}
