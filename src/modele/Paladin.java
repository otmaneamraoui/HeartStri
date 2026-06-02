
package modele;

/**
 * Classe représentant le héros Paladin dans le jeu.
 * Le Paladin possède un pouvoir spécial appelé "Renforcer" qui invoque un serviteur 1/1
 * sur le terrain du joueur.
 */
public class Paladin extends Heros {

    /**
     * Constructeur de la classe Paladin.
     * Initialise le héros avec un nom, des points de vie, un coût en mana pour le pouvoir spécial,
     * et une description de ce pouvoir.
     */
    public Paladin() {
        super("Paladin", 30, 2, "Renforcer");
    }

    /**
     * Utilise le pouvoir spécial du Paladin, "Renforcer".
     * Ce pouvoir invoque un serviteur 1/1 sur le terrain du joueur,
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
        // création d'un serviteur 1/1
        Serviteur recrue = new Serviteur("Recrue", 1, 1, 1); // Crée un serviteur 1/1
        lanceur.poserServiteur(recrue); // Place le serviteur sur le terrain
        System.out.println("--> Recrue 1/1 invoquée sur le terrain de " + lanceur.getNom());
        
     // Déduction du mana après application réussie
        lanceur.deduireMana(getCoutPouvoir());
    }
}
