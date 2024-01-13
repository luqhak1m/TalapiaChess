public class TimePiece extends Piece{

    public TimePiece(int x, int y, char st, char si) {
        super(x, y, st, si);
    }

    @Override
    public boolean validMove(int newX, int newY) {
        if(Piece.piecePositions[newX][newY]==null){ // not occupied tiles
            return true;
        }else{
            return false;
        }
    }

    @Override
    public boolean skipPiece(int newX, int newY) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'skipPiece'");
    }
}
