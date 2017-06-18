package window.tournament;

import java.awt.BorderLayout;
import java.awt.Color;

import javax.swing.JPanel;
import javax.swing.border.LineBorder;


public class TournamentPanel extends JPanel {
	private static final long serialVersionUID = 1L;
	
	private JPanel main_panel = new JPanel();

	public TournamentViewer main_view;
	public UpdateTournamentButton update_button = new UpdateTournamentButton();

	
	public TournamentPanel() {
		main_view = new TournamentViewer();
		
		this.setBackground(Color.WHITE);
		this.setOpaque(true);
		
		// setup main panel
		main_panel.setOpaque(false);
		main_panel.setBorder(new LineBorder(Color.BLACK));
		main_panel.setLayout( new BorderLayout() );
		main_panel.add(main_view);
		
		// setup this card
		this.setLayout(new BorderLayout());
		this.add( main_panel, BorderLayout.CENTER );
		this.add( update_button , BorderLayout.SOUTH);
	}
	
	public void draw_now_game(){
		main_view.draw_now_game();
	}
	
	public UpdateTourViewListener get_UpdateTourViewListener(){
		return main_view;
	}
}

