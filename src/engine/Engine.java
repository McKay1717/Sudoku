package engine;

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

public class Engine {

	private final int ValeurParDefautDesCase = -1;
	List<Integer> rl = new ArrayList<Integer>();
	final int Collone = 9;
	final int Ligne = 9;
	private int[][] grille = new int[9][9];
	private int[][] grilleResolved = new int[9][9];
	int Dificulte = 50;
	Difficulty difEnum;

	public Engine() {
	}

	public void Gen(Difficulty dif) {
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
		StartGrid(grilleResolved);
		while (!GenGrid(grilleResolved)) {
			StartGrid(grilleResolved);
		}
	}

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

	public int[][] PlayGrille() {
		return grille;
	}

	public int[][] ResolvedGrid() {
		return grilleResolved;
	}

	/**
	 * On remplie la grille de valuer -1
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
		int failure = 0;
		int rand;
		Random randomGenerator = new Random((new Date().getTime()));

		for (short k = 0; k < grille.length; k++) {
			for (short j = 0; j < grille[k].length; j++) {
				rl.clear();
				do {
					rand = randomGenerator.nextInt(Collone) + 1;
					if (rl.contains(rand)) {
						continue;
					} else {
						rl.add(rand);
						if (rl.size() == Collone) {
							rand = -1;
							failure++;
							break;
						}
					}
				} while (!CheckUniq(rand, grille, k, j));
				grille[k][j] = rand;

			}
		}

		return (failure == 0);
	}

	private boolean CheckUniq(int rn, int[][] tab, int lign, int col) {
		return (Col(rn, tab, lign, col, 0, tab[lign].length)
				&& Lign(rn, tab, lign, col, 0, tab.length) && Zone(rn, tab,
					lign, col));
	}

	private boolean Col(int rn, int[][] tab, int lign, int col, int start,
			int end) {
		// ligns before
		for (int i = start; i < lign; i++) {
			if (rn == tab[i][col])
				return false;
		}
		// ligns after
		for (int i = lign; i < end - 1; i++) {
			if (rn == tab[i][col])
				return false;
		}
		return true;
	}

	private boolean Zone(int rn, int[][] tab, int lign, int col) {

		int LigneDeZone = (lign / (Ligne / 3)) * Ligne / 3;

		int LigneDeZone2 = LigneDeZone + Ligne / 3;

		int ColoneDeZone = (col / (Collone / 3)) * Collone / 3;

		int ColoneDeZone2 = ColoneDeZone + Collone / 3;

		for (int k = LigneDeZone; k < LigneDeZone2; k++) {
			for (int j = ColoneDeZone; j < ColoneDeZone2; j++) {
				if (rn == tab[k][j])
					return false;
			}
		}
		return true;

	}

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

	public boolean LoadFromCloud(Difficulty dif) {

		boolean result = false;
		boolean SiteOnline = false;
		String srvAddr = "https://files.darkube.net/torrent/Sudoku/";
		String fileName = "";
		String GridFolder = "";
		int RemoteNbGrid = 0;
		int SelectedGrid = -1;
		Random rand = new Random();

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

		try {
			URL siteURL = new URL(srvAddr);
			HttpsURLConnection connection = (HttpsURLConnection) siteURL
					.openConnection();
			connection.setRequestMethod("GET");
			connection
					.setRequestProperty("User-Agent",
							"Mozilla/5.0 (Windows NT 5.1; rv:19.0) Gecko/20100101 Firefox/19.0");
			connection.connect();

			int code = connection.getResponseCode();
			if (code == 200) {
				SiteOnline = true;
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		System.out.println(SiteOnline);

		if (SiteOnline) {
			try {
				URL siteURL = new URL(srvAddr + fileName);
				HttpsURLConnection connection = (HttpsURLConnection) siteURL
						.openConnection();
				connection.setRequestMethod("GET");
				connection
				.setRequestProperty("User-Agent",
						"Mozilla/5.0 (Windows NT 5.1; rv:19.0) Gecko/20100101 Firefox/19.0");
				connection.connect();
				BufferedReader outputReader = new BufferedReader(
						new InputStreamReader(connection.getInputStream()));
				RemoteNbGrid = Integer.parseInt(outputReader.readLine());
				SelectedGrid = rand.nextInt(RemoteNbGrid);

			} catch (Exception e) {
				e.printStackTrace();
			}

		}

		if (SelectedGrid != -1) {
			try {
				URL siteURL1 = new URL(srvAddr + GridFolder + "Grille@"
						+ SelectedGrid + ".txt");
				HttpsURLConnection connection1 = (HttpsURLConnection) siteURL1
						.openConnection();
				connection1
				.setRequestProperty("User-Agent",
						"Mozilla/5.0 (Windows NT 5.1; rv:19.0) Gecko/20100101 Firefox/19.0");
				connection1.setRequestMethod("GET");
				connection1.connect();
				BufferedReader outputReader = new BufferedReader(
						new InputStreamReader(connection1.getInputStream()));
				for (int i = 0; i < 9; i++) {
					for (int j = 0; j < 9; j++) {

						grille[i][j] = Integer
								.parseInt(outputReader.readLine());
					}
				}

				URL siteURL2 = new URL(srvAddr + GridFolder + "GrilleR@"
						+ SelectedGrid + ".txt");
				HttpsURLConnection connection2 = (HttpsURLConnection) siteURL2
						.openConnection();
				connection2.setRequestMethod("GET");
				connection2
				.setRequestProperty("User-Agent",
						"Mozilla/5.0 (Windows NT 5.1; rv:19.0) Gecko/20100101 Firefox/19.0");
				connection2.connect();
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
			} catch (Exception e) {
				result = false;
				System.out.println("Erreur lors de la Récupération : "+e.getMessage() );
			}

		}

		return result;
	}

}
