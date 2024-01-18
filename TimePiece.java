
// Model


public class TimePiece extends Piece {

    // Constructor for TimePiece
    public TimePiece(int x, int y, char st, char si) {
        super(x, y, st, si);
    }

    // Check if the move is valid for a TimePiece (diagonal movement only)
    @Override
    public boolean validMove(int newX, int newY) {
        int deltaX = Math.abs(newX - this.getPosX());
        int deltaY = Math.abs(newY - this.getPosY());

        // Diagonal movement is allowed
        if (deltaX == deltaY) {
            // Check if the move is valid without skipping over pieces
            return !skipPiece(newX, newY);
        } else {
            // Print a message and return false for invalid moves
            System.out.println("Diagonal movement only!");
            return false;
        }
    }

    // Check if the TimePiece can skip over pieces and handle opponents/teammates
    @Override
    public boolean skipPiece(int newX, int newY) {
        int directionX = Integer.compare(newX, this.getPosX());
        int directionY = Integer.compare(newY, this.getPosY());

        int checkX = this.getPosX() + directionX;
        int checkY = this.getPosY() + directionY;

        // Handle opponent at the destination
        if (Piece.piecePositions[newX][newY] != null && Piece.piecePositions[newX][newY].getSide() != this.getSide()) {
            // "Kill" the opponent piece at the destination
            Piece.piecePositions[newX][newY].setStatusDead();
        } else if (Piece.piecePositions[newX][newY] != null && Piece.piecePositions[newX][newY].getSide() == this.getSide()) {
            // Print a message and return true for teammate at the destination
            System.out.println();
            System.out.println("Cannot eat teammate!");
            return true;
        }

        // Check for pieces in the path of the TimePiece
        while (checkX != newX || checkY != newY) {
            if (Piece.piecePositions[checkX][checkY] != null) {
                // Print a message and return true for invalid moves (skipping over pieces)
                System.out.println("Cannot skip over pieces.");
                return true;
            }
            checkX += directionX;
            checkY += directionY;
        }

        // Return false if the move is valid (no skipping over pieces)
        return false;
    }
}



