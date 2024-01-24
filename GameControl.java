
// Controller

import java.util.HashMap;
import java.util.Map;

import java.awt.event.*;


public class GameControl {
    Board board=Board.getBoard();

    private CurrentState currentState;
    private int turnCount=0;
    private char sideA='Y', sideB='B';
    private char whoseTurn=sideA;
    private Map<Class<? extends Piece>, Class<? extends Piece>> swapPieceMapTest=new HashMap<>();
    private HashMap<Class<? extends Piece>, PieceIcon> pieceIconMap = new HashMap<>();

    public GameControl(){

        for(int i=0; i<Board.row; i++){
            for(int j=0; j<Board.column; j++){
                Tile tile=Tile.tiles[i][j];
                tile.addMouseListener(new MouseAdapter() {
                    public void mouseClicked(MouseEvent e){
                        clickTile(tile.xCoord, tile.yCoord);
                    }
                });
            }
        }

        board.getSaveButton().addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                currentState=new CurrentState("chess_save.txt");
                setState(currentState);
                currentState.saveState();
                // JOptionPane.showMessageDialog( Board.this, "Game saved!");
            }
        });

        board.getSavedFiles().addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if(currentState!=null){

                    System.out.println(currentState.getSavedStates());
                }
                // JOptionPane.showMessageDialog( Board.this, "Game saved!");
            }
        });

        MainMenu.getMainMenu().getPlayButton().addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e){
                MainMenu.getMainMenu().setVisible(false);
                Board.getBoard().displayBoard();
            }
        });

        MainMenu.getMainMenu().getLoadButton().addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e){

                MainMenu.getMainMenu().setVisible(false);


                // Reset the board and variables
                resetGame();

                currentState=new CurrentState("chess_save.txt");
                currentState.loadGame();

                turnCount=currentState.getTurnCountState();
                whoseTurn=currentState.getWhoseTurnState();

                for (int i = 0; i < Board.row; i++) {
                    for (int j = 0; j < Board.column; j++) {

                        if(Piece.piecePositions[i][j]!=null){

                            setPieceAtTile(Piece.piecePositions[i][j]);

                        }
                    }
                }

                updateDisplay();
                Board.getBoard().displayBoard();
            }
        });
        MainMenu.getMainMenu().getExitButton().addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e){
                System.exit(0);
            }
        });
         
        pieceIconMap.putIfAbsent(PointPiece.class, new PieceIcon(PointPiece.class, PieceIcon.getImage("piecesPics/yellowArrow.png"), PieceIcon.getImage("piecesPics/blueArrow.png")));
        pieceIconMap.putIfAbsent(PlusPiece.class, new PieceIcon(PlusPiece.class, PieceIcon.getImage("piecesPics/yellowPlus.png"), PieceIcon.getImage("piecesPics/bluePlus.png")));
        pieceIconMap.putIfAbsent(TimePiece.class, new PieceIcon(TimePiece.class, PieceIcon.getImage("piecesPics/yellowTime.png"), PieceIcon.getImage("piecesPics/blueTime.png")));
        pieceIconMap.putIfAbsent(HourglassPiece.class, new PieceIcon(HourglassPiece.class, PieceIcon.getImage("piecesPics/yellowHourglass.png"), PieceIcon.getImage("piecesPics/blueHourglass.png")));
        pieceIconMap.putIfAbsent(SunPiece.class, new PieceIcon(SunPiece.class, PieceIcon.getImage("piecesPics/yellowSun.png"), PieceIcon.getImage("piecesPics/blueSun.png")));

        swapPieceMapTest.put(PlusPiece.class, TimePiece.class);
        swapPieceMapTest.put(TimePiece.class, PlusPiece.class);
    }



    // Initialize pieces, its icons and its positions
    public void initializePiece(String pieceType, int x, int y, char status, char side) {
        Piece piece=CreatePiece.createPiece(pieceType, x, y, status, side);
        setPieceAtTile(piece);
    }

    public void instantiatePieces(){ // create the piece and put it on the board
        initializePiece("PlusPiece", 5, 0, 'A', 'Y');
        initializePiece("PlusPiece", 5, 6, 'A', 'Y');
        initializePiece("PlusPiece", 0, 0, 'A', 'B');
        initializePiece("PlusPiece", 0, 6, 'A', 'B');
        initializePiece("HourglassPiece", 5, 1, 'A', 'Y');
        initializePiece("HourglassPiece", 5, 5, 'A', 'Y');
        initializePiece("HourglassPiece", 0, 1, 'A', 'B');
        initializePiece("HourglassPiece", 0, 5, 'A', 'B');
        initializePiece("TimePiece", 5, 2, 'A', 'Y');
        initializePiece("TimePiece", 5, 4, 'A', 'Y');
        initializePiece("TimePiece", 0, 2, 'A', 'B');
        initializePiece("TimePiece", 0, 4, 'A', 'B');
        initializePiece("SunPiece", 0, 3, 'A', 'B');
        initializePiece("SunPiece", 5, 3, 'A', 'Y');
        for (int column = 0; column < 7; column++) {
            initializePiece("PointPiece", 4, column, 'A', 'Y');
            initializePiece("PointPiece", 1, column, 'A', 'B');
        }   
        setRotationStatus();
        rotateThePiece();
    }

    public int getTurnNumber(){
        return turnCount;
    }

    public char getWhoseTurn(){
        return whoseTurn;
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
    
    // change side after each turn and check for possible piece swapping
    public void updateTurn(){

        if(whoseTurn=='B'){whoseTurn='Y';}else{whoseTurn='B';}
        
        turnCount++;
    }

    public void updateDisplay(){
        
        if(turnCount%2==0){
            if(turnCount%4==0){
                swapPieces();
            }
            board.setRotationStatus(true);
        }else{
            board.setRotationStatus(false);
        }

        setRotationStatus();

        board.flipBoard();
        rotateThePiece();
    }

    public void setRotationStatus(){
        System.out.println("checking rotation ");

        for(int i=0; i<Board.row; i++){
            for(int j=0; j<Board.column; j++){

                Piece piece=Piece.piecePositions[i][j];

                if(piece!=null){
                    
                    if(piece.getSide()!=whoseTurn){ // if it's not the pieces' turn rotate the icon
                        Tile.tiles[i][j].setRotationStatus(true);
                    }else{
                        Tile.tiles[i][j].setRotationStatus(false);
                    }

                    if(piece instanceof PointPiece){
                        PointPiece pointPiece=(PointPiece) piece;
                        if(pointPiece.getReversedB()||pointPiece.getReversedY()){
                            // Tile.getTileAtCoordinate(i, j).rotateIcon(Piece.piecePositions[i][j]);
                            Tile.getTileAtCoordinate(i, j).setRotationStatus(true);
                            System.out.println("Reached end of board. Tile " + Tile.tiles[i][j].getxCoord(i, j) + ", " + Tile.tiles[i][j].getyCoord(i, j) + "'s rotation status is " + Tile.getTileAtCoordinate(i, j).getRotationStatus());
                        }
                    }
                    //System.out.println("Done Checking. Tile " + Tile.tiles[i][j].getxCoord(i, j) + ", " + Tile.tiles[i][j].getyCoord(i, j) + "'s rotation status is " + Tile.getTileAtCoordinate(i, j).getRotationStatus());
                }
            }
        }
    }

    public void rotateThePiece(){
        for(int i=0; i<Board.row; i++){
            for(int j=0; j<Board.column; j++){
                if(Piece.piecePositions[i][j]!=null && Tile.tiles[i][j].getRotationStatus()){
                    // System.out.println("Tile " + Tile.tiles[i][j].getxCoord() + ", " + Tile.tiles[i][j].getyCoord() + " is rotating");
                    Tile.getTileAtCoordinate(i, j).rotateIcon();
                }
                else if(Piece.piecePositions[i][j]!=null&&!Tile.tiles[i][j].getRotationStatus()){ // if rotate status is false set original image
                    // System.out.println("Tile " + Tile.tiles[i][j].getxCoord() + ", " + Tile.tiles[i][j].getyCoord() + " is set to default");
                    Tile.getTileAtCoordinate(i, j).setIcon(pieceIconMap.get(Piece.piecePositions[i][j].getClass()).getIconImg(Piece.piecePositions[i][j].getSide()));;
                }
            }
        }
    }

    public void swapPieces(){
        for(int i=0; i<Board.row; i++){
            for(int j=0; j<Board.column; j++){
                Piece currentPiece=Piece.piecePositions[i][j];
                if(currentPiece!=null && swapPieceMapTest.containsKey(currentPiece.getClass())){ // if the piece's class is in the map of the pieces to be swapped
                    char currentPieceSide=currentPiece.getSide();    
                    removePieceFromTile(currentPiece);
                    initializePiece(swapPieceMapTest.get(currentPiece.getClass()).getSimpleName(), i, j, 'A', currentPieceSide);
                }
            }
        }
    }

    // logging and debugging
    public void printSelectedPiece(){
        System.out.println("Current selected piece is: " + Piece.selectedPiece);

    }

    public void clickTile(int x, int y){ // this int x, int y is the destination X and Y

        System.out.println("\n+-+-+-+-+-+-+\nTile " + x + ", " + y + " clicked!");
        System.out.println("Turn number " + turnCount + "\n");
        System.out.println("Tile rotation status is " + Tile.tiles[x][y].getRotationStatus());

        if(Piece.selectedPiece==null){ // if no selected piece then select the clicked piece
            verifyValidTurn(x, y);
            printSelectedPiece();
            return;
        }
        
        if(Piece.selectedPiece.getPosX()==x&&Piece.selectedPiece.getPosY()==y){ // if click at same position (deselect)
            System.out.println("Deselect piece");
            selectPiece(null);
            printSelectedPiece();
            return;
        }

        movePieces(x, y, Piece.selectedPiece); // move da pieces
        printSelectedPiece();
    }

    public void selectPiece(Piece p){
        Piece.selectedPiece=p;
    }

    public void removePieceFromTile(Piece p){
        Piece.piecePositions[p.getPosX()][p.getPosY()]=null; // set the coordinate/tile to null
        Tile.tiles[p.getPosX()][p.getPosY()].setIcon(null); // set icon at tile to null
        Tile.tiles[p.getPosX()][p.getPosY()].setDefaultImg(null);

    }
    
    // automatically sets the position based on the pieces position
    public void setPieceAtTile(Piece p){
        Piece.piecePositions[p.getPosX()][p.getPosY()]=p;
        Tile.tiles[p.getPosX()][p.getPosY()].setIcon(pieceIconMap.get(p.getClass()).getIconImg(p.getSide()));
        Tile.tiles[p.getPosX()][p.getPosY()].setDefaultImg(pieceIconMap.get(p.getClass()).getIconImg(p.getSide()));
    }  
    
    public void movePieces(int x, int y, Piece p){ // this int x, int y is the destination X and Y
        if(p.validMove(x, y)){

            System.out.println("Placed piece.");

            if(Piece.piecePositions[x][y]!=null){ // if the destination ada other piece
                System.out.println("Ate a piece! The piece "+Piece.piecePositions[x][y]+"'s status is DEAD: " + Piece.piecePositions[x][y].getStatus());
                removePieceFromTile(p);            }

            removePieceFromTile(p); // remove piece
            p.setPosXY(x, y); // set new xy for the piece
            setPieceAtTile(p); //set piece at new tile

            Piece.selectedPiece=null;
            updateTurn();
            updateDisplay();
        }else{
            System.out.println("invalid move bb");
        }
    }

    public CurrentState setState(CurrentState state){
        state.setTurnCountState(turnCount);
        state.setWhoseTurnState(whoseTurn);
        state.setPiecesPositionState(Piece.piecePositions);
        return state;
    }

    private void resetGame() {
        turnCount = 0;
        whoseTurn = 'Y';
        Piece.selectedPiece = null;
        board.setRotationStatus(false);

        // Clear the board
        for (int i = 0; i < Board.row; i++) {
            for (int j = 0; j < Board.column; j++) {
                if(Piece.piecePositions[i][j]!=null){
                    removePieceFromTile(Piece.piecePositions[i][j]);
                    Piece.piecePositions[i][j] = null;
                }
            }
        }
    }
}
