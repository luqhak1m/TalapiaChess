
// This class will handle the piece's movement logic.
// Used in: GameControl.java
// Author: Luqman

public class PieceMovement {

    private GameControl gameControl;

    // Constructor.
    // Written by: Luqman
    public PieceMovement(GameControl gameControl){
        this.gameControl=gameControl;
    }

    // Move the pieces into a new location, while also handling invalid move and killing pieces.
    // Written by: Luqman
    public void movePieces(int x, int y, Piece p){ // this int x, int y is the destination X and Y
        if(p.validMove(x, y)){
            
            System.out.println("Placed piece.");
            
            Piece pieceAtPlacedTile=Piece.piecePositions[x][y];
            
            if(pieceAtPlacedTile!=null){ // if the tile has another piece
                pieceAtPlacedTile.setStatusDead();
                removePieceFromTile(Piece.piecePositions[x][y]);
                if(pieceAtPlacedTile instanceof SunPiece){
                    gameControl.endGame();return;
                }        
                // System.out.println("Ate a piece! The piece "+pieceAtPlacedTile+ " at " + x + ", " + y  + "'s status is DEAD: " + pieceAtPlacedTile.getStatus());
            }
            
            removePieceFromTile(p); // remove piece
            p.setPosXY(x, y); // set new xy for the piece
            gameControl.setPieceAtTile(p); //set piece at new tile
            gameControl.setSelectedPiece(null);
            gameControl.newTurn();
            }else{
                Board.getBoard().displayMessage("Invalid Move");
            }
        }
           
    // Remove pieces from its position and its icons from the board.
    // Written by: Luqman
    public void removePieceFromTile(Piece p){
        Piece.piecePositions[p.getPosX()][p.getPosY()]=null; // set the coordinate/tile to null
        Tile.tiles[p.getPosX()][p.getPosY()].resetImg();
        Tile.tiles[p.getPosX()][p.getPosY()].setIconAtTile();// set icon at tile to null

    }
}
