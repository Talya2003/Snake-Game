import javax.swing.*;
import java.awt.*;

public class App {

    public static final int SIZE_BOARD = 600;

    public static void main(String[] args) throws Exception{
        int width_board = SIZE_BOARD;
        int height_board = SIZE_BOARD;

        // decorate the frame
        JFrame frame = new JFrame("Snake Game");
        frame.setVisible(true);
        frame.setSize(width_board , height_board);
        frame.setLocationRelativeTo(null);
        frame.setResizable(false);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        // create the board of the game
        SnakeGame snakeGame = new SnakeGame(width_board , height_board);
        frame.add(snakeGame);
        frame.pack();

        snakeGame.requestFocus();
    }

}
