package kaleidoscope;

import java.awt.Color;
import java.awt.Graphics;
import java.util.Observable;
import java.util.Observer;

import javax.swing.JPanel;

/**
 * View class to observe and display what is going on in the shape classes.
 * 
 * @author David Matuszek
 * @author Alex Fiuk
 * @author Matthias Chia
 */

public class View extends JPanel implements Observer {

	private static final long serialVersionUID = 1L;
	Shape[] shapes;
	int r = 167;
	int g = 199;
	int b = 99;
	Color baColor= new Color(r, g, b);
	int count = 0;

    /**
     * Constructor.
     * @param model The Model whose working is to be displayed.
     */
    View(Shape[] shapes) {
    	this.shapes = shapes;
    }
    
    
    /**
     * Displays what is going on in the Model. Note: This method should
     * NEVER be called directly; call repaint() instead.
     * 
     * @param g The Graphics on which to paint things.
     * @see javax.swing.JComponent#paint(java.awt.Graphics)
     */
    @Override
    public void paint(Graphics g) {
        g.setColor(baColor);
        g.fillRect(0, 0, getWidth(), getHeight());
        for (Shape shape : shapes) {
        	shape.draw(g);
        }
    }

    
    /**
     * When an Observer notifies Observers (and this View is an Observer),
     * this is the method that gets called.
     * 
     * @param obs Holds a reference to the object being observed.
     * @param arg If notifyObservers is given a parameter, it is received here.
     * @see java.util.Observer#update(java.util.Observable, java.lang.Object)
     */
    @Override
    public void update(Observable obs, Object arg) {
    	// create varying background color
    	if (count<=100){
    		r = r-1;
    		count++;
    	}
    	
    	if (count > 100 && count <= 200) {
    		r = r + 1;
    		count++;
    	}
    	
    	if (count > 200) {
    		count = 0;
    		r = 167;
    	}
    	
    	baColor = new Color(r, g, b);
        repaint();
    }
}