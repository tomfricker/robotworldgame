package robot.game;

import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.util.ArrayList;

/**
 *  
 * This class stores every input that the user puts in the codeInput in order to access it later through
 * the scroll down and up arrow keys.
 *
 * @author Robot World Group
 *
 */
public class HistoryLog extends KeyAdapter {
	
		//List of commands which have been entered by user
		static ArrayList<String> historyLog = new ArrayList<String>();
		//variable to store how many times the UP and DOWN keys were pressed
		private int pressedKeys = 0;
		
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
		
		public static void printHistory() {
			for (String log : historyLog) {
				System.out.println(log);
			}
		}
		
		/**
		 * This method controls the player object by the arrow keys on the keyboard
		 */
		@Override
		public void keyPressed(KeyEvent e) {
			
			int key = e.getKeyCode();
			
						
			if (key == KeyEvent.VK_UP) {
				String phrase = get(historyLog.size()-(pressedKeys+1));
				pressedKeys ++;
				CodePanel.setInput(phrase);
			}
			else if (key == KeyEvent.VK_DOWN && pressedKeys != 0) {
				String phrase = get(historyLog.size()-(pressedKeys-1));
				pressedKeys --;
				CodePanel.setInput(phrase);
						
			}
			
			else if ((key == KeyEvent.VK_UP || key == KeyEvent.VK_DOWN) && (pressedKeys < 0 || pressedKeys >= historyLog.size())) {
				// do nothing
			}
		}

}
