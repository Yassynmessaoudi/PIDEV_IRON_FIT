/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entities;

import java.sql.Date;

/**
 *
 * @author gheri
 */
public class Livraison {
    private int ref_livraison;
   private Date date_livraison;
        private String refCommande;

    @Override
    public String toString() {
        return "Livraison{" + "ref_livraison=" + ref_livraison + ", date_livraison=" + date_livraison + ", refCommande=" + refCommande + '}';
    }

  

    public Livraison(int ref_livraison, Date date_livraison) {
        this.ref_livraison = ref_livraison;
        this.date_livraison = date_livraison;
        
    }

    public Livraison(int ref_livraison, Date date_livraison, String refCommande) {
        this.ref_livraison = ref_livraison;
        this.date_livraison = date_livraison;
        this.refCommande = refCommande;
    }

    public Livraison(Date date_livraison, String refCommande) {
        this.date_livraison = date_livraison;
        this.refCommande = refCommande;
    }

    public void setRefCommande(String refCommande) {
        this.refCommande = refCommande;
    }

    public String getRefCommande() {
        return refCommande;
    }

    public Livraison(Date date_livraison) {
        this.date_livraison = date_livraison;
    }

    public Livraison() {
    }

  
    
    
    
    public void setRef_livraison(int ref_livraison) {
        this.ref_livraison = ref_livraison;
    }

    public void setDate_livraison(Date date_livraison) {
        this.date_livraison = date_livraison;
    }
    
    
    

    public int getRef_livraison() {
        return ref_livraison;
    }

    public Date getDate_livraison() {
        return date_livraison;
    }
}
