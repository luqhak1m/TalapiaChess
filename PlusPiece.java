
// Model

public class PlusPiece extends Piece{

    public PlusPiece(int x, int y, char st, char si) {
        super(x, y, st, si);   
    }

    // Test move on fake rules. Remove if necessary.

    @Override
    public boolean validMove(int newX, int newY) {

        int sameX=Math.abs(newX-this.getPosX());
        int sameY=Math.abs(newY-this.getPosY());

        if(sameY<=3&&sameX==0){
            return !skipPiece(newX, newY);
        }else{
            System.out.println("Horizontal and 3 Tiles only!");
            return false;
        }
    }

    @Override
    public boolean skipPiece(int newX, int newY) {

        int directionX=Integer.compare(newX, this.getPosX()); // ke atas x-1, ke bawah x+1
        int directionY=Integer.compare(newY, this.getPosY()); // ke kanan y+1, ke kiri y-1

        int checkX=this.getPosX()+directionX; // increment the x position based on the direction
        int checkY=this.getPosY()+directionY; // increment the y position based on the direction

        // System.out.println("Distance X: " + distanceX);
        // System.out.println("Distance Y: " + distanceY);

        // System.out.println("Destination X: " + newX);
        // System.out.println("Destination Y: " + newY);

        if(Piece.piecePositions[newX][newY]!=null&&Piece.piecePositions[newX][newY].getSide()!=this.getSide()){ // if ada piece dekat destination AND the piece yang ada dekat situ bukan from the same side of this piece
            Piece.piecePositions[newX][newY].setStatusDead(); // kill the piece dekat destination
        }else if(Piece.piecePositions[newX][newY]!=null&&Piece.piecePositions[newX][newY].getSide()==this.getSide()){ // if teammate
            System.out.println();
            System.out.println("Cannot eat teammate!");
            return true;
        }

        while (checkX!=newX||checkY!=newY) { // selagi belum reach the final destination (newX, newY)
            if (Piece.piecePositions[checkX][checkY]!=null) { // if the checked tile ada other piece
                System.out.println("Tile " + checkX + ", " + checkY + " is occupied. Stopping algorithm.");
                return true;
            } else {
                System.out.println("Tile " + checkX + ", " + checkY + " is NOT occupied");
            }
            checkX += directionX;
            checkY += directionY;
        }
        return false;
    }
}
