package httpServer;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.Socket;
import java.util.HashMap;

import data.communication.StateData;
import data.exception.DataNotFoundException;
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
	private StateData state;

	public HttpSocket(
			Socket _soc,
			RoboList _robo_list,
			TeamList _team_list,
			Tournament _tour,
			StateData _state
	) {
		this.soc = _soc;
		this.robo_list = _robo_list;
		this.team_list = _team_list;
		this.tour = _tour;
		this.state = _state;
		
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
			
			String[] command = line.split(" ");
			if(!command[0].equals("GET")) {
				System.err.println(str_port + "HTTP Request is not \"GET\"");
				return;
			}
			
			String[] location = command[1].split("/");
			if(command[1].equals("/")) {
				// ルートページを表示
				out.write(HtmlData.HEADER_OK);
				out.write(HtmlData.PAGE_ROOT);
				out.flush();
			} else if(location[1].equals("team_list")) {
				HtmlDecoder.send_team_page(out, robo_list, team_list, tour, false);
				
			} else if(location[1].equals("team_list_mc")) {
				HtmlDecoder.send_team_page(out, robo_list, team_list, tour, true);
				
			} else if(location[1].equals("team")) {
				// 表示するチームの番号
				int t = Integer.parseInt(location[2]);
				
				// 送信用データの作成
				HashMap<String, String> map = new HashMap<String, String>();
				try {
					map.put("team", team_list.get_team_name(t));
					map.put("team_desc", team_list.get_team_desc(t));
					for(int i = 0; i < 2; i++){
						map.put("robot"+i, robo_list.get_name(team_list.get_robot_id(t)[i]));
						map.put("creator"+i, robo_list.get_creator(team_list.get_robot_id(t)[i]));
						map.put("grade"+i, robo_list.get_grade(team_list.get_robot_id(t)[i]));
						map.put("robot_desc"+i, robo_list.get_desc(team_list.get_robot_id(t)[i]));
					}
					out.write(HtmlData.HEADER_OK);
					HtmlDecoder.send_html(out,HtmlData.HTML_TEAMINFO, map);
					
				} catch (DataNotFoundException e) {
					out.write(HtmlData.HEADER_OK);
					out.write(HtmlData.PAGE_H_SIMPLE);
					out.write("<br>Error! チームデータが存在しません。<br>" + CRLF);
					out.write(HtmlData.PAGE_F_SIMPLE);
					out.flush();
					e.printStackTrace();
				}
				
			} else if(location[1].equals("status")) {
				if(state == null) {
					// ページの表示
					out.write(HtmlData.HEADER_OK);
					out.write(HtmlData.PAGE_H_SIMPLE);
					out.write("<br>Error! Please Update Show Mode.<br>" + CRLF);
					out.write(HtmlData.PAGE_F_SIMPLE);
					out.flush();
				}else {
					int[] team = {0,0};
					
					out.write(HtmlData.HEADER_OK);
					team[1] = Integer.valueOf(state.get_team_desc()[0].split(",")[0]);
					team[0] = Integer.valueOf(state.get_team_desc()[1].split(",")[0]);
					
					// 送信用データの作成
					HashMap<String, String> map = new HashMap<String, String>();
					for(int i = 0; i < 2; i++){
						try {
							map.put("team"+i, team_list.get_team_name(team[i]));
							map.put("team_desc"+i, team_list.get_team_desc(team[i]));
							for(int j = 0; j < 2; j++){
								map.put("robot"+i+"_"+j, robo_list.get_name(team_list.get_robot_id(team[i])[j]));
								map.put("creator"+i+"_"+j, robo_list.get_creator(team_list.get_robot_id(team[i])[j]));
								map.put("grade"+i+"_"+j, robo_list.get_grade(team_list.get_robot_id(team[i])[j]));
								map.put("robot_desc"+i+"_"+j, robo_list.get_desc(team_list.get_robot_id(team[i])[j]));
							}
						} catch (DataNotFoundException e) {
							e.printStackTrace();
						}
					}

					// データをハッシュマップで置き換えて送信
					HtmlDecoder.send_html(out, HtmlData.HTML_STATUS, map);
				}
				
			} else {
				out.write(HtmlData.HEADER_404);
				out.write(HtmlData.PAGE_404);
				System.err.println(str_port + "HTTP status 404:" + command[1]);
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
