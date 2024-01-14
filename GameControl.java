
// Controller

import java.util.HashMap;
import java.util.Map;

public class GameControl {
    private int turnCount=0;
    private char whoseTurn='Y';
    private Map<Class<? extends Piece>, Class<? extends Piece>> swapPieceMapTest=new HashMap<>();

    public GameControl(){
        pieceIconMap.putIfAbsent(PointPiece.class, new PieceIcon(PointPiece.class, PieceIcon.getImage("piecesPics/yellowArrow.png"), PieceIcon.getImage("piecesPics/blueArrow.png")));
        pieceIconMap.putIfAbsent(PlusPiece.class, new PieceIcon(PlusPiece.class, PieceIcon.getImage("piecesPics/yellowPlus.png"), PieceIcon.getImage("piecesPics/bluePlus.png")));
        pieceIconMap.putIfAbsent(TimePiece.class, new PieceIcon(TimePiece.class, PieceIcon.getImage("piecesPics/yellowTime.png"), PieceIcon.getImage("piecesPics/blueTime.png")));
        pieceIconMap.putIfAbsent(HourglassPiece.class, new PieceIcon(HourglassPiece.class, PieceIcon.getImage("piecesPics/yellowHourglass.png"), PieceIcon.getImage("piecesPics/blueHourglass.png")));
        pieceIconMap.putIfAbsent(SunPiece.class, new PieceIcon(SunPiece.class, PieceIcon.getImage("piecesPics/yellowSun.png"), PieceIcon.getImage("piecesPics/blueSun.png")));

        swapPieceMapTest.put(SunPiece.class, PointPiece.class);
    }

    // mapping Pieces to its respective pieceIcons
    
    private HashMap<Class<? extends Piece>, PieceIcon> pieceIconMap = new HashMap<>();

    // Initialize pieces, its icons and its positions
    public void initializePiece(String pieceType, int x, int y, char status, char side) {
        Piece piece=CreatePiece.createPiece(pieceType, x, y, status, side);
        // PieceIcon pieceIcon=new PieceIcon(piece.getClass(), PieceIcon.getImage(imagePath));
        // mapPiecesIcon(piece.getClass());
        setPieceAtTile(piece);
    }

    public void instantiatePieces(){
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
        if(whoseTurn=='Y'){
            whoseTurn='B';
        }else{whoseTurn='Y';}
        turnCount++;
        if(turnCount%4==0){
            swapPieces();
        }
    }

    public void swapPieces(){
        for(int i=0; i<Piece.row; i++){
            for(int j=0; j<Piece.column; j++){
                Piece currentPiece=Piece.piecePositions[i][j];
                if(currentPiece!=null && swapPieceMapTest.containsKey(currentPiece.getClass())){ // if the piece's class is in the map of the pieces to be swapped
                    Piece latestPiece=CreatePiece.createPiece(swapPieceMapTest.get(currentPiece.getClass()).getSimpleName(), i, j, 'A', currentPiece.getSide()); // create the new piece based on the map pairing
                    removePieceFromTile(currentPiece);
                    setPieceAtTile(latestPiece);
                }
            }
        }
    }

    // logging and debugging
    public void printSelectedPiece(){
        System.out.println("Current selected piece is: " + Piece.selectedPiece);

    }

    public void clickTile(int x, int y){ // this int x, int y is the destination X and Y

        System.out.println("\n+-+-+-+-+-+-+\nTile clicked!");
        System.out.println("Turn number " + turnCount + "\n");

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
    }

    public void setPiecesIcon(Piece p){
        Tile.tiles[p.getPosX()][p.getPosY()].setIcon(pieceIconMap.get(p.getClass()).getIconImg(p.getSide()));
    }
    
    // automatically sets the position based on the pieces position
    public void setPieceAtTile(Piece p){
        Piece.piecePositions[p.getPosX()][p.getPosY()]=p;
        setPiecesIcon(p);  
    }  
    
    // if dead remove piece from the board
    public void checkStatus(Piece p){
        if(p.getStatus()=='D'){
            removePieceFromTile(p);
        }
    }

    public void movePieces(int x, int y, Piece p){ // this int x, int y is the destination X and Y
        if(p.validMove(x, y)){

            System.out.println("Placed piece.");

            if(Piece.piecePositions[x][y]!=null){ // if the destination ada other piece
                System.out.println("Ate a piece! The piece "+Piece.piecePositions[x][y]+"'s status is DEAD: " + Piece.piecePositions[x][y].getStatus());
                checkStatus(Piece.piecePositions[x][y]);
            }

            removePieceFromTile(p); // remove piece
            p.setPosXY(x, y); // set new xy for the piece
            setPieceAtTile(p); //set piece at new tile

            Piece.selectedPiece=null;
            updateTurn();

        }else{
            System.out.println("invalid move bb");
        }
    }
}
