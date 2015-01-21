package engine;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Random;

public class Engine {

	private static final int ValeurParDefautDesCase = -1;
	static List<Integer> rl = new ArrayList<Integer>();
	static final int Collone = 9;
	static final int Ligne = 9;
	public static int[][] grille = new int[9][9];
	public static int[][] grilleResolved = new int[9][9];
	static final int Dificulte = 50;

	public Engine() {

	}

	public static int[][] Grille() {
		Random rand = new Random((new Date().getTime()));
		StartGrid(grille);
		while (!GenGrid(grille)) {
			StartGrid(grille);
		}
		grilleResolved = grille;
		for (short k = 0; k < grille.length; k++) {
			for (short j = 0; j < grille[k].length; j++) {
				if (rand.nextInt(100) < Dificulte) {

					grille[k][j] = 0;
				}

			}

		}
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
	private static void StartGrid(int[][] grille) {
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
	protected static boolean GenGrid(int[][] grille) {
		int failure = 0;
		int rand;
		Random randomGenerator = new Random((new Date().getTime()));

		for (short k = 0; k < grille.length; k++) {
			for (short j = 0; j < grille[k].length; j++) {
				rl.clear();
				do {
					rand = randomGenerator.nextInt(Collone) + 1; // generate a
																	// random
																	// number
					if (rl.contains(rand)) { // already tested
						continue;
					} else {
						rl.add(rand); // add to alreadyDonelist
						if (rl.size() == Collone) { // cancel if all tested
							rand = -1;
							failure++;
							break;
						}
					}
				} while (!CheckUniq(rand, grille, k, j));
				grille[k][j] = rand;

			}
		}

		return (failure == 0); // success if no failure
	}

	private static boolean CheckUniq(int rn, int[][] tab, int lign, int col) {
		return (Col(rn, tab, lign, col, 0, tab[lign].length)
				&& Lign(rn, tab, lign, col, 0, tab.length) && Zone(rn, tab,
					lign, col));
	}

	private static boolean Col(int rn, int[][] tab, int lign, int col,
			int start, int end) {
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

	private static boolean Zone(int rn, int[][] tab, int lign, int col) {

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

	private static boolean Lign(int rn, int[][] tab, int lign, int col,
			int start, int end) {
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

}
