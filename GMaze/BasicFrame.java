package GMaze;

import java.awt.*;
import javax.swing.*;

public class BasicFrame extends JFrame {
	int newBoard = 0;
	public void setNewBoard(int i) {
		newBoard = i;
	}
	@Override
	public void paint(Graphics g){
		if(newBoard == 0) {
			g.setColor(Color.white);
			g.fillRect(0, 0, this.getWidth(), this.getHeight());
			g.setColor(Color.black);
			return;
		}
//		g.setColor(Color.black);
		for(int y=0; y<GMaze.YMAX; y++) {
			for(int x=0; x<GMaze.XMAX; x++) {
				paintCell(x, y, g);
			}
		}
		paintLogo(Color.lightGray, g);
	}

	void paintCell(int xPos, int yPos, Graphics g) {
		int paint = GMaze.m.cellArray[xPos][yPos].getPaint();
		
		if((paint == 1) && (GMaze.m.cellArray[xPos][yPos].getVisited()!=0)) {
			g.setColor(Color.white);
			g.fillRect((10*xPos+20), (10*yPos+40), 10, 10);
			g.setColor(Color.black);
			if(!GMaze.m.cellArray[xPos][yPos].getBottom()) {			// Bottom
				g.drawLine((10*xPos+20), (10*yPos+9+40), (10*xPos+9+20), (10*yPos+9+40));
			}
			if(!GMaze.m.cellArray[xPos][yPos].getTop()) {			// Top
				g.drawLine((10*xPos+20), (10*yPos+40), (10*xPos+9+20), (10*yPos+40));
			}
			if(!GMaze.m.cellArray[xPos][yPos].getLeft()) {			// Left
				g.drawLine((10*xPos+20), (10*yPos+40), (10*xPos+20), (10*yPos+9+40));
			}
			if(!GMaze.m.cellArray[xPos][yPos].getRight()) {			// Right
				g.drawLine((10*xPos+9+20), (10*yPos+40), (10*xPos+9+20), (10*yPos+9+40));
			}
			if(GMaze.m.cellArray[xPos][yPos].getVisited()==0x2) {	// correct path
				g.setColor(Color.blue);
				g.fillRect((10*xPos+2+20), (10*yPos+2+40), 6, 6);
				if(GMaze.m.cellArray[xPos][yPos].getBottom())
					g.fillRect((10*xPos+2+20), (10*yPos+4+40), 6, 6);
				if(GMaze.m.cellArray[xPos][yPos].getTop())
					g.fillRect((10*xPos+2+20), (10*yPos+40), 6, 6);
				if(GMaze.m.cellArray[xPos][yPos].getLeft())
					g.fillRect((10*xPos+20), (10*yPos+2+40), 6, 6);
				if(GMaze.m.cellArray[xPos][yPos].getRight())
					g.fillRect((10*xPos+4+20), (10*yPos+2+40), 6, 6);
				g.setColor(Color.black);
			}
			if(GMaze.m.cellArray[xPos][yPos].getVisited()==0x3) {	// incorrect path
				g.setColor(Color.red);
				g.fillRect((10*xPos+2+20), (10*yPos+2+40), 6, 6);
				if(GMaze.m.cellArray[xPos][yPos].getBottom())
					g.fillRect((10*xPos+2+20), (10*yPos+4+40), 6, 6);
				if(GMaze.m.cellArray[xPos][yPos].getTop())
					g.fillRect((10*xPos+2+20), (10*yPos+40), 6, 6);
				if(GMaze.m.cellArray[xPos][yPos].getLeft())
					g.fillRect((10*xPos+20), (10*yPos+2+40), 6, 6);
				if(GMaze.m.cellArray[xPos][yPos].getRight())
					g.fillRect((10*xPos+4+20), (10*yPos+2+40), 6, 6);
				g.setColor(Color.black);
			}
		}
	}
	void paintLogo(Color c, Graphics g) {
	    byte[] logo =
	       {0,0,0,0,0,0,0,1,0,0,0,0,0,0,0,0,1,0,0,1,0,0,0,0,0,0,0,0,0,1,0,0,
	    	0,0,0,0,0,0,0,1,0,0,0,0,0,0,0,0,1,0,0,1,0,0,0,0,0,0,0,0,0,1,0,0,
	        0,0,1,0,0,0,0,1,1,1,0,0,0,0,0,0,1,1,0,1,0,1,0,0,0,0,0,0,0,1,0,0,
	        0,0,0,0,0,0,0,0,1,0,0,0,0,0,0,0,0,0,0,0,0,0,1,0,0,0,0,0,1,0,0,0,
	        0,0,0,0,0,0,0,1,1,1,1,0,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,0,0,1,0,0,
	        0,0,0,0,1,0,0,0,1,0,0,0,1,0,0,0,0,0,0,0,0,0,0,0,0,0,1,0,0,0,0,0,
	        0,0,0,0,1,0,0,0,1,0,0,0,1,0,0,0,0,0,0,0,0,0,0,0,0,0,1,0,0,0,0,0,
	        0,0,0,0,1,1,0,0,1,0,0,0,1,0,1,1,1,0,0,0,0,0,0,0,1,0,1,0,0,1,1,1,
	        1,1,0,0,0,0,0,0,1,0,0,0,1,0,0,1,1,1,0,0,0,0,0,1,0,0,1,0,1,0,0,1,
	        0,1,1,1,0,0,0,0,1,0,0,0,1,0,0,0,1,1,1,0,0,0,1,0,0,0,1,0,0,0,0,0,
	        0,0,0,0,0,0,0,1,1,1,0,0,1,0,0,0,0,1,1,1,0,1,0,0,0,0,1,0,0,1,0,0,
	        0,0,0,0,0,0,0,0,0,1,0,0,1,0,0,0,0,0,1,0,1,0,0,0,0,0,1,0,1,0,1,0,
	        0,0,0,0,0,0,0,0,0,1,0,0,1,0,0,0,0,1,0,1,1,1,0,0,0,0,1,0,0,1,0,0,
	        0,0,0,0,0,0,0,0,0,1,0,0,1,0,0,0,1,0,0,0,1,1,1,0,0,0,1,0,0,0,0,0,
	        0,0,0,0,1,0,0,0,0,0,0,0,1,0,0,1,0,0,0,0,0,1,1,1,0,0,1,0,0,0,0,0,
	        0,0,0,0,1,0,0,0,1,0,0,0,1,0,1,0,0,0,0,0,0,0,1,1,1,0,1,0,0,1,0,0,
	        1,1,1,0,1,0,0,0,1,0,1,0,1,0,0,0,0,0,0,0,0,0,0,0,0,0,1,0,1,0,0,0,
	        0,0,0,1,1,0,0,0,1,0,0,0,1,0,0,0,0,0,0,0,0,0,0,0,0,0,1,0,0,0,0,0,
	        0,1,1,0,0,0,0,0,0,0,0,0,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,0,0,0,0,0,
	        0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,1,0,0,0,0,0,0,0,0,0,0,0,0,0,1,0,
	        0,0,0,0,0,0,1,1,1,1,1,1,1,0,0,0,1,1,1,0,1,1,0,0,0,0,0,0,1,0,0,0,
	        0,0,0,0,0,0,0,1,0,1,0,0,1,0,0,0,0,1,0,0,0,1,0,0,0,0,0,0,1,1,1,1,
	        0,0,0,0,0,0,0,0,0,0,0,0,1,0,0,0,0,0,0,0,0,1,0,0,0,1,0,0,0,0,0,0,
	        0,0,1,1,1,0,0,0,0,0,0,1,1,0,1,1,1,1,1,0,0,0,0,1,1,1,0,0,0,0,0,0,
	        1,1,1,0,1,0,0,0,1,1,0,0,1,0,1,0,0,0,0,1,1,1,0,1,0,1,0,0,0,0,0,0,
	        0,0,0,0,1,1,0,0,1,0,1,0,1,1,0,0,1,1,0,0,0,0,0,1,1,1,1,0,0,1,0,0,
	        0,0,0,0,1,0,0,0,1,1,0,0,1,0,0,0,0,1,0,0,0,1,0,0,0,0,0,0,0,0,0,0,
	        0,0,0,0,0,0,0,0,1,0,0,0,1,0,0,0,0,0,0,0,0,1,0,0,0,0,0,0,1,0,0,0,
	        0,0,0,0,0,0,0,0,1,0,0,0,1,1,0,0,0,0,0,0,0,1,0,0,0,0,0,0,0,1,0,0,
	        0,0,1,1,0,0,0,0,1,0,0,0,0,0,0,0,1,0,0,0,1,0,0,0,0,0,0,0,0,1,0,0,
	        1,0,0,0,0,1,0,0,1,0,0,0,0,0,0,0,0,0,0,0,1,0,0,0,0,0,0,0,1,0,1,0,
	    	1,0,0,0,0,1,0,0,1,0,0,0,0,0,0,0,0,0,0,0,1,0,0,0,0,0,0,0,1,0,1,0};
	    
	    int xLogoPixel = GMaze.m.getXLogo() * 10 + 20;
	    int yLogoPixel = GMaze.m.getYLogo() * 10 + 40;
	    
		g.setColor(Color.black);
		g.drawLine(xLogoPixel, yLogoPixel, xLogoPixel+99, yLogoPixel);
		g.drawLine(xLogoPixel, yLogoPixel, xLogoPixel, yLogoPixel + 99);
		g.drawLine(xLogoPixel+99, yLogoPixel+99, xLogoPixel+99, yLogoPixel);
		g.drawLine(xLogoPixel+99, yLogoPixel+99, xLogoPixel, yLogoPixel+99);
		g.setColor(c);
	    for(int x=0; x<32; x++)
	    	for(int y=0; y<32; y++){ 
	    		if(logo[y*32+x]==1){
	    			g.fillRect(xLogoPixel+2+3*x, yLogoPixel+2+3*y, 3, 3);
	    		}
	    	}		
	}
}
