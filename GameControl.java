
// Controller

import java.util.HashMap;
import java.util.Map;

public class GameControl {
    private int turnCount=0;
    private char whoseTurn='Y';
    private Map<Class<? extends Piece>, SwappingPieceInfo> swapPieceMap=new HashMap<>();

    public GameControl(){
        swapPieceMap.put(TimePiece.class, new SwappingPieceInfo(PlusPiece.class, "piecesPics/yellowPlus.png", "piecesPics/bluePlus.png"));
        swapPieceMap.put(PlusPiece.class, new SwappingPieceInfo(TimePiece.class, "piecesPics/yellowTime.png", "piecesPics/blueTime.png"));
        swapPieceMap.put(SunPiece.class, new SwappingPieceInfo(PointPiece.class, "piecesPics/yellowArrow.png", "piecesPics/blueArrow.png"));
    }

    // mapping Pieces to its respective pieceIcons
    
    private HashMap<Piece, PieceIcon> pieceIconMap = new HashMap<>();
    
    public void mapPiecesIcon(Piece p, PieceIcon pIcon){
        pieceIconMap.putIfAbsent(p, pIcon);
    }
    
    public HashMap<Piece, PieceIcon> getMap(){
        return pieceIconMap;
    }

    // Initialize pieces, its icons and its positions
    public void initializePiece(String pieceType, int x, int y, char status, char side, String imagePath) {
        Piece piece=CreatePiece.createPiece(pieceType, x, y, status, side);
        PieceIcon pieceIcon=new PieceIcon(piece, PieceIcon.getImage(imagePath));
        mapPiecesIcon(piece, pieceIcon);
        setPieceAtTile(piece);
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
                if(currentPiece!=null && swapPieceMap.containsKey(currentPiece.getClass())){ // if it's a subclass of the mapped object
                    SwappingPieceInfo toBeSwapped=swapPieceMap.get(currentPiece.getClass()); // get the mapped object
                    String imagePath=(currentPiece.getSide()=='Y') ? toBeSwapped.getYellow():toBeSwapped.getBlue();
                    removePieceFromTile(currentPiece);
                    initializePiece(toBeSwapped.getPieceType().getSimpleName(), i, j, 'A', currentPiece.getSide(), imagePath);
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
        Tile.tiles[p.getPosX()][p.getPosY()].setIcon(pieceIconMap.get(p).getImg());
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
