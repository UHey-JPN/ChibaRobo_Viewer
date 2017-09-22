package main;

import java.awt.BorderLayout;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.concurrent.Executor;
import java.util.concurrent.Executors;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.CompoundBorder;
import javax.swing.border.EmptyBorder;
import javax.swing.border.TitledBorder;

import communication.udp.UdpSocket;
import data.robot.RoboList;
import data.team.TeamList;
import data.tournament.Tournament;
import window.cardTournament.TournamentPanel;
import window.main.LogToSystemIO;

public class MainSelecter extends JFrame implements ActionListener {
	private static final long serialVersionUID = 1L;
	
	// 選択画面を生成するためのインスタンス
	private JPanel select_panel = new JPanel();
	private JButton btn_mc_mode = new JButton("MC Mode");
	private JButton btn_view_mode = new JButton("Tournament");
	private JButton[] mode_btn = { btn_mc_mode, btn_view_mode };
	
	// トーナメント画面を表示するためのPanelクラス
	private TournamentPanel tour_view;
	
	// ログはsystem ioに流すので、そのためのクラス
	LogToSystemIO log2systemio = new LogToSystemIO();
	
	// UDP通信の取りまとめ
	private UdpSocket udp;

	public MainSelecter() {
		// -----------------------------------------------------
		// ここから選択画面を生成
		this.setSize(300, 400);
		this.setLocationRelativeTo(null);
		this.setTitle("Select Mode");
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		TitledBorder t = new TitledBorder("Select Mode");
		t.setTitleFont(new Font("", Font.PLAIN, 20));
		CompoundBorder in_border = new CompoundBorder(t, new EmptyBorder(10,10,10,10));
		CompoundBorder border = new CompoundBorder(new EmptyBorder(20,20,20,20), in_border);
		select_panel.setBorder(border);
		
		// ボタンを配置
		select_panel.setLayout(new GridLayout(mode_btn.length, 1));
		for(int i = 0; i < mode_btn.length; i++){
			JPanel p = new JPanel();
			p.setBorder(new EmptyBorder(10,10,10,10));
			p.setLayout(new BorderLayout());
			p.add(mode_btn[i]);
			select_panel.add(p);
			
			mode_btn[i].setFont(new Font("", Font.PLAIN, 30));
			mode_btn[i].addActionListener(this);
		}
		
		this.getContentPane().setLayout(new BorderLayout());
		this.getContentPane().add(select_panel);
		
		
		// -----------------------------------------------------
		// 選択後に使用するTournamentPanelの設定
		Executor ex = Executors.newCachedThreadPool();
		tour_view = new TournamentPanel();
		
		RoboList robo_list = new RoboList(ex, log2systemio);
		TeamList team_list = new TeamList(ex, log2systemio);
		Tournament tour = new Tournament(ex, log2systemio);

		udp = new UdpSocket(ex, log2systemio);

		// -----------------------------------------------------
		// set listener
		udp.add_ServerUpdateListener(robo_list);
		udp.add_ServerUpdateListener(team_list);
		udp.add_ServerUpdateListener(tour.get_game_as_SUL());

		udp.add_TournamentUpdateListener(tour);

		tour_view.update_button.add_UpdateDatabaseListener(robo_list);
		tour_view.update_button.add_UpdateDatabaseListener(team_list);
		tour_view.update_button.add_UpdateDatabaseListener(tour.get_game_as_UDL());

		robo_list.set_UpdateTourViewListener(tour_view.get_UpdateTourViewListener());
		team_list.set_UpdateTourViewListener(tour_view.get_UpdateTourViewListener());
		tour.set_UpdateTourViewListener(tour_view.get_UpdateTourViewListener());
		
		tour_view.main_view.set_robo_list(robo_list);
		tour_view.main_view.set_team_list(team_list);
		tour_view.main_view.set_tournament(tour);
		
		udp.add_ServerUpdateListener(tour_view.update_button);
		
		
		this.setVisible(true);
		
	}
	
	public static void main(String[] args){
		new MainSelecter();
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		// 選択画面を非表示に
		this.setVisible(false);
		
		if( e.getSource() == btn_mc_mode ){
			tour_view.draw_now_game();
			udp.add_StateUpdateListener( new ViewerMc(tour_view) );
		}else if( e.getSource() == btn_view_mode ){
			new ViewerTour(tour_view);
		}
	}
}
