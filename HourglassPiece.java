
// Model
// Author: Aisyah

public class HourglassPiece extends Piece{

    // Constructor.
    // Written: Aisyah
    public HourglassPiece(int x, int y, char st, char si) {
        super(x, y, st, si);
    }

    // Check for valid movement.
    // Written: Aisyah
    @Override
    public boolean validMove(int newX, int newY) {
        int hDistance = Math.abs(newX - getPosX()); //horizontal distance
        int vDistance = Math.abs(newY - getPosY()); //vertical distance
        System.out.println("hDistance: " + hDistance + ", vDistance: " + vDistance);

        //movement of hourglass piece in 3x2 L shape in any orientation
        return (hDistance == 2 && vDistance == 1) || (hDistance == 1 && vDistance == 2); //return true if 3x2 in L shape
    }

    // Check if it skips any pieces.
    // Written by: Aisyah
    @Override
    public boolean skipPiece(int newX, int newY) {
        //check if next coordinates are within the board bounds
        if (newX < 0 || newX >= Board.row || newY < 0 || newY >= Board.column) {
            return false;
        }

        return true; //skips over any piece
    }
}
