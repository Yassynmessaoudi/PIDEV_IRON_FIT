/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entities;
import java.util.Objects;
/**
 *
 * @author gheri
 */
public class Commande {
    
    
    private int refCommande;
    private String delaisLivraison;
     private double FraisdePort;
    private double montant;

    public Commande(int refCommande, String delaisLivraison, double FraisdePort, double montant) {
        this.refCommande = refCommande;
        this.delaisLivraison = delaisLivraison;
        this.FraisdePort = FraisdePort;
        this.montant = montant;
    }

    public Commande(String delaisLivraison, double FraisdePort, double montant) {
        this.delaisLivraison = delaisLivraison;
        this.FraisdePort = FraisdePort;
        this.montant = montant;
    }

    public Commande() {
    }


    

    public int getRefCommande() {
        return refCommande;
    }

    public String getDelaisLivraison() {
        return delaisLivraison;
    }

    public double getFraisdePort() {
        return FraisdePort;
    }

    public double getMontant() {
        return montant;
    }

    public void setRefCommande(int refCommande) {
        this.refCommande = refCommande;
    }

    public void setDelaisLivraison(String delaisLivraison) {
        this.delaisLivraison = delaisLivraison;
    }

    public void setFraisdePort(double FraisdePort) {
        this.FraisdePort = FraisdePort;
    }

    public void setMontant(double montant) {
        this.montant = montant;
    }

    @Override
    public String toString() {
        return "commande{" + "refCommande=" + refCommande + ", delaisLivraison=" + delaisLivraison + ", FraisdePort=" + FraisdePort + ", montant=" + montant + '}';
    }
    
    

    
}
