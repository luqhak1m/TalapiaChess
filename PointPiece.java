public class PointPiece extends Piece {
    
    //Reversed when piece reached the end of the board
    private boolean reversedY = false;
    private boolean reversedB = false;

    public PointPiece(int x, int y, char st, char si) {
        super(x, y, st, si);
    }

    public boolean getReversedY(){
        return reversedY;
    }

    public boolean getReversedB(){
        return reversedB;
    }

    @Override
    public boolean validMove(int newX, int newY) {
        int deltaX = Math.abs(newX - getPosX());
        int deltaY = Math.abs(newY - getPosY());
        int forward = newX - getPosX();
        int position = getPosX();
        char turn = getSide();

        // Ensure that the total movement is 1 or 2 tiles in a straight line vertically
        int totalMovement = deltaX;
        

        //Yellow's Turn
        if (turn == 'Y') {
            if (reversedY) {
                if (position == 5) {
                    reversedY = false;      //Yellow reached the end of the board, switch direction
                    if ((totalMovement == 1 || totalMovement == 2) && deltaY == 0 && forward < 0) {
                        return !skipPiece(newX, newY);
                    } else {
                        System.out.println("You can only move forward 1 or 2 tiles");
                        return false;
                    }
                } else {
                    if ((totalMovement == 1 || totalMovement == 2) && deltaY == 0 && forward > 0) {
                        return !skipPiece(newX, newY);
                    } else {
                        System.out.println("You can only move backward 1 or 2 tiles");
                        return false;
                    }
                }
            } else {
                if (position == 0) {
                    reversedY = true;   //Yellow reached the end of the board, switch direction
                    if ((totalMovement == 1 || totalMovement == 2) && deltaY == 0 && forward > 0) {
                        return !skipPiece(newX, newY);
                    } else {
                        System.out.println("You can only move backward 1 or 2 tiles");
                        return false;
                    }
                    
                } else if ((totalMovement == 1 || totalMovement == 2) && deltaY == 0 && forward < 0) {
                    return !skipPiece(newX, newY);
                } else {
                    System.out.println("You can only move forward 1 or 2 tiles");
                    return false;
                }
            }
        } 
        
        //Blue's Turn
        else {
            if (reversedB) {
                if (position == 0) {
                    reversedB = false;  //Blue reached the end of the board, switch direction
                    if ((totalMovement == 1 || totalMovement == 2) && deltaY == 0 && forward > 0) {
                        return !skipPiece(newX, newY);
                    } else {
                        System.out.println("You can only move forward 1 or 2 tiles");
                        return false;
                    }
                    
                } else {
                    if ((totalMovement == 1 || totalMovement == 2) && deltaY == 0 && forward < 0) {
                        return !skipPiece(newX, newY);
                    } else {
                        System.out.println("You can only move backward 1 or 2 tiles");
                        return false;
                    }
                }
            } else {
                if (position == 5) {
                    reversedB = true;       //Blue reached the end of the board, switch direction
                    if ((totalMovement == 1 || totalMovement == 2) && deltaY == 0 && forward < 0) {
                        return !skipPiece(newX, newY);
                    } else {
                        System.out.println("You can only move backward 1 or 2 tiles");
                        return false;
                    }
                } else if ((totalMovement == 1 || totalMovement == 2) && deltaY == 0 && forward > 0) {
                    return !skipPiece(newX, newY);
                } else {
                    System.out.println("You can only move forward 1 or 2 tiles");
                    return false;
                }
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

