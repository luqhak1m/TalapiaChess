// Model

public class SunPiece extends Piece{

    public SunPiece(int x, int y, char st, char si) {
        super(x, y, st, si);
    }

    @Override
    public boolean validMove(int newX, int newY) {

        int distanceX=Math.abs(this.getPosX()+newX);
        int distanceY=Math.abs(this.getPosY()+newY);

        if(distanceX<=1&&distanceY<=1){
            return !skipPiece(newX, newY);
        }else{return false;}

    }

    @Override
    public boolean skipPiece(int newX, int newY) {
        return false;
    }
}