
# 💖 HeartStri - Jeu de Cartes Java Inspiré de Hearthstone

HeartStri est un projet Java universitaire inspiré du célèbre jeu *Hearthstone*. Il s'agit d'un **jeu de cartes à collectionner (JCC)** qui permet à deux joueurs de s'affronter avec des decks personnalisés, des héros, des sorts, des armes et des pouvoirs spéciaux.

---

## 🗂️ Structure du Projet

```
HeartStri/
│
├── src/
│   ├── modele/        # Classes métier (Cartes, Héros, Joueurs, Sorts, etc.)
│   ├── controleur/    # Gestion du déroulement des combats et de la partie
│   ├── vue/           # Interfaces Console et Graphique (en développement)
│   ├── sauvegarde/    # Sauvegarde et chargement des parties/decks
│   └── heartstri/     # Lancement principal du jeu
│
├── bin/               # Fichiers compilés
├── .classpath, .project, .settings/  # Fichiers Eclipse
├── README.md          # Ce fichier
└── ressources/        # (Optionnel) Images ou sons si ajout d'IHM
```

---

## 🕹️ Fonctionnalités

### ✅ Étape 1 : Decks & Combats
- Création aléatoire ou manuelle d’un deck de cartes de type `Serviteur`
- Combat automatique entre deux serviteurs jusqu'à la mort de l’un

### ✅ Étape 2 : Mana & Invocations
- Implémentation des héros avec 30 PV
- Système de mana progressif (1 à 10)
- Possibilité d’invoquer des serviteurs en respectant le mana disponible
- Attaques dirigées vers serviteurs ennemis ou le héros adverse

### ✅ Étape 3 : Sorts & Armes
- Sorts infligeant des dégâts, soignant ou modifiant les stats
- Armes équipables par les héros, avec gestion de la durabilité

### ✅ Étape 4 : Pouvoirs Spéciaux
- Chaque héros dispose d’un pouvoir unique (inspiré de Hearthstone)

### ✅ Étape 5 : Sauvegarde
- Sauvegarde et chargement des parties en cours (`EtatPartie`)
- Sauvegarde et chargement des decks (`SauvegardeDeck`)

---

## 🚀 Lancer le Projet

### Conditions requises
- Java JDK 17+
- Un IDE Java (Eclipse, IntelliJ) ou terminal

### Exécution depuis le terminal

```bash
cd HeartStri/src
javac heartstri/LancementJeu.java
java heartstri.LancementJeu
```

Ou exécuter `LancementJeu.java` depuis l'IDE.

---

## 📷 Interface utilisateur

- Une **interface console** est pleinement fonctionnelle (`ConsoleUI`)
- Une **interface graphique** est en cours de développement (`Graphique.java`)

---

## 📄 UML & Documentation

Les diagrammes suivants sont fournis dans le rapport PDF :
- Diagramme de cas d’utilisation
- Diagramme de classes
- Diagrammes de séquence (système et utilisateurs)

Les classes sont annotées avec **Javadoc** pour améliorer la lisibilité et la qualité du code.

---

## 💾 Sauvegarde

Les fichiers de sauvegarde sont enregistrés au format texte binaire (Java `ObjectOutputStream`), et peuvent être rechargés à tout moment via le menu console.

---

## 👨‍💻 Auteurs

Projet réalisé par :
- AMRAOUI Otmane - 1A/STRI

Sous l'encadrement de :
- Pr. Patrice TORGUET
- Pr. Andreï ANDREÏ

---

## 📜 Licence

Projet académique - Non destiné à un usage commercial.
