package robot.game;

import java.util.ArrayList;

public class HistoryLog {
	
		//List of commands which have been entered by user
		static ArrayList<String> historyLog = new ArrayList<String>();
		
		public HistoryLog() {
			for (String line: historyLog) {
    			System.out.println(line);
    		}
		}
		
		/**
		 * Adds each line entered by user to the history log
		 * @param command
		 */
		public static void addToCommandList(String command) {
			historyLog.add(command);
		}

		public static int length() {
			return historyLog.size();
		}

		public static String get(int i) {
			return historyLog.get(i);
			
		}

}
