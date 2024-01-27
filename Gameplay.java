public class Gameplay {

    private int turnCount=0;
    private char sideA='Y', sideB='B';
    private char whoseTurn=sideA;

    public Gameplay(){}
    
    public void setWhoseTurn(char c){
        whoseTurn=c;
    }
    public void setTurnNumber(int n){
        turnCount=n;
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

    // change side after each turn and check for possible piece swapping
    public void updateTurn(){

        if(whoseTurn==sideA){
            setWhoseTurn(sideB);
        }else{
            setWhoseTurn(sideA);
        }
        setTurnNumber(turnCount+=1);
    }

    public void updateDisplay(){
        if(turnCount%2==0){
            Board.getBoard().setRotationStatus(true);
        }else{
            Board.getBoard().setRotationStatus(false);
        }
    }

    public void selectPiece(Piece p){
        Piece.selectedPiece=p;
    }

    // logging and debugging
    public void printSelectedPiece(){
        System.out.println("Current selected piece is: " + Piece.selectedPiece);
    }

    // if clicked piece's side is equal to the whoseTurn
    public boolean checkValidTurn(Piece p){
        if(p.getSide()==getWhoseTurn()){
            return true;
        }else{return false;}
    }

    // deal with multiple clicking instances
    public void verifyValidTurn(int x, int y){
        if(Piece.piecePositions[x][y]==null){
            System.out.println("Empty Tile");
        }else if(checkValidTurn(Piece.piecePositions[x][y])){
            selectPiece(Piece.piecePositions[x][y]);
        }else{System.out.println("Not your turn");}
    }
}
