package main;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Calendar;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import communication.udp.StateUpdateListener;
import data.communication.StateData;
import window.cardTournament.TournamentPanel;

public class ViewerMc extends JFrame implements ActionListener, StateUpdateListener, Runnable {
	private static final long serialVersionUID = 1L;
	
	private final static int WIDTH_TOUR_INFO = 450;

	private TeamInfoDisplay l_infomation = new TeamInfoDisplay();
	private TeamInfoDisplay r_infomation = new TeamInfoDisplay();
	
	private boolean flip = true;

	private JPanel info_panel = new JPanel();
	private JPanel info_btn_panel = new JPanel();
	private JButton flip_btn = new JButton("Flip");
	private JLabel l_now_time = new JLabel("12 : 00 - 00");

	private StateData state;
	
	
	public ViewerMc(TournamentPanel t_view) {
		this.setSize(1024, 768);
		this.setTitle("MC Mode");

		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		l_now_time.setHorizontalAlignment(JLabel.CENTER);
		l_now_time.setFont(new Font("", Font.BOLD, 50));
		l_now_time.setOpaque(true);
		l_now_time.setBackground(Color.YELLOW);
		l_now_time.setBorder(new EmptyBorder(5,5,5,5));
		
		info_panel.setLayout(new GridLayout(1,2));
		info_panel.add(l_infomation);
		info_panel.add(r_infomation);
		
		info_btn_panel.setLayout(new BorderLayout());
		info_btn_panel.add(l_now_time, BorderLayout.NORTH);
		info_btn_panel.add(info_panel, BorderLayout.CENTER);
		info_btn_panel.add(flip_btn, BorderLayout.SOUTH);
		
		t_view.setPreferredSize(new Dimension(WIDTH_TOUR_INFO, 300));
		
		this.getContentPane().removeAll();
		this.getContentPane().setLayout(new BorderLayout());
		this.getContentPane().add(t_view, BorderLayout.WEST);
		this.getContentPane().add(info_btn_panel, BorderLayout.CENTER);
		
		flip_btn.addActionListener(this);
		
		Executors.newSingleThreadScheduledExecutor().scheduleAtFixedRate(this, 0, 20, TimeUnit.MILLISECONDS);

		this.setVisible(true);
		
	}


	@Override
	public void state_update(StateData state) {
		this.state = state;
		String[] team = state.get_team_desc();
		String[] robo = state.get_robot_desc();
		if( flip == true ){
			l_infomation.update(team[1], robo[2], robo[3]);
			r_infomation.update(team[0], robo[0], robo[1]);
		}else{
			l_infomation.update(team[0], robo[0], robo[1]);
			r_infomation.update(team[1], robo[2], robo[3]);
		}		
	}


	@Override
	public void actionPerformed(ActionEvent e) {
		flip = !flip;
		if( state != null ){
			state_update(state);
		}
	}


	@Override
	public void run() {
		Calendar t = Calendar.getInstance();
		String now_time = "";
		if( t.get(Calendar.HOUR_OF_DAY) < 10 ){
			now_time += "0";
		}
		now_time += t.get(Calendar.HOUR_OF_DAY);
		now_time += " : ";
		if( t.get(Calendar.MINUTE) < 10 ){
			now_time += "0";
		}
		now_time += t.get(Calendar.MINUTE);
		now_time += " : ";
		if( t.get(Calendar.SECOND) < 10 ){
			now_time += "0";
		}
		now_time += t.get(Calendar.SECOND);
		l_now_time.setText(now_time);
	}



}
