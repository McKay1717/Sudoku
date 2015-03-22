package gui;

import java.awt.BorderLayout;

import javax.swing.JFrame;
import javax.swing.JPanel;

import engine.Engine;

public class GamePanel extends JPanel {

	public GamePanel(Engine engine, JFrame f) {
		Graph g = new Graph(engine);
		Gmenu gdb = new Gmenu(engine,f, g); 
		g.setLocation(0, 0);
		gdb.setLocation(820, 0);
		g.setVisible(true);
		gdb.setVisible(true);
		this.setSize(1100, 850);
		this.setLayout(null);
		this.add(g);
		this.add(gdb);
	}

}
