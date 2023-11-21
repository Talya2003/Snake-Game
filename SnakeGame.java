import java.awt.*;
import java.awt.event.*;
import java.util.ArrayList;
import java.util.Random;
import javax.swing.*;

public class SnakeGame extends JPanel implements ActionListener , KeyListener{

    @Override
    public void actionPerformed(ActionEvent e) {
        move_location();
        repaint();
    }

    @Override
    public void keyPressed(KeyEvent e) {
        //move the snake & validation
        if (e.getKeyCode() == KeyEvent.VK_UP && speed_y != 1) {
            speed_x = 0;
            speed_y = -1;
        }

        else if (e.getKeyCode() == KeyEvent.VK_DOWN && speed_y != -1) {
            speed_x = 0;
            speed_y = 1;
        }

        else if (e.getKeyCode() == KeyEvent.VK_RIGHT && speed_x != -1) {
            speed_x = 1;
            speed_y = 0;
        }

        else if (e.getKeyCode() == KeyEvent.VK_LEFT && speed_x != 1) {
            speed_x = -1;
            speed_y = 0;
        }
    }

    @Override
    public void keyTyped(KeyEvent e) {

    }

    @Override
    public void keyReleased(KeyEvent e) {

    }

    private class Dice{
        int x,y;

        //constructor
        Dice(int x , int y) {
            this.x = x;
            this.y = y;
        }
    }

    int width_board;
    int height_board;
    int dice_size = 25;
    Dice snake_head; //this is the snake
    Dice apple;
    Random random_location;

    Timer time;

    int speed_x , speed_y;

    SnakeGame(int width_board , int height_board) {
        this.width_board = width_board;
        this.height_board = height_board;
        setPreferredSize(new Dimension(this.width_board , this.height_board));
        setBackground(Color.BLACK);

        addKeyListener(this);
        setFocusable(true);

        snake_head = new Dice(5,5);
        apple = new Dice(10,10);
        random_location = new Random();
        apple_location();

        speed_x = 0;
        speed_y = 0;

        //this is the timer of the game
        time = new Timer(100 , this);
        time.start();
    }

    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        draw(g);
    }

    public void draw(Graphics g) {
        //draw the grid
        for(int i = 0; i < (width_board/dice_size); i++) {
            g.drawLine(i*dice_size , 0 , i*dice_size , height_board);
            g.drawLine(0 , i*dice_size , width_board , i*dice_size);
        }
        //draw the snake(the beginning status)
        g.setColor(Color.green);
        g.fillRect(snake_head.x*dice_size , snake_head.y*dice_size , dice_size , dice_size);

        //draw the apple
        g.setColor(Color.red);
        g.fillRect(apple.x*dice_size , apple.y*dice_size , dice_size , dice_size);
    }

    public void apple_location() {
        apple.x = random_location.nextInt(width_board/dice_size);
        apple.y = random_location.nextInt(height_board/dice_size);
    }

    public void move_location() {
        //the snake
        snake_head.x += speed_x;
        snake_head.y += speed_y;
    }

}
