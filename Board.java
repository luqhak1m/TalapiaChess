import javax.swing.JFrame;
import javax.swing.*;
import java.awt.*;
import java.awt.event.*; 
import java.util.Arrays;
import java.util.HashMap;
import java.util.Random;

public class Board extends JFrame{

    JPanel gameBoard = new JPanel(new GridLayout (6,7));

    public Board(){

        for(int i=0; i<6; i++){
            for(int j=0; j<7; j++){
                    String xS= String.valueOf(i) + ", " + String.valueOf(j);
                    Tile.tiles[i][j] = new Tile(xS, i, j);
                    gameBoard.add(Tile.tiles[i][j]);
            }
        }

        add(gameBoard, BorderLayout.CENTER);
        setSize(600,600);
        setVisible(true);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
    }
}
