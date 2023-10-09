/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package models;

/**
 *
 * @author tlili
 */
public class SalleDeSport {
     private int idSalleDeSport;
     private String nom;
     private String adresse;
     private int capacite;
     private String specialite;

    public SalleDeSport() {
    }

    public SalleDeSport(int id, String nom, String adresse, int capacite, String specialite) {
        this.idSalleDeSport = id;
        this.nom = nom;
        this.adresse = adresse;
        this.capacite = capacite;
        this.specialite = specialite;
    }

    public SalleDeSport(String nom, String adresse, int capacite, String specialite) {
        this.nom = nom;
        this.adresse = adresse;
        this.capacite = capacite;
        this.specialite = specialite;
    }

    public int getId() {
        return idSalleDeSport;
    }

    public void setId(int id) {
        this.idSalleDeSport = id;
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public String getAdresse() {
        return adresse;
    }

    public void setAdresse(String adresse) {
        this.adresse = adresse;
    }

    public int getCapacite() {
        return capacite;
    }

    public void setCapacite(int capacite) {
        this.capacite = capacite;
    }

    public String getSpecialite() {
        return specialite;
    }

    public void setSpecialite(String specialite) {
        this.specialite = specialite;
    }

    @Override
    public String toString() {
        return "SalleDeSport{" + "id=" + idSalleDeSport + ", nom=" + nom + ", adresse=" + adresse + ", capacite=" + capacite + ", specialite=" + specialite + '}';
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 97 * hash + this.idSalleDeSport;
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
        final SalleDeSport other = (SalleDeSport) obj;
        if (this.idSalleDeSport != other.idSalleDeSport) {
            return false;
        }
        return true;
    }
 
}
