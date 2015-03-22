/**
 * 
 */
package gui;

import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JTextField;

import engine.Engine;

/**
 * @author Marie
 *
 */
public class GDBouttons extends JButton implements MouseListener {

	Engine eng;
	JTextField jt;
	JFrame f;
	Graph g;

	public GDBouttons(Engine engine, JTextField jt, JFrame f, Graph g) {
		this.eng = engine;
		this.jt = jt;
		this.f = f;
		this.g = g;
		this.addMouseListener(this);

	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see java.awt.event.MouseListener#mouseClicked(java.awt.event.MouseEvent)
	 */
	@Override
	public void mouseClicked(MouseEvent e) {

		switch (this.getText()) {
		case "Menu Principal":
			this.setEnabled(false);
			f.setContentPane(new MenuPrincipal(eng, f));
			f.setSize(800, 300);
			f.setVisible(false);
			f.repaint();
			f.setVisible(true);

			break;
		case "Vérifier":
			eng.check(g.jf);
			break;

		case "Résoudre":
			eng.resolv(g.jf);

			break;
		case "Sauvegarder":
			if (jt.getText().length() > 0) {
				eng.Save(jt.getText());
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
