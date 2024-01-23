
// View

import javax.swing.*;
import java.awt.*;
import java.awt.event.ComponentAdapter;
import java.awt.event.ComponentEvent;

public class Board extends JFrame{

    private JPanel gamePanel;
    private static Board theBoard;
    private Dimension originalSize;
    private int width = 900;
    private int height = 800;

    private Board(){
        super("Talapia Chess");

        gamePanel=new JPanel(new GridLayout (6,7));
        for(int i=0; i<6; i++){
            for(int j=0; j<7; j++){
                    Tile.tiles[i][j] = new Tile(i, j);
                    gamePanel.add(Tile.tiles[i][j]);
            }
        }        

        JPanel sideBar = new JPanel();
        JButton saveGame = new JButton("Save Game");
        JButton exitGame = new JButton("Exit Game");

        sideBar.setLayout(new BoxLayout(sideBar, BoxLayout.Y_AXIS));
        sideBar.add(saveGame);
        sideBar.add(exitGame);
        sideBar.setSize(100, gamePanel.getHeight());
        add(gamePanel, BorderLayout.CENTER);
        add(sideBar, BorderLayout.EAST);
        

        gamePanel.addComponentListener(new ComponentAdapter() {
           @Override
           public void componentResized(ComponentEvent e) {
               if (gamePanel.getSize() != originalSize){
                  resizeToOriginal(); 
               }
           }
        });

        setSize(width,height);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
    }

    public void resizeToOriginal() {
        int x = getWidth();
        int y = getHeight();

        if ((x > 0 && x < 400) || (y > 0 && y < 200)) {
            setSize(400, 200);
        }
        else if ((x > 0 && x < 600) || ( y > 200 && y < 400)) {
            setSize(600, 400);
        }
        else if ((x > 600 && x < 800) || (y > 400 && y < 600)) {
            setSize(800, 600);
        }
        else if ((x > 800 && x < 1550) || (y > 600 && y < 800))  {
            setSize(1000, 800);
        }
    }

    public static Board getBoard(){
        if(theBoard==null){theBoard=new Board();}
        return theBoard;
    }

    public void displayBoard(){
        getBoard().setVisible(true);
    }
}
