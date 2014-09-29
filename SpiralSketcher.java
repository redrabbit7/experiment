import java.awt.Color;
import java.awt.Graphics;
import java.util.ArrayList;
import javax.swing.JApplet;

/*
 * @author: Sahithi Reddigari
 * 
 * */

public class SpiralSketcher extends JApplet {

	private static final int DELAY_TIME = 300;
	private static final long serialVersionUID = 1L;
	private static final int SPACING = 2;
	private Direction direction = Direction.START;

	private enum Direction {
		RIGHT, LEFT, UP, DOWN, START
	};

	private int startX = 0;
	private int startY = 0;

	private ArrayList<Integer> horizontal_coords = new

	ArrayList<Integer>();
	private ArrayList<Integer> vertical_coords = new

	ArrayList<Integer>();

	public static int INCREMENT = 1;
	private int width, height;

	public void init() {

		width = getSize().width;
		height = getSize().height;
		startX = width / 2;
		startY = height / 2;
		setSize(width, height);
		getContentPane().setBackground(Color.black);
	}

	public void drawFibonacciSpirals(Graphics g, int max, int curr, int next) {

		try {
			Thread.sleep(DELAY_TIME);
		} catch (Exception ex) {
		}

		if (direction.equals(Direction.START)) {

			horizontal_coords.add(startX);
			vertical_coords.add(startY);
			horizontal_coords.add(startX + curr);
			vertical_coords.add(startY + curr);

			drawArc(startX, startY, curr, -270, g);

			drawPoint(startX, startY + curr, g);
			direction = Direction.RIGHT;

		} else if (direction.equals(Direction.RIGHT)) {
			startX = horizontal_coords.get(horizontal_coords.size() - 1);
			startY = vertical_coords.get(0);
			vertical_coords.add(startY + curr);
			horizontal_coords.add(startX + curr);

			drawArc(startX - curr, startY, curr, 0, g);

			drawPoint(startX, startY, g);
			direction = Direction.DOWN;

		} else if (direction.equals(Direction.DOWN)) {

			startX = horizontal_coords.get(0);
			startY = vertical_coords.get

			(vertical_coords.size() - 1);
			vertical_coords.add(startY + curr);
			horizontal_coords.add(startX + curr);

			drawArc(startX - curr, startY - curr, curr, 270, g);

			drawPoint(startX, startY + curr, g);
			drawPoint(startX + curr, startY, g);
			direction = Direction.LEFT;

		} else if (direction.equals(Direction.LEFT)) {
			startX = horizontal_coords.get(0) - curr;
			startY = vertical_coords.get(0);
			horizontal_coords.add(0, horizontal_coords.get(0) - curr);

			drawArc(startX, startY - curr, curr, 180, g);

			drawPoint(startX, startY, g);
			direction = Direction.UP;

		} else {
			startX = horizontal_coords.get(0);
			startY = vertical_coords.get(0) - curr;
			vertical_coords.add(0, vertical_coords.get(0) - curr);

			drawArc(startX, startY, curr, -270, g);

			direction = Direction.RIGHT;

		}

		drawRectangle(g, curr);

		// For e.g.: 1,1,2,3,5,8,13,21,34,55,..etc.
		if (curr + next < max) {

			drawFibonacciSpirals(g, max, next, curr + next);
		}

	}

	private void drawArc(int startX, int startY, int radius, int arcPoint,
			Graphics g) {
		g.setColor(Color.green);
		g.drawArc(startX, startY, radius * 2, radius * 2, arcPoint, 90);
	}

	private void drawPoint(int x, int y, Graphics g) {
		g.setColor(Color.red);
		int size = 2;
		g.fillOval(x - size / 2, y - size / 2, size, size);
	}

	private void drawRectangle(Graphics g, int curr) {
		g.setColor(Color.blue);
		g.drawRect(startX, startY, curr, curr);
	}

	/*
	 * public void update(Graphics g){ super.update(g); paint(g); }
	 */

	public void paint(Graphics g) {

		super.paint(g);

		for (int i = 0; i < 100; i++) {
			drawFibonacciSpirals(g, height * 4, INCREMENT, INCREMENT);

			try {
				Thread.sleep(1000);
			} catch (Exception ex) {
			}
			
			g.setColor(Color.black);
			g.fillRect(0, 0, width, height);
			
			reset(i);
		}

	}

	private void reset(int i) {
		startX = width / 2;		// - i * SPACING ;
		startY = height / 2;	// - i * SPACING ;
		horizontal_coords = new ArrayList<Integer>();
		vertical_coords = new ArrayList<Integer>();
		direction = Direction.START;
	}

}