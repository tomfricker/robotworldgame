package robot.game;

import java.awt.BorderLayout;
import java.awt.Container;
import java.awt.Dimension;
import java.io.FileReader;
import java.util.Scanner;

import javax.swing.JEditorPane;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JSplitPane;
import javax.swing.JTree;
import javax.swing.event.TreeSelectionEvent;
import javax.swing.event.TreeSelectionListener;
import javax.swing.text.html.HTMLEditorKit;
import javax.swing.text.html.StyleSheet;
import javax.swing.tree.DefaultMutableTreeNode;

/**
 * This class creates a new window that displays html files that are
 * stored within the project's help pages folder. There is a selectable
 * tree to open new pages, within a split pane window.
 * 
 * @author Robot World Group
 *
 */
public class HelpWindow {
	
	/**
	 * Constructor of the HelpWindow class.
	 */
	public HelpWindow() {
		JFrame frame = new JFrame("Help");
		
		Container contentPane = frame.getContentPane();
		contentPane.setLayout(new BorderLayout());
		contentPane.setPreferredSize(new Dimension(640, 480));
		
		JPanel contents = new JPanel();
		JEditorPane helpPane = new JEditorPane();
		helpPane.setEditable(false);
		JScrollPane scrollPane = new JScrollPane(helpPane);
		
		// add an html editor kit for interpreting HTML
        HTMLEditorKit kit = new HTMLEditorKit();
        helpPane.setEditorKit(kit);
        
        //add css to html
        StyleSheet styleSheet = kit.getStyleSheet();
        styleSheet.addRule("h1 {margin: 5px 10px 5px 10px; padding: 5px 10px 5px 10px; color: blue; text-align: center; font-size: 180%;}");
        styleSheet.addRule("h2 {margin: 5px 10px 5px 10px; padding: 5px 10px 5px 10px; color: red; font-size: 140%;}");
        styleSheet.addRule("p, pre {margin: 5px 10px 5px 10px; padding: 5px 10px 5px 10px; color: black; text-align: justify; font-size: 120%;}");
        styleSheet.addRule("ul {list-style-type: square; font-size: 105%;}");
        styleSheet.addRule("ul li {padding: 8px 16px;}");
		
        //create the options for the JTree selection menu
		DefaultMutableTreeNode top = new DefaultMutableTreeNode("Help");
		createNodes(top);
		JTree tree = new JTree(top);
		tree.setRootVisible(false);
		for (int i = 0; i < tree.getRowCount(); i++) {
		    tree.expandRow(i);
		}
		
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
	            //sets scroll bar to top of page
	            helpPane.setCaretPosition(0);
	          }
	        }

	        /**
	         * Return the correct file location for the html page to load.
	         * @param help
	         * @return
	         */
			private String getHelp(String help) {
				switch(help) {
					case "Controls": return readFile("HelpPages\\controls.html");
					case "Buttons": return readFile("HelpPages\\buttons.html");
					case "Characters": return readFile("HelpPages\\characters.html");
					case "Input": return readFile("HelpPages\\input.html");
					case "1 - Data Types": return readFile("HelpPages\\datatypes.html");
					case "2 - Classes and Objects": return readFile("HelpPages\\classes&objects.html");
					case "4 - Loops and Methods": return readFile("HelpPages\\loops.html");
				}
				return null;
			}
	      });
		
		contents.add(tree);		

		JSplitPane splitPane = new JSplitPane();
		
		splitPane.setTopComponent(tree);
		splitPane.setBottomComponent(scrollPane);
		splitPane.setDividerLocation(210);
	    splitPane.setPreferredSize(new Dimension(630, 350));
		
		contentPane.add(splitPane);
				
		frame.pack();
		frame.setLocationRelativeTo(null);
		frame.setVisible(true);
	}
	
	/**
	 * Read a file and return it's contents
	 * @return
	 */
	private String readFile(String file) {
		StringBuilder contents = new StringBuilder("");
		
		try {
			Scanner scanner = new Scanner(new FileReader(file));
			
			while(scanner.hasNextLine()) {
				contents.append(scanner.nextLine());
			}
		}
		catch(Exception e) {
			e.printStackTrace();	
		}
		
		String helpContents = contents.toString();
		
		return helpContents;
	}
	
	/**
	 * Creates the nodes for the JTree selection menu of the help window.
	 * @param top
	 */
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
	    
	    info = new DefaultMutableTreeNode("Input");
	    category.add(info);

	    

	    category = new DefaultMutableTreeNode("Levels");
	    top.add(category);

	    info = new DefaultMutableTreeNode("1 - Data Types");
	    category.add(info);

	    info = new DefaultMutableTreeNode("2 - Classes and Objects");
	    category.add(info);
	    
	    info = new DefaultMutableTreeNode("3 - Collections");
	    category.add(info);
	    
	    info = new DefaultMutableTreeNode("4 - Loops and Methods");
	    category.add(info);
	    
	    info = new DefaultMutableTreeNode("5 - Conditional Statements");
	    category.add(info);
	}

}
