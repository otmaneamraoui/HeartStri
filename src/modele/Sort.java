
package modele;

import java.util.List;


/**
 * Classe représentant un sort dans le jeu.
 * Un sort est une carte qui possède un type d'effet et une valeur associée.
 * Les effets peuvent inclure des dégâts, des soins, des pioches, ou des buffs.
 */
public class Sort extends Carte {

    private String typeEffet;   // Type d'effet du sort (dégât, soin, pioche, buff, etc.)
    private int valeurEffet;    // Valeur de l'effet (par exemple, 6 dégâts, 5 PV, etc.)

    /**
     * Constructeur de la classe Sort.
     *
     * @param nom         Le nom du sort.
     * @param mana        Le coût en mana du sort.
     * @param typeEffet   Le type d'effet du sort (dégât, soin, pioche, buff, etc.).
     * @param valeurEffet La valeur de l'effet du sort.
     */
    public Sort(String nom, int mana, String typeEffet, int valeurEffet) {
        super(nom, mana);
        this.typeEffet = typeEffet.toLowerCase();
        this.valeurEffet = valeurEffet;
    }

    /**
     * Applique l'effet du sort en fonction de son type.
     *
     * @param lanceur       Le joueur qui lance le sort.
     * @param cibleJoueur   Le joueur cible du sort (pour les dégâts ou les soins).
     * @param cibleServiteur Le serviteur cible du sort (pour les buffs).
     */
    public void utiliserSort(Joueur lanceur, Joueur cibleJoueur, Serviteur cibleServiteur) {
        switch (typeEffet) {
            case "degat":
                cibleJoueur.subirDegats(valeurEffet);
                System.out.println("\n--> " + nom + " inflige " + valeurEffet + " dégâts à " + cibleJoueur.getNom());
                break;

            case "soin":
                int nouveauxPV = lanceur.getPv() + valeurEffet;
                if (nouveauxPV > 30) nouveauxPV = 30;
                lanceur.setPv(nouveauxPV);
                System.out.println("\n--> " + nom + " rend " + valeurEffet + " PV à " + lanceur.getNom());
                break;

            case "pioche":
                Carte carte = lanceur.getDeck().piocher();
                if (carte != null) {
                    lanceur.ajouterCarteMain(carte);
                    System.out.println("\n--> " + nom + " permet de piocher : " + carte.getNom() + "\n");
                } else {
                    System.out.println("\n--> Deck vide, aucune carte piochée.\n");
                }
                break;

            case "buff":
                if (cibleServiteur != null) {
                    cibleServiteur.ajouterAttaque(valeurEffet);
                    cibleServiteur.ajouterVie(valeurEffet);
                    System.out.println("--> " + nom + " augmente " + cibleServiteur.getNom()
                        + " de +" + valeurEffet + " ATQ / +" + valeurEffet + " PV");
                } else {
                    System.out.println("\n--> Aucun serviteur ciblé pour le buff.");
                }
                break;
            
            case "degattous":
                List<Serviteur> serviteurs = cibleJoueur.getServiteursEnJeu();
                if (serviteurs.isEmpty()) {
                    System.out.println("\n--> Aucun serviteur adverse à toucher.");
                } else {
                    System.out.println("\n--> " + nom + " inflige " + valeurEffet + " dégâts à tous les serviteurs ennemis !");
                    for (int i = serviteurs.size() - 1; i >= 0; i--) { // parcours inversé pour éviter bugs si suppression
                        Serviteur s = serviteurs.get(i);
                        s.subirDegats(valeurEffet);
                        System.out.println("   - " + s.getNom() + " subit " + valeurEffet + " dégâts.");
                        if (s.estMort()) {
                            System.out.println("   - " + s.getNom() + " meurt.");
                            serviteurs.remove(s);
                        }
                    }
                }
                break;


            default:
                System.out.println("--> Effet inconnu : " + typeEffet);
        }
    }

    /**
     * Obtient le type d'effet du sort.
     *
     * @return Le type d'effet du sort.
     */
    public String getTypeEffet() {
        return typeEffet;
    }

    /**
     * Obtient la valeur de l'effet du sort.
     *
     * @return La valeur de l'effet du sort.
     */
    public int getValeurEffet() {
        return valeurEffet;
    }

    /**
     * Retourne une représentation textuelle du sort.
     *
     * @return Une chaîne de caractères représentant le sort.
     */
    @Override
    public String toString() {
        return nom + " - Sort (Mana : " + mana + ", Effet : " + typeEffet + " " + valeurEffet + ")";
    }
}
