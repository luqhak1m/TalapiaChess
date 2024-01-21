public class PointPiece extends Piece {

    public PointPiece(int x, int y, char st, char si) {
        super(x, y, st, si);
    }

    @Override
    public boolean validMove(int newX, int newY) {
        int deltaX = Math.abs(newX - getPosX());
        int deltaY = Math.abs(newY - getPosY());
        int forward = newX - getPosX();
        int position = getPosX();

        // Ensure that the total movement is 1 or 2 tiles
        int totalMovement = deltaX;
        char turn = getSide();
    
        if (turn == 'Y') {
            if (position == 0) {
                System.out.println("You can now move only backwards");
                while (position != 5) {
                    if ((totalMovement == 1 || totalMovement == 2) && deltaY == 0 && forward > 0) {
                        return !skipPiece(newX, newY);
                    } else {
                        System.out.println("You can only move backwards 1 or 2 tiles");
                        return false;
                    }
                    
                }
                return true;
            }
            else{
                if ((totalMovement == 1 || totalMovement == 2) && deltaY == 0 && forward < 0) {
                    return !skipPiece(newX, newY);
                } else {
                    System.out.println("You can only move forward 1 or 2 tiles");
                    return false;
                }
            }
        } else {
            if (position == 5) {
                System.out.println("You can now move only backwards");
                while (position != 0) {
                    if ((totalMovement == 1 || totalMovement == 2) && deltaY == 0 && forward < 0) {
                        return !skipPiece(newX, newY);
                    } else {
                        System.out.println("You can only move backwards 1 or 2 tiles");
                        return false;
                    }
                    
                }
                return true;
            }
            if ((totalMovement == 1 || totalMovement == 2) && deltaY == 0 && forward > 0) {
                return !skipPiece(newX, newY);
            } else {
                System.out.println("You can only move forward 1 or 2 tiles");
                return false;
            }
        }
    }



    @Override
    public boolean skipPiece(int newX, int newY) {
        int deltaX = Integer.compare(newX, this.getPosX());
        int deltaY = Integer.compare(newY, this.getPosY());

        int checkX = getPosX() + deltaX;
        int checkY = getPosY() + deltaY;

        while (checkX != newX || checkY != newY) {
            if (Piece.piecePositions[checkX][checkY] != null) {
                System.out.println("Tile " + checkX + ", " + checkY + " is occupied. Stopping algorithm.");
                return true;
            }
            checkX += deltaX;
            checkY += deltaY;
        }

        if (Piece.piecePositions[newX][newY] != null && Piece.piecePositions[newX][newY].getSide() != getSide()) {
            Piece.piecePositions[newX][newY].setStatusDead();
        } else if (Piece.piecePositions[newX][newY] != null && Piece.piecePositions[newX][newY].getSide() == getSide()) {
            System.out.println("Cannot eat teammate!");
            return true;
        }

        return false;
    }
}
