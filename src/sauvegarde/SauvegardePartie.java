package sauvegarde;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.List;
import modele.*;

/**
 * Classe utilitaire pour sauvegarder l'état complet d'une partie dans un fichier texte.
 * Le fichier contient les informations des deux joueurs, y compris leurs héros,
 * mains, terrains, decks, ainsi que le joueur actif et le numéro du tour.
 */

public class SauvegardePartie {

	/**
     * Sauvegarde la partie complète dans un fichier texte.
     * Le fichier inclut les informations des deux joueurs, le joueur actif,
     * et le numéro du tour.
     *
     * @param fichier         Chemin du fichier où sauvegarder la partie.
     * @param j1              Le premier joueur.
     * @param j2              Le deuxième joueur.
     * @param nomJoueurActif  Le nom du joueur actuellement actif.
     * @param numeroTour      Le numéro actuel du tour.
     */
	public static void sauvegarderPartie(String fichier, Joueur j1, Joueur j2, String nomJoueurActif, int numeroTour) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(fichier))) {
        	// Entête : Joueur actif et numéro de tour
        	writer.write("TourActif;" + nomJoueurActif); // pour stocker le nom du joueur actif
            writer.newLine(); // pour séparer le joueur actif des joueurs
        	writer.write("NumeroTour;" + numeroTour);
        	writer.newLine();
        	
            // Sauvegarder les deux joueurs
            sauvegarderJoueur(writer, j1);
            writer.newLine(); // Séparation des joueurs
            sauvegarderJoueur(writer, j2);
            
            System.out.println("\n-------------------------------------------------");
            System.out.println("==> Partie sauvegardée dans : " + fichier);
            System.out.println("-------------------------------------------------");

        } catch (IOException e) {
            System.err.println("\n==> Erreur lors de la sauvegarde : " + e.getMessage());
        }
    }

	/**
     * Sauvegarde les informations d'un joueur dans le fichier.
     * Les informations incluent le nom, l'état général, le héros, la main,
     * le terrain, et le deck du joueur.
     *
     * @param writer Le `BufferedWriter` utilisé pour écrire dans le fichier.
     * @param joueur Le joueur dont les informations doivent être sauvegardées.
     * @throws IOException Si une erreur d'entrée/sortie se produit.
     */
    private static void sauvegarderJoueur(BufferedWriter writer, Joueur joueur) throws IOException {
        // Nom du joueur
        writer.write("Joueur;" + joueur.getNom());
        writer.newLine();

        // État général du joueur (mana et PV)
        writer.write("Etat;" + joueur.getMana() + ";" + joueur.getPv());
        writer.newLine();

        // Héros
        Heros h = joueur.getHeros();
        writer.write("Heros;" + h.getNom() + ";" + h.getPouvoirSpecial() + ";" + h.getPv());
        writer.newLine();
        

        // Main
        for (Carte carte : joueur.getMain()) {
            writer.write("Main;" + formaterCarte(carte));
            writer.newLine();
        }

        // Terrain (serviteurs en jeu)
        for (Serviteur s : joueur.getServiteursEnJeu()) {
            writer.write("Terrain;" + "Serviteur;" + s.getNom() + ";" + s.getMana() + ";" + s.getAttaque() + ";" + s.getVie());
            writer.newLine();
        }

        // Deck
        for (Carte carte : joueur.getDeck().getCartes()) {
            writer.write("Deck;" + formaterCarte(carte));
            writer.newLine();
        }
    }


    /**
     * Formate une carte en chaîne de caractères en fonction de son type.
     * Les types de cartes incluent les serviteurs, les armes, les sorts, et les cartes génériques.
     *
     * @param carte La carte à formater.
     * @return Une chaîne de caractères représentant la carte.
     */
    private static String formaterCarte(Carte carte) {
        if (carte instanceof Serviteur s) {
            return "Serviteur;" + s.getNom() + ";" + s.getMana() + ";" + s.getAttaque() + ";" + s.getVie();
        } else if (carte instanceof Arme a) {
            return "Arme;" + a.getNom() + ";" + a.getMana() + ";" + a.getAttaque() + ";" + a.getDurabilite();
        } else if (carte instanceof Sort sort) {
            return "Sort;" + sort.getNom() + ";" + sort.getMana() + ";" + sort.getTypeEffet() + ";" + sort.getValeurEffet();
        }
        return "Carte;" + carte.getNom() + ";" + carte.getMana();
    }
}
