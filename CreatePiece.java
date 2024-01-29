
// This Factory class is responsible for creating the pieces needed for the game.
// Singleton is also applied here to make sure every pieces' instances exist in one place.
// As a controller, this class also acts as a subsystem to the Facade class GameControl.
// Used in: GameControl.java
// Author: Luqman, Sabrina

import java.util.Map;

public class CreatePiece{

    private static CreatePiece createPieceController;
    private GameControl gameControl;

    // Store the reference of a constructor to each of the Key's class.
    // Written by: Luqman
    private static final Map<String, PieceMaker> pieceInstanceMap=Map.of(
        "PlusPiece", PlusPiece::new,
        "TimePiece", TimePiece::new,
        "SunPiece", SunPiece::new,
        "HourglassPiece", HourglassPiece::new,
        "PointPiece", PointPiece::new
    );

    // Constructor.
    // Written by: Luqman
    public CreatePiece(){}
    
    // Setting Facade class.
    // Written by: Luqman
    public void setGameControl(GameControl gameControl){
        this.gameControl=gameControl;
    }

    // Get the singleton object.
    // Written by: Luqman
    public static CreatePiece getCreatePieceController(){
        if(createPieceController==null){createPieceController=new CreatePiece();}
        return createPieceController;
    }

    // Return an instance of a piece based on its type.
    // Written by: Luqman
    public Piece createPiece(String type, int x, int y, char status, char side){
        return pieceInstanceMap.get(type).makePiece(x, y, status, side);
    }    

    // Create a piece and putting the icon on the board.
    // Written by: Sabrina
    public void initializePiece(String pieceType, int x, int y, char status, char side) {
        Piece p=createPieceController.createPiece(pieceType, x, y, status, side);
        gameControl.setPieceAtTile(p);
    }
    
    // Create pieces and putting it on the board.
    // Written by: Sabrina
    public void instantiatePieces(){

        initializePiece("PlusPiece", 5, 0, 'A', 'Y');
        initializePiece("PlusPiece", 5, 6, 'A', 'Y');
        initializePiece("PlusPiece", 0, 0, 'A', 'B');
        initializePiece("PlusPiece", 0, 6, 'A', 'B');
        initializePiece("HourglassPiece", 5, 1, 'A', 'Y');
        initializePiece("HourglassPiece", 5, 5, 'A', 'Y');
        initializePiece("HourglassPiece", 0, 1, 'A', 'B');
        initializePiece("HourglassPiece", 0, 5, 'A', 'B');
        initializePiece("TimePiece", 5, 2, 'A', 'Y');
        initializePiece("TimePiece", 5, 4, 'A', 'Y');
        initializePiece("TimePiece", 0, 2, 'A', 'B');
        initializePiece("TimePiece", 0, 4, 'A', 'B');
        initializePiece("SunPiece", 0, 3, 'A', 'B');
        initializePiece("SunPiece", 5, 3, 'A', 'Y');
        for (int column = 0; column < 7; column++) {
            initializePiece("PointPiece", 4, column, 'A', 'Y');
            initializePiece("PointPiece", 1, column, 'A', 'B');
        }  
    }
}
