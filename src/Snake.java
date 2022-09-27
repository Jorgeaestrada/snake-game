import java.awt.Point;
import java.util.LinkedList;

public class Snake {
	private LinkedList<Point> linkedList = new LinkedList<>();

	public Snake() {
		generateSnake();
	}

	public void generateSnake(){
		linkedList.clear();
		int xHead = ((Settings.BOARD_WIDTH / 2) / Settings.SNAKE_WIDTH) * Settings.SNAKE_WIDTH;
		int yHead = ((Settings.BOARD_HEIGHT / 2) / Settings.SNAKE_HEIGHT) * Settings.SNAKE_HEIGHT;

		for (int i = 0; i < Settings.SNAKE_LENGTH; i++) {
			//add point for snake head
			if(i == 0){
				linkedList.addFirst(new Point(xHead, yHead));
			} else {
				// add points for snake body
				int xTail = xHead - (i * Settings.SNAKE_HEIGHT);
				int yTail = yHead;
				linkedList.addLast(new Point(xTail, yTail));
			}
		}
	}

	public Point getHead() {
		return linkedList.getFirst();
	}

	public void addHead(Point p) {
		linkedList.addFirst(p);
	}

	public void removeHead() {
		linkedList.removeFirst();
	}

	public Point getTail() {
		return linkedList.getLast();
	}

	public void addTail(int x, int y, int xAxis, int yAxis) {
		Point p = new Point();
		p.setLocation(x, y);
		linkedList.addLast(p);
	}

	public void removeTail() {
		linkedList.removeLast();
	}

	public int getSnakeSize() {
		return linkedList.size();
	}

	public Point getPointByIndex(int i) {
		return linkedList.get(i);
	}

	public void updatePointByIndex(int i, Point p) {
		linkedList.set(i, p);
	}

	public void print() {
		System.out.println("Snake:");
		for (int i = 0; i < linkedList.size(); i++) {
			Point p = linkedList.get(i);
			System.out.println("[" + p.x + ",\t" + p.y + "]");
		}
		System.out.println();
	}
}