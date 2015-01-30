package kaleidoscope;

import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ComponentAdapter;
import java.awt.event.ComponentEvent;
import java.io.IOException;
import java.util.Random;
import java.util.Timer;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;

/**
 * The Controller sets up the GUI and handles all the controls (buttons,
 * menu items, etc.)
 * 
 * @author David Matuszek
 * @author Alex Fiuk
 * @author Matthias Chia
 */

public class Controller extends JFrame {

	private static final long serialVersionUID = 1L;
	JPanel buttonPanel = new JPanel();
    JButton runButton = new JButton("Run");
    JButton stopButton = new JButton("Stop");
    JButton fasterButton = new JButton("Faster");
    JButton slowerButton = new JButton("Slower");

    Timer timer;

    Shape dave;
    Shape mark;
    Shape brad;
    Shape hao;
    Shape chris;
    Shape[] shapes;
    
    /** The View object displays what is happening in the ball. */
    View view;
    
    /**
     * Runs the kaleidoscope.
     * @param args Ignored.
     * @throws IOException 
     */
    public static void main(String[] args) throws IOException {
        Controller c = new Controller();
        c.init();
        c.display();
    }

    /**
     * Sets up communication between the components.
     * @throws IOException 
     */
    private void init() throws IOException {
        int arrIndex = 0;
        int pCount = 0;
    	int pQuant = 4; // value of pQuant corresponds to desired number of polygons
    	int bCount = 0;
    	int bQuant = 5; // value of bQuant corresponds to desired number of balls
    	
    	shapes = new Shape[pQuant+bQuant+5]; // 6 must be hard-coded based on the number of unique images to be included as shapes
    	
    	while (pCount < pQuant){ // create polygon shapes
    		shapes[arrIndex] = new Poly(this.getRandom(0, 300), this.getRandom(0, 300), this.getRandom(0, 20), this.getRandom(0, 20));
    		arrIndex++;
    		pCount++;
    	}
    	
    	while (bCount < bQuant){ // create ball shapes
    		shapes[arrIndex] = new Ball(this.getRandom(0, 300), this.getRandom(0, 300), this.getRandom(0, 20), this.getRandom(0, 20));
    		arrIndex++;
    		bCount++;
    	}
    	
    	// create face shapes
        dave = new Face(this.getRandom(0, 300), this.getRandom(0, 300), this.getRandom(8, 20), this.getRandom(8, 20), "./src/resources/dave.png");
        mark = new Face(this.getRandom(0, 300), this.getRandom(0, 300), this.getRandom(2, 20), this.getRandom(2, 20), "./src/resources/mark.png");
        brad = new Face(this.getRandom(0, 300), this.getRandom(0, 300), this.getRandom(2, 10), this.getRandom(2, 10), "./src/resources/brad.png");
        hao = new Face(this.getRandom(0, 300), this.getRandom(0, 300), this.getRandom(8, 20), this.getRandom(8, 20), "./src/resources/hao.png");
        chris = new Face(this.getRandom(0, 300), this.getRandom(0, 300), this.getRandom(0, 12), this.getRandom(0, 15), "./src/resources/chris.png");

        shapes[arrIndex] = hao;
        arrIndex++;
        shapes[arrIndex] = dave;
        arrIndex++;
        shapes[arrIndex] = chris;
        arrIndex++;
        shapes[arrIndex] = brad;
        arrIndex++;
        shapes[arrIndex] = mark;
        
        view = new View(shapes);  // The view needs to know what ball to look at
        for (Shape shape : shapes) {
        	shape.addObserver(view);
        }
    }

    /**
     * Displays the GUI.
     */
    private void display() {
        layOutComponents();
        attachListenersToComponents();
        setSize(540, 600);
        setVisible(true);
        setResizable(true);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }
    
    /**
     * Arranges the various components in the GUI.
     */
    private void layOutComponents() {
    	setLayout(new BorderLayout());
        this.add(BorderLayout.SOUTH, buttonPanel);
        buttonPanel.add(runButton);
        buttonPanel.add(stopButton);
        buttonPanel.add(fasterButton);
        buttonPanel.add(slowerButton);
        stopButton.setEnabled(false);
        this.add(BorderLayout.CENTER, view);
    }
    
    /**
     * Attaches listeners to the components, and schedules a Timer.
     */
    private void attachListenersToComponents() {
        // The Run button tells the ball to start
        runButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent event) {
                runButton.setEnabled(false);
                stopButton.setEnabled(true);
                for (Shape shape : shapes) {
                	shape.start();
                }
            }
        });
        
        // The Stop button tells the ball to pause
        stopButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent event) {
                runButton.setEnabled(true);
                stopButton.setEnabled(false);
                for (Shape shape : shapes) {
                	shape.pause();
                }
            }
        });
        
        // All shapes move slightly faster upon each click
        fasterButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent event) {
                for (Shape shape : shapes) {
                	shape.speedUp();
                }
            }
        });
        
        // All shapes move slightly slower upon each click
        slowerButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent event) {
                for (Shape shape : shapes) {
                	shape.slowDown();
                }
            }
        });
        
        
        // When the window is resized, the ball is given the new limits
        this.addComponentListener(new ComponentAdapter() {
            @Override
            public void componentResized(ComponentEvent arg0) {
                for (Shape shape : shapes) {
                	shape.setLimits(view.getWidth(), view.getHeight());
                }
            }
        });

    }
    
    // Generates random integer
    public int getRandom(int min, int max){
    	Random random = new Random();
    	return random.nextInt(max) + min;
    }

}