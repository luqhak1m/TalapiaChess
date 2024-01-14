
// View

import java.awt.*;

import javax.swing.ImageIcon;

public class PieceIcon {
    private Class<? extends Piece> pieceClass;
    private ImageIcon yellowImage, blueImage;

    public static ImageIcon getImage(String path){
        ImageIcon pieceImage = new ImageIcon(path);
        Image image = pieceImage.getImage();
        Image newimg = image.getScaledInstance(100,100,java.awt.Image.SCALE_SMOOTH);
        pieceImage = new ImageIcon(newimg);

        return pieceImage;
    }

    public Class<? extends Piece> getPiece(){
        return this.pieceClass;
    }
    public ImageIcon getYellowImg(){
        return this.yellowImage;
    }
    public ImageIcon getBlueImg(){
        return this.blueImage;
    }
    
    public ImageIcon getIconImg(char side){
        if(side=='Y'){
            return this.yellowImage;
        }
        return this.blueImage;
    }


    public PieceIcon(Class<? extends Piece> p, ImageIcon yImg, ImageIcon bImg){
        this.pieceClass=p;
        this.yellowImage=yImg;
        this.blueImage=bImg;
    }
}
