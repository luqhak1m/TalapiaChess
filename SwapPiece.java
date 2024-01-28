
// Swapping pieces that need swapping.
// Singleton because every piece swapping must be declared in here only.
// Author: Luqman

import java.util.HashMap;
import java.util.Map;

public class SwapPiece {

    private Map<Class<? extends Piece>, Class<? extends Piece>> swapPieceMap=new HashMap<>();
    private static SwapPiece swapPieceController;
    private GameControl gameControl;
    private int swapTurn=4;

    // Constructor. Each piece that need swapping must be declared here.
    // Written by: Luqman
    private SwapPiece(){
        swapPieceMap.put(PlusPiece.class, TimePiece.class);
        swapPieceMap.put(TimePiece.class, PlusPiece.class);
    }

    // Getters and Setters.
    // Written by: Luqman
    public void setGameControl(GameControl gameControl){
        this.gameControl=gameControl;
    }
    public void setSwapTurn(int t){
        swapTurn=t;
    }
    public int getSwapTurn(){
        return swapTurn;
    }
    public Map<Class<? extends Piece>, Class<? extends Piece>> getSwapMap(){
        return swapPieceMap;
    }

    // Get Singleton object.
    // Written by: Luqman
    public static SwapPiece getSwapPieceController(){
        if(swapPieceController==null){swapPieceController=new SwapPiece();}
        return swapPieceController;
    }

    // The swapping algorithm happens here. 
    // Iterate the map and create new piece based on the key's value.
    // Written by: Luqman
    public void swapPieces(){
        for(int i=0; i<Board.row; i++){
            for(int j=0; j<Board.column; j++){

                Piece currentPiece=Piece.piecePositions[i][j];

                if(currentPiece!=null && swapPieceMap.containsKey(currentPiece.getClass())){ // if the piece's class is in the map of the pieces to be swapped
                    gameControl.recreatePiece(currentPiece);
                }
            }
        }
    }
}
