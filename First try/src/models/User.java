<<<<<<< HEAD
/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package models;

/**
 *
 * @author ANIS
 */

public class User {
    private int id_user,age;
    private String username,mail,mdp,image;
    private String role;
    private String sexe;

    public User(int id_user, String username, String mail, String mdp, String role, String image,int age, String sexe) {
        this.id_user = id_user;
        this.age = age;
        this.username = username;
        this.mail = mail;
        this.mdp = mdp;
        this.image = image;
        this.sexe = sexe;
        this.role = role;
    }

    public User( String username, String mail, String mdp, String role, String image,int age, String sexe) {
        this.age = age;
        this.username = username;
        this.mail = mail;
        this.mdp = mdp;
        this.image = image;
        this.sexe = sexe;
        this.role = role;
    }

    public User() {
    }

    public int getId_user() {
        return id_user;
    }

    public void setId_user(int id_user) {
        this.id_user = id_user;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getMail() {
        return mail;
    }

    public void setMail(String mail) {
        this.mail = mail;
    }

    public String getMdp() {
        return mdp;
    }

    public void setMdp(String mdp) {
        this.mdp = mdp;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public String getSexe() {
        return sexe;
    }

    public void setSexe(String sexe) {
        this.sexe = sexe;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    @Override
    public String toString() {
        return "User{" + "username=" + username + ", mail=" + mail + ", role=" + role +  ", age=" + age + '}';
    }



  
}
=======
/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entite;

/**
 *
 * @author ANIS
 */

public class User {
    private int id_user,age;
    private String username,mail,mdp,image;
    private String role;
    private String sexe;

    public User(int id_user, String username, String mail, String mdp, String role, String image,int age, String sexe) {
        this.id_user = id_user;
        this.age = age;
        this.username = username;
        this.mail = mail;
        this.mdp = mdp;
        this.image = image;
        this.sexe = sexe;
        this.role = role;
    }

    public User( String username, String mail, String mdp, String role, String image,int age, String sexe) {
        this.age = age;
        this.username = username;
        this.mail = mail;
        this.mdp = mdp;
        this.image = image;
        this.sexe = sexe;
        this.role = role;
    }

    public User() {
    }

    public int getId_user() {
        return id_user;
    }

    public void setId_user(int id_user) {
        this.id_user = id_user;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getMail() {
        return mail;
    }

    public void setMail(String mail) {
        this.mail = mail;
    }

    public String getMdp() {
        return mdp;
    }

    public void setMdp(String mdp) {
        this.mdp = mdp;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public String getSexe() {
        return sexe;
    }

    public void setSexe(String sexe) {
        this.sexe = sexe;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    @Override
    public String toString() {
        return "User{" + "username=" + username + ", mail=" + mail + ", role=" + role +  ", age=" + age + '}';
    }



  
}
>>>>>>> 595d8441276fcc8a656ad5e3c28f2e4ca6806126
