
// View

import java.awt.*;
import javax.swing.*;
import java.awt.geom.AffineTransform;
import java.awt.image.BufferedImage;
import javax.swing.ImageIcon;

public class Tile extends JButton{

    private boolean amIRotated=false;
    int xCoord, yCoord;
    
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
        return amIRotated;
    }
    public void setRotation(boolean r){
        amIRotated=r;
    }

    public void rotateIcon(Piece p) {
        Icon icon = getIcon();
        if (icon instanceof ImageIcon) {
            ImageIcon imageIcon = (ImageIcon) icon;
            Image image = imageIcon.getImage();
            Image rotatedImage = rotateImage(image, 180); // Rotate by 180 degrees
            setIcon(new ImageIcon(rotatedImage));
        }
        // System.out.println("Rotated " + p + " at " + p.getPosX() + ", " + p.getPosY());
    }
    public void rotateIcon90(Piece p) {
        Icon icon = getIcon();
        if (icon instanceof ImageIcon) {
            ImageIcon imageIcon = (ImageIcon) icon;
            Image image = imageIcon.getImage();
            Image rotatedImage = rotateImage(image, 90); // Rotate by 180 degrees
            setIcon(new ImageIcon(rotatedImage));
        }
        // System.out.println("Rotated " + p + " at " + p.getPosX() + ", " + p.getPosY());
    }

    private Image rotateImage(Image image, int degrees) {
        int width = image.getWidth(null);
        int height = image.getHeight(null);

        BufferedImage rotatedImage = new BufferedImage(width, height, BufferedImage.TYPE_INT_ARGB);
        Graphics2D g2d = rotatedImage.createGraphics();

        AffineTransform at = new AffineTransform();
        at.rotate(Math.toRadians(degrees), width / 2, height / 2);
        g2d.setTransform(at);

        g2d.drawImage(image, 0, 0, null);
        g2d.dispose();

        return rotatedImage;
    }

    public void setCoordinates(int x, int y) {
        xCoord = x;
        yCoord = y;
    }
}
