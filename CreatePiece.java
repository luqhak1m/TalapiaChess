
// Factory method

import java.util.Map;

public class CreatePiece{

    private static CreatePiece createPieceController;
    private GameControl gameControl;

    public CreatePiece(){}
    
    public void setGameControl(GameControl gControl){
        gameControl=gControl;
    }

    private static final Map<String, PieceMaker> pieceInstanceMap=Map.of(
        "PlusPiece", PlusPiece::new, // "PlusPiece" will hold an object of PlusPiece
        "TimePiece", TimePiece::new, // vv
        "SunPiece", SunPiece::new,
        "HourglassPiece", HourglassPiece::new,
        "PointPiece", PointPiece::new
    );

    public Piece createPiece(String type, int x, int y, char status, char side){
        return pieceInstanceMap.get(type).makePiece(x, y, status, side);
    }    

    public static CreatePiece getCreatePieceController(){
        if(createPieceController==null){createPieceController=new CreatePiece();}
        return createPieceController;
    }

    public void instantiatePieces(){ // create the piece and put it on the board

        gameControl.initializePiece("PlusPiece", 5, 0, 'A', 'Y');
        gameControl.initializePiece("PlusPiece", 5, 6, 'A', 'Y');
        gameControl.initializePiece("PlusPiece", 0, 0, 'A', 'B');
        gameControl.initializePiece("PlusPiece", 0, 6, 'A', 'B');
        gameControl.initializePiece("HourglassPiece", 5, 1, 'A', 'Y');
        gameControl.initializePiece("HourglassPiece", 5, 5, 'A', 'Y');
        gameControl.initializePiece("HourglassPiece", 0, 1, 'A', 'B');
        gameControl.initializePiece("HourglassPiece", 0, 5, 'A', 'B');
        gameControl.initializePiece("TimePiece", 5, 2, 'A', 'Y');
        gameControl.initializePiece("TimePiece", 5, 4, 'A', 'Y');
        gameControl.initializePiece("TimePiece", 0, 2, 'A', 'B');
        gameControl.initializePiece("TimePiece", 0, 4, 'A', 'B');
        gameControl.initializePiece("SunPiece", 0, 3, 'A', 'B');
        gameControl.initializePiece("SunPiece", 5, 3, 'A', 'Y');
        for (int column = 0; column < 7; column++) {
            gameControl.initializePiece("PointPiece", 4, column, 'A', 'Y');
            gameControl.initializePiece("PointPiece", 1, column, 'A', 'B');
        }   
    }
}
