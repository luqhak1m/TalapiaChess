
// The Facade class. The goal is to provide high level methods that's made out of methods from different controllers.
// Used in: ClickHandler.java, CreatePiece.java, CurrentState.java, Gameplay.java, IconHandler.java, PieceMovement.java, Rotation.java and SwapPiece.java.
// Authors: Asyrani, Haiqal, Luqman

public class GameControl{
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

    // Constructor
    // Written by: Luqman
    public GameControl(){
            this.clickHandlerController=new ClickHandler(this);
            this.gameplayController=new Gameplay();
            this.pieceMovementController=new PieceMovement(this);
            this.rotationController=new Rotation(this);
            
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

    // Display main menu.
    // Written by: Luqman
    public void displayMainMenu(){
            mainMenu.displayMainMenu();
    }

    // Initialize the resources needed to play the game.
    // Written by: Luqman
    public void startGame(){
            iconHandlerController.mapIcon();
            clickHandlerController.addListener();
            createPieceController.instantiatePieces();
            rotate();
    }

    // End the game by resetting the board and pieces.
    // Written by: Luqman
    public void endGame(){
        resetGame();
        createPieceController.instantiatePieces();
    }

    // Load the latest saved state and putting everything in place.
    // Written by: Luqman
    public void loadGame(){

        resetGame();

        currentStateController.loadState();

        gameplayController.setTurnNumber(currentStateController.getTurnCountState());
        gameplayController.setWhoseTurn(currentStateController.getWhoseTurnState());

        
        for (int i = 0; i < Board.row; i++) {
            for (int j = 0; j < Board.column; j++) {
                PieceState pieceState=currentStateController.getPiecePositionsState()[i][j];
                if(pieceState!=null){
                    createPieceController.initializePiece(pieceState.getPieceType(), pieceState.getPosX(), pieceState.getPosY(), pieceState.getStatus(), pieceState.getSide());
                }else{
                    currentStateController.setPiecesPositionStateNull(i, j);;
                }
            }
        }
        
        gameplayController.updateBoardRotationStatus();
        rotate();
        rotationController.flipBoard();
        board.displayBoard();
    }

    // Reset the game state.
    // Written by: Haiqal
    public void resetGame() {

        gameplayController.setTurnNumber(0);
        gameplayController.setWhoseTurn(gameplayController.getSideA());
        gameplayController.setSelectedPiece(null);
        board.setRotationStatus(false);
        gameplayController.updateBoardRotationStatus();
        rotationController.flipBoard();

        // Clear the pieces
        for (int i = 0; i < Board.row; i++) {
            for (int j = 0; j < Board.column; j++) {
                if(Piece.piecePositions[i][j]!=null){
                    pieceMovementController.removePieceFromTile(Piece.piecePositions[i][j]);
                    Piece.piecePositions[i][j] = null;
                }
            }
        }
    }

    // Update necessary information every new turn.
    // Written by: Luqman
    public void newTurn(){
        gameplayController.updateTurn();
        gameplayController.updateBoardRotationStatus();
        if(gameplayController.getTurnNumber()%swapPieceController.getSwapTurn()==0){
            swapPieceController.swapPieces();
        }
        rotationController.flipBoard();
        rotate();
        
    }

    // Automatically sets the icon on the board based on the pieces position.
    // Written by: Luqman
    public void setPieceAtTile(Piece p){
        Piece.piecePositions[p.getPosX()][p.getPosY()]=p;
        Tile tile=Tile.tiles[p.getPosX()][p.getPosY()];

        tile.setDefaultImg((IconHandler.getIconMap().get(p.getClass()).getIconImg(p.getSide())));
        tile.setRotatedImg(tile.getIconImageIconType(rotationController.rotateImage(tile.getIconImageType(), 180)));

        rotationController.checkRotation(p, gameplayController.getWhoseTurn(), p.getPosX(), p.getPosY());
        tile.setIconAtTile();
    }  

    // Replace the current piece with its counterpart during swapping.
    // Written by: Luqman
    public void recreatePiece(Piece currentPiece){

        char currentPieceSide=currentPiece.getSide();    

        pieceMovementController.removePieceFromTile(currentPiece);
        createPieceController.initializePiece(swapPieceController.getSwapMap().get(currentPiece.getClass()).getSimpleName(), currentPiece.getPosX(), currentPiece.getPosY(), 'A', currentPieceSide);
    }

    // Check each piece's rotation status and rotate the piece based on the rotation status.
    // Written by: Luqman
    public void rotate(){
        
        for(int i=0; i<Board.row; i++){
            for(int j=0; j<Board.column; j++){
                Piece piece=Piece.piecePositions[i][j];
                char c=gameplayController.getWhoseTurn();

                if(piece!=null){
                    rotationController.checkRotation(piece, c, i, j);
                    rotationController.rotateIcon(Tile.tiles[i][j]);
                }
            }
        }
    }
    
    // For each clicking, this method will be executed.
    // Written by: Luqman
    public void clickTile(int x, int y){ // this int x, int y is the destination X and Y

        System.out.println("\n+-+-+-+-+-+-+\nTile " + x + ", " + y + " clicked!");
        System.out.println("Turn number " + gameplayController.getTurnNumber() + "\n");

         // If no selected piece then select the clicked piece.
        if(gameplayController.getSelectedPiece()==null){
            // Check if the click on the piece is a valid one (correct turn, etc)
            gameplayController.verifyValidTurn(x, y);
            gameplayController.printSelectedPiece();
            return;
        }
        
        // If click at the same tile when already selecting a piece it will deselect.
        if(gameplayController.getSelectedPiece().getPosX()==x&&gameplayController.getSelectedPiece().getPosY()==y){
            board.displayMessage("Piece deselected!");
            gameplayController.setSelectedPiece(null);
            gameplayController.printSelectedPiece();
            return;
        }

        // Move the piece.
        pieceMovementController.movePieces(x, y, gameplayController.getSelectedPiece());
    }

    // Resize the board.
    // Written by: Asyrani
    public void resizeBoard(){
        if (Board.getBoard().getBoardPanel().getSize() != Board.getBoard().getOriginalSize()) {
            rotationController.resizeToOriginal();
        }
    }

    // To get the saveGame() method from the CurrentState controller.
    // Written by: Haiqal
    public void saveGame(){
        currentStateController.saveGame(gameplayController.getTurnNumber(), gameplayController.getWhoseTurn(), currentStateController.getPiecePositionsState());
    }

    // To get the saveState() method from the CurrentState controller.
    // Written by: Haiqal
    public void saveState(){
        currentStateController.saveState(gameplayController.getTurnNumber(), gameplayController.getWhoseTurn());
    }

    // To set the selected piece.
    // Written by: Luqman
    public void setSelectedPiece(Piece p){
        gameplayController.setSelectedPiece(p);
    }
}