/**
 * 
 */
package gui;

import java.awt.Color;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JTextField;

import engine.Difficulty;
import engine.Engine;

/**
 * @author Marie
 *
 */
public class Boutton extends JButton implements MouseListener {

	/**
	 * 
	 */
	private static final long serialVersionUID = -5348362039160577107L;
	Engine eng;
	JComboBox<Difficulty> dificulte;
	JFrame frame;
	private JTextField saveName;

	public Boutton(Engine engine, JComboBox<Difficulty> dif, JFrame f, JTextField s) {
		this.eng = engine;
		this.dificulte = dif;
		this.addMouseListener(this);
		this.frame = f;
		this.saveName = s;

	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see java.awt.event.MouseListener#mouseClicked(java.awt.event.MouseEvent)
	 */
	@Override
	public void mouseClicked(MouseEvent e) {
		System.out.println(dificulte.getSelectedItem());

		switch (this.getText()) {
		case "Grille Unique":
			JOptionPane.showMessageDialog(null, "Grille en cours de génération ...", "Information", JOptionPane.INFORMATION_MESSAGE);
			this.setEnabled(false);
			eng.Gen((Difficulty) dificulte.getSelectedItem());
			eng.Hide();
			this.frame.setVisible(false);
			JFrame f1 = new JFrame();
			GamePanel g1 = new GamePanel(eng, f1);
			g1.setVisible(true);
			f1.setSize(1100, 850);
			f1.setContentPane(g1);
			f1.setVisible(true);
			f1.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
			break;
		case "Grille Cloud":
			this.setEnabled(false);
			eng.LoadFromCloud((Difficulty) dificulte.getSelectedItem());
			this.frame.setVisible(false);
			JFrame f2 = new JFrame();
			GamePanel g2 = new GamePanel(eng, f2);
			g2.setVisible(true);
			f2.setContentPane(g2);
			f2.setSize(1100, 850);
			f2.setVisible(true);
			f2.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
			break;
		case "Charger sauvegarde":
			if (saveName.getText().length() > 0) {
				this.setEnabled(false);
				eng.Load(saveName.getText());
				this.frame.setVisible(false);
				JFrame f3 = new JFrame();
				GamePanel g3 = new GamePanel(eng, f3);
				g3.setVisible(true);
				f3.setContentPane(g3);
				f3.setVisible(true);
				f3.setSize(1100, 850);
				f3.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
				saveName.setBackground(Color.white);
			} else {
				saveName.setBackground(Color.RED);
				JOptionPane.showMessageDialog(null, "Vous devez remplir le champ en rouge", "Information", JOptionPane.ERROR_MESSAGE);

			}
			break;

		}

	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see java.awt.event.MouseListener#mousePressed(java.awt.event.MouseEvent)
	 */
	@Override
	public void mousePressed(MouseEvent e) {
		// TODO Auto-generated method stub

	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * java.awt.event.MouseListener#mouseReleased(java.awt.event.MouseEvent)
	 */
	@Override
	public void mouseReleased(MouseEvent e) {
		// TODO Auto-generated method stub

	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see java.awt.event.MouseListener#mouseEntered(java.awt.event.MouseEvent)
	 */
	@Override
	public void mouseEntered(MouseEvent e) {
		// TODO Auto-generated method stub

	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see java.awt.event.MouseListener#mouseExited(java.awt.event.MouseEvent)
	 */
	@Override
	public void mouseExited(MouseEvent e) {
		// TODO Auto-generated method stub

	}

}
