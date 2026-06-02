
package vue;

import modele.Arme;  
import sauvegarde.*;
import modele.Carte; 
import modele.Deck;
import modele.Serviteur;
import controleur.GestionCombat;
import modele.Joueur;
import modele.Heros;
import java.util.List;
import modele.Sort;
import javax.sound.sampled.*; // pour le son
import java.util.Scanner; // pour la lecture de l'entrée utilisateur
import java.io.File; // pour le fichier de son

/**
 * Classe représentant l'interface utilisateur en mode console.
 * Permet de gérer les interactions avec les joueurs, comme le choix des héros,
 * la gestion des tours, les actions possibles, et le déroulement de la partie.
 */

public class ConsoleUI {
	
	/**
     * Le premier joueur de la partie.
     */
	private Joueur joueur1;
	/**
     * Le deuxième joueur de la partie.
     */
	private Joueur joueur2;
	/**
     * Le numéro actuel du tour.
     */
	private int numeroTour = 1;

	/**
     * Scanner pour lire les entrées utilisateur.
     */
	private Scanner scanner = new Scanner(System.in);

	/**
     * Affiche le menu principal de l'application.
     */
	public void afficherMenuPrincipal() {
		System.out.println("\n=====================================================================");
		System.out.println(" =========================|MENU PRINCIPAL|==========================");
		System.out.println("=====================================================================");
		System.out.println("	1. Démarrer une nouvelle partie");
	    System.out.println("	2. Reprendre une partie sauvegardée");
	    System.out.println("	3. Quitter");
		System.out.print("	- Choix : ");

	}

	/**
     * Initialise une nouvelle partie en créant les decks et en choisissant les héros.
     */
	public void initialiserPartie() {
	    // Création des decks
	    Deck deckJ1 = creerDeck();
	    Deck deckJ2 = creerDeck();

	    // Choix des héros
	    System.out.println("\n=== Joueur 1, choisissez votre héros ===");
	    Heros heros1 = choisirHeros();

	    System.out.println("\n=== Joueur 2, choisissez votre héros ===");
	    Heros heros2 = choisirHeros();

	    // Création des joueurs
	    joueur1 = new Joueur("Joueur 1", deckJ1, heros1);
	    joueur2 = new Joueur("Joueur 2", deckJ2, heros2);

	    // Pioche initiale
	    // joueur 1 pioche 3 cartes 
	    for (int i = 0; i < 2; i++) {
	        joueur1.ajouterCarteMain(joueur1.getDeck().piocher());
	    }
	    // joueur 2 pioche 4 cartes
	    for (int i = 0; i < 3; i++) {
	        joueur2.ajouterCarteMain(joueur2.getDeck().piocher());
	    }
	}
	
	/**
     * Lance une partie entre deux joueurs.
     */
	public void lancerPartie() {
	    boolean partieEnCours = true; // pour 

	    while (partieEnCours) {
	    	// ------>System.out.println("\n=== Début du tour " + numeroTour + " ===");
	    	// Recharge le mana des deux joueurs selon le tour (max 10)
	        joueur1.rechargerMana(numeroTour);
	        joueur2.rechargerMana(numeroTour);
	        // Tour joueur 1
	        jouerTour(joueur1, joueur2);
	        if (joueur2.getPv() <= 0) {
	            afficherVictoire(joueur1);
	            break;
	        }
	        //Tour joueur 2
	        jouerTour(joueur2, joueur1);
	        if (joueur1.getPv() <= 0) {
	            break;
	        }
	        	        
	      
            // Incrémente le numéro du tour
	        numeroTour++;
	    }
	}

	int nDeck = 0; // pour le numéro de deck

	/**
     * Crée un deck pour un joueur avec des cartes prédéfinies.
     *
     * @return Un objet `Deck` contenant les cartes du joueur.
     */
	public Deck creerDeck() {
		System.out.println("\nCréation du deck du joueur N°" + nDeck + "...");
		nDeck++;
		Deck deck = new Deck(); // création d'un nouveau deck
		deck.ajouterCarte(new Serviteur("Chevalier", 3, 5, 8)); // ajout de cartes au deck avec des attributs : nom, mana, attaque, vie
		deck.ajouterCarte(new Serviteur("Dragon", 7, 8, 9));
		deck.ajouterCarte(new Serviteur("Gobelin", 2, 3, 4));
		deck.ajouterCarte(new Serviteur("Sorcier", 4, 6, 5));
		deck.ajouterCarte(new Serviteur("Viking", 3, 5, 6));
		deck.ajouterCarte(new Serviteur("Nécromancien", 6, 6, 7));
		deck.ajouterCarte(new Serviteur("Ronaldo", 1, 2, 3));
		deck.ajouterCarte(new Serviteur("Messi", 10, 10, 10));
		deck.ajouterCarte(new Serviteur("Chevalier de Givre", 4, 5, 6));
		deck.ajouterCarte(new Serviteur("Pyromancien fou", 3, 4, 2));
		deck.ajouterCarte(new Serviteur("Garde du Sanctuaire", 4, 2, 7));
		deck.ajouterCarte(new Serviteur("Lutin agile", 2, 2, 3));
		deck.ajouterCarte(new Serviteur("Chasseur de dragons", 6, 6, 5));
		deck.ajouterCarte(new Serviteur("Samouraï mystique", 4, 6, 5));
		deck.ajouterCarte(new Serviteur("Écuyer novice", 1, 1, 2));
		deck.ajouterCarte(new Serviteur("Soldat rebelle", 2, 2, 3));
		deck.ajouterCarte(new Sort("Boule de feu", 4, "degat", 6));
		deck.ajouterCarte(new Sort("Soin sacré", 3, "soin", 5));
		deck.ajouterCarte(new Sort("Clairvoyance", 2, "pioche", 1));
		deck.ajouterCarte(new Sort("Bénédiction de puissance", 2, "buff", 2));
		deck.ajouterCarte(new Sort("Lumière guérissante", 3, "soin", 4));
		deck.ajouterCarte(new Sort("Explosion de flammes", 5, "degatTous", 3)); 
		deck.ajouterCarte(new Sort("Vision prophétique", 2, "pioche", 2));
		deck.ajouterCarte(new Sort("Bénédiction des anciens", 3, "buff", 2));
		deck.ajouterCarte(new Sort("Choc électrique", 2, "degat", 2));
		deck.ajouterCarte(new Sort("Choc mineur", 1, "degat", 1));
		deck.ajouterCarte(new Arme("Dague de l’ombre", 2, 2, 2)); // ATQ 2, 2 utilisations
		deck.ajouterCarte(new Arme("Épée bénie", 5, 3, 3));        // ATQ 4, 3 utilisations
        deck.ajouterCarte(new Arme("Hache de guerre", 6, 4, 4)); // ATQ 5, 4 utilisations
        deck.ajouterCarte(new Arme("Arc long", 4, 3, 3));        // ATQ 3, 5 utilisations
        deck.ajouterCarte(new Arme("L'épée marocain", 2, 5, 5)); // ATQ 4, 6 utilisations
        deck.ajouterCarte(new Arme("Lame d'argent", 3, 3, 2));
        deck.ajouterCarte(new Arme("Marteau du destin", 6, 5, 1));
        deck.ajouterCarte(new Arme("Poignard cassé", 1, 1, 1));
		System.out.println("\n--> Deck créé avec succès !");
		return deck;
	}

	/**
     * Lance un combat entre deux serviteurs piochés d'un deck.
     *
     * @param deck Le deck contenant les serviteurs.
     */
	public void lancerCombat(Deck deck) {
		// Vérifie si le deck contient au moins 2 cartes
		if (deck.taille() < 2) {
			System.out.println("\n--> Pas assez de cartes dans le deck pour lancer un combat.\n"); // il faut au moins 2
																									// cartes pour un
																									// combat
			return;
		}
		// Pioche deux serviteurs du deck
		Serviteur s1 = (Serviteur) deck.piocher();
		Serviteur s2 = (Serviteur) deck.piocher();
		// Affiche les informations des serviteurs
		System.out.println("- Combat entre " + s1.getNom() + " et " + s2.getNom() + ".");
		// Lance le combat entre les deux serviteurs
		GestionCombat.lancerCombat(s1, s2);
	}

	/**
     * Permet à un joueur de jouer une carte de sa main.
     *
     * @param joueurActif  Le joueur qui joue la carte.
     * @param joueurCible  Le joueur adverse.
     */
	public void jouerCarte(Joueur joueurActif, Joueur joueurCible) {
	    joueurActif.afficherMain();
	    System.out.print("- Numéro de la carte à jouer (0 pour retourner au menu précédent) : ");
	    int num = scanner.nextInt() - 1;
	    scanner.nextLine();
	    
	    if (num == -1) {
	        System.out.println("\n--> Retour au menu précédent.");
	        return;
	    }

	    if (num < 0 || num >= joueurActif.getMain().size()) {
	        System.out.println("--> Numéro invalide.");
	        return;
	    }

	    Carte carte = joueurActif.getMain().get(num);

	    if (joueurActif.getMana() < carte.getMana()) {
	        System.out.println("--> Pas assez de mana pour jouer cette carte.");
	        return;
	    }

	    joueurActif.setMana(joueurActif.getMana() - carte.getMana());
	    joueurActif.retirerCarteMain(carte);

	    // === Si c'est un serviteur, on le place sur le terrain
	    if (carte instanceof Serviteur) {
	        joueurActif.poserServiteur((Serviteur) carte);
	        System.out.println("\n ->> Serviteur " + carte.getNom() + " est placé sur le terrain.");
	    }

	    // Si c'est un sort, on applique l'effet
	    else if (carte instanceof Sort) {
	        Sort sort = (Sort) carte;
	        String effet = sort.getTypeEffet();

	        // Si le sort est de type "buff", il a besoin d’un serviteur cible
	        Serviteur cible = null;
	        if (effet.equals("buff")) {
	            if (joueurActif.getServiteursEnJeu().isEmpty()) {
	                System.out.println("\n --> Aucun serviteur à cibler pour le buff.");
	                return;
	            }
	            // Choisir un serviteur à cibler
	            System.out.println("\n --> Vos serviteurs :");
	            joueurActif.afficherServiteursEnJeu();
	            System.out.print("- Numéro du serviteur à renforcer : ");
	            int choix = scanner.nextInt() - 1;
	            scanner.nextLine();

	            if (choix >= 0 && choix < joueurActif.getServiteursEnJeu().size()) {
	                cible = joueurActif.getServiteursEnJeu().get(choix);
	            } else {
	                System.out.println("\n --> Choix invalide.");
	                return;
	            }
	        }

	        // Exécuter l'effet du sort
	        sort.utiliserSort(joueurActif, joueurCible, cible);
	        
	      // Si c'est une arme, on l'équipe au héros 
	    } else if (carte instanceof Arme) {
	        Arme arme = (Arme) carte;
	        joueurActif.setArme(arme); // équipe l'arme
	        System.out.println("\n ->> Arme " + arme.getNom() + " équipée !");
	    }

	}
	
	/**
     * Permet à un joueur d'utiliser le pouvoir spécial de son héros.
     *
     * @param joueurActif   Le joueur actif.
     * @param joueurAdverse Le joueur adverse.
     */
	public void utiliserPouvoirSpecial(Joueur joueurActif, Joueur joueurAdverse) {
	    Heros heros = joueurActif.getHeros(); // récupérer le héros du joueur actif
	    String nomHeros = heros.getNom().toLowerCase(); // récupérer le nom du héros en minuscule
	    
	 // Vérifie si le joueur a déjà utilisé son pouvoir spécial ce tour
	 	if (joueurActif.getPouvoirUtiliseCeTour()) {
	 		System.out.println("\n--> Vous avez déjà utilisé votre pouvoir spécial ce tour !");
	 		return;
	    }

	 	if (joueurAdverse == null) {
	        System.out.println("--> Aucun pouvoir appliqué.");
	        return;
	    }

	    switch (nomHeros) { // traitement selon le héros 
	        case "chasseur":
	            heros.utiliserPouvoirSpecial(joueurActif, joueurAdverse);
	            joueurActif.setPouvoirUtiliseCeTour(true); // marquer le pouvoir comme utilisé
	            break;

			case "mage": // partage le même bloc d'instruction avec prêtre
	        case "prêtre":
	            System.out.println("\n=== Choisissez la cible ===");
	            System.out.println("	1. Un serviteur ennemi");
	            System.out.println("	2. Le héros adverse");
	            int choix;
	            do {
	                System.out.print("   - Votre choix : ");
	                choix = scanner.nextInt();
	                scanner.nextLine();
	                if (choix == 1 || choix == 2) break;
	                System.out.println("\n--> Choix invalide.");
	            } while (true);


	            if (choix == 1) {
	                if (joueurAdverse.getServiteursEnJeu().isEmpty()) {
	                    System.out.println("\n--> Aucun serviteur en jeu.");
	                } else {
	                    joueurAdverse.afficherServiteursEnJeu();
	                    System.out.print("- Numéro du serviteur : ");
	                    int num = scanner.nextInt() - 1;
	                    scanner.nextLine();

	                    if (num >= 0 && num < joueurAdverse.getServiteursEnJeu().size()) {
	                        heros.utiliserPouvoirSpecial(joueurActif, joueurAdverse.getServiteursEnJeu().get(num));
	                        joueurActif.setPouvoirUtiliseCeTour(true); // marquer le pouvoir comme utilisé
	                    } else {
	                        System.out.println("\n--> Numéro invalide.");
	                    }
	                }
	            } else if (choix == 2) {
	                heros.utiliserPouvoirSpecial(joueurActif, joueurAdverse);
	                joueurActif.setPouvoirUtiliseCeTour(true); // marquer le pouvoir comme utilisé
	            } else {
	                System.out.println("--> Choix invalide.");
	            }
	            break;
	            
	           // Ces héros n'ont pas besoin de choisir une cible
	            // et ils partagent le même bloc d'instruction
	        case "paladin":
	        case "guerrier":
	        case "druide":
	        case "voleur":
	            // Pas besoin de cible
	            heros.utiliserPouvoirSpecial(joueurActif, null);
	            joueurActif.setPouvoirUtiliseCeTour(true); // marquer le pouvoir comme utilisé
	            break;

	        default:
	            System.out.println("\n--> Héros inconnu.");
	    }
	}


	/**
     * Permet à un joueur de choisir un héros parmi une liste prédéfinie.
     *
     * @return Une instance de `Heros` correspondant au choix du joueur.
     */
	public Heros choisirHeros() {
		System.out.println("	1. Chasseur (Inflige 2 points de dégâts - Mana : 3 )");
		System.out.println("	2. Druide (+1 ATQ, +1 armure - Mana : 2)");
		System.out.println("	3. Guerrier (+2 armure - Mana: 4)");
		System.out.println("	4. Mage (Inflige 1 dégât - Mana : 3)");
		System.out.println("	5. Paladin (Invoque une recrue 1/1 - Mana : 2)");
		System.out.println("	6. Prêtre (Rend 2 PV - Mana : 4)");
		System.out.println("	7. Voleur (Équipe une dague 1/2 - Mana : 5)");
		
		int choix; // pour le choix de l'utilisateur
		do {
		    System.out.print("   - Votre choix : ");
		    choix = scanner.nextInt();
		    scanner.nextLine();
		    if (choix >= 1 && choix <= 7) break;
		    System.out.println("\n--> Choix invalide.");
		} while (true);


		switch (choix) {
		case 1:
			return new modele.Chasseur();
		case 2:
			return new modele.Druide();
		case 3:
			return new modele.Guerrier();
		case 4:
			return new modele.Mage();
		case 5:
			return new modele.Paladin();
		case 6:
			return new modele.Pretre();
		case 7:
			return new modele.Voleur();
		default:
			System.out.println("\n--> Choix invalide, Mage sélectionné par défaut.\n");
			return new modele.Mage();
		}
	}
	
	/**
     * Lance une partie à partir d'un état sauvegardé.
     *
     * @param actif Le joueur actif au moment de la reprise.
     * @param autre L'autre joueur.
     */
	public void lancerPartieDepuis(Joueur actif, Joueur autre) {
		
		boolean partieEnCours = true;

	    
	    while (partieEnCours) {
	        System.out.println("\n=== Début du tour " + numeroTour + " ===");

	        // Recharge le mana des deux joueurs selon le tour (max 10)
	        joueur1.rechargerMana(numeroTour);
	        joueur2.rechargerMana(numeroTour);

	        // Normal gameplay
	        jouerTour(actif, autre);
	        if (autre.getPv() <= 0) {
	            afficherVictoire(actif);
	            break;
	        }

	        // Inversion des joueurs
	        Joueur temp = actif;
	        actif = autre;
	        autre = temp;

	        // Incrémente le numéro du tour seulement si les 2 joueurs ont joué
	        if (actif == joueur1) {
	            numeroTour++;
	        }
	    }
	}

	/**
     * Démarre l'application et gère le menu principal.
     */
	public void demarrer() {
	    boolean quitter = false; // Pour quitter la boucle principale
	    boolean partieInitialisee = false; // Indique si une partie a été lancée (avec 2 joueurs)

	    while (!quitter) {
	    	afficherMenuPrincipal(); // Affiche les options disponibles
	        int choix = scanner.nextInt(); // Pour lecture du choix utilisateur
	        scanner.nextLine(); 

	        switch (choix) {
	        case 1:
                initialiserPartie(); // crée les deux joueurs
                lancerPartie();      // boucle principale du jeu
                break;
	        case 2:
	            EtatPartie etat = ChargementPartie.chargerPartie("sauvegardes/sauvegarde_partie.txt");
	            // affecte les joueurs et le numéro de tour
	            this.joueur1 = etat.joueur1;
	            this.joueur2 = etat.joueur2;
	            String actif = etat.joueurActif;
	            this.numeroTour = etat.getNumeroTour();

	            if (joueur1 != null && joueur2 != null) {
	                System.out.println("\n==> Partie chargée avec succès !");
	                
	             // Déterminer qui commence (selon nom stocké)
	                if (joueur1.getNom().equals(actif)) {
	                    lancerPartieDepuis(joueur1, joueur2);
	                } else {
	                    lancerPartieDepuis(joueur2, joueur1);
	                } 
	            } else {
	                System.out.println("\n==> Erreur lors du chargement de la partie.");
	            }
	            break;
            case 3:
                quitter = true;
                System.out.println("\n--> Au revoir !");
                break;
            default:
                System.out.println("--> Choix invalide. Essayez encore.");
	        }

	        // Vérifie si un joueur est mort et annonce le vainqueur
	        if (partieInitialisee && (joueur1.getPv() <= 0 || joueur2.getPv() <= 0)) {
	            System.out.println("\n--> La partie est terminée !");
	            if (joueur1.getPv() <= 0) {
	                System.out.println(">>> Joueur 2 gagne !");
	            } else {
	                System.out.println(">>> Joueur 1 gagne !");
	            }
	            break;
	        }
	    }
	}
	
	/**
     * Gère le tour d'un joueur, y compris les actions possibles.
     *
     * @param joueurActif   Le joueur dont c'est le tour.
     * @param joueurAdverse Le joueur adverse.
     */
	public void jouerTour(Joueur joueurActif, Joueur joueurAdverse) {
		System.out.println("\n-----------------------------------------------------------------------------");
	    System.out.println("\n--> Tour de " + joueurActif.getNom());

	 // Pioche une carte au début du tour
	    Carte pioche = joueurActif.getDeck().piocher();
	    if (pioche != null) {
	        joueurActif.ajouterCarteMain(pioche);
	    } else {
	        System.out.println("\n--> Deck vide, aucune carte piochée.\n");
	    }

	    boolean fini = false;

	    while (!fini) {
	        // Affiche les options du tour
	    	System.out.println("\n=============================================================================");
	    	System.out.println(" =====> Tour n°" + numeroTour + " de " + joueurActif.getNom() + " - Héros : " + joueurActif.getHeros().getDescription() );
	    	System.out.println("=============================================================================");
	    	// Affiche les cartes dans la main et le mana disponible
		    joueurActif.afficherMain();
		    System.out.println(" ==> Mana disponible : " + joueurActif.getMana());
		    System.out.println("\n=== Actions possibles ===");
	    	System.out.println("	1. Jouer une carte.");
	    	if (joueurActif.getPouvoirUtiliseCeTour()) {
	    	    System.out.println("	2. Utiliser le pouvoir spécial (déjà utilisé ce tour).");
	    	} else {
	    	    System.out.println("	2. Utiliser le pouvoir spécial.");
	    	}
	    	System.out.println("	3. Attaquer avec un serviteur.");
	    	System.out.println("	4. Attaquer avec une Arme.");
	    	System.out.println("	5. Afficher les serviteurs en jeu.");
	    	System.out.println("	6. Passer le tour.");
	    	System.out.println("	7. Sauvegarder et quitter la partie.");
	    	System.out.println("	8. Quitter sans sauvegarder.");
	    	
	        int choix; // pour le choix de l'utilisateur
	        do {
	            System.out.print("   - Votre choix : ");
	            choix = scanner.nextInt();
	            scanner.nextLine();
	            if (choix >= 1 && choix <= 7) break;
	            System.out.println("\n--> Choix invalide.");
	        } while (true);

	        
	        switch (choix) {
	        case 1: // L'utilisateur choisit une carte de la main à jouer (serviteur ou sort)
	            jouerCarte(joueurActif, joueurAdverse);
	            break;


	        case 2:
	            // Utilisation du pouvoir spécial du héros
	            utiliserPouvoirSpecial(joueurActif, joueurAdverse);
	            break;

	        case 3:
	            // Attaquer avec un serviteur
	            attaquer(joueurActif, joueurAdverse);
	            // Vérifie si le joueur adverse est mort
	            if (joueurAdverse.getPv() <= 0) {
	                afficherVictoire(joueurActif); // Déclare le vainqueur
	                System.exit(0); // Quitte le programme
	            }
	            break;
	            
	        case 4:
	            joueurActif.attaquerAvecArme(joueurAdverse);
	            // Vérifie si le joueur adverse est mort
	            if (joueurAdverse.getPv() <= 0) {
	                afficherVictoire(joueurActif); // Déclare le vainqueur
	                System.exit(0); // Quitte le programme
	            }
	            break;

	        case 5:
	            // Afficher les serviteurs en jeu des deux joueurs
	            System.out.println("\n	--- " + joueurActif.getNom() + " ---");
	            joueurActif.afficherServiteursEnJeu();
	            System.out.println("\n	--- " + joueurAdverse.getNom() + " ---");
	            joueurAdverse.afficherServiteursEnJeu();
	            break;

	        case 6:
	            // Fin du tour
	            fini = true;
	            break;
	            
	        case 7:
	        	SauvegardePartie.sauvegarderPartie("sauvegardes/sauvegarde_partie.txt", joueur1, joueur2, joueurActif.getNom(), numeroTour);
	            System.out.print("\n==> Voulez-vous vraiment quitter ? (oui/non) : ");
	            String reponse = scanner.nextLine().trim().toLowerCase();
	            if (reponse.equals("oui") || reponse.equals("o")) {
	                System.out.println("\n--> À bientôt !");
	                System.exit(0);
	            } else {
	                System.out.println("\n--> Retour au tour en cours.");
	            }
	            break;
	        case 8:
				System.out.print("\n==> Voulez-vous vraiment quitter ? (oui/non) : ");
				String reponse2 = scanner.nextLine().trim().toLowerCase();
				if (reponse2.equals("oui") || reponse2.equals("o")) {
					System.out.println("\n--> À bientôt !");
					System.exit(0);
				} else {
					System.out.println("\n--> Retour au tour en cours.");
				}
				break;

	        default:
	        	System.out.println("--> Choix non reconnu.");
	        }

	    }

	    System.out.println("\n --> Fin du tour de " + joueurActif.getNom());
	    System.out.println("\n-----------------------------------------------------------------------------");
	    joueurActif.reinitialiserAttaqueTemporaire(); // on réinitialise si le joueur a utilisé le pouvoir spécial du druide
	    joueurActif.setPouvoirUtiliseCeTour(false); // Réinitialise pour le prochain tour
	    // Réinitialise les serviteurs pour le prochain tour
	    for (Serviteur s : joueurActif.getServiteursEnJeu()) {
	        s.setAttaqueCeTour(false);
	    }
	    // Réinitialise l'arme pour le prochain tour
	    joueurActif.setAttaqueAvecArmeCeTour(false);


	}

	/**
     * Permet à un joueur d'attaquer avec un serviteur ou une arme.
     *
     * @param joueurAttaquant Le joueur qui attaque.
     * @param joueurDefenseur Le joueur qui défend.
     */
	public void attaquer(Joueur joueurAttaquant, Joueur joueurDefenseur) {
	    // Liste des serviteurs en jeu chez l'attaquant et le défenseur
	    List<Serviteur> listeAttaquants = joueurAttaquant.getServiteursEnJeu();
	    List<Serviteur> listeDefenseurs = joueurDefenseur.getServiteursEnJeu();

	    // Vérifie que l'attaquant a bien des serviteurs
	    if (listeAttaquants.isEmpty()) {
	        System.out.println("\n --> " + joueurAttaquant.getNom() + " n'a aucun serviteur pour attaquer.");
	        return;
	    }

	    // Affiche les serviteurs disponibles pour attaquer
	    System.out.println("\n --- " + joueurAttaquant.getNom() + " --- (Vos serviteurs) ");
	    joueurAttaquant.afficherServiteursEnJeu();
	    System.out.print(" - Quel serviteur attaque ? (numéro, 0 pour annuler) : ");
	    int i = scanner.nextInt() - 1;
	    scanner.nextLine();
	    
	    // si le joueur entre 0, il annule l'attaque
	    if (i == -1) {
	        System.out.println("--> Retour au menu principal.");
	        return;
	    }
	    
	    // Vérifie que le numéro est valide
	    if (i < 0 || i >= listeAttaquants.size()) {
	        System.out.println("--> Choix invalide.");
	        return;
	    }

	    // Récupère le serviteur qui attaque
	    Serviteur sAttaquant = listeAttaquants.get(i);
	    
	    // Vérifie que le serviteur n'a pas été déjà utilisé ce tour
	    if (sAttaquant.aAttaqueCeTour()) {
	        System.out.println("\n--> Ce serviteur a déjà attaqué ce tour.");
	        return;
	    }

	    // S'il y a des serviteurs en face, le joueur ddiot en cibler un
	    if (!listeDefenseurs.isEmpty()) {
	        System.out.println("\n --- Serviteurs adverses ---");
	        joueurDefenseur.afficherServiteursEnJeu();
	        System.out.print(" - Cible à attaquer (numéro, 0 pour annuler) : ");
	        int j = scanner.nextInt() - 1; // -1 pour le numéro de la carte
	        scanner.nextLine();
	        
	        // si le joueur entre 0, il annule l'attaque
	        if (j == -1) {
	            System.out.println("--> Retour au menu principal.");
	            return;
	        }
	        
	        // Vérifie que le numéro est valide
	        if (j < 0 || j >= listeDefenseurs.size()) {
	            System.out.println("--> Choix invalide.");
	            return;
	        }

	        // Récupère le serviteur qui défend
	        Serviteur sDefenseur = listeDefenseurs.get(j);

	        // Combat : chacun inflige ses dégâts à l'autre
	        sDefenseur.subirDegats(sAttaquant.getAttaque());
	        sAttaquant.subirDegats(sDefenseur.getAttaque());

	        System.out.println("\n --> " + sAttaquant.getNom() + " attaque " + sDefenseur.getNom());

	        // Suppression des serviteurs morts
	        if (sAttaquant.estMort()) {
	            System.out.println("\n--> " + sAttaquant.getNom() + " est mort.");
	            listeAttaquants.remove(sAttaquant);
	        }

	        if (sDefenseur.estMort()) {
	            System.out.println("\n--> " + sDefenseur.getNom() + " est mort.");
	            listeDefenseurs.remove(sDefenseur);
	        }

	    } else {
	        // Aucun serviteur en face alors on attaque le héros 
	        System.out.println("\n--> Pas de serviteur en face. Attaque du héros adverse !");
	        joueurDefenseur.subirDegats(sAttaquant.getAttaque());
	        System.out.println("\n --> " + joueurDefenseur.getNom() + " perd " + sAttaquant.getAttaque() + " PV.");
	        // Vérifie si le héros adverse est mort
	        if (joueurDefenseur.getPv() <= 0) {
	            afficherVictoire(joueurAttaquant);
	            System.exit(0);
	        }
	    }
	    sAttaquant.setAttaqueCeTour(true);
	}
	
	/**
     * Joue un son de victoire à la fin de la partie.
     */
	public void jouerSonVictoire() {
	    try {
	    	File son = new File("ressources/son-victoire.wav"); // chemin vers le fichier audio
	        if (!son.exists()) {
	            System.out.println("--> Le fichier audio n'existe pas : " + son.getAbsolutePath());
	            return;
	        }
	        AudioInputStream audioStream = AudioSystem.getAudioInputStream(son); // charge le fichier audio
	        Clip clip = AudioSystem.getClip(); // crée un clip audio
	        clip.open(audioStream); // ouvre le fichier audio
	        clip.start(); // démarre la lecture
	        Thread.sleep(clip.getMicrosecondLength() / 1000); // attendre la fin de la lecture
	    } catch (Exception e) {
	        System.out.println("--> Impossible de jouer le son : " + e.getMessage());
	    }
	}

	/**
     * Affiche un message de victoire pour le joueur gagnant.
     *
     * @param gagnant Le joueur qui a remporté la partie.
     */
	public void afficherVictoire(Joueur gagnant) {
	    
		jouerSonVictoire(); // joue le son de victoire

	    System.out.println("\n===============================================================================");
	    System.out.println("      				🎉🎉 VICTOIRE 🎉🎉");
	    System.out.println("~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~");
	    System.out.println("     			" + gagnant.getNom().toUpperCase() + " a remporté la partie !");
	    System.out.println("     				Félicitations 🏆");
	    System.out.println("===============================================================================\n");
	    
	}


}
