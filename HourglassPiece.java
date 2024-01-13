
// Model

public class HourglassPiece extends Piece{

    public HourglassPiece(int x, int y, char st, char si) {
        super(x, y, st, si);
    }

    @Override
    public boolean validMove(int newX, int newY) {
        return true;
    }

    @Override
    public boolean skipPiece(int newX, int newY) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'skipPiece'");
    }
}
