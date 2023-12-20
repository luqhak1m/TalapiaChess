import java.awt.*;
import javax.swing.*;

public class Tile extends JButton{

    int xCoord, yCoord;
    
    static Tile[][] tiles = new Tile[6][7];

    public Tile(String s, int x, int y){
        super(s);
        setBackground(new Color(139,68,19));
        setSize(200, 200);  
        this.xCoord=x;
        this.yCoord=y;               
    }
}
