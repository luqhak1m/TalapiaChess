public class PlusPiece extends Piece{

    public PlusPiece(int x, int y, char st, char si) {
        super(x, y, st, si);   
    }

    @Override
    public boolean validMove(int newX, int newY) {

        boolean sameX=newX-this.getPosX()==0;
        boolean sameY=newY-this.getPosY()==0;

        if((sameX&&!sameY)||(sameY&&!sameX)){
            return !skipPiece(newX, newY);
        }else{
            System.out.println("Horizontal and Vertical only");
            return false;
        }
    }

    @Override
    public boolean skipPiece(int newX, int newY) {
        int distanceX=Integer.compare(newX, this.getPosX()); // check the direction
        int distanceY=Integer.compare(newY, this.getPosY());

        int checkX=this.getPosX()+distanceX; // adds the coordinate respective to the direction
        int checkY=this.getPosY()+distanceY;

        // System.out.println("Distance X: " + distanceX);
        // System.out.println("Distance Y: " + distanceY);

        // System.out.println("Destination X: " + newX);
        // System.out.println("Destination Y: " + newY);

        if(Piece.piecePositions[newX][newY]!=null&&Piece.piecePositions[newX][newY].getSide()!=this.getSide()){
            Piece.piecePositions[newX][newY].setStatusDead();
        }else if(Piece.piecePositions[newX][newY]!=null&&Piece.piecePositions[newX][newY].getSide()==this.getSide()){
            System.out.println();
            System.out.println("Cannot eat teammate!");
            return true;
        }

        while (checkX!=newX||checkY!=newY) {
            if (Piece.piecePositions[checkX][checkY]!=null) { // if the tile is NOT empty
                System.out.println("Tile " + checkX + ", " + checkY + " is occupied. Stopping algorithm.");
                return true;
            } else {
                System.out.println("Tile " + checkX + ", " + checkY + " is NOT occupied");
            }
            checkX += distanceX;
            checkY += distanceY;
        }
        return false;
    }
}
