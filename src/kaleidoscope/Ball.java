package kaleidoscope;

import java.awt.Color;
import java.awt.Graphics;

/**
 * Balls are one type of shape that makes up the complete kaleidoscope.
 * 
 * @author David Matuszek
 * @author Alex Fiuk
 * @author Matthias Chia
 * 
 */

public class Ball extends Shape {
	Color shapeColor = new Color(this.getRandom(40, 160), this.getRandom(40, 160), this.getRandom(40, 160));
	int ballSize = this.getRandom(5, 35);
	int origSize = ballSize;
	int count = 0;
	public Ball(int xPosition, int yPosition, int xDelta, int yDelta) {
		super(xPosition, yPosition, xDelta, yDelta);
	}
		
	
	public void draw(Graphics g){
		
		// dynamic ball size
		if (count<=25) {
			count+=1;
			ballSize += 5;
		}
		
		if (count>25 && count<50) {
			count+=1;
			ballSize -= 5;
		}
		
		if (count>=50) {
			count = 0;
			ballSize = origSize;
		}
		
		// variables used for drawing
		posX = xPosition;
		posY = yPosition;
		negX = xLimit - xPosition - ballSize;
		negY = yLimit - yPosition - ballSize;
		
		// draw
		g.setColor(shapeColor);
        g.fillOval(posX, posY, ballSize, ballSize);
        g.fillOval(negX, posY, ballSize, ballSize);
        g.fillOval(posX, negY, ballSize, ballSize);
        g.fillOval(negX, negY, ballSize, ballSize);
        g.fillOval(posY, posX, ballSize, ballSize);
        g.fillOval(negY, posX, ballSize, ballSize);
        g.fillOval(posY, negX, ballSize, ballSize);
        g.fillOval(negY, negX, ballSize, ballSize);
	}
}
	