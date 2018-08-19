package robot.game;

import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.util.ArrayList;

/**
 *  
 * This class stores every input that the user puts 
 * in the codeInput in order to access it later through
 * the scroll DOWN and UP arrow keys.
 * 
 * It extends and acts as a KeyAdapter which allows it 
 * to read key input from the user and do an action accordingly.
 *
 * @author Robot World Group
 *
 */
public class HistoryLog extends KeyAdapter {
	
		//List of commands which have been entered by user
		static ArrayList<String> historyLog = new ArrayList<String>();
		//variable to store how many times the UP and DOWN keys were pressed
		private int pressedKeys = 0;
		
		/**
		 * Constructor for the History Log
		 */
		public HistoryLog() {
						
    	}
		
		/**
		 * Adds the specified command to the history log
		 * @param command
		 */
		public static void addToCommandList(String command) {
			historyLog.add(command);
		}
		
		/**
		 * Returns the i-th element of the history log list
		 * @param i index number
		 * @return i-th element of HL
		 */
		public static String get(int i) {
			return historyLog.get(i);
			
		}
		
		/**
		 * Print all elements of the history log list (for testing)
		 * 
		 */
		public static void printHistory() {
			for (String log : historyLog) {
				System.out.println(log);
			}
		}
		
		/**
		 * This method allows the user to scroll through the history log
		 * with the UP and DOWN arrow keys when they are on the CodePanel
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
