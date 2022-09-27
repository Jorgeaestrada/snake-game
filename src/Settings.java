import java.awt.Color;
import java.awt.Dimension;

public class Settings {
    public static final int DELAY = 60;
    
    public static final int GLOBAL_SCALE = 30;
    
    // snake settings
    public static int SNAKE_LENGTH = 30;
    public static int SNAKE_WIDTH = GLOBAL_SCALE;
    public static int SNAKE_HEIGHT = SNAKE_WIDTH;
    public static Color SNAKE_COLOR = new Color(0, 136, 163);
    // board settings
    public static int BOARD_WIDTH = 1920 - (1920 % GLOBAL_SCALE);
    public static int BOARD_HEIGHT = 1080 - (1080 % GLOBAL_SCALE);
    public static Color BOARD_BG_COLOR = Color.WHITE;
    public static Dimension BOARD_DIMENSION = new Dimension(BOARD_WIDTH, BOARD_HEIGHT);
    // food settings
    public static int FOOD_WIDTH = GLOBAL_SCALE;
    public static int FOOD_HEIGHT = FOOD_WIDTH;    
    public static Color FOOD_COLOR = new Color(255, 64, 129);
    // text font settings
    public static final String GAME_OVER_MSG = "Game Over";
    public static final int TEXT_SIZE = 50;
    public static final String TEXT_FONT = "Helvetica";
    public static Color TEXT_COLOR = new Color(244, 67, 54);

    public static final String SCORE_MSG = "Score: ";
}