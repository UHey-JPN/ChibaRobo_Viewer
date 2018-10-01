
package httpServer;

import java.io.BufferedWriter;
import java.io.IOException;

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
	
	public static String PAGE_STATUS
		= "<h1>Chibarobo System Status</h1>"
		+ "<a href=\"/\">top</a><br>";

	
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
}
