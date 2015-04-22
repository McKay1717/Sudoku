package gui;

import java.awt.Font;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JTextField;

import engine.Engine;

public class Gmenu extends JPanel {

	/**
	 * 
	 */
	private static final long serialVersionUID = -1097523167911726307L;
	JButton B1;
	JButton B2;
	JButton B3;
	JButton B4;
	Font police = new Font("Calibri", Font.BOLD, 20);
	JTextField jt = new JTextField();
	

	public Gmenu(Engine engine, JFrame jf, Graph g) {
		this.setSize(250, 800);
		this.setLayout(null);
		

		B1 = new GDBouttons(engine, jt,jf,g);
		B2 = new GDBouttons(engine, jt,jf,g);
		B3 = new GDBouttons(engine, jt,jf,g);
		B4 = new GDBouttons(engine, jt,jf,g);

		B1.setText("Menu Principal");
		B2.setText("Vérifier");
		B3.setText("Résoudre");
		B4.setText("Sauvegarder");

		B1.setFont(police);
		B2.setFont(police);
		B3.setFont(police);
		B4.setFont(police);

		B1.setSize(200, 50);
		B2.setSize(200, 50);
		B3.setSize(200, 50);
		B4.setSize(200, 50);

		B1.setLocation(10, 220);
		B2.setLocation(B1.getX(), B1.getY() + B1.getHeight() + 10);
		B3.setLocation(B2.getX(), B2.getY() + B2.getHeight() + 10);
		B4.setLocation(B3.getX(), B3.getY() + B3.getHeight() + 10);

		jt.setSize(200, 50);
		jt.setFont(police);
		jt.setLocation(B4.getX(), B4.getY() + B4.getHeight() + 10);

		
		
		this.add(B1);
		this.add(B2);
		this.add(B3);
		this.add(B4);
		this.add(jt);
		
		
		
		
		
		
		
		
		
	}

}
