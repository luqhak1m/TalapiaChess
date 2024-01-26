
import java.util.HashMap;
import java.util.Map;

public class SwapPiece {

    private Map<Class<? extends Piece>, Class<? extends Piece>> swapPieceMap=new HashMap<>();
    private static SwapPiece swapPieceController;
    private GameControl gameControl;
    private int swapTurn=4;

    private SwapPiece(){
        swapPieceMap.put(PlusPiece.class, TimePiece.class);
        swapPieceMap.put(TimePiece.class, PlusPiece.class);
    }

    public void setGameControl(GameControl gameControl){
        this.gameControl=gameControl;
    }

    public void setSwapTurn(int t){
        swapTurn=t;
    }

    public Map<Class<? extends Piece>, Class<? extends Piece>> getSwapMap(){
        return swapPieceMap;
    }

    public static SwapPiece getSwapPieceController(){
        if(swapPieceController==null){swapPieceController=new SwapPiece();}
        return swapPieceController;
    }

    public void swapPieces(){

        if(swapTurn==4){
            for(int i=0; i<Board.row; i++){
                for(int j=0; j<Board.column; j++){
    
                    Piece currentPiece=Piece.piecePositions[i][j];

                    if(currentPiece!=null && swapPieceMap.containsKey(currentPiece.getClass())){ // if the piece's class is in the map of the pieces to be swapped
                        gameControl.repositionPiece(currentPiece);
                    }
                }
            }
        }
    }
}
