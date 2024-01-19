
// View

import java.awt.*;
import javax.swing.*;
import java.awt.event.*;

public class Tile extends JButton{

    int xCoord, yCoord;
    
    public static Tile[][] tiles = new Tile[6][7];

    public Tile(int x, int y, GameControl c){
        setBackground(new Color(255,255,255));
        setSize(200, 200);  
        this.xCoord=x;
        this.yCoord=y;     
        this.addMouseListener(new MouseAdapter(){
            public void mouseClicked(MouseEvent e){
                c.clickTile(xCoord, yCoord);
            }
        });          
    }

    public static int getxCoord(int x, int y){
        return tiles[x][y].xCoord;
    }
    public static int getyCoord(int x, int y){
        return Tile.tiles[x][y].yCoord;
    }
}
