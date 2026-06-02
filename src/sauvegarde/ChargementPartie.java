package sauvegarde;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.*;

import modele.*;

/**
 * Classe utilitaire pour charger l'état d'une partie à partir d'un fichier texte.
 * Le fichier doit contenir les informations nécessaires pour recréer les joueurs,
 * leurs decks, leurs mains, leurs terrains, et d'autres détails de la partie.
 */

public class ChargementPartie {

	/**
     * Charge une partie à partir d’un fichier texte.
     * Le fichier doit suivre un format spécifique pour inclure les informations
     * sur les joueurs, leurs héros, leurs decks, leurs mains, et l'état du jeu.
     *
     * @param cheminFichier Le chemin du fichier texte contenant les données de la partie.
     * @return Un objet `EtatPartie` contenant les informations des deux joueurs,
     *         le nom du joueur actif, et le numéro du tour.
     */
	public static EtatPartie chargerPartie(String cheminFichier) {
	    Joueur joueur1 = null;
	    Joueur joueur2 = null;
	    String nomJoueurActif = null;
	    int numeroTour = 1;

	    try (BufferedReader reader = new BufferedReader(new FileReader(cheminFichier))) {
	        String ligne;
	        Joueur joueurActuel = null;
	        Deck deckTemp = new Deck();
	        List<Carte> mainTemp = new ArrayList<>();
	        List<Serviteur> terrainTemp = new ArrayList<>();
	        String nom = null;
	        Heros heros = null;
	        int mana = 0;
	        int pv = 0;

	        int compteurJoueur = 0;

	        while ((ligne = reader.readLine()) != null) {
	            if (ligne.trim().isEmpty()) continue;

	            String[] parts = ligne.split(";");
	            switch (parts[0]) {
	            
	                case "TourActif":
	                    nomJoueurActif = parts[1];
	                    break;
	                    
	                case "NumeroTour":
		                numeroTour = Integer.parseInt(parts[1]);
		                break;

	                case "Joueur":
	                    if (compteurJoueur == 1) {
	                        Joueur temp = new Joueur(nom, deckTemp, heros);
	                        temp.setMana(mana);
	                        temp.setPv(pv);
	                        temp.getMain().addAll(mainTemp);
	                        temp.getServiteursEnJeu().addAll(terrainTemp);
	                        joueur1 = temp;

	                        // Réinitialisation
	                        deckTemp = new Deck();
	                        mainTemp = new ArrayList<>();
	                        terrainTemp = new ArrayList<>();
	                    }
	                    nom = parts[1];
	                    compteurJoueur++;
	                    break;

	                case "Etat":
	                    mana = Integer.parseInt(parts[1]);
	                    pv = Integer.parseInt(parts[2]);
	                    break;

	                case "Heros":
	                    String nomHeros = parts[1];
	                    String pouvoir = parts[2];
	                    int pvH = Integer.parseInt(parts[3]);
	                    heros = creerHerosDepuisNom(nomHeros, pouvoir, pvH);
	                    break;

	                case "Main":
	                    mainTemp.add(creerCarteDepuisTexte(Arrays.copyOfRange(parts, 1, parts.length)));
	                    break;

	                case "Terrain":
	                    terrainTemp.add((Serviteur) creerCarteDepuisTexte(Arrays.copyOfRange(parts, 1, parts.length)));
	                    break;

	                case "Deck":
	                    deckTemp.ajouterCarte(creerCarteDepuisTexte(Arrays.copyOfRange(parts, 1, parts.length)));
	                    break;
	            }
	        }

	        // Dernier joueur
	        Joueur temp = new Joueur(nom, deckTemp, heros);
	        temp.setMana(mana);
	        temp.setPv(pv);
	        temp.getMain().addAll(mainTemp);
	        temp.getServiteursEnJeu().addAll(terrainTemp);
	        joueur2 = temp;

	    } catch (IOException e) {
	        System.err.println("\n==> Erreur lors du chargement de la partie : " + e.getMessage());
	    }

	    return new EtatPartie(joueur1, joueur2, nomJoueurActif, numeroTour);

	    
	}


	/**
     * Crée une carte à partir d'un tableau de données textuelles.
     * Les données doivent inclure le type de carte et ses attributs spécifiques.
     *
     * @param data Un tableau de chaînes représentant les attributs de la carte.
     * @return Une instance de `Carte` correspondant aux données fournies,
     *         ou `null` si le type de carte est inconnu.
     */
    private static Carte creerCarteDepuisTexte(String[] data) {
        String typeCarte = data[0];
        switch (typeCarte) {
            case "Serviteur":
                return new Serviteur(data[1], Integer.parseInt(data[2]), Integer.parseInt(data[3]), Integer.parseInt(data[4]));
            case "Arme":
                return new Arme(data[1], Integer.parseInt(data[2]), Integer.parseInt(data[3]), Integer.parseInt(data[4]));
            case "Sort":
                return new Sort(data[1], Integer.parseInt(data[2]), data[3], Integer.parseInt(data[4]));
            default:
            	System.err.println("\n==> Type de carte inconnu : " + typeCarte);
                return null;
        }
    }

    /**
     * Crée un héros à partir de son nom, de son pouvoir, et de ses points de vie.
     * Si le nom du héros est inconnu, un héros par défaut (Mage) est créé.
     *
     * @param nom Le nom du héros.
     * @param pouvoir Le pouvoir spécial du héros.
     * @param pv Les points de vie du héros.
     * @return Une instance de `Heros` correspondant au nom fourni.
     */
    private static Heros creerHerosDepuisNom(String nom, String pouvoir, int pv) {
        return switch (nom.toLowerCase()) {
            case "chasseur" -> new Chasseur();
            case "druide" -> new Druide();
            case "guerrier" -> new Guerrier();
            case "mage" -> new Mage();
            case "paladin" -> new Paladin();
            case "prêtre", "pretre" -> new Pretre();
            case "voleur" -> new Voleur();
            default -> new Mage(); // par défaut
        };
    }
}
