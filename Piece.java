
// Model class. Holds all the necessary information for each piece's subclasses.
// Author: Sabrina

public abstract class Piece{

    private int posX, posY;
    private char side, status;

    // All pieces are stored here.
    public static Piece piecePositions[][]=new Piece[Board.row][Board.column];

    // Constructor.
    // Written by: Sabrina
    public Piece(int x, int y, char st, char si){

        this.posX=x;
        this.posY=y;
        this.status=st;
        this.side=si;

        piecePositions[x][y]=this;
    }

    // Getters and Setters
    // Written by: Sabrina
    public int getPosX(){
        return this.posX;
    }
    public int getPosY(){
        return this.posY;
    }
    public char getStatus(){
        return status;
    }
    public char getSide(){
        return side;
    }
    public void setPosXY(int x, int y){
        this.posX=x;
        this.posY=y;
    }
    public void setSide(char s){
        this.side=s;
    }
    public void setStatus(char s){
        this.status=s;
    }
    public void setStatusDead(){
        this.status='D';
    }

    // Check if the piece's movement is valid.
    // Written by: Sabrina
    public abstract boolean validMove(int newX, int newY);

    // Check if the piece's path have any other piece.
    // Written by: Sabrina
    public abstract boolean skipPiece(int newX, int newY);
}
