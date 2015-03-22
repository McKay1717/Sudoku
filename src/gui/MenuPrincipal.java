/**
 * 
 */
package gui;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

import engine.Difficulty;
import engine.Engine;

/**
 * @author Marie
 *
 */
public class MenuPrincipal extends JPanel {

	/**
	 * 
	 */
	private static final long serialVersionUID = 8615423218491304000L;
	private JComboBox<Difficulty> combo = new JComboBox<Difficulty>();
	JButton B1;
	JButton B2;
	JButton B3;
	Font police = new Font("Calibri", Font.BOLD, 20);
	Font police2 = new Font("Calibri", Font.BOLD, 60);
	JTextField jt = new JTextField();
	JLabel jl = new JLabel("Nom de la Sauvegarde :");
	JLabel jl2 = new JLabel("SUDOKU :D");

	public MenuPrincipal(Engine engine, JFrame f) {
		this.setSize(800, 300);
		this.setLayout(null);
		B1 = new Boutton(engine, combo, f, jt);
		B2 = new Boutton(engine, combo, f, jt);
		B3 = new Boutton(engine, combo, f, jt);

		B1.setText("Grille Unique");
		B2.setText("Grille Cloud");
		B3.setText("Charger sauvegarde");

		B1.setFont(police);
		B2.setFont(police);
		B3.setFont(police);

		B1.setSize(200, 50);
		B2.setSize(200, 50);
		B3.setSize(200, 50);

		B1.setLocation(0, 70);
		B2.setLocation(B1.getX(), B1.getY() + B1.getHeight() + 10);
		B3.setLocation(B2.getX(), B2.getY() + B2.getHeight() + 10);

		jl.setSize(200, 50);
		jl.setFont(police);
		jl.setLocation(B3.getX() + B3.getWidth() + 50, B3.getY());

		jl2.setSize(400, 50);
		jl2.setFont(police2);
		jl2.setLocation(250, 0);
		jl2.setForeground(Color.blue);

		jt.setSize(200, 50);
		jt.setFont(police);
		jt.setLocation(jl.getX() + jl.getWidth() + 20, jl.getY());

		combo.addItem(Difficulty.Facile);
		combo.addItem(Difficulty.Moyen);
		combo.addItem(Difficulty.Difficile);
		combo.setSize(new Dimension(200, 50));
		combo.setBackground(Color.cyan);
		combo.setLocation(500, B1.getY());
		combo.setFont(police);
		combo.setVisible(true);

		this.add(combo);
		this.add(B1);
		this.add(B2);
		this.add(B3);
		this.add(jt);
		this.add(jl);
		this.add(jl2);

	}

}
