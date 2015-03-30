package engine;

import gui.JNumberTextField;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URL;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Random;

import javax.net.ssl.HttpsURLConnection;
import javax.swing.JFormattedTextField;

public class Engine {

	private final int ValeurParDefautDesCase = -1;
	List<Integer> rl = new ArrayList<Integer>();
	final int Colonne = 9;
	final int Ligne = 9;
	private int[][] grille = new int[9][9];
	private int[][] grilleResolved = new int[9][9];
	int Dificulte = 50;
	Difficulty difEnum;

	public Engine() {
	}

	public void Gen(Difficulty dif) {
		/*
		 * Selection du % de case cache en 3 niveaux
		 */
		switch (dif) {
		case Facile:
			this.Dificulte = 25;
			break;
		case Moyen:
			this.Dificulte = 50;
			break;
		case Difficile:
			this.Dificulte = 75;
			break;
		}
		/*
		 * On initialise la Génération 
		 */
		StartGrid(grilleResolved);
		/*
		 * Génére une serie de 81 nombre aléatoire , si ils sont conforme ou régle de sudoku GenGrid(grilleResolved) est vrai
		 */
		while (!GenGrid(grilleResolved)) {
			StartGrid(grilleResolved);
		}
	}

	/**
	 * Retire un % de nombre de la grille de manière aléatoire
	 */
	public void Hide() {
		Random rand = new Random((new Date().getTime()));
		for (short k = 0; k < grilleResolved.length; k++) {
			for (short j = 0; j < grilleResolved[k].length; j++) {
				if (rand.nextInt(100) < Dificulte) {

					grille[k][j] = 0;
				} else {
					grille[k][j] = grilleResolved[k][j];
				}

			}

		}
	}

	/**
	 * Grille de jeux 
	 * @return tableaux de 9*9 entier
	 */
	public int[][] PlayGrille() {
		return grille;
	}
	/**
	 * Grille de jeux résolue 
	 * @return tableaux de 9*9 entier
	 */
	public int[][] ResolvedGrid() {
		return grilleResolved;
	}

	/**
	 * On Prepare la génération en remplissant la grille de -1
	 * 
	 * @param grille
	 *            La Grille de Jeux
	 */
	private void StartGrid(int[][] grille) {
		for (short k = 0; k < grille.length; k++) {
			for (short j = 0; j < grille[k].length; j++) {
				grille[k][j] = ValeurParDefautDesCase;
			}
		}
	}

	/**
	 * Genere une Grille
	 * 
	 * @param grille
	 *            Grille de Jeux
	 * @return true si la grille est bonne
	 */
	protected boolean GenGrid(int[][] grille) {
		/*
		 * Variable & inititialisation du générateur de nombre aléatoire
		 */
		int failure = 0;
		int rand;
		Random randomGenerator = new Random((new Date().getTime()));

		/*
		 * Boucle parcourant les 81 entier de la grille 
		 *
		 */
		for (short k = 0; k < grille.length; k++) {
			for (short j = 0; j < grille[k].length; j++) {
				/*
				 * On vide la liste d'entier 
				 */
				rl.clear();
				do {
					/*
					 * Nombre alétoire +1 pour eviter -1
					 */
					rand = randomGenerator.nextInt(Colonne) + 1;
					/*
					 * Si la liste contiens le chiffre on passe au suivant 
					 */
					if (rl.contains(rand)) {
						continue;
					} else {
						/*
						 * Si non on ajouter l'entier aléatoire 
						 */
						rl.add(rand);
						/*
						 * Si la liste fait la taille de la colonne on réinitilise le nombre aléatoire et on arréte la boucle actuel
						 */
						if (rl.size() == Colonne) {
							rand = -1;
							failure++;
							break;
						}
					}
					/*
					 * On lance la verification 
					 */
				} while (!CheckUniq(rand, grille, k, j)); 
				grille[k][j] = rand;

			}
		}

		return (failure == 0);
	}

	/**
	 * Verifie si le nombre rn est valide pour la ligne , la colone et la zone
	 * ou il se trouve
	 * 
	 * @param rn
	 *            le nombre à vérifier
	 * @param tab
	 *            la grille
	 * @param lign
	 *            la ligne actuelle
	 * @param col
	 *            la colone actuelle
	 * @return
	 */
	private boolean CheckUniq(int rn, int[][] tab, int lign, int col) {
		return (Col(rn, tab, lign, col, 0, tab[lign].length)
				&& Lign(rn, tab, lign, col, 0, tab.length) && Zone(rn, tab,
					lign, col));
	}

	/**
	 * Verification par colone
	 * 
	 * @param rn
	 *            Nombre à tester
	 * @param tab
	 *            La Grille
	 * @param lign
	 *            la ligne actuelle
	 * @param col
	 *            la colone actuelle
	 * @param start
	 *            ou es que l'on commence la verification
	 * @param end
	 *            Ou es qu'elle ce termine
	 * @return Si le nombre rn est valide pour cette colone
	 */
	private boolean Col(int rn, int[][] tab, int lign, int col, int start,
			int end) {
		// ligne avant
		for (int i = start; i < lign; i++) {
			if (rn == tab[i][col])
				return false;
		}
		// ligne après
		for (int i = lign; i < end - 1; i++) {
			if (rn == tab[i][col])
				return false;
		}
		return true;
	}

	/**
	 * Verification par zone 3x3
	 * 
	 * @param rn
	 *            le nombre à verifier
	 * @param tab
	 *            la grille
	 * @param lign
	 *            la ligne actuelle
	 * @param col
	 *            la colone actuelle
	 * @return Si le nombre rn est valide pour cette Zone 3x3
	 */
	private boolean Zone(int rn, int[][] tab, int lign, int col) {

		int LigneDeZone = (lign / (Ligne / 3)) * Ligne / 3;

		int LigneDeZone2 = LigneDeZone + Ligne / 3;

		int ColoneDeZone = (col / (Colonne / 3)) * Colonne / 3;

		int ColoneDeZone2 = ColoneDeZone + Colonne / 3;

		for (int k = LigneDeZone; k < LigneDeZone2; k++) {
			for (int j = ColoneDeZone; j < ColoneDeZone2; j++) {
				if (rn == tab[k][j])
					return false;
			}
		}
		return true;

	}

	/**
	 * Verification par ligne
	 * 
	 * @param rn
	 *            Nombre à tester
	 * @param tab
	 *            La Grille
	 * @param lign
	 *            la ligne actuelle
	 * @param col
	 *            la colone actuelle
	 * @param start
	 *            ou es que l'on commence la verification
	 * @param end
	 *            Ou es qu'elle ce termine
	 * @return Si le nombre rn est valide pour cette ligne
	 */
	private boolean Lign(int rn, int[][] tab, int lign, int col, int start,
			int end) {
		for (int i = start; i < col; i++) {
			if (rn == tab[lign][i])
				return false;
		}
		for (int i = col; i < end; i++) {
			if (rn == tab[lign][i])
				return false;
		}
		return true;
	}

	/**
	 * Ecrit la grille dans fichier
	 * 
	 * @param saveName
	 *            Nom de la Sauvegarde
	 */
	public void Save(String saveName) {

		BufferedWriter outputWriter = null;
		BufferedWriter outputWriters = null;

		try {
			outputWriter = new BufferedWriter(new FileWriter("Grille@"
					+ saveName + ".txt"));
			for (int i = 0; i < 9; i++) {
				for (int j = 0; j < 9; j++) {

					outputWriter.write(Integer.toString(grille[i][j]));
					outputWriter.newLine();
				}
			}

			outputWriter.flush();
			outputWriter.close();
			outputWriters = new BufferedWriter(new FileWriter("GrilleR@"
					+ saveName + ".txt"));
			for (int i = 0; i < 9; i++) {
				for (int j = 0; j < 9; j++) {

					outputWriters.write(Integer.toString(grilleResolved[i][j]));
					outputWriters.newLine();
				}
			}

			outputWriters.flush();
			outputWriters.close();

		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	/**
	 * Charge une grille depuis un fichier
	 * 
	 * @param saveName
	 *            Nom de la Sauvegarde
	 */
	public void Load(String saveName) {

		BufferedReader outputReader = null;
		BufferedReader outputReaders = null;

		try {
			outputReader = new BufferedReader(new FileReader("Grille@"
					+ saveName + ".txt"));
			for (int i = 0; i < 9; i++) {
				for (int j = 0; j < 9; j++) {

					grille[i][j] = Integer.parseInt(outputReader.readLine());

				}
			}

			outputReader.close();
			outputReaders = new BufferedReader(new FileReader("GrilleR@"
					+ saveName + ".txt"));
			for (int i = 0; i < 9; i++) {
				for (int j = 0; j < 9; j++) {

					grilleResolved[i][j] = Integer.parseInt(outputReaders
							.readLine());
				}
			}

			outputReaders.close();

			System.out.println("Grille chargé en memoire ");
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			System.out.println("File error : " + e.getMessage());
		}

	}

	/**
	 * Télécharge une grille depuis files.darkube.net
	 * 
	 * @param dif
	 *            La Difficule de la grille suivant l'enum Difficulty
	 * @return true si la récuperation est résusie
	 */
	public boolean LoadFromCloud(Difficulty dif) {

		boolean result = false;
		boolean SiteOnline = false;
		String srvAddr = "https://files.darkube.net/Sudoku/";
		String fileName = "";
		String GridFolder = "";
		int RemoteNbGrid = 0;
		int SelectedGrid = -1;
		Random rand = new Random();

		/**
		 * Selection de la difficulté
		 */
		switch (dif) {
		case Facile:
			fileName = "Easy.txt";
			GridFolder = "Easy/";
			break;
		case Moyen:
			fileName = "Medium.txt";
			GridFolder = "Medium/";
			break;
		case Difficile:
			fileName = "Hard.txt";
			GridFolder = "Hard/";
			break;
		}

		/**
		 * On verifie l'état du depot
		 */
		try {
			/**
			 * On initialise le client HTTPS
			 */
			URL siteURL = new URL(srvAddr + "online.txt");
			HttpsURLConnection connection = (HttpsURLConnection) siteURL
					.openConnection();
			connection.setRequestMethod("GET");
			connection
					.setRequestProperty("User-Agent",
							"Mozilla/5.0 (Windows NT 5.1; rv:19.0) Gecko/20100101 Firefox/19.0");
			/**
			 * On recupère le code de réponce du serveur
			 */
			connection.connect();

			int code = connection.getResponseCode();
			if (code == 200) {
				SiteOnline = true;
			}
			connection.disconnect();
		} catch (Exception e) {
			e.printStackTrace();
		}
		System.out.println(SiteOnline);

		/**
		 * On recupère l'etat de la dernière verification , si le depot est en
		 * ligne on continue
		 */
		if (SiteOnline) {
			try {
				/**
				 * On initialise le client HTTPS
				 */
				URL siteURL = new URL(srvAddr + fileName);
				HttpsURLConnection connection = (HttpsURLConnection) siteURL
						.openConnection();
				connection.setRequestMethod("GET");
				connection
						.setRequestProperty("User-Agent",
								"Mozilla/5.0 (Windows NT 5.1; rv:19.0) Gecko/20100101 Firefox/19.0");
				connection.connect();
				/**
				 * On récupère le fichier index des grille et on le lit
				 */
				BufferedReader outputReader = new BufferedReader(
						new InputStreamReader(connection.getInputStream()));
				RemoteNbGrid = Integer.parseInt(outputReader.readLine());
				/**
				 * On choisie une grille alèatoire dans l'index
				 */
				SelectedGrid = rand.nextInt(RemoteNbGrid);
				connection.disconnect();

			} catch (Exception e) {
				e.printStackTrace();
			}

		}

		if (SelectedGrid != -1) {
			try {
				/**
				 * On initialise le 1er client HTTPS
				 */
				URL siteURL1 = new URL(srvAddr + GridFolder + "Grille@"
						+ SelectedGrid + ".txt");
				HttpsURLConnection connection1 = (HttpsURLConnection) siteURL1
						.openConnection();
				connection1
						.setRequestProperty("User-Agent",
								"Mozilla/5.0 (Windows NT 5.1; rv:19.0) Gecko/20100101 Firefox/19.0");
				connection1.setRequestMethod("GET");
				connection1.connect();
				/**
				 * On récupère la Grille de Jeux
				 */
				BufferedReader outputReader = new BufferedReader(
						new InputStreamReader(connection1.getInputStream()));
				for (int i = 0; i < 9; i++) {
					for (int j = 0; j < 9; j++) {

						grille[i][j] = Integer
								.parseInt(outputReader.readLine());
					}
				}

				connection1.disconnect();

				/**
				 * On initialise le 2eme client HTTPS
				 */
				URL siteURL2 = new URL(srvAddr + GridFolder + "GrilleR@"
						+ SelectedGrid + ".txt");
				HttpsURLConnection connection2 = (HttpsURLConnection) siteURL2
						.openConnection();
				connection2.setRequestMethod("GET");
				connection2
						.setRequestProperty("User-Agent",
								"Mozilla/5.0 (Windows NT 5.1; rv:19.0) Gecko/20100101 Firefox/19.0");
				connection2.connect();
				/**
				 * On récupère la solution de la Grille de jeux
				 */
				BufferedReader outputReaders = new BufferedReader(
						new InputStreamReader(connection2.getInputStream()));
				for (int i = 0; i < 9; i++) {
					for (int j = 0; j < 9; j++) {

						grilleResolved[i][j] = Integer.parseInt(outputReaders
								.readLine());
					}
				}
				result = true;
				System.out
						.println("Récupération terminé , Grille de jeux et solution chargé dans la memoire");
				connection2.disconnect();
			} catch (Exception e) {
				result = false;
				System.out.println("Erreur lors de la Récupération : "
						+ e.getMessage());
			}

		}

		return result;
	}

	public void check(JNumberTextField jf[][]) {
		for (int i = 0; i < 9; i++) {
			for (int j = 0; j < 9; j++) {
				if (jf[i][j].getText().length() > 0) {
					if (Integer.parseInt(jf[i][j].getText()) == this.grilleResolved[i][j]) {
						jf[i][j].setEditable(false);
					}
				}

			}

		}

	}

	public void resolv(JNumberTextField jf[][]) {
		for (int i = 0; i < 9; i++) {
			for (int j = 0; j < 9; j++) {

				jf[i][j].setText("" + this.grilleResolved[i][j]);
				jf[i][j].setEditable(false);

			}

		}
	}

}
