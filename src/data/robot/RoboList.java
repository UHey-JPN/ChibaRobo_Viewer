package data.robot;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.concurrent.Executor;

import communication.data_getter.TcpDataGetter;
import communication.udp.ServerUpdateListener;
import communication.data_getter.DatabaseGetterListener;
import data.communication.ServerData;
import data.exception.DataNotFoundException;
import window.tournament.UpdateDatabaseListener;
import window.tournament.UpdateTourViewListener;

public class RoboList implements ServerUpdateListener, DatabaseGetterListener, UpdateDatabaseListener{
	private List<Robot> list;
	private final Object lock_obj = new Object();
	private Executor ex;
	private ServerData state = null;
	private UpdateTourViewListener update_view_listener;
	private boolean initialize = false;
	
	
	public RoboList(Executor ex) {
		this.list = Collections.synchronizedList(new ArrayList<Robot>());
		this.ex = ex;
	}
	
	public boolean add(int id, String data){
		synchronized(lock_obj){
			for(Robot r : list){
				if( r.get_id() == id ){
					list.remove(r);
				}
			}
			list.add(new Robot(id, data));
			return list.get(list.size()-1).get_valid();
		}
	}
	
	public void set_UpdateTourViewListener(UpdateTourViewListener l){
		update_view_listener = l;
	}
	
	public void clear(){
		synchronized(lock_obj){
			list.clear();
		}
	}

	public String get_robot_summary(int id) throws DataNotFoundException {
		synchronized(lock_obj){
			for(Robot r : list){
				if(r.get_id() == id){
					return r.get_summary();
				}
			}
			throw new DataNotFoundException("Robot(id=" + id + ") is not found.");
		}
	}
	
	public void update_robot_list(){
		System.out.println("----------------- update robot list -----------------");
		ex.execute( new TcpDataGetter(TcpDataGetter.TYPE.ROBOT, this, this.state) );
	}

	@Override
	public void server_update(ServerData state) {
		this.state = state;
		if( initialize == false ){
			update_robot_list();
			initialize = true;
		}
	}

	@Override
	public void set_new_database(String data) {
		// XML Process
		RobotXmlParser xml_process = new RobotXmlParser(data);	
		list = xml_process.get_list();
		
		// update TourView
		if( update_view_listener != null) update_view_listener.update_tour_view();
	}

	@Override
	public void update_database() {
		this.update_robot_list();
	}

}
