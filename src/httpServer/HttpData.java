
package httpServer;

import java.io.BufferedWriter;
import java.io.IOException;
import java.util.ArrayList;

import data.exception.DataNotFoundException;
import data.robot.RoboList;
import data.team.TeamList;
import data.tournament.Tournament;

public class HttpData {
	public static final String CRLF = "\r\n";

	public static String HEADER_OK
		= "HTTP/1.1 200 OK" + CRLF
		+ "Content-Type: text/html" + CRLF
		+ CRLF;

	public static String HEADER_404
		= "HTTP/1.1 404 Not Found" + CRLF
		+ "Content-Type: text/html" + CRLF
		+ CRLF;

	public static String PAGE_ROOT
		= "<h1>Chibarobo Viewer</h1>"
		+ "<a href=\"/team_list\">team list</a><br>"
		+ "<a href=\"/status\">status</a><br>";

	
	public static String PAGE_H_TEAM
		= "<!DOCTYPE html>"
		+ "<html>"
		+ "<head>"
		+ "<title>チームリスト</title>"
		+ "<meta http-equiv=\"Content-Type\" content=\"text/html; charset=UTF-8\">"
		+ "</head>"
		+ "<body>"
		+ "<h1>千葉ロボ ロボットリスト・チームリスト</h1>";

	public static String PAGE_F_TEAM
		= "</body>"
		+ "</html>";
	
	public static String PAGE_H_STATUS
		= "<!DOCTYPE html>"
		+ "<html>"
		+ "<head>"
		+ "<title>チームリスト</title>"
		+ "<meta http-equiv=\"refresh\" content=\"10\" >" 
		+ "<meta http-equiv=\"Content-Type\" content=\"text/html; charset=UTF-8\">"
		+ "</head>"
		+ "<body>"
		+ "<h1>Chibarobo System Status</h1>"
		+ "<a href=\"/\">top</a><br>";

	public static String PAGE_F_STATUS
		= "</body>"
		+ "</html>";
	
	public static String PAGE_404
		= "<h1>404 Not Found</h1>";

	
	
	public static void send_team_page(
			BufferedWriter out,
			RoboList robo_list,
			TeamList team_list,
			Tournament tour
	) throws IOException {
		// ページ上部の表示
		out.write(HttpData.HEADER_OK);
		out.write(HttpData.PAGE_H_TEAM);
		
		// テーブルを作る
		out.write("<table border=\"1\" width=\"500\" >");
		out.write("<tr>");
		out.write("<th>チーム名</th>");
		out.write("<th>ロボット名</th>");
		out.write("</tr>");

		// チームを表示
		int[] teamnum_list = tour.get_team_list();
		for(int t : teamnum_list) {
			out.write("<tr><td rowspan=\"2\">");
			try {
				out.write(team_list.get_team_name(t));
			} catch (DataNotFoundException e) {
				out.write("N/A");
			}
			out.write("</td><td>");
			try {
				out.write(robo_list.get_name(team_list.get_robot_id(t)[0]));
			} catch (DataNotFoundException e) {
				out.write("N/A");
			}
			out.write("</td></tr><tr><td>");
			try {
				out.write(robo_list.get_name(team_list.get_robot_id(t)[1]));
			} catch (DataNotFoundException e) {
				out.write("N/A");
			}
			out.write("</td></tr>");
		}
		out.write("</table>");
		
		// ページの下部を表示
		out.write(HttpData.PAGE_F_TEAM);
		out.flush();
		
	}
	public static void send_status_page(
			BufferedWriter out,
			RoboList robo_list,
			TeamList team_list,
			Tournament tour,
			int[] team_num,
			int team_win
	) throws IOException {
		// データ設定
		String[] team = {new String("N/A"), new String("N/A")};
		String[][] robot = {{new String("N/A"), new String("N/A")}, {new String("N/A"), new String("N/A")}};
		String[][] creator  = {{new String("N/A"), new String("N/A")}, {new String("N/A"), new String("N/A")}};
		String[][] grade = {{new String("N/A"), new String("N/A")}, {new String("N/A"), new String("N/A")}};
		String[][] desc  = {{new String("N/A"), new String("N/A")}, {new String("N/A"), new String("N/A")}};
		ArrayList<String[][]> list = new ArrayList<String[][]>();
		list.add(robot); list.add(creator); list.add(grade); list.add(desc);
		
		for(int i = 0; i < 2; i++) {
			try {
				team[i] = team_list.get_team_name(team_num[i]);
			} catch (DataNotFoundException e) {}
			for(int j = 0; j < 2; j++) {
				for(int k = 0; k < 4; k++) {
					try {
						if(k == 0) list.get(k)[i][j] = robo_list.get_name(team_list.get_robot_id(team_num[i])[j]);
						if(k == 1) list.get(k)[i][j] = robo_list.get_creator(team_list.get_robot_id(team_num[i])[j]);
						if(k == 2) list.get(k)[i][j] = robo_list.get_grade(team_list.get_robot_id(team_num[i])[j]);
						if(k == 3) list.get(k)[i][j] = robo_list.get_desc(team_list.get_robot_id(team_num[i])[j]);
					} catch (DataNotFoundException e) {}
				}
			}
		}
		
		
		String html
			= "<table border=\"1\" width=\"100%\"><tr align=\"center\"><th /><th colspan=\"2\">"
			+ team[0] + "</th><th colspan=\"2\">" + team[1] + "</th></tr><tr align=\"center\">"
			+ "<td align=\"left\">"
			+ "ロボット名" + "</td><th>"
			+ robot[0][0] + "</th><th>"
			+ robot[0][1] + "</th><th>"
			+ robot[1][0] + "</th><th>"
			+ robot[1][1] + "</th></tr><tr align=\"center\"><td align=\"left\">"
			+ "製作者名" + "</td><td>"
			+ creator[0][0] + "</td><td>"
			+ creator[0][1] + "</td><td>"
			+ creator[1][0] + "</td><td>"
			+ creator[1][1] + "</td></tr><tr align=\"center\"><td align=\"left\">"
			+ "学年" + "</td><td>"
			+ grade[0][0] + "</td><td>"
			+ grade[0][1] + "</td><td>"
			+ grade[1][0] + "</td><td>"
			+ grade[1][1] + "</td></tr><tr><td>"
			+ "説明" + "</td><td>"
			+ desc[0][0] + "</td><td>"
			+ desc[0][1] + "</td><td>"
			+ desc[1][0] + "</td><td>"
			+ desc[1][1] + "</td></tr></table>";

		
		// ページ上部の表示
		out.write(HttpData.HEADER_OK);
		out.write(HttpData.PAGE_H_STATUS);
		
		out.write(html);
		
		// ページの下部を表示
		out.write(HttpData.PAGE_F_STATUS);
		out.flush();
		
	}
}
