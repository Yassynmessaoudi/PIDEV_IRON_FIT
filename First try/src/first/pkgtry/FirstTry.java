/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package first.pkgtry;


import java.io.IOException;
import java.sql.SQLException;
import java.time.LocalDate;
import models.Abonnement;
import services.ServicesAbonnement;
import java.util.List;
import javafx.stage.Stage;
import models.SalleDeSport;
import services.ServicesSalleDeSport;
import util.DataSource;

/**
 *
 * @author tlili
 */
public class FirstTry {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) throws SQLException {
  // TODO code application logic here
  
        
         DataSource.getInstance();
         DataSource.getInstance();
         DataSource.getInstance();
         DataSource.getInstance();
         DataSource.getInstance();
    
        
         
   
       // Créez une instance du service
    ServicesSalleDeSport service = new ServicesSalleDeSport();

    // Créez un objet SalleDeSport avec les détails que vous souhaitez mettre à jour
    SalleDeSport salleAModifier = new SalleDeSport();
    salleAModifier.setId(1); // Remplacez par l'ID de la salle que vous voulez modifier
    salleAModifier.setNom("Nouveau nom");
    salleAModifier.setAdresse("Nouvelle adresse");
    salleAModifier.setCapacite("Nouvelle capacité");
    salleAModifier.setSpecialite("Nouvelle spécialité");

    // Appelez la méthode pour modifier la salle de sport
    service.modifierSalleDeSport(salleAModifier);

    ServicesAbonnement serviceAbonnements = new ServicesAbonnement();

    // Appelez la méthode afficherAbonnement
    List<Abonnement> abonnements = serviceAbonnements.afficherAbonnement();

    // Parcourez la liste des abonnements et affichez-les
    for (Abonnement abonnement : abonnements) {
       System.out.println(abonnement);
  }

         
// Ajouter l'abonnement
  
// Créez un nouvel abonnement en spécifiant d'autres informations nécessaires
String nomSalleDeSport = "Iron Fit";
Abonnement abonnement = new Abonnement();
abonnement.setType("YASSINE");
abonnement.setDateDebut("13/12/2012");
abonnement.setDateFin("13/12/2022");
abonnement.setPrix(55.55);

// Ajoutez cet abonnement à la base de données en spécifiant le nom de la salle de sport
serviceAbonnements.ajouterAbonnement(abonnement, nomSalleDeSport);

        
        
//Modifier
Abonnement abonnement1 = new Abonnement();
abonnement1.setId(15); 
abonnement1.setType("Nouveau Type");
abonnement1.setDateDebut("13/12/2012");
abonnement1.setDateFin("2023, 3, 4");
abonnement1.setPrix(99.99);
abonnement1.setIdSalleDeSport(7); 
serviceAbonnements.modifierAbonnement(abonnement1);
      

    // suppression
    int idAbonnement = 15; 
    int idSalleDeSport = 7; 

    // $supprimer l'abonnement
   serviceAbonnements.supprimerAbonnement(idAbonnement, idSalleDeSport);

   
   
   
   
   
   
   
    // Ajouter salle de sport
        SalleDeSport salledesport = new SalleDeSport ("Iron Fit","Aana km3", "50 ","Body Builder" );
        ServicesSalleDeSport services = new ServicesSalleDeSport();
        services.ajouterSalleDeSport(salledesport);
        // Modifiez l'abonnement
        salledesport.setNom("Iron Fit");
    //    services.modifierSalleDeSport(salledesport);
        //System.out.println("Salle de sport modifié avec succès!");
        // Supprimer un abonnement par ID
        int idsuppp= 6;
        if (services.getOneSalleDeSportById(idsuppp) != null) {
        services.supprimerSalleDeSport(idsuppp);
        System.out.println("Salle De Sport avec l'ID " + idsuppp + " supprimé avec succès!");
        } else {
        System.out.println("Salle De Sport avec l'ID " + idsuppp + " n'existe pas.");
        }   
        // Recherchez un abonnement par ID
        SalleDeSport SalleDeSportRecherche = services.getOneSalleDeSportById(110);
        if (SalleDeSportRecherche != null) {
        System.out.println("SalleDeSport trouvé : " + SalleDeSportRecherche);
        } else {
        System.out.println("Salle De Sport non trouvé.");
        }
        // Affichez tous les Salle de Sport
        List<SalleDeSport> tousLesSalleDeSport = services.getAllSalleDeSport();
        System.out.println("Tous les Salle De Sport :");
        for (SalleDeSport ss : tousLesSalleDeSport) {
        System.out.println(ss);
        }
        
    }
    
}
   
   
   
   
   
   
   
   
   
   

   // Créez un objet Abonnement avec l'ID que vous souhaitez récupérer 
   //abonnement.setId(12); // Remplacez 1 par l'ID que vous souhaitez récupérer

    // Appelez la méthode getOneAbonnement pour récupérer les données
   /* Abonnement rs = serviceAbonnements.getOneAbonnement(abonnement);

    // Vérifiez si les données ont été récupérées avec succès
    if (rs != null) {
        // Affichez les données récupérées
        System.out.println("ID: " + rs.getId());
        System.out.println("Type: " + rs.getType());
        System.out.println("Date de début: " + rs.getDateDebut());
        System.out.println("Date de fin: " + rs.getDateFin());
        System.out.println("Prix: " + rs.getPrix());
        System.out.println("ID de salle de sport: " + rs.getIdSalleDeSport());
    } else {
        System.out.println("Aucun abonnement trouvé avec cet ID.");
    }
 */
     // Instanciez votre service d'abonnement et établissez la connexion à la base de données
       

        // Appelez la méthode getAllAbonnement pour récupérer la liste de tous les abonnements
       // List<Abonnement> abonnements = serviceAbonnements.getAllAbonnement();
     
        // Parcourez la liste des abonnements et affichez les détails de chaque abonnement
      //  for (Abonnement ab : abonnements) {
       //     System.out.println("ID: " + ab.getId());
       //     System.out.println("Type: " + ab.getType());
       //     System.out.println("Date de début: " + LocalDate.now());
       //     System.out.println("Date de fin: " + dateFin4);
        //    System.out.println("Prix: " + ab.getPrix());
        //    System.out.println("ID de salle de sport: " + ab.getIdSalleDeSport());
       //     System.out.println("----------------------------------");
      //  }

        
        
    //}
   
//}
    // Fermez votre connexion à la base de données ici (si nécessaire)        
        
        
        
        
        
        
    /*  Abonnement abonnementMODIFIER = new Abonnement("MSS", LocalDate.now(), dateFin, 55.55);
    serviceAbonnement.modifierAbonnement(abonnementMODIFIER);
        // Supprimer un abonnement par ID
int idsupp = 3;
if (serviceAbonnement.getOneAbonnementById(idsupp) != null) {
serviceAbonnement.supprimerAbonnement(idsupp);
    System.out.println("Abonnement avec l'ID " + idsupp + " supprimé avec succès!");
} else {
    System.out.println("L'abonnement avec l'ID " + idsupp + " n'existe pas.");
}
        // Recherchez un abonnement par ID
Abonnement abonnementRecherche = serviceAbonnement.getOneAbonnementById(0);
if (abonnementRecherche != null) {
    System.out.println("Abonnement trouvé : " + abonnementRecherche);
} else {
    System.out.println("Abonnement non trouvé.");
     }
    // Affichez tous les abonnements
List<Abonnement> tousLesAbonnements = serviceAbonnement.getAllAbonnement(abonnement);
    System.out.println("Tous les abonnements :");
for (Abonnement ab : tousLesAbonnements) {
    System.out.println(ab);
        }











    }}
        // Ajouter salle de sport
        SalleDeSport salledesport = new SalleDeSport ("Iron Fit","Aana km3", 50 ,"Buddy Builder" );
        ServicesSalleDeSport services = new ServicesSalleDeSport();
        services.ajouterSalleDeSport(salledesport);
        // Modifiez l'abonnement
        salledesport.setNom("Iron Fit");
        services.modifierSalleDeSport(salledesport);
        System.out.println("Salle de sport modifié avec succès!");
        // Supprimer un abonnement par ID
        int idsuppp= 4;
        if (services.getOneSalleDeSportById(idsuppp) != null) {
        services.supprimerSalleDeSport(idsuppp);
        System.out.println("Salle De Sport avec l'ID " + idsuppp + " supprimé avec succès!");
        } else {
        System.out.println("Salle De Sport avec l'ID " + idsuppp + " n'existe pas.");
        }   
        // Recherchez un abonnement par ID
        SalleDeSport SalleDeSportRecherche = services.getOneSalleDeSportById(110);
        if (SalleDeSportRecherche != null) {
        System.out.println("SalleDeSport trouvé : " + SalleDeSportRecherche);
        } else {
        System.out.println("Salle De Sport non trouvé.");
        }
        // Affichez tous les abonnements
        List<SalleDeSport> tousLesSalleDeSport = services.getAllSalleDeSport(salledesport);
        System.out.println("Tous les Salle De Sport :");
        for (SalleDeSport ss : tousLesSalleDeSport) {
        System.out.println(ss);
        }*/
       







       
    

    

