/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package services;

import java.util.List;

/**
 *
 * @author tlili
 */
interface IServiceSalleDeSport<T> {
    public void ajouterSalleDeSport(T t);
    public void modifierSalleDeSport(T t);
    public void supprimerSalleDeSport(int id);
    public T getOneSalleDeSport(T t);
    public List<T> getAllSalleDeSport();
}
