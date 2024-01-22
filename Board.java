
// View

import javax.swing.JFrame;
import javax.swing.*;
import java.awt.*;

public class Board extends JFrame{

    JPanel gameBoard = new JPanel(new GridLayout (6,7));

    public Board(){

        for(int i=0; i<6; i++){
            for(int j=0; j<7; j++){
                    Tile.tiles[i][j] = new Tile(i, j);
                    gameBoard.add(Tile.tiles[i][j]);
            }
        }

        add(gameBoard, BorderLayout.CENTER);
        setSize(600,600);
        setVisible(true);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
    }
}
