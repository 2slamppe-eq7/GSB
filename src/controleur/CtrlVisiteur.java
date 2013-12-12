/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package controleur;

import java.util.List;
import modele.dao.DaoException;
import modele.dao.Dao;
import modele.metier.Visiteur;
import vues.VueVisiteur;

/**
 *
 * @author btssio
 */
public class CtrlVisiteur extends ControleurAbstrait {
    VueVisiteur vueVisiteur;
    List<Visiteur>lesVisiteurs;
    public CtrlVisiteur(ControleurAbstrait controleur) throws DaoException {
        super(controleur);
        vueVisiteur = new VueVisiteur(this);
        vue=vueVisiteur;
            
        this.afficherVue();
        lesVisiteurs=initListVisiteurs();
        vueVisiteur.chargerListeVisiteur(lesVisiteurs);
        
    }
    
   private List<Visiteur> initListVisiteurs() throws DaoException{
   
        List<Visiteur> desVisiteurs = dao.lireTousLesVisiteur();
        
        return desVisiteurs;
   }
    
}
