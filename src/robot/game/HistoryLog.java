package robot.game;

import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.util.ArrayList;

public class HistoryLog extends KeyAdapter {
	
		//List of commands which have been entered by user
		static ArrayList<String> historyLog = new ArrayList<String>();
		//private CodePanel codePanel;
		
		public HistoryLog() {
						
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
		
		/**
		 * This method controls the player object by the arrow keys on the keyboard
		 */
		@Override
		public void keyPressed(KeyEvent e) {
			
			int key = e.getKeyCode();
			int pressedKeys = 0;
						
			if (key == KeyEvent.VK_UP) {
				String phrase = historyLog.get(historyLog.size()-(pressedKeys+1));
				pressedKeys ++;
				System.out.println(phrase);
				CodePanel.setText(phrase);
			}
			else if (key == KeyEvent.VK_DOWN && pressedKeys != 0) {
				String phrase = historyLog.get(historyLog.size()-(pressedKeys-1));
				pressedKeys ++;
				System.out.println(phrase);
				CodePanel.setText(phrase);
						
			}
		}

}
