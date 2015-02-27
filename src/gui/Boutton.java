/**
 * 
 */
package gui;

import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JTextField;

import engine.Difficulty;
import engine.Engine;

/**
 * @author Marie
 *
 */
public class Boutton extends JButton implements MouseListener {

	Engine eng;
	JComboBox<Difficulty> dificulte;
	JFrame frame;
	private JTextField saveName;
	public Boutton(Engine engine,JComboBox dif,JFrame f,JTextField s)
	{
		this.eng = engine;
		this.dificulte = dif;
	    this.addMouseListener(this);
	    this.frame = f;
	    this.saveName = s;
	    
	}
	/* (non-Javadoc)
	 * @see java.awt.event.MouseListener#mouseClicked(java.awt.event.MouseEvent)
	 */
	@Override
	public void mouseClicked(MouseEvent e) {
		System.out.println(dificulte.getSelectedItem());
		
		switch(this.getText())
		{
		case "Grille Unique":
			this.setEnabled(false);
			eng.Gen((Difficulty) dificulte.getSelectedItem());
			eng.Hide();
			this.frame.setVisible(false);
			Graph g = new Graph(eng);
			g.setVisible(true);
			break;
		case "Grille Cloud":
			this.setEnabled(false);
			eng.LoadFromCloud((Difficulty) dificulte.getSelectedItem());
			this.frame.setVisible(false);
			Graph g1 = new Graph(eng);
			g1.setVisible(true);
			break;
		case "Charger sauvegarde":
			this.setEnabled(false);
			eng.Load(saveName.getText());
			this.frame.setVisible(false);
			Graph g2 = new Graph(eng);
			g2.setVisible(true);
			break;
			
			
		  
		
		}
		

	}

	/* (non-Javadoc)
	 * @see java.awt.event.MouseListener#mousePressed(java.awt.event.MouseEvent)
	 */
	@Override
	public void mousePressed(MouseEvent e) {
		// TODO Auto-generated method stub


	}

	/* (non-Javadoc)
	 * @see java.awt.event.MouseListener#mouseReleased(java.awt.event.MouseEvent)
	 */
	@Override
	public void mouseReleased(MouseEvent e) {
		// TODO Auto-generated method stub

	}

	/* (non-Javadoc)
	 * @see java.awt.event.MouseListener#mouseEntered(java.awt.event.MouseEvent)
	 */
	@Override
	public void mouseEntered(MouseEvent e) {
		// TODO Auto-generated method stub
		

	}

	/* (non-Javadoc)
	 * @see java.awt.event.MouseListener#mouseExited(java.awt.event.MouseEvent)
	 */
	@Override
	public void mouseExited(MouseEvent e) {
		// TODO Auto-generated method stub

	}

}
