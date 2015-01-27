package gui;

import java.awt.Font;
import java.awt.GridLayout;

import javax.swing.JFormattedTextField;
import javax.swing.JFrame;

import engine.Engine;

public class Graph extends JFrame {
	GridLayout gl = new GridLayout(9, 9);
	Font police = new Font("Calibri", Font.BOLD, 60);

	JNumberTextField jf[][] = new JNumberTextField[9][9];

	Engine e = new Engine();

	public Graph() {
		e.Gen();
		e.Hide();
		int[][] tab = e.PlayGrille();
		// Titre
		this.setTitle("Sudoku");
		// Taille
		this.setSize(800, 800);
		// Declaration de Style

		gl.setHgap(5);
		gl.setVgap(5);

		// Appliquer le Style
		this.setLayout(gl);

		// On ajoute les JtextField
		for (int i = 0; i < 9; i++) {
			for (int j = 0; j < 9; j++) {
				jf[i][j] = new JNumberTextField(1);
				jf[i][j].setFont(police);
				jf[i][j].setHorizontalAlignment(JFormattedTextField.CENTER);
				if (tab[i][j] != 0) {
					jf[i][j].setText("" + tab[i][j]);
					jf[i][j].setEditable(false);
				}

				this.getContentPane().add(jf[i][j]);

			}

		}

	}
}