// Model

public class SunPiece extends Piece{

    SunDeathListener death; // An object that imlements the listener

    public SunPiece(int x, int y, char st, char si) {
        super(x, y, st, si);
    }

    @Override
    public void setStatusDead(){
        super.setStatusDead();
        if(this.death!=null){death.update(this);}
    }

    public void setDeathListener(SunDeathListener death){
        this.death=death;
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