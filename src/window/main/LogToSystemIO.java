package window.main;

import window.logger.LogMessageAdapter;

public class LogToSystemIO implements LogMessageAdapter {

	@Override
	public void log_println(String x) {
		System.out.println(x);
	}

	@Override
	public void log_print(Exception e) {
		e.printStackTrace();
	}

}
