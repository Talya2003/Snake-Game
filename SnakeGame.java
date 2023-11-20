import java.awt.*;
import java.awt.event.*;
import java.util.ArrayList;
import java.util.Random;
import javax.swing.*;

public class SnakeGame extends JPanel{
    int width_board;
    int height_board;

    SnakeGame(int width_board , int height_board) {
        this.width_board = width_board;
        this.height_board = height_board;
        setPreferredSize(new Dimension(this.width_board , this.height_board));
        setBackground(Color.BLACK);
    }


}
