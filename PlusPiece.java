public class PlusPiece extends Piece {

    // Constructor for PlusPiece
    public PlusPiece(int x, int y, char st, char si) {
        super(x, y, st, si);
    }
    
    // Check if the move to the new position is valid
    @Override
    public boolean validMove(int newX, int newY) {
        return !skipPiece(newX, newY);
    }

    // Check if there are any pieces in the path to the new position
    @Override
    public boolean skipPiece(int newX, int newY) {
        // Determine the direction of the move
        int directionX = Integer.compare(newX, this.getPosX());
        int directionY = Integer.compare(newY, this.getPosY());

        // Initialize the position to check
        int checkX = this.getPosX() + directionX;
        int checkY = this.getPosY() + directionY;

        // Loop through each tile in the path
        while (checkX != newX || checkY != newY) {
            // Check if the tile is occupied by another piece
            if (Piece.piecePositions[checkX][checkY] != null) {
                System.out.println("Tile " + checkX + ", " + checkY + " is occupied. Stopping algorithm.");
                return true; // There is a piece in the path
            } else {
                System.out.println("Tile " + checkX + ", " + checkY + " is NOT occupied");
            }
            // Move to the next tile in the path
            checkX += directionX;
            checkY += directionY;
        }

        // If the destination is occupied by an opponent, capture the piece
        if (Piece.piecePositions[newX][newY] != null && Piece.piecePositions[newX][newY].getSide() != this.getSide()) {
            Piece.piecePositions[newX][newY].setStatusDead();
        }
        // If the destination is occupied by a teammate, disallow the move
        else if (Piece.piecePositions[newX][newY] != null && Piece.piecePositions[newX][newY].getSide() == this.getSide()) {
            System.out.println("Cannot eat teammate!");
            return true;
        }

        return false; // The move is valid
    }
}

