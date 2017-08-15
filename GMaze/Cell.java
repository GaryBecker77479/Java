package GMaze;

public class Cell {
	/*
	 * Bit 0	= Bottom wall
	 * Bit 1	= Top wall
	 * Bit 2	= Left wall
	 * Bit 3	= Right wall
	 * Bit 5,4	= 00 Cell not visited
	 * 			= 01 Cell Initialized
	 * 			= 10 Cell valid path
	 * 			= 11 Cell not valid path
	 */

	private int cellStatus = 15;
	private int xLoc=0;
	private int yLoc=0;
	private int paint = 0;

	Cell(int x, int y) {
		xLoc=x;
		yLoc=y;
		cellStatus=15;
		paint = 0;
	}

	private int getStatus() {
		return cellStatus;
	}
	public boolean getBottom() {
		if((cellStatus & 1) > 0)
			return false;
		else
			return true;
	}
	public void setBottom(int i) {
		if(i > 0)
			cellStatus |= 1;
		else
			cellStatus &= 0x3E;
	}
	public boolean getTop() {
		if((cellStatus & 2) > 0)
			return false;
		else
			return true;
	}
	public void setTop(int i) {
		if(i > 0)
			cellStatus |= 2;
		else
			cellStatus &= 0x3D;
	}
	public boolean getLeft() {
		if((cellStatus & 4) > 0)
			return false;
		else
			return true;
	}
	public void setLeft(int i) {
		if(i > 0)
			cellStatus |= 4;
		else
			cellStatus &= 0x3B;
	}
	public boolean getRight() {
		if((cellStatus & 8) > 0)
			return false;
		else
			return true;
	}
	public void setRight(int i) {
		if(i > 0)
			cellStatus |= 8;
		else
			cellStatus &= 0x37;
	}
	public int getVisited() {
			return cellStatus/16;
	}
	public void setVisited(int i) {
		cellStatus = (cellStatus & 15) | (i*16);
	}
	public int getPaint() {
		return paint;
	}
	public void makeBorder() {
		cellStatus = 0x3F;
		paint = 1;
	}
// Init cell called with direction that it was entered
	public int initCell(Wall z) {
// If cell is already inited, return -1 to indicate it cannot be re-inited 
		if(cellStatus >= 16) {
			return -1;
		} else {
// remove the wall from the opposite side from the calling cell
			cellStatus = cellStatus ^ z.opp;
// Mark cell as inited
			cellStatus = cellStatus | 0x10;
// keep checking to make sure an adjacent cell is available until there is none
			while (((GMaze.m.cellArray[xLoc-1][yLoc].cellStatus & 48) == 0)
				|  ((GMaze.m.cellArray[xLoc+1][yLoc].cellStatus & 48) == 0)
				|  ((GMaze.m.cellArray[xLoc][yLoc-1].cellStatus & 48) == 0)
				|  ((GMaze.m.cellArray[xLoc][yLoc+1].cellStatus & 48) == 0)) {
// If at least one cell is available, try a ramdom direction
				Wall a = getRandomDirection();
// Call initCell from this random direction
				if(GMaze.m.cellArray[xLoc+a.nextX][yLoc+a.nextY].initCell(a) > 0) {
// If cell was free, mark our wall as gone
					cellStatus = cellStatus ^ a.dir;
				}
			}
// we ran out of free adjacent cells to init
		paint = 1;
		GMaze.m.frame.repaint();
		try {
			Thread.sleep(1);
		} catch (Exception ex) {}
		return 1;
		}
	}
	public int solveCell(Wall z) {
// Mark cell as a good path
		cellStatus = ((cellStatus & 0x0F) | 0x20);
//		entry = z.opp;
// check cell address to see if it is 0 which means this is the exit cell
		if(yLoc==0) {
			GMaze.m.frame.repaint();
			try {
				Thread.sleep(1);
			} catch (Exception ex) {}
// If 0 then return sucessful completion
			return 1;
// if not the end cell try finding an adjacent cell to move into
		} else {
// try each direction in order
			for(Wall a : Wall.values()) {
//				Maze.frame.repaint();
//				try {
//					Thread.sleep(1);
//				} catch (Exception ex) {}
// is wall gone in this direction and that next cell has only been inited
				if(((cellStatus & a.dir) == 0) && ((GMaze.m.cellArray[xLoc+a.nextX][yLoc+a.nextY].getStatus()&0x30) == 0x10))  {
//					exit = exit | a.dir;
// If so, then move into the next cell
					if (GMaze.m.cellArray[xLoc+a.nextX][yLoc+a.nextY].solveCell(a) == 1){ 
//						Maze.frame.repaint();
//						try {
//							Thread.sleep(1);
//						} catch (Exception ex) {}
// if that cell return sucessful completion of the maze, then return the same
						return 1;
					}
//					Maze.frame.repaint();
//					try {
//						Thread.sleep(1);
//					} catch (Exception ex) {}
				}
				GMaze.m.frame.repaint();
				try {
					Thread.sleep(1);
				} catch (Exception ex) {}
			}
// If sucessful completion not found, this must not be the valid path
// so mark cell as invalid and return failure flag
			cellStatus = ((cellStatus & 0x0F) | 0x30);
			GMaze.m.frame.repaint();
			try {
				Thread.sleep(1);
			} catch (Exception ex) {}
			return 2;
		}
	}
// Choose a random direction of the 4 available
	public Wall getRandomDirection() {
		Wall [] a = Wall.values();
		return a[(int)(Math.random() * a.length)];
	}

}
