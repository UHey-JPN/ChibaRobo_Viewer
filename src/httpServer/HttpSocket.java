package httpServer;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.Socket;

import data.robot.RoboList;
import data.team.TeamList;
import data.tournament.Tournament;

public class HttpSocket implements Runnable {
	public static final String CRLF = "\r\n";
	private Socket soc;
	private RoboList robo_list;
	private TeamList team_list;
	private Tournament tour;
	private String str_port;

	public HttpSocket(
			Socket _soc,
			RoboList _robo_list,
			TeamList _team_list,
			Tournament _tour
	) {
		this.soc = _soc;
		this.robo_list = _robo_list;
		this.team_list = _team_list;
		this.tour = _tour;
		
		str_port = "[" + soc.getPort() + "]";
	}
	
	@Override
	public void run() {
		
		try {
			BufferedReader in = new BufferedReader(
					new InputStreamReader(soc.getInputStream(), "UTF-8"));
			BufferedWriter out = new BufferedWriter(
					new OutputStreamWriter(soc.getOutputStream(), "UTF-8"));

			String line = in.readLine();
			if(line == null) return;
			System.out.println(str_port + line);
			
			String[] split = line.split(" ");
			if(!split[0].equals("GET")) {
				System.err.println(str_port + "HTTP Request is not \"GET\"");
				return;
			}
			
			if(split[1].equals("/")) {
				// ルートページを表示
				out.write(HttpData.HEADER_OK);
				out.write(HttpData.PAGE_ROOT);
				out.flush();
			} else if(split[1].equals("/team_list")) {
				HttpData.send_team_page(out, robo_list, team_list, tour);
				
			} else if(split[1].equals("/status")) {
				out.write(HttpData.HEADER_OK);
				out.write(HttpData.PAGE_STATUS);
				out.flush();
				
			} else {
				out.write(HttpData.HEADER_404);
				out.write(HttpData.PAGE_404);
				System.err.println(str_port + "HTTP status 404:" + split[1]);
				return;
			}
			
		
		} catch (IOException e) {
			e.printStackTrace();
			
		} finally {
			try {
				soc.close();
				System.out.println(str_port + "Socket is closed.");
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		
	}

}
