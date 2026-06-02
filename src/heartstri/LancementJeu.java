
package heartstri;

import vue.ConsoleUI;

/**
 * Classe principale pour lancer le jeu Heartstri.
 * Cette classe contient la méthode main qui initialise et démarre le jeu.
 */
public class LancementJeu {

    /**
     * Méthode principale pour exécuter le jeu.
     * Affiche un message d'introduction et démarre l'interface utilisateur via la classe ConsoleUI.
     *
     * @param args Arguments de la ligne de commande (non utilisés).
     */
    public static void main(String[] args) {
        System.out.println("		**********************************");
        System.out.println("		|   HEARTSTRI by Otmane Amraoui  |");
        System.out.println("		**********************************\n");

        ConsoleUI ui = new ConsoleUI(); // Instance de la classe ConsoleUI
        ui.demarrer(); // Appel de la méthode pour démarrer le jeu
    }
}
