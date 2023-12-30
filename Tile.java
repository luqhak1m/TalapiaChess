
import java.util.HashMap;

public class GameControl {
    private HashMap<Piece, PieceIcon> pieceIconMap = new HashMap<>();
    Piece piecePositions[][]=new Piece[6][7];
    
    public void mapPiecesIcon(Piece p, PieceIcon pIcon){
        pieceIconMap.putIfAbsent(p, pIcon);
    }

    public HashMap<Piece, PieceIcon> getMap(){
        return pieceIconMap;
    }

    public void clickTile(int x, int y){

        System.out.print("Tile clicked. ");

        if(Piece.selectedPiece!=null){
            System.out.print("Placed piece: ");
            movePieces(x, y, Piece.selectedPiece);
        }else{
            selectPiece(Piece.piecePositions[x][y]);
            System.out.println("Selected piece: " + Piece.selectedPiece);
        }

        // if(Piece.piecePositions[x][y]!=null){
        //     Piece.selectedPiece=Piece.piecePositions[x][y];
        //     Piece.selectedPiece.printSomething();
        // }else{
        //     System.out.println("tile is null");
        // }
    }

    public void setPiecesIcon(Piece p){
        Tile.tiles[p.getPosX()][p.getPosY()].setIcon(pieceIconMap.get(p).getImg());
    }

    public void removePiecesIcon(int x, int y){
        Tile.tiles[x][y].setIcon(null);
    }

    public void setPiecesPositions(int x, int y, Piece p){
        Piece.piecePositions[x][y]=p;
    }

    public void selectPiece(Piece p){
        Piece.selectedPiece=p;
    }

    public void movePieces(int x, int y, Piece p){
        removePiecesIcon(p.getPosX(), p.getPosY());
        p.setPosXY(x, y);
        setPiecesPositions(x, y, p);
        setPiecesIcon(p);
        Piece.selectedPiece.printSomething();
        Piece.selectedPiece=null;
    }

    public Piece getPieceAtPosition(int x, int y){
        return Piece.piecePositions[x][y];
    }
}
