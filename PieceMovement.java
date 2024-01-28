public class PieceMovement {

    private GameControl gameControl;

    public PieceMovement(GameControl gameControl){
        this.gameControl=gameControl;
    }

    public void movePieces(int x, int y, Piece p){ // this int x, int y is the destination X and Y
        if(p.validMove(x, y)){
            
            System.out.println("Placed piece.");
            
            Piece pieceAtPlacedTile=Piece.piecePositions[x][y];
            
            if(pieceAtPlacedTile!=null){ // if the tile has another piece
                pieceAtPlacedTile.setStatusDead();
                if(gameControl.getGameOver()){
                    return;
                }
                System.out.println("Ate a piece! The piece "+pieceAtPlacedTile+ " at " + x + ", " + y  + "'s status is DEAD: " + pieceAtPlacedTile.getStatus());
                removePieceFromTile(Piece.piecePositions[x][y]);      
            }
            
            removePieceFromTile(p); // remove piece
            p.setPosXY(x, y); // set new xy for the piece
            gameControl.setPieceAtTile(p); //set piece at new tile
            
            Piece.selectedPiece=null;
            gameControl.newTurn();

        
            }else{
                Board.getBoard().displayMessage("Invalid Move");
            }
        }
           
    // Initialize pieces, its icons and its positions
    public void removePieceFromTile(Piece p){
        Piece.piecePositions[p.getPosX()][p.getPosY()]=null; // set the coordinate/tile to null
        Tile.tiles[p.getPosX()][p.getPosY()].resetImg();
        Tile.tiles[p.getPosX()][p.getPosY()].setIconAtTile();// set icon at tile to null

    }
}
