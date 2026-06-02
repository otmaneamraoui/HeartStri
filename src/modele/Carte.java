
package modele;

/**
 * Classe abstraite représentant une carte générique dans le jeu.
 * Chaque carte possède un nom et un coût en mana.
 */
public abstract class Carte {
    /**
     * Le nom de la carte.
     */
    protected String nom;

    /**
     * Le coût en mana de la carte.
     */
    protected int mana;

    /**
     * Constructeur de la classe Carte.
     *
     * @param nom  Le nom de la carte.
     * @param mana Le coût en mana de la carte.
     */
    public Carte(String nom, int mana) {
        this.nom = nom;
        this.mana = mana;
    }

    /**
     * Obtient le nom de la carte.
     *
     * @return Le nom de la carte.
     */
    public String getNom() {
        return nom;
    }

    /**
     * Obtient le coût en mana de la carte.
     *
     * @return Le coût en mana de la carte.
     */
    public int getMana() {
        return mana;
    }
}
