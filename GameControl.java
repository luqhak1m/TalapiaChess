
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

    public void clickTile(int x, int y, GameControl c){
        if(Piece.piecePositions[x][y]!=null){
            Piece.selectedPiece=Piece.piecePositions[x][y];
            System.out.println(Piece.selectedPiece);
        }else{
            System.out.println("tile is null");
        }
    }

    public void setPiecesIcon(Piece p){
        Tile.tiles[p.getPosX()][p.getPosY()].setIcon(pieceIconMap.get(p).getImg());
    }

    public void setPiecesPositions(int x, int y, Piece p){
        Piece.piecePositions[x][y]=p;
    }

    public Piece getPieceAtPosition(int x, int y){
        return Piece.piecePositions[x][y];
    }
}
