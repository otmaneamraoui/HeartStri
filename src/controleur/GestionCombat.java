
package controleur;

import java.util.Scanner;
import modele.Serviteur;

/**
 * Classe de gestion des combats entre deux serviteurs.
 * Cette classe permet de simuler un combat tour par tour entre deux serviteurs,
 * avec des options pour recommencer le combat ou terminer.
 */
public class GestionCombat {

    /**
     * Lance un combat entre deux serviteurs.
     * Le combat se déroule en tours alternés où chaque serviteur attaque l'autre
     * jusqu'à ce que l'un des deux soit mort ou que les deux soient morts.
     * Une option est proposée à la fin pour recommencer le combat.
     *
     * @param s1 Le premier serviteur participant au combat.
     * @param s2 Le deuxième serviteur participant au combat.
     */
    public static void lancerCombat(Serviteur s1, Serviteur s2) {
        Scanner scanner = new Scanner(System.in); // Pour lire l'entrée de l'utilisateur
        System.out.println("=== Bienvenue dans le combat de serviteurs ! ===");
        System.out.println("--> Voici les serviteurs :");
        System.out.println("    1. " + s1.toString()); // Affiche le nom et les PV du serviteur 1
        System.out.println("    2. " + s2.toString()); // Affiche le nom et les PV du serviteur 2

        System.out.println("- Début du combat entre " + s1.getNom() + " et " + s2.getNom() + " !");

        int tour = 1; // Premier tour de combat

        while (!s1.estMort() && !s2.estMort()) { // Vérifie si les deux serviteurs sont vivants
            System.out.println("\n=== Tour " + tour + " ==="); // Affiche le numéro de tour
            // Serviteur 1 attaque Serviteur 2
            System.out.println(" - " + s1.getNom() + " attaque " + s2.getNom() + ".");
            s1.attaquer(s2);
            System.out.println(" - " + s2.getNom() + " a maintenant " + s2.getVie() + " PV.");

            // Vérifie si Serviteur 2 est mort
            if (s2.estMort()) {
                System.out.println(" --> " + s2.getNom() + " est mort !");
                System.out.println(" --> " + s1.getNom() + " est le vainqueur !");
                break;
            }

            // Serviteur 2 contre-attaque
            System.out.println(" - " + s2.getNom() + " contre-attaque " + s1.getNom() + ".");
            s2.attaquer(s1);
            System.out.println(" - " + s1.getNom() + " a maintenant " + s1.getVie() + " PV.");

            // Vérifie si Serviteur 1 est mort
            if (s1.estMort()) {
                System.out.println(" --> " + s1.getNom() + " est mort !");
                System.out.println(" --> " + s2.getNom() + " est le vainqueur !");
                break;
            }

            tour++; // Incrémentation du numéro de tour
        }

        // Affiche le résultat du combat
        if (s1.estMort() && s2.estMort()) {
            System.out.println(" --> Le combat se termine par une égalité !\n");
        }

        // Option pour recommencer le combat
        System.out.println("\n--> Voulez-vous recommencer le combat ? (oui/non)");
        String choix = scanner.nextLine().trim().toLowerCase();

        if (choix.equals("oui")) {
            s1.reinitialiser(); // Réinitialise Serviteur 1
            s2.reinitialiser(); // Réinitialise Serviteur 2
            lancerCombat(s1, s2); // Recommence le combat
        } else {
            System.out.println("\n===> Merci d'avoir joué !");
        }
    }
}
