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
 */
public class CtrlVisiteur extends CtrlAbstrait {
    
   private DaoVisiteur daoVisiteur = new DaoVisiteur();
   private DaoLabo daoLabo = new DaoLabo();


    public CtrlVisiteur(CtrlPrincipal ctrlPrincipal) {
        super(ctrlPrincipal);
        vue = new VueVisiteur(this);
        actualiser();
    }

    public final void actualiser() {
        try {
            chargerListeVisiteur();
            chargerListeLabo();
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
   /**Bouton suivant. rajoute +1 a chaque utilisateur 
    * 
    */
    public void visiteurSuivant(){
        int index = getVue().getjComboBoxVisiteur().getSelectedIndex()+1;
        if(index== getVue().getjComboBoxVisiteur().getItemCount())index=0;
        getVue().getjComboBoxVisiteur().setSelectedIndex(index);
    }
    
    /**
     Bouton précédent enleve -1 a chaque utilisateur
     */
    public void visiteurPrecedent(){
        int index = getVue().getjComboBoxVisiteur().getSelectedIndex()-1;
        if(index== -1) index=getVue() .getjComboBoxVisiteur() .getItemCount() -1;
        getVue().getjComboBoxVisiteur().setSelectedIndex(index);
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
        private void chargerListeLabo() throws DaoException {
        List<Labo> desLabos = daoLabo.getAll();
        getVue().getModeleJComboBoxLabo().removeAllElements();
        for (Labo unLabo : desLabos) {
            getVue().getModeleJComboBoxLabo().addElement(unLabo);
        }
    }
    
    public void visiteurSelectionner (){
        Visiteur visiteurSelect = (Visiteur) getVue().getjComboBoxVisiteur().getSelectedItem();
        getVue().getTxtNom().setText(visiteurSelect.getNom());
        getVue().getTxtPrenom().setText(visiteurSelect.getPrenom());
        getVue().getTxtAdrs().setText(visiteurSelect.getAdresse());
        getVue().getTxtVille().setText(visiteurSelect.getVille());
        getVue().getTxtCp().setText(visiteurSelect.getCp());
        getVue().getModeleJComboBoxSecteur().setSelectedItem(visiteurSelect.getSecteur());
        getVue().getModeleJComboBoxLabo().setSelectedItem(visiteurSelect.getLabo());
        
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
