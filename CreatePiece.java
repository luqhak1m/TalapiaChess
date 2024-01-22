
// Factory method

import java.util.Map;

public class CreatePiece{

    private static final Map<String, PieceMaker> pieceInstanceMap=Map.of(
        "PlusPiece", PlusPiece::new, // "PlusPiece" will hold an object of PlusPiece
        "TimePiece", TimePiece::new, // vv
        "SunPiece", SunPiece::new,
        "HourglassPiece", HourglassPiece::new,
        "PointPiece", PointPiece::new
    );

    public static Piece createPiece(String type, int x, int y, char status, char side){
        return pieceInstanceMap.get(type).makePiece(x, y, status, side);
    }
}
