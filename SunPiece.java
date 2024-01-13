
// Model

public class SunPiece extends Piece{

    public SunPiece(int x, int y, char st, char si) {
        super(x, y, st, si);
    }

    @Override
    public boolean validMove(int newX, int newY) {
        if(!emptyTile(newX, newY)){
            return true;
        }
        return false;
    }

    @Override
    public boolean skipPiece(int newX, int newY) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'skipPiece'");
    }
}
