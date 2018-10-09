package httpServer;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Font;
import java.awt.GridLayout;
import java.net.InetAddress;
import java.net.UnknownHostException;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.border.CompoundBorder;
import javax.swing.border.EmptyBorder;
import javax.swing.border.TitledBorder;

public class HttpWindow extends JFrame {
	private static final long serialVersionUID = 1L;

	public HttpWindow(int port) {
		// IPアドレスの取得
		String ip_addr = "N/A";
		try {
			ip_addr = InetAddress.getLocalHost().getHostAddress();
		} catch (UnknownHostException e) {
			e.printStackTrace();
		}
		
		// このJFrame(ウィンドウ)の設定
		this.setSize(470, 200);
		this.setLocationRelativeTo(null);
		this.setTitle("About HTTP Server");
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		// パネル用のTitleBorderの設定
		TitledBorder t = new TitledBorder("About HTTP Server");
		t.setTitleFont(new Font("", Font.PLAIN, 20));
		CompoundBorder in_border = new CompoundBorder(t, new EmptyBorder(10,10,10,10));
		CompoundBorder border = new CompoundBorder(new EmptyBorder(20,20,20,20), in_border);
		
		// パネルの設定
		JPanel p = new JPanel();
		p.setBackground(Color.WHITE);
		p.setOpaque(true);
		p.setBorder(border);
		p.setLayout(new GridLayout(3, 2));
		
		p.add(new JLabel("IP Address"));
		p.add(new JTextField(ip_addr) {
			private static final long serialVersionUID = 1L;
			{
				this.setHorizontalAlignment(CENTER);
				this.setEditable(false);
			}
		});
		p.add(new JLabel("Listener PORT"));
		p.add(new JTextField("" + port) {
			private static final long serialVersionUID = 1L;
			{
				this.setHorizontalAlignment(CENTER);
				this.setEditable(false);
			}
		});
		p.add(new JLabel("Open with Browser"));
		p.add(new JTextField("http://"+ ip_addr +":" + port) {
			private static final long serialVersionUID = 1L;
			{
				this.setHorizontalAlignment(CENTER);
				this.setEditable(false);
			}
		});

		this.setLayout(new BorderLayout());
		this.add(p, BorderLayout.CENTER);
		
		this.setVisible(true);
	}
	

}
