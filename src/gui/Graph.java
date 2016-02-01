package gui;

import java.awt.Color;
import java.awt.Font;
import java.awt.GridLayout;

import javax.swing.JFormattedTextField;
import javax.swing.JPanel;

import engine.Engine;

public class Graph extends JPanel {
	/**
	 * 
	 */
	private static final long serialVersionUID = 7643595700185614528L;
	GridLayout gl = new GridLayout(9, 9);
	Font police = new Font("Calibri", Font.BOLD, 60);

	JNumberTextField jf[][] = new JNumberTextField[9][9];

	Engine en;

	public Graph(Engine e) {
		this.en = e;
		int[][] tab = en.PlayGrille();
		// Taille
		this.setSize(800, 800);
		this.setBackground(Color.BLACK);
		// Declaration de Style

		gl.setHgap(5);
		gl.setVgap(5);

		// Appliquer le Style
		this.setLayout(gl);

		// On ajoute les JtextField
		for (int i = 0; i < 9; i++) {
			for (int j = 0; j < 9; j++) {
				jf[i][j] = new JNumberTextField(1, i, j, jf);
				jf[i][j].setFont(police);
				jf[i][j].setHorizontalAlignment(JFormattedTextField.CENTER);
				if (tab[i][j] != 0) {
					jf[i][j].setText("" + tab[i][j]);
					jf[i][j].setEditable(false);

				}
				jf[i][j].setBackground(Color.white);
				this.add(jf[i][j]);

			}

		}

	}
}