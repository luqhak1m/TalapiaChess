
// View

import java.awt.*;
import javax.swing.*;
import java.awt.geom.AffineTransform;
import java.awt.image.BufferedImage;

public class Tile extends JButton{

    int xCoord, yCoord;
    private boolean rotate=false;
    private ImageIcon defaultImage, rotatedImage;
    private int defaultWidth=545, defaultHeight=633;
    
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

    public void rotateIcon() {
        
        // int newWidth = Tile.tiles[0][0].getWidth();
        // int newHeight = Tile.tiles[0][0].getHeight();

        if(rotate){
            Icon icon = defaultImage;
            if (icon instanceof ImageIcon) {
                ImageIcon imageIcon = (ImageIcon) icon;
                Image image = imageIcon.getImage();
                Image rotatedImage = rotateImage(image, 180); // Rotate by 180 degrees
                // if (newWidth >= 35 && newWidth <= 64){
                //     Image resizedImage=rotatedImage.getScaledInstance(25, 25, java.awt.Image.SCALE_SMOOTH);
                //     setRotatedImg(new ImageIcon(resizedImage));
                // }
                // else if (newWidth > 64 && newWidth <= 92){
                //     Image resizedImage=rotatedImage.getScaledInstance(65, 65, java.awt.Image.SCALE_SMOOTH);
                //     setRotatedImg(new ImageIcon(resizedImage));
                // }
                // else if (newWidth > 92 && newWidth <= 121){
                //     Image resizedImage=rotatedImage.getScaledInstance(95, 95, java.awt.Image.SCALE_SMOOTH);
                //     setRotatedImg(new ImageIcon(resizedImage));
                // }
                // else if (newWidth > 121 && newWidth < 200){
                //     Image resizedImage=rotatedImage.getScaledInstance(130, 130, java.awt.Image.SCALE_SMOOTH);
                //     setRotatedImg(new ImageIcon(resizedImage));
                // }
                // else if (newWidth > 200 && newHeight > 100){
                //     Image resizedImage=rotatedImage.getScaledInstance(175, 175, java.awt.Image.SCALE_SMOOTH);
                //     setRotatedImg(new ImageIcon(resizedImage));
                // }

                Image resizedImage=rotatedImage.getScaledInstance(130, 130, java.awt.Image.SCALE_SMOOTH);
                setRotatedImg(new ImageIcon(resizedImage));
                this.setRotatedIconAtTile();
                
            }
        }
        else {
            // if (icon instanceof ImageIcon) {
            //     ImageIcon imageIcon = (ImageIcon) icon;
            //     Image image = imageIcon.getImage();
            //     if (newWidth >= 35 && newWidth <= 64){
            //         Image resizedImage=image.getScaledInstance(25, 25, java.awt.Image.SCALE_SMOOTH);
            //         setDefaultImg(new ImageIcon(resizedImage));
            //     }
            //     else if (newWidth > 64 && newWidth <= 92){
            //         Image resizedImage=image.getScaledInstance(65, 65, java.awt.Image.SCALE_SMOOTH);
            //         setDefaultImg(new ImageIcon(resizedImage));
            //     }
            //     else if (newWidth > 92 && newWidth <= 121){
            //         Image resizedImage=image.getScaledInstance(95, 95, java.awt.Image.SCALE_SMOOTH);
            //         setDefaultImg(new ImageIcon(resizedImage));
            //     }
            //     else if (newWidth > 121 && newWidth < 200){
            //         Image resizedImage=image.getScaledInstance(130, 130, java.awt.Image.SCALE_SMOOTH);
            //         setDefaultImg(new ImageIcon(resizedImage));
            //     }
            //     else if (newWidth > 200 && newHeight > 100){
            //         Image resizedImage=image.getScaledInstance(175, 175, java.awt.Image.SCALE_SMOOTH);
            //         setDefaultImg(new ImageIcon(resizedImage));
            //     }
            // }
            
            this.setIconAtTile();
        }
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


    public void setCoordinates(int x, int y) {
        xCoord = x;
        yCoord = y;
    }

    public void setRotationStatus(boolean r){
        rotate=r;
    }
}
