package ca.utoronto.utm.paint;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.IOException;

// https://docs.oracle.com/javase/8/docs/api/java/awt/Graphics2D.html
// https://docs.oracle.com/javase/tutorial/2d/
/**
 * A ShapeChooserPanel is a JPanel that contains buttons representing different shapes.
 * Each button has an icon of the shape that it represents.
 * Users are able to switch to drawing a differnt shape by clicking on one of the buttons.
 * 
 */
class ShapeChooserPanel extends JPanel implements ActionListener {
	private View view; // So we can talk to our parent or other components of the view
	
	/**
	 * Creates a new ShaperChooserPanel
	 * @param view the view that contains the panel where the user will draw
	 */
	public ShapeChooserPanel(View view) {	
		this.view=view;
		
		//Create the buttons
		String[] buttonLabels = { "Circle", "Rectangle", "Square", "Squiggle", "Polyline" };
		this.setLayout(new GridLayout(buttonLabels.length+3, 1)); //+3 to allow for colour select button ,fill button, and Line thickness combobox
		ButtonGroup shapeButtonGroup = new ButtonGroup();
		JToggleButton b1 = new JToggleButton();
	  	b1.setToolTipText("Circle");
		JToggleButton b2 = new JToggleButton();
	  	b2.setToolTipText("Rectangle");
	  	JToggleButton b3 = new JToggleButton();
	  	b3.setToolTipText("Square");
	  	JToggleButton b4 = new JToggleButton();
	  	b4.setToolTipText("Squiggle");
	  	JToggleButton b5 = new JToggleButton();
	  	b5.setToolTipText("Polyline");
	  	JToggleButton [] buttons = {b1,b2,b3,b4,b5};
	  	
	  	//Sets icons for the buttons
	 	try {
	    	BufferedImage img = ImageIO.read(getClass().getResource("circleicon.png"));
	    	b1.setIcon(new ImageIcon(img));
	    	img = ImageIO.read(getClass().getResource("rectangleicon.png"));
	   		b2.setIcon(new ImageIcon(img));
	    	img = ImageIO.read(getClass().getResource("squareicon.png"));
	   	 	b3.setIcon(new ImageIcon(img));
	    	img = ImageIO.read(getClass().getResource("scribbleicon.png"));
	    	b4.setIcon(new ImageIcon(img));
	    	img = ImageIO.read(getClass().getResource("polylineicon.png"));
	    	b5.setIcon(new ImageIcon(img));
	 	} catch (IOException ex) {
	  	}
	 	//Add actionListener to each button and set the initial Shape
	 	// that could be drawn to Squiggle
		for (JToggleButton b : buttons){
			shapeButtonGroup.add(b);
			this.add(b);
			b.addActionListener(this);
			b.setBackground(Color.WHITE);
			b.setPreferredSize(new Dimension(60, 60));
			b.setBorder(null);
			if (b.getToolTipText().equals("Squiggle")) {
				b.setSelected(true);
			}
		}
	}
	
	/**
	 * Controller aspect of ShapeChooserPanel
	 */
	@Override
	public void actionPerformed(ActionEvent e) {
		this.view.getPaintPanel().setMode(((JToggleButton) e.getSource()).getToolTipText());
		System.out.println(((JToggleButton) e.getSource()).getToolTipText());
	}

	
}
