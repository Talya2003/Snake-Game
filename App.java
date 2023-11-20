import javax.swing.*;

public class App {

    public static final int SIZE_BOARD = 600;

    public static void main(String[] args) throws Exception{
        int width_board = SIZE_BOARD;
        int height_board = SIZE_BOARD;

        JFrame frame = new JFrame("Snake Game");
        frame.setVisible(true);
        frame.setSize(width_board , height_board);
        frame.setLocationRelativeTo(null);
        frame.setResizable(false);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        SnakeGame snakeGame = new SnakeGame(width_board , height_board);
        frame.add(snakeGame);
        frame.pack();
    }
}
