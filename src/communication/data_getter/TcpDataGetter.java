package communication.data_getter;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;

import data.communication.ServerData;

public class TcpDataGetter implements Runnable {
	public static enum TYPE {
		ROBOT("robot"),
		TEAM("team"),
		TOUR("tournament");
		
		private String label;
		
		private TYPE(String label){
			this.label = label;
		}
		
		public String label(){
			return label;
		}
	}

	private TYPE type;
	private DatabaseGetterListener listener;
	private ServerData state;
	
	public TcpDataGetter(TYPE type, DatabaseGetterListener listener, ServerData state) {
		this.type = type;
		this.listener = listener;
		this.state = state;
		
		System.out.println("data request (" + this.type.label() + ")");
	}

	@Override
	public void run() {
		Socket soc = null;
		BufferedReader in = null;
		PrintWriter out = null;
		String ret = "";
		
		try {
			soc = new Socket(state.get_server_ip(), state.get_db_port());
			in = new BufferedReader(new InputStreamReader(soc.getInputStream()));
			out = new PrintWriter(soc.getOutputStream(), true);
			
			// set data type
			out.println( type.label() + ",utf-8");
			
			// get ACK
			{
				String line;
				line = in.readLine();
				System.out.println(line + " (get from server, in " + type.label() +" data)");
				if( ! line.matches("ACK") ) return;
			}
			
			// get data
			{
				String line;
				while( (line = in.readLine()) != null ){
					ret += line;
				}
			}
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			if( out != null ) out.close();
				try {
					if( soc != null ) soc.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
		}
		listener.set_new_database(ret);
	}

}
