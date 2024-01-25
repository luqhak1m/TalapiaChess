
import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.awt.event.*;
import java.io.BufferedReader;
import java.io.FileReader;
import javax.swing.JOptionPane;

// Controller

public class GameControl {
       private ClickHandler clickHandlerController;
       private CreatePiece createPieceController;
       private Gameplay gameplayController;
       private PieceMovement pieceMovementController;

       private CurrentState currentStateController;
       private IconHandler iconHandlerController;
       private SwapPiece swapPieceController;
       private Board board;
       private MainMenu mainMenu;

        

        public GameControl(){
            this.clickHandlerController=new ClickHandler(this);
            this.createPieceController=new CreatePiece(this);
            this.gameplayController=new Gameplay(this);
            this.pieceMovementController=new PieceMovement(this);
            
            this.currentStateController=CurrentState.getCurrentStateController();
            this.iconHandlerController=IconHandler.getIconHandlerController();

            this.swapPieceController=SwapPiece.getSwapPieceController();
            swapPieceController.setGameControl(this);

            this.board=Board.getBoard();
            this.mainMenu=MainMenu.getMainMenu();

            startGame();
       }

       public void displayMainMenu(){
            mainMenu.displayMainMenu();
       }

       public void startGame(){
            gameplayController.resetGame();
            clickHandlerController.addListener();
            createPieceController.instantiatePieces();
       }

       public void saveState(){
        currentStateController.setTurnCountState(gameplayController.getTurnNumber());
        currentStateController.setWhoseTurnState(gameplayController.getWhoseTurn());
        currentStateController.setPiecesPositionState(Piece.piecePositions);
    }

       public void loadGame(){
        // Reset the board and variables
        gameplayController.resetGame();

        currentStateController.loadGame();

        gameplayController.setTurnNumber(currentStateController.getTurnCountState());
        gameplayController.setWhoseTurn(currentStateController.getWhoseTurnState());

        for (int i = 0; i < Board.row; i++) {
            for (int j = 0; j < Board.column; j++) {

                if(Piece.piecePositions[i][j]!=null){

                    pieceMovementController.setPieceAtTile(Piece.piecePositions[i][j]);

                }
            }
        }

        updateDisplay();
        Board.getBoard().displayBoard();
       }

       public void saveGame(){

        CurrentState currentState=CurrentState.getCurrentStateController();


        try (BufferedWriter writer = new BufferedWriter(new FileWriter(currentState.getPath()))) {
            // Save turn count and whose turn it is
            writer.write("TurnCount: " + currentState.getTurnCountState() + "\n");
            writer.write("WhoseTurn: " + currentState.getWhoseTurnState() + "\n");

            // Save piece information
            for (int i = 0; i < Board.row; i++) {
                for (int j = 0; j < Board.column; j++) {
                    Piece piece = currentState.getPiecePositionsState()[i][j];
                    if (piece != null) {
                        // Example format: "Piece: PlusPiece, X: 2, Y: 3, Status: A, Side: Y\n"
                        writer.write("Piece: " + piece.getClass().getSimpleName() +
                                ", X: " + piece.getPosX() +
                                ", Y: " + piece.getPosY() +
                                ", Status: " + piece.getStatus() +
                                ", Side: " + piece.getSide());
                        writer.newLine();
                    }
                }
            }
            JOptionPane.showMessageDialog(null, "Game saved!");
        } catch (IOException e) {
            e.printStackTrace();
            // JOptionPane.showMessageDialog(null, "Failed to save the game!");
        }
    }

       public void clickTile(int x, int y){ // this int x, int y is the destination X and Y

        System.out.println("\n+-+-+-+-+-+-+\nTile " + x + ", " + y + " clicked!");
        System.out.println("Turn number " + gameplayController.getTurnNumber() + "\n");
        System.out.println("Tile rotation status is " + Tile.tiles[x][y].getRotationStatus());

        if(Piece.selectedPiece==null){ // if no selected piece then select the clicked piece
            gameplayController.verifyValidTurn(x, y);
            gameplayController.printSelectedPiece();
            return;
        }
        
        if(Piece.selectedPiece.getPosX()==x&&Piece.selectedPiece.getPosY()==y){ // if click at same position (deselect)
            System.out.println("Deselect piece");
            gameplayController.selectPiece(null);
            gameplayController.printSelectedPiece();
            return;
        }

        pieceMovementController.movePieces(x, y, Piece.selectedPiece); // move da pieces
        gameplayController.printSelectedPiece();
    }

    // Initialize pieces, its icons and its positions
    public void initializePiece(String pieceType, int x, int y, char status, char side) {
        Piece piece=CreatePiece.createPiece(pieceType, x, y, status, side);
        pieceMovementController.setPieceAtTile(piece);
    }

    public void removePieceFromTile(Piece p){
        Piece.piecePositions[p.getPosX()][p.getPosY()]=null; // set the coordinate/tile to null
        Tile.tiles[p.getPosX()][p.getPosY()].setIcon(null); // set icon at tile to null
        Tile.tiles[p.getPosX()][p.getPosY()].setDefaultImg(null);

    }

    public void setRotationStatus(){
        System.out.println("checking rotation ");

        for(int i=0; i<Board.row; i++){
            for(int j=0; j<Board.column; j++){

                Piece piece=Piece.piecePositions[i][j];

                if(piece!=null){
                    
                    if(piece.getSide()!=gameplayController.getWhoseTurn()){ // if it's not the pieces' turn rotate the icon
                        Tile.tiles[i][j].setRotationStatus(true);
                    }else{
                        Tile.tiles[i][j].setRotationStatus(false);
                    }

                    if(piece instanceof PointPiece){
                        PointPiece pointPiece=(PointPiece) piece;
                        if(pointPiece.getReversedB()||pointPiece.getReversedY()){
                            Tile.getTileAtCoordinate(i, j).setRotationStatus(true);
                        }
                    }
                }
            }
        }
    }

    public void rotateThePiece(){
        for(int i=0; i<Board.row; i++){
            for(int j=0; j<Board.column; j++){
                if(Piece.piecePositions[i][j]!=null && Tile.tiles[i][j].getRotationStatus()){
                    Tile.getTileAtCoordinate(i, j).rotateIcon();
                }
                else if(Piece.piecePositions[i][j]!=null&&!Tile.tiles[i][j].getRotationStatus()){ // if rotate status is false set original image
                    Tile.getTileAtCoordinate(i, j).setIcon(IconHandler.getIconMap().get(Piece.piecePositions[i][j].getClass()).getIconImg(Piece.piecePositions[i][j].getSide()));;
                }
            }
        }
    }

    public void updateDisplay(){

        int turnCount=gameplayController.getTurnNumber();
        
        if(turnCount%2==0){
            if(turnCount%4==0){
                swapPieceController.swapPieces();
            }
            board.setRotationStatus(true);
        }else{
            board.setRotationStatus(false);
        }

        setRotationStatus();

        board.flipBoard();
        rotateThePiece();
    }

    // change side after each turn and check for possible piece swapping
    public void updateTurn(){

        int turnCount=gameplayController.getTurnNumber();
        char whoseTurn=gameplayController.getWhoseTurn();
        char sideA=gameplayController.getSideA();
        char sideB=gameplayController.getSideB();

        if(whoseTurn==sideA){gameplayController.setWhoseTurn(sideB);}else{gameplayController.setWhoseTurn(sideA);}
        gameplayController.setTurnNumber(turnCount+=1);
    }
}
