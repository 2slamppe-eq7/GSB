package modele.dao;

import modele.metier.*;
import modele.jdbc.Jdbc;
import java.sql.*;
import java.util.*;

/**
 * Classe DAO pour la classe Visiteur
 *
 * @version 22 novembre 2013
 * @author nbourgeois
 */
public class DaoVisiteur implements DaoInterface<Visiteur, Integer> {

    /**
     * Non implémenté
     */
    @Override
    public int create(Visiteur unVisiteur) throws Exception {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    /**
     * Lire un enregistrement d'après son identifiant
     *
     * @param identifiant métier de l'objet recherché
     * @return objet métier trouvé, ou null sinon
     * @throws Exception
     */
    @Override
    public Visiteur getOne(Integer idVisiteur) throws DaoException {
        Visiteur result = null;
        ResultSet rs = null;
        // préparer la requête
        String requete = "SELECT * FROM VISITEUR WHERE ID_VISITEUR=?";
        try {
            PreparedStatement ps = Jdbc.getInstance().getConnexion().prepareStatement(requete);
            ps.setInt(1, idVisiteur);
            rs = ps.executeQuery();
            if (rs.next()) {
                result = chargerUnEnregistrement(rs);
            }
        } catch (SQLException ex) {
            throw new modele.dao.DaoException("DaoVisiteur::getOne : erreur requete SELECT : " + ex.getMessage());
        }
        return (result);
    }

    /**
     * getAll
     *
     * @return ArrayList de l'ensemble des occurences d'visiteurs de la table
     * VISITEUR
     */
    @Override
    public ArrayList<Visiteur> getAll() throws DaoException {
        ArrayList<Visiteur> result = new ArrayList<Visiteur>();
        ResultSet rs;
        // préparer la requête
        String requete = "SELECT * FROM VISITEUR";
        try {
            PreparedStatement ps = Jdbc.getInstance().getConnexion().prepareStatement(requete);
            rs = ps.executeQuery();
            // Charger les enregistrements dans la collection
            while (rs.next()) {
                Visiteur unVisiteur = chargerUnEnregistrement(rs);
                result.add(unVisiteur);
            }
        } catch (SQLException ex) {
            throw new modele.dao.DaoException("DaoVisiteur::getAll : erreur requete SELECT : " + ex.getMessage());
        }
        return result;
    }

    /**
     * Non implémenté
     */
    @Override
    public int update(Integer idMetier, Visiteur objetMetier) throws Exception {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    /**
     * Non implémenté
     */
    @Override
    public int delete(Integer idMetier) throws Exception {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    //----------------------------------------------------------------------
    //  Méthodes privées
    //----------------------------------------------------------------------
    /**
     * chargerUnEnregistrementVisiteur Instancie un objet visiteur avec les
     * valeurs lues dans la base de données La jointure avec la table PRESENCE
     * n'est pas effectuée
     *
     * @param rs enregistrement de la table Visiteur courant
     * @return un objet Visiteur, dont la liste des "présences" n'est pas
     * renseignée
     * @throws DaoException
     */
    private Visiteur chargerUnEnregistrement(ResultSet rs) throws DaoException {
        try {
            Visiteur visiteur = new Visiteur(null,null,null,null,null,null,null,null,null);
            visiteur.setMatricule(rs.getString("VIS_MATRICULE"));
            visiteur.setPrenom(rs.getString("VIS_PRENOM"));
            visiteur.setNom(rs.getString("VIS_NOM"));
            visiteur.setAdresse(rs.getString("VIS_ADRESSE"));
            visiteur.setVille(rs.getString("VIS_VILLE"));
            //visiteur.setSecteur(rs.getString("VIS_SECTEUR"));
            //visiteur.setLabo(rs.getString("VIS_LABO"));
            return visiteur;
        } catch (SQLException ex) {
            throw new DaoException("DaoVisiteur - chargerUnEnregistrement : pb JDBC\n" + ex.getMessage());
        }
    }
}
