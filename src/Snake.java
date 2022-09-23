import java.awt.*;
import java.util.LinkedList;

public class Snake {

	private LinkedList<Point> linkedList = new LinkedList<>();

	private int size = 7;
	private int xPos;
	private int yPos;

	public Snake() {
		for (int i = 0; i < size; i++) {
			this.xPos = 250 + (i * 10);
			this.yPos = 250;
			Point p = new Point();
			p.setLocation(xPos, yPos);
			linkedList.addLast(p);
		}
	}

	public void addHead(Point p) {
		linkedList.addFirst(p);
	}

	public void removeHead() {
		linkedList.removeFirst();
	}

	public void addTail(int x, int y, int xAxis, int yAxis) {
		xPos = x;
		yPos = y;
		Point p = new Point();
		p.setLocation(xPos + (10 * xAxis), yPos + (10 * yAxis));
		linkedList.addLast(p);
	}

	public Point getTail() {
		return linkedList.getLast();
	}

	public int getSnakeSize() {
		return linkedList.size();
	}

	public Point getSnakeBodyIndex(int i) {
		return linkedList.get(i);
	}

	public void print() {
		System.out.println(linkedList);
	}
}