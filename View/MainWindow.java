import java.util.ArrayList;
import javax.swing.JFrame;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import javax.swing.JPanel;
import javax.swing.Timer;
import java.awt.event.*;

public class MainWindow extends JFrame implements ActionListener {
    private static boolean		inst = false;
    private Controller			controller;
    private static JPanel		content = new JPanel();
    private static MenuBar		menuBar = new MenuBar();
    private static ButtonBar		buttonBar = new ButtonBar();
    private static TabsPanel		tabsPanel = new TabsPanel();
    private static ToolsBar		toolsBar = new ToolsBar();
    private static MapPanel		mapPanel;
    private static GridBagConstraints	gbc = new GridBagConstraints();
    private Map				map;

    private Timer			timer = new Timer(10, this);

    public MainWindow(Controller controller) throws NombreInstanceGUIException{
	if (inst == true)
	    throw new NombreInstanceGUIException("Il existe deja une instance de la classe mainWindow.");
	inst = true;
   	this.setTitle("IntervenSim by Teks");
	this.setExtendedState(JFrame.MAXIMIZED_BOTH);
	this.setLocationRelativeTo(null);
	this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	this.setVisible(true);
	this.setJMenuBar(menuBar);
	this.controller = controller;
	this.mapPanel = new MapPanel(this.controller);
	this.map = this.controller._model.getMap();
		
	content.setLayout(new GridBagLayout());
	setPanels();
	toolsBar.selectButton.addActionListener(new SelectButtonListener());
	this.timer.start();
    }

    public void actionPerformed(ActionEvent event) {
	this.mapPanel.displayMap(this.map.getFormatMap());
    }
    
    private void setPanels() {
    	gbc.gridx = 0;
    	gbc.gridy = 0;
    	gbc.gridwidth = GridBagConstraints.REMAINDER;	//Dernier de la ligne
    	gbc.gridheight = 1;
    	gbc.weightx = 1;
    	gbc.weighty = 1;
    	gbc.fill = GridBagConstraints.HORIZONTAL;
    	gbc.anchor = GridBagConstraints.FIRST_LINE_START;
    	content.add(buttonBar, gbc);
    	    	    
    	gbc.gridx = 0;
    	gbc.gridy = GridBagConstraints.RELATIVE;
    	gbc.weightx = 1;
    	gbc.weighty = 100;
    	gbc.gridheight = GridBagConstraints.RELATIVE;	//Avant dernier colonne
    	gbc.gridwidth = GridBagConstraints.RELATIVE;	//Avant dernier ligne
    	gbc.fill = GridBagConstraints.NONE;
    	gbc.anchor = GridBagConstraints.FIRST_LINE_START;
    	content.add(toolsBar, gbc);
    	    
    	gbc.gridx = 1;
    	gbc.gridy = 1;
    	gbc.weightx = 3.4;
    	gbc.weighty = 1;
    	gbc.gridheight = GridBagConstraints.REMAINDER; 	// Dernier de la colonne
    	gbc.gridwidth = GridBagConstraints.REMAINDER; 	// et de la ligne
    	gbc.anchor = GridBagConstraints.FIRST_LINE_START;
    	gbc.fill = GridBagConstraints.BOTH;
    	content.add(mapPanel, gbc);
    	   
    	gbc.gridx = 0;
    	gbc.gridy = 3;
    	gbc.weightx = 1;
    	gbc.weighty = 100;
    	gbc.gridheight = GridBagConstraints.REMAINDER; 	// dernier de la colonne
    	gbc.gridwidth = GridBagConstraints.REMAINDER; 	// et de la ligne
    	gbc.fill = GridBagConstraints.VERTICAL;
    	gbc.anchor = GridBagConstraints.FIRST_LINE_START;
    	content.add(tabsPanel, gbc);
    	    
    	this.setContentPane(content);
    	this.setVisible(true);
    }
    
    class SelectButtonListener implements ActionListener{
        //Redefinition de la methode actionPerformed()
        public void actionPerformed(ActionEvent arg0) {
          System.out.print("Vous avez clique sur le bouton de Selection");        
        }
      }
}
