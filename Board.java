
// View

import javax.swing.JFrame;
import javax.swing.*;
import java.awt.*;

public class Board extends JFrame{

    private JPanel gamePanel;
    private static Board theBoard;

    private Board(){
        gamePanel=new JPanel(new GridLayout (6,7));
        for(int i=0; i<6; i++){
            for(int j=0; j<7; j++){
                    Tile.tiles[i][j] = new Tile(i, j);
                    gamePanel.add(Tile.tiles[i][j]);
            }
        }

        add(gamePanel, BorderLayout.CENTER);
        setSize(600,600);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
    }

    public static Board getBoard(){
        if(theBoard==null){theBoard=new Board();}
        return theBoard;
    }

    public void displayBoard(){
        getBoard().setVisible(true);
    }
}
