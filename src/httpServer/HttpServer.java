package httpServer;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.concurrent.Executor;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import data.robot.RoboList;
import data.team.TeamList;
import data.tournament.Tournament;

public class HttpServer implements Runnable{
	private static final int HTTP_PORT = 8080;
	private ServerSocket listen;
	private Executor ex;
	private RoboList robo_list;
	private TeamList team_list;
	private Tournament tour;


	public HttpServer(
			Executor _ex,
			RoboList _robo_list,
			TeamList _team_list,
			Tournament _tour
	) {
		this.ex = _ex;
		this.robo_list = _robo_list;
		this.team_list = _team_list;
		this.tour = _tour;
		
		try {
			listen = new ServerSocket(HTTP_PORT);
		} catch (IOException e) {
			e.printStackTrace();
			JFrame f = new JFrame();
			JLabel label = new JLabel("HTTP_Port 8080 is already used.");
			JOptionPane.showMessageDialog(f, label);
			System.exit(1);
		}

		new HttpWindow(HTTP_PORT);
		
		ex.execute(this);
	}

	@Override
	public void run() {
		while(true) {
			
			try {
				Socket soc = listen.accept();
				System.out.println("[" + soc.getPort() + "]" + "connected from" + soc.getRemoteSocketAddress());
				ex.execute(new HttpSocket(soc, robo_list, team_list, tour));
				
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		
	}

}
