
package sauvegarde;

import modele.Joueur;

/**
 * Classe représentant l'état d'une partie.
 * Cette classe contient les informations nécessaires pour sauvegarder ou restaurer
 * une partie, notamment les joueurs, le joueur actif, et le numéro du tour.
 */
public class EtatPartie {
    /**
     * Le premier joueur de la partie.
     */
    public Joueur joueur1;

    /**
     * Le deuxième joueur de la partie.
     */
    public Joueur joueur2;

    /**
     * Le nom du joueur actuellement actif.
     */
    public String joueurActif;

    /**
     * Le numéro actuel du tour dans la partie.
     */
    int numeroTour;

    /**
     * Constructeur de la classe `EtatPartie`.
     *
     * @param j1 Le premier joueur.
     * @param j2 Le deuxième joueur.
     * @param actif Le nom du joueur actif.
     * @param numeroTour Le numéro actuel du tour.
     */
    public EtatPartie(Joueur j1, Joueur j2, String actif, int numeroTour) {
        this.joueur1 = j1;
        this.joueur2 = j2;
        this.joueurActif = actif;
        this.numeroTour = numeroTour;
    }

    /**
     * Retourne le numéro actuel du tour.
     *
     * @return Le numéro du tour.
     */
    public int getNumeroTour() {
        return numeroTour;
    }
}
