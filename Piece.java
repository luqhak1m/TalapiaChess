
// Model

public abstract class Piece{

    private int posX, posY;
    static int row=6, column=7;
    private char side, status;
    static Piece selectedPiece=null;
    static Piece piecePositions[][]=new Piece[row][column];

    public Piece(int x, int y, char st, char si){

        this.posX=x;
        this.posY=y;
        this.status=st;
        this.side=si;

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
    public char getStatus(){
        return status;
    }
    public char getSide(){
        return side;
    }

    public void setPosXY(int x, int y){
        this.posX=x;
        this.posY=y;
    }

    public boolean emptyTile(int x, int y){
        return Tile.tiles[x][y]==null;
    }

    public void setStatusDead(){
        this.status='D';
    }
    public void setStatusAlive(){
        this.status=1;
    }

    public abstract boolean validMove(int newX, int newY);
    public abstract boolean skipPiece(int newX, int newY); // will check for piece skip in the validmove
}
