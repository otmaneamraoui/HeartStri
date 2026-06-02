
package modele;

/**
 * Cette classe représente un serviteur avec des attributs d'attaque et de vie.
 */
public class Serviteur extends Carte {
    private int attaque;
    private int vie;
    
    private boolean aAttaqueCeTour = false; // Indique si le serviteur a attaqué ce tour

    /**
     * Constructeur de la classe Serviteur.
     * 
     * @param nom    Le nom du serviteur.
     * @param mana   Le coût en mana du serviteur.
     * @param attaque Les points d'attaque du serviteur.
     * @param vie    Les points de vie du serviteur.
     */
    public Serviteur(String nom, int mana, int attaque, int vie) {
        super(nom, mana);
        this.attaque = attaque;
        this.vie = vie;
    }

    /**
     * Obtient les points d'attaque du serviteur.
     * 
     * @return Les points d'attaque.
     */
    public int getAttaque() {
        return attaque;
    }

    /**
     * Définit les points d'attaque du serviteur.
     * 
     * @param attaque Les nouveaux points d'attaque.
     */
    public void setAttaque(int attaque) {
        this.attaque = attaque;
    }

    /**
     * Obtient les points de vie du serviteur.
     * 
     * @return Les points de vie.
     */
    public int getVie() {
        return vie;
    }

    /**
     * Définit les points de vie du serviteur.
     * 
     * @param vie Les nouveaux points de vie.
     */
    public void setVie(int vie) {
        this.vie = vie;
    }

    /**
     * Permet au serviteur d'attaquer un autre serviteur.
     * 
     * @param cible Le serviteur cible de l'attaque.
     */
    public void attaquer(Serviteur cible) {
        if (!this.estMort()) {
            cible.subirDegats(this.attaque);
        }
    }

    /**
     * Réduit les points de vie du serviteur en fonction des dégâts subis.
     * 
     * @param degats Les points de dégâts à infliger.
     */
    public void subirDegats(int degats) {
        this.vie -= degats;
    }

    /**
     * Vérifie si le serviteur est mort.
     * 
     * @return `true` si le serviteur est mort, sinon `false`.
     */
    public boolean estMort() {
        return this.vie <= 0;
    }

    /**
     * Retourne une représentation textuelle du serviteur.
     * 
     * @return Une chaîne de caractères représentant le serviteur.
     */
    public String toString() {
        return nom + " (Mana : " + mana + ", Attaque : " + attaque + ", Vie : " + vie + ")";
    }

    /**
     * Réinitialise les points d'attaque et de vie du serviteur.
     */
    public void reinitialiser() {
        this.attaque = 0;
        this.vie = 0;
    }

    /**
     * Ajoute des points d'attaque au serviteur.
     * 
     * @param points Les points d'attaque à ajouter.
     */
    public void ajouterAttaque(int points) {
        this.attaque += points;
        if (this.attaque < 0) this.attaque = 0;
    }

    /**
     * Ajoute des points de vie au serviteur.
     * 
     * @param points Les points de vie à ajouter.
     */
    public void ajouterVie(int points) {
        this.vie += points;
        if (this.vie < 0) this.vie = 0;
    }

    /**
     * Soigne le serviteur en augmentant ses points de vie.
     * 
     * @param pv Les points de vie à ajouter.
     */
    public void soigner(int pv) {
        this.vie += pv;
        System.out.println("\n ->> " + this.nom + " récupère " + pv + " PV (total : " + this.vie + ").");
    }
    
    /**
     * Vérifie si le serviteur a attaqué pendant ce tour.
     *
     * @return `true` si le serviteur a attaqué ce tour, sinon `false`.
     */
    public boolean aAttaqueCeTour() {
        return aAttaqueCeTour;
    }

	/**
	 * Définit si le serviteur a attaqué pendant ce tour.
	 *
	 * @param aAttaque `true` si le serviteur a attaqué ce tour, sinon `false`.
	 */
    public void setAttaqueCeTour(boolean aAttaque) {
        this.aAttaqueCeTour = aAttaque;
    }


    // Classes spécifiques pour chaque type de serviteur

    /**
     * Classe représentant un serviteur de type Chevalier.
     */
    public class Chevalier extends Serviteur {
        public Chevalier() {
            super("Chevalier", 3, 5, 8);
        }
    }

    /**
     * Classe représentant un serviteur de type Dragon.
     */
    public class Dragon extends Serviteur {
        public Dragon() {
            super("Dragon", 7, 10, 12);
        }
    }

    /**
     * Classe représentant un serviteur de type Gobelin.
     */
    public class Gobelin extends Serviteur {
        public Gobelin() {
            super("Gobelin", 2, 3, 4);
        }
    }

    /**
     * Classe représentant un serviteur de type Mage.
     */
    public class Sorcier extends Serviteur {
        public Sorcier() {
            super("Sorcier", 5, 6, 5);
        }
    }

    /**
     * Classe représentant un serviteur de type Viking.
     */
    public class Viking extends Serviteur {
        public Viking() {
            super("Viking", 4, 5, 6);
        }
    }

    /**
     * Classe représentant un serviteur de type Nécromancien.
     */
    public class Necromancien extends Serviteur {
        public Necromancien() {
            super("Nécromancien", 6, 6, 7);
        }
    }

    /**
     * Classe représentant un serviteur de type Archer.
     */
    public class Archer extends Serviteur {
        public Archer() {
            super("Archer", 4, 4, 5);
        }
    }

    /**
     * Classe représentant un serviteur de type Magicien de guerre.
     */
    public class MagicienDeGuerre extends Serviteur {
        public MagicienDeGuerre() {
            super("Magicien de guerre", 6, 8, 6);
        }
    }

    /**
     * Classe représentant un serviteur de type Garde d'élite.
     */
    public class GardeElite extends Serviteur {
        public GardeElite() {
            super("Garde d'élite", 5, 6, 9);
        }
    }
}
