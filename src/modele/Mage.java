
package modele;

/**
 * Classe représentant le héros Mage dans le jeu.
 * Le Mage possède un pouvoir spécial appelé "Explosion de feu" qui inflige 1 point de dégât
 * à un serviteur ou au héros adverse.
 */
public class Mage extends Heros {

    /**
     * Constructeur de la classe Mage.
     * Initialise le héros avec un nom, des points de vie, un coût en mana pour le pouvoir spécial,
     * et une description de ce pouvoir.
     */
    public Mage() {
        super("Mage", 30, 3, "Explosion de feu");
    }

    /**
     * Utilise le pouvoir spécial du Mage, "Explosion de feu".
     * Ce pouvoir inflige 1 point de dégât à un serviteur ou au héros adverse,
     * si le joueur a suffisamment de mana.
     *
     * @param lanceur Le joueur qui utilise le pouvoir spécial.
     * @param cible   La cible du pouvoir spécial (peut être un serviteur ou un joueur).
     */
    @Override
    public void utiliserPouvoirSpecial(Joueur lanceur, Object cible) {
        if (lanceur.getMana() < getCoutPouvoir()) { // Vérifie si le joueur a assez de mana
        	System.out.println("\n--> Pas assez de mana pour utiliser le pouvoir.");
            return;
        } 
        if (cible instanceof Serviteur) { // Si la cible est un serviteur
            Serviteur cibleServiteur = (Serviteur) cible;
            cibleServiteur.subirDegats(1); // Inflige 1 dégât au serviteur
            System.out.println("\n ==> Explosion de feu inflige 1 dégât au serviteur : " + cibleServiteur.getNom() + " !\n");
        } else if (cible instanceof Joueur) { // Si la cible est un joueur
            Joueur cibleJoueur = (Joueur) cible;
            cibleJoueur.subirDegats(1); // Inflige 1 dégât au joueur
            System.out.println("\n ==> Explosion de feu inflige 1 dégât à " + cibleJoueur.getNom() + " !\n");
        } else {
            System.out.println("--> Cible invalide.");
            return; // poue ne pas retirer le mana
        }
     // Mana est retiré SEULEMENT si la cible est correcte
        lanceur.deduireMana(getCoutPouvoir());
    }
}
