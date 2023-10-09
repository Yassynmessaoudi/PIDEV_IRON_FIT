/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package models;

import java.time.LocalDate;

public class Abonnement {
    private int id;
    private String type;
    private LocalDate dateDebut; 
    private LocalDate dateFin;   
    private double prix;
    private int idSalleDeSport ;
    private SalleDeSport SalleDeSport ;
    public Abonnement() {
    }

    public Abonnement(String type, double prix) {
        this.type = type;
        this.prix = prix;
    }
    

    public Abonnement(int id, String type, LocalDate dateDebut, LocalDate dateFin, double prix) {
        this.id = id;
        this.type = type;
        this.dateDebut = dateDebut;
        this.dateFin = dateFin;
        this.prix = prix;
    }

    public Abonnement(String type, LocalDate dateDebut, LocalDate dateFin, double prix) {
        this.type = type;
        this.dateDebut = dateDebut;
        this.dateFin = dateFin;
        this.prix = prix;
    }

    // Getters and Setters

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public LocalDate getDateDebut() {
        return dateDebut;
    }

    public void setDateDebut(LocalDate dateDebut) {
        this.dateDebut = dateDebut;
    }

    public LocalDate getDateFin() {
        return dateFin;
    }

    public void setDateFin(LocalDate dateFin) {
        this.dateFin = dateFin;
    }

    public double getPrix() {
        return prix;
    }

    public void setPrix(double prix) {
        this.prix = prix;
    }

    public int getIdSalleDeSport() {
        return idSalleDeSport;
    }

    public void setIdSalleDeSport(int idSalleDeSport) {
        this.idSalleDeSport = idSalleDeSport;
    }

    public SalleDeSport getSalleDeSport() {
        return SalleDeSport;
    }

    public void setSalleDeSport(SalleDeSport SalleDeSport) {
        this.SalleDeSport = SalleDeSport;
    }

  

    @Override
    public String toString() {
        return "Abonnement{" +
                "id=" + id +
                ", type='" + type + '\'' +
                ", dateDebut=" + dateDebut +
                ", dateFin=" + dateFin +
                ", prix=" + prix +
                '}';
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 37 * hash + this.id;
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final Abonnement other = (Abonnement) obj;
        if (this.id != other.id) {
            return false;
        }
        return true;
    }
    

    
}
