import java.awt.Color;
import java.awt.Dimension;
import java.awt.BorderLayout;
import javax.swing.JPanel;
import javax.swing.JLabel;
import javax.swing.JButton;

import java.awt.GridLayout;
import javax.swing.*;

public class ButtonBar extends JToolBar {
    protected NewButton newButton = new NewButton();
    protected OpenFileButton openFileButton = new OpenFileButton();
    protected SaveFileButton saveFileButton = new SaveFileButton();
    protected LoadMapButton loadMapButton = new LoadMapButton();
    protected ExportStatButton exportStatButton = new ExportStatButton();
    protected UndoButton undoButton = new UndoButton();
    protected RedoButton redoButton = new RedoButton();
    protected SelectAllButton selectAllButton = new SelectAllButton();
    protected PlayButton playButton = new PlayButton();
    protected PauseButton pauseButton = new PauseButton();
    protected StopButton stopButton = new StopButton();
    protected SkipButton skipButton = new SkipButton();	
    protected SkipButton skipButton2 = new SkipButton();	
    protected QuickEditionButton quickEditionButton = new QuickEditionButton();	
    protected JPanel panel1 = new JPanel();
    protected GroupLayout layout = new GroupLayout(panel1);
    protected JPanel panel2 = new JPanel();
    protected GroupLayout layout2 = new GroupLayout(panel2);
    protected JPanel panel3 = new JPanel();
    protected GroupLayout layout3 = new GroupLayout(panel3);
    protected ButtonGroup layout3Group = new ButtonGroup();

    protected JPanel panel4 = new JPanel();
    protected GroupLayout layout4 = new GroupLayout(panel4);
    protected JLabel		speedLabel = new JLabel("");
    protected JLabel		backImgLabel = new JLabel("");
    protected SpeedSlider	speedSlider = new SpeedSlider(speedLabel);
    protected BackgroundScaleSlider	backgroundScaleSlider = new BackgroundScaleSlider(backImgLabel);

    ButtonBar() {
	// Set ButtonBar
	this.setPreferredSize(new Dimension(600, 100));
	this.setSize(new Dimension(600, 100));
	this.setMaximumSize(new Dimension(600, 100));
	this.setMinimumSize(new Dimension(600, 100));
	this.setBackground(new Color(200, 200, 200));
	this.setFloatable(false);
		
	// Group layout3 buttons
	layout3Group.add(playButton);
	layout3Group.add(pauseButton);
	layout3Group.add(stopButton);
	layout3Group.add(skipButton);
	layout3Group.add(quickEditionButton);

	panel4.add(speedLabel, BorderLayout.EAST);
	panel4.add(speedSlider, BorderLayout.EAST);
	panel4.add(backImgLabel, BorderLayout.EAST);
	panel4.add(backgroundScaleSlider, BorderLayout.EAST);
	
	// Set the 3 visual groups
	setFirstVisualGroup();
	setSecondVisualGroup();
	setThirdVisualGroup();
 		
	// Add the 3 groups to panel
	this.add(panel1);
	this.addSeparator();
	this.add(panel2);
	this.addSeparator();
	this.add(panel3);
	this.addSeparator();
	this.add(panel4);
    }

    public int	getSpeedValue() {
	return this.speedSlider.getValue();
    }
	
    void setFirstVisualGroup() {
	panel1.setBackground(new Color(128, 128, 128));
	panel1.setLayout(layout);		
	layout.setAutoCreateGaps(true);
	layout.setAutoCreateContainerGaps(true);
	layout.setHorizontalGroup(
				  layout.createSequentialGroup() 
				  .addComponent(newButton)
				  .addComponent(openFileButton)
				  .addComponent(saveFileButton) 
				  .addComponent(loadMapButton)
				  .addComponent(exportStatButton)
				  );
		
	layout.setVerticalGroup(
				layout.createParallelGroup(GroupLayout.Alignment.CENTER)
				.addComponent(newButton)
				.addComponent(openFileButton)
				.addComponent(saveFileButton)
				.addComponent(loadMapButton)
				.addComponent(exportStatButton)
				);
    }
		
    void setSecondVisualGroup() {
	panel2.setBackground(new Color(128, 128, 128));
	panel2.setLayout(layout2);		
	layout2.setAutoCreateGaps(true);
	layout2.setAutoCreateContainerGaps(true);
	layout2.setHorizontalGroup(
				   layout2.createSequentialGroup() 
				   .addComponent(undoButton)
				   .addComponent(redoButton)
				   .addComponent(selectAllButton));
			
	layout2.setVerticalGroup(
				 layout2.createParallelGroup(GroupLayout.Alignment.CENTER)
				 .addComponent(undoButton)
				 .addComponent(redoButton)
				 .addComponent(selectAllButton)
				 );
    }
		
    void setThirdVisualGroup() {
	panel3.setBackground(new Color(128, 128, 128));
	panel3.setLayout(layout3);		
	layout3.setAutoCreateGaps(true);
	layout3.setAutoCreateContainerGaps(true);
	layout3.setHorizontalGroup(
				   layout3.createSequentialGroup() 
				   .addComponent(playButton)
				   .addComponent(pauseButton)
				   .addComponent(stopButton)
				   .addComponent(skipButton)
				   .addComponent(quickEditionButton)
				   );
			
	layout3.setVerticalGroup(
				 layout3.createParallelGroup(GroupLayout.Alignment.CENTER)
				 .addComponent(playButton)
				 .addComponent(pauseButton)
				 .addComponent(stopButton)
				 .addComponent(skipButton)
				 .addComponent(quickEditionButton)
				 );
    }

    void setFourthVisualGroup() {
	panel4.setBackground(new Color(128, 128, 128));
	panel4.setLayout(layout4);
	layout4.setAutoCreateGaps(true);
	layout4.setAutoCreateContainerGaps(true);
	layout4.setHorizontalGroup(layout4.createSequentialGroup() 
				   .addComponent(speedLabel)
				   .addComponent(speedSlider));
			
	layout4.setVerticalGroup(layout4.createParallelGroup(GroupLayout.Alignment.CENTER)
				 .addComponent(speedLabel)
				 .addComponent(speedSlider));
    }
		
	
}

