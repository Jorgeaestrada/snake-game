import java.awt.Point;

public class Food {

	private static Point point = new Point();
	private int xPos;
	private int yPos;

	public Food () {
		generateFood();
	}

	public void generateFood () {
		this.xPos = (int) (Math.random() * 50) * 10;
		this.yPos = (int) (Math.random() * 50) * 10;
		point.setLocation(xPos, yPos);
	}

	public Point getFoodCoordinates () {
		return point;
	}
}