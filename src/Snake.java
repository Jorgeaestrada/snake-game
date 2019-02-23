import java.awt.*;
import java.util.LinkedList;
import java.util.Queue;

public class Snake {

	private LinkedList<Point> linkedList = new LinkedList<>();

	private int size = 7;
	private int xPos;
	private int yPos;

	public Snake () {
		for (int i = 0;i < size; i++) {
			this.xPos = 250 + (i * 10);
			this.yPos = 250;
			Point p = new Point();
			p.setLocation(xPos, yPos);
			linkedList.addLast(p);
		}
	}

	public void removeFirst () {
		linkedList.removeFirst();
	}

	public void addLast (Point p) {
		linkedList.addFirst(p);
	}

	public int getSize () {
		return linkedList.size();
	}

	public void print () {
		System.out.println(linkedList);
	}

	public Point getIndex (int i) {
		return linkedList.get(i);
	}

	public Point getLast () {
		return linkedList.getLast();
	}

	public void newPoint (int x, int y, int xAxis, int yAxis) {
		xPos = x;
		yPos = y;
		Point p = new Point();
		p.setLocation(xPos + (10 * xAxis), yPos + (10 * yAxis));
		linkedList.addLast(p);
	}
}