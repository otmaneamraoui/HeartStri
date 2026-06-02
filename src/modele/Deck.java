
package modele;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

/**
 * Classe représentant un deck de cartes dans le jeu.
 * Un deck contient une liste de cartes et permet diverses opérations
 * comme l'ajout, le retrait, et l'affichage des cartes.
 */
public class Deck {
    /**
     * Liste des cartes dans le deck.
     */
    private List<Carte> cartes;

    /**
     * Constructeur de la classe Deck.
     * Initialise un deck vide.
     */
    public Deck() {
        this.cartes = new ArrayList<>();
    }

    /**
     * Obtient la liste des cartes dans le deck.
     *
     * @return La liste des cartes.
     */
    public List<Carte> getCartes() {
        return cartes;
    }

    /**
     * Ajoute une carte au deck.
     *
     * @param carte La carte à ajouter.
     */
    public void ajouterCarte(Carte carte) {
        this.cartes.add(carte);
    }

    /**
     * Pioche une carte au hasard dans le deck.
     * La carte piochée est retirée du deck.
     *
     * @return La carte piochée, ou `null` si le deck est vide.
     */
    public Carte piocher() {
        if (!cartes.isEmpty()) {
            Random random = new Random();
            int index = random.nextInt(cartes.size());
            return cartes.remove(index);
        }
        return null;
    }

    /**
     * Vérifie si le deck est vide.
     *
     * @return `true` si le deck est vide, sinon `false`.
     */
    public boolean estVide() {
        return cartes.isEmpty();
    }

    /**
     * Obtient la taille du deck.
     *
     * @return Le nombre de cartes dans le deck.
     */
    public int taille() {
        return cartes.size();
    }

    /**
     * Affiche les cartes présentes dans le deck.
     * Si le deck est vide, un message approprié est affiché.
     */
    public void afficherCartes() {
        if (estVide()) {
            System.out.println("--> Le deck est vide.\n");
        } else {
            System.out.println("\n=== Cartes dans le deck ===");
            for (Carte carte : cartes) {
                if (carte instanceof Serviteur s) {
                    System.out.println("   - " + s.getNom() + " (Mana : " + s.getMana() + ", Attaque : " + s.getAttaque() + ", Vie : " + s.getVie() + ").");
                } else {
                    System.out.println("   - " + carte.getNom() + " (Mana : " + carte.getMana() + ").");
                }
            }
            System.out.println();
        }
    }
}
