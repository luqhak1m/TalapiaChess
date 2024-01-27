
// Model

public abstract class Piece{

    private int posX, posY;
    private char side, status;

    public static Piece selectedPiece=null;
    public static Piece piecePositions[][]=new Piece[Board.row][Board.column];

    public Piece(int x, int y, char st, char si){

        this.posX=x;
        this.posY=y;
        this.status=st;
        this.side=si;

        piecePositions[x][y]=this;
    }

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

    public static void clearPieces(){
        for(int i=0;i <Board.row; i++){
            for(int j=0; j<Board.column; j++){
                piecePositions[i][j]=null;
            }
        }
    }

    public abstract boolean validMove(int newX, int newY);
    public abstract boolean skipPiece(int newX, int newY); // will check for piece skip in the validmove
}
