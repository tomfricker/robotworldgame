package robot.game;

import java.io.FileReader;
import java.util.Scanner;

import javax.swing.JEditorPane;
import javax.swing.JFrame;
import javax.swing.JScrollPane;
import javax.swing.text.html.HTMLEditorKit;
import javax.swing.text.html.StyleSheet;

/**
 * This class creates a new window that displays html file that is 
 * stored within the project's help pages folder. It shows the Source Code of the
 * Class that is build in Level 2.
 * 
 * @author Robot World Group
 *
 */
public class SourceCodeWindow {
			
	/**
	 * Constructor of the SourceCodeWindow class.
	 */
	public SourceCodeWindow(){
		//sets up the JFrame
		JFrame frame = new JFrame("Source Code");
		frame.setVisible(true);
		frame.setSize(740, 640);
		frame.setLocationRelativeTo(null);
		
		//sets ups EditorPane with a scroll bar		
		JEditorPane sourceCodePane = new JEditorPane();
		sourceCodePane.setEditable(false);
		sourceCodePane.setSize(740,640);
		JScrollPane scrollPane = new JScrollPane(sourceCodePane);
		frame.getContentPane().add(scrollPane);
				
		// add an html editor kit for interpreting HTML
        HTMLEditorKit htmlKit = new HTMLEditorKit();
        sourceCodePane.setEditorKit(htmlKit);
        //read the file and set it to show the top
        sourceCodePane.setText(readFile("HelpPages\\robotClass.html"));
        sourceCodePane.setCaretPosition(0);
        
        //add css to html
        StyleSheet styleSheet1 = htmlKit.getStyleSheet();
        addAllRules(styleSheet1);
        
	}
	
	/**
	 * Read a file and return it's contents
	 * @return
	 */
	String readFile(String file) {
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
	 * Add all the CSS rules to a Style Sheet
	 * (rules have been sourced from Prism CSS File: https://prismjs.com/)
	 *  
	 */
	private void addAllRules(StyleSheet styleSheet) {
		styleSheet.addRule("code {margin: 5px 10px 5px 10px; padding: 5px 10px 5px 10px; color: black; text-align: justify; font-size: 90%;}");
		styleSheet.addRule("code[class*=\"language-\"],\r\n" + 
        		"pre[class*=\"language-\"] {\r\n" + 
        		"	color: black;\r\n" + 
        		"	background: none;\r\n" + 
        		"	text-shadow: 0 1px white;\r\n" + 
        		"	font-family: Consolas, Monaco, 'Andale Mono', 'Ubuntu Mono', monospace;\r\n" + 
        		"	text-align: left;\r\n" + 
        		"	white-space: pre;\r\n" + 
        		"	word-spacing: normal;\r\n" + 
        		"	word-break: normal;\r\n" + 
        		"	word-wrap: normal;\r\n" + 
        		"	line-height: 1.5;\r\n" + 
        		"\r\n" + 
        		"	-moz-tab-size: 4;\r\n" + 
        		"	-o-tab-size: 4;\r\n" + 
        		"	tab-size: 4;\r\n" + 
        		"\r\n" + 
        		"	-webkit-hyphens: none;\r\n" + 
        		"	-moz-hyphens: none;\r\n" + 
        		"	-ms-hyphens: none;\r\n" + 
        		"	hyphens: none;\r\n" + 
        		"}");
		styleSheet.addRule("pre[class*=\"language-\"]::-moz-selection, pre[class*=\"language-\"] ::-moz-selection,\r\n" + 
				"code[class*=\"language-\"]::-moz-selection, code[class*=\"language-\"] ::-moz-selection {\r\n" + 
				"	text-shadow: none;\r\n" + 
				"	background: #b3d4fc;\r\n" + 
				"}");
		styleSheet.addRule("pre[class*=\"language-\"]::selection, pre[class*=\"language-\"] ::selection,\r\n" + 
				"code[class*=\"language-\"]::selection, code[class*=\"language-\"] ::selection {\r\n" + 
				"	text-shadow: none;\r\n" + 
				"	background: #b3d4fc;\r\n" + 
				"}");
		styleSheet.addRule("@media print {\r\n" + 
				"	code[class*=\"language-\"],\r\n" + 
				"	pre[class*=\"language-\"] {\r\n" + 
				"		text-shadow: none;\r\n" + 
				"	}\r\n" + 
				"}");
		styleSheet.addRule("pre[class*=\"language-\"] {\r\n" + 
				"	padding: 1em;\r\n" + 
				"	margin: .5em 0;\r\n" + 
				"	overflow: auto;\r\n" + 
				"}");
		styleSheet.addRule(":not(pre) > code[class*=\"language-\"],\r\n" + 
				"pre[class*=\"language-\"] {\r\n" + 
				"	background: #f5f2f0;\r\n" + 
				"}");
		styleSheet.addRule(":not(pre) > code[class*=\"language-\"] {\r\n" + 
				"	padding: .1em;\r\n" + 
				"	border-radius: .3em;\r\n" + 
				"	white-space: normal;\r\n" + 
				"}");
		styleSheet.addRule(".token.comment,\r\n" + 
				".token.prolog,\r\n" + 
				".token.doctype,\r\n" + 
				".token.cdata {\r\n" + 
				"	color: slategray;\r\n" + 
				"}");
		styleSheet.addRule(".token.punctuation {\r\n" + 
				"	color: #999;\r\n" + 
				"}");
		styleSheet.addRule(".namespace {\r\n" + 
				"	opacity: .7;\r\n" + 
				"}");
		styleSheet.addRule(".token.property,\r\n" + 
				".token.tag,\r\n" + 
				".token.boolean,\r\n" + 
				".token.number,\r\n" + 
				".token.constant,\r\n" + 
				".token.symbol,\r\n" + 
				".token.deleted {\r\n" + 
				"	color: #905;\r\n" + 
				"}");
		styleSheet.addRule(".token.selector,\r\n" + 
				".token.attr-name,\r\n" + 
				".token.string,\r\n" + 
				".token.char,\r\n" + 
				".token.builtin,\r\n" + 
				".token.inserted {\r\n" + 
				"	color: #690;\r\n" + 
				"}");
		styleSheet.addRule(".token.operator,\r\n" + 
				".token.entity,\r\n" + 
				".token.url,\r\n" + 
				".language-css .token.string,\r\n" + 
				".style .token.string {\r\n" + 
				"	color: #9a6e3a;\r\n" + 
				"	background: hsla(0, 0%, 100%, .5);\r\n" + 
				"}");
		styleSheet.addRule(".token.atrule,\r\n" + 
				".token.attr-value,\r\n" + 
				".token.keyword {\r\n" + 
				"	color: #07a;\r\n" + 
				"}");
		styleSheet.addRule(".token.function,\r\n" + 
				".token.class-name {\r\n" + 
				"	color: #DD4A68;\r\n" + 
				"}");
		styleSheet.addRule(".token.regex,\r\n" + 
				".token.important,\r\n" + 
				".token.variable {\r\n" + 
				"	color: #e90;\r\n" + 
				"}");
		styleSheet.addRule(".token.important,\r\n" + 
				".token.bold {\r\n" + 
				"	font-weight: bold;\r\n" + 
				"}");
		styleSheet.addRule(".token.italic {\r\n" + 
				"	font-style: italic;\r\n" + 
				"}");
		styleSheet.addRule(".token.entity {\r\n" + 
				"	cursor: help;\r\n" + 
				"}");
	}

}
