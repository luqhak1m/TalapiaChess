import javax.swing.JFrame;
import javax.swing.*;
import java.awt.*;
import java.awt.event.*; 
import java.util.Arrays;
import java.util.HashMap;
import java.util.Random;

public class Board extends JFrame{

    private JButton[][] tiles = new JButton[6][7];
    JPanel gameBoard = new JPanel(new GridLayout (6,0));

    public Board(){

        for(int i=0; i<6; i++){
            for(int j=0; j<7; j++){

                    String xS= String.valueOf(i) + ", " + String.valueOf(j);
                    tiles[i][j] = new JButton(xS);
                    tiles[i][j].setBackground(new Color(139,68,19));
                    tiles[i][j].setSize(200, 200);                    
                    gameBoard.add(tiles[i][j]);
            }
        }

        add(gameBoard, BorderLayout.CENTER);
        setSize(600,600);
        setVisible(true);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
    }

    public JButton getTiles(int x, int y){
        return tiles[x][y];
    }

    public static void main(String[] args) {
        Piece.putImg();
        Board board=new Board();

        PointPiece[] pieces=new PointPiece[14];

        PlusPiece yellowPlus1=new PlusPiece(board, 5, 0);
        PlusPiece bluePlus1=new PlusPiece(board, 0, 0);
        PlusPiece yellowPlus2=new PlusPiece(board, 5, 6);
        PlusPiece bluePlus2=new PlusPiece(board, 0, 6);

        HourglassPiece yellowHourglass1=new HourglassPiece(board, 5, 1);
        HourglassPiece blueHourglass1=new HourglassPiece(board, 0, 1);
        HourglassPiece yellowHourglass2=new HourglassPiece(board, 5, 5);
        HourglassPiece blueHourglass2=new HourglassPiece(board, 0, 5);
        
        TimePiece yellowTime1=new TimePiece(board, 5, 2);
        TimePiece blueTime1=new TimePiece(board, 0, 2);
        TimePiece yellowTime2=new TimePiece(board, 5, 4);
        TimePiece blueTime2=new TimePiece(board, 0, 4);

        SunPiece yellowSun=new SunPiece(board, 5, 3);
        SunPiece blueSun=new SunPiece(board, 0, 3);

        for(int row=1; row<5; row=row+3){
            for(int column=0; column<7; column++){
                if(row==1){
                    PointPiece yellowPoint=new PointPiece(board, row, column);
                }else{
                    PointPiece bluePoint=new PointPiece(board, row, column);   
                }
            }
        }
    
    }
}
