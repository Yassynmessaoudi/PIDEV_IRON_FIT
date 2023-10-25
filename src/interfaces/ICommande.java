/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package interfaces;

import entities.Commande;
import java.sql.SQLException;
import java.util.List;

/**
 *
 * @author gheri
 */
public interface ICommande {
    
    public void ajouterCommand(Commande C);
    public void modifierCommand(Commande C);
    public void supprimerCommand(String refCommande);
    public List<Commande> afficherCommandes();
    public Commande getCommendByRef (String refCommande) throws SQLException;
}
