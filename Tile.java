import java.awt.*;
import javax.swing.*;
import java.awt.event.*;

public class Tile extends JButton{

    int xCoord, yCoord;
    
    public static Tile[][] tiles = new Tile[6][7];

    public Tile(int x, int y, GameControl c){
        setBackground(new Color(139,68,19));
        setSize(200, 200);  
        this.xCoord=x;
        this.yCoord=y;     
        this.addMouseListener(new MouseAdapter(){
            public void mouseClicked(MouseEvent e){
                c.clickTile(xCoord, yCoord);
            }
        });          
    }
}
