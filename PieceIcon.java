import java.util.HashMap;
import java.awt.*;

import javax.swing.ImageIcon;

public class PieceIcon {
    private Piece piece;
    private ImageIcon pieceImage;
    private static int rowLength=6, columnLength=7;
    static ImageIcon[][] imagePosition = new ImageIcon[rowLength][columnLength];

    public static ImageIcon getImage(String path){
        ImageIcon pieceImage = new ImageIcon(path);
        Image image = pieceImage.getImage();
        Image newimg = image.getScaledInstance(100,100,java.awt.Image.SCALE_SMOOTH);
        pieceImage = new ImageIcon(newimg);

        return pieceImage;
    }

    public static void setIcons(){

        PieceIcon.imagePosition[0][0]=PieceIcon.getImage("piecesPics/bluePlus.png");
        PieceIcon.imagePosition[0][1]=PieceIcon.getImage("piecesPics/blueHourglass.png");
        PieceIcon.imagePosition[0][2]=PieceIcon.getImage("piecesPics/blueTime.png");
        PieceIcon.imagePosition[0][3]=PieceIcon.getImage("piecesPics/blueSun.png");
        PieceIcon.imagePosition[0][4]=PieceIcon.getImage("piecesPics/blueTime.png");
        PieceIcon.imagePosition[0][5]=PieceIcon.getImage("piecesPics/blueHourglass.png");
        PieceIcon.imagePosition[0][6]=PieceIcon.getImage("piecesPics/bluePlus.png");
        PieceIcon.imagePosition[1][0]=PieceIcon.getImage("piecesPics/blueArrow.png");
        PieceIcon.imagePosition[1][1]=PieceIcon.getImage("piecesPics/blueArrow.png");
        PieceIcon.imagePosition[1][2]=PieceIcon.getImage("piecesPics/blueArrow.png");
        PieceIcon.imagePosition[1][3]=PieceIcon.getImage("piecesPics/blueArrow.png");
        PieceIcon.imagePosition[1][4]=PieceIcon.getImage("piecesPics/blueArrow.png");
        PieceIcon.imagePosition[1][5]=PieceIcon.getImage("piecesPics/blueArrow.png");
        PieceIcon.imagePosition[1][6]=PieceIcon.getImage("piecesPics/blueArrow.png");
        PieceIcon.imagePosition[5][0]=PieceIcon.getImage("piecesPics/yellowPlus.png");
        PieceIcon.imagePosition[5][1]=PieceIcon.getImage("piecesPics/yellowHourglass.png");
        PieceIcon.imagePosition[5][2]=PieceIcon.getImage("piecesPics/yellowTime.png");
        PieceIcon.imagePosition[5][3]=PieceIcon.getImage("piecesPics/yellowSun.png");
        PieceIcon.imagePosition[5][4]=PieceIcon.getImage("piecesPics/yellowTime.png");
        PieceIcon.imagePosition[5][5]=PieceIcon.getImage("piecesPics/yellowHourglass.png");
        PieceIcon.imagePosition[5][6]=PieceIcon.getImage("piecesPics/yellowPlus.png");
        PieceIcon.imagePosition[4][0]=PieceIcon.getImage("piecesPics/yellowArrow.png");
        PieceIcon.imagePosition[4][1]=PieceIcon.getImage("piecesPics/yellowArrow.png");
        PieceIcon.imagePosition[4][2]=PieceIcon.getImage("piecesPics/yellowArrow.png");
        PieceIcon.imagePosition[4][3]=PieceIcon.getImage("piecesPics/yellowArrow.png");
        PieceIcon.imagePosition[4][4]=PieceIcon.getImage("piecesPics/yellowArrow.png");
        PieceIcon.imagePosition[4][5]=PieceIcon.getImage("piecesPics/yellowArrow.png");
        PieceIcon.imagePosition[4][6]=PieceIcon.getImage("piecesPics/yellowArrow.png");

        for(int i=0; i<rowLength; i++){
            for(int j=0; j<columnLength; j++){
                if(Piece.piecesPosition[i][j]!=null){
                    Tile.tiles[i][j].setIcon(imagePosition[i][j]);
                }
            }
        }
    }

    public PieceIcon(){}
}
