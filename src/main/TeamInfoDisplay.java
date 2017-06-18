package main;

import java.awt.Color;
import java.awt.Font;
import java.awt.GridLayout;

import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.border.CompoundBorder;
import javax.swing.border.EmptyBorder;
import javax.swing.border.LineBorder;
import javax.swing.border.TitledBorder;

public class TeamInfoDisplay extends JPanel {
	private static final long serialVersionUID = 1L;
	
	private JLabel team_name = new JLabel("No Team");
	private TitledBorder[] robo_name = { new TitledBorder("No Robot"), new TitledBorder("No Robot")};
	private JLabel[] creater = {new JLabel("No Creator"), new JLabel("No Creator")};
	private JLabel[] grade = {new JLabel("No Grade"), new JLabel("No Grade")};
	
	
	TeamInfoDisplay(){
		this.setBackground(Color.WHITE);
		this.setOpaque(true);
		
		this.setLayout(new GridLayout(3,1));
		this.setBorder(new LineBorder(Color.BLACK, 1));
		
		team_name.setHorizontalAlignment(JLabel.CENTER);
		team_name.setFont(new Font("", Font.BOLD, 30));
		team_name.setBorder(new EmptyBorder(10,10,10,10));
		
		this.add(team_name);
		
		for(int i = 0; i < 2; i++){
			JPanel p = new JPanel();
			p.setOpaque(false);
			
			robo_name[i].setTitleFont(new Font("", Font.BOLD, 30));
			grade[i].setFont(new Font("", Font.BOLD, 30));
			grade[i].setHorizontalAlignment(JLabel.CENTER);
			creater[i].setFont(new Font("", Font.BOLD, 30));
			creater[i].setHorizontalAlignment(JLabel.CENTER);

			CompoundBorder border = new CompoundBorder(new EmptyBorder(10,10,10,10), robo_name[i]);

			p.setBorder(border);
			p.setLayout(new GridLayout(2,1));
			p.add(grade[i]);
			p.add(creater[i]);
			this.add(p);
		}
	}
	
	public void update(String team, String robot0, String robot1){
		String[] s;
		s = team.split(",");
		if( s.length >= 4 ){
			team_name.setText(s[3]);
		}else{
			System.out.println("team is illegal.:" + team);
		}
		
		s = robot0.split(",");
		if( s.length >= 4 ){
			robo_name[0].setTitle(s[1]);
			creater[0].setText(s[2]);
			grade[0].setText(s[3]);
		}else{
			System.out.println("robo0 is illegal.:" + robot0);
		}

		s = robot1.split(",");
		if( s.length >= 4 ){
			robo_name[1].setTitle(s[1]);
			creater[1].setText(s[2]);
			grade[1].setText(s[3]);
		}else{
			System.out.println("robo0 is illegal.:" + robot1);
		}
	}


}
