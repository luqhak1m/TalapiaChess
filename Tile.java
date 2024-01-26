
// View

import java.awt.*;
import javax.swing.*;
import java.awt.geom.AffineTransform;
import java.awt.image.BufferedImage;
import javax.swing.ImageIcon;

public class Tile extends JButton{

    int xCoord, yCoord;
    private boolean rotate=false;
    private ImageIcon defaultImage, rotatedImage;
    private int defaultWidth=100, defaultHeight=100;
    
    public static Tile[][] tiles = new Tile[Board.row][Board.column];

    public Tile(int x, int y){
        setBackground(new Color(255,255,255));
        setSize(200, 200);  
        this.xCoord=x;
        this.yCoord=y;              
    }

    public static Tile getTileAtCoordinate(int x, int y){
        for(int i=0; i<Board.row; i++){
            for(int j=0; j<Board.column; j++){
                if(Tile.tiles[i][j].xCoord==x&&Tile.tiles[i][j].yCoord==y){
                    return Tile.tiles[i][j];
                }
            }
        }
        return null;
    }

    public void setDefaultImg(ImageIcon img){
        defaultImage=img;
    }
    public void setRotatedImg(ImageIcon img){
        rotatedImage=img;
    }

    public ImageIcon getDefaultImg(){
        return defaultImage;
    }

    public void setIconAtTile(){
        this.setIcon(defaultImage);
    }
    public void setRotatedIconAtTile(){
        this.setIcon(rotatedImage);
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
    public boolean getRotationStatus(){
        return rotate;
    }

    public void setCoordinates(int x, int y) {
        xCoord = x;
        yCoord = y;
    }

    public void setTileRotationStatus(boolean r){
        rotate=r;
    }

    public void resizeImages(int width, int height) {
        if (defaultImage != null) {
            Image image = defaultImage.getImage();
            Image resizedImage = image.getScaledInstance(width, height, java.awt.Image.SCALE_SMOOTH);
            setDefaultImg(new ImageIcon(resizedImage));
            setIconAtTile();
        }

        if (rotatedImage != null) {
            Image image = rotatedImage.getImage();
            Image resizedImage = image.getScaledInstance(width, height, java.awt.Image.SCALE_SMOOTH);
            setRotatedImg(new ImageIcon(resizedImage));
            setRotatedIconAtTile();
        }
    }
}
