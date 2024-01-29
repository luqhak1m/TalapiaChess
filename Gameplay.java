
// Stores every attributes and methods during the gameplay.
// Used in: GameControl.java
// Author: Luqman

public class Gameplay {

    private int turnCount=0;
    private char sideA='Y', sideB='B';
    private char whoseTurn=sideA;
    private Piece selectedPiece=null;

    // Constructor.
    // Written by: Luqman
    public Gameplay(){}
    
    // Getters and Setters.
    // Written by: Luqman
    public void setWhoseTurn(char c){
        whoseTurn=c;
    }
    public void setTurnNumber(int n){
        turnCount=n;
    }
    public void setSelectedPiece(Piece p){
        selectedPiece=p;
    }
    public int getTurnNumber(){
        return turnCount;
    }
    public char getWhoseTurn(){
        return whoseTurn;
    }
    public char getSideA(){
        return sideA;
    }
    public char getSideB(){
        return sideB;
    }
    public Piece getSelectedPiece(){
        return selectedPiece;
    }

    // Update turn count and change the side that will move on the next turn.
    // Written by: Luqman
    public void updateTurn(){

        if(whoseTurn==sideA){
            setWhoseTurn(sideB);
        }else{
            setWhoseTurn(sideA);
        }
        setTurnNumber(turnCount+=1);
    }

    // Check if the board should rotate on current turn.
    // Written by: Luqman
    public void updateBoardRotationStatus(){
        if(turnCount%2==0){
            Board.getBoard().setRotationStatus(true);
        }else{
            Board.getBoard().setRotationStatus(false);
        }
    }

    // Logging and debugging.
    // Written by: Luqman
    public void printSelectedPiece(){
        System.out.println("Current selected piece is: " + selectedPiece);
    }

    // Check if clicked piece's side is equal to the whoseTurn.
    // Written by: Luqman
    public boolean checkValidTurn(Piece p){
        if(p.getSide()==getWhoseTurn()){
            return true;
        }else{return false;}
    }

    // Check if clicked tile is empty and if the clicked piece can be selected.
    // Written by: Luqman
    public void verifyValidTurn(int x, int y){
        if(Piece.piecePositions[x][y]==null){
            Board.getBoard().displayMessage("Empty Tile!");;
        }else if(checkValidTurn(Piece.piecePositions[x][y])){
            selectedPiece=Piece.piecePositions[x][y];
        }else{
            Board.getBoard().displayMessage("Not your turn!");}
    }
}
