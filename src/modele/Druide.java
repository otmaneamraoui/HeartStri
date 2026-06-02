
package modele;

/**
 * Classe représentant le héros Druide dans le jeu.
 * Le Druide possède un pouvoir spécial appelé "Métamorphose" qui ajoute 1 point d'armure
 * et 1 point d'attaque temporaire au joueur.
 */
public class Druide extends Heros {

    /**
     * Constructeur de la classe Druide.
     * Initialise le héros avec un nom, des points de vie, un coût en mana pour le pouvoir spécial,
     * et une description de ce pouvoir.
     */
    public Druide() {
        super("Druide", 30, 2, "Métamorphose");
    }

    /**
     * Utilise le pouvoir spécial du Druide, "Métamorphose".
     * Ce pouvoir ajoute 1 point d'armure et 1 point d'attaque temporaire au joueur,
     * si le joueur a suffisamment de mana.
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
        
        lanceur.ajouterArmure(1); // Ajoute 1 point d'armure
        lanceur.ajouterAttaqueTemporaire(1); // Ajoute 1 point d'attaque temporaire
        System.out.println("--> Métamorphose : +1 armure et +1 ATQ temporaire pour " + lanceur.getNom());
        
        // Déduction du mana après application réussie
        lanceur.deduireMana(getCoutPouvoir());
    }
}
