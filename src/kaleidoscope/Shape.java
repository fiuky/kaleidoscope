package kaleidoscope;

import java.awt.Graphics;
import java.util.Observable;
import java.util.Random;
import java.util.Timer;
import java.util.TimerTask;

/**
 * Abstract Model class for shapes to be displayed in kaleidoscope..
 * 
 * @author David Matuszek
 * @author Alex Fiuk
 * @author Matthias Chia
 */

public abstract class Shape extends Observable {
	
	abstract void draw(Graphics g);
    
	protected int xPosition;
    protected int yPosition;
    protected int xLimit, yLimit;
    protected int xDelta;
    protected int yDelta;
    protected int negX;
    protected int negY;
    protected int posX;
    protected int posY;
    private Timer timer;

    protected int timerSet;
    
    public Shape(int xPosition, int yPosition, int xDelta, int yDelta){
		this.xPosition = xPosition;
		this.yPosition = yPosition;
		this.xDelta = xDelta;
		this.yDelta = yDelta;
		timerSet = 60;
    }

    /**
     * Sets the "walls" that the ball should bounce off from.
     * 
     * @param xLimit The position (in pixels) of the wall on the right.
     * @param yLimit The position (in pixels) of the floor.
     */
    public void setLimits(int xLimit, int yLimit) {
        this.xLimit = xLimit;
        this.yLimit = yLimit;
        xPosition = Math.min(xPosition, xLimit);
        yPosition = Math.min(yPosition, yLimit);
    }

    /**
     * @return The shapes X position.
     */
    public int getX() {
        return xPosition;
    }

    /**
     * @return The shapes Y position.
     */
    public int getY() {
        return yPosition;
    }
    
    /**
     * Tells the shape to start moving. This is done by starting a Timer
     * that periodically executes a TimerTask. The TimerTask then tells
     * the shape to make one "step."
     */
    public void start() {
        timer = new Timer(true);
        timer.schedule(new Strobe(), 0, timerSet); // 25 times a second        
    }
    
    /**
     * Tells the shape to stop where it is.
     */
    public void pause() {
        timer.cancel();
        timer.schedule(new Strobe(), 0, timerSet*2); // 25 times a second
    }
    
    /**
     * Tells the shape to advance one step in the direction that it is moving.
     * If it hits a wall, its direction of movement changes.
     */
    public void makeOneStep() {
        // Do the work
        xPosition += xDelta;
        if (xPosition < 0 || xPosition >= xLimit) {
            xDelta = -xDelta;
            xPosition += xDelta;
        }
        yPosition += yDelta;
        if (yPosition < 0 || yPosition >= yLimit) {
            yDelta = -yDelta;
            yPosition += yDelta;
        }
        // Notify observers
        setChanged();
        notifyObservers();
    }
   
    /**
     * Slightly increases shape's speed
     */   
    public void speedUp() {
    	if (xDelta < 0) xDelta -= 2;
    	if (xDelta > 0) xDelta += 2;
    	if (yDelta < 0) yDelta -= 2;
    	if (yDelta > 0) yDelta += 2;
    }
    
    /**
     * Slightly decreases shape's speed
     */       
    public void slowDown() {
    	if (xDelta < 0) xDelta += 2;
    	if (xDelta > 0) xDelta -= 2;
    	if (yDelta < 0) yDelta += 2;
    	if (yDelta > 0) yDelta -= 2;
    }
    
    /**
     * Generates random integer
     */   
    public int getRandom(int min, int max){
    	Random random = new Random();
    	return random.nextInt(max) + min;
    }
    

    /**
     * Tells the model to advance one "step."
     */
    private class Strobe extends TimerTask {
        @Override
        public void run() {
            makeOneStep();
        }
    }
}
	

