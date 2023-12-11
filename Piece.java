
import javax.swing.JFrame;
import javax.swing.*;
import java.awt.*;
import java.util.HashMap;

public abstract class Piece {

    private Board board;
    private int posX, posY;
    private char side;
    static HashMap<Integer, HashMap<Integer, ImageIcon>> imgMap=new HashMap<>();
    

    static void putValues(int x, int y, ImageIcon img){
        imgMap.putIfAbsent(x, new HashMap<>());
        imgMap.get(x).put(y, img);
    }

    static ImageIcon getImage(String path){
        ImageIcon pieceImage = new ImageIcon(path);
        Image image = pieceImage.getImage();
        Image newimg = image.getScaledInstance(100,100,java.awt.Image.SCALE_SMOOTH);
        pieceImage = new ImageIcon(newimg);

        return pieceImage;
    }

    static void putImg(){
        String[] blueFirstRow={"bluePlus.png", "blueHourglass.png", "blueTime.png", "blueSun.png", "blueTime.png", "blueHourglass.png", "bluePlus.png"};
        String[] blueSecRow={"blueArrow.png"};
        String[] yellowFirstRow={"yellowPlus.png", "yellowHourglass.png", "yellowTime.png", "yellowSun.png", "yellowTime.png", "yellowHourglass.png", "yellowPlus.png"};
        String[] yellowSecRow={"yellowArrow.png"};

        for (int column=0; column<7; column++) {
            Piece.putValues(0, column, getImage("piecesPics/" + blueFirstRow[column]));
            Piece.putValues(1, column, getImage("piecesPics/" + blueSecRow[0]));
            Piece.putValues(5, column, getImage("piecesPics/" + yellowFirstRow[column]));
            Piece.putValues(4, column, getImage("piecesPics/" + yellowSecRow[0]));
        }
    }

    public void setImage(int x, int y, ImageIcon img){
        board.getTiles(x, y).setIcon(img);
    }

    public Piece(Board b, int x, int y){
        board=b;
        posX=x;
        posY=y;
        
        if(x==0||x==1){
            side='B';
        }else{
            side='Y';
        }

        this.setImage(x, y, imgMap.get(x).get(y));
    }
}
