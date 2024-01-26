public class PieceState {

    String pieceType;
    int x;
    int y;
    char status;
    char side;

    public PieceState(String pieceType, int x, int y, char status, char side){
        this.pieceType=pieceType;
        this.x=x;
        this.y=y;
        this.status=status;
        this.side=side;
    }

    public String getPieceType(){
        return pieceType;
    }
    public int getPosX(){
        return x;
    }
    public int getPosY(){
        return y;
    }
    public char getStatus(){
        return status;
    }
    public char getSide(){
        return side;
    }
}
