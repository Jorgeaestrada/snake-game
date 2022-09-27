import java.awt.Point;
import java.util.Random;

public class Food {

	static final Random random = new Random(System.currentTimeMillis());

	private Point p = new Point();
	private int xPos;
	private int yPos;

	public Food() {
		generateFood();
	}

	public void generateFood() {
		xPos = random.nextInt(Settings.BOARD_WIDTH / Settings.FOOD_WIDTH) * Settings.FOOD_WIDTH;
		yPos = random.nextInt(Settings.BOARD_HEIGHT / Settings.FOOD_HEIGHT) * Settings.FOOD_HEIGHT;
		while(yPos <= 50){
			yPos = random.nextInt(Settings.BOARD_HEIGHT / Settings.FOOD_HEIGHT) * Settings.FOOD_HEIGHT;
		}
		p.setLocation(xPos, yPos);
	}

	public Point getFoodCoordinates() {
		return p;
	}

	public void print(){
		System.out.println("Food:");		
		System.out.println("[" + p.x + ",\t" + p.y + "]");
		System.out.println();
	}
}