public class PieceMovement {

    private GameControl gameControl;

    public PieceMovement(GameControl gameControl){
        this.gameControl=gameControl;
    }
    
    // automatically sets the position based on the pieces position
    public void setPieceAtTile(Piece p){
        Piece.piecePositions[p.getPosX()][p.getPosY()]=p;
        Tile.tiles[p.getPosX()][p.getPosY()].setDefaultImg((IconHandler.getIconMap().get(p.getClass()).getIconImg(p.getSide())));
        Tile.tiles[p.getPosX()][p.getPosY()].setIconAtTile();
    }  

    public void movePieces(int x, int y, Piece p){ // this int x, int y is the destination X and Y
        if(p.validMove(x, y)){
            
            System.out.println("Placed piece.");
            
            if(Piece.piecePositions[x][y]!=null){ // if the has another piece
                System.out.println("Ate a piece! The piece "+Piece.piecePositions[x][y]+"'s status is DEAD: " + Piece.piecePositions[x][y].getStatus());
                removePieceFromTile(Piece.piecePositions[x][y]);            
            }
                
                removePieceFromTile(p); // remove piece
                p.setPosXY(x, y); // set new xy for the piece
                setPieceAtTile(p); //set piece at new tile
                
                Piece.selectedPiece=null;
                gameControl.newTurn();
            }else{
                Board.getBoard().displayInvalidMove();
                System.out.println("invalid move bb");
            }
        }
           
    // Initialize pieces, its icons and its positions
    public void removePieceFromTile(Piece p){
        Piece.piecePositions[p.getPosX()][p.getPosY()]=null; // set the coordinate/tile to null
        Tile.tiles[p.getPosX()][p.getPosY()].setDefaultImg(null);
        Tile.tiles[p.getPosX()][p.getPosY()].setIconAtTile();// set icon at tile to null

    }
}
