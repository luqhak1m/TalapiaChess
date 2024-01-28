
// For storing pieces state.
// Used in: CurrentState.java
// Author: Luqman

public class PieceState {

    String pieceType;
    int x;
    int y;
    char status;
    char side;

    // Constructor.
    // Written by: Luqman
    public PieceState(String pieceType, int x, int y, char status, char side){
        this.pieceType=pieceType;
        this.x=x;
        this.y=y;
        this.status=status;
        this.side=side;
    }

    // Getters.
    // Written by: Luqman
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
