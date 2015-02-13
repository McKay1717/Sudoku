import java.util.Scanner;

import engine.Difficulty;
import engine.Engine;
import gui.Graph;

public class Main {

	public static void main(String[] args) {

		Engine engine = new Engine();
		if (args.length == 0) {
			Graph gui = new Graph(engine);
			gui.setVisible(true);
		} else {
			printHelp();
			Scanner sc = new Scanner(System.in);
			while (true) {
				String str = sc.nextLine();
				/***
				 * Gen cmd
				 */
				if (str.contains("gen")) {
					String[] strSplited = str.split(":");
					if (strSplited.length == 2) {
						switch (strSplited[1]) {
						case "Facile":
							System.out.println("Géneration de la Grille ...");
							engine.Gen(Difficulty.Facile);
							engine.Hide();
							System.out
									.println("Géneration terminé , Grille de jeux et solution chargé dans la memoire");
							break;
						case "Moyen":
							System.out.println("Géneration de la Grille ...");
							engine.Gen(Difficulty.Moyen);
							engine.Hide();
							System.out
									.println("Géneration terminé , Grille de jeux et solution chargé dans la memoire");
							break;
						case "Difficile":
							System.out.println("Géneration de la Grille ...");
							engine.Gen(Difficulty.Difficile);
							System.out
									.println("Géneration terminé , Grille de jeux et solution chargé dans la memoire");
							engine.Hide();
							break;
						default:
							System.out.println("Mauvaise Synthaxe");
							System.out
									.println("gen:Difficulté - Générer une grille depuis cette Ordinateur");
							break;
						}

					} else {
						System.out.println("Mauvaise Synthaxe");
						System.out
								.println("gen:Difficulté - Générer une grille depuis cette Ordinateur");

					}

				}
				/**
				 * Load cmd
				 */
				if (str.contains("load")) {
					String[] strSplited = str.split(":");
					if (strSplited.length == 2) {
						engine.Load(strSplited[1]);

					} else {
						System.out.println("Mauvaise Synthaxe");
						System.out
								.println("load:NomDeLaSauvegarde - Charge la Sauvegarde Ayant pour nom NomDeLaSauvegarde");

					}
				}
				/**
				 * Save cmd
				 */
				if (str.contains("save")) {
					String[] strSplited = str.split(":");
					if (strSplited.length == 2) {
						engine.Save(strSplited[1]);

					} else {
						System.out.println("Mauvaise Synthaxe");
						System.out
								.println("save:NomDeLaSauvegarde - Sauvegarde la grille actuelle dans le fichier Grille@NomDeLaSauvegarde.txt");

					}
				}
				/**
				 * Cloud loading
				 */
				if (str.contains("Cloud")) {
					String[] strSplited = str.split(":");
					if (strSplited.length == 2) {
						switch (strSplited[1]) {
						case "Facile":
							System.out
									.println("Récupération d'une grille Grille ...");
							engine.LoadFromCloud(Difficulty.Facile);

							break;
						case "Moyen":
							System.out
									.println("Récupération d'une grille Grille ...");
							engine.LoadFromCloud(Difficulty.Moyen);

							break;
						case "Difficile":
							System.out
									.println("Récupération d'une grille Grille ...");
							engine.LoadFromCloud(Difficulty.Difficile);

							break;
						default:
							System.out.println("Mauvaise Synthaxe");

							break;
						}

					} else {
						System.out.println("Mauvaise Synthaxe");
						System.out
								.println("loadFomCloud:Difficulté - Charge Une Grille prégénérer depuis internet");

					}

				}
				/**
				 * GUI
				 */
				if (str.contains("gui")) {
					Graph gui = new Graph(engine);
					gui.setVisible(true);


				}
				/**
				 * exit
				 */
				if (str.contains("exit")) {
					break;
					

				}

			}
			System.exit(0);

		}

	}

	private static void printHelp() {
		System.out.println("Voici les commandes possible :");
		System.out.println();
		System.out
				.println("gen:Difficulté - Générer une grille depuis cette Ordinateur");
		System.out
				.println("load:NomDeLaSauvegarde - Charge la Sauvegarde Ayant pour nom NomDeLaSauvegarde");
		System.out
				.println("save:NomDeLaSauvegarde - Sauvegarde la grille actuelle dans le fichier Grille@NomDeLaSauvegarde.txt");
		System.out
				.println("Cloud:Difficulté - Charge Une Grille prégénérer depuis internet");
		System.out
				.println("gui - demarer l'interface Graphique et stop l'interface lingne de commande");
		System.out.println("exit - fermer le programe");
		System.out.println();
		System.out.println("Difficulté Disponible :");
		System.out.println("Facile");
		System.out.println("Moyen");
		System.out.println("Difficile");
		System.out.println();
	}
}