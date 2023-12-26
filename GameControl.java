
import java.util.HashMap;

public class GameControl {
    private HashMap<Piece, PieceIcon> pieceIconMap = new HashMap<>();
    
    public void mapPiecesIcon(Piece p, PieceIcon pIcon){
        pieceIconMap.putIfAbsent(p, pIcon);
    }

    public HashMap<Piece, PieceIcon> getMap(){
        return pieceIconMap;
    }

    public void setPiecesIcon(Piece p){
        Tile.tiles[p.getPosX()][p.getPosY()].setIcon(pieceIconMap.get(p).getImg());
    }
}
