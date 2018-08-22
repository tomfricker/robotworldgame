package robot.game;

import javax.swing.JDialog;
import javax.swing.JTextArea;
import javax.swing.ScrollPaneLayout;

public class SourceCodeWindow {
	
	public SourceCodeWindow(){
		JDialog dialog = new JDialog();
		dialog.setVisible(true);
		dialog.setModal(true);
		ScrollPaneLayout scrollPane = new ScrollPaneLayout();
		dialog.setLayout(scrollPane);
		String robotCode = "hello world";
		scrollPane.addLayoutComponent(null, new JTextArea(robotCode));
	}

}
