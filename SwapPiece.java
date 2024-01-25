
import java.util.HashMap;
import java.util.Map;

public class SwapPiece {

    private Map<Class<? extends Piece>, Class<? extends Piece>> swapPieceMapTest=new HashMap<>();
    private static SwapPiece swapPieceController;
    private GameControl gameControl;

    private SwapPiece(){
        swapPieceMapTest.put(PlusPiece.class, TimePiece.class);
        swapPieceMapTest.put(TimePiece.class, PlusPiece.class);
    }

    public void setGameControl(GameControl gameControl){
        this.gameControl=gameControl;
    }

    public static SwapPiece getSwapPieceController(){
        if(swapPieceController==null){swapPieceController=new SwapPiece();}
        return swapPieceController;
    }

    public void swapPieces(){
        for(int i=0; i<Board.row; i++){
            for(int j=0; j<Board.column; j++){
                Piece currentPiece=Piece.piecePositions[i][j];
                if(currentPiece!=null && swapPieceMapTest.containsKey(currentPiece.getClass())){ // if the piece's class is in the map of the pieces to be swapped
                    char currentPieceSide=currentPiece.getSide();    
                    gameControl.removePieceFromTile(currentPiece);
                    gameControl.initializePiece(swapPieceMapTest.get(currentPiece.getClass()).getSimpleName(), i, j, 'A', currentPieceSide);
                }
            }
        }
    }
}
