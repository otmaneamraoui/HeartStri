
package modele;

/**
 * Classe abstraite représentant un héros dans le jeu.
 * Chaque héros possède un nom, des points de vie (PV), un pouvoir spécial, 
 * et un coût en mana pour utiliser ce pouvoir.
 */
public abstract class Heros {
    private String nom;
    private int pv;
    private String pouvoirSpecial;
    private int coutManaPouvoir;

    /**
     * Constructeur de la classe Heros.
     *
     * @param nom             Le nom du héros.
     * @param pv              Les points de vie du héros.
     * @param coutManaPouvoir Le coût en mana pour utiliser le pouvoir spécial.
     * @param pouvoirSpecial  La description du pouvoir spécial du héros.
     */
    public Heros(String nom, int pv, int coutManaPouvoir, String pouvoirSpecial) {
        this.nom = nom;
        this.pv = pv;
        this.pouvoirSpecial = pouvoirSpecial;
        this.coutManaPouvoir = coutManaPouvoir;
    }

    /**
     * Méthode abstraite à implémenter dans chaque héros pour l'effet de leur pouvoir spécial.
     *
     * @param joueur Le joueur qui utilise le pouvoir spécial.
     * @param cible  La cible du pouvoir spécial.
     */
    public abstract void utiliserPouvoirSpecial(Joueur joueur, Object cible);

    /**
     * Obtient le nom du héros.
     *
     * @return Le nom du héros.
     */
    public String getNom() {
        return nom;
    }

    /**
     * Obtient les points de vie du héros.
     *
     * @return Les points de vie du héros.
     */
    public int getPv() {
        return pv;
    }

    /**
     * Définit les points de vie du héros.
     *
     * @param pv Les nouveaux points de vie.
     */
    public void setPv(int pv) {
        this.pv = pv;
    }

    /**
     * Réduit les points de vie du héros en fonction des dégâts subis.
     *
     * @param degats Les points de dégâts à infliger.
     */
    public void subirDegats(int degats) {
        this.pv -= degats;
        if (this.pv < 0) {
            this.pv = 0; // S'assurer que les PV ne deviennent pas négatifs.
        }
    }

    /**
     * Vérifie si le héros est mort.
     *
     * @return `true` si les points de vie sont inférieurs ou égaux à 0, sinon `false`.
     */
    public boolean estMort() {
        return this.pv <= 0;
    }

    /**
     * Retourne une représentation textuelle du héros.
     *
     * @return Une chaîne de caractères contenant le nom et les points de vie du héros.
     */
    public String toString() {
        return " - " + nom + " (PV : " + pv + ").";
    }

    /**
     * Obtient la description du pouvoir spécial du héros.
     *
     * @return La description du pouvoir spécial.
     */
    public String getPouvoirSpecial() {
        return pouvoirSpecial;
    }

    /**
     * Obtient le coût en mana pour utiliser le pouvoir spécial.
     *
     * @return Le coût en mana du pouvoir spécial.
     */
    public int getCoutPouvoir() {
        return coutManaPouvoir;
    }

    /**
     * Retourne une description textuelle du héros.
     *
     * @return Une chaîne de caractères contenant le nom et les points de vie du héros.
     */
    public String getDescription() {
        return getNom() + " (PV : " + getPv() + " - Cout Pouvoir : " + getCoutPouvoir() + ")";
    }
}
