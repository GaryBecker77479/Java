package GMaze;

public enum Wall {
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
	DOWN(1, 2, 0, 1), UP(2, 1, 0, -1), LEFT(4, 8, -1, 0), RIGHT(8, 4, 1, 0);
	int dir = 0;
	int opp = 0;
	int nextX = 0;
	int nextY = 0;
	Wall(int i1, int i2, int i3, int i4){
		dir = i1;
		opp = i2;
		nextX = i3;
		nextY = i4;
	}
}
