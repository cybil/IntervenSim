import java.util.ArrayList;

import javax.imageio.ImageIO;
import javax.swing.JFrame;

import javax.swing.filechooser.*;

import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.GridLayout;
import java.awt.BorderLayout;

import javax.swing.JPanel;
import javax.swing.Timer;

import java.awt.event.*;
import javax.swing.*;

import javax.swing.JToolBar;

import java.awt.Dimension;
import java.io.File;
import java.io.IOException;

import javax.swing.JButton;

import java.awt.Image;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

public class MainWindow extends JFrame implements ActionListener {
    public JScrollPane			scrollPane = null;
    private boolean				inst = false;
    static Controller				controller;
    private JPanel				content = new JPanel();
    private MenuBar				menuBar = new MenuBar();
    private ButtonBar			buttonBar = new ButtonBar();
    private TabsPanel			tabsPanel = new TabsPanel();
    private ToolsBar			toolsBar = new ToolsBar();
    private MapPanel			mapPanel;
    private GridBagConstraints	gbc = new GridBagConstraints();
    private Map					map;
    private Timer				timer = new Timer(10, this);
    private JToolBar			tabToolBar = new JToolBar();
    private SeeTabButton		seeTabButton = new SeeTabButton();


    static void		setAnimationSpeed(int speed) {
	controller.eventSetSpeed(speed);
    }

    public MainWindow(Controller controller) throws NombreInstanceGUIException{
	if (inst == true)
	    throw new NombreInstanceGUIException("Il existe deja une instance de la classe mainWindow.");
	inst = true;
	configureJFrame();
	this.controller = controller;
	this.mapPanel = new MapPanel(this.controller);
	this.map = this.controller._model.getMap();
	setPanels();
	listenToolBarButtons();
	listenTabButton();
	listenButtonBar();
	tabToolBar.setSize(new Dimension(25, 50));
	tabToolBar.setPreferredSize(new Dimension(25, 50));
	tabToolBar.setMaximumSize(new Dimension(25, 50));
	tabToolBar.setMinimumSize(new Dimension(25, 50));
	tabToolBar.setFloatable(false);
	tabToolBar.setLayout(new BorderLayout());
	tabToolBar.add(seeTabButton, BorderLayout.NORTH);
	tabToolBar.setVisible(false);
	this.timer.start();

    }

    // action Performed class
    public void actionPerformed(ActionEvent event) {
	this.mapPanel.displayMap(this.map.getFormatMap());
    }

    //Configure all JFrame attributs
    void configureJFrame() {
     	this.setTitle("IntervenSim by Teks");
    	this.setExtendedState(JFrame.MAXIMIZED_BOTH);
    	this.setLocationRelativeTo(null);
    	this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    	this.setVisible(true);
    	this.setJMenuBar(menuBar);
    	content.setLayout(new GridBagLayout());
    }

    //set all main panels
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

	scrollPane = new JScrollPane(mapPanel);
	scrollPane.setWheelScrollingEnabled(false);
	mapPanel.setScrollBarRef(scrollPane);
	scrollPane.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_ALWAYS);
	scrollPane.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);

	content.add(scrollPane, gbc);

    	// content.add(mapPanel, gbc);

    	gbc.gridx = 0;
    	gbc.gridy = 2;
    	gbc.weightx = 1;
    	gbc.weighty = 100;
    	gbc.gridheight = GridBagConstraints.REMAINDER; 	// dernier de la colonne
    	gbc.gridwidth = GridBagConstraints.REMAINDER; 	// et de la ligne
    	gbc.fill = GridBagConstraints.VERTICAL;
    	gbc.anchor = GridBagConstraints.FIRST_LINE_START;
    	content.add(tabsPanel, gbc);

    	gbc.gridx = 0;
    	gbc.gridy = 2;
    	gbc.weightx = 1;
    	gbc.weighty = 1000;
    	gbc.gridheight = GridBagConstraints.REMAINDER; 	// dernier de la colonne
    	gbc.gridwidth = GridBagConstraints.REMAINDER; 	// et de la ligne
    	gbc.fill = GridBagConstraints.VERTICAL;
    	gbc.anchor = GridBagConstraints.FIRST_LINE_START;
    	content.add(tabToolBar, gbc);

    	this.setContentPane(content);
    	this.setVisible(true);
    }

    public void editObject(NodeGraphic Object) {
    	//	if (Object.type == MapPanel.EObjectTools.VEHICULE) {
    	//		tabsPanel.editTab.showPanel(0, Object);
    	//	} else {
    	//		tabsPanel.editTab.showPanel(1, Object);
    	//	}
    }

    //Listen to Tool Bar Buttons
    void listenToolBarButtons() {
    	this.toolsBar.selectButton.addActionListener(new SelectButtonListener());
    	this.toolsBar.vehiculeButton.addActionListener(new VehiculeButtonListener());
    	this.toolsBar.roadButton.addActionListener(new RoadButtonListener());
    	this.toolsBar.nodeButton.addActionListener(new NodeButtonListener());
    }

    void listenTabButton() {
    	this.tabsPanel.hideButton.addActionListener(new HideButtonListener());
      	this.seeTabButton.addActionListener(new SeeTabButtonListener());
    }

    void listenButtonBar() {
	this.buttonBar.newButton.addActionListener(new NewButtonListener());
	this.buttonBar.openFileButton.addActionListener(new OpenFileButtonListener());
	this.buttonBar.saveFileButton.addActionListener(new SaveFileButtonListener());
	this.buttonBar.loadMapButton.addActionListener(new LoadMapButtonListener());
	this.buttonBar.exportStatButton.addActionListener(new ExportStatButtonListener());
	this.buttonBar.undoButton.addActionListener(new UndoButtonListener());
	this.buttonBar.redoButton.addActionListener(new RedoButtonListener());
    	this.buttonBar.selectAllButton.addActionListener(new SelectAllButtonListener());
	this.buttonBar.playButton.addActionListener(new PlayButtonListener());
	this.buttonBar.pauseButton.addActionListener(new PauseButtonListener());
	this.buttonBar.stopButton.addActionListener(new StopButtonListener());
	this.buttonBar.skipButton.addActionListener(new SkipButtonListener());
    }

    // ActionListener class for ToolsBar Buttons
    class SelectButtonListener implements ActionListener{
        public void actionPerformed(ActionEvent arg0) {
	    System.out.print("Vous avez clique sur le bouton de Selection\n");
	    mapPanel.setSelectedObject(MapPanel.EObjectTools.CURSOR);
	    toolsBar.deselectButton(MapPanel.EObjectTools.CURSOR);
        }
    }

    class VehiculeButtonListener implements ActionListener{
        //Redefinition de la methode actionPerformed()
        public void actionPerformed(ActionEvent arg0) {
	    System.out.print("Vous avez clique sur le bouton Vehicule\n");
	    mapPanel.setSelectedObject(MapPanel.EObjectTools.VEHICULE);
	    tabsPanel.editTab.showPanel(0);
	    toolsBar.deselectButton(MapPanel.EObjectTools.VEHICULE);
	}
    }

    class RoadButtonListener implements ActionListener{
        //Redefinition de la methode actionPerformed()
        public void actionPerformed(ActionEvent arg0) {
	    System.out.print("Vous avez clique sur le bouton Road\n");
	    mapPanel.setSelectedObject(MapPanel.EObjectTools.ROAD);
	    tabsPanel.editTab.showPanel(2);
	    toolsBar.deselectButton(MapPanel.EObjectTools.ROAD);
	}
    }

    class NodeButtonListener implements ActionListener{
        //Redefinition de la methode actionPerformed()
        public void actionPerformed(ActionEvent arg0) {
	    System.out.print("Vous avez clique sur le bouton Node\n");
	    mapPanel.setSelectedObject(MapPanel.EObjectTools.NODE);
	    tabsPanel.editTab.showPanel(1);
	    toolsBar.deselectButton(MapPanel.EObjectTools.NODE);
	}
    }

    // ActionListener class for TabsPanel
    class HideButtonListener implements ActionListener{
        public void actionPerformed(ActionEvent arg0) {
	    System.out.print("clic hideButton\n");
	    tabsPanel.setVisible(false);
	    tabToolBar.setVisible(true);
	}
    }

    class SeeTabButtonListener implements ActionListener{
        public void actionPerformed(ActionEvent arg0) {
	    System.out.print("clic seeTabButton\n");
	    tabToolBar.setVisible(false);
	    tabsPanel.setVisible(true);
	}
    }

    // ActionListener class for ButtonBar
    class ExportStatButtonListener implements ActionListener{
	public void actionPerformed(ActionEvent arg0) {
	    JFileChooser chooser = new JFileChooser();

	    // FileFilter filter = new FileFilter();
	    // filter.addExtension("jpg");
	    // filter.addExtension("gif");
	    // filter.setDescription("JPG & GIF Images");
	    // chooser.setFileFilter(filter);
	    int returnVal = chooser.showOpenDialog(null);
	    if (returnVal == JFileChooser.APPROVE_OPTION) {
	    	System.out.println("You chose to open this file: " +
	    			   chooser.getSelectedFile().getAbsolutePath());
		controller.eventSaveStat(new File(chooser.getSelectedFile().getAbsolutePath()));
	    }
	}
    }


    class NewButtonListener implements ActionListener{
        public void actionPerformed(ActionEvent arg0) {
	    controller.eventNewFile();
        }
    }

    class OpenFileButtonListener implements ActionListener{
        public void actionPerformed(ActionEvent arg0) {

	    JFileChooser chooser = new JFileChooser();

	    // FileFilter filter = new FileFilter();
	    // filter.addExtension("jpg");
	    // filter.addExtension("gif");
	    // filter.setDescription("JPG & GIF Images");
	    // chooser.setFileFilter(filter);
	    int returnVal = chooser.showOpenDialog(null);
	    if (returnVal == JFileChooser.APPROVE_OPTION) {
	    	System.out.println("You chose to open this file: " +
	    			   chooser.getSelectedFile().getAbsolutePath());
		if (controller.eventLoadMap(new File(chooser.getSelectedFile().getAbsolutePath())) == false)
		    JOptionPane.showMessageDialog(null, "File not found: " +
						  chooser.getSelectedFile().getName(), "Error", 0);
	    }	    
        }
    }

    class SaveFileButtonListener implements ActionListener{
        public void actionPerformed(ActionEvent arg0) {
	    JFileChooser chooser = new JFileChooser();

	    // FileFilter filter = new FileFilter();
	    // filter.addExtension("jpg");
	    // filter.addExtension("gif");
	    // filter.setDescription("JPG & GIF Images");
	    // chooser.setFileFilter(filter);
	    int returnVal = chooser.showOpenDialog(null);
	    if (returnVal == JFileChooser.APPROVE_OPTION) {
	    	System.out.println("You chose to open this file: " +
	    			   chooser.getSelectedFile().getAbsolutePath());
		controller.eventSaveMap(new File(chooser.getSelectedFile().getAbsolutePath()));
	    }
        }
    }

    class LoadMapButtonListener implements ActionListener{
        public void actionPerformed(ActionEvent arg0) {
	    // Recuperer fichier !
	    JFileChooser chooser = new JFileChooser();

	    // FileFilter filter = new FileFilter();
	    // filter.addExtension("jpg");
	    // filter.addExtension("gif");
	    // filter.setDescription("JPG & GIF Images");
	    // chooser.setFileFilter(filter);
	    int returnVal = chooser.showOpenDialog(null);
	    if (returnVal == JFileChooser.APPROVE_OPTION) {
	    	System.out.println("You chose to import this file: " +
	    			   chooser.getSelectedFile().getAbsolutePath());
		if (controller.eventImportImage(new File(chooser.getSelectedFile().getAbsolutePath())) == false)
		    JOptionPane.showMessageDialog(null, "File not found: " +
						  chooser.getSelectedFile().getAbsolutePath(), "Error", 0);
	    }
	    // controller.eventLoadMap();
        }
    }

    class UndoButtonListener implements ActionListener{
        public void actionPerformed(ActionEvent arg0) {
	    System.out.println("------> UNDO <------");
	    controller.eventUndo();
        }
    }

    class RedoButtonListener implements ActionListener{
        public void actionPerformed(ActionEvent arg0) {
	    System.out.println("------> REDO <------");
	    controller.eventRedo();
        }
    }

    class SelectAllButtonListener implements ActionListener{
        public void actionPerformed(ActionEvent arg0) {
	    System.out.println("------> SELECT ALL <------");
	    mapPanel.selectAll();
        }
    }

    class PlayButtonListener implements ActionListener{
        public void actionPerformed(ActionEvent arg0) {
	    System.out.println("------> PLAY <------");
	    if (controller.eventGetVehicule() == false)
		JOptionPane.showMessageDialog(null, "There is no vehicule on the map.",
					      "Information", 0);
	    // if (controller.eventGetPathOK() == false)
	    // 	JOptionPane.showMessageDialog(null, "There is no way to go to an urgency.");
	    else
	    {
		controller.eventPlay();
		MapPanel.running = true;
	    }
        }
    }

    class PauseButtonListener implements ActionListener{
        public void actionPerformed(ActionEvent arg0) {
	    System.out.println("------> PAUSE <------");
	    controller.eventPause();
        }
    }

    class StopButtonListener implements ActionListener{
        public void actionPerformed(ActionEvent arg0) {
	    System.out.println("------> STOP <------");
	    MapPanel.running = false;
	    controller.eventStop();
        }
    }

    class SkipButtonListener implements ActionListener{
        public void actionPerformed(ActionEvent arg0) {
	    System.out.println("------> GO TO STAT <------");
	    controller.eventGoToStat();
        }
    }

}
