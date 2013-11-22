import javax.swing.JFrame;
import javax.swing.JMenuBar;
import javax.swing.JMenu;

public class MenuBar extends JMenuBar {
	private FileMenu 	fileMenu = new FileMenu("File");
	private EditMenu 	editMenu = new EditMenu("Edit");
	private ToolsMenu	toolsMenu = new ToolsMenu("Tools");
	private StatMenu	statisticsMenu = new StatMenu("Statistics");
	// private ViewMenu	viewMenu = new ViewMenu("View");
	private SimMenu		simulationMenu = new SimMenu("Simulation");
	private HelpMenu	helpMenu = new HelpMenu("Help");
	
	MenuBar() {
		this.add(fileMenu);
		this.add(editMenu);
		this.add(toolsMenu);
		this.add(statisticsMenu);
		//this.add(viewMenu);
		//
		this.add(helpMenu);
	}
}
