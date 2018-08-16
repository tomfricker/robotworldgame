package robot.game;

import java.awt.BorderLayout;
import java.awt.Container;
import java.awt.Dimension;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JSplitPane;
import javax.swing.JTextArea;
import javax.swing.JTree;
import javax.swing.event.TreeSelectionEvent;
import javax.swing.event.TreeSelectionListener;
import javax.swing.tree.DefaultMutableTreeNode;

public class HelpWindow {
	
	public HelpWindow() {
		JFrame frame = new JFrame("Help");
		
		Container contentPane = frame.getContentPane();
		contentPane.setLayout(new BorderLayout());
		contentPane.setPreferredSize(new Dimension(640, 480));
		
		JPanel contents = new JPanel();
		JTextArea helpPane = new JTextArea();
		
		DefaultMutableTreeNode top = new DefaultMutableTreeNode("Help");
		createNodes(top);
		JTree tree = new JTree(top);
		
		 // listen for when the selection changes.
	    tree.addTreeSelectionListener(new TreeSelectionListener() {
	        public void valueChanged(TreeSelectionEvent e) {
	          DefaultMutableTreeNode node = (DefaultMutableTreeNode) tree.getLastSelectedPathComponent();

	          if (node == null)
	            return;

	          Object nodeInfo = node.getUserObject();

	          //put the information from the help item to the help pane
	          if (node.isLeaf()) {
	            String help = (String)nodeInfo;
	            helpPane.setText(getHelp(help));
	          }
	        }

	        /**
	         * Return the correct information for the item selected
	         * @param help
	         * @return
	         */
			private String getHelp(String help) {
				switch(help) {
					case "Controls": return "Use the arrow keys to move the robot";
					case "Buttons": return "The Main Menu button takes you back to the level selector.\n\n"
							+ "The Run Code button executes all the code in the right hand panel.\n\n"
							+ "When on the Code Panel, you can scroll with the UP and DOWN arrow keys to navigate "
							+ "through the commands you've written.";
					case "Characters": return "There are different characters to interact with, try them all!";
					case "1": return "Here we learn about data types and how to type them out in Java.";
				}
				return null;
			}
	      });
		
		contents.add(tree);		

		JSplitPane splitPane = new JSplitPane();
		
		splitPane.setTopComponent(tree);
		splitPane.setBottomComponent(helpPane);
		splitPane.setDividerLocation(180);
	    splitPane.setPreferredSize(new Dimension(630, 350));
		
		contentPane.add(splitPane);
				
		frame.pack();
		frame.setLocationRelativeTo(null);
		frame.setVisible(true);
	}
	
	private void createNodes(DefaultMutableTreeNode top) {
	    DefaultMutableTreeNode category = null;
	    DefaultMutableTreeNode info = null;
	    
	    category = new DefaultMutableTreeNode("General");
	    top.add(category);
	    
	    info = new DefaultMutableTreeNode("Controls");
	    category.add(info);
	    
	    info = new DefaultMutableTreeNode("Buttons");
	    category.add(info);
	    
	    info = new DefaultMutableTreeNode("Characters");
	    category.add(info);

	    

	    category = new DefaultMutableTreeNode("Levels");
	    top.add(category);

	    info = new DefaultMutableTreeNode("1");
	    category.add(info);

	    info = new DefaultMutableTreeNode("2");
	    category.add(info);
	    
	    info = new DefaultMutableTreeNode("3");
	    category.add(info);
	    
	    info = new DefaultMutableTreeNode("4");
	    category.add(info);
	    
	    info = new DefaultMutableTreeNode("5");
	    category.add(info);
	}

}
