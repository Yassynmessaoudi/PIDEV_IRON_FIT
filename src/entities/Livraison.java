/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entities;

/**
 *
 * @author gheri
 */
public class Livraison {
    private int ref_livraison;
   private String date_livraison;
        private int refCommande;

    @Override
    public String toString() {
        return "Livraison{" + "ref_livraison=" + ref_livraison + ", date_livraison=" + date_livraison + ", refCommande=" + refCommande + '}';
    }

  

    public Livraison(int ref_livraison, String date_livraison) {
        this.ref_livraison = ref_livraison;
        this.date_livraison = date_livraison;
        
    }

    public Livraison(int ref_livraison, String date_livraison, int refCommande) {
        this.ref_livraison = ref_livraison;
        this.date_livraison = date_livraison;
        this.refCommande = refCommande;
    }

    public Livraison(String date_livraison, int refCommande) {
        this.date_livraison = date_livraison;
        this.refCommande = refCommande;
    }

    public void setRefCommande(int refCommande) {
        this.refCommande = refCommande;
    }

    public int getRefCommande() {
        return refCommande;
    }

    public Livraison(String date_livraison) {
        this.date_livraison = date_livraison;
    }

    public Livraison() {
    }

  
    
    
    
    public void setRef_livraison(int ref_livraison) {
        this.ref_livraison = ref_livraison;
    }

    public void setDate_livraison(String date_livraison) {
        this.date_livraison = date_livraison;
    }
    
    
    

    public int getRef_livraison() {
        return ref_livraison;
    }

    public String getDate_livraison() {
        return date_livraison;
    }
}
