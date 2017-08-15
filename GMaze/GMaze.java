package GMaze;

public class GMaze {
//	public final static int XMAX = 180;
	public final static int XMAX = 120;
	
//	public final static int YMAX = 95;
	public final static int YMAX = 80;
//	public final static int XMAX = 24;
//	public final static int YMAX = 10;
	static Maze m;
	public static void main(String[] args) {
		m = new Maze();
		while (true) {
			m.init();
			m.solve();
			m.frame.repaint();
			try {
				Thread.sleep(5000);
			} catch (Exception ex) {}
		}
	}
}
