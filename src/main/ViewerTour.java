package main;

import java.awt.BorderLayout;

import javax.swing.JFrame;

import window.cardTournament.TournamentPanel;

public class ViewerTour extends JFrame {
	private static final long serialVersionUID = 1L;
	
	public ViewerTour(TournamentPanel t_view) {
		this.setSize(1024, 768);
		this.setTitle("Tournament");
		
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		this.getContentPane().setLayout(new BorderLayout());
		this.getContentPane().add(t_view);
		
		
		this.setVisible(true);
		
	}

}
