
package modele;

/**
 * Classe représentant le héros Voleur dans le jeu.
 * Le Voleur possède un pouvoir spécial appelé "Maîtrise des dagues" qui équipe une arme 1/2
 * sur le héros.
 */
public class Voleur extends Heros {

    /**
     * Constructeur de la classe Voleur.
     * Initialise le héros avec un nom, des points de vie, un coût en mana pour le pouvoir spécial,
     * et une description de ce pouvoir.
     */
    public Voleur() {
        super("Voleur", 30, 5, "Maîtrise des dagues");
    }

    /**
     * Utilise le pouvoir spécial du Voleur, "Maîtrise des dagues".
     * Ce pouvoir équipe une arme 1/2 sur le héros, si le joueur a suffisamment de mana.
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

        Arme dague = new Arme("Dague 1/2", 1, 2, 1); // Crée une arme avec 1 ATQ et 2 de durabilité
        lanceur.setArme(dague); // Équipe l'arme sur le héros
        System.out.println("--> " + lanceur.getNom() + " équipe une dague 1/2 !");

        // Déduction du mana après application réussie
        lanceur.deduireMana(getCoutPouvoir());
    }
}
