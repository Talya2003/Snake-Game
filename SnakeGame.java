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

        //game over
        if (game_over) {
            time.stop();
        }
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
    Dice snake_head; //this is the head of snake (the beginning status)
    ArrayList<Dice> snake; // this is the body of the snake
    Dice apple;
    Random random_location;

    Timer time;

    int speed_x , speed_y;

    boolean game_over;

    //constructor
    SnakeGame(int width_board , int height_board) {
        this.width_board = width_board;
        this.height_board = height_board;

        setPreferredSize(new Dimension(this.width_board , this.height_board));
        setBackground(Color.BLACK);

        this.game_over = false;

        addKeyListener(this);
        setFocusable(true);

        snake_head = new Dice(5,5);
        snake = new ArrayList<>();

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

        //draw the body of te snake
        for (int i = 0; i < snake.size()-1; i++) { //We will go through all the squares of the snake's body, that is, we will go through the entire snake array, and color them each separately.
            Dice part_of_snake = snake.get(i); //catch one of the dices of the snake
            g.fillRect(part_of_snake.x*dice_size , part_of_snake.y*dice_size , dice_size , dice_size); //draw this part (dice)
        }

        //draw the apple
        g.setColor(Color.red);
        g.fillRect(apple.x*dice_size , apple.y*dice_size , dice_size , dice_size);

        //score
        g.setFont(new Font("Calibri" , Font.PLAIN , 18));
        if (game_over) {
            g.setColor(Color.red);
            g.drawString("Game Over: " + String.valueOf(snake.size()) , dice_size - 16 , dice_size);
        }

        else {
            g.setColor(Color.green);
            g.drawString("Score: " + String.valueOf(snake.size()) , dice_size - 16 , dice_size);
        }
    }

    public void apple_location() {
        apple.x = random_location.nextInt(width_board/dice_size);
        apple.y = random_location.nextInt(height_board/dice_size);
    }

    public boolean stuck(Dice dice_1 , Dice dice_2) {
        // check if those two dices are in the same place --> if it is true == there is a stuck
        return (dice_1.x == dice_2.x && dice_1.y == dice_2.y);
    }

    public void move_location() {
        //check if there is a stuck == if it is true == eats the apple
        if (stuck(snake_head , apple)) {
            Dice dice_adding = new Dice(apple.x , apple.y);
            //add the location of the apple to the snake
            snake.add(dice_adding);
            //find a new location for the apple
            apple_location();
        }

        //the body of the snake
        //We will want to join together all the parts of the snake that are added to it
        for (int i = 0; i < snake.size()-1; i++) {
            Dice part_of_snake = snake.get(i);
            //If it is the first part of the snake - we will define the position to be the position of the head of the snake
            if(i == 0) {
                part_of_snake.x = snake_head.x;
                part_of_snake.y = snake_head.y;
            }

            //Otherwise, we will grab the previous part, and paint this place
            else {
                Dice previous_part = snake.get(i-1);
                part_of_snake.x = previous_part.x;
                part_of_snake.y = previous_part.y;
            }
        }

        //the snake
        snake_head.x += speed_x;
        snake_head.y += speed_y;

        //game over
        for (int i = 0; i < snake.size(); i++) {
            Dice part_of_snake = snake.get(i);
            //check if it stacks with the head of the snake
            if(stuck(snake_head , part_of_snake)) {
                game_over = true;
            }
        }

        if (snake_head.x*dice_size < 0 || snake_head.x*dice_size > width_board || snake_head.y*dice_size < 0 || snake_head.y*dice_size > height_board) {
            game_over = true;
        }
    }

}
