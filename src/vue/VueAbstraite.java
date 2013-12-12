/*
* To change this template, choose Tools | Templates
* and open the template in the editor.
*/
package vue;

import controleur.ControleurAbstrait;
import javax.swing.JFrame;

/**
*
* @author btssio
*/
public abstract class VueAbstraite extends JFrame{
    // associations
    protected ControleurAbstrait controleur=null;
    
    public VueAbstraite(ControleurAbstrait ctrl) {
        this.controleur = ctrl;
    }

    public ControleurAbstrait getControleur() {
        return controleur;
    }
    
    public void afficher(){
        this.setVisible(true);
    }
    
    public void cacher(){
        this.setVisible(false);
    }
    
}