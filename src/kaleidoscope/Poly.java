package kaleidoscope;

import java.awt.Color;
import java.awt.Graphics;

/**
 * Used to create polygons to be displayed in the kaleidoscope.  
 * 
 * @author David Matuszek
 * @author Alex Fiuk
 * @author Matthias Chia
 */
public class Poly extends Shape {
	int x1;
	int y1;
	int x2;
	int y2;
	int x3;
	int y3;
	int x4;
	int y4;
	int nx1;
	int nx2;
	int nx3;
	int nx4;
	int ny1;
	int ny2;
	int ny3;
	int ny4;
	Color polyColor = new Color(this.getRandom(40, 160), this.getRandom(40, 160), this.getRandom(40, 160));	

	
	public Poly(int xPosition, int yPosition, int xDelta, int yDelta){
		super(xPosition, yPosition, xDelta, yDelta);
	}
		
	
	public void draw(Graphics g){
		x1 = xPosition;
		y1 = yPosition;
		x2 = xPosition + this.getRandom(this.getRandom(30, 50),this.getRandom(60, 120));
		y2 = yPosition - this.getRandom(this.getRandom(30, 50),this.getRandom(60, 120));
		x3 = x2 + this.getRandom(this.getRandom(30, 50),this.getRandom(60, 120));
		y3 = y2 + this.getRandom(this.getRandom(30, 50),this.getRandom(60, 120));
		x4 = x3 - this.getRandom(this.getRandom(30, 50),this.getRandom(60, 120));
		y4 = y3 + this.getRandom(this.getRandom(30, 50),this.getRandom(60, 120));
		
		nx1 = xLimit - x1;
		nx2 = xLimit - x2;
		nx3 = xLimit - x3;
		nx4 = xLimit - x4;
		
		ny1 = yLimit - y1;
		ny2 = yLimit - y2;
		ny3 = yLimit - y3;
		ny4 = yLimit - y4;
		
		g.setColor(polyColor);
		g.fillPolygon(new int[] {x1,x2,x3,x4},new int[]{y1,y2,y3,y4},4);
		g.fillPolygon(new int[] {nx1,nx2,nx3,nx4},new int[]{y1,y2,y3,y4},4);
		g.fillPolygon(new int[] {x1,x2,x3,x4},new int[]{ny1,ny2,ny3,ny4},4);
		g.fillPolygon(new int[] {nx1,nx2,nx3,nx4},new int[]{ny1,ny2,ny3,ny4},4);
		
		g.fillPolygon(new int[] {y1,y2,y3,y4},new int[]{x1,x2,x3,x4},4);
		g.fillPolygon(new int[] {ny1,ny2,ny3,ny4},new int[]{x1,x2,x3,x4},4);
		g.fillPolygon(new int[] {y1,y2,y3,y4},new int[]{nx1,nx2,nx3,nx4},4);
		g.fillPolygon(new int[] {ny1,ny2,ny3,ny4},new int[]{nx1,nx2,nx3,nx4},4);
		
	}
}