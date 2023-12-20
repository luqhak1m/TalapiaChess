public abstract class Piece{

    private int posX, posY;
    private char side;
    static Piece[][] piecesPosition = new Piece[6][7];
    static Piece selectedPiece=null;

    public Piece(int x, int y){

        this.posX=x;
        this.posY=y;

        if(x==0||x==1){
            this.side='B';
        }else{
            this.side='Y';
        }

        piecesPosition[x][y]=this;
    }

    public int getPosX(){
        return this.posX;
    }
    public int getPosY(){
        return this.posY;
    }

    public void setPosX(int x){
        this.posX=x;
    }
    public void setPosY(int y){
        this.posY=y;
    }

    public static Piece getPiecesPositionAtIndex(int x, int y){
        return piecesPosition[x][y];
    }
    public static void setPiecesPosition(Piece piece, int x, int y){
        piecesPosition[x][y]=piece;
    }

    
}
