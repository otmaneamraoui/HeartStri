
package modele;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

/**
 * Classe représentant un joueur dans le jeu.
 * Un joueur possède un nom, un deck, un héros, des points de vie, du mana, une arme,
 * une main de cartes, et des serviteurs en jeu.
 */
public class Joueur {
    private String nom; // Nom du joueur
    private Deck deck; // Deck du joueur
    private Heros heros; // Héros du joueur
    private int mana; // Points de mana du joueur
    private int armure; // Points d'armure du joueur
    private int pv; // Points de vie du joueur
    private Arme arme; // Arme actuellement équipée par le joueur
    private int attaqueTemporaire = 0; // Attaque temporaire (par exemple, pour le Druide)
    private boolean pouvoirUtiliseCeTour = false; // Indique si le pouvoir spécial a été utilisé ce tour

    private List<Carte> main; // Cartes en main du joueur
    private List<Serviteur> enJeu = new ArrayList<>(); // Serviteurs en jeu pour le joueur

    private boolean aAttaqueAvecArmeCeTour = false; // Indique si le joueur a attaqué avec son arme ce tour
    
    private static final Scanner scanner = new Scanner(System.in); // Scanner pour les entrées utilisateur

    /**
     * Constructeur de la classe Joueur.
     *
     * @param nom  Le nom du joueur.
     * @param deck Le deck du joueur.
     * @param heros Le héros du joueur.
     */
    public Joueur(String nom, Deck deck, Heros heros) {
        this.nom = nom;
        this.deck = deck;
        this.heros = heros;
        this.mana = 1; // Initialisation du mana à 1
        this.pv = 30; // Initialisation des points de vie à 30
        this.main = new ArrayList<>(); // Initialisation de la main vide
    }

    /**
     * Obtient le nom du joueur.
     *
     * @return Le nom du joueur.
     */
    public String getNom() {
        return nom;
    }

    /**
     * Obtient le deck du joueur.
     *
     * @return Le deck du joueur.
     */
    public Deck getDeck() {
        return deck;
    }

    /**
     * Obtient le héros du joueur.
     *
     * @return Le héros du joueur.
     */
    public Heros getHeros() {
        return heros;
    }

    /**
     * Obtient les points de mana du joueur.
     *
     * @return Les points de mana.
     */
    public int getMana() {
        return mana;
    }

    /**
     * Définit les points de mana du joueur.
     *
     * @param mana Les nouveaux points de mana.
     */
    public void setMana(int mana) {
        this.mana = mana;
    }

    /**
     * Obtient les points de vie du joueur.
     *
     * @return Les points de vie.
     */
    public int getPv() {
        return pv;
    }

    /**
     * Définit les points de vie du joueur.
     *
     * @param pv Les nouveaux points de vie.
     */
    public void setPv(int pv) {
        this.pv = pv;
        this.heros.setPv(pv); // Synchronise avec les points de vie du héros
    }

    /**
     * Ajoute des points d'armure au joueur.
     *
     * @param points Les points d'armure à ajouter.
     */
    public void ajouterArmure(int points) {
        this.armure += points;
    }

    /**
     * Obtient les points d'armure du joueur.
     *
     * @return Les points d'armure.
     */
    public int getArmure() {
        return armure;
    }

    /**
     * Obtient l'arme actuellement équipée par le joueur.
     *
     * @return L'arme équipée.
     */
    public Arme getArme() {
        return arme;
    }

    /**
     * Définit l'arme actuellement équipée par le joueur.
     *
     * @param arme L'arme à équiper.
     */
    public void setArme(Arme arme) {
        this.arme = arme;
    }

    /**
     * Vérifie si le joueur possède une arme non cassée.
     *
     * @return `true` si le joueur a une arme valide, sinon `false`.
     */
    public boolean aUneArme() {
        return arme != null && !arme.estCassee();
    }

    /**
     * Recharge le mana du joueur en fonction du tour.
     *
     * @param tour Le numéro du tour.
     */
    public void rechargerMana(int tour) {
        this.mana = Math.min(tour, 10); // Limite le mana à 10
    }

    /**
     * Déduit un coût en mana du joueur.
     *
     * @param cout Le coût en mana à déduire.
     * @return `true` si le joueur a suffisamment de mana, sinon `false`.
     */
    public boolean deduireMana(int cout) {
        if (mana >= cout) {
            mana -= cout;
            return true;
        }
        return false;
    }

    /**
     * Inflige des dégâts au joueur.
     *
     * @param degats Les points de dégâts à infliger.
     */
    public void subirDegats(int degats) {
        this.pv -= degats;
        this.heros.setPv(this.pv); // Synchronise avec les points de vie du héros
        if (this.pv < 0) {
            this.pv = 0; // Empêche les points de vie négatifs
            this.heros.setPv(this.pv);
        }
    }
    
	/**
	 * Vérifie si le joueur a attaqué avec son arme ce tour.
	 * 
	 * @return `true` si le joueur a attaqué avec son arme, sinon `false`.
	 */
    public boolean aAttaqueAvecArmeCeTour() {
        return aAttaqueAvecArmeCeTour;
    }

	/**
	 * Définit si le joueur a attaqué avec son arme ce tour.
	 *
	 * @param aAttaque `true` si le joueur a attaqué, sinon `false`.
	 */
    public void setAttaqueAvecArmeCeTour(boolean aAttaque) {
        this.aAttaqueAvecArmeCeTour = aAttaque;
    }

    /**
     * Ajoute une carte à la main du joueur.
     *
     * @param carte La carte à ajouter.
     */
    public void ajouterCarteMain(Carte carte) {
        if (main.size() < 10) { // Limite la main à 10 cartes
            main.add(carte);
            System.out.println("\n--> " + nom + " pioche " + carte.getNom() + ".");
        } else {
            System.out.println("\n    ==> La main est pleine ! Carte non ajoutée.");
        }
    }

    /**
     * Retire une carte de la main du joueur.
     *
     * @param carte La carte à retirer.
     */
    public void retirerCarteMain(Carte carte) {
        main.remove(carte);
    }

    /**
     * Obtient la liste des cartes en main du joueur.
     *
     * @return La liste des cartes en main.
     */
    public List<Carte> getMain() {
        return main;
    }

    /**
     * Affiche les cartes en main du joueur.
     */
    public void afficherMain() {
        int numeroCarte = 1;
        System.out.println("\n==> Cartes en main de " + nom + " : ");
        for (Carte carte : main) {
            if (carte instanceof Serviteur serviteur) {
                System.out.println("    -" + numeroCarte + "- Serviteur : " + serviteur.getNom() + " (Mana : " + serviteur.getMana()
                        + ", ATQ : " + serviteur.getAttaque() + ", PV : " + serviteur.getVie() + ")");
            } else if (carte instanceof Sort sort) {
                System.out.println("    -" + numeroCarte + "- Sort : " + sort.getNom() + " (Mana : " + sort.getMana() + ", Effet : " + sort.getTypeEffet() + ")");
            } else if (carte instanceof Arme arme) {
                System.out.println("    -" + numeroCarte + "- Arme : " + arme.getNom() + " (Mana : " + arme.getMana() + ", ATQ : " + arme.getAttaque() + ", Durabilité : " + arme.getDurabilite() + ")");
            } else {
                System.out.println("    - Carte inconnue : " + carte.getClass().getSimpleName());
            }
            numeroCarte++;
        }
        System.out.println("");
    }

    /**
     * Ajoute un serviteur sur le terrain.
     *
     * @param s Le serviteur à poser.
     */
    public void poserServiteur(Serviteur s) {
        enJeu.add(s);
    }

    /**
     * Obtient la liste des serviteurs en jeu.
     *
     * @return La liste des serviteurs en jeu.
     */
    public List<Serviteur> getServiteursEnJeu() {
        return enJeu;
    }

    /**
     * Affiche les serviteurs en jeu.
     */
    public void afficherServiteursEnJeu() {
        if (enJeu.isEmpty()) {
            System.out.println("--> Aucun serviteur en jeu.");
        } else {
            System.out.println("    --> Serviteurs en jeu :");
            for (int i = 0; i < enJeu.size(); i++) {
                Serviteur s = enJeu.get(i);
                System.out.println("     -" + (i + 1) + "- " + s.getNom() + " (ATQ: " + s.getAttaque() + ", PV: " + s.getVie() + ")");
            }
            System.out.println("");
        }
    }

    /**
     * Permet au joueur d'attaquer un autre joueur ou un serviteur avec son arme.
     *
     * @param joueurDefenseur Le joueur défenseur.
     */
    public void attaquerAvecArme(Joueur joueurDefenseur) {
    	// Vérifie si le joueur a déjà attaqué avec son arme ce tour
    	if (aAttaqueAvecArmeCeTour) {
    	    System.out.println("\n--> Vous avez déjà attaqué avec votre arme ce tour.");
    	    return;
    	}
        if (aUneArme()) {
            System.out.println("\n --- Attaque avec l'arme ---");
            System.out.println(" - 1 - Attaquer le héros adverse.");
            System.out.println(" - 2 - Attaquer un serviteur adverse.");
            System.out.println(" - 0 - Annuler et revenir au menu principal.");
            System.out.print("    - Votre choix : ");

            int choix = scanner.nextInt();
            scanner.nextLine();

            if (choix == 0) {
                System.out.println("--> Retour au menu principal.");
                return;
            }

            switch (choix) {
                case 1:
                    joueurDefenseur.subirDegats(arme.getAttaque());
                    arme.utiliser();
                    setAttaqueAvecArmeCeTour(true);
                    System.out.println("\n--> Le héros adverse perd " + arme.getAttaque() + " PV.");
                    break;

                case 2:
                    if (joueurDefenseur.getServiteursEnJeu().isEmpty()) {
                        System.out.println("\n--> Aucun serviteur adverse à attaquer.");
                        return;
                    }

                    joueurDefenseur.afficherServiteursEnJeu();
                    System.out.print(" - Cible à attaquer (numéro, 0 pour annuler) : ");
                    int i = scanner.nextInt() - 1;
                    scanner.nextLine();

                    if (i == -1) {
                        System.out.println("\n--> Retour au menu principal.");
                        return;
                    }

                    if (i < 0 || i >= joueurDefenseur.getServiteursEnJeu().size()) {
                        System.out.println("\n--> Choix invalide.");
                        return;
                    }

                    Serviteur cible = joueurDefenseur.getServiteursEnJeu().get(i);
                    cible.subirDegats(arme.getAttaque());
                    arme.utiliser();
                    setAttaqueAvecArmeCeTour(true);
                    System.out.println("\n--> " + cible.getNom() + " perd " + arme.getAttaque() + " PV.");

                    if (cible.estMort()) {
                        System.out.println("\n--> " + cible.getNom() + " est mort.");
                        joueurDefenseur.getServiteursEnJeu().remove(cible);
                    }
                    break;

                default:
                    System.out.println("\n--> Choix invalide.");
            }

            if (arme.estCassee()) {
                System.out.println("\n --> L’arme " + arme.getNom() + " est cassée !");
                arme = null;
            }
        } else {
            System.out.println("\n --> Aucune arme équipée !");
        }
    }

    /**
     * Ajoute une attaque temporaire au joueur.
     *
     * @param atq Les points d'attaque temporaire à ajouter.
     */
    public void ajouterAttaqueTemporaire(int atq) {
        this.attaqueTemporaire += atq;
    }

    /**
     * Obtient l'attaque totale du joueur (temporaire + arme).
     *
     * @return L'attaque totale.
     */
    public int getAttaqueTotale() {
        return this.attaqueTemporaire + (this.arme != null ? this.arme.getAttaque() : 0);
    }

    /**
     * Réinitialise l'attaque temporaire du joueur.
     */
    public void reinitialiserAttaqueTemporaire() {
        this.attaqueTemporaire = 0;
    }

    /**
     * Vérifie si le pouvoir spécial a été utilisé ce tour.
     *
     * @return `true` si le pouvoir a été utilisé, sinon `false`.
     */
    public boolean getPouvoirUtiliseCeTour() {
        return pouvoirUtiliseCeTour;
    }

    /**
     * Définit si le pouvoir spécial a été utilisé ce tour.
     *
     * @param valeur `true` si le pouvoir a été utilisé, sinon `false`.
     */
    public void setPouvoirUtiliseCeTour(boolean valeur) {
        this.pouvoirUtiliseCeTour = valeur;
    }

    /**
     * Retourne une représentation textuelle du joueur.
     *
     * @return Une chaîne de caractères contenant les informations du joueur.
     */
    @Override
    public String toString() {
        return " - Joueur : " + nom + "\n" +
               "  - Héros : " + heros.getNom() + " (PV : " + heros.getPv() + ")\n" +
               "  - Mana : " + mana;
    }
}
