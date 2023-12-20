import java.awt.*;

import javax.swing.ImageIcon;

public class PieceIcon {
    private Piece piece;
    private ImageIcon pieceImage;
    public static PieceIcon[][] piecesIcons = new PieceIcon[6][7];


    public static ImageIcon getImage(String path){
        ImageIcon pieceImage = new ImageIcon(path);
        Image image = pieceImage.getImage();
        Image newimg = image.getScaledInstance(100,100,java.awt.Image.SCALE_SMOOTH);
        pieceImage = new ImageIcon(newimg);

        return pieceImage;
    }

    public void setIconAtPosition(int x, int y){

        Tile.tiles[x][y].setIcon(null);
        Tile.tiles[piece.getPosX()][piece.getPosY()].setIcon(pieceImage);
    }

    public Piece getPiece(){
        return this.piece;
    }

    public PieceIcon(Piece p, ImageIcon img){
        this.piece=p;
        this.pieceImage=img;

        Tile.tiles[p.getPosX()][p.getPosY()].setIcon(img);
        piecesIcons[p.getPosX()][p.getPosY()]=this;
    }
}
