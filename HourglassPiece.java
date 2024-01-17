// Model

public class HourglassPiece extends Piece{

    public HourglassPiece(int x, int y, char st, char si) {
        super(x, y, st, si);
    }

    @Override
    public boolean validMove(int newX, int newY) {
        int hDistance = Math.abs(newX - getPosX()); //horizontal distance
        int vDistance = Math.abs(newY - getPosY()); //vertical distance
        System.out.println("hDistance: " + hDistance + ", vDistance: " + vDistance);

        //movement of hourglass piece in 3x2 L shape in any orientation
        return (hDistance == 2 && vDistance == 1) || (hDistance == 3 && vDistance == 1) || (hDistance == 1 && vDistance == 2); //return true if 3x2 in L shape
        
    }

    @Override
    public boolean skipPiece(int newX, int newY) {
        //check if next coordinates are within the board bounds
        if (newX < 0 || newX >= Piece.row || newY < 0 || newY >= Piece.column) {
            return false;
        }

        return true; //skips over any piece
    }
}
