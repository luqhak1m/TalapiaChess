// Model

public class SunPiece extends Piece{

    public SunPiece(int x, int y, char st, char si) {
        super(x, y, st, si);
    }

    @Override
    public boolean validMove(int newX, int newY) {

        int distanceX=Math.abs(newX-this.getPosX());
        int distanceY=Math.abs(newY-this.getPosY());

        System.out.println("sun moved a total of X:" + distanceX + " Y: " + distanceY);
        if(distanceX<=1&&distanceY<=1){
            return !skipPiece(newX, newY);
        }else{return false;}

    }

    @Override
    public boolean skipPiece(int newX, int newY) {
        return false;
    }
}