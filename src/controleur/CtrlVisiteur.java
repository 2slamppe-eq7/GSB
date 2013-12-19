package controleur;

import java.util.Date;
import java.util.List;
import javax.swing.JOptionPane;
import modele.dao.*;
import modele.metier.*;
import vue.VueVisiteur;

/**
 * Contrôleur de la fenêtre VuePresence
 *
 * @author nbourgeois
 * @version 1 20 novembre 2013
 */
public class CtrlVisiteur extends CtrlAbstrait {
    
   private DaoVisiteur daoVisiteur = new DaoVisiteur();


    public CtrlVisiteur(CtrlPrincipal ctrlPrincipal) {
        super(ctrlPrincipal);
        vue = new VueVisiteur(this);
        actualiser();
    }

    public final void actualiser() {
        try {
            chargerListeVisiteur();
        } catch (DaoException ex) {
            JOptionPane.showMessageDialog(getVue(), "CtrlVisiteur - actualiser - " + ex.getMessage(), "Saisie des présences", JOptionPane.ERROR_MESSAGE);
        }
    }

    /**
     * presenceEnregistrer réaction au clic sur le bouton VALIDER de la vue
     * VuePresences
     */

    /**
     * presenceAnnuler réaction au clic sur le bouton ANNULER de la vue Le
     * contrôle est rendu au contrôleur frontal
     */
    public void visiteurQuitter() {
        this.getCtrlPrincipal().action(EnumAction.VISITEUR_QUITTER);
    }

    /**
     * chargerListeEquipiers renseigner le modèle du composant jComboBoxEquipier
     * à partir de la base de données
     *
     * @throws DaoException
     */
    private void chargerListeVisiteur() throws DaoException {
        List<Visiteur> desVisiteurs = daoVisiteur.getAll();
        getVue().getModeleJComboBoxVisiteur().removeAllElements();
        for (Visiteur unVisiteur : desVisiteurs) {
            getVue().getModeleJComboBoxVisiteur().addElement(unVisiteur);
        }
    }

    /**
     * chargerLesCodesEtat renseigner le modèle du composant
     * jComboBoxEtatPresence à partir de la base de données
     *
     * @throws DaoException
     */
    
    @Override
    public VueVisiteur getVue() {
        return (VueVisiteur) vue;
    }
    
}
