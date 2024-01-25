public class Gameplay {

    private GameControl gameControl;

    private int turnCount=0;
    private char sideA='Y', sideB='B';
    private char whoseTurn=sideA;

    public Gameplay(GameControl gameControl){
        this.gameControl=gameControl;
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

    

    public int getTurnNumber(){
        return turnCount;
    }
    public void setTurnNumber(int n){
        turnCount=n;
    }

    public char getWhoseTurn(){
        return whoseTurn;
    }
    public void setWhoseTurn(char c){
        whoseTurn=c;
    }

    public char getSideA(){
        return sideA;
    }
    public char getSideB(){
        return sideB;
    }

    public void resetGame() {
        setTurnNumber(0);
        setWhoseTurn(getSideA());
        Piece.selectedPiece = null;
        Board.getBoard().setRotationStatus(false);

        // Clear the board
        for (int i = 0; i < Board.row; i++) {
            for (int j = 0; j < Board.column; j++) {
                if(Piece.piecePositions[i][j]!=null){
                    gameControl.removePieceFromTile(Piece.piecePositions[i][j]);
                    Piece.piecePositions[i][j] = null;
                }
            }
        }
    }
}
