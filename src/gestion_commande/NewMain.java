/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gestion_commande;

import entities.Commande;
import entities.Livraison;
import services.commandeService;
import services.livraisonService;

/**
 *
 * @author gheri
 */
public class NewMain {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
               commandeService c2= new commandeService(); //instance commande serivce

         livraisonService l2=new livraisonService();     //instance livraison service
     
         
//ajout commande 
   /*    Commande C1= new Commande("3 jours",40,20);

        c2.ajouterCommande(C1);
      */
      
        /*  modifier commande
Commande C1= new Commande (1,"1 jours",300,200);
 c2.modifier(C1);    }*/
        
        
        
        
        //afficher commande 
        /*
         
System.out.println( c2.afficherCommande());*/
        
        
        //supprimer commande 
//c2.supprimercommande(1);
       
    
        
         
         //afficher livraison
     
      //  System.out.println( l2.afficher());
      /*
      //ajouter livrasion
Livraison  L1 =new Livraison("18/10/2023");
l2.ajouterLivraison(L1);
        */
      
      //supprimer livraison
   //   l2.supprimerLivraison(1);
   //modifier livraison
    //  Livraison  L1 =new Livraison(2,"18/10/2003");
     //l2.modifierLivraison(L1);
      
        
}

}