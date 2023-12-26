public abstract class Piece{

    private int posX, posY;
    private char side;
    static Piece selectedPiece=null;
    static Piece piecePositions[][]=new Piece[6][7];


    public Piece(int x, int y){

        this.posX=x;
        this.posY=y;

        if(x==0||x==1){
            this.side='B';
        }else{
            this.side='Y';
        }

        piecePositions[x][y]=this;
    }

    public int getPosX(){
        return this.posX;
    }
    public int getPosY(){
        return this.posY;
    }

    public void setPosXY(int x, int y){
        this.posX=x;
        this.posY=y;
    }
}
