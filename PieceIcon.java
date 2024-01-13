
// View

import java.awt.*;

import javax.swing.ImageIcon;

public class PieceIcon {
    private Piece piece;
    private ImageIcon pieceImage;

    public static ImageIcon getImage(String path){
        ImageIcon pieceImage = new ImageIcon(path);
        Image image = pieceImage.getImage();
        Image newimg = image.getScaledInstance(100,100,java.awt.Image.SCALE_SMOOTH);
        pieceImage = new ImageIcon(newimg);

        return pieceImage;
    }

    public Piece getPiece(){
        return this.piece;
    }
    public ImageIcon getImg(){
        return this.pieceImage;
    }

    public PieceIcon(Piece p, ImageIcon img){
        this.piece=p;
        this.pieceImage=img;
    }
}
