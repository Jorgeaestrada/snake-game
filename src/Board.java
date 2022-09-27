import java.awt.Font;
import java.awt.Point;
import java.awt.Toolkit;
import java.awt.Graphics;
import java.awt.FontMetrics;
import java.awt.event.KeyEvent;
import java.awt.event.KeyAdapter;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.Timer;
import javax.swing.JPanel;

public class Board extends JPanel implements ActionListener {
    private Snake snake = new Snake();
    private Food food = new Food();
    Point lastTail = new Point();
    private Timer timer;
    private boolean inGame = true;
    private int xAxisDirection = 1;
    private int yAxisDirection = 0;
    private int score = 25;

    String scoreMsg = Settings.SCORE_MSG;
    Font scoreFont = new Font(Settings.TEXT_FONT, Font.BOLD, Settings.TEXT_SIZE);
    FontMetrics scoreFontMetrics = getFontMetrics(scoreFont);

    public Board() {
        addKeyListener(new TAdapter());
        setBackground(Settings.BOARD_BG_COLOR);
        setPreferredSize(Settings.BOARD_DIMENSION);
        setFocusable(true);
        requestFocusInWindow();
        checkElementOverlap();

        timer = new Timer(Settings.DELAY, this);
        timer.start();
    }

    public void startGame() {
        Point snakeHead = snake.getHead();

        checkCollision();
        // snake.print();
        // food.print();

        Point newHead = new Point();

        int xHead = snakeHead.x + (Settings.SNAKE_WIDTH * xAxisDirection);
        int yHead = snakeHead.y + (Settings.SNAKE_HEIGHT * yAxisDirection);

        newHead.setLocation(xHead, yHead);
        snake.addHead(newHead);
        lastTail = snake.getTail();
        snake.removeTail();

        if (food.getFoodCoordinates().equals(snake.getHead())) {
            snake.addTail(lastTail.x, lastTail.y, xAxisDirection, yAxisDirection);
            score += 1;
            checkElementOverlap();
        }
    }

    public void checkElementOverlap() {
        Point p = food.getFoodCoordinates();
        for (int i = 0; i < Settings.SNAKE_LENGTH; i++) {
            Point q = snake.getPointByIndex(i);
            if (p.equals(q)) {
                food.generateFood();
                checkElementOverlap();
            }
        }
    }

    public void checkCollision() {
        for (int i = 0; i < Settings.SNAKE_LENGTH; i++) {
            Point bodyCoordinate = snake.getPointByIndex(i);
            // out of board limits
            if (bodyCoordinate.x >= Settings.BOARD_WIDTH
                    || bodyCoordinate.y >= Settings.BOARD_HEIGHT
                    || bodyCoordinate.x <= 0
                    || bodyCoordinate.y <= 0) {
                inGame = false;
            }
            // self collision
            if (i != 0 && snake.getHead().equals(bodyCoordinate)) {
                inGame = false;
            }
        }
    }

    @Override
    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        if (inGame) {
            g.setFont(scoreFont);
            g.setColor(Settings.TEXT_COLOR);
            g.drawString(scoreMsg + String.valueOf(score), Settings.BOARD_WIDTH - 350, 50);
            // painting the food
            g.setColor(Settings.FOOD_COLOR);
            Point foodPoint = food.getFoodCoordinates();
            g.fillRect(foodPoint.x, foodPoint.y, Settings.FOOD_WIDTH, Settings.FOOD_HEIGHT);
            // paint snake body
            g.setColor(Settings.SNAKE_COLOR);
            for (int i = 0; i < snake.getSnakeSize(); i++) {
                Point p = snake.getPointByIndex(i);
                g.fillRect(p.x, p.y, Settings.SNAKE_WIDTH, Settings.SNAKE_HEIGHT);
            }
        } else {
            String gameOverMsg = Settings.GAME_OVER_MSG;
            Font font = new Font(Settings.TEXT_FONT, Font.BOLD, Settings.TEXT_SIZE);
            FontMetrics fontMetrics = getFontMetrics(font);

            g.setFont(font);
            g.setColor(Settings.TEXT_COLOR);
            g.drawString(gameOverMsg, (Settings.BOARD_WIDTH - fontMetrics.stringWidth(gameOverMsg)) / 2,
                    Settings.BOARD_HEIGHT / 2);
        }
        Toolkit.getDefaultToolkit().sync();
    }

    public class TAdapter extends KeyAdapter {
        public void keyPressed(KeyEvent e) {
            int key = e.getKeyCode();

            if (key == KeyEvent.VK_UP && yAxisDirection != 1) {
                xAxisDirection = 0;
                yAxisDirection = -1;
            } else if (key == KeyEvent.VK_DOWN && yAxisDirection != -1) {
                xAxisDirection = 0;
                yAxisDirection = 1;
            } else if (key == KeyEvent.VK_LEFT && xAxisDirection != 1) {
                xAxisDirection = -1;
                yAxisDirection = 0;
            } else if (key == KeyEvent.VK_RIGHT && xAxisDirection != -1) {
                xAxisDirection = 1;
                yAxisDirection = 0;
            }
        }
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        startGame();
        repaint();
    }

}
