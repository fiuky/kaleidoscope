package kaleidoscope;

import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;

/**
 * Faces are considered a type of shape that is displayed in the kaleidoscope.
 * We decided to have some fun with this project and include our TAs' and professor's
 * faces.  They hauntingly drift across your screen as they gaze into your soul.
 * 
 * @author David Matuszek
 * @author Alex Fiuk
 * @author Matthias Chia
 */

public class Face extends Shape {
	BufferedImage face;
	
	public Face(int xPosition, int yPosition, int xDelta, int yDelta, String fileName) throws IOException{
		super(xPosition, yPosition, xDelta, yDelta);
		face = ImageIO.read(new File(fileName));
	}
		
	
	public void draw(Graphics g){
		posX = xPosition;
		posY = yPosition;
		negX = xLimit - xPosition - face.getWidth();
		negY = yLimit - yPosition - face.getHeight();
		
        g.drawImage(face, posX, posY, null);
        g.drawImage(face, posX, negY, null);
        g.drawImage(face, negX, posY, null);
        g.drawImage(face, negX, negY, null);
     
        g.drawImage(face, posY, posX, null);
        g.drawImage(face, negY, posX, null);
        g.drawImage(face, posY, negX, null);
        g.drawImage(face, negY, negX, null);

	}
}
	
	



