package httpServer;

import java.io.BufferedWriter;
import java.io.IOException;
import java.util.HashMap;

import data.exception.DataNotFoundException;
import data.robot.RoboList;
import data.team.TeamList;
import data.tournament.Tournament;

public class HtmlDecoder {
	// -------------------------------------------------------------------------------
	// send team list page
	public static void send_team_page(
			BufferedWriter out,
			RoboList robo_list,
			TeamList team_list,
			Tournament tour,
			boolean with_link
	) throws IOException {
		// ページ上部の表示
		out.write(HtmlData.HEADER_OK);
		out.write(HtmlData.PAGE_H_TEAM);
		
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
			if(with_link) out.write("<a href=\"team/" + t + "\">");
			try {
				out.write(team_list.get_team_name(t));
			} catch (DataNotFoundException e) {
				out.write("N/A");
			}
			if(with_link) out.write("</a>");
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
		out.write(HtmlData.PAGE_F_TEAM);
		out.flush();
		
	}
	
	
	// -------------------------------------------------------------------------------
	// send replace key with Hashmap and send
	public static void send_html(
			BufferedWriter out,
			String html,
			HashMap<String, String> word_map
	) throws IOException {
		for(String k : word_map.keySet()) {
			// System.out.println(k + "=>" + word_map.get(k));
			html = html.replaceAll("\\[!"+k+"\\]", word_map.get(k));
		}
		// System.out.println(html);
		out.write(html);
		out.flush();
		
	}
	
	

}
