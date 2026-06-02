
package modele;

/**
 * Classe représentant le héros Chasseur dans le jeu.
 * Le Chasseur possède un pouvoir spécial appelé "Tir assuré" qui inflige 2 points de dégâts
 * au héros adverse.
 */
public class Chasseur extends Heros {

    /**
     * Constructeur de la classe Chasseur.
     * Initialise le héros avec un nom, des points de vie, un coût en mana pour le pouvoir spécial,
     * et une description de ce pouvoir.
     */
    public Chasseur() {
        super("Chasseur", 30, 3, "Tir assuré (Inflige 2 points de dégâts)");
    }

    /**
     * Utilise le pouvoir spécial du Chasseur, "Tir assuré".
     * Ce pouvoir inflige 2 points de dégâts au héros adverse, si le joueur a suffisamment de mana.
     *
     * @param lanceur Le joueur qui utilise le pouvoir spécial.
     * @param cible   La cible du pouvoir spécial (doit être un joueur).
     */
    @Override
    public void utiliserPouvoirSpecial(Joueur lanceur, Object cible) {
    	// Vérification du mana
        if (lanceur.getMana() < getCoutPouvoir()) {
            System.out.println("--> Pas assez de mana.");
            return;
        }
        if (cible instanceof Joueur) { // Vérifie si la cible est un joueur
            Joueur cibleJoueur = (Joueur) cible;
            cibleJoueur.subirDegats(2); // Inflige 2 dégâts au joueur cible
            System.out.println("\n--> Tir assuré inflige 2 dégâts à " + cibleJoueur.getNom() + ".");
        } else {
            System.out.println("\n--> Cible invalide pour Tir assuré.");
            return; // Ne retire pas le mana si la cible est invalide
        }
        // Déduction du mana seulement si le pouvoir a été utilisé correctement
        lanceur.deduireMana(getCoutPouvoir());
    }
}
