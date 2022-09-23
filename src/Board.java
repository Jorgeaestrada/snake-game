import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class Board extends JPanel implements Runnable {

    private Thread thread;
    private Snake snake;
    private static Food food;

    private int xAxis = 1;
    private int yAxis = 0;

    private static final int X_SIZE = 1024;
    private static final int Y_SIZE = 768;
    private final int DELAY = 75;

    public Board() {
        snake = new Snake();
        food = new Food();
        thread = new Thread(this);
        thread.start();

        setBackground(Color.WHITE);
        setFocusable(true);
        requestFocusInWindow();
        setPreferredSize(new Dimension(X_SIZE, Y_SIZE));
    }

    @Override
    public void paint(Graphics g) {
        super.paint(g);

        // painting the snake
        g.setColor(new Color(0, 136, 163));
        // add last && remove first
        for (int i = 0; i < snake.getSnakeSize(); i++) {
            Point p = snake.getSnakeBodyIndex(i);
            g.fillRect(p.x, p.y, 10, 10);
        }

        // painting the food
        g.setColor(new Color(255, 64, 129));
        Point foodPoint = food.getFoodCoordinates();
        g.fillRect(foodPoint.x, foodPoint.y, 10, 10);

        // some snake logic
        Point p = snake.getTail();
        snake.addTail(p.x, p.y, xAxis, yAxis);
        snake.removeHead();
        Toolkit.getDefaultToolkit().sync();
    }

    @Override
    public void run() {

        long beforeTime, timeDifference, sleep;
        beforeTime = System.currentTimeMillis();

        addKeyListener(new KeyListener() {
            @Override
            public void keyPressed(KeyEvent e) {
                int key = e.getKeyCode();

                if (key == KeyEvent.VK_UP && yAxis != 1) {
                    xAxis = 0;
                    yAxis = -1;
                } else if (key == KeyEvent.VK_DOWN && yAxis != -1) {
                    xAxis = 0;
                    yAxis = 1;
                } else if (key == KeyEvent.VK_LEFT && xAxis != 1) {
                    xAxis = -1;
                    yAxis = 0;
                } else if (key == KeyEvent.VK_RIGHT && xAxis != -1) {
                    xAxis = 1;
                    yAxis = 0;
                }
            }

            @Override
            public void keyReleased(KeyEvent e) {
            }

            @Override
            public void keyTyped(KeyEvent e) {
            }
        });

        while (true) {
            repaint();
            timeDifference = System.currentTimeMillis() - beforeTime;
            sleep = DELAY - timeDifference;

            if (food.getFoodCoordinates().equals(snake.getTail())) {
                snake.addHead(food.getFoodCoordinates());

                food.generateFood();
            }

            if (sleep < 0) {
                sleep = 2;
            }

            try {
                Thread.sleep(sleep);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            beforeTime = System.currentTimeMillis();
        }
    }
}