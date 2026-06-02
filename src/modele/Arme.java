
package modele;

/**
 * Classe représentant une arme dans le jeu.
 * Une arme est une carte qui possède des points d'attaque et une durabilité.
 */
public class Arme extends Carte {

    /**
     * Points d'attaque de l'arme.
     */
    private int attaque;

    /**
     * Points de durabilité de l'arme.
     */
    private int durabilite;

    /**
     * Constructeur de la classe Arme.
     *
     * @param nom        Le nom de l'arme.
     * @param mana       Le coût en mana de l'arme.
     * @param attaque    Les points d'attaque de l'arme.
     * @param durabilite Les points de durabilité de l'arme.
     */
    public Arme(String nom, int mana, int attaque, int durabilite) {
        super(nom, mana);
        this.attaque = attaque;
        this.durabilite = durabilite;
    }

    /**
     * Obtient les points d'attaque de l'arme.
     *
     * @return Les points d'attaque.
     */
    public int getAttaque() {
        return attaque;
    }

    /**
     * Obtient les points de durabilité de l'arme.
     *
     * @return Les points de durabilité.
     */
    public int getDurabilite() {
        return durabilite;
    }

    /**
     * Diminue la durabilité de l'arme après chaque utilisation.
     */
    public void utiliser() {
        durabilite--;
    }

    /**
     * Vérifie si l'arme est cassée.
     *
     * @return `true` si la durabilité est inférieure ou égale à 0, sinon `false`.
     */
    public boolean estCassee() {
        return durabilite <= 0;
    }

    /**
     * Retourne une représentation textuelle de l'arme.
     *
     * @return Une chaîne de caractères représentant l'arme.
     */
    @Override
    public String toString() {
        return nom + " (Arme - Mana : " + mana + ", ATQ : " + attaque + ", Durabilité : " + durabilite + ")";
    }
}
