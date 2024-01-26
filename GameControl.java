
import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import javax.swing.JOptionPane;

// Controller

public class GameControl {
    private ClickHandler clickHandlerController;
    private CreatePiece createPieceController;
    private Gameplay gameplayController;
    private PieceMovement pieceMovementController;
    private Rotation rotationController;

    private CurrentState currentStateController;
    private IconHandler iconHandlerController;
    private SwapPiece swapPieceController;
    private Board board;
    private MainMenu mainMenu;

    public GameControl(){
            this.clickHandlerController=new ClickHandler(this);
            this.gameplayController=new Gameplay();
            this.pieceMovementController=new PieceMovement(this);
            this.rotationController=new Rotation();
            
            this.currentStateController=CurrentState.getCurrentStateController();
            this.createPieceController=CreatePiece.getCreatePieceController();
            createPieceController.setGameControl(this);

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
            iconHandlerController.mapIcon();
            clickHandlerController.addListener();
            createPieceController.instantiatePieces();
            rotate();
       }

    public void saveState(){
        currentStateController.setTurnCountState(gameplayController.getTurnNumber());
        currentStateController.setWhoseTurnState(gameplayController.getWhoseTurn());

        for(int i=0; i<Board.row; i++){
            for(int j=0; j<Board.column; j++){
                Piece piece=Piece.piecePositions[i][j];
                if(piece!=null){
                    PieceState pieceState=new PieceState(piece.getClass().getSimpleName(), piece.getPosX(), piece.getPosY(), piece.getStatus(), piece.getSide());
                    currentStateController.setPiecesPositionState(pieceState);
                }
            }
        }

    }

    public void loadGame(){
        // Reset the board and variables
        resetGame();

        currentStateController.loadState();

        gameplayController.setTurnNumber(currentStateController.getTurnCountState());
        gameplayController.setWhoseTurn(currentStateController.getWhoseTurnState());

        for (int i = 0; i < Board.row; i++) {
            for (int j = 0; j < Board.column; j++) {
                PieceState pieceState=currentStateController.getPiecePositionsState()[i][j];
                if(pieceState!=null){
                    initializePiece(pieceState.getPieceType(), pieceState.getPosX(), pieceState.getPosY(), pieceState.getStatus(), pieceState.getSide());
                }
            }
        }

        gameplayController.updateDisplay();
        rotate();
        board.displayBoard();
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
                    PieceState pieceState = currentState.getPiecePositionsState()[i][j];
                    if (pieceState != null) {
                        // Example format: "Piece: PlusPiece, X: 2, Y: 3, Status: A, Side: Y\n"
                        writer.write("Piece: " + pieceState.getPieceType() +
                                ", X: " + pieceState.getPosX() +
                                ", Y: " + pieceState.getPosY() +
                                ", Status: " + pieceState.getStatus() +
                                ", Side: " + pieceState.getSide());
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
    
    public void resetGame() {
        gameplayController.setTurnNumber(0);
        gameplayController.setWhoseTurn(gameplayController.getSideA());
        Piece.selectedPiece = null;
        board.setRotationStatus(false);

        // Clear the board
        for (int i = 0; i < Board.row; i++) {
            for (int j = 0; j < Board.column; j++) {
                if(Piece.piecePositions[i][j]!=null){
                    pieceMovementController.removePieceFromTile(Piece.piecePositions[i][j]);
                    Piece.piecePositions[i][j] = null;
                }
            }
        }
    }

    public void newTurn(){
        gameplayController.updateTurn();
        gameplayController.updateDisplay();
        if(gameplayController.getTurnNumber()%swapPieceController.getSwapTurn()==0){
            swapPieceController.swapPieces();
        }
        rotate();
    }

    public void initializePiece(String pieceType, int x, int y, char status, char side) {
        pieceMovementController.setPieceAtTile(createPieceController.createPiece(pieceType, x, y, status, side));
    }

    public void repositionPiece(Piece currentPiece){

        char currentPieceSide=currentPiece.getSide();    

        pieceMovementController.removePieceFromTile(currentPiece);
        initializePiece(swapPieceController.getSwapMap().get(currentPiece.getClass()).getSimpleName(), currentPiece.getPosX(), currentPiece.getPosY(), 'A', currentPieceSide);
    }

    public void rotate(){
        
        for(int i=0; i<Board.row; i++){
            for(int j=0; j<Board.column; j++){
                Piece piece=Piece.piecePositions[i][j];

                if(piece!=null){
                    
                    if(piece.getSide()!=gameplayController.getWhoseTurn()){ // if it's not the pieces' turn, rotate the icon
                        Tile.tiles[i][j].setTileRotationStatus(true);
                    }else{
                        Tile.tiles[i][j].setTileRotationStatus(false);
                    }

                    rotationController.setPointPieceRotation(piece);
                }
            }
        }

        rotationController.rotateThePiece();
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
}
