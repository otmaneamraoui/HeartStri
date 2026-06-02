
package modele;

/**
 * Classe représentant le héros Guerrier dans le jeu.
 * Le Guerrier possède un pouvoir spécial appelé "Gain d'armure" qui ajoute 2 points d'armure
 * au joueur.
 */
public class Guerrier extends Heros {

    /**
     * Constructeur de la classe Guerrier.
     * Initialise le héros avec un nom, des points de vie, un coût en mana pour le pouvoir spécial,
     * et une description de ce pouvoir.
     */
    public Guerrier() {
        super("Guerrier", 30, 4, "Gain d'armure");
    }

    /**
     * Utilise le pouvoir spécial du Guerrier, "Gain d'armure".
     * Ce pouvoir ajoute 2 points d'armure au joueur, si le joueur a suffisamment de mana.
     *
     * @param lanceur Le joueur qui utilise le pouvoir spécial.
     * @param cible   La cible du pouvoir spécial (non utilisée dans ce cas).
     */
    @Override
    public void utiliserPouvoirSpecial(Joueur lanceur, Object cible) {
    	// Vérification du mana disponible
        if (lanceur.getMana() < getCoutPouvoir()) {
            System.out.println("\n--> Pas assez de mana.");
            return;
        }

        lanceur.ajouterArmure(2); // Ajoute 2 points d'armure
        System.out.println("--> " + lanceur.getNom() + " gagne 2 points d’armure !");
        
        // Déduction du mana après application réussie
        lanceur.deduireMana(getCoutPouvoir());
    }
}
