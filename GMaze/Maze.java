package GMaze;

import java.awt.*;
import javax.swing.*;

public class Maze {
	Cell [][] cellArray = new Cell[GMaze.XMAX][GMaze.YMAX];
	BasicFrame frame = new BasicFrame();
	int xLogo = 0;
	int yLogo = 0;
	int getXLogo() { return xLogo; }
	int getYLogo() { return yLogo; }
	int startX=0;
	int endX=0;

	public void init() {
// Use Swing to display the maze
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setSize(10*GMaze.XMAX+40,10*GMaze.YMAX+60);
		frame.setVisible(true);
		frame.setNewBoard(0);
		frame.repaint();
		try {
			Thread.sleep(300);
		} catch (Exception ex) {}
		frame.setNewBoard(1);
// Call method to create array of cells
		makeMaze(GMaze.XMAX, GMaze.YMAX);
		frame.repaint();
		try {
			Thread.sleep(300);
		} catch (Exception ex) {}
		
//		printMaze(GMaze.XMAX, GMaze.YMAX);
// Use random number to find starting position on bottom border row
// and ending position on top border row
		startX = (int)(Math.random() * (GMaze.XMAX-3))+1; // cannot start at 0 or XMAX-1
		endX = (int)(Math.random() * (GMaze.XMAX-3))+1;
// Create maze by calling the cell below the ending cell (create backwards from solving)
/*		int xInit = GMaze.XMAX;
		int yInit = GMaze.YMAX;
		do {
			xInit /= 2;
			yInit /= 2;
		} while (cellArray[xInit][yInit].getVisited() != 0);
		System.out.println(xInit + ", " + yInit);
		cellArray[xInit][yInit].initCell(Wall.DOWN);
		cellArray[xInit][yInit].setTop(1); */
		cellArray[1][GMaze.YMAX/2].initCell(Wall.RIGHT);
		cellArray[1][GMaze.YMAX/2].setLeft(1);
// initialize ending border cell and the cell below
		cellArray[startX][GMaze.YMAX-1].setVisited(1);
		cellArray[startX][GMaze.YMAX-1].setTop(0);
		cellArray[startX][GMaze.YMAX-2].setBottom(0);
// initialize starting border cell and the cell below
		cellArray[endX][0].setVisited(1);
		cellArray[endX][0].setBottom(0);
		cellArray[endX][1].setTop(0);
	}
	void makeMaze(int xMax, int yMax) {
// Create cell objects
		for(int x=0; x<xMax; x++) {
			for(int y=0; y<yMax; y++) {
				cellArray[x][y] = new Cell(x, y);
// if cell is left, top, right or bottom, make it a border cell
				if((x==0) | (y==0) | x==(xMax-1) | y==(yMax-1)) {
					cellArray[x][y].makeBorder();
					frame.repaint();
				}
			}
		}
		xLogo =(int)(Math.random()*(xMax-30))+15;
		yLogo =(int)(Math.random()*(yMax-30))+15;
		for(int x = xLogo; x < xLogo + 10; x++) {
			for(int y = yLogo; y < yLogo + 10; y++) {
				cellArray[x][y].setVisited(3);
			}
		}
	}
// Solve maze starting at the start cell
	void solve() {
		cellArray[startX][GMaze.YMAX-1].solveCell(Wall.UP);
//		printMaze(GMaze.XMAX, GMaze.YMAX);
	}
// Print the text version of the maze
//	void printMaze(int xMax, int yMax) {
//		for(int y=0; y<yMax; y++) {
//			for(int x=0; x<xMax; x++) {
//				System.out.print(cellArray[x][y].getStatus() + " ");
//			}
//			System.out.println();
//		}
//		System.out.println();
//	}
}