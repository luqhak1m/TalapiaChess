
// View
// Each tile exists inside the board.
// Authors: Aisyah, Luqman

import java.awt.*;
import javax.swing.*;
import javax.swing.ImageIcon;

public class Tile extends JButton{

    private int xCoord, yCoord;
    private boolean rotate=false;
    private ImageIcon defaultImage, rotatedImage;
    private int defaultWidth=100, defaultHeight=100;
    
    public static Tile[][] tiles = new Tile[Board.row][Board.column];

    // Constructor.
    // Written by: Luqman
    public Tile(int x, int y){
        setBackground(new Color(255,255,255));
        setSize(200, 200);  
        this.xCoord=x;
        this.yCoord=y;              
    }

    // Set XY coordinate for the tile.
    // Written by: Aisyah
    public void setCoordinates(int x, int y) {
        xCoord = x;
        yCoord = y;
    }

    // Getters and Setters.
    // Written by: Luqman
    public void setDefaultImg(ImageIcon img){
        defaultImage=img;
    }
    public void setRotatedImg(ImageIcon img){
        rotatedImage=img;
    }
    public void setTileRotationStatus(boolean r){
        rotate=r;
    }
    public void setIconAtTile(){
        if(defaultImage!=null&&rotatedImage!=null){
            if(this.rotate){
                Image resizedImage = getRotatedIconImageType().getScaledInstance(this.getWidth()/2, this.getHeight()/2, java.awt.Image.SCALE_SMOOTH);
                this.setIcon(getIconImageIconType(resizedImage));
            }else{
                Image resizedImage = getIconImageType().getScaledInstance(this.getWidth()/2, this.getHeight()/2, java.awt.Image.SCALE_SMOOTH);
                this.setIcon(getIconImageIconType(resizedImage));
            }
        }else{
            this.setIcon(null);
        }
    }
    public ImageIcon getDefaultImg(){
        return defaultImage;
    }
    public Image getIconImageType(){
        ImageIcon imageIcon=this.getDefaultImg();
        return imageIcon.getImage();
    }
    public Image getRotatedIconImageType(){
        ImageIcon imageIcon=this.getRotatedImg();
        return imageIcon.getImage();
    }
    public ImageIcon getIconImageIconType(Image img){
        return new ImageIcon(img);
    }
    public ImageIcon getRotatedImg(){
        return rotatedImage;
    }
    public int getxCoord(){
        return this.xCoord;
    }
    public int getyCoord(){
        return this.yCoord;
    }
    public int getxCoord(int x, int y){
        return tiles[x][y].xCoord;
    }
    public int getyCoord(int x, int y){
        return Tile.tiles[x][y].yCoord;
    }
    public int getDefaultHeight(){
        return defaultHeight;
    }
    public int getDefaultWidth(){
        return defaultWidth;
    }
    public boolean getRotationStatus(){
        return rotate;
    }

    // Set image on tile to null.
    // Written by: Luqman
    public void resetImg(){
        defaultImage=null;
        rotatedImage=null;
    }
}
