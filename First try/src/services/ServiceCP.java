/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Services;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import entite.CodePromo;
import tools.MyDB;

/**
 *
 * @author nasri
 */
public class ServiceCP {
   Connection con; 
    Statement ste;
    
    public ServiceCP() {
      con = MyDB.getinstance().getCon();  
    }


    public void ajoutercp(CodePromo cp) {
        try {
            String req = "INSERT INTO CodePromo(code, description,datedexpiration, used) VALUES ('"
                    + cp.getCode() + "','" + cp.getDescription() + "','" +cp.getDatedexpiration()+ "','"+ cp.isUsed() +"')";
            Statement stm = con.createStatement();
            stm.executeUpdate(req);
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
    }

    public void modifiercp(CodePromo cp) {
    String req = "UPDATE CodePromo SET code = ?, Description = ?, datedexpiration = ? WHERE id_codepromo = ?";
    try (PreparedStatement preparedStatement = con.prepareStatement(req)) {
        preparedStatement.setString(1, cp.getCode());
        preparedStatement.setString(2, cp.getDescription());
        preparedStatement.setString(3, cp.getDatedexpiration());
        preparedStatement.setInt(4, cp.getId_codepromo());
        preparedStatement.executeUpdate();
    } catch (SQLException ex) {
        Logger.getLogger(ServiceUser.class.getName()).log(Level.SEVERE, null, ex);
    }
}


   
  public void supprimercp(CodePromo cp) {
        String req = "DELETE FROM CodePromo WHERE id_codepromo =? " ;
    try (PreparedStatement ps = con.prepareStatement(req)) {
                ps.setInt(1, cp.getId_codepromo());
                ps.executeUpdate();
                System.out.println("CodePromo supprimé");
            
        } catch (SQLException ex) {
            Logger.getLogger(ServiceCP.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public CodePromo recherchercp(int id) {
         try {
            String req = "SELECT * FROM CodePromo WHERE idUtilisateur = " + id;
            try (Statement stm = con.createStatement()) {
                ResultSet rs = stm.executeQuery(req);
                if (rs.next()) {
                    CodePromo  codepromo = new CodePromo ();
                  codepromo.setId_codepromo(rs.getInt("Id_codepromo"));
                  codepromo.setCode(rs.getString("code"));
                  codepromo.setDescription(rs.getString("description"));
                    codepromo.setDescription(rs.getString("datedexpiration"));
                  codepromo.setUsed(rs.getString("Used"));
                  
                    return codepromo;
                }
            }
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
        return null;
    }

  public List<CodePromo> getAllcp() {
    String req = "SELECT * FROM CodePromo";
    ArrayList<CodePromo> codepromos = new ArrayList<>();
    
    try (Statement stm = con.createStatement();
         ResultSet rs = stm.executeQuery(req)) {
        while (rs.next()) {
            CodePromo codepromo = new CodePromo();
            codepromo.setId_codepromo(rs.getInt("Id_codepromo"));
            codepromo.setCode(rs.getString("code"));
            codepromo.setDescription(rs.getString("description"));
            codepromo.setDatedexpiration(rs.getString("datedexpiration"));
            codepromo.setUsed(rs.getString("Used"));
            codepromos.add(codepromo); // Ajouter le code promo à la liste
        }
    } catch (SQLException ex) {
        Logger.getLogger(ServiceCP.class.getName()).log(Level.SEVERE, null, ex);
    }

    return codepromos;
}
   public boolean verifiercp(String code) {
    String req = "SELECT COUNT(*) AS count FROM CodePromo WHERE code = '" + code + "'";
    
    try (Statement stm = con.createStatement();
         ResultSet rs = stm.executeQuery(req)) {
        if (rs.next()) {
            int count = rs.getInt("count");
            return count > 0;
        }
    } catch (SQLException ex) {
        Logger.getLogger(ServiceCP.class.getName()).log(Level.SEVERE, null, ex);
    }

    return false;
}
    
    
}
