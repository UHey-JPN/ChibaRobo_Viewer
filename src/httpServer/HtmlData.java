package httpServer;

public class HtmlData {
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
		+ "<font size=\"10\"><a href=\"/team_list\">team list</a></font><br>"
		+ "<font size=\"10\"><a href=\"/status\">status</a></font><br>"
//		+ "<font size=\"10\"><a href=\"/team_list_mc\">team list(MC)</a></font><br>"
		;

	
	public static String PAGE_H_TEAM
		= "<!DOCTYPE html>"
		+ "<html>"
		+ "<head>"
		+ "<title>チームリスト</title>"
		+ "<meta http-equiv=\"Content-Type\" content=\"text/html; charset=UTF-8\">"
		+ "</head>"
		+ "<body>"
		+ "<h1>千葉ロボ ロボットリスト・チームリスト</h1>"
		;

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
	public static String PAGE_F_TEAM
		= "</body>"
		+ "</html>";

	public static String PAGE_404
		= "<h1>404 Not Found</h1>";

	public static final String HTML_STATUS
	= "<!doctype html>"
	+ "<html>"
	+ "<head>"
	+ "	<style>"
	+ ""
	+ "		.tableline{"
	+ "			letter-spacing: -.4em; /*inline-blockの隙間対策*/"
	+ "			width:98%;"
	+ "			margin-left: auto;"
	+ "			margin-right: auto;"
	+ "			margin-bottom:0.4%;"
	+ "			display:table;"
	+ "			table-layout: fixed;"
	+ "		}"
	+ "		.tablecont{"
	+ "			padding-left:0.5%;"
	+ "			letter-spacing: normal; /*inline-blockの隙間対策*/"
	+ "			display:table-cell;"
	+ "			width:21%;"
	+ "			margin-left:0.2%;"
	+ "			"
	+ "		"
	+ "		}"
	+ "		.tablecont2{"
	+ ""
	+ "			padding-left:0.5%;"
	+ "			letter-spacing: normal; /*inline-blockの隙間対策*/"
	+ "			display:table-cell;"
	+ "			width:42.7%;"
	+ "			margin-left:0.2%;"
	+ "			height:100%;"
	+ "		}"
	+ "		.tablecont3{"
	+ "			padding:0;"
	+ "			letter-spacing: normal; /*inline-blockの隙間対策*/"
	+ "			display:table-cell;"
	+ "			width:42.7%;"
	+ "			margin-left:0.2%;"
	+ "			height:100%;"
	+ "		}"
	+ "		.cont{"
	+ "			"
	+ "			padding:3px;"
	+ "			word-wrap: break-word;"
	+ "		}"
	+ "		.rowtitle{"
	+ "			margin-left: 1%;"
	+ "			letter-spacing: normal; /*inline-blockの隙間対策*/"
	+ "			background:#75EAA7;"
	+ "			display:table-cell;"
	+ "			width:8%;"
	+ "		}"
	+ "		.rowtitle2{"
	+ "			margin-left: 1%;"
	+ "			letter-spacing: normal; /*inline-blockの隙間対策*/"
	+ ""
	+ "			display:table-cell;"
	+ "			width:8%;"
	+ "		}"
	+ "		.roundall{"
	+ "			border-radius: 6px;"
	+ "			"
	+ "		}"
	+ "		.roundright{"
	+ "			border-bottom-right-radius: 6px;"
	+ "			border-top-right-radius: 6px"
	+ "		}"
	+ "		.roundleft{"
	+ "			border-bottom-left-radius: 6px;"
	+ "			border-top-left-radius: 6px"
	+ "		}"
	+ "		.red{"
	+ "			background:#BC0A0A;"
	+ "		}"
	+ "		.blue{"
	+ "			background:#3131F4;"
	+ "		}"
	+ "		.red2{"
	+ "			background:#EA4949;"
	+ "		}"
	+ "		.blue2{"
	+ "			background:#9191F9;"
	+ "		}"
	+ "		.redodd{"
	+ "			background:#FFCCCC;"
	+ "		}"
	+ "		.redeven{"
	+ "			background:#ED7474;"
	+ "		}"
	+ "		.blueodd{"
	+ "			background:#99CCFF;"
	+ "		}"
	+ "		.blueeven{"
	+ "			background:#A4A4F7;"
	+ "		}"
	+ "		.yellow{"
	+ "			background:#EC8924;"
	+ "		}"
	+ "		.headtxt{"
	+ "			"
	+ "			"
	+ "			padding:0;"
	+ "			margin:0;"
	+ "			color:white;"
	+ "			text-align: center;"
	+ "		}"
	+ "		"
	+ "		.reload{"
	+ "			display:none;	"
	+ "		}"
	+ "		.headtxt{"
	+ "			font-size: 50px;/* 見出し文字いじる用*/ "
	+ "		}"
	+ "		.big{"
	+ "			font-size:23px;	/* 本文フォント(強調用）いじる用*/ "
	+ "		}"
	+ "		.normal{"
	+ "	     font-size:18px;  /* 本文フォントいじる用*/ "
	+ "		}"
	+ "	</style>"
	+ "<meta http-equiv=\"Content-Type\" content=\"text/html; charset=UTF-8\">"
	+ "<title>無題ドキュメント</title>"
	+ "</head>"
	+ "	<div class=\"tableline\">"
	+ "	<div class=\"rowtitle2\">"
	+ "		<label><input type=\"button\"onclick=\"window.location.reload(true);\" class=\"reload\">"
	+ "<svg style=\"baseline-shift: baseline\"version=\"1.1\" id=\"レイヤー_1\" xmlns=\"http://www.w3.org/2000/svg\""
	+ "	 xmlns:xlink=\"http://www.w3.org/1999/xlink\" x=\"0px\""
	+ "	 y=\"0px\" width=\"100%\" viewBox=\"0 0 480 480\" enable-background=\"new 0 0 480 480\" xml:space=\"preserve\">"
	+ "<path fill=\"#455A64\" d=\"M416.786,322.322l-77.752-34.387C320.659,329.217,279.286,358,231.185,358c-65.17,0-118-52.83-118-118"
	+ "	c0-65.169,52.83-118,118-118c34.841,0,66.14,15.113,87.738,39.127L263.815,192l155.914,56.76L451.815,86l-57.549,33.125"
	+ "	C357.278,69.297,298.009,37,231.185,37c-112.114,0-203,90.886-203,203c0,112.114,90.886,203,203,203"
	+ "	C313.99,443,385.206,393.415,416.786,322.322z\"/>"
	+ "</svg>"
	+ "			</input></label>"
	+ "</div>"
	+ "	<div class=\"tablecont3 red roundall\">"
	+ "		<p class=\"headtxt\">赤チーム</p>"
	+ "	</div>"
	+ "    <div class=\"tablecont3 blue roundall\">"
	+ "		<p class=\"headtxt\">青チーム</p>"
	+ "	</div>"
	+ "</div>	"
	+ "<div class=\"tableline\">"
	+ "	<div class=\"rowtitle roundleft cont\">"
	+ "		<p class=\"big\">チーム名</p>"
	+ "	</div>"
	+ "	<div class=\"tablecont2 red2 cont\">"
	+ "		<p class=\"big\">[!team0]</p>"
	+ "	</div>"
	+ "    <div class=\"tablecont2 blue2 cont roundright\">"
	+ "		<p class=\"big\">[!team1]</p>"
	+ "	</div>"
	+ "</div>	"
	+ "<div class=\"tableline\">"
	+ "	<div class=\"rowtitle roundleft cont\">"
	+ "		<p class=\"normal\">チーム紹介</p>"
	+ "	</div>"
	+ "	<div class=\"tablecont2 red2 cont\">"
	+ "		<p class=\"normal\">[!team_desc0]</p>"
	+ "	</div>"
	+ "    <div class=\"tablecont2 blue2 cont roundright\">"
	+ "		<p class=\"normal\">[!team_desc1]</p>"
	+ "	</div>"
	+ "</div>	"
	+ "<div class=\"tableline\">"
	+ "	<div class=\"rowtitle roundleft cont\">"
	+ "		<p >ロボット名</p>"
	+ "	</div>"
	+ "	<div class=\"tablecont redodd cont\">"
	+ "	<p class=\"big\">[!robot0_0]</p>"
	+ "	</div>"
	+ "	<div class=\"tablecont redeven cont\">"
	+ "		<p class=\"big\">[!robot0_1]</p>"
	+ "	</div>"
	+ "    <div class=\"tablecont blueodd cont\">"
	+ "		<p class=\"big\">[!robot1_0]</p>"
	+ "	</div>"
	+ "	<div class=\"tablecont blueeven cont roundright\">"
	+ "		<p class=\"big\">[!robot1_1]</p>"
	+ "	</div>"
	+ "</div>"
	+ "<div class=\"tableline\">"
	+ "	<div class=\"rowtitle roundleft cont\">"
	+ "		<p class=\"normal\">製作者名</p>"
	+ "	</div>"
	+ "	<div class=\"tablecont redodd cont\">"
	+ "		<p class=\"normal\">[!creator0_0]</p>"
	+ "	</div>"
	+ "	<div class=\"tablecont redeven cont\">"
	+ "	<p class=\"normal\">[!creator0_1]</p>"
	+ "	</div>"
	+ "    <div class=\"tablecont blueodd cont\">"
	+ "	<p class=\"normal\">[!creator1_0]</p>"
	+ "	</div>"
	+ "	<div class=\"tablecont blueeven cont roundright\">"
	+ "	<p class=\"normal\">[!creator1_1]</p>"
	+ "	</div>"
	+ "</div>"
	+ "<div class=\"tableline\">"
	+ "	<div class=\"rowtitle roundleft cont\">"
	+ "	<p class=\"normal\">学年</p>"
	+ "	</div>"
	+ "	<div class=\"tablecont redodd cont\">"
	+ "	<p class=\"normal\">[!grade0_0]</p>"
	+ "	</div>"
	+ "	<div class=\"tablecont redeven cont\">"
	+ "		<p class=\"normal\">[!grade0_1]</p>"
	+ "	</div>"
	+ "    <div class=\"tablecont blueodd cont\">"
	+ "	<p class=\"normal\">[!grade1_0]</p>"
	+ "	</div>"
	+ "	<div class=\"tablecont blueeven cont roundright\">"
	+ "<p class=\"normal\">[!grade1_1]</p>"
	+ "	</div>"
	+ "</div>"
	+ "<div class=\"tableline\">"
	+ "	<div class=\"rowtitle roundleft cont\">"
	+ "		<p class=\"normal\">説明</p>"
	+ "	</div>"
	+ "		<div class=\"tablecont redodd cont\">"
	+ "		<p class=\"normal\">[!robot_desc0_0]</p>"
	+ "	</div>"
	+ "	<div class=\"tablecont redeven cont\">"
	+ "		<p class=\"normal\">[!robot_desc0_1]</p>"
	+ "	</div>"
	+ "    <div class=\"tablecont blueodd cont\">"
	+ "		<p class=\"normal\">[!robot_desc1_0]</p>"
	+ "	</div>"
	+ "	<div class=\"tablecont blueeven cont roundright\">"
	+ "		<p class=\"normal\">[!robot_desc1_1]</p>"
	+ "	</div>"
	+ "</div>"
	+ "<label><input type=\"button\"onclick=\"window.location.reload(true);\" class=\"reload\">"
	+ " <div class=\"tableline yellow\">　　　"
	+ "	 <p class=\"headtxt\" style＝\"display:inline;\">更新</p>"
	+ "	</div>"
	+ "</input></label>"
	+ "<body>"
	+ "</body>"
	+ "</html>"
	+ "";
	
	
/*	// -------------------------------------------------------------------------------
	// send status page
	public void send_status_page(
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

		
		// ページ上部の表示
		out.write(HtmlData.HEADER_OK);
		
//		out.write(html);
        try {
            InputStream in_stream = getClass().getClassLoader().getResourceAsStream("status.html");
            BufferedReader buf = new BufferedReader(new InputStreamReader(in_stream));
            String data;
            while ((data = buf.readLine()) != null) {
            	
                out.write(data);
            }
         
            // 4.最後にファイルを閉じてリソースを開放する
            in_stream.close();
            
        } catch (IOException e) {
            e.printStackTrace();
        }
				
		out.flush();
		
	}*/

}
