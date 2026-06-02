
package modele;

/**
 * Classe représentant le héros Prêtre dans le jeu.
 * Le Prêtre possède un pouvoir spécial appelé "Soins inférieurs" qui rend 2 points de vie
 * à un joueur ou à un serviteur.
 */
public class Pretre extends Heros {

    /**
     * Constructeur de la classe Pretre.
     * Initialise le héros avec un nom, des points de vie, un coût en mana pour le pouvoir spécial,
     * et une description de ce pouvoir.
     */
    public Pretre() {
        super("Prêtre", 30, 4, "Soins inférieurs");
    }

    /**
     * Utilise le pouvoir spécial du Prêtre, "Soins inférieurs".
     * Ce pouvoir rend 2 points de vie à un joueur ou à un serviteur,
     * si le joueur a suffisamment de mana.
     *
     * @param lanceur Le joueur qui utilise le pouvoir spécial.
     * @param cible   La cible du pouvoir spécial (peut être un joueur ou un serviteur).
     */
    @Override
    public void utiliserPouvoirSpecial(Joueur lanceur, Object cible) {
    	// Vérification du mana disponible
        if (lanceur.getMana() < getCoutPouvoir()) {
            System.out.println("\n--> Pas assez de mana.");
            return;
        }
        if (cible instanceof Joueur) { // Si la cible est un joueur
            Joueur cibleJoueur = (Joueur) cible;
            cibleJoueur.setPv(cibleJoueur.getPv() + 2); // Rend 2 PV au joueur
            System.out.println("--> Soins inférieurs rend 2 PV à " + cibleJoueur.getNom());
        } else if (cible instanceof Serviteur) { // Si la cible est un serviteur
            Serviteur cibleServiteur = (Serviteur) cible;
            cibleServiteur.soigner(2); // Rend 2 PV au serviteur
            System.out.println("--> Soins inférieurs rend 2 PV à " + cibleServiteur.getNom());
        } else {
            System.out.println("--> Cible invalide pour les soins.");
            return; 
        }
        // Déduction du mana après application réussie
        lanceur.deduireMana(getCoutPouvoir());
    }
}
